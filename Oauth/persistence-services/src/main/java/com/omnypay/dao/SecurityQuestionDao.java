package com.omnypay.dao;

import java.util.List;

import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.util.CloudDAException;

public interface SecurityQuestionDao {

	
	
	// getting default  SecurityQuestions  list from database 
	List<CloudSvrSecQuesMaster> getSecurityQuestionsDao() throws CloudDAException;
	
	//getting  SecurityQuestions  list from database by id
	List<CloudSvrSecQuesMaster> findSecurityQuestionsByIdsDao(String ids)throws CloudDAException;
}
