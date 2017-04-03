package org.omnypay.mobileapp.webservices.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



















import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.PasswordWebService;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.service.CommonService;
import com.omnypay.business.service.PasswordService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.BaseWebServiceImpl;
import com.omnypay.common.services.CommonHelper;
import com.omnypay.common.services.PasswordHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.crypt.ShahHashEncoder;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.PasswordDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.merchant.services.MerchantPasswordService;
import com.sun.jersey.spi.resource.Singleton;

/**
 * 
 * @author iliyasm
 *
 */
@Component
@Path("/password")
public class PasswordWebServiceImpl implements PasswordWebService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	// constructor
	/*public PasswordWebServiceImpl() {
		// log.debug("Account service created");
	}*/

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
			
	@Autowired
	PasswordService passwordService;
	
	@Autowired
	MerchantPasswordService merchantPasswordService;
	
	
	/**
	 * 
	 * forgotPwd request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * about user forgotPwd send both success and failure message to the mobile
	 * 
	 * @param BaseDTO
	 *            object containing all the parameters send from mobile to
	 *            forgotPwd a user
	 * @return response object to the mobile regarding user registerInStore
	 * @see org.omnypay.mobileapp.webservices.PasswordWebService#forgotPassword(org.
	 *      omnypay .mobileapp.dto.PasswordDTO)
	 * 
	 * 
	 * 
	 */
	@POST
	@Path("/forgot")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotPassword(PasswordDTO passwordDTO) {

		String METHOD_NAME = "forgotPassword";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		Status status = null;

		Response response = null;

		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(passwordDTO)
					&& PasswordHelper.checkMandatoryFields(passwordDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(passwordDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {


					status = merchantPasswordService.forgotPassword(merchantDTO,passwordDTO); 
					
					// for internal merchant
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {


					// getting the user object
					CloudSvrUser dbuser = this.getUser(passwordDTO);

					String respString = this.getResultForforgotPwd(dbuser,
							passwordDTO);

					if (respString.contains(WebServiceConstants.ONE)) {

						this.sentMailForForgotPswd(dbuser);

					}

					status = this.getStatusForgotPassword(respString, dbuser);

				} else {

					status = CloudServiceStatus
							.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

				}

			} else {

				status = CloudServiceStatus
						.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);

			}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus
					.getStatusByException(cloudServiceException);

			String message = "businessException occured while forgot password";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_FORGOT_PASSWORD);

			String message = "Exception occured while forgot password";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		} finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}

	/**
	 * 
	 * changePwd request from mobile sending response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * about user changePwd send both success and failure message to the mobile
	 * 
	 * @param BaseDTO
	 *            object containing all the parameters send from mobile to
	 *            changePwd a user
	 * @return response object to the mobile regarding user changePwd
	 * @see org.omnypay.mobileapp.webservices.PasswordWebService#changePassword(org.omnypay
	 *      .mobileapp.dto.PasswordDTO)
	 * 
	 * 
	 * 
	 */

	@POST
	@Path("/change")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(PasswordDTO passwordDTO) {

		String METHOD_NAME = "changePassword";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		Status status = null;

		Response response = null;

		try {

			// required data are coming with request attribute or not

			if (WebServiceUtil.baseCheckMandatoryFields(passwordDTO)
					&& PasswordHelper
							.checkMandatoryFieldsChangePwd(passwordDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
						
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(passwordDTO.getMerchantAccessKey());
				
				
				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

					status = merchantPasswordService.changePassword(merchantDTO,passwordDTO); 
					
				//	for internal merchant
				} else 	if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					CloudSvrUser dbuser = this.getUser(passwordDTO);

					String respString = this.getResultForChangePassword(
							passwordDTO, dbuser);

					status = this.getStatusChangePassword(respString, dbuser);

				} else {

					status = CloudServiceStatus
							.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

				}

			} else {

				status = CloudServiceStatus
						.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);

			}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus
					.getStatusByException(cloudServiceException);

			String message = "businessException occured while change password ";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_CHANGE_PASSWORD);

			String message = "Exception occured while change password";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		} finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Private method Starts
	// here/////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Private method Starts
	// here/////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// /////////////////////////service service service
	// service//////////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service////////////////////////////////////////////////////////////////
	// ///////////////////////// start
	// //////////////////////////////////////////////////////////////////////

	/*// to get service object of common service
	private CommonService GetCommonService() {

		CommonService commonService = (CommonService) super
				.getApplicationContext().getBean("commonService");

		return commonService;
	}

	// to get user service object of userService
	private UserService getUserService() {

		// business layer integration
		UserService userService = (UserService) super.getApplicationContext()
				.getBean(WebServiceConstants.USER_SERVICE);

		return userService;
	}

	// to get user service object of passworsService
	private PasswordService getPasswordService() {

		// business layer integration
		PasswordService passwordService = (PasswordService) super
				.getApplicationContext().getBean(
						WebServiceConstants.PASSWORD_SERVICE);

		return passwordService;

	}
*/
	// ///////////////////////// end
	// //////////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////// business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ///////////////////////// start
	// //////////////////////////////////////////////////////////////////////

	/***
	 * to get merchant access key in database exist or not
	 * 
	 * @param accessKey
	 *            represent the access key from mobile
	 * @return true/false
	 * @throws CloudServiceException
	 */
