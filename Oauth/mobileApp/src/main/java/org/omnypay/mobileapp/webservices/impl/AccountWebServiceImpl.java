package org.omnypay.mobileapp.webservices.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*import org.omnypay.httpclient.dto.CardOnFileDTO;
 import org.omnypay.httpclient.dto.CardOnFileRespDTO;
 import org.omnypay.httpclient.dto.HceTokenDTO;
 import org.omnypay.httpclient.dto.HceTokenRespDTO;
 import org.omnypay.httpclient.dto.MitekServerRespDTO;
 import org.omnypay.httpclient.services.CardOnFileService;
 import org.omnypay.httpclient.services.HceTokenService;
 import org.omnypay.httpclient.services.Impl.MitekServerService;
 import org.omnypay.httpclient.util.ConnectorConstants;
 import org.omnypay.httpclient.util.IntegrationException;
 import org.omnypay.httpclient.util.PropertiesUtil;*/
























import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.AccountWebService;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.service.CommonService;
import com.omnypay.business.service.HceTokenService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.service.impl.MitekServerService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.business.util.PropertiesUtil;
import com.omnypay.common.services.AccountHelper;
import com.omnypay.common.services.BaseWebServiceImpl;
import com.omnypay.common.services.CommonHelper;
import com.omnypay.common.services.TransactionHelper;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.common.services.dto.HceTokenDTO;
import com.omnypay.common.services.dto.HceTokenRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.MitekServerRespDTO;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;
import com.omnypay.merchant.services.MerchantAccountService;
import com.omnypay.merchant.services.MerchantUserServices;

