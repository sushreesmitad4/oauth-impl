package org.omnypay.mobileapp.webservices.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.omnypay.mobileapp.listener.ReflectionStrtegy;
import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.CommonService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersProfile;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.merchant.services.MerchantServiceRoute;
import com.omnypay.merchant.services.MerchantUserServices;

/**
 * 
 * @author iliyasm
 *
 */
//@Singleton
@Component
@Path("/user")
public class UserWebServiceImpl  implements UserWebService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	// constructor
	/*public UserWebServiceImpl() {
	}*/

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MerchantUserServices merchantUserServices;
	
	
		
	
	/**
	 * 
	 * Register request from mobile send response back to mobile
	 * Returns response object that will send to the mobile containing 
	 * all the details about user registration and other details.
	 * send both success and failure message to the mobile 
	 * only internal merchant can register now not for external 
	 * 
	 * @param   UserDTO object containing all the parameters send from mobile to register a user    
	 * @return  response object to the mobile regarding user registration
	 * @see
	 * org.omnypay.mobileapp.webservices.UserWebService#getUserData(org.omnypay
	 * .mobileapp.dto.UserDTO)
	 * 
	 * 
	 * 	 
	 */
	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(UserDTO userDTO) {

		String METHOD_NAME = "register";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,METHOD_NAME);

		Status status = null;
	
		Response response = null;
		try {

			// check base fields
			if (WebServiceUtil.baseCheckRegistartionMandatoryFields(userDTO)
					&& UserHelper.checkMandatoryFieldsRegistration(userDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(userDTO.getMerchantAccessKey());
				
				

				if (merchantDTO !=null && merchantDTO.getIsExternal()) {

					status = merchantUserServices.register(merchantDTO, userDTO);
			
					//for internal merchant
				} else 	if (merchantDTO !=null && (!merchantDTO.getIsExternal())) {

						String respString = this.registerUser(userDTO);

							status = this.registerUser(respString);

				}  else {

					status = CloudServiceStatus.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

				}

			} else {
				
				status = CloudServiceStatus
						.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);
			}

		} catch (CloudServiceException cloudServiceException) {
				
			
			status = CloudServiceStatus
					.getStatusByException(cloudServiceException);

			String message = "businessException occured while doing user registration";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);
			
			
		
		} catch (Exception ex) {
			
			status = CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.REGISTER_USER);

			String message = "Exception occured while doing user registration";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);
		
		}
		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * login request from mobile send response back to mobile
	 * Returns response object that will send to the mobile containing all the details about user login
	 * send both success and failure message to the mobile 
	 * 
	 * @param   LoginDTO object containing all the parameters send from mobile to login a user    
	 * @return  response object to the mobile regarding user login
	 * @see
	 * org.omnypay.mobileapp.webservices.UserWebService#login(org.omnypay
	 * .mobileapp.dto.login)
	 * 
	 * 
	 * 	 
	 */
		@Path("oauth/login")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response login(LoginDTO loginDTO) {

			String METHOD_NAME = "login";

			CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,METHOD_NAME);
			
			Response response = null;

			Status status = null;

			try {

				if (WebServiceUtil.baseCheckMandatoryFields(loginDTO)
						&& UserHelper.checkMandatoryFields(loginDTO)) {

					// this statement check that merchant access key is there in
					// database or not
					// if exist merchant access key then it will return true
					// if not exits then return false
						
					
					
					MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(loginDTO.getMerchantAccessKey());
					
					//status = 	ReflectionStrtegy.getMethod(merchantUserServices, userService,merchantDTO,loginDTO, "login", merchantDTO.getIsExternal());
					
					
					if (merchantDTO !=null && merchantDTO.getIsExternal()) {
						
						//MerchantUserServices asb = new MerchantUserServices();
					
						status = merchantUserServices.login(merchantDTO, loginDTO);
					
						
						 						 
			        } else if (merchantDTO !=null &&  (!merchantDTO.getIsExternal())){
			        	
			        	
			        	status = this.getUserLogin(loginDTO);
			        	
			        }
			   					
				

				} else {

					status = CloudServiceStatus
							.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);

				}

			} catch (CloudServiceException cloudServiceException) {
				
				status = CloudServiceStatus
						.getStatusByException(cloudServiceException);

				String message = "businessException occured while doing user login";

				CloudServiceExceptionLogger.LogCloudServiceException(
						cloudServiceException, CLASS_NAME, METHOD_NAME, message,
						null);
				
			}
			catch (Exception ex) {
				
							
				status = CloudServiceStatus
						.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_LOGIN);

				String message = "Exception occured while doing user login";

				CloudServiceExceptionLogger.LogCloudServiceException(ex,
						CLASS_NAME, METHOD_NAME, message, null);

						
			}

			finally {

				CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
						METHOD_NAME);
				response = Response.status(javax.ws.rs.core.Response.Status.OK)
						.entity(status).build();

			}

			return response;

		}

	 
	 
	// working copy previously 
	// working copy previously 
	/*@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginDTO loginDTO) {

		String METHOD_NAME = "login";

		Response response = null;

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,METHOD_NAME);

		Status status = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(loginDTO)
					&& UserHelper.checkMandatoryFields(loginDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				boolean accesskey = this
						.accessKeyForMerchantExistService(loginDTO
								.getMerchantAccessKey());

				if (accesskey) {

					status = this.getUserLogin(loginDTO);

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

			String message = "businessException occured while doing user login";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);
			
		}
		catch (Exception ex) {
			
						
			status = CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_LOGIN);

			String message = "Exception occured while doing user login";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

					
		}

		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}
*/


	
	
	
	///////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * logout request from mobile send response back to mobile
	 * Returns response object that will send to the mobile containing all the details about user logout
	 * send both success and failure message to the mobile 
	 * 
	 * @param   BaseDTO object containing all the parameters send from mobile to logout a user    
	 * @return  response object to the mobile regarding user logout
	 * @see
	 * @see org.omnypay.mobileapp.webservices.UserWebService#logout(org.omnypay
	 * .mobileapp.dto.BaseDTO)
	 * 
	 * 
	 * 	 
	 */
	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(BaseDTO baseDTO) {

		String METHOD_NAME = "logout";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		Response response = null;
		Status status = null;

		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(baseDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				/*CloudSvrBusinessEntityInfo accesskey = this
						.accessKeyForMerchantExistService((baseDTO)
								.getMerchantAccessKey());*/
				
				
				
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(baseDTO.getMerchantAccessKey());
				
				

				if (merchantDTO !=null &&  merchantDTO.getIsExternal()) {
				
			

					status = merchantUserServices.logout(merchantDTO, baseDTO);

			
					// for internal merchant logout
				} else if (merchantDTO !=null && ( !merchantDTO.getIsExternal())){
					
					
					String respString = getResultForUserLogout(baseDTO);

					status = this.userLogout(respString);
					
					
				} else {
				

					status = CloudServiceStatus
							.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

				}

			} else {

				status = CloudServiceStatus
						.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);

			}

		}

		catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus
					.getStatusByException(cloudServiceException);

			String message = "businessException occured while doing user logout";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_LOGOUT);

			String message = "Exception occured while doing user logout";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		}

		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}
	
	
	/**
	 * 
	 * registerInStore request from mobile send response back to mobile
	 * Returns response object that will send to the mobile containing all the details about user registerInStore
	 * send both success and failure message to the mobile 
	 * 
	 * @param   BaseDTO object containing all the parameters send from mobile to registerInStore a user    
	 * @return  response object to the mobile regarding user registerInStore
	 * @see
	 * org.omnypay.mobileapp.webservices.UserWebService#registerInStore(org.
	 * omnypay .mobileapp.dto.BaseDTO)
	 * 
	 * 
	 * 	 
	 */

	@Path("/registerInStore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerInStore(BaseDTO baseDTO) {
		Status status = null;

		String METHOD_NAME = "registerInStore";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

        Response response=null;
		
        if (baseDTO != null) {
			try {

				// check base fields
				if (WebServiceUtil.baseCheckMandatoryFields(baseDTO)
						&& UserHelper.checkMandatoryFieldsRegistration(baseDTO)) {

					// this statement check that merchant access key is there in
					// database or not
					// if exist merchant access key then it will return true
					// if not exits then return false
				/*	CloudSvrBusinessEntityInfo accesskey = this
							.accessKeyForMerchantExistService(baseDTO
									.getMerchantAccessKey());*/

					//CommonService commonService = GetCommonService();
					
					MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(baseDTO.getMerchantAccessKey());
					
					

					if (merchantDTO.getIsExternal()) {

						String respString = this.registerUserInStore(baseDTO);

						status = this.registerUserInStore(respString);

					} else {

						status = CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY);

					}

				} else {

					status = CloudServiceStatus
							.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);

				}

			} 
			catch (CloudServiceException cloudServiceException) {

				status = CloudServiceStatus
						.getStatusByException(cloudServiceException);

				String message = "businessException occured while doing user registration in store";

				CloudServiceExceptionLogger.LogCloudServiceException(
						cloudServiceException, CLASS_NAME, METHOD_NAME, message,
						null);

			} catch (Exception ex) {

				status = CloudServiceStatus
						.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.REGISTER_USER_STORE);

				String message = "Exception occured while doing user registration in store";

				CloudServiceExceptionLogger.LogCloudServiceException(ex,
						CLASS_NAME, METHOD_NAME, message, null);

			} finally {

				CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
						METHOD_NAME);
				response = Response.status(javax.ws.rs.core.Response.Status.OK)
						.entity(status).build();

			}
			

		}
		return response;
	}	
		
	

	
	/**
	 * 
	 * updateUserPro request from mobile send response back to mobile
	 * Returns response object that will send to the mobile containing all the details about user updateUserPro
	 * send both success and failure message to the mobile 
	 * 
	 * @param   UpdateUserDTO object containing all the parameters send from mobile to UpdateUserDTO a user    
	 * @return  response object to the mobile regarding user updateUserPro
	 * @see
	 * @see org.omnypay.mobileapp.webservices.UserWebService#updateUserPro(org.omnypay
	 * .mobileapp.dto.UpdateUserDTO)
	 * 
	 * 
	 * 	 
	 */

	@POST
	@Path("/updateUserPro")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateUserProfile(UpdateUserDTO updateUserDTO) {
		String METHOD_NAME = "UpdateUserProfile";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		Status status = null;
		Response response = null;

		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(updateUserDTO)
					&& UserHelper.checkMandatoryFieldsUpdateUser(updateUserDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
			/*	CloudSvrBusinessEntityInfo accesskey = this
						.accessKeyForMerchantExistService(updateUserDTO
								.getMerchantAccessKey());

				if (accesskey != null) {*/
				
				
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(updateUserDTO.getMerchantAccessKey());
				
				

				if (merchantDTO !=null &&  merchantDTO.getIsExternal()) {
				

					status = merchantUserServices.updateUserProfile(merchantDTO, updateUserDTO);
					

				// for internal merchant 	
				} else if (merchantDTO !=null &&  (!merchantDTO.getIsExternal())) {
				

					String respString = updateUserProfile(updateUserDTO);

					status = updateUserProfile(respString);

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

			String message = "businessException occured while updating user profile";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);
			
		
		} catch (Exception ex) {

			status = CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_UPADTE_PROFILE);

			String message = "Exception occured while updating user profile";

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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	// ///////////////////////// start
	// /////////////////////////////////////////////////////////

	
	
	/***
	 * get Status after register an user
	 * 
	 * @param result - a response string
	 * @return Status object
	 */
	private Status registerUser(String result) {
		Status status = null;

		if (result != null) {
			if (result.contains(WebServiceConstants.TWO)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.USER_EXIST,
						WebServiceConstants.NUMBER_TWO);
			} else if (result != null
					&& result.contains(WebServiceConstants.ONE)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.REGSTN_SUCCESS,
						WebServiceConstants.NUMBER_ONE);
			}
		}

		return status;
	}

	
	/***
	 * get Status after register user in store
	 * 
	 * @param result- a response string
	 * @return Status object
	 */
	private Status registerUserInStore(String result) {
		Status status = null;

		if (result != null) {
			if (result.contains(WebServiceConstants.TWO)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.USER_EXIST,
						WebServiceConstants.NUMBER_TWO);
			} else if (result.contains(WebServiceConstants.ONE)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.REGSTN_SUCCESS,
						WebServiceConstants.NUMBER_ONE);
			}
		}

		return status;
	}

	
	
	
	/***
	 * get Status for user logout
	 * 
	 * @param result - a response string
	 * @return Status object
	 */
	private Status userLogout(String result) {

		Status status = null;

		if (result != null) {
			if (result.contains(BusinessConstants.ONE)) {
				status = WebServiceUtil.getStatus(WebServiceConstants.LOG_OUT,
						WebServiceConstants.NUMBER_ONE);
			} else if (result.contains(BusinessConstants.TWO)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.SIGN_OUT_NOT,
						WebServiceConstants.NUMBER_TWO);
			}
		}
		return status;

	}

	
	
	/***
	 * get Status for user update profile
	 * 
	 * @param result - a response string
	 * @return Status object
	 */
	private Status updateUserProfile(String result) {
		Status status = null;
		if (result != null) {
			if (result.contains(WebServiceConstants.ONE))

				status = WebServiceUtil.getStatus(
						WebServiceConstants.PROF_UPD_SUCCESS,
						WebServiceConstants.ONE);
		}
		return status;

	}

	
	/***
	 * get Status for user exist
	 * 
	 * @param result - a response string
	 * @return Status object
	 */
	private Status getStatusForUserExist(String result) {
		Status status = null;

		if (result != null) {
			if (result.contains(WebServiceConstants.TWO)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.LOGIN_INVALID_CREDENETIAL,
						WebServiceConstants.NUMBER_SEVEN);
			}

		}
		return status;

	}

	
	
	/***
	 * get Status for  user login
	 * 
	 * @param result - a response string
	 * @return Status object
	 */
	private Status getStatusForUserLogin(String result) {

		Status status = null;
		// String isUser = null;

		if (result != null) {
			if (result != null
					&& result.contains(WebServiceConstants.ONE)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.LOGIN_SUCCESS,
						WebServiceConstants.ONE);
			}
		}
		return status;
	}

	
	/***
	 * get Status if user already login
	 * 
	 * @param result - a response string
	 * @return Status object
	 */
	private Status getStatusForUserAlreadyLogin(String result) {

		Status status = null;
		
		if (result != null) {
			if (result != null
					&& result.contains(WebServiceConstants.ONE)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.LOGIN_FAIL_ALREADYLOGIN,
						WebServiceConstants.FOUR);
			}
		}
		return status;
	}

	
	/***
	 * get Status if user login failed
	 * 
	 * @param result - a response string
	 * @return Status object
	 */
	private Status getStatusForLoginFailed(String result) {

		Status status = null;
		
		if (result != null) {
			if (result != null
					&& result.contains(WebServiceConstants.ONE)) {
				status = WebServiceUtil.getStatus(
						WebServiceConstants.LOGIN_FAIL,
						WebServiceConstants.NUMBER_TWO);

			}

			else if (result != null
					&& result.contains(WebServiceConstants.TWO)) {

				status = WebServiceUtil.getStatus(
						WebServiceConstants.LOGIN_LOCKED,
						WebServiceConstants.NUMBER_FIVE);
			}
		}
		return status;
	}

	

	/***
	 * get Status if no user found
	 * 
	 * @param status - Status object
	 * @return Status object
	 */
	private Status userNull(Status status) {

		status = new Status();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(WebServiceConstants.USER_NOT_REGISTER);
		status.setType(WebServiceConstants.FIVE);

		return status;
	}

	
	
	/***
	 * get Status for getting code,message and type for any response send to mobile
	 * 
	 * @param status - Status object
	 * @return Status object
	 */
	private Status getStatus(Status status) {
		
		status.setCode(status.getCode());
		status.setMessage(status.getMessage());
		status.setType(status.getType());

		return status;
	}

	
	/***
	 * get Status for any server side error occur
	 * 
	 * @param status - Status object
	 * @return Status object
	 */
	private Status serverError(Status status) {

		status = new Status();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(WebServiceConstants.CLOUD_SERVER_ERROR);
		status.setType(WebServiceConstants.FIVE);

		return status;

	}


	/***
	 * get Status if user account block 
	 * 
	 * @param status - Status object
	 * @return Status object
	 */
	private Status accountBlock(Status status) {

		status = new Status();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(WebServiceConstants.ACCOUNT_BLOCKED);
		status.setType(WebServiceConstants.NUMBER_SIX);

		return status;
	}

	
	/***
	 * get Status if any server connection problem
	 * 
	 * @param status - Status object
	 * @return Status object
	 */
	private Status connectionProblem(Status status) {

		status = new Status();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(WebServiceConstants.CONNECTION_PROBLEM);
		status.setType(WebServiceConstants.FIVE);

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
	// ///////////////////////// start
	// //////////////////////////////////////////////////////////////////////

	
	/***
	 * to register user
	 * 
	 * @param UserDTO
	 * @return string
	 */
	private String registerUser(UserDTO userDTO) throws CloudServiceException {

		// conversion from DTO to BO
		//CloudSvrUser user = UserHelper.convertFromDTOtoBO(userDTO);

		String respString = userService.registerUser(userDTO);

		return respString;
	}

	
	/***
	 * get the resultString for logout
	 * 
	 * @param BaseDTO
	 * @return String
	 */
	private String getResultForUserLogout(BaseDTO baseDTO)
			throws CloudServiceException {

		String result = null;

		// conversion from DTO to BO
		CloudSvrUser user = UserHelper.convertFromDTOtoBO(baseDTO);

		// call business layer
		result = userService.logoutService(user);

		return result;
	}

	
	/***
	 * For registering a user
	 * 
	 * @param BaseDTO
	 * @return String
	 */
	private String registerUserInStore(BaseDTO baseDTO)
			throws CloudServiceException {

		String respString = null;
		// conversion from DTO to BO
		CloudSvrUser user = UserHelper.convertFromDTOtoBO(baseDTO);

		respString = userService.registerUserInStoreService(user);

		return respString;
	}

	
	/***
	 * For for update user profile
	 * 
	 * @param UpdateUserDTO
	 * @return String
	 */
	private String updateUserProfile(UpdateUserDTO updateUserDTO)
			throws CloudServiceException {

		// conversion from DTO to BO
		CloudSvrUsersProfile user = UserHelper
				.convertFromDTOtoBOUpdateUserProf(updateUserDTO);

		// call business layer
		String respString = userService
				.updateUserProfileService(user);

		return respString;

	}

	
	/***
	 * To get user details
	 * 
	 * @param BaseDTO contain all the parameters to get user
	 * @return CloudSvrUser object
	 * @throws CloudServiceException
	 */
	private CloudSvrUser getUser(BaseDTO baseDTO) throws CloudServiceException {

		// conversion from DTO to BO
		CloudSvrUser user = UserHelper.convertFromDTOtoBO(baseDTO);

		//UserService userService = this.getUserService();

		user = userService.getUserService(user);

		return user;

	}

	
	/***
	 * To validate user
	 * 
	 * @param LoginDTO contain all the parameters to get user
	 * @return String object
	 * @throws CloudServiceException
	 */
	private String validatingUser(LoginDTO loginDTO) throws CloudServiceException {

		// if user account is not blocked during wrong
		// matched
		// answer during forgot password
		// and user is a valid user then allowed user to
		// login.

		CloudSvrUser userBlock = this.getUser(loginDTO);

		int badSecCountForgot = userBlock.getBadSeccurityAnsCount().intValue();

		if (badSecCountForgot == Integer.parseInt(WebServiceConstants.THREE)) {

			return WebServiceConstants.ONE;
		} else {
			return WebServiceConstants.TWO;
		}
	}

	
	/***
	 * use to register a user
	 * 
	 * @param LoginDTO contain all the parameters for user login
	 * @return String
	 * @throws CloudServiceException
	 */
	private String userLogin(LoginDTO loginDTO) throws CloudServiceException {

		String isUser = null;

	//	UserService userService = this.getUserService();

		// conversion from DTO to BO
		CloudSvrUser userLogin = UserHelper
				.convertFromDTOtoBOuserLogin(loginDTO);

		isUser = userService.validatingUserService(userLogin);
		return isUser;
	}

	
	/***
	 * use to  check if user exist or not
	 * 
	 * @param LoginDTO contain all the parameters for checking user in database
	 * @return String
	 * @throws CloudServiceException
	 */
	private String getResultForuserExist(LoginDTO loginDTO)
			throws CloudServiceException {

		String userExistString = null;
		// business layer integration
		//UserService userService = this.getUserService();

		// check first to know whether coming phone number or email
		// user
		// is already exist or not

		// conversion from DTO to BO
		CloudSvrUser userVal = UserHelper
				.convertFromDTOtoBOIsUserExist(loginDTO);

		userExistString = userService.isUserExistService(userVal);

		return userExistString;

	}

	
	/***
	 *  to get merchant access key in database exist or not
	 * 
	 * @param accessKey represent the access key from mobile
	 * @return true/false
	 * @throws CloudServiceException
	 */
	/*private CloudSvrBusinessEntityInfo accessKeyForMerchantExistService(String accessKey)
			throws CloudServiceException

	{

		boolean keyExist = false;

		// business layer integration for getting accesskey
		CommonService commonService = GetCommonService();

		CloudSvrBusinessEntityInfo entityInfo = CommonHelper
				.convertFromDTOtoBOForMerchantAccessKey(accessKey);

		entityInfo = commonService.isAccessKeyForMerchantExistService(entityInfo);
		return entityInfo;
	}*/


	/***
	 *  to know if user login or not
	 * 
	 * @param LoginDTO contain all the parameters for user login
	 * @return String
	 * @throws CloudServiceException
	 */
	private String alreadyLogin(LoginDTO loginDTO) throws CloudServiceException {

		String isAlreadyLogin = null;
		// conversion from DTO to BO
		CloudSvrUser user = UserHelper
				.convertFromDTOtoBOuserLogin(loginDTO);
	//	UserService userService = this.getUserService();

		isAlreadyLogin = userService.isAlreadyLogedInService(user);

		return isAlreadyLogin;
	}

	

	/***
	 * for user login
	 * 
	 * @param LoginDTO contain all the parameters for user login
	 * @return String
	 * @throws CloudServiceException
	 */
	private String loginFailed(LoginDTO loginDTO) throws CloudServiceException {
		String respString = null;
		// incorrect details
		// if pass code is matching as per database
		// or not
		// if he do wrong attempt 3 time we have to
		// locked
		CloudSvrUser userAttenpt = this.getUser(loginDTO);

		//UserService userService = this.getUserService();

		respString = userService.loginFailedService(userAttenpt);

		return respString;
	}
	
	
	
	/***
	 * get Status for user Login
	 * 
	 * @param loginDTO  LoginDTO object contain all the parameters for user login
	 * @return Status object
	 */
	private Status getUserLogin(LoginDTO loginDTO) throws CloudServiceException {
		Status status = null;
		String respString = null;
		respString = this.getResultForuserExist(loginDTO);

		if (respString != null) {

			if (respString != null
					&& respString.contains(WebServiceConstants.TWO)) {
				status = this.getStatusForUserExist(respString);

				if (status != null) { 
					status = this.getStatus(status);
				} else {
					status = this.serverError(status);
				}
			}

			else if (respString != null
					&& respString.contains(WebServiceConstants.ONE)) {

				String valUser = this.validatingUser(loginDTO);

				if (valUser != null
						&& valUser.contains(WebServiceConstants.ONE)) {
					status = this.accountBlock(status);
					if (status != null) {
						status = this.getStatus(status);
					} else {
						status = this.serverError(status);
					}

				}

				else if (valUser != null
						&& valUser.contains(WebServiceConstants.TWO)) {

					String userVal = this.userLogin(loginDTO);

					if (userVal != null
							&& userVal.contains(WebServiceConstants.ONE)) {
						status = this.getStatusForUserLogin(userVal);
						if (status != null) {
							status = this.getStatus(status);
						} else {
							status = this.serverError(status);
						}
					} else {
						userVal = this.alreadyLogin(loginDTO);

						if (userVal != null
								&& userVal.contains(WebServiceConstants.ONE)) {

							status = this.getStatusForUserAlreadyLogin(userVal);
							if (status != null) {
								status = this.getStatus(status);
							} else {
								status = this.serverError(status);
							}
						}

						else {
							if (userVal != null
									&& userVal
											.contains(WebServiceConstants.TWO)) {
								userVal = this.loginFailed(loginDTO);

								if (userVal != null
										&& userVal
												.contains(WebServiceConstants.ONE)
										|| userVal
												.contains(WebServiceConstants.TWO)) {
									status = this
											.getStatusForLoginFailed(userVal);
									if (status != null) {
										status = this.getStatus(status);
									} else {
										status = this.serverError(status);
									}
								} else {
									status = this.connectionProblem(status);
								}

							}

						}

					}
				}

			} else {
				status = this.connectionProblem(status);
			}
		} else {
			status = this.userNull(status);
		}

		return status;
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

	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// ///////////////////////// start
	// ///////////////////////////////////////////////////////////

	// to get user service
/*	private UserService getUserService() {

		// business layer integration
		UserService userService = (UserService) super.getApplicationContext()
				.getBean(WebServiceConstants.USER_SERVICE);

		return userService;
	}*/

	// to get common service
/*	private CommonService GetCommonService() {

		// business layer integration
		CommonService commonService = (CommonService) super
				.getApplicationContext().getBean(
						WebServiceConstants.COMMON_SERVICE);

		return commonService;
	}*/

	


	// ///////////////////////// end
	// ///////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	// //////////////////////////////////////////////////private method
	// //////////////////////////////////////////////////
	


	
	
	/*Status loginWithKohls(LoginDTO loginDTO) throws MerchantServiceException{
		
		Status as = null;
		
		
		as = new Status();
		as.setMessage(asb.kohlLoginService(loginDTO));
		as.setCode("go");
		as.setType("01");
		return as;
	}*/
}
