/**
 * 
 */
package com.omnypay.merchant.kohls.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.service.HceTokenService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.common.services.CustomerHelper;
import com.omnypay.common.services.TransactionHelper;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CustomerRespDTO;
import com.omnypay.common.services.dto.HceTokenDTO;
import com.omnypay.common.services.dto.HceTokenRespDTO;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jConstants;
import com.omnypay.merchant.common.IMerchantCustomerService;
import com.omnypay.merchant.kohls.util.MerchantConstants;
import com.omnypay.merchant.kohls.util.MerchantUtil;

/**
 * @author iliyasm
 *
 */
public class KohlCustomerServiceImpl  implements IMerchantCustomerService{
	
	
	
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private AccountService accountService;
	
	
	@Autowired
	private HceTokenService hceService;

	
	
	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public CustomerRespDTO fetchCustomerDetails(BaseDTO baseDTO) {
		
		
		CustomerRespDTO status = null;
		
		
		CloudSvrUser cloudSvrUser = null;
		try {
			cloudSvrUser = this.getUserId(baseDTO);
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (cloudSvrUser != null) {

			String IsSecuirty = isSecurity(cloudSvrUser);

			UpdateUserDTO updateProfile = getUpdateProfileData(cloudSvrUser);

			List<AccountDTO> accountList = null;
			try {
				accountList = getDefaultCardList(cloudSvrUser);
			} catch (IntegrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<AccountDTO> accTokenLists = null;
			try {
				accTokenLists = getTokenForAllCard(
						accountList, cloudSvrUser);
			} catch (IntegrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			status = this.getStatusFetchCustomerDetails(
					accTokenLists, accTokenLists, IsSecuirty,
					updateProfile);

		} else {
			
			status = MerchantUtil.getCustomerRespStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
		
		}
		
		// TODO Auto-generated method stub
		return status;
	}
	
	
	
	
	
	
	/***
	 * To get user object 
	 * 
	 * @param BaseDTO contain all the parameters to get user
	 * @return CloudSvrUser object
	 */
	    
		private CloudSvrUser getUserId(BaseDTO baseDTO) throws CloudServiceException {

			CloudSvrUser user = UserHelper.convertFromDTOtoBO(baseDTO);

			//UserService userService = this.getUserService();

			CloudSvrUser userId = userService.getUserService(user);

			return userId;
		}
	

		
		
		/***
		 * to check Security question set or not
		 * 
		 * @param  cloudSvrUser represent  CloudSvrUser object having info about user
		 * @return String as true/false
		 */
		private String isSecurity(CloudSvrUser cloudSvrUser) {

			String isTure = "false";

			if (cloudSvrUser.getListSecQuestion().size() != 0) {

				isTure = "true";
			}

			return isTure;
		}
		
		
		/***
		 * to check user has updated his profile or not 
		 * 
		 * @param  cloudSvrUser represent  CloudSvrUser object having info about user
		 * @return updateUserDTO UpdateUserDTO having info about user update profile
		 */
		private UpdateUserDTO getUpdateProfileData(CloudSvrUser cloudSvrUser) {

			UpdateUserDTO updateUserDTO = null;

			if (cloudSvrUser.getUsersProfile() != null) {

				// conversion from DTO to BO
				updateUserDTO = UserHelper.convertBOUpdateUserProftoFromDTO(
						cloudSvrUser.getUsersProfile(), cloudSvrUser);

			} else {

				if (cloudSvrUser.getEmailId() != null) {

					updateUserDTO = new UpdateUserDTO();
					updateUserDTO.setEmailId(cloudSvrUser.getEmailId());

				}

			}

			return updateUserDTO;
		}
		
		
		
		
		  /***
		   * To return list of card/bank/gift card of a user
		   *  
		   * @param CloudSvrUser object
		   * @return List<AccountDTO> containing account details of an user
		   */
			private List<AccountDTO> getDefaultCardList(CloudSvrUser cloudSvrUser) throws IntegrationException{

				List<AccountDTO> accountList = null;

				CardOnFileDTO cofAccount = CustomerHelper
						.convertFromDTOtoBO(cloudSvrUser.getUserId());

			
				//AccountService accountService = getAccountService();

				List<CardOnFileDTO> cofCardList = accountService
						.getAccountDetailsService(cofAccount);

				accountList = CustomerHelper.converFromBOListToDTOList(cofCardList);

				return accountList;
			}
			
			
			
			/***
			 * to get list for all the cards
			 * 
			 * @param  cloudSvrUser represent  CloudSvrUser object having info about user
			 * @return updateUserDTO UpdateUserDTO having info about user update profile
			 */
			private List<AccountDTO> getTokenForAllCard(List<AccountDTO> accountList,
					CloudSvrUser userId) throws IntegrationException{

				List<AccountDTO> cardList = new ArrayList<AccountDTO>();

				if (accountList != null && accountList.size() != 0) {

					for (AccountDTO accdto : accountList) {

						if (accdto.getAccType().equalsIgnoreCase(
								WebServiceConstants.CREDIT_CARD_TYPE)
								|| accdto.getAccType().equalsIgnoreCase(
										WebServiceConstants.DEBIT_CARD_TYPE)
								|| accdto.getAccType().equalsIgnoreCase(
										WebServiceConstants.GIFT_PRIVATE_LAB_CARD_TYPE)) {

							cardList.add(accdto);

						}

					}

				}

				// card list for get offline token

				if (cardList != null && cardList.size() != 0) {

					List<AccountDTO> cardLists = new CopyOnWriteArrayList<AccountDTO>();
					// getting offline tokens for each card
					List<HceTokenRespDTO> cardTokens = this.getOfflineTokenForAllCards(
							cardList, userId);

					for (AccountDTO cardlists : cardList) {
						for (HceTokenRespDTO hceTokenRespDTO : cardTokens) {
							if (cardlists.getAcctId().equalsIgnoreCase(
									hceTokenRespDTO.getAccountRefId())) {
								cardlists.setTokenexpDate(hceTokenRespDTO
										.getTokenexpDate());
								cardlists.setTokens(hceTokenRespDTO.getTokens());
								// cardlists.setAcctId(hceTokenRespDTO.getAccountRefId());
								cardlists.setUdk(hceTokenRespDTO.getUdk());
							}

						}
						cardLists.add(cardlists);
					}

				}

				return accountList;

			}
			
			
			
			/***
			   * this method is taking list of card based upon user and
			   * give us offline token for every card three token in connecting to hce server
			   *  
			   * @param userId CloudSvrUser object having all the user information
			   * @param  cardList list of cards related to an user
			 * @throws IntegrationException 
			   * 
			   **/
			private List<HceTokenRespDTO> getOfflineTokenForAllCards(
					List<AccountDTO> cardList, CloudSvrUser userId) throws IntegrationException {
				List<HceTokenRespDTO> token = null;
				// TODO Auto-generated method stub
				String METHOD_NAME = "getOfflineTokenForAllCards";

				//log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

				List<HceTokenDTO> hceTokenDTO = TransactionHelper
						.convertFromDTOtoBOOfflineTokenForAllCards(cardList, userId);

				//HceTokenService hceService = GetHceService();

				
					token = hceService.getOfflineTokenForAllCardsService(hceTokenDTO);

				
				return token;
			}
			
			
			
			/***
			   * To get the status for a user
			   *  
			   * @param  accTokenLists object having token information
			   * @param  accountList list of cards related to an user
			   * @param  IsSecuirty String give info about security questions set or not
			   * @param  updateProfile represent user profile update or not
			   * @return CustomerRespDTO object having all the response and other details about the user
			   **/
			private CustomerRespDTO getStatusFetchCustomerDetails(
					List<AccountDTO> accountList, List<AccountDTO> accTokenLists,
					String IsSecuirty, UpdateUserDTO updateProfile) {
				CustomerRespDTO status = null;

				if (accTokenLists != null && accTokenLists.size() != 0
						|| updateProfile != null) {

					// get status to send to client
					status = WebServiceUtil.getCustomerResp(
							WebServiceConstants.RECORDS_FOUND, WebServiceConstants.ONE,
							accTokenLists, IsSecuirty, updateProfile);
				} else if (accTokenLists.size() == 0) {

					status = WebServiceUtil.getCustomerResp(
							WebServiceConstants.HCE_SERVER_ERROR,
							WebServiceConstants.TWO, null, IsSecuirty, updateProfile);

				}

				else {
					status = WebServiceUtil.getCustomerResp(
							WebServiceConstants.RECORDS_NOT_FOUND,
							WebServiceConstants.TWO, accTokenLists, null, null);

				}

				return status;

			}

}
