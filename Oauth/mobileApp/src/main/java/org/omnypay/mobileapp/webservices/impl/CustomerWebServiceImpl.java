/**
 * 
 */
package org.omnypay.mobileapp.webservices.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;













/*
import org.omnypay.httpclient.dto.CardOnFileDTO;
import org.omnypay.httpclient.dto.HceTokenDTO;
import org.omnypay.httpclient.dto.HceTokenRespDTO;
import org.omnypay.httpclient.services.CardOnFileService;
import org.omnypay.httpclient.services.HceTokenService;
import org.omnypay.httpclient.util.IntegrationException;*/
























import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.CustomerWebService;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.service.CommonService;
import com.omnypay.business.service.HceTokenService;
import com.omnypay.business.service.PasswordService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.service.impl.MitekServerService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.common.services.BaseWebServiceImpl;
import com.omnypay.common.services.CommonHelper;
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
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;
import com.omnypay.merchant.services.MerchantCustomerService;
import com.omnypay.merchant.services.MerchantUserServices;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @author iliyasm
 *
 */
@Component
@Path("/cust")
public class CustomerWebServiceImpl implements 
		CustomerWebService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	// constructor
	public CustomerWebServiceImpl() {
	}

	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
			
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HceTokenService hceService;
	
	@Autowired
	MerchantCustomerService merchantCustomerService;
	
	
	
	
	
	
	
	/**
	 * 
	 * org.omnypay.mobileapp.webservices.CustomerWebService#fetchCustomerDetails
	 * (org .omnypay .mobileapp.dto.BaseDTO)
	 * Returns response object that will send to the mobile containing all the details about a user. 
	 * fetchCustomerDetails request from mobile send response back to mobile
	 * 
	 * @param   BaseDTO object containing all the parameters send from mobile to fetch the customer details     
	 * @return  response object to the mobile
	 * 
	 * 
	 * 
	 */

	@Path("/fetchCust")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchCustomerDetails(BaseDTO baseDTO) {

		String METHOD_NAME = "fetchCustomerDetails";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		CustomerRespDTO status = null;
		
		Response response = null;

		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(baseDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
			//	CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(baseDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

			
					status = merchantCustomerService.fetchCustomerDetails(merchantDTO, baseDTO);
					
					
			
				// for external merchant
				} else 	if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					CloudSvrUser cloudSvrUser = this.getUserId(baseDTO);

					if (cloudSvrUser != null) {

						String IsSecuirty = isSecurity(cloudSvrUser);

						UpdateUserDTO updateProfile = getUpdateProfileData(cloudSvrUser);

						List<AccountDTO> accountList = getDefaultCardList(cloudSvrUser);

						List<AccountDTO> accTokenLists = getTokenForAllCard(
								accountList, cloudSvrUser);

						status = this.getStatusFetchCustomerDetails(
								accTokenLists, accTokenLists, IsSecuirty,
								updateProfile);

					} else {
						
						status =CloudServiceStatus.customerStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.GET_USER));
					}

				} else {

					status = CloudServiceStatus.customerStatus(CloudServiceStatus
							.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus.customerStatus(CloudServiceStatus
						.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}
			
		}  catch (CloudServiceException cloudServiceException) {

			
			String message = "businessException occured while fetch customer detail";
			
			status = CloudServiceStatus.customerStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			
           CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);
			
		
		} catch (Exception ex) {
			
			status = CloudServiceStatus.customerStatus(CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.FETCH_CUSTOMER));

			String message = "Exception occured while fetch customer detail";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);
		}

		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /Private methods Start here//////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /Private methods Start here//////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /Private methods Start here//////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /Private methods Start here//////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /Private methods Start here//////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	
	
///////////////////////////service service service service//////////////////////////////////////////////////////////////////////
///////////////////////////service service service service////////////////////////////////////////////////////////////////
///////////////////////////service service service service////////////////////////////////////////////////////////////////
///////////////////////////service service service service////////////////////////////////////////////////////////////////
///////////////////////////service service service service////////////////////////////////////////////////////////////////
///////////////////////////service service service service////////////////////////////////////////////////////////////////
///////////////////////////                 start         ////////////////////////////////////////////////////////////////////// 	







	
/*//to get user service	
private UserService getUserService() {

// business layer integration
UserService userService = (UserService) super.getApplicationContext()
.getBean(WebServiceConstants.USER_SERVICE);

return userService;
}
*/



/*//to get account service for customer object
private AccountService getAccountService() {

	// business layer integration
	AccountService accountService = (AccountService) super
			.getApplicationContext().getBean(
					WebServiceConstants.ACCOUNT_SERVICE);

	return accountService;
}
*/




/*//to get common service from the common object
private CommonService GetCommonService() {

	// business layer integration

	CommonService commonService = (CommonService) super
			.getApplicationContext().getBean(
					WebServiceConstants.COMMON_SERVICE);

	return commonService;
}
*/



/*//to get hce service from the common object
private HceTokenService GetHceService() {

	// business layer integration
	HceTokenService hceService = (HceTokenService) super
			.getApplicationContext().getBean(
					WebServiceConstants.HCE_SERVICE);

	return hceService;
}
*/



///////////////////////////                 end         ////////////////////////////////////////////////////////////////////// 		
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////


	
	
	
	



//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
///////////////////////////                 start         //////////////////////////////////////////////////////////////////////	

	
	
/***
 *  to get merchant access key in database exist or not
 * 
 * @param accessKey represent the access key from mobile
 * @return True/false
 * @throws CloudServiceException
 */
/*private CloudSvrBusinessEntityInfo accessKeyForMerchantExistService(String accessKey) throws CloudServiceException{

	boolean keyExist = false;

	CommonService commonService = GetCommonService();

	CloudSvrBusinessEntityInfo entityInfo = CommonHelper
			.convertFromDTOtoBOForMerchantAccessKey(accessKey);

	entityInfo = commonService.isAccessKeyForMerchantExistService(entityInfo);
	return entityInfo;
}*/











	
	
	

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

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

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

	
	
	
	
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
///////////////////////////                 start         ///////////////////////////////////////////////////////////	
	
	
	
	

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

	
	
	
	
	
	
///////////////////////////                 end         ///////////////////////////////////////////////////////////	
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
	

	
	
	
	
	
	
	


	
	
}
