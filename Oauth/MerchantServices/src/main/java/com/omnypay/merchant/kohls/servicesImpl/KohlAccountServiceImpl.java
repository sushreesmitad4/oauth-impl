/**
 * 
 */
package com.omnypay.merchant.kohls.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.AccountHelper;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.merchant.common.IMerchantAccountService;
import com.omnypay.merchant.kohls.util.MerchantConstants;
import com.omnypay.merchant.kohls.util.MerchantUtil;

/**
 * @author iliyasm
 *
 */
public class KohlAccountServiceImpl  implements IMerchantAccountService {

	
	
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	
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
	public AccountRespDTO addCard(AccountDTO accountDTO) {

		// TODO Auto-generated method stub
		
		 AccountRespDTO status = null;

		try {

			CloudSvrUser user = getUser(accountDTO);

			if (user != null) {

				CardOnFileDTO cofAccount = AccountHelper
						.convertFromDTOtoCOFDTOAddCardAndBank(accountDTO,
								user.getUserId());

				CardOnFileRespDTO response = accountService.addAccountService(cofAccount);
				
				status = AccountHelper.addCard(response);
		}
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

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
	public AccountRespDTO addBank(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getAccountRespStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
	}

	
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
	public AccountRespDTO deleteAccount(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getAccountRespStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
	}

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
	public AccountRespDTO getMoreOffLineToken(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getAccountRespStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
	}

	
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
	public AccountRespDTO checkScanRequest(AccountDTO accountDTO) {
		
		// TODO Auto-generated method stub
		return MerchantUtil.getAccountRespStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
	}
	
	
	
	
	
	/***
	 * to get user deatils
	 * 
	 * @param AccountDTO
	 * @return CloudSvrUser object
	 * @throws CloudServiceException
	 */
	// to get userID
	private CloudSvrUser getUser(AccountDTO accountDTO)
			throws CloudServiceException {

		CloudSvrUser user = null;

		// conversion from DTO to BO
		user = UserHelper.convertFromDTOtoBO(accountDTO);

		//UserService userService = this.getUserService();

		user = userService.getUserService(user);

		return user;
	}

}
