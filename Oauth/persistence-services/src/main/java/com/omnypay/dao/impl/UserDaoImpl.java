package com.omnypay.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.omnypay.dao.UserDao;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersProfile;
import com.omnypay.dao.bo.CloudSvrUsersSecQuestion;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/**
 * 
 * @author Kirank
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	// constructor
	public UserDaoImpl() {
	}

	
	
	/***
	 * to register user
	 * 
	 * @param user CloudSvrUser object having all details require to register user
	 * @return True/false
	 * @throws CloudDAException
	 */
	public boolean registerUserDao(CloudSvrUser user) throws CloudDAException {

		String METHOD_NAME = "registerUserDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		boolean isUserExists = false;

		try {
			
			
			
			
			
			user.setIsLogin(DaoConstants.IS_LOGIN_YES);
			user.setIsLocked(DaoConstants.IS_LOCKED_NO);
			user.setBadLoginCount(new BigDecimal(0));
			user.setBadSeccurityAnsCount(new BigDecimal(0));
			user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			super.getHibernateTemplate().save(user);

			

		}  catch (DataAccessException accessException) {
			
			registerUserDaoDAException(accessException);
			
			
		} catch (Exception ex) {
			registerUserDaoException(ex);
		}
		
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return isUserExists;
	}

	
	
	/***
	 * to check user exist in db or not
	 * 
	 * @param user CloudSvrUser object having all details require to register user
	 * @return True/false
	 * @throws CloudDAException
	 */
	public boolean isUserExistsDao(CloudSvrUser user) throws CloudDAException {

		String METHOD_NAME = "isUserExistsDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		boolean isUserRegstd = false;

		try {
			String userQuery = this.getRegisterDaoQuery(user);

			@SuppressWarnings("unchecked")
			List<CloudSvrUser> users = (List<CloudSvrUser>) super
					.getHibernateTemplate().find(userQuery);
			if (users.size() > 0) {

				isUserRegstd = true;
			} else {
				isUserRegstd = false;
			}
		} catch (DataAccessException accessException) {
			
			isUserExistsDaoDAException(accessException);
			
			
		} catch (Exception ex) {
			isUserExistsDaoException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return isUserRegstd;
	}

	
	
	/***
	 * to updating Wrong Attempt SecurityAns while forgot password
	 * 
	 * @param dbUser CloudSvrUser object having all details to update
	 * @return True/false
	 * @throws CloudDAException
	 */
	@Override
	public boolean updateWrongAttemptSecurityAnsDao(CloudSvrUser dbUser) throws CloudDAException{

		String METHOD_NAME = "updateWrongAttemptSecurityAnsDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		boolean isUserUpdated = false;
		// TODO Auto-generated method stub

		int badCount = dbUser.getBadSeccurityAnsCount().intValue();
		if (badCount <= 2) {

			++badCount;

			dbUser.setBadSeccurityAnsCount(new BigDecimal(badCount));

			try {
				super.getHibernateTemplate().update(dbUser);
				isUserUpdated = true;
			} catch (DataAccessException accessException) {
				
				updateWroungAttempSecuityDaoDAException(accessException);
				
				
			} catch (Exception ex) {
				updateWroungAttempSecuityDaoException(ex);
			}
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return isUserUpdated;
	}

	
	/***
	 * for user login
	 * 
	 * @param dbUser CloudSvrUser object having all details to update
	 * @return userData  CloudSvrUser having all details about user
	 * @throws CloudDAException
	 */
	public CloudSvrUser loginDao(CloudSvrUser user) throws CloudDAException{

		String METHOD_NAME = "loginDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrUser userData = null;

		StringBuilder queryString = new StringBuilder(
				"from CloudSvrUser c where");

		if (user.getEmailId() != null && !user.getEmailId().isEmpty()) {
			queryString.append(" c.emailId= '").append(user.getEmailId())
					.append("'").append(" and ");
		}
		if (user.getMobileNo() != null) {
			queryString.append(" c.mobileNo= '").append(user.getMobileNo())
					.append("' and ");
		}

		if (user.getPassCode() != null) {
			queryString.append(" c.passCode= '").append(user.getPassCode())
					.append("' and ");
		}
		if (user.getImeiNo() != null) {
			queryString.append(" c.imeiNo= '").append(user.getImeiNo())
					.append("' and ");
		}
		if (user.getIsLogin() != null) {
			queryString.append(" c.isLogin= '").append(user.getIsLogin())
					.append("' and ");
		}
		if (user.getIsLocked() != null) {
			queryString.append(" c.isLocked= '").append(user.getIsLocked())
					.append("'");
		}
		try {
			@SuppressWarnings("unchecked")
			List<CloudSvrUser> users = (List<CloudSvrUser>) super
					.getHibernateTemplate().find(queryString.toString());
			if (users.size() > 0) {
				userData = users.get(0);
			}
		} catch (DataAccessException accessException) {
			
			loginDaoDAException(accessException);
			
			
		} catch (Exception ex) {
			loginDaoException(ex);
		}
		return userData;
	}

	
	/***
	 * for user logout
	 * 
	 * @param user CloudSvrUser object having all details to update
	 * 
	 * @throws CloudDAException
	 */
	public void logoutDao(CloudSvrUser user) throws CloudDAException {

		String METHOD_NAME = "logoutDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		boolean isLogout = false;

		CloudSvrUser dbUser = this.getUserDao(user);
		try {
			if (dbUser != null) {

				dbUser.setIsLogin(user.getIsLogin());

				super.getHibernateTemplate().update(dbUser);

				isLogout = true;
			}
		}

		catch (DataAccessException accessException) {
			
			logoutDaoDAException(accessException);
			

		} catch (Exception ex) {
			
			logoutDaoException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
	}

	
	/***
	 * to get user from database
	 * 
	 * @param user CloudSvrUser object 
	 * @return userData CloudSvrUser object 
	 * @throws CloudDAException
	 */
	@SuppressWarnings("unchecked")
	public CloudSvrUser getUserDao(CloudSvrUser user) throws CloudDAException {

		String METHOD_NAME = "getUserDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		
		CloudSvrUser userData = null;
		
		List<CloudSvrUser> users = null;

		String userQuery = this.getUserDaoQuery(user);

		

		try {
			
			users =  (List<CloudSvrUser>) super	.getHibernateTemplate().find(userQuery);
			
			if (users != null & users.size() > 0) { 
				
				userData = users.get(0);
			}
			
			} catch (DataAccessException accessException) {
				
				getUserDaoDAException(accessException);
				
				
			} catch (Exception ex) {
				
				getUserDaoException(ex);
				
			}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return userData;
	}

	
	/***
	 * for user User update Email
	 * 
	 * @param user CloudSvrUser object having all details to get User update Email
	 * @param userEmail CloudSvrUser object having all details about User update Email
	 * @throws CloudDAException
	 */
	public CloudSvrUser getUserupdateEmailDao(CloudSvrUser user) throws CloudDAException {

		String METHOD_NAME = "getUserupdateEmailDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String userQuery = this.getUserDaoQuery(user);

		CloudSvrUser userEmail = null;

		try {
			@SuppressWarnings("unchecked")
			List<CloudSvrUser> users = (List<CloudSvrUser>) super
					.getHibernateTemplate().find(userQuery);
			if (users != null & users.size() > 0) {
				userEmail = users.get(0);
			}

		} catch (DataAccessException accessException) {
			
			getUserupdateEmailDaoDAException(accessException);
			
		} catch (Exception ex) {
			getUserupdateEmailDaoException(ex);
		}
		return userEmail;
	}

	

	/***
	 * for getting  User update sec
	 * 
	 * @param user CloudSvrUsersSecQuestion object having all details to get User update sec
	 * @retun  updateUser CloudSvrUsersSecQuestion object having all details to get User update sec
	 * 
	 * @throws CloudDAException
	 */
	public CloudSvrUsersSecQuestion getUserupdatesec(
			CloudSvrUsersSecQuestion user) throws CloudDAException {

		String METHOD_NAME = "getUserupdatesec";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String userQuery = this.getSecQuery(user);

		CloudSvrUsersSecQuestion updateUser = null;
		try {
			@SuppressWarnings("unchecked")
			List<CloudSvrUsersSecQuestion> users = (List<CloudSvrUsersSecQuestion>) super
					.getHibernateTemplate().find(userQuery);
			if (users != null & users.size() > 0) {

				updateUser = users.get(0);
			}
		}  catch (DataAccessException accessException) {
			
			
			getUserupdatesecDaoDAException(accessException);
			
		} catch (Exception ex) {
			getUserupdatesecDaoException(ex);
		}
		return updateUser;
	}

	
	/***
	 * for updating  UserWrongAttempt
	 * 
	 * @param user CloudSvrUsersSecQuestion object having all details to get User update sec
	 * @param updateUser CloudSvrUsersSecQuestion object having all details to get User update sec
	 * @throws CloudDAException
	 */

	@Override
	public void updateUserWrongAttemptDao(CloudSvrUser user) throws CloudDAException{

		String METHOD_NAME = "updateUserWrongAttemptDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrUser dbUser = null;

		dbUser = this.getUserDao(user);

		int badCount = user.getBadLoginCount().intValue();

		++badCount;

		dbUser.setBadLoginCount(new BigDecimal(badCount));

		try {
			super.getHibernateTemplate().update(dbUser);

		}  catch (DataAccessException accessException) {
			
			updateWroungAttempSecuityDaoDAException(accessException);
			
			
		} catch (Exception ex) {
			updateWroungAttempSecuityDaoException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

	}

	
	/***
	 * to get getUserList
	 * 
	 * @param user CloudSvrUser object having all details to get getUserList
	 * @return users List<CloudSvrUser> object having all details of list of users
	 * @throws CloudDAException
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<CloudSvrUser> getUserListDao(CloudSvrUser user)
			throws CloudDAException {

		String METHOD_NAME = "getUserListDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		// TODO Auto-generated method stub
		List<CloudSvrUser> users = null;
		try {
			String userQuery = this.getUserDaoQuery(user);

			users = (List<CloudSvrUser>) super.getHibernateTemplate().find(
					userQuery);
		} catch (DataAccessException accessException) {
               
			getUserListDaoDAException(accessException);
			

		} catch (Exception ex) {
			
			getUserListDaoException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return users;
	}

	
	
	/***
	 * for user Login
	 * 
	 * @param user CloudSvrUser object having all details about user
	 * @throws CloudDAException
	 */
	@Override
	public void userLoginDao(CloudSvrUser user) throws CloudDAException {
		String METHOD_NAME = "userLoginDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		// TODO Auto-generated method stub

		try {
			super.getHibernateTemplate().update(user);

		} catch (DataAccessException accessException) {

			getUserLoginDaoDAException(accessException);

		} catch (Exception ex) {
			
			getUserLoginDaoException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
	}

	
	/***
	 * to get user query for register user
	 * 
	 * @param user CloudSvrUser object having all details require to register user
	 * @return String
	 *
	 */
	private String getUserDaoQuery(CloudSvrUser user) {

		StringBuilder queryString = new StringBuilder(
				"from CloudSvrUser c where 1=1");

		if (user.getEmailId() != null && !user.getEmailId().isEmpty()) {
			queryString.append(" and c.emailId= '").append(user.getEmailId())
					.append("'");
		}

		if (user.getMobileNo() != null) {
			queryString.append(" and c.mobileNo= '").append(user.getMobileNo())
					.append("'");
		}

		if (user.getImeiNo() != null) {
			queryString.append(" and c.imeiNo= '").append(user.getImeiNo())
					.append("'");
		}

		if (user.getBusinessId() != null) {
			queryString.append(" and c.businessId= '").append(user.getBusinessId())
					.append("'");
		}
		return queryString.toString();

	}

	
	/***
	 * to get user query for register user
	 * 
	 * @param user CloudSvrUser object having all details require to register user
	 * @return String
	 *
	 */
	private String getRegisterDaoQuery(CloudSvrUser user) {

		StringBuilder queryString = new StringBuilder(
				"from CloudSvrUser c where 1=1");

		if (user.getMobileNo() != null) {
			queryString.append(" and c.mobileNo= '").append(user.getMobileNo())
					.append("'");
		}

		if (user.getBusinessId() != null) {
			queryString.append(" and c.businessId= '").append(user.getBusinessId())
					.append("'");
		}

		return queryString.toString();

	}
	
	/***
	 * updating userLock to yes in db
	 * 
	 * @param user CloudSvrUser object having all details about user
	 * @throws CloudDAException
	 */
	@Override
	public void userLockDao(CloudSvrUser user) throws CloudDAException {
		String METHOD_NAME = "userLockDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrUser dbUser = this.getUserDao(user);
		// TODO Auto-generated method stub
		dbUser.setIsLocked(DaoConstants.IS_LOCKED_YES);
		try {
			super.getHibernateTemplate().update(dbUser);

		} catch (DataAccessException accessException) {

			userLockDaoDAException(accessException);

		} catch (Exception ex) {
			
			userLockDaoException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
	}

	
	/***
	 * to get query for getting security questions
	 * 
	 * @param user CloudSvrUser object having all details about user
	 * @return string
	 * 
	 */
	private String getSecQuery(CloudSvrUsersSecQuestion user) {

		StringBuilder queryString = new StringBuilder(
				"from CloudSvrUsersSecQuestion c where 1=1");

		if (user.getUsersSecQuesId() != 0) {
			queryString.append(" and c.user= '")
					.append(user.getUsersSecQuesId()).append("'");
		}

		return queryString.toString();

	}

	
	/***
	 * to update user
	 * 
	 * @param user CloudSvrUser object having all details about user
	 * @throws CloudDAException
	 * 
	 */
	public void updateUserDao(CloudSvrUser user) throws CloudDAException {
		String METHOD_NAME = "updateUserDao";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		// Setting default value before saving
		CloudSvrUsersProfile userPro = user.getUsersProfile();

		userPro.setStatus(Long.parseLong(DaoConstants.STATUS_SINGLE_ONE));
		userPro.setCreatedDate(new Timestamp(System.currentTimeMillis()));

		try {
			
			super.getHibernateTemplate().saveOrUpdate(userPro);
			//super.getHibernateTemplate().saveOrUpdate(user);
			
		} catch (DataAccessException accessException) {

			updateUserDaoDAException(accessException);

		} catch (Exception ex) {
			
			updateUserDaoException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
	}
	
	
	
	
	
/////////////private methods for exception///////////////
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException registerUserDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "registerUserDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while register User ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.REGISTER_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.REGISTER_USER,
				accessException.getMessage());
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException registerUserDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "registerUserDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while  register User")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.REGISTER_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_USER,
				ex.getMessage());
	}


	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException isUserExistsDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "isUserExistsDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_USER,
				accessException.getMessage());
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException isUserExistsDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "isUserExistsDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while  getting user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_USER,
				ex.getMessage());
	}


	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException updateWroungAttempSecuityDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "updateWroungAttempSecuityDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while update wroung attemp secuity answer ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException updateWroungAttempSecuityDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "updateWroungAttempSecuityDao";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while update wroung attemp secuity answer")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.UPDATE_WRONG_ATTEMPT_SECURITYANS,
				ex.getMessage());
	}


	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException loginDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "LoginDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user try to Login ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_LOGIN,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException loginDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "LoginDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while user try to Login")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_LOGIN,
				ex.getMessage());
	}

	
	
	

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException logoutDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "logoutDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user logout ")
				.append(DaoConstants.COMMA)
				.append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGOUT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_LOGOUT,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException logoutDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "logoutDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while user logout")
				.append(DaoConstants.COMMA)
				.append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGOUT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_LOGOUT,
				ex.getMessage());
	
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getUserDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while gettting user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_USER,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "getUserDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting the user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_USER,
				ex.getMessage());
	
	}


	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserupdateEmailDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getUserupdateEmailDaoDAException";

		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while gettting user update Email ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_EMAIL)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPDATE_EMAIL,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserupdateEmailDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "getUserupdateEmailDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting user update Email")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_EMAIL)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPDATE_EMAIL,
				ex.getMessage());
	
	}

	
	
	
	
	
	
	

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserupdatesecDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getUserupdatesecDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while gettting user update secQuestion ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_SEC_QUESTION)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPDATE_SEC_QUESTION,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserupdatesecDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "getUserupdatesecDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting user update secQuestion")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_SEC_QUESTION)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPDATE_SEC_QUESTION,
				ex.getMessage());
	
	}


	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException updatingUserWrongAttemptDaoDAException(
			DataAccessException ex) throws CloudDAException {
		String METHOD_NAME = "updatingUserWrongAttemptDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("accessException occurred while updating User WrongAttempt")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT,
				ex.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException updatingUserWrongAttemptDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "updatingUserWrongAttemptDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("accessException occurred while updating User WrongAttempt")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT,
				ex.getMessage());
	
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getUserListDaoDAException(
			DataAccessException ex) throws CloudDAException {
		String METHOD_NAME = "getUserListDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting UserList ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER_LIST)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_USER_LIST,
				ex.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserListDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "getUserListDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
		.append("Exception occurred while getting UserList ")
		.append(DaoConstants.COMMA).append("Error Code : ")
		.append(ErrorCodeConstants.GET_USER_LIST)
		.append(DaoConstants.COMMA).append("Error Message : ")
		.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.GET_USER_LIST,
				ex.getMessage());
	
	}
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  getUserLoginDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getUserLoginDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user try to login ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_LOGIN,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getUserLoginDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "getUserLoginDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
		.append("Exception occurred while user try to login ")
		.append(DaoConstants.COMMA).append("Error Code : ")
		.append(ErrorCodeConstants.USER_LOGIN)
		.append(DaoConstants.COMMA).append("Error Message : ")
		.append(ex.getMessage());
       log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
        throw new CloudDAException(
		ErrorCodeConstants.USER_LOGIN,
		ex.getMessage());
	
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  userLockDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "userLockDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user lock ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOCK)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_LOCK,
				accessException.getMessage());
		
	}

	/**
	 * get Exception
	 * 
	 * @param Exception
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  userLockDaoException(
			Exception ex) throws CloudDAException {
		String METHOD_NAME = "userLockDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while user lock ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOCK)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_LOCK,
				ex.getMessage());
		
	}
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  updateUserDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "updateUserDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
				errorBuffer
				.append("DataAccessException occurred while update user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPADTE,
				accessException.getMessage());
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  updateUserDaoException(
			Exception accessException) throws CloudDAException {
		String METHOD_NAME = "updateUserDaoException";
		StringBuilder errorBuffer = new StringBuilder();
				errorBuffer
				.append("Exception occurred while update user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPADTE,
				accessException.getMessage());
		
	}



	/***
	 * to getBusinessId for the  user
	 * 
	 * @param user CloudSvrUser object having all details require to getBusinessId user
	 * @return True/false
	 * @throws CloudDAException
	 */
	public String getBusinessId(String merchantAccesskey) throws CloudDAException {
		// TODO Auto-generated method stub

		String METHOD_NAME = "getBusinessId";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String businessId = null;
		
		try {
		
		StringBuilder queryString = new StringBuilder(
				
				"select merchantId from CloudSvrTxnEntity where status=9376503 and Id=(select entityId from CloudSvrBusinessEntityInfo "
				+ "where status=9376503 and merchantKey='").append(merchantAccesskey).append("')");
		
		
		Query query = super.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(queryString.toString());

		List<String> list = query.list();
				
		for (String result : list) {

			businessId = result;
			
		}	
			

		} catch (DataAccessException accessException) {

			registerUserDaoDAException(accessException);

		} catch (Exception ex) {
			registerUserDaoException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return businessId;
	}

	







}
