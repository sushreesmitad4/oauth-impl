package com.omnypay.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.omnypay.dao.PasswordDao;
import com.omnypay.dao.bo.CloudSvrPasswordsHistory;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

@Repository("passwordDao")
public class PasswordDaoImpl extends BaseDaoImpl implements PasswordDao {
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	PasswordDaoImpl() {
	}

	
	/***
	 * for ChangedPwd 
	 * 
	 * @param user  CloudSvrUser  having details of user
	 * @throws CloudDAException
	 * 
	 * 
	 */
	public void ChangedPwdDao(CloudSvrUser user) throws CloudDAException {

		String METHOD_NAME = "ChangedPwdDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {

			super.getHibernateTemplate().update(user);

		} catch (DataAccessException accessException) {

			changedPwdDaoDAException(accessException);

		} catch (Exception ex) {
			changedPwdDaoException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

	}

	
	

	/***
	 * for getPWDHistory
	 * 
	 * @param inputNewPwd user new password
	 * @return string
	 * @throws CloudDAException
	 * 
	 * 
	 */
	public String getPWDHistoryDao(String inputNewPwd) throws CloudDAException {
		String METHOD_NAME = "getPWDHistoryDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String password = null;
		// String Pwd=null;
		String userQuery = this.getUserQueryHistory(inputNewPwd);
		try {
			@SuppressWarnings("unchecked")
			List<CloudSvrPasswordsHistory> pwdlist = (List<CloudSvrPasswordsHistory>) super
					.getHibernateTemplate().find(userQuery);
			if (pwdlist != null & pwdlist.size() > 0) {
				password = DaoConstants.EXIST;
			}

			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

			password = DaoConstants.NOT_EXIST;
		} catch (DataAccessException accessException) {
         
			getPWDHistoryDaoDAException(accessException);
			

		} catch (Exception ex) {
			
			getPWDHistoryDaoException(ex);
			
		}

		return password;
	}

	
	
	/***
	 * to getUserPassword QueryHistory
	 * @param inputNewPwd represent new password
	 * @return String
	 * 
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	private String getUserQueryHistory(String inputNewPwd) {

		StringBuilder queryString = new StringBuilder(
				"from CloudSvrPasswordsHistory c where 1=1");

		if (inputNewPwd != null && !inputNewPwd.isEmpty()) {
			queryString.append(" and c.currentPassword= '").append(inputNewPwd)
					.append("'");
		}

		return queryString.toString();
	}

	
	/***
	 * to update Reset PassValString
	 * @param user  CloudSvrUser object having all data of user
	 * @throws CloudDAException
	 * 
	 * 
	 */
	public void updateResetPassValStringDao(CloudSvrUser user)
			throws CloudDAException {

		String METHOD_NAME = "updateResetPassValStringDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {

			super.getHibernateTemplate().update(user);

		} catch (DataAccessException accessException) {

			updateResetPassValStringDaoDAException(accessException);

		} catch (Exception ex) {
			
			updateResetPassValStringDaoDAException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

	}
	
	
	// /////////////// private methods for Exception /////////////////

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException changedPwdDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "changedPwdDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user doing change password  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_CHANGE_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.USER_CHANGE_PASSWORD,
				accessException.getMessage());

	}

	/**
	 * get CloudDAException
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException changedPwdDaoException(
			Exception ex) throws CloudDAException {
		String METHOD_NAME = "changedPwdDaoException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while user doing change password")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_CHANGE_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.USER_CHANGE_PASSWORD,
				ex.getMessage());

	}

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getPWDHistoryDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getPWDHistoryDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting password history  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_USER_PASSWORD,
				accessException.getMessage());

	}

	/**
	 * get CloudDAException
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getPWDHistoryDaoException(
			Exception accessException) throws CloudDAException {
		String METHOD_NAME = "getPWDHistoryDaoException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting password history  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_USER_PASSWORD,
				accessException.getMessage());

	}
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException updateResetPassValStringDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "updateResetPassValStringDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while update reset pass val string ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.UPDATE_PASS_VAL_STRING)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.UPDATE_PASS_VAL_STRING,
				accessException.getMessage());

	}

	/**
	 * get CloudDAException
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException updateResetPassValStringDaoDAException(
			Exception ex) throws CloudDAException {
		String METHOD_NAME = "updateResetPassValStringDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while update reset pass val string")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.UPDATE_PASS_VAL_STRING)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.UPDATE_PASS_VAL_STRING,
				ex.getMessage());

	}


}
