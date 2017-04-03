/**
 * 
 */
package com.omnypay.merchant.kohls.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.SecurityQuestionService;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.SecurityQuestionsDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.merchant.common.IMerchantSecurityQuestionService;
import com.omnypay.merchant.kohls.util.MerchantConstants;
import com.omnypay.merchant.kohls.util.MerchantUtil;

/**
 * @author iliyasm
 *
 */

@Component
public class KohlSecurityQuestionServiceImpl implements IMerchantSecurityQuestionService{

	
	
	@Autowired
	SecurityQuestionService securityQuestionService;
	
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
	public SecurityQuestionsRespDTO fetchSecurityQuestion(BaseDTO baseDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getSecurityQuestionsStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
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
	public SecurityQuestionsRespDTO getSecurityQuestions(BaseDTO baseDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getSecurityQuestionsStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
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
	public SecurityQuestionsRespDTO setSecurityQuestions(
			SecurityQuestionsDTO securityQuestionsDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getSecurityQuestionsStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
	}

}
