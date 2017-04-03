package com.omnypay.merchant.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.SecurityQuestionsDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.merchant.common.IMerchantSecurityQuestionService;
import com.omnypay.merchant.common.IMerchantUserServices;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;

public class MerchantSecurityQuestionService {
	
	
	@Autowired
	private MerchantServiceRoute merchantServiceRoute;
	
	
	public SecurityQuestionsRespDTO fetchSecurityQuestion(MerchantAccessDTO merchantDto,BaseDTO baseDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantSecurityQuestionService mechantSecurityQuestionServices = merchantServiceRoute.createServiceSecurityQuestionObject(merchantDto);
			
			if(mechantSecurityQuestionServices != null) {
				
				return mechantSecurityQuestionServices.fetchSecurityQuestion(baseDTO);
			
			}
		}
		return MerchantUtil.getSecurityQuestionsStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	public SecurityQuestionsRespDTO setSecurityQuestions(MerchantAccessDTO merchantDto,SecurityQuestionsDTO securityQuestionsDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantSecurityQuestionService mechantSecurityQuestionServices = merchantServiceRoute.createServiceSecurityQuestionObject(merchantDto);
			
			if(mechantSecurityQuestionServices != null) {
				
				return mechantSecurityQuestionServices.setSecurityQuestions(securityQuestionsDTO);
			
			}
		}
		return MerchantUtil.getSecurityQuestionsStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}



	public SecurityQuestionsRespDTO getSecurityQuestions(MerchantAccessDTO merchantDto,BaseDTO baseDTO) throws MerchantServiceException{
	
	if(merchantServiceRoute!= null)
	{
		IMerchantSecurityQuestionService mechantSecurityQuestionServices = merchantServiceRoute.createServiceSecurityQuestionObject(merchantDto);
		
		if(mechantSecurityQuestionServices != null) {
			
			return mechantSecurityQuestionServices.getSecurityQuestions(baseDTO);
		
		}
	}
	return MerchantUtil.getSecurityQuestionsStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
	}
	
	
	
	
	

}
