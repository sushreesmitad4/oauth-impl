package com.omnypay.business.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.PasswordService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.dao.PasswordDao;
import com.omnypay.dao.UserDao;
import com.omnypay.dao.bo.CloudSvrPasswordsHistory;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersSecQuestion;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/**
 * 
 * @author iliyasm
 *
 */
@Service("passwordService")
public class PasswordServiceImpl implements PasswordService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	@Autowired
	UserDao userDao;
	@Autowired
	PasswordDao passwordDao;

	// constructor
	public PasswordServiceImpl() {

	}

	
	
	
	
	/**
	 * to check coming question and answer from mobile is matching or not
	 * @param dbUser CloudSvrUser object having all details about user
	 * @param user  CloudSvrUser object having all details about user
	 * @return true/false
	 * 
	 */
	private boolean checkMatchingSecAnswerService(CloudSvrUser dbUser,
			CloudSvrUser user) {

		List<CloudSvrUsersSecQuestion> userSecQuestion = user
				.getListSecQuestion();

		List<CloudSvrUsersSecQuestion> dbSecQuestion = dbUser
				.getListSecQuestion();

		int correctAnswercounter = 0;

		boolean updatingwrongAnswercounter = false;

		for (CloudSvrUsersSecQuestion uSecQuestion : userSecQuestion) {

			for (CloudSvrUsersSecQuestion dSecQuestion : dbSecQuestion) {

				if (uSecQuestion.getSecQuesAnswer().equalsIgnoreCase(
						dSecQuestion.getSecQuesAnswer())
						&& uSecQuestion.getSecQuesId() == dSecQuestion
								.getSecQuesId()) {

					++correctAnswercounter;
					break;

				}

			}

		}

		if (correctAnswercounter != userSecQuestion.size()) {

			updatingwrongAnswercounter = true;

		}

		return updatingwrongAnswercounter;
	}

	
	

	/**
	 * to update the Password Validation String in case of forgot password
	 * @param user CloudSvrUser object having all details about user
	 * @param resetPassValString  represent the validation string use for forgot password
	 * 
	 * 
	 */
	@Transactional
	public void resetPassValStringUpdateService(CloudSvrUser user,
			String resetPassValString) throws CloudServiceException {

		String METHOD_NAME = "resetPassValStringUpdateService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {

			user.setBadSeccurityAnsCount(new BigDecimal(BusinessConstants.ZERO));
			// updating reset Password validation string.
			user.setResetPassValString(resetPassValString);
			// updating reset password link creation time.
			user.setResetPassLinkCreatedTime(new Timestamp(System
					.currentTimeMillis()));
			user.setIsLocked(BusinessConstants.ISLOCKED);

			this.passwordDao.updateResetPassValStringDao(user);

			

		} catch (CloudDAException accessException) {

			resetPassValStringUpdateCloudServiceException(accessException);

		} catch (Exception ex) {
			resetPassValStringUpdateServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
	}

	
	/**
	 * to change password
	 * @param user CloudSvrUser object having all details about user
	 * @param newpasscode  represent new password for the user
	 * @throws CloudServiceException in case business validation failed
	 * 
	 */
	private void changePassword(CloudSvrUser user, String newpasscode)
			throws CloudServiceException {

		//String METHOD_NAME = "changePassword";

		try {

			// if (user != null) {

			List<CloudSvrPasswordsHistory> passwordsHistory = new ArrayList<CloudSvrPasswordsHistory>();

			CloudSvrPasswordsHistory newHistory = new CloudSvrPasswordsHistory();
			// /newCard.setCard_holder_name(cardInfo.getCard_holder_name());
			newHistory.setPassword(newpasscode);
			newHistory.setPwdChangeType(BusinessConstants.PASSWORD_CHANGE_TYPE);
			newHistory
					.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			// set childs to parent
			passwordsHistory.add(newHistory);

			// setting new to user
			user.setPassCode(newpasscode);
			user.setIsLocked(BusinessConstants.ISUNLOCKED);
			user.setIsLogin(BusinessConstants.ISLOGOUT);
			user.setBadLoginCount(new BigDecimal(BusinessConstants.ZERO));
			user.setBadSeccurityAnsCount(new BigDecimal(BusinessConstants.ZERO));
			newHistory.setUser(user);

			user.setUserPassList(passwordsHistory);

			this.passwordDao.ChangedPwdDao(user);

			// }
		} catch (CloudDAException accessException) {

			changePasswordCloudServiceException(accessException);

		} catch (Exception ex) {
			changePasswordServiceException(ex);
		}

	}

	

	/**
	 * to check user password matching last five or not in case of change password
	 * @param user CloudSvrUser object having all details about user
	 * @param newpasscode  represent the new password for the user
	 * @return true/false
	 * @throws CloudServiceException while business validation failed
	 * 
	 */
	private boolean IsLastFivePasswordService(CloudSvrUser user,
			String newpasscode) throws CloudServiceException {

		//String METHOD_NAME = "IsLastFivePasswordService";
		// TODO Auto-generated method stub
		boolean isMatch = false;
		try {

			List<CloudSvrPasswordsHistory> oldPasscodeList = user
					.getUserPassList();

			for (CloudSvrPasswordsHistory oldHistroy : oldPasscodeList) {

				if (oldHistroy.getPassword().equals(newpasscode)) {

					isMatch = true;
				}

			}

		} catch (Exception ex) {
			isLastFivePasswordServiceException(ex);
		}

		return isMatch;
	}

	
	
	
	/**
	 * use for user forgot password
	 * @param dbuser CloudSvrUser object having all details about user from database
	 * @param newpasscode  represent the new password for the user
	 * @return true/false
	 * @throws 
	 * 
	 */
	@Transactional
	public String forgotPasswordService(CloudSvrUser dbuser, CloudSvrUser user)
			throws CloudServiceException {

		String METHOD_NAME = "forgotPasswordService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String respString = null;

		try {

			boolean isSecqueMatch = this.checkMatchingSecAnswerService(dbuser,
					user);

			if (isSecqueMatch
					&& dbuser.getBadSeccurityAnsCount().intValue() < 3) {

				this.userDao.updateWrongAttemptSecurityAnsDao(dbuser);

				respString = BusinessConstants.TWO;

			} else if (dbuser.getBadSeccurityAnsCount().intValue() == 3) {

				respString = BusinessConstants.THREE;
			}

			else {

				respString = BusinessConstants.ONE;
			}
		} catch (CloudDAException accessException) {

			forgotPasswordCloudServiceException(accessException);

		} catch (Exception ex) {
			forgotPasswordException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return respString;
	}

	
	/**
	 * to change password
	 * @param dbuser CloudSvrUser object having all details about user
	 * @param newEncryptedPass  represent new password for the user
	 * @param isOldPasscode    represent old password for the user
	 * @return String
	 * @throws CloudServiceException in case business validation failed
	 * 
	 */
	@Transactional
	public String changePasswordService(CloudSvrUser dbuser,
			String newEncryptedPass, boolean isOldPasscode)
			throws CloudServiceException {

		String METHOD_NAME = "changePasswordService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String respString = null;
		try {
			if (isOldPasscode) {

				boolean isLastFivePasscode = this.IsLastFivePasswordService(
						dbuser, newEncryptedPass);

				if (isLastFivePasscode) {

					respString = BusinessConstants.TWO;
				}

				else {

					this.changePassword(dbuser, newEncryptedPass);

					respString = BusinessConstants.ONE;
				}

			} else {
				respString = BusinessConstants.THREE;
			}
		} catch (Exception ex) {
			changePasswordException(ex);
		}

		return respString;

	}
	
	
	
	
	// //////////////////private methods for exception/////////
	/**
	 * get cloudserviceException for resetPassValStringUpdate
	 * 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException resetPassValStringUpdateCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "resetPassValStringUpdateCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while updating Pass Val String ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.UPDATE_PASS_VAL_STRING)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}

	/**
	 * get Exception for resetPassValStringUpdate
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException resetPassValStringUpdateServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "resetPassValStringUpdateServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while updating Pass Val String")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.UPDATE_PASS_VAL_STRING)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.UPDATE_PASS_VAL_STRING, ex.getMessage());
	}

	
	
	
	
	/**
	 * get cloudserviceException for resetPassValStringUpdate
	 * 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException changePasswordCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "changePasswordCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user doing change password")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_CHANGE_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}

	/**
	 * get Exception for changePasswordServiceException
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException changePasswordServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "changePasswordServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while user doing change password ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_CHANGE_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.USER_CHANGE_PASSWORD, ex.getMessage());
	}
	
	
	
	/**
	 * get Exception for IsLastFivePasswordServiceException
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException isLastFivePasswordServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "IsLastFivePasswordServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while change the password with is last five should not match")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.LAST_FIVE_PASSWORD_MATCH)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.LAST_FIVE_PASSWORD_MATCH,
				ex.getMessage());
	}
	
	
	
	/**
	 * get cloudserviceException for forgotPasswordException
	 * 
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException forgotPasswordCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "forgotPasswordCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while forgot password ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_FORGOT_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}

	/**
	 * get Exception for forgotPasswordException
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException forgotPasswordException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "forgotPasswordException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while forgot password")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_FORGOT_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.USER_FORGOT_PASSWORD, ex.getMessage());
	}
	
	
	
	/**
	 * get Exception for changePasswordServiceException
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException changePasswordException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "changePasswordServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while change password")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_CHANGE_PASSWORD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.USER_CHANGE_PASSWORD, ex.getMessage());
	}
	
	


}
