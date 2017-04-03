package com.omnypay.merchant.common;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.SecurityQuestionsDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;

/**
 * @author jagdishm
 *
 */
public interface IMerchantSecurityQuestionService {
	
	
	SecurityQuestionsRespDTO fetchSecurityQuestion(BaseDTO baseDTO);
	
	
	SecurityQuestionsRespDTO getSecurityQuestions(BaseDTO baseDTO);
	
	
	SecurityQuestionsRespDTO setSecurityQuestions(SecurityQuestionsDTO securityQuestionsDTO);

}
