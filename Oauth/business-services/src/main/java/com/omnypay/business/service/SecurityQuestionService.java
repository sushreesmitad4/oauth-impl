package com.omnypay.business.service;

import java.util.List;

import com.omnypay.business.util.CloudServiceException;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.bo.CloudSvrUser;

public interface SecurityQuestionService {
	
	
	
	// getting default  SecurityQuestions  list from database 
	List<CloudSvrSecQuesMaster> getSecurityQuestionsService() throws CloudServiceException;

}