/*	private CloudSvrBusinessEntityInfo accessKeyForMerchantExistService(String accessKey)
			throws CloudServiceException {

		boolean keyExist = false;

		CommonService commonService = GetCommonService();

		CloudSvrBusinessEntityInfo entityInfo = CommonHelper
				.convertFromDTOtoBOForMerchantAccessKey(accessKey);

		entityInfo = commonService.isAccessKeyForMerchantExistService(entityInfo);

		return entityInfo;
	}*/

	/***
	 * to get user from database
	 * 
	 * @param PasswordDTO
	 *            contain all the parameter for getting user
	 * @return user CloudSvrUser object having all the details about user
	 * @throws CloudServiceException
	 */
	private CloudSvrUser getUser(PasswordDTO passwordDTO)
			throws CloudServiceException {

		//UserService userService = this.getUserService();

		CloudSvrUser user = PasswordHelper.convertFromDTOtoBO(passwordDTO);

		user = userService.getUserService(user);

		return user;

	}

	/***
	 * for getting forget result object
	 * 
	 * 
	 * @param dbuser
	 *            representing all details of user
	 * @param passwordDTO
	 *            PasswordDTO object contain all the parameter for getting
	 *            forget password result
	 * @return String represent a result object
	 * @throws CloudServiceException
	 */
	private String getResultForforgotPwd(CloudSvrUser dbuser,
			PasswordDTO passwordDTO) throws CloudServiceException {

		String result = null;

		//PasswordService passwordService = this.getPasswordService();

		CloudSvrUser user = PasswordHelper.convertFromDTOtoBO(passwordDTO);

		result = passwordService.forgotPasswordService(dbuser, user);

		return result;
	}

	/***
	 * for sending mail to user mail
	 * 
	 * 
	 * @param dbuser
	 *            CloudSvrUser object representing all details of user
	 * 
	 * @throws CloudServiceException
	 */
	private void sentMailForForgotPswd(CloudSvrUser dbuser)
			throws CloudServiceException {

		//PasswordService passwordService = this.getPasswordService();

		CloudSvrUser radomPassValString = PasswordHelper
				.convertFromDTOtoBOForResetPassValString();

		passwordService.resetPassValStringUpdateService(dbuser,
				radomPassValString.getResetPassValString());

		PasswordHelper.forgotPassword(dbuser.getEmailId(),
				radomPassValString.getResetPassValString());

	}

	/***
	 * for getting result for change password
	 * 
	 * 
	 * @param dbuser
	 *            CloudSvrUser object representing all details of user
	 * @param passwordDTO
	 *            PasswordDTO object contain require parameter for change
	 *            password
	 * @return String
	 * @throws CloudServiceException
	 */
	private String getResultForChangePassword(PasswordDTO passwordDTO,
			CloudSvrUser dbuser) throws Exception {

		String userOldPassword = null;

		String newEncryptedPass = null;

		String respString = null;

		boolean isOldPasscode = false;

		//PasswordService passwordService = this.getPasswordService();

		userOldPassword = ShahHashEncoder.encodeShaHash(passwordDTO
				.getOldPassword());
		newEncryptedPass = ShahHashEncoder.encodeShaHash(passwordDTO
				.getNewPassword());
		isOldPasscode = PasswordHelper.isOldPasscode(
				WebServiceUtil.URLDecoderField(userOldPassword),
				dbuser.getPassCode());

		respString = passwordService
				.changePasswordService(dbuser,
						WebServiceUtil.URLDecoderField(newEncryptedPass),
						isOldPasscode);

		return respString;
	}

	// ///////////////////////// end
	// /////////////////////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////
	// ////////////////////////////////////////////business layer integration
	// ////////////////////////////////////////

	// ////////////////////////////////////////////////// status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// ///////////////////////// start
	// /////////////////////////////////////////////////////////

	/***
	 * get Status for Forgot Password
	 * 
	 * @param dbuser
	 *            CloudSvrUser object having all the details of user
	 * @param result
	 *            a string having the response
	 * @return status Status object having response
	 */
	private Status getStatusForgotPassword(String result, CloudSvrUser dbuser) {

		Status status = null;

		if (result != null) {

			if (result.contains(WebServiceConstants.TWO)) {

				status = WebServiceUtil.getStatus(
						WebServiceConstants.SECURE_PASSWORD_NOT_SENT,
						WebServiceConstants.TWO);
			} else if (result.contains(WebServiceConstants.THREE)) {

				status = WebServiceUtil.getStatus(
						WebServiceConstants.ACCOUNT_BLOCKED,
						WebServiceConstants.THREE);
			} else if (result.contains(WebServiceConstants.ONE)) {

				status = WebServiceUtil.getStatus(
						WebServiceConstants.PASSWORD_SENT,
						WebServiceConstants.ONE);
			}

		}

		return status;

	}

	/***
	 * get Status for change password
	 * 
	 * @param dbuser
	 *            CloudSvrUser object having all the details of user
	 * @param result
	 *            a string having the response
	 * @return status Status object having response
	 */
	private Status getStatusChangePassword(String result, CloudSvrUser dbuser) {

		Status status = null;
		if (result.contains(BusinessConstants.TWO)) {

			status = WebServiceUtil.getStatus(
					WebServiceConstants.PASSWORD_ALREADY_EXIST,
					WebServiceConstants.TWO);
		}

		else if (result.contains(BusinessConstants.ONE)) {

			status = WebServiceUtil.getStatus(
					WebServiceConstants.PASSWORD_CHANGED,
					WebServiceConstants.ONE);

		}

		else if (result.contains(BusinessConstants.THREE)) {

			status = WebServiceUtil.getStatus(
					WebServiceConstants.PASSWORD_OLD_NOT_CORRECT,
					WebServiceConstants.THREE);
		}

		return status;
	}

	// ///////////////////////// end
	// ///////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////status object
	// //////////////////////////////////////////////////

}
