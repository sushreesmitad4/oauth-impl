/**
 * 
 */
package com.omnypay.merchant.kohls.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.PasswordService;
import com.omnypay.business.service.SecurityQuestionService;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.PasswordDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.merchant.common.IMerchantPasswordService;
import com.omnypay.merchant.kohls.util.MerchantConstants;
import com.omnypay.merchant.kohls.util.MerchantUtil;

/**
 * @author iliyasm
 *
 */

@Component
public class KohlPasswordServiceImpl implements IMerchantPasswordService{
	
	
	@Autowired
	PasswordService passwordService;
	
	
	
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
	public Status  forgotPassword(PasswordDTO passwordDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
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
	public Status changePassword(PasswordDTO passwordDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
	}

}