@Component
@Path("/Account")
public class AccountWebServiceImpl implements 	AccountWebService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();

	private final String CLASS_NAME = this.getClass().getName();

	public AccountWebServiceImpl() {

		// log.debug("Account service created");
	}
	
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MerchantUserServices merchantUserServices;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	HceTokenService hceService;
	
	@Autowired
	MitekServerService mitekServerService;
	
	@Autowired
	MerchantAccountService merchantAccountService;
	
	
	

	/**
	 * 
	 * add card request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all other details
	 * about user add card send both success and failure message to the mobile
	 * 
	 * @param AccountDTO
	 *            object containing all the parameters send from mobile to add a
	 *            Card
	 * @return response object to the mobile regarding user addCard
	 * @see org.omnypay.mobileapp.webservices.AccountWebService#addCard(org.omnypay
	 *      .mobileapp.dto.AccountDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/addCard")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCard(AccountDTO accountDTO) {

		String METHOD_NAME = "addCard";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);
		Response response = null;

		AccountRespDTO status = null;

		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(accountDTO)
					&& AccountHelper.checkMandatoryFieldsCard(accountDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false

				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(accountDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

					
					status = merchantAccountService.addCard(merchantDTO, accountDTO);
					
					
					// for internal merchant	
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					CloudSvrUser user = this.getUser(accountDTO);

					if (user != null) {

						CardOnFileRespDTO accountId = this.addCard(accountDTO,
								user.getUserId());

						status = AccountHelper.addCard(accountId);

						if (status != null) {

							accountId = this.updateAccount(accountDTO,
									user.getUserId());

						} else {

							status = CloudServiceStatus
									.accountStatus(CloudServiceStatus
											.getStatusByErrorCode(ErrorCodeConstants.CONNECTING_WITH_COF));

						}

					} else {

						status = CloudServiceStatus
								.accountStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.GET_USER));

					}

				} else {

					status = CloudServiceStatus
							.accountStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.accountStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {

			String message = "businessException occured while add card";

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.accountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.ADD_CARD));

			String message = "Exception occured while add card";

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

	// ////////////////////////////////////////////////////////////

	/**
	 * 
	 * request from mobile to add Bank Returns response object that will send to
	 * the mobile containing all the details about user added Bank and other
	 * details send both success and failure message to the mobile
	 * 
	 * @param AccountDTO
	 *            object containing all the parameters send from mobile to add a
	 *            Bank
	 * @return response object to the mobile regarding user addBank
	 * @see org.omnypay.mobileapp.webservices.AccountWebService#addBank(org.omnypay
	 *      .mobileapp.dto.AccountDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/addBank")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBank(AccountDTO accountDTO) {
		// TODO Auto-generated method stub

		String METHOD_NAME = "addBank";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		AccountRespDTO status = null;
		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(accountDTO)
					&& AccountHelper.checkMandatoryFieldsBank(accountDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(accountDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {
		
					
					
					status = merchantAccountService.addBank(merchantDTO, accountDTO);
					
					
					// for internal merchant
				} else 	if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {
					
					CloudSvrUser user = this.getUser(accountDTO);

					if (user != null) {

						CardOnFileRespDTO accountId = this.addBank(accountDTO,
								user.getUserId());

						status = this.addBank(accountId);

						if (status != null) {

							this.updateAccount(accountDTO, user.getUserId());

						} else {

							status = CloudServiceStatus
									.accountStatus(CloudServiceStatus
											.getStatusByErrorCode(ErrorCodeConstants.CONNECTING_WITH_COF));

						}

					} else {

						status = CloudServiceStatus
								.accountStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.GET_USER));

					}

				}  else {

					status = CloudServiceStatus
							.accountStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.accountStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (IntegrationException integrationException) {
			String message = "integrationException occured while add bank/ach ";

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusIntegrationException(integrationException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					integrationException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "cloudServiceException occured while add bank/ach";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.accountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.ADD_ACH));

			String message = "Exception occured while add bank/ach";

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

	// /////////////////////////////////////////////////////////////

	/**
	 * 
	 * request from mobile to delete an Account Returns response object that
	 * will send to the mobile containing all the details after deleting the
	 * account send both success and failure message to the mobile
	 * 
	 * @param AccountDTO
	 *            object containing all the parameters send from mobile to
	 *            delete an account
	 * @return response object to the mobile regarding user deleteAcct
	 * @see org.omnypay.mobileapp.webservices.AccountWebService#deleteAcct(org.omnypay
	 *      .mobileapp.dto.AccountDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/deleteAcct")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(AccountDTO accountDTO) {

		String METHOD_NAME = "deleteAccount";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		AccountRespDTO status = null;

		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(accountDTO)
					&& AccountHelper
							.checkMandatoryFieldsDeleteAccount(accountDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
			
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(accountDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {
					
					
					
					status = merchantAccountService.deleteAccount(merchantDTO, accountDTO);
				
					
				// for internal merchant 	
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {
					
					CloudSvrUser user = this.getUser(accountDTO);

					if (user != null) {

						CardOnFileRespDTO accountId = this.delete(accountDTO,
								user.getUserId());

						if (accountId != null) {

							status = this.delete(accountId);

						} else {

							status = CloudServiceStatus
									.accountStatus(CloudServiceStatus
											.getStatusByErrorCode(ErrorCodeConstants.CONNECTING_WITH_COF_DELETEACCOUNT));

						}

					} else {

						status = CloudServiceStatus
								.accountStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.GET_USER));

					}

				}  else {

					status = CloudServiceStatus
							.accountStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			}  else {

				status = CloudServiceStatus
						.accountStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (IntegrationException integrationException) {

			String message = "integrationException occured while DeleteAccount Card/ach ";

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusIntegrationException(integrationException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					integrationException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (CloudServiceException cloudServiceException) {

			String message = "cloudServiceException occured while DeleteAccount Card/ach ";

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.accountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.DELETE_ACCOUNT));

			String message = "Exception occured while DeleteAccount Card/ach";

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

	// ////////////////////////////////////////////////////////////

	/**
	 * 
	 * request from mobile for offline token Returns response object that will
	 * send to the mobile containing all the details about user offline tokens
	 * and other fields send both success and failure message to the mobile
	 * 
	 * @param AccountDTO
	 *            object containing all the parameters send from mobile to
	 *            getMoreToken a user
	 * @return response object to the mobile regarding user getMoreToken
	 * @see org.omnypay.mobileapp.webservices.AccountWebService#getMoreOffLineToken(org.omnypay
	 *      .mobileapp.dto.AccountDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/getMoreToken")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMoreOffLineToken(AccountDTO accountDTO) {

		String METHOD_NAME = "getMoreOffLineToken";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		AccountRespDTO status = null;
		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(accountDTO)
					&& AccountHelper
							.checkMandatoryFieldsDeleteAccount(accountDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(accountDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

					status = merchantAccountService.getMoreOffLineToken(merchantDTO, accountDTO);
					
					
					
				// for internal merchant	
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					CloudSvrUser user = this.getUser(accountDTO);

					if (user != null) {

						HceTokenRespDTO token = this.moreOfflineToken(
								accountDTO, user);

						status = this.moreOfflineToken(token);

					} else {

						status = CloudServiceStatus
								.accountStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.GET_USER));

					}

				}  else {

					status = CloudServiceStatus
							.accountStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.accountStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (IntegrationException integrationException) {
			String message = "integrationException occured getMoreOffLineToken ";

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusIntegrationException(integrationException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					integrationException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (CloudServiceException cloudServiceException) {

			String message = "businessException occured getMoreOffLineToken ";

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {
			status = CloudServiceStatus
					.accountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_MORE_OFFLINE_TOKEN));

			String message = "Exception occured while DeleteAccount Card/ach";

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

	// //////////////////////////////////////

	/**
	 * 
	 * request from mobile while try to scan check or card Returns response
	 * object that will send to the mobile containing all the details after
	 * scanned the check and other fields got from server send both success and
	 * failure message to the mobile
	 * 
	 * @param AccountDTO
	 *            object containing all the parameters send from mobile to
	 *            getMoreToken a user
	 * @return response object to the mobile regarding user getMoreToken
	 * @see org.omnypay.mobileapp.webservices.AccountWebService#getMoreOffLineToken(org.omnypay
	 *      .mobileapp.dto.AccountDTO)
	 * 
	 */
	@Path("/getDataForCheckScan")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkScanRequest(AccountDTO accountDTO) {

		String METHOD_NAME = "checkScanRequest";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		AccountRespDTO status = null;

		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(accountDTO)
					&& AccountHelper
							.checkMandatoryFieldsCheckScanCardData(accountDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(accountDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

					status = merchantAccountService.checkScanRequest(merchantDTO, accountDTO);
					
					
				} else 	if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					MitekServerRespDTO mitekServerRespDTO = this
							.mitekServer(accountDTO);

					if (mitekServerRespDTO != null) {

						status = this.mitekServer(mitekServerRespDTO);

					} else {

						status = CloudServiceStatus
								.accountStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.CONNECTION_WITH_MITECH));

					}
				} else {

					status = CloudServiceStatus
							.accountStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.accountStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {

			String message = "businessException occured while check Scan Request ";

			status = CloudServiceStatus.accountStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.accountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_DATA_FOR_CHECK_SCAN));

			String message = "Exception occured while check Scan Request";

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

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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

	// to get account service
	/*private AccountService CommonGetAccountService() {

		AccountService accountService = (AccountService) super
				.getApplicationContext().getBean(
						WebServiceConstants.ACCOUNT_SERVICE);

		return accountService;
	}
*/
	/*
	 * // to get account service private AccountService getAccountService() {
	 * 
	 * 
	 * 
	 * AccountService accountService = (AccountService) super
	 * .getApplicationContext().getBean(WebServiceConstants.ACCOUNT_SERVICE);
	 * 
	 * return accountService; }
	 */

	// to get user service
