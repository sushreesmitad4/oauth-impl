package com.omnypay.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.SecurityQuestionService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.dao.SecurityQuestionDao;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;


//@Service("secQuestionService")
public class SecurityQuestionServiceImpl implements SecurityQuestionService{

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	@Autowired
	SecurityQuestionDao secQuestionDao;
	
	public SecurityQuestionServiceImpl() {
	}
	
	
	

	/**
	 * getting default  SecurityQuestions  list from database 
	 * @return cloudSvrSecQuesMaster  List<CloudSvrSecQuesMaster> object having all the default security question
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional(readOnly = true)
	public List<CloudSvrSecQuesMaster> getSecurityQuestionsService() throws CloudServiceException
			 {

		String METHOD_NAME = "getSecurityQuestionsService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		List<CloudSvrSecQuesMaster> cloudSvrSecQuesMaster = null;

		try {

			cloudSvrSecQuesMaster = this.secQuestionDao
					.getSecurityQuestionsDao();

		} catch (DataAccessException accessException) {

			getSecurityQuestionsServiceCloudServiceException(accessException);

		} catch (Exception ex) {
			getSecurityQuestionsServiceException(ex);
		}
		return cloudSvrSecQuesMaster;
	}
	
	
	
	
	////////////////////private methods for exception/////////
	/**
	 * get cloudserviceException for  getSecurityQuestions
	 * @param DataAccessException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getSecurityQuestionsServiceCloudServiceException(
			DataAccessException accessException) throws CloudServiceException {
		String METHOD_NAME = "getSecurityQuestionsServiceCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting Security Questions")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_SECURITY_QUESTIONS,
				accessException.getMessage());
	}
	
	/**
	 * get Exception for getSecurityQuestions
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getSecurityQuestionsServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getSecurityQuestionsServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting Security Questions")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_SECURITY_QUESTIONS,
				ex.getMessage());
	}
	
}
