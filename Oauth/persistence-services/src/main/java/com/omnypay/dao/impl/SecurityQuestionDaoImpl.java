package com.omnypay.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.omnypay.dao.SecurityQuestionDao;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

@Repository("secQuestionDao")
public class SecurityQuestionDaoImpl extends BaseDaoImpl implements
										SecurityQuestionDao {
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	public SecurityQuestionDaoImpl() {
	}

	
	

	/***
	 * to getting default  SecurityQuestions  list from database 
	 * 
	 * @return secQuestionMasterList  List<CloudSvrSecQuesMaster>  object having all default security questions
	 * @throws CloudDAException
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<CloudSvrSecQuesMaster> getSecurityQuestionsDao() throws CloudDAException{
		
		String METHOD_NAME="getSecurityQuestionsDao";
		
		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		List<CloudSvrSecQuesMaster> secQuestionMasterList = null;
		
		try {
			
			secQuestionMasterList = (List<CloudSvrSecQuesMaster>)super.getHibernateTemplate().
											find("from CloudSvrSecQuesMaster");
		} catch (DataAccessException accessException) {
          
			getSecurityQuestionsDaoDAException(accessException);
			

		} catch (Exception ex) {
			getSecurityQuestionsDaoException(ex);
		}
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return secQuestionMasterList;
		
	}

	
	
	/***
	 * to find Security Questions ById
	 * 
	 * @return secQuestionMasterList  List<CloudSvrSecQuesMaster>  object having all default security questions
	 * @throws CloudDAException
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<CloudSvrSecQuesMaster> findSecurityQuestionsByIdsDao(String ids) throws CloudDAException {
		
        String METHOD_NAME="findSecurityQuestionsByIdsDao";
		
		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		List<CloudSvrSecQuesMaster> quesMasterList = null;
		
		String listid = ids.substring(0, ids.length()-1);
		
		try{
			 quesMasterList = (List<CloudSvrSecQuesMaster>)super.getHibernateTemplate().find("from CloudSvrSecQuesMaster c where c.id in("+listid+")");
			
						
		}
		 catch (DataAccessException accessException) {
				
				
			 findSecurityQuestionsByIdsDaoDAException(accessException);
				
			} catch (Exception ex) {
				
				
				findSecurityQuestionsByIdsDaoException(ex);
				
			}
		return quesMasterList;
	}

	
	
	
	
	///////////////// private methods for Exception /////////////////
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getSecurityQuestionsDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getSecurityQuestionsDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting Security Questions")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_SECURITY_QUESTIONS,
				accessException.getMessage());
		
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getSecurityQuestionsDaoException(
			Exception accessException) throws CloudDAException {
		String METHOD_NAME = "getSecurityQuestionsDaoException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting Security Questions")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_SECURITY_QUESTIONS,
				accessException.getMessage());
		
		
	}
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  findSecurityQuestionsByIdsDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "findSecurityQuestionsByIdsDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while finding security questions by ids ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS_BY_ID)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_SECURITY_QUESTIONS_BY_ID,
				accessException.getMessage());
		
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  findSecurityQuestionsByIdsDaoException(
			Exception ex) throws CloudDAException {
		String METHOD_NAME = "findSecurityQuestionsByIdsDaoException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while finding security questions by ids")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS_BY_ID)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_SECURITY_QUESTIONS_BY_ID,
				ex.getMessage());
		
		
	}
	
}