/*	private UserService getUserService() {

		// business layer integration
		UserService userService = (UserService) super.getApplicationContext()
				.getBean(WebServiceConstants.USER_SERVICE);

		return userService;
	}
*/
	// to get hce service
/*	private HceTokenService CommonGetHceService() {

		// business layer integration
		HceTokenService hceService = (HceTokenService) super
				.getApplicationContext().getBean(
						WebServiceConstants.HCE_SERVICE);

		return hceService;
	}*/

	// to get mitek server service
/*	private MitekServerService GetMitekServerService() {

		// business layer integration
		MitekServerService mitekServerConnector = (MitekServerService) super
				.getApplicationContext().getBean(
						WebServiceConstants.MITECK_SERVER_SERVICE);

		return mitekServerConnector;
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
	 * get Status for server error
	 * 
	 * @param errorcode
	 * @return AccountRespDTO as status
	 */
	private AccountRespDTO serverError(String errorcode) {

		AccountRespDTO status = new AccountRespDTO();

		status.setCode(errorcode);

		String msg = PropertiesUtil.getProjectProperties().getProperty(
				errorcode);
		status.setMessage(msg);
		status.setType(WebServiceConstants.FIVE);

		return status;

	}

	
	/***
	 * get Status after adding an bank/ach
	 * 
	 * @param accountId
	 * @return AccountRespDTO as status
	 */
	private AccountRespDTO addBank(CardOnFileRespDTO accountId) {

		AccountRespDTO status = null;

		if (accountId != null
				&& accountId.getType() != null
				&& accountId.getType()
						.equalsIgnoreCase(WebServiceConstants.ONE)) {

			status = (AccountRespDTO) WebServiceUtil.getAccount(
					WebServiceConstants.ADD_BANK_SUCCESS,
					WebServiceConstants.ONE, accountId.getAccId(),
					accountId.getTokens(), accountId.getTokenexpDate(),
					accountId.getUdk());
		} else if (accountId != null
				&& accountId.getType() != null
				&& accountId.getType()
						.equalsIgnoreCase(WebServiceConstants.TWO)) {

			status = (AccountRespDTO) WebServiceUtil.getAccount(
					WebServiceConstants.HCE_SERVER_ERROR,
					WebServiceConstants.TWO, accountId.getAccId(), null, null,
					null);

		} else if (accountId != null
				&& accountId.getType() != null
				&& accountId.getType().equalsIgnoreCase(
						WebServiceConstants.THREE)) {

			status = (AccountRespDTO) WebServiceUtil.getAccount(
					WebServiceConstants.ADD_BANK_EXIST,
					WebServiceConstants.THREE, accountId.getAccId(), null,
					null, null);

		} else if (accountId != null
				&& accountId.getType() != null
				&& accountId.getType().equalsIgnoreCase(
						WebServiceConstants.FOUR)) {

			status = (AccountRespDTO) WebServiceUtil.getAccount(
					WebServiceConstants.COF_SERVER_ERROR,
					WebServiceConstants.FOUR, accountId.getAccId(), null, null,
					null);

		}

		return status;
	}

	/***
	 * get Status after deleting an account
	 * 
	 * @param accountId
	 * @return AccountRespDTO as status
	 */
	private AccountRespDTO delete(CardOnFileRespDTO accountId) {

		AccountRespDTO status = null;

		if (accountId.getType() != null
				&& accountId.getType()
						.equalsIgnoreCase(WebServiceConstants.ONE)) {

			status = WebServiceUtil.getDeleteStatus(
					WebServiceConstants.ACCOUNT_DELETED,
					WebServiceConstants.NUMBER_ONE);

		} else if (accountId.getType() != null
				&& accountId.getType()
						.equalsIgnoreCase(WebServiceConstants.TWO)) {

			status = WebServiceUtil.getDeleteStatus(
					WebServiceConstants.ACCOUNT_NOT_DELETED,
					WebServiceConstants.NUMBER_TWO);
		}

		return status;
	}

	/***
	 * get Status for offline token
	 * 
	 * @param HceTokenRespDTO
	 * @return AccountRespDTO as status
	 */
	private AccountRespDTO moreOfflineToken(HceTokenRespDTO token) {

		AccountRespDTO status = null;

		if (token != null
				&& token.getStatusCode().equalsIgnoreCase(
						WebServiceConstants.HCE_SUCCESS)) {

			status = (AccountRespDTO) WebServiceUtil.getAccountForMoreToken(
					WebServiceConstants.GETTING_TOKEN_SUCCESS,
					WebServiceConstants.ONE, token.getTokens(),
					token.getTokenexpDate());

		} else {

			status = (AccountRespDTO) WebServiceUtil.getAccountForMoreToken(
					WebServiceConstants.GETTING_TOKEN__FAIL,
					WebServiceConstants.TWO, null, null);

		}

		return status;
	}

	/***
	 * get Status object after scanning the check
	 * 
	 * @param MitekServerRespDTO
	 * @return AccountRespDTO as status
	 */
	private AccountRespDTO mitekServer(MitekServerRespDTO mitekServerRespDTO) {

		AccountRespDTO status = null;

		if (mitekServerRespDTO.getAccBankRout() != null
				&& mitekServerRespDTO.getCheckNo() != null
				&& mitekServerRespDTO.getAccNo() != null) {

			status = (AccountRespDTO) WebServiceUtil
					.getAccountFieldsFromMitech(
							WebServiceConstants.GETTING_CHECK_DETAILS_SUCCESS,
							WebServiceConstants.ONE, mitekServerRespDTO);
		}

		else {

			status = (AccountRespDTO) WebServiceUtil
					.getAccountFieldsFromMitech(
							WebServiceConstants.GETTING_CHECK_DETAILS_FAILED,
							WebServiceConstants.TWO, mitekServerRespDTO);
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
	 * to get user deatils
	 * 
	 * @param AccountDTO
	 * @return CloudSvrUser object
	 * @throws CloudServiceException
	 */
	// to get userID
	private CloudSvrUser getUser(AccountDTO accountDTO)
			throws CloudServiceException {

		CloudSvrUser user = null;

		// conversion from DTO to BO
		user = UserHelper.convertFromDTOtoBO(accountDTO);

		//UserService userService = this.getUserService();

		user = userService.getUserService(user);

		return user;
	}

	/***
	 * to add card
	 * 
	 * @param accountDTO
	 *            - containing all the fields required to add a card
	 * @param userid
	 *            - represent the userId
	 * @return
	 * @throws CloudServiceException
	 */
	private CardOnFileRespDTO addCard(AccountDTO accountDTO, String userid)
			throws CloudServiceException {

		// conversion from DTO to BO
		CardOnFileDTO cofAccount = AccountHelper
				.convertFromDTOtoCOFDTOAddCardAndBank(accountDTO, userid);

		CardOnFileRespDTO accountId = null;
		// AccountService accountService = this.getAccountService();
		//AccountService accountService = this.CommonGetAccountService();

		accountId = accountService.addAccountService(cofAccount);

		return accountId;
	}

	/***
	 * to add bank/ach
	 * 
	 * @param accountDTO
	 *            - containing all the fields required to add Bank/ach
	 * @param userid
	 *            - represent the userId
	 * @return
	 */
	private CardOnFileRespDTO addBank(AccountDTO accountDTO, String userid)
			throws CloudServiceException {

		// conversion from DTO to BO
		CardOnFileDTO cofAccount = AccountHelper
				.convertFromDTOtoCOFDTOAddCardAndBank(accountDTO, userid);

		// AccountService accountService = this.getAccountService();
		//AccountService accountService = this.CommonGetAccountService();

		CardOnFileRespDTO accountId = accountService.addAchService(cofAccount);

		return accountId;

	}

	/***
	 * to delete the card/bank
	 * 
	 * @param accountDTO
	 *            - containing all the fields required to add an
	 *            account(card/bank)
	 * @param userid
	 *            - represent the userId
	 * @return
	 */
	private CardOnFileRespDTO delete(AccountDTO accountDTO, String userid)
			throws CloudServiceException, IntegrationException {

		CardOnFileDTO cofAccount = AccountHelper
				.convertFromDTOtoBODeleteAccount(accountDTO, userid);

		// card on file Service
		//AccountService accountService = CommonGetAccountService();

		CardOnFileRespDTO accountId = accountService
				.deleteAccountService(cofAccount);

		return accountId;

	}

	/***
	 * to update Account (card/bank)
	 * 
	 * @param accountDTO
	 *            - containing all the fields required to add an
	 *            account(card/bank)
	 * @param userid
	 *            - represent the userId
	 * @return
	 */
	private CardOnFileRespDTO updateAccount(AccountDTO accountDTO, String userid)
			throws CloudServiceException, IntegrationException {

		// conversion from DTO to BO
		CardOnFileDTO cofAccount = AccountHelper
				.convertFromDTOtoCOFDTOAddCardAndBank(accountDTO, userid);

		//AccountService accountService = this.CommonGetAccountService();

		CardOnFileRespDTO accountId = accountService
				.updateAccountService(cofAccount);

		return accountId;

	}

	/***
	 * to get accessKey For Merchant exist in database or not
	 * 
	 * @param accessKey
	 *            - represent the access key getting from mobile
	 * @return true/false
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
	 * to get more Offline Token
	 * 
	 * @param accountDTO
	 *            - containing all the fields required to add an
	 *            account(card/bank)
	 * @param user
	 *            - represent the user details
	 * @return HceTokenRespDTO
	 * @throws CloudServiceException
	 * @throws IntegrationException
	 */
	private HceTokenRespDTO moreOfflineToken(AccountDTO accountDTO,
			CloudSvrUser user) throws CloudServiceException,
			IntegrationException {

		HceTokenRespDTO token = null;

		// conversion from DTO to BO
		CardOnFileRespDTO accountId = AccountHelper
				.convertFromDTOtoBOGetMoreOfflineToken(accountDTO);

		// Set DTO
		HceTokenDTO hceTokenDTO = TransactionHelper
				.convertFromDTOtoBOOfflineToken(accountId, user);

		// HCE Service
	//	HceTokenService accountService = CommonGetHceService();

		// here when u are adding card
		token = hceService.getOnlineTokenService(hceTokenDTO,
				WebServiceConstants.CREDIT_CARD_TYPE);

		return token;

	}

	/***
	 * to give request to mitek Server
	 * 
	 * @param accountDTO
	 *            - containing all the fields required to give request to mitek
	 *            server
	 * @return MitekServerRespDTO
	 */
	private MitekServerRespDTO mitekServer(AccountDTO accountDTO) {

		MitekServerRespDTO checkData = null;

	//	MitekServerService mitekServerService = GetMitekServerService();

		String xmlResponse = mitekServerService.sendRequestToMitek(accountDTO
				.getAccCheckScanData());

		checkData = mitekServerService.parseXmlResponse(xmlResponse);

		return checkData;

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

	
}