package com.omnypay.business.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.UserService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.dao.SecurityQuestionDao;
import com.omnypay.dao.UserDao;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
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
@Service("userService")
public class UserServiceImpl implements UserService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	public UserServiceImpl() {
	}

	@Autowired
	UserDao userDao;
	@Autowired
	SecurityQuestionDao secQuestionDao;


	
	
	/**
	 * get User 
	 * @param user CloudSvrUser object having all details to get user
	 * @return users - CloudSvrUser object having the user details from database
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public CloudSvrUser getUserService(CloudSvrUser user)
			throws CloudServiceException {

		String METHOD_NAME = "getUserService";
		CloudSvrUser users = null;
		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {
			users = this.userDao.getUserDao(user);

		} catch (CloudDAException accessException) {

			 getUserCloudServiceException(accessException);
			
		} catch (Exception ex) {
			
			getUserException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return users;
	}

	
	
	
	/***
	 * to register user
	 * 
	 * @param user CloudSvrUser object having all details require to register user
	 * @return True/false
	 * @throws CloudServiceException
	 */
	private boolean registerUser(CloudSvrUser user) throws CloudServiceException {

		return this.commonRegisterService(user);
	}

	
	/**
	 * for user Login
	 * @param user CloudSvrUser object having all details to get user
	 * @return true/false
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public boolean loginService(CloudSvrUser user) throws CloudServiceException {

		String METHOD_NAME = "loginService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {
			CloudSvrUser dbUser = this.userDao.loginDao(user);

		} catch (CloudDAException accessException) {

			getLoginCloudServiceException(accessException);

		} catch (Exception ex) {
			
			getLoginException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return true;

	}

	/**
	 * for user logout
	 * @param user CloudSvrUser object having all details to get user
	 * @throws CloudServiceException in case of business validation failures
	 */
	private void logout(CloudSvrUser user) throws CloudServiceException {
		//String METHOD_NAME = "logout";
		try {

			user.setIsLogin(BusinessConstants.ISLOGOUT);

			this.userDao.logoutDao(user);
		} catch (CloudDAException accessException) {

			getLogoutCloudServiceException(accessException);

		} catch (Exception ex) {
			getLogoutException(ex);
		}
		
	}

	
	/**
	 * for user register InStore
	 * @param user CloudSvrUser object having all details to get user
	 * @return String
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public String registerUserInStoreService(CloudSvrUser user)
			throws CloudServiceException {

		String METHOD_NAME = "registerUserInStoreService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		boolean userExist = false;

		userExist = this.commonRegisterService(user);

		String respString = null;

		if (userExist) {

			respString = BusinessConstants.TWO;

		} else {

			respString = BusinessConstants.ONE;

		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;

	}

	
	/**
	 * to get Selected Security Questions
	 * @param user CloudSvrUser object having all details to get user
	 * @return List<CloudSvrSecQuesMaster> list of security questions
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public List<CloudSvrSecQuesMaster> getSelectedSecurityQuestionsService(
			CloudSvrUser user) throws CloudServiceException {

		String METHOD_NAME = "getSelectedSecurityQuestionsService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		List<CloudSvrUsersSecQuestion> dbSecQuesList = null;

		List<CloudSvrSecQuesMaster> secQuesList = null;

		StringBuilder idsBuilder = null;

		try {

			dbSecQuesList = user.getListSecQuestion();

			idsBuilder = new StringBuilder();

			for (CloudSvrUsersSecQuestion ques : dbSecQuesList) {

				idsBuilder.append(ques.getSecQuesId());
				idsBuilder.append(BusinessConstants.QUERY_APPENDER);
			}

			// size check
			if (idsBuilder != null && idsBuilder.length() != 0) {

				secQuesList = this.secQuestionDao
						.findSecurityQuestionsByIdsDao(idsBuilder.toString());
			}

			
		} catch (CloudDAException accessException) {

			getSecurityQuestionsCloudServiceException(accessException);

		} catch (Exception ex) {
			
			getSecurityQuestionsException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return secQuesList;
	}

	
	
	
	/***
	 * to register user
	 * 
	 * @param user CloudSvrUser object having all details require to register user
	 * @return True/false
	 * @throws CloudServiceException while business validation failed
	 */
	private boolean commonRegisterService(CloudSvrUser user)
			throws CloudServiceException {

		

		boolean isUserExists = false;

		try {
			isUserExists = this.userDao.isUserExistsDao(user);

			if (isUserExists) {

				return isUserExists = true;
			} else {

				isUserExists = this.userDao.registerUserDao(user);
			}

		} catch (CloudDAException accessException) {
			getRegisterUserCloudServiceException(accessException);
			
		} catch (Exception ex) {
			getRegisterUserException(ex);
		}
		
		return isUserExists;
	}

	/**
	 * to get User By IMeino
	 * @param user CloudSvrUser object having all details to get user
	 * @return userData  CloudSvrUser object having all details about a user
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public CloudSvrUser getUserByIMeino(CloudSvrUser user)
			throws CloudServiceException {

		String METHOD_NAME = "getUserByIMeino";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrUser userData = null;
		// TODO Auto-generated method stub
		try {

			userData = this.userDao.getUserDao(user);

		} catch (CloudDAException accessException) {

			getUserByImeiCloudServiceException(accessException);

		} catch (Exception ex) {
			
			getUserByImeiException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return userData;
	}

	
	/**
	 * to update User WrongAttempt while login failed
	 * @param user CloudSvrUser object having all details to get user
	 * @throws CloudServiceException in case of business validation failures
	 */
	private void updateUserWrongAttemptService(CloudSvrUser user)
			throws CloudServiceException {
		// TODO Auto-generated method stub

		//String METHOD_NAME = "updateUserWrongAttemptService";

		try {
			this.userDao.updateUserWrongAttemptDao(user);
		} catch (CloudDAException accessException) {

			getUpdateUserWrongAttemptCloudServiceException(accessException);

		} catch (Exception ex) {
			
			getUpdateUserWrongAttemptException(ex);
		}

	}

	
	/**
	 * to check if user already login or not
	 * @param user CloudSvrUser object having all details to get user
	 * @return boolean true/false
	 * @throws CloudServiceException in case of business validation failures
	 */
	private boolean isAlreadyLogedIn(CloudSvrUser user)
			throws CloudServiceException {

		//String METHOD_NAME = "isAlreadyLogedIn";

		// TODO Auto-generated method stub
		boolean isUserLogedIn = false;

		CloudSvrUser userDb = null;

		try {

			List<CloudSvrUser> users = this.userDao.getUserListDao(user);

			if (users.size() != 0) {

				userDb = users.get(0);

				if ((userDb.getIsLogin().equalsIgnoreCase(
						BusinessConstants.ISLOGIN) && userDb.getPassCode()
						.equals(user.getPassCode()))
						&& userDb.getIsLocked().equalsIgnoreCase(
								BusinessConstants.ISUNLOCKED)) {

					isUserLogedIn = true;
				}

			}

		} 

		catch (CloudDAException accessException) {
			
			isAlreadyLogedInCloudServiceException(accessException);
			
		}

		catch (Exception ex) {
			
			isAlreadyLogedInException(ex);
		}

		return isUserLogedIn;
	}

	
	/***
	 * to check if user exit or not in database
	 * 
	 * @param user CloudSvrUser object 
	 * @return true/false
	 * @throws CloudServiceException
	 */
	private boolean isUserExist(CloudSvrUser user) throws CloudServiceException {

		//String METHOD_NAME = "isUserExist";

		// TODO Auto-generated method stub
		boolean isUserExist = true;
		try {
			CloudSvrUser users = this.userDao.getUserDao(user);

			if (users != null) {

				isUserExist = false;

			}
		} catch (CloudDAException accessException) {

			isUserExistServiceException(accessException);
		}

		catch (Exception ex) {
			isUserExistException(ex);
		}

		return isUserExist;
	}

	/**
	 * to validate user 
	 * @param user CloudSvrUser object having all details to validating User
	 * @return boolean true/false
	 * @throws CloudServiceException in case of business validation failures
	 */
	private boolean validatingUser(CloudSvrUser user) throws CloudServiceException {

		//String METHOD_NAME = "validatingUser";

		// TODO Auto-generated method stub
		boolean isValdatingUser = false;

		// setting isLogin And IsLocked to N
		user.setIsLogin(BusinessConstants.ISLOGOUT);
		user.setIsLocked(BusinessConstants.ISUNLOCKED);
		try {
			CloudSvrUser users = this.userDao.loginDao(user);
			if (users != null) {

				isValdatingUser = true;

			}
		} 
		catch (CloudDAException accessException) {

			validatingUserServiceException(accessException);

		}

		catch (Exception ex) {
			validatingUserException(ex);
		}
		return isValdatingUser;

	}

	
	/**
	 * to update WrongAttempt SecurityAns while forgot password
	 * @param user CloudSvrUser object having all details to update Wrong Attempt SecurityAns
	 * @return boolean true/false
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public boolean updateWrongAttemptSecurityAnsService(CloudSvrUser user)
			throws CloudServiceException {
		// TODO Auto-generated method stub

		String METHOD_NAME = "updateWrongAttemptSecurityAnsService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		boolean status = false;

		try {

			status = this.userDao.updateWrongAttemptSecurityAnsDao(user);
		} catch (CloudDAException accessException) {

			updateWrongAttemptSecurityAnsCloudServiceException(accessException);

		} catch (Exception ex) {
			updateWrongAttemptSecurityAnsException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return status;

	}

	
	/**
	 * to update UserLock after 6  wrong attempt of password while login
	 * @param user CloudSvrUser object having all details to update UserLock
	 * @throws CloudServiceException in case of business validation failures
	 */
	private void UpdateUserLockService(CloudSvrUser user)
			throws CloudServiceException {

		try {
			this.userDao.userLockDao(user);
		} 

		catch (CloudDAException accessException) {

			updateUserLockCloudServiceException(accessException);

		}

		catch (Exception ex) {
		
			updateUserLockException(ex);
		}
	}

	/**
	 * for user login
	 * @param user CloudSvrUser object having all details to login user
	 * @throws CloudServiceException in case of business validation failures
	 */
	private void userLogin(CloudSvrUser user) throws CloudServiceException {

		//String METHOD_NAME = "userLoginService";

		
		// setting isLogin And IsLocked to N
		user.setIsLogin(BusinessConstants.ISLOGOUT);
		user.setIsLocked(BusinessConstants.ISUNLOCKED);
		try {
			CloudSvrUser myLogin = this.userDao.loginDao(user);
			// update IsLogin YES
			myLogin.setIsLogin(BusinessConstants.ISLOGIN);
			myLogin.setBadLoginCount(new BigDecimal(BusinessConstants.ZERO));

			// update
			this.userDao.userLoginDao(myLogin);
		}

		catch (CloudDAException accessException) {
			
			userLoginCloudServiceException(accessException);
		

		} catch (Exception ex) {
			userLoginException(ex);
		}

	}

	
	/**
	 * to set user Selected Security Questions
	 * @param user CloudSvrUser object having all details
	 * to set user Selected Security Questions
	 * @return true/false
	 * @throws CloudServiceException in case of business validation failures
	 * 
	 */
	private boolean setSelectedSecurityQuestions(CloudSvrUser user)
			throws CloudServiceException {
		//String METHOD_NAME = "setSelectedSecurityQuestions";

		try {
			CloudSvrUser myLogin = this.userDao.getUserupdateEmailDao(user);

			List<CloudSvrUsersSecQuestion> newListSecQuestion = new ArrayList<CloudSvrUsersSecQuestion>();

			for (CloudSvrUsersSecQuestion secQuestion : user
					.getListSecQuestion()) {

				// secQuestion.setCreatedBy(new BigDecimal(1));
				secQuestion.setCreatedDate(new Timestamp(System
						.currentTimeMillis()));
				secQuestion.setSecQuesId(secQuestion.getSecQuesId());
				secQuestion.setSecQuesAnswer(secQuestion.getSecQuesAnswer());
				secQuestion.setUser(myLogin);
				newListSecQuestion.add(secQuestion);

			}

			myLogin.setListSecQuestion(newListSecQuestion);

			myLogin.setEmailId(user.getEmailId());

			this.userDao.userLoginDao(myLogin);
		} 
		catch (CloudDAException accessException) {
			
			setSelectedSecurityQuestionsCloudServiceException(accessException);

		}

		catch (Exception ex) {
			
			setSelectedSecurityQuestionsException(ex);
		}
		// TODO Auto-generated method stub

		return true;

	}

	
	/**
	 * to update User Profile 
	 * @param userProf CloudSvrUser object having all details to update UserProfile
	 * 
	 * @throws CloudServiceException in case of business validation failures
	 * 
	 */
	private void updateUserProfile(CloudSvrUsersProfile userProf)

	throws CloudServiceException {

		//String METHOD_NAME = "updateUserProfile";

		try {
			CloudSvrUser dbUser = this.userDao.getUserDao(userProf.getUser());
			CloudSvrUsersProfile dbUserProfile = dbUser.getUsersProfile();

			if (dbUserProfile == null) {

				CloudSvrUsersProfile newUserProfile = new CloudSvrUsersProfile();
				commonSetuserdata(newUserProfile, userProf, dbUser);

				commonUpdateuser(dbUser);

			} else {

				commonSetuserdata(dbUserProfile, userProf, dbUser);

				commonUpdateuser(dbUser);
			}
		} catch (CloudDAException accessException) {

			updateUserProfileCloudServiceException(accessException);

		}

		catch (Exception ex) {
			
			updateUserProfileException(ex);
		}

	}

	/**
	 * to update User Profile 
	 * @param user CloudSvrUser object having all details to Update user
	 * 
	 * @throws CloudServiceException in case of business validation failures
	 * 
	 */
	private void commonUpdateuser(CloudSvrUser user) throws CloudServiceException {

		//String METHOD_NAME = "commonUpdateuser";

		try {

			this.userDao.updateUserDao(user);

		} 
		catch (CloudDAException accessException) {

			commonUpdateUserCloudServiceException(accessException);
		}

		catch (Exception ex) {
			commonUpdateUserException(ex);
		}
	}

	
	/**
	 * to set update user data
	 * @param dbUserProfile CloudSvrUsersProfile object having data from database
	 * @param userProfile CloudSvrUsersProfile object having updated data
	 * @param dbUser CloudSvrUser object having all the details about user
	 * 
	 * @throws CloudServiceException in case of business validation failures
	 * 
	 */
	private void commonSetuserdata(CloudSvrUsersProfile dbUserProfile,
			CloudSvrUsersProfile userProfile, CloudSvrUser dbUser) {

		dbUserProfile.setAddress1(userProfile.getAddress1());
		dbUserProfile.setAddress2(userProfile.getAddress2());
		dbUserProfile.setAddress3(userProfile.getAddress3());
		dbUserProfile.setCity(userProfile.getCity());
		dbUserProfile.setZipCode(userProfile.getZipCode());
		dbUserProfile.setState(userProfile.getState());
		dbUserProfile.setFirstName(userProfile.getFirstName());
		dbUserProfile.setLastName(userProfile.getLastName());
		// dbUser.setEmailId(userProfile.getEmailId());
		//
		dbUser.setEmailId(userProfile.getNewEmailId());         
		dbUserProfile.setUser(dbUser);

		dbUser.setUsersProfile(dbUserProfile);

	}

	
	

	/***
	 * to register user
	 * 
	 * @param user
	 *            CloudSvrUser object having all details require to register
	 *            user
	 * @return string
	 * @throws CloudServiceException
	 */
	@Transactional
	public String registerUser(UserDTO userDTO)
			throws CloudServiceException

	{
		String METHOD_NAME = "registerUserService";

		log.info(userDTO + Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String respString = null;

		try {

			String busnessId = userDao.getBusinessId(userDTO
					.getMerchantAccessKey());

			CloudSvrUser myuser = UserHelper.convertFromDTOtoBO(userDTO);
			
			myuser.setBusinessId(busnessId);
			

			boolean userExist = userDao.isUserExistsDao(myuser);

			if (userExist) {

				respString = BusinessConstants.TWO;

			} else {

				userDao.registerUserDao(myuser);
				respString = BusinessConstants.ONE;

			}
		} catch (Exception ex) {
			registerUserServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return respString;
	}

	
	
	
	/***
	 * to register user
	 * 
	 * @param user
	 *            CloudSvrUser object having all details require to register
	 *            user
	 * @return string
	 * @throws CloudServiceException
	 */
	@Transactional
	public Boolean isRegisteredUser(LoginDTO loginDTO)
			throws CloudServiceException {
		// TODO Auto-generated method stub
		String METHOD_NAME = "registeredUserService";

		log.info(loginDTO + Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		Boolean userExist = false;

		try {

			String busnessId = userDao.getBusinessId(loginDTO.getMerchantAccessKey());

			CloudSvrUser myuser = UserHelper.convertFromDTOtoBO(loginDTO);
			
			myuser.setBusinessId(busnessId);
			

			userExist = userDao.isUserExistsDao(myuser);

		
		} catch (Exception ex) {
			registerUserServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return userExist;
	}
	
	
	
	
	
	/***
	 * for user logout
	 * 
	 * @param user CloudSvrUser object having all details about user
	 * @return string
	 * @throws CloudServiceException while business validation failed
	 */
	@Transactional
	public String logoutService(CloudSvrUser user) throws CloudServiceException {

		String METHOD_NAME = "logoutService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String respString = null;

		try {

			this.logout(user);
			respString = BusinessConstants.ONE;

			}

		catch (Exception ex) {
			logoutServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;
	}

	
	/***
	 * for set Selected Security Questions
	 * 
	 * @param user CloudSvrUser object having all details about user
	 * @return string 
	 * @throws CloudServiceException while business validation failed
	 */
	@Transactional
	public String setSelectedSecurityQuestionsService(CloudSvrUser user)
			throws CloudServiceException {

		String METHOD_NAME = "setSelectedSecurityQuestionsService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		// call service layer
		boolean isSet = this.setSelectedSecurityQuestions(user);

		String respString = null;
		try {
			if (isSet) {

				respString = BusinessConstants.ONE;

			} else {

				respString = BusinessConstants.TWO;

			}
		} catch (Exception ex) {
			
        setSelectedSecurityQuestionsException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;
	}

	
	/***
	 * to check if user exit or not in database
	 * 
	 * @param user CloudSvrUser object 
	 * @return string
	 * @throws CloudServiceException
	 */
	@Transactional
	public String isUserExistService(CloudSvrUser user)
			throws CloudServiceException {
		String METHOD_NAME = "isUserExistService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		String respString = null;

		// check first to know whether coming phone number or email
		// user
		// is already exist or not
		boolean isUserNotExist = false;
		try {
			isUserNotExist = this.isUserExist(user);

			if (isUserNotExist) {

				respString = BusinessConstants.TWO;
			} else {
				respString = BusinessConstants.ONE;
			}
		} catch (Exception ex) {
			isUserExistException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;
	}

	
	
	/***
	 * use validate user while login
	 * 
	 * @param user  CloudSvrUser object contain all the parameters for user login
	 * @return String
	 * @throws CloudServiceException
	 */
	@Transactional
	public String validatingUserService(CloudSvrUser user)
			throws CloudServiceException {

		String METHOD_NAME = "validatingUserService";
		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String respString = null;

		boolean isUser = false;

		try {

			isUser = this.validatingUser(user);
			// if user account is not blocked during wrong
			// matched
			// answer during forgot password
			// and user is a valid user then allowed user to
			// login.
			if (isUser) {

				this.userLogin(user);
				respString = BusinessConstants.ONE;

			}

			else {
				respString = BusinessConstants.TWO;
			}
		} catch (Exception ex) {
			
			validatingUserServiceException(ex);
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;
	}

	
	/**
	 * for login
	 * @param user CloudSvrUser object having details about user
	 * @return String - represents status 
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public String isAlreadyLogedInService(CloudSvrUser user)
			throws CloudServiceException {
		String METHOD_NAME = "isAlreadyLogedInService";
		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		boolean isAlreadyLogin = false;

		String respString = null;
		try {
			isAlreadyLogin = this.isAlreadyLogedIn(user);

			if (isAlreadyLogin) {

				respString = BusinessConstants.ONE;

			} else {
				respString = BusinessConstants.TWO;
			}
		} catch (Exception ex) {
			isAlreadyLogedInServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;
	}

	
	/**
	 * for user login
	 * @param userAttempt CloudSvrUser object having details about user
	 * @return String - represents status
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public String loginFailedService(CloudSvrUser userAttempt)
			throws CloudServiceException {

		String METHOD_NAME = "loginFailedService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String respString = null;

		int badloggincount = userAttempt.getBadLoginCount().intValue();
		try {
			// updating wrong attempt
			if (badloggincount < Integer.parseInt(BusinessConstants.FIVE)) {

				userAttempt.setBadLoginCount(userAttempt.getBadLoginCount());

				this.updateUserWrongAttemptService(userAttempt);

				respString = BusinessConstants.ONE;
			}

			else {

				this.UpdateUserLockService(userAttempt);

				respString = BusinessConstants.TWO;
			}
		} catch (Exception ex) {
			loginFailedServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;
	}

	/**
	 * for user update profile
	 * @param userProf CloudSvrUsersProfile object having details about user
	 * @return String 
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public String updateUserProfileService(CloudSvrUsersProfile userProf)
			throws CloudServiceException {

		String METHOD_NAME = "updateUserProfileService";
		
		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		
		try {
			
			this.updateUserProfile(userProf);
		
		} catch (Exception ex) {
			updateUserProfileServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return BusinessConstants.ONE;
	}
	
	
	
	
	
	/////////////private methods for exception///////////////
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getUserCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getUserCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while gettting user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}
	
	
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getUserException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getUserException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer.append("Exception occurred while getting the user")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.GET_USER)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(ex.getMessage());

	log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
	throw new CloudServiceException(ErrorCodeConstants.GET_USER,
			ex.getMessage());
}
	
	
	
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getLogoutCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getLogoutCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user try to logout ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGOUT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  getLogoutException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getLogoutException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while user try to logout")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGOUT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_LOGOUT,
				ex.getMessage());
         }
	
	
	
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getLoginCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getLoginCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user try to login ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  getLoginException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getLoginException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while doing user try to login")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_LOGIN,
				ex.getMessage());
         }
	
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getSecurityQuestionsCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getSecurityQuestionsCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting selected security questions ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException   getSecurityQuestionsException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getSecurityQuestionsException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting selected security questions")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_SECURITY_QUESTIONS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.GET_SECURITY_QUESTIONS, ex.getMessage());
        }
	
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getRegisterUserCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getCommonRegisterServiceCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while  register user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.REGISTER_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException   getRegisterUserException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getCommonRegisterServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while register user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.REGISTER_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.REGISTER_USER,
				ex.getMessage());
             }
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getUserByImeiCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getUserByImeiCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting user by Imei")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER_BY_IMEI)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException   getUserByImeiException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = " getUserByImeiException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while getting user by Imei")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER_BY_IMEI)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_USER_BY_IMEI,
				ex.getMessage());
        }
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getUpdateUserWrongAttemptCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getUpdateUserWrongAttemptCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while update User Wrong Attempt")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException   getUpdateUserWrongAttemptException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "updateUserWrongAttemptServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while update User Wrong Attempt")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT,
				ex.getMessage());
}
	
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException isAlreadyLogedInCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "isAlreadyLogedInCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user try to login")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());


	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  isAlreadyLogedInException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "isAlreadyLogedInException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while user try to login")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_LOGIN,
				ex.getMessage());
    }
	
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException isUserExistServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "isUserExistServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while gettting user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  isUserExistException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "isUserExistException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while getting the user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_USER,
				ex.getMessage());
	}

	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException validatingUserServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "validatingUserServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while validating user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  validatingUserException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "validatingUserException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while validating user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_USER,
				ex.getMessage());
	}
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException updateWrongAttemptSecurityAnsCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "updateWrongAttemptSecurityAnsServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while update wrong attempt security answer")
				.append(DaoConstants.COMMA)
				.append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT_SEC_ANS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  updateWrongAttemptSecurityAnsException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "updateWrongAttemptSecurityAnsException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting the user")
				.append(DaoConstants.COMMA)
				.append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT_SEC_ANS)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.USER_UPDATE_WRONG_ATTEMPT_SEC_ANS,
				ex.getMessage());
	}
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException updateUserLockCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "updateUserLockCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("DataAccessException occurred while user lock")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOCK)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  updateUserLockException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "updateUserLockException";
		StringBuilder errorBuffer = new StringBuilder();
				errorBuffer.append("Exception occurred while user lock")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOCK)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_LOCK,
				ex.getMessage());
	
	}
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException userLoginCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "userLoginCloudServiceException";;
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user try to login ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  userLoginException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "userLoginException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while user try to login")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_LOGIN,
				ex.getMessage());
	
	}
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException setSelectedSecurityQuestionsCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "setSelectedSecurityQuestionsCloudServiceException";;
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while set selected security questions ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.SET_SEC_QUESTION)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  setSelectedSecurityQuestionsException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "setSelectedSecurityQuestionsException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while set selected security questions")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.SET_SEC_QUESTION)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.SET_SEC_QUESTION,
				ex.getMessage());
	
	}
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException updateUserProfileCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "updateUserProfileCloudServiceException";;
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while update user profile ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE_PROFILE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  updateUserProfileException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "updateUserProfileException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while update user profile")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE_PROFILE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_UPADTE_PROFILE,
				ex.getMessage());
	
	}
	
	
	
	/**
	 * get cloud service Exception 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException commonUpdateUserCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "commonUpdateUserCloudServiceException";;
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while update user ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());


	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  commonUpdateUserException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "commonUpdateUserException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while update user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_UPADTE,
				ex.getMessage());
	
	}
	
	
	
	
	
	
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  registerUserServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "registerUserServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while  register user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.REGISTER_USER)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.REGISTER_USER,
				ex.getMessage());
	
	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  logoutServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "logoutServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while  user logout")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGOUT)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_USER,
				ex.getMessage());
	
	}
	
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  validatingUserServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "validatingUserException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while validating user")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN_VALIDATE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_LOGIN_VALIDATE,
				ex.getMessage());
	
	}

	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  isAlreadyLogedInServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "isAlreadyLogedInServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while user try to login")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_LOGIN)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_LOGIN,
				ex.getMessage());
	
	}
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException  loginFailedServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "isAlreadyLogedInServiceException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer.append("Exception occurred while user try to login")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.USER_LOGIN_FAILED)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(ex.getMessage());

	log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
	throw new CloudServiceException(ErrorCodeConstants.USER_LOGIN_FAILED,
			ex.getMessage());
	}
	
	
	
	
	/**
	 * get Exception 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException   updateUserProfileServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "updateUserProfileServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while update user profile")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE_PROFILE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_UPADTE_PROFILE,
				ex.getMessage());
	}




	
}