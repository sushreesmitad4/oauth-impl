package org.omnypay.mobileapp.webservices.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;























import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.SecurityQuestionWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.CommonService;
import com.omnypay.business.service.PasswordService;
import com.omnypay.business.service.SecurityQuestionService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.BaseWebServiceImpl;
import com.omnypay.common.services.CommonHelper;
import com.omnypay.common.services.SecurityQuestionHelper;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.RespSubFieldsDTO;
import com.omnypay.common.services.dto.SecurityQuestionsDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.merchant.services.MerchantSecurityQuestionService;
import com.omnypay.merchant.services.MerchantUserServices;
import com.sun.jersey.spi.resource.Singleton;

@Component
@Path("/securityQuestion")
public class SecurityQuestionWebServiceImpl
		implements SecurityQuestionWebService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();

	private final String CLASS_NAME = this.getClass().getName();

	// constructor
	public SecurityQuestionWebServiceImpl() {
	}

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
			
	@Autowired
	private SecurityQuestionService secQuestionService;
	
	@Autowired
	private MerchantSecurityQuestionService merchantSecurityQuestionService;
	
	
	
	/**
	 * 
	 * fetchSecurityQuestionList request from mobile send response back to
	 * mobile Returns response object that will send to the mobile containing
	 * all the details about user fetchSecurityQuestionList send both success
	 * and failure message to the mobile
	 * 
	 * @param BaseDTO
	 *            object containing all the parameters send from mobile to fetch
	 *            Security Question List of user
	 * @return response object to the mobile regarding user
	 *         fetchSecurityQuestionList
	 * @see org.omnypay.mobileapp.webservices.SecurityQuestionWebService#
	 *      fetchSecurityQuestionList (org .omnypay .mobileapp.dto.BaseDTO)
	 * 
	 * 
	 * 
	 */
	@POST
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fetchSecurityQuestionList(BaseDTO baseDTO) {

		String METHOD_NAME = "fetchSecurityQuestionList";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		SecurityQuestionsRespDTO status = null;

		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(baseDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(baseDTO.getMerchantAccessKey());
				
				

				if (merchantDTO !=null & merchantDTO.getIsExternal()) {

					status =  merchantSecurityQuestionService.fetchSecurityQuestion(merchantDTO, baseDTO);

				// for internal merchant	
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					List<CloudSvrSecQuesMaster> defaultList = this
							.fetchSecurityQuestion();
					status = this.fetchSecurityQuestion(defaultList);

				} else {

					status = CloudServiceStatus
							.securitytStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.securitytStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {

			String message = "businessException occured while fetchSecurityQuestion";

			status = CloudServiceStatus.securitytStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {
			status = CloudServiceStatus
					.securitytStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.FETCH_SECURITY_QUESTIONS));

			String message = "Exception occured while fetchSecurityQuestion";

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
	 * getSecurityQuestions request from mobile send response back to mobile
	 * Returns response object that will send to the mobile containing all the
	 * details about user getSecurityQuestions send both success and failure
	 * message to the mobile
	 * 
	 * @param BaseDTO
	 *            object containing all the parameters send from mobile to get
	 *            Security Questions of a user
	 * @return response object to the mobile regarding user security questions
	 * @see org.omnypay.mobileapp.webservices.SecurityQuestionWebService#
	 *      secQuestionList(org .omnypay .mobileapp.dto.BaseDTO)
	 * 
	 * 
	 * 
	 */
	@POST
	@Path("/secQuestionList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSecurityQuestions(BaseDTO baseDTO) {

		String METHOD_NAME = "getSecurityQuestions";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		Status status = null;

		Response response = null;

		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(baseDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(baseDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {
					
					
					
				status =  merchantSecurityQuestionService.getSecurityQuestions(merchantDTO, baseDTO);
					
					
			
				//for internal merchant
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {
					
					CloudSvrUser dbuser = this.getUser(baseDTO);

					
					if (dbuser != null) {
						
						String respString = this
								.getSecurityQuestionsList(dbuser);

						status = this.getSecurityQuestionsList(respString,
								dbuser);
					} else {
						status = CloudServiceStatus
								.securitytStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.GET_USER));
					}

				} else {

					status = CloudServiceStatus
							.securitytStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.securitytStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {
			String message = "businessException while doing user getSecurityQuestions";

			status = CloudServiceStatus.securitytStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.securitytStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_SECURITY_QUESTIONS));

			String message = "Exception occured while doing user getSecurityQuestions";

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
	 * setQuestionList request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * to set user security questions send both success and failure message to
	 * the mobile
	 * 
	 * @param BaseDTO
	 *            object containing all the parameters send from mobile to set
	 *            Security Questions a user
	 * @return response object to the mobile regarding user set Security
	 *         Questions
	 * @see org.omnypay.mobileapp.webservices.SecurityQuestionWebService#
	 *      setQuestionList(org .omnypay .mobileapp.dto.SecurityQuestionsDTO)
	 * 
	 * 
	 * 
	 */

	@POST
	@Path("/setQuestionList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSecurityQuestions(
			SecurityQuestionsDTO securityQuestionsDTO) {
		// TODO Auto-generated method stub

		String METHOD_NAME = "setSecurityQuestions";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		Status status = null;

		Response response = null;

		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(securityQuestionsDTO)
					&& UserHelper
							.checkMandatoryFieldsSecurityQuestions(securityQuestionsDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(securityQuestionsDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

					status =  merchantSecurityQuestionService.setSecurityQuestions(merchantDTO, securityQuestionsDTO);

				// for internal merchant	
				} else 	if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					String respString = this
							.setSelectedSecurityQuestions(securityQuestionsDTO);

					status = this.setSelectedSecurityQuestions(respString);

				}  else {

					status = CloudServiceStatus
							.securitytStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.securitytStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {

			String message = "businessException occured while doing user setSecurityQuestions";

			status = CloudServiceStatus.securitytStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.accountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.SET_SEC_QUESTION));

			String message = "Exception occured while doing user setSecurityQuestions";

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

	// /////////////////////////service service service

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
	// /////////////////////////service service service
	// service///////////////////////////////////////////////////////////////////

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

	/***
	 * for checking whether bad security count will be 3 or not,If its 3 then
	 * user has to block
	 * 
	 * 
	 * @param user
	 *            CloudSvrUser object representing all details of user
	 * @return String
	 * 
	 */
	private String getSecurityQuestionsList(CloudSvrUser user) {
		String result = null;

		int badSecCountForgot = user.getBadSeccurityAnsCount().intValue();

		if (badSecCountForgot == Integer.parseInt(BusinessConstants.THREE)) {

			result = BusinessConstants.THREE;

		} else {
			result = BusinessConstants.ONE;
		}
		return result;
	}

	/***
	 * fetch security question from db and give response to the user
	 * 
	 * 
	 * @param boList
	 *            List<CloudSvrSecQuesMaster> object representing all details
	 *            required for getting Security Questions
	 * @return status SecurityQuestionsRespDTO contain all the required fields
	 *         for Security Questions
	 *
	 */
	private SecurityQuestionsRespDTO fetchSecurityQuestion(
			List<CloudSvrSecQuesMaster> boList) {
		SecurityQuestionsRespDTO status = null;
		List<RespSubFieldsDTO> subFields = null;

		if (boList != null) {

			// convert BOList to DTOList
			subFields = SecurityQuestionHelper
					.converFromBOListToDTOList(boList);

			// get status to send to client
			status = (SecurityQuestionsRespDTO) WebServiceUtil
					.getSecurityQuestionResp(WebServiceConstants.RECORDS_FOUND,
							WebServiceConstants.ONE, subFields);

		} else {
			// get status to send to client
			status = (SecurityQuestionsRespDTO) WebServiceUtil
					.getSecurityQuestionResp(
							WebServiceConstants.RECORDS_NOT_FOUND,
							WebServiceConstants.TWO, subFields);
		}

		return status;

	}

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

		CloudSvrBusinessEntityInfo entityInfo = CommonHelper
				.convertFromDTOtoBOForMerchantAccessKey(accessKey);

		CommonService commonService = GetCommonService();

		entityInfo = commonService.isAccessKeyForMerchantExistService(entityInfo);

		return entityInfo;
	}*/

	/***
	 * to fetch Security Question for a user
	 * 
	 * @param accessKey
	 *            represent the access key from mobile
	 * @return true/false
	 * @throws CloudServiceException
	 */

	private List<CloudSvrSecQuesMaster> fetchSecurityQuestion()
			throws CloudServiceException {

/*		SecurityQuestionService secQuestionService = this
				.getSecQuestionService();*/

		List<CloudSvrSecQuesMaster> boList = secQuestionService
				.getSecurityQuestionsService();

		return boList;
	}

	/***
	 * to fetch Security Question for a user
	 * 
	 * @param baseDTO
	 *            BaseDTO represent the access key from mobile
	 * @return user CloudSvrUser object representing all details of user
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
	 * to get Security Question for a user
	 * 
	 * @param result
	 *            BaseDTO represent the access key from mobile
	 * @param user
	 *            CloudSvrUser object representing all details of user
	 * @return status Status object
	 * @throws CloudServiceException
	 */
	private Status getSecurityQuestionsList(String result, CloudSvrUser user)
			throws CloudServiceException {
		List<RespSubFieldsDTO> userSecQuestionList = null;

		Status status = null;

		if (result != null) {

			if (result.contains(WebServiceConstants.THREE)) {
				status = WebServiceUtil.getSecurityQuestionResp(
						WebServiceConstants.ACCOUNT_BLOCKED,
						WebServiceConstants.THREE, userSecQuestionList);
			}

			else if (result.contains(WebServiceConstants.ONE)) {

				// call service layer
				List<CloudSvrSecQuesMaster> secQuestionMasterList = userService.getSelectedSecurityQuestionsService(
								user);
				if (secQuestionMasterList != null) {
					// convert BOList to DTOList
					userSecQuestionList = UserHelper
							.converFromBOListToDTOList(secQuestionMasterList);

					// get status to send to client
					status = WebServiceUtil.getSecurityQuestionResp(
							WebServiceConstants.RECORDS_FOUND,
							WebServiceConstants.ONE, userSecQuestionList);
				}

				else {
					// get status to send to client
					status = WebServiceUtil.getSecurityQuestionResp(
							WebServiceConstants.SECURITY_QUESTIONS_NOT_FOUND,
							WebServiceConstants.TWO, userSecQuestionList);
				}

			}

		}
		return status;
	}

	/***
	 * to set security questions for a user
	 * 
	 * @param securityQuestionsDTO
	 *            SecurityQuestionsDTO represent the required field to set
	 *            security questions
	 * 
	 * @return result String object
	 * @throws CloudServiceException
	 */
	private String setSelectedSecurityQuestions(
			SecurityQuestionsDTO securityQuestionsDTO)
			throws CloudServiceException

	{
		String result = null;

		// conversion from DTO to BO
		CloudSvrUser user = UserHelper
				.convertFromDTOtoBOSecurityQuestions(securityQuestionsDTO);

		// call service layer
		result = userService.setSelectedSecurityQuestionsService(user);

		return result;
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
	 * get Status for set Selected Security Questions
	 * 
	 * @param dbuser
	 *            CloudSvrUser object having all the details of user
	 * @param result
	 *            a string having the response
	 * @return status Status object having response
	 */
	private Status setSelectedSecurityQuestions(String result) {

		Status status = null;
		if (result != null) {
			if (result.contains(WebServiceConstants.ONE)) {
				// get status to send to client
				status = WebServiceUtil.getStatus(
						WebServiceConstants.SECURITY_QUESTIONS_ANSWERS_ADDED,
						WebServiceConstants.NUMBER_ONE);

			} else if (result.contains(WebServiceConstants.TWO)) {

				status = WebServiceUtil
						.getStatus(
								WebServiceConstants.SECURITY_QUESTIONS_ANSWERS_ADDED_NOT,
								WebServiceConstants.NUMBER_TWO);
			}
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
