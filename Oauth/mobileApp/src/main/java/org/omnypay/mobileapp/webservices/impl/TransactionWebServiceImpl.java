package org.omnypay.mobileapp.webservices.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.TransactionWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.service.CommonService;
import com.omnypay.business.service.CouponService;
import com.omnypay.business.service.EcommerceService;
import com.omnypay.business.service.HceTokenService;
import com.omnypay.business.service.PosService;
import com.omnypay.business.service.TransactionService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.service.impl.MitekServerService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.business.util.PropertiesUtil;
import com.omnypay.common.services.AccountHelper;
import com.omnypay.common.services.BaseWebServiceImpl;
import com.omnypay.common.services.CommonHelper;
import com.omnypay.common.services.EcommerceHelper;
import com.omnypay.common.services.TransactionHelper;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.common.services.dto.HceTokenDTO;
import com.omnypay.common.services.dto.HceTokenRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.PosDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.SwitchTxnRespDTO;
import com.omnypay.common.services.dto.TransactionDTO;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrPosCon;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.dao.bo.CloudSvrTempToken;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;
import com.omnypay.merchant.common.IMerchantTransactionService;
import com.omnypay.merchant.kohls.servicesImpl.KohlTransactionServiceImpl;
import com.omnypay.merchant.services.MerchantTransactionService;
import com.omnypay.merchant.services.MerchantUserServices;
import com.sun.jersey.spi.resource.Singleton;

/**
 * 
 * @author iliyasm
 *
 */
@SuppressWarnings("unused")
//@Singleton
@Component
@Path("/transaction")
public class TransactionWebServiceImpl  implements
		TransactionWebService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MerchantUserServices merchantUserServices;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HceTokenService hceService;
	
	@Autowired
	private TransactionService txnService;
	
	@Autowired
	private PosService posService;
	
	@Autowired
	private MerchantTransactionService merchantTransactionService;
	
	@Autowired
	private CouponService couponService;
	
	
	@Autowired
	KohlTransactionServiceImpl kohlTransactionService;
	
	
	
	
	

	ConcurrentMap<String, CloudSvrQrCodeTxn> mapQrCode = null;

	ConcurrentMap<String, CloudSvrEcommerceTxn> mapEcommCode = null;

	// constructor
	public TransactionWebServiceImpl() {

		mapQrCode = new ConcurrentHashMap<String, CloudSvrQrCodeTxn>();

		mapEcommCode = new ConcurrentHashMap<String, CloudSvrEcommerceTxn>();
	}

	
	
	
	
	@POST
	@Path("/processSplit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Response> processSplit(List<TransactionDTO> tranDTOList) {

		TransactionInitiateRespDTO status = null;
		
		Response response = null;
		boolean isSplit =false;
		
		
		List<Response> responses = new ArrayList<Response>();
		if (tranDTOList !=null && tranDTOList.get(0).isSplit()) {
			if (tranDTOList.size() > 1) {
				double totalAmount = 0;
				for (TransactionDTO tranDTO : tranDTOList) {
					totalAmount = totalAmount + Double.valueOf(tranDTO.getAmount());
				}
				for (TransactionDTO tranDTO : tranDTOList) {
					//if (totalAmount == Double.valueOf(tranDTO.getTotalAmount())) {
										
						responses.add(process(tranDTO,!isSplit));
						isSplit =true;	
					//}
				/*	else {	
						status = CloudServiceStatus
								.getTransactionStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.TRANSACTION_INITIATE_AMOUNT_NOT));
						response = Response.status(javax.ws.rs.core.Response.Status.OK)
								.entity(status).build();
						responses.add(response);
					}*/
					//	return responses;
				}
					
				}
			}
		 else {
			responses.add(process(tranDTOList.get(0),isSplit));
		}

		
		
		
		return responses;
	}

	
	
	/**
	 * initiate request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * to initiate a transaction.
	 * org.omnypay.mobileapp.webservices.TransactionWebService#initiate
	 * (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters send from mobile to
	 *            initiate the transaction
	 * @return response object to the mobile
	 * 
	 * 
	 * 
	 */
	@POST
	@Path("/initiate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initiate(TransactionDTO tranDTO) {

		String METHOD_NAME = "initiate";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(tranDTO)
					&& TransactionHelper.checkMandatoryInitiateFields(tranDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
			
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(tranDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

				
					status = merchantTransactionService.initiate(merchantDTO, tranDTO);
					
					
				// for the internal	
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					// checking if posId contain only number or not
					// if it not contains only number do the ecommerce
					// amount confirmation else normal amount flow
					boolean isEcommTrans = checkPosIdContainNumber(tranDTO
							.getPosId());

					if (isEcommTrans) {
						// if for e-commerce
						status = isEcommerceAmountConfirmationTxn(tranDTO);

					} else {

						// if for QR code
						status = isQrCOdeAmountConfirmationTxn(tranDTO);
					}

				}  else {

					status = CloudServiceStatus
							.getTransactionStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (IntegrationException integrationException) {

			status = CloudServiceStatus.getTransactionStatus(CloudServiceStatus
					.getStatusByIntergrationException(integrationException));

			String message = "integrationException occured while initiate amount";

			CloudServiceExceptionLogger.LogCloudServiceException(
					integrationException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.getTransactionStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException occured while initiate amount";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

			// // return getInitiateServiceException(cloudServiceException);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.TRANSACTION_INITIATE_AMOUNT_NOT));

			String message = "Exception occured while initiate amount";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getInitiateException(ex);

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
	 * process payment request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * about user process payment send both success and failure message to the
	 * mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters send from mobile to
	 *            process a transaction
	 * @return response object to the mobile after process a payment
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService# process
	 *      (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 * 
	 */
	//@Path("/process")
	//@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	private Response process(TransactionDTO tranDTO, boolean isSplit) {

		String METHOD_NAME = "process";

		long startTime = System.currentTimeMillis();

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(tranDTO)
					&& TransactionHelper
							.checkMandatoryProcessPaymentFields(tranDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(tranDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

					status = merchantTransactionService.process(merchantDTO, tranDTO);
					
					
				
					//	for internal
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					boolean isEcommTrans = this.checkPosIdContainNumber(tranDTO
							.getPosId());

					if (isEcommTrans) {

						status = ecommerceProcess(tranDTO);

					} else {

						// qr code running code
						//TransactionService transactionService = CommonGetTransactionService();

						CloudSvrQrCodeTxn amountInfo = TransactionHelper.convertFromDTOtoCOFDTONormalTxn(tranDTO);

						CloudSvrPosCon posConDetail = txnService.getMidFromDbService(amountInfo);

						CloudSvrQrCodeTxn dbDetails = mapQrCode.get(posConDetail.gettId());
						
						/// new method for token 
						
						setTokenForTnx(tranDTO,dbDetails);
						
						

						if ((dbDetails != null
								&& dbDetails.getIsProcessReq()
										.equalsIgnoreCase("N") /*&& dbDetails
								.getMerchantAccessKey().equalsIgnoreCase(
										tranDTO.getMerchantAccessKey())*/)

						) {

							// here we are trying to intimate to system that isSplit  or not 
							dbDetails.setSplit(isSplit);
							dbDetails.setSplitAmount(TransactionHelper.removeDotFromAmount(tranDTO.getAmount()));
							dbDetails.setIsProcessReq(WebServiceConstants.IS_PROCESS_REQ_YES);
							
							mapQrCode.put(posConDetail.gettId(), dbDetails);

							// we have to run one thread here to wait till
							// unsentpos
							// send request to us.

							// thread sleep time read from property file
							int sleepTime = Integer
									.parseInt(PropertiesUtil
											.getProjectProperties()
											.getProperty(
													WebServiceConstants.THREAD_SLEEP_TIME));

							dbDetails = makingThreadToWait(tranDTO,sleepTime,posConDetail.gettId());

							// if not null send response to mobile
							if (dbDetails != null
									&& dbDetails.getProcessPayment()
											.equalsIgnoreCase(
													WebServiceConstants.YES)) {

								status = WebServiceUtil
										.porccessPaymentDone(
												WebServiceConstants.TRANSACTION_PROCESS,
												WebServiceConstants.NUMBER_ONE,
												dbDetails);
								
								
								
								// this is the newly added code in case split is true
								dbDetails.setIsProcessReq(WebServiceConstants.NOT_PROCESS_REQ);
								dbDetails.setProcessPayment(WebServiceConstants.NOT_PROCESS_REQ);
								mapQrCode.put(posConDetail.gettId(), dbDetails);
								
								
								if(!dbDetails.isSplit()) {
									
									
									if(tranDTO.getCouponId()!=null && tranDTO.getCouponId().length()!=0) {
									
										couponService.setCouponRedemptionData(dbDetails.getCouponId(), dbDetails.getUserId());
									}
									if(tranDTO.getRewardPoint()!=null && tranDTO.getRewardPoint().length()!=0) {
										
										couponService.updateRewardPoint(dbDetails.getRewardPoint(), dbDetails.getUserId(), tranDTO.getTotalAmount());
									}
									mapQrCode.remove(posConDetail.gettId());

									
								}
								

							}

						} else {

							status = CloudServiceStatus
									.getTransactionStatus(CloudServiceStatus
											.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

						}
					}
				} else {

					status = CloudServiceStatus
							.getTransactionStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.getTransactionStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException occured while process payment";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

			// return getProcessServiceException(cloudServiceException);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.TRANSACTION_PROCESS_NOT));

			String message = "Exception occured while process payment";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getProcessException(ex);

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
	 * Request from mobile to get history report of the transaction Returns
	 * response object that will send to the mobile containing all the details
	 * about user history report send both success and failure message to the
	 * mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters to get the history
	 *            reports
	 * @return response object to containing the history reports & other details
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService# getHistory
	 *      (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/history")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHistory(TransactionDTO transDTO) {

		String METHOD_NAME = "getHistory";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(transDTO)
					|| TransactionHelper.checkMandatoryFields(transDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(transDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {
					

					status = merchantTransactionService.getUserHistory(merchantDTO, transDTO);
					
					
				// for internal	
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					CloudSvrUser user = this.getUser(transDTO);

					if (user != null) {

						List<CloudSvrTxn> reportList = this
								.getUserHistoryReport(transDTO, user);

						status = this.getUserHistoryReport(reportList);

					} else {

						status = CloudServiceStatus
								.getTransactionStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.GET_USER));

					}

				} else {

					status = CloudServiceStatus
							.getTransactionStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.getTransactionStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException occured while user report history";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

			// return getHistoryServiceException(cloudServiceException);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_REPORT_HISTORY));

			String message = "Exception occured while user report history";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getHistoryException(ex);

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
	 * Request from mobile to get acc Based History of the transaction Returns
	 * response object that will send to the mobile containing all the details
	 * about account Based History reports send both success and failure message
	 * to the mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters to get account Based
	 *            History reports
	 * @return response object to containing the account Based History reports &
	 *         other details
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService#
	 *      getAccountBasedTransactionSummary
	 *      (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/accBasedHistory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountBasedTransactionSummary(TransactionDTO transDTO) {

		String METHOD_NAME = "getAccountBasedTransactionSummary";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		try {

			if (WebServiceUtil.baseCheckMandatoryFields(transDTO)
					|| TransactionHelper.checkMandatoryFields(transDTO)) {

				// this statement check that merchant access key is there in
				// database or not
				// if exist merchant access key then it will return true
				// if not exits then return false
				//CommonService commonService = GetCommonService();
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(transDTO.getMerchantAccessKey());
				
				

				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

					
					status = merchantTransactionService.getAccountBasedTransactionSummary(merchantDTO, transDTO);
					
					
					
					// for internal 
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {

					CloudSvrUser user = this.getUser(transDTO);

					if (user != null) {

						// based upon account id we are getting account details
						CardOnFileRespDTO accountno = this.getAccount(transDTO,
								user.getUserId());

						List<CloudSvrTxn> reportList = this
								.getAccountBasedUserHistoryReport(transDTO,
										user, accountno);

						status = this.getUserHistoryReport(reportList);

					} else {

						status = CloudServiceStatus
								.getTransactionStatus(CloudServiceStatus
										.getStatusByErrorCode(ErrorCodeConstants.GET_USER));

					}

				} else {

					status = CloudServiceStatus
							.getTransactionStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_MERCHANT_ACCESS_KEY));

				}

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.getTransactionStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException  occured while getting user report history account based";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

			// return getAccBaseHistoryServiceException(cloudServiceException);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY));

			String message = "Exception occured while user report history account based";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getAccBaseHistoryException(ex);

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
	 * Request from unicenta pos to store Amount for the transaction Returns
	 * response object that will send to the mobile containing all the required
	 * fields send both success and failure message to the mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters to store the amount
	 * @return response object containing the required field for unicenta pos
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService#
	 *      storeRequestOfAmountSave (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/storeRequestOfAmountSave")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response storeRequestOfAmountSave(TransactionDTO txnDTO) {

		String METHOD_NAME = "storeRequestOfAmountSave";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		try {

			if (TransactionHelper
					.checkMandatoryFieldForDetailsFromUnicenta(txnDTO)) {
				
			
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(txnDTO.getMerchantAccessKey());
				
				
				if (merchantDTO!=null && merchantDTO.getIsExternal()) {

				
				status = merchantTransactionService.storeRequestOfAmountSave(merchantDTO, txnDTO);
				
				
				
				// for internal 
				} else if (merchantDTO!=null && (!merchantDTO.getIsExternal())) {
				
					boolean isTrue = storeRequestOfAmountSaveCommon(txnDTO);

					status = TransactionHelper.storeRequestOfAmountSaveStatus(isTrue);
				
				}
				

				

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.STORE_REQUESTOF_AMOUNTSAVE));

			String message = "Exception occured while Store Request OfAmountSave";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getStoreRequestOfAmountSaveException(ex);

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
	 * pingRequest from unicent pos,keep on pinging to the server till the
	 * require response recived Returns response object that will send to the
	 * mobile containing all the required fields send both success and failure
	 * message to the mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters required for the method
	 * @return response object containing the required field for unicenta pos
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService#
	 *      pingRequestFromStore (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 */
	@Path("/pingRequestFromStore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pingRequestFromStore(TransactionDTO txnDTO) {

		String METHOD_NAME = "pingRequestFromStore";

		//CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
			//	METHOD_NAME);
		TransactionInitiateRespDTO status = null;

		Response response = null;

		try {

			if (TransactionHelper
					.checkMandatoryFieldForPingRequestFromUnicenta(txnDTO)) {
				
			
				CloudSvrQrCodeTxn amountInfo = TransactionHelper.convertFromDTOtoBOAmountForNormalTxn(txnDTO);
				
				CloudSvrQrCodeTxn internalDetail =  mapQrCode.get(amountInfo.getTid());
				
				
				
				
				
				
				// internal merchant ping
				if (internalDetail!=null){
					
					status = pingRequestFromStoreCommonCheck(internalDetail, amountInfo, txnDTO);
					
				} else {
					
					
					// kolhs 
					CloudSvrQrCodeTxn kolhsDetail1 = kohlTransactionService.getMapQrCode().get(amountInfo.getTid());
					
					if (kolhsDetail1 !=null){
						
						status = pingRequestFromStoreCommonCheck(kolhsDetail1, amountInfo, txnDTO);
					}
					
				}
				
				
				
				
				
				
				
			} else {

				/*
				 * status = this
				 * .entityNull(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY);
				 */

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.PING_REQUEST_FROMSTORE));

			String message = "Exception occured while Ping Request From Store";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		//	return getPingRequestFromStoreException(ex);

		} finally {

			//CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
			//		METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();
		}

		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * timeOutReqFromUnicenta from unicent pos when time out will happen for
	 * unicenta Returns response object that will send to the mobile containing
	 * all the required fields send both success and failure message to the
	 * mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters required for the method
	 * @return response object containing the required field for unicenta pos
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService#
	 *      getTimeOutReqFromUnicenta (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 */
	@Path("/timeOutReqFromUnicenta")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTimeOutReqFromUnicenta(TransactionDTO txnDTO) {

		String METHOD_NAME = "getTimeOutReqFromUnicenta";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		try {

			if (TransactionHelper
					.checkMandatoryFieldForDetailsFromUnicenta(txnDTO)) {

				CloudSvrQrCodeTxn dbDetail = this.timeOutServiceStop(txnDTO);

				status = this.timeOutServiceStop(dbDetail);

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_TIMEOUTREQ_FROM_UNICENTA));

			String message = "Exception occured while getting timeout request from unicenta";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getTimeOutReqFromUnicentaException(ex);

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
	 * Request From Switch server to get the proper response for a transaction
	 * Returns response object that will send to the mobile containing all the
	 * required fields send both success and failure message to the mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters required for the method
	 * @return response object containing the required field for Switch server
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService#
	 *      switchUpdate (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 */

	@POST
	@Path("/requestFromSwitch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response switchUpdate(TransactionDTO transDTO) {

		String METHOD_NAME = "switchUpdate";

		long startTime = System.currentTimeMillis();

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		CloudSvrQrCodeTxn dbDetails = null;

		try {

			if (TransactionHelper.checkMandatorySwitchReqFields(transDTO)) {

				dbDetails = mapQrCode.get(transDTO.getTerminalId());

				if (dbDetails != null) {

					dbDetails.setStatusCode(transDTO.getStatusCode());

					dbDetails
							.setProcessPayment(WebServiceConstants.IS_PROCESS_REQ_YES);
					dbDetails.setTxnStartDate(transDTO.getTxnStartDate());
					// isUpdate = transactionService.updateRrnService(dbDetail);
					// new code

					dbDetails = mapQrCode.put(transDTO.getTerminalId(),
							dbDetails);

				} else {
					
					
					CloudSvrQrCodeTxn kolhsDetail1 = kohlTransactionService.getMapQrCode().get(transDTO.getTerminalId());
					
					if (kolhsDetail1!=null){
						
						
						kolhsDetail1.setProcessPayment(WebServiceConstants.IS_PROCESS_REQ_YES);
						kolhsDetail1.setTxnStartDate(transDTO.getTxnStartDate());
						//kohlTransactionService.getMapQrCode().put(transDTO.getTerminalId(), kolhsDetail1);
						//kolhsDetail1 = mapQrCode.put(transDTO.getTerminalId(),kolhsDetail1);
						
					}
					
					
					
					
				}

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_REQUEST_FROM_SWITCH));

			String message = "Exception occured while process payment response from switch server ";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getRequestFromSwitchException(ex);

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
	 * e-commerce request for save amount Returns response object that will send
	 * to the mobile containing all the required fields send both success and
	 * failure message to the mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters required for the method
	 * @return response object containing the required field e-commerce
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService#
	 *      amountRequestFromEcommerce
	 *      (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 */

	@Path("/amountRequestOfEcomm")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response amountRequestFromEcommerce(TransactionDTO transDTO) {

		// TODO Auto-generated method stub
		String METHOD_NAME = "amountRequestFromEcommerce";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		CloudSvrQrCodeTxn dbDetails = null;

		try {

			if (EcommerceHelper.checkMandatoryFieldsToSaveAmountInfo(transDTO)) {

				boolean isSave = ecommerceAmountSaveMethod(transDTO);

				status = this.ecommerceAmountSaveMethod(isSave);

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.AMOUNT_REQUEST_OF_ECOMM));

			String message = "Exception occured while get AmountRequestOfEcomm site ";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// return getAmountRequestOfEcommException(ex);

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
	 * pingRequestFromEcomm use for response to merchant Returns response object
	 * that will send to the mobile containing all the required fields send both
	 * success and failure message to the mobile
	 * 
	 * @param TransactionDTO
	 *            object containing all the parameters required for the method
	 * @return response object containing the required field e-commerce
	 *         merchants
	 * @see org.omnypay.mobileapp.webservices.TransactionWebService#
	 *      pingRequestFromEcommerce (org.omnypay.mobileapp.dto.TransactionDTO)
	 * 
	 * 
	 */

	@Path("/pingRequestFromEcomm")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pingRequestFromEcommerce(TransactionDTO transDTO) {

		// TODO Auto-generated method stub
		String METHOD_NAME = "pingRequestFromEcommerce";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		Response response = null;

		CloudSvrQrCodeTxn dbDetails = null;

		try {

			if (EcommerceHelper.checkMandatoryFieldsToGetInfo(transDTO)) {

				String isResp = ecommerceWaiting(transDTO);

				status = this.ecommerceWaiting(isResp);

			} else {

				status = CloudServiceStatus
						.getTransactionStatus(CloudServiceStatus
								.getStatusByErrorCode(ErrorCodeConstants.ENTITY_NULL_OR_EMPTY));

			}

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getTransactionStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.PING_REQUEST_FROMSTORE));

			String message = "Exception occured while get ping request for e-commerce  site ";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

			// /return getPingRequestFromStoreException(ex);

		} finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();
		}

		return response;

	}
	

		
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	

	
	
	
	
	
	
	
	

	

	/*
	 * 
	 * e-commerce merchant id validation
	 */
	private String getMerchantIdForEcomm(String merchantId) {

		String as = merchantId;

		return as;

	}

	
	
	
	
	
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Private method Starts here/////////////////////////////////////////////////////////////////////////////////////////////
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Private method Starts here/////////////////////////////////////////////////////////////////////////////////////////////
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		
		
	///////////////////////////service service service service//////////////////////////////////////////////////////////////////////
	///////////////////////////service service service service////////////////////////////////////////////////////////////////
	///////////////////////////service service service service////////////////////////////////////////////////////////////////
	///////////////////////////service service service service////////////////////////////////////////////////////////////////
	///////////////////////////service service service service////////////////////////////////////////////////////////////////
	///////////////////////////service service service service////////////////////////////////////////////////////////////////
	///////////////////////////                 start         ////////////////////////////////////////////////////////////////////// 	
		
		
	
	
	
	
	
	
	
	
	// to get Common service
		/*	private TransactionService CommonGetTransactionService() {

				String METHOD_NAME = "CommonGetTransactionService";

				log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

				// business layer integration
				TransactionService transService = (TransactionService) super
						.getApplicationContext().getBean(WebServiceConstants.TXN_SERVICE);

				log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

				return transService;
			}
*/
			


		/*	// to get Pos service
			private PosService CommonGetPosService() {

				// business layer integration

				String METHOD_NAME = "CommonGetPosService";

				log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

				PosService posService = (PosService) super.getApplicationContext()
						.getBean(WebServiceConstants.POS_SERVICE);

				log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

				return posService;
			}*/

			


		
		
		
	
/*	// to get common service
	private CommonService GetCommonService() {

		CommonService commonService = (CommonService) super
				.getApplicationContext().getBean(WebServiceConstants.COMMON_SERVICE);
		
		return commonService;
	}
	*/
	
	
	
	
/*	// to get user service
		private UserService GetUserService() {

			UserService userService = (UserService) super.getApplicationContext()
					.getBean("userService");
			
			return userService;
		}
	
	*/
	
	
		
/*		// to get account service
		private AccountService getAccountService() {

			
			// business layer integration
			AccountService accountService = (AccountService) super
					.getApplicationContext().getBean("accountService");

			return accountService;
		}
	*/
		
		
		
		
	/*	// to get hce service
		private HceTokenService getHceService() {

			HceTokenService hceService = (HceTokenService) super
					.getApplicationContext().getBean("hceService");

			return hceService;
		}*/
		
		
	/*	// get transaction history 
		private TransactionService getTransactionService(){
			
			// transactionService
			TransactionService txnService = (TransactionService) super
			.getApplicationContext().getBean("txnService");
			
			return transService;
			
			
		}
		*/
		
		
	
///////////////////////////                 end         ////////////////////////////////////////////////////////////////////// 		
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////
///////////////////////////service service service service///////////////////////////////////////////////////////////////////

///////////////////////////service service service service///////////////////////////////////////////////////////////////////


	
	
	
	
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
///////////////////////////                 start         //////////////////////////////////////////////////////////////////////	
	
	
	
	
	
	
/*	private CloudSvrBusinessEntityInfo accessKeyForMerchantExistService(String accessKey)
			throws CloudServiceException {

		boolean keyExist = false;

		CloudSvrBusinessEntityInfo entityInfo = CommonHelper
				.convertFromDTOtoBOForMerchantAccessKey(accessKey);

		// business layer integration for getting accesskey
		CommonService commonService = GetCommonService();

		entityInfo = commonService.isAccessKeyForMerchantExistService(entityInfo);

		return entityInfo;
		}
	*/

	
	
	
	
	
	
	
	// to get userID
	private CloudSvrUser getUser(TransactionDTO tranDTO)
			throws CloudServiceException {

		
		CloudSvrUser user = UserHelper.convertFromDTOtoBO(tranDTO);

		//UserService userService = GetUserService();
		
		CloudSvrUser userId = userService.getUserService(user);

		return userId;
	}
	
	
	
	
	
	// to get account type
		private CardOnFileRespDTO getAccount(TransactionDTO tranDTO,String userid) throws CloudServiceException {

			

			CardOnFileDTO cofAccount = AccountHelper
					.checkMandatoryFieldsGetAccountType(tranDTO,userid);

			//AccountService accountService = getAccountService();

			CardOnFileRespDTO accounttype = accountService.getAccountService(cofAccount);

			

			return accounttype;

		}
	
	
		
		
		/**
		 * 
		 * this is use for get online token
		 * 
		 * @param tranDTO
		 * @return
		 * @throws IntegrationException 
		 */
		private HceTokenRespDTO getOnlineToken(TransactionDTO tranDTO,
				String accounttype, CloudSvrUser user) throws CloudServiceException, IntegrationException {

			

			HceTokenRespDTO token = null;

			HceTokenDTO hceTokenDTO = TransactionHelper
					.convertFromDTOtoBOOnlineToken(tranDTO, user.getUserId());

			//HceTokenService accountService = this.getHceService();

			token = hceService.getOnlineTokenService(hceTokenDTO, accounttype);
			
			
			return token;

			/*tokenLists = token.getTokens();

			// to get the token number
			if (tokenLists != null) {

				String tokenId = getTokenId(tokenLists);
				// new code
				// tmpData.setToken(tokenId);
				ecommTxn.setTokenId(tokenId);

				// if card type we will get token exp date

				if (!accounttype.equalsIgnoreCase(BusinessConstants.BANK_TYPE)) {
					// newcode
					// tmpData.setTokenExpiryDate(token.getTokenexpDate());
					ecommTxn.setTokenExp(token.getTokenexpDate());
				}

				// new code
				// update
				// updateToken(transService, tmpData);

			}*/

			

		}
		
		
		/**
		 * 
		 * this is use for get online token
		 * 
		 * @param tranDTO
		 * @return
		 * @throws IntegrationException 
		 * @throws CloudServiceException 
		 */
		private void getOnlineToken(TransactionDTO tranDTO, String accounttype,
				CloudSvrUser user, CloudSvrQrCodeTxn dbDetails) throws  CloudServiceException, IntegrationException {

			String METHOD_NAME = "getOnlineToken";

			log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

			HceTokenRespDTO token = null;

			List<Map<String, String>> tokenLists = null;

			// getting user Id
			// CloudSvrUser user = commonGetUserId(tranDTO);

			// Set DTO
			HceTokenDTO hceTokenDTO = TransactionHelper
					.convertFromDTOtoBOOnlineToken(tranDTO, user.getUserId());

		

			// HCE Service
			//HceTokenService accountService = getHceService();

			token = hceService.getOnlineTokenService(hceTokenDTO, accounttype);

			tokenLists = token.getTokens();

			// to get the token number
			if (tokenLists != null) {

				String tokenId = getTokenId(tokenLists);
				// new code
				dbDetails.setTokenId(tokenId);

				// if card type we will get token exp date

				if (!accounttype.equalsIgnoreCase(BusinessConstants.BANK_TYPE)) {
					// newcode
					dbDetails.setTokenExp(token.getTokenexpDate());
				}

				
			}

			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
			// return token;

		}
	
	
		
		
		
		
		
		
		
		
		
		
		private List<CloudSvrTxn> getUserHistoryReport(TransactionDTO transDTO, CloudSvrUser user) throws CloudServiceException{
			
			
			
			
			// transactionService
			//TransactionService transService = this.getTransactionService();
			// Convert DTO to BO
			CloudSvrTxn txn = TransactionHelper.converDTOtoBO(transDTO,	user.getUserId());

			// call business layer
			List<CloudSvrTxn> txnList = txnService.getHistoryService(txn);
			
			return  txnList;

		}
		
		
		
		
		
		
		private List<CloudSvrTxn> getAccountBasedUserHistoryReport(TransactionDTO transDTO, CloudSvrUser user,CardOnFileRespDTO accountno) throws CloudServiceException{
			
			
			
			
			// transactionService
			//TransactionService transService = this.getTransactionService();
			// Convert DTO to BO
			// Convert DTO to BO
			CloudSvrTxn txn = TransactionHelper.ConvertFromDTOtoBO(user.getUserId(), TransactionHelper.AccountNoMasking(accountno.getAccNo()),
					accountno.getAcctype());
			// call business layer
			List<CloudSvrTxn> txnList = txnService.getAcctBasedTransactionSummaryService(txn);
			
			return  txnList;

		}
		
		
		
		
		
	/*	private CloudSvrPosCon getMidFromDb(CloudSvrQrCodeTxn amountInfo) throws CloudServiceException {
			

			CloudSvrPosCon dbDetails = null;

			// getting details from db based on posId
			dbDetails = txnService.getMidFromDbService(amountInfo);

			
			return dbDetails;
		}*/

		
		
		
		
		/*// to get account service
		private CardOnFileRespDTO CommonGetCofService(TransactionDTO transDTO,
				Long userId) {

			

			CardOnFileRespDTO accountId = null;

			// card on file Service
			CardOnFileService accountService = (CardOnFileService) super
					.getApplicationContext().getBean("cardOnFileService");

			CardOnFileDTO cofAccount = AccountHelper
					.convertFromDTOtoBOAccountBasedTx(transDTO, userId);

			// card on file Service
			accountId = accountService.getAccountService(cofAccount);

			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

			return accountId;
		}*/
	
	
///////////////////////////                 end         /////////////////////////////////////////////////////////		
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
//////////////////////////////////////////////business layer integration ////////////////////////////////////////
	
	
	

	
	
	
	
	
	
	
	
	
	
////////////////////////////////////////////////////status object //////////////////////////////////////////////////		
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
///////////////////////////                 start         /////////////////////////////////////////////////////////			
	
		
		

		/**
		 * 
		 * this is use for  e-commerce Process payment
		 * 
		 * @param tranDTO    TransactionDTO object
		 * @return status  TransactionInitiateRespDTO object
		 * @throws CloudServiceException 
		 */
		private TransactionInitiateRespDTO ecommerceProcess(TransactionDTO tranDTO) throws CloudServiceException {

			SwitchTxnRespDTO sTxnRespData = null;

			TransactionInitiateRespDTO status = null;

			PosDTO posdata = null;

			CloudSvrEcommerceTxn info = TransactionHelper.convertFromDTOtoCOFDTOEcommAmount(tranDTO);

			CloudSvrEcommerceTxn ecomm = mapEcommCode.get(info.getIpAddress());

			posdata = getDataForEcomm(tranDTO, /* token, */ecomm);

			sTxnRespData = sendDataToSwitchService(tranDTO, posdata);

			if (sTxnRespData.getStatusCode().equalsIgnoreCase(
					WebServiceConstants.UPDATE_SWITCH_SERVER_SUCC_CODE)) {

				// based upon rrn number get report
				// CloudSvrQrCodeTxn txn = getReportForEcommerce(ecommTxnRespData
				// .getSwitchTxnId());

				ecomm = mapEcommCode.get(info.getIpAddress());

				ecomm.setTxnStartDate(sTxnRespData.getSwitchTxnDate());

				status = WebServiceUtil.porccessPaymentForECommerceDone(
						WebServiceConstants.TRANSACTION_PROCESS,
						WebServiceConstants.NUMBER_ONE, ecomm);

				// sending response back to merchant success message
				// updateEcommerceStatus(WebServiceConstants.NUMBER_ZERO,tranDTO.getPosId());

				ecomm.setStatus(WebServiceConstants.NUMBER_ZERO);

				mapEcommCode.put(info.getIpAddress(), ecomm);

				String isSave = mapEcommCode.get(info.getIpAddress()).getStatus();
				System.out.println("---------------------------------------------------------------------------ip"+info.getIpAddress());
				System.out.println("---------------------------------------------------------------------------"+isSave);

			} else {

				// some method
				status = WebServiceUtil.getProcessPaymentNotDone(
						WebServiceConstants.TRANSACTION_PROCESS_NOT_RRN,
						WebServiceConstants.NUMBER_TWO);

				// updateEcommerceStatus(WebServiceConstants.NUMBER_ONE,tranDTO.getPosId());
				ecomm = mapEcommCode.get(info.getIpAddress());
				ecomm.setStatus(WebServiceConstants.NUMBER_ONE);
				mapEcommCode.put(info.getIpAddress(), ecomm);
				
				

			}

			// that talk to authorization.net
			// String responce = getTalkToAuthNet();
			// check the response either success or failure
			// if success connect to switch server and update the records

			// if response is success then talk to Switch server
			/*
			 * if ("success".equals(responce)) {
			 * 
			 * tranDTO.setRequestType(WebServiceConstants.REQUEST_TYPE_UPDATE);
			 * 
			 * tranDTO.setSwitchTxnId(ecommTxnRespData.getSwitchTxnId());
			 * 
			 * log.info("switchTxnId :: " + ecommTxnRespData.getSwitchTxnId());
			 * 
			 * ecommTxnRespData = getEcommerceConnectorService(tranDTO);
			 * 
			 * if (ecommTxnRespData.getStatusCode().equalsIgnoreCase(
			 * WebServiceConstants.UPDATE_SWITCH_SERVER_SUCC_CODE)) {
			 * 
			 * // based upon rrn number get report //CloudSvrQrCodeTxn txn =
			 * getReportForEcommerce(ecommTxnRespData //.getSwitchTxnId());
			 * 
			 * ecomm = mapEcommCode.get(tranDTO.getIpAddress());
			 * 
			 * status = WebServiceUtil.porccessPaymentForECommerceDone(
			 * WebServiceConstants.TRANSACTION_PROCESS,
			 * WebServiceConstants.NUMBER_ONE, ecomm);
			 * 
			 * // sending response back to merchant success message
			 * //updateEcommerceStatus
			 * (WebServiceConstants.NUMBER_ZERO,tranDTO.getPosId());
			 * 
			 * 
			 * ecomm.setStatus(WebServiceConstants.NUMBER_ZERO);
			 * 
			 * mapEcommCode.put(tranDTO.getIpAddress(),ecomm);
			 * 
			 * 
			 * } else {
			 * 
			 * // some method status = WebServiceUtil .getProcessPaymentNotDone(
			 * WebServiceConstants.TRANSACTION_PROCESS_NOT_RRN,
			 * WebServiceConstants.NUMBER_TWO);
			 * 
			 * //updateEcommerceStatus(WebServiceConstants.NUMBER_ONE,tranDTO.getPosId
			 * ()); ecomm = mapEcommCode.get(tranDTO.getIpAddress());
			 * ecomm.setStatus(WebServiceConstants.NUMBER_ONE);
			 * mapEcommCode.put(tranDTO.getIpAddress(),ecomm);
			 * 
			 * }
			 * 
			 * } else {
			 * 
			 * // some method
			 * 
			 * status = WebServiceUtil.getProcessPaymentNotDone(
			 * WebServiceConstants.TRANSACTION_PROCESS_AUTH_FAIL,
			 * WebServiceConstants.NUMBER_THREE);
			 * 
			 * }
			 */
			
			
			
			

			return status;

		}
	
	
		/**
		 * 
		 * this is to get status for rrn number & response back to unicenta
		 * 
		 * @param isUpdate    boolean value 
		 * @return status  Status object
		 */
		
		private Status rrnRequestFromStroeStatus(boolean isUpdate) {

			Status status = null;

			if (isUpdate) {

				status = WebServiceUtil.getStatus(WebServiceConstants.RRN_SAVED,
						WebServiceConstants.ONE);
				
			} else {

				status = WebServiceUtil.getStatus(
						WebServiceConstants.RRN_NOT_SAVED, WebServiceConstants.TWO);
			}
			return status;
		}

		
	
		/***
		 * get Status for entityNull(if any entity is null)
		 * 
		 * @param errorcode
		 * @return Status object
		 */
	private TransactionInitiateRespDTO entityNull(String errorcode) {

		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();

	status.setCode(WebServiceConstants.CODE);
	status.setMessage(WebServiceConstants.ENTITY_NULL);
	status.setType(WebServiceConstants.SIX);

	return status;
	}	
	
	
	
	
	
	/***
	 * get Status if access key is not found
	 * 
	 * @param errorcode
	 * @return Status object
	 */
	private TransactionInitiateRespDTO accessKeyNull(String errorcode) {

		TransactionInitiateRespDTO	status = new TransactionInitiateRespDTO();

	status.setCode(errorcode);
	status.setMessage(WebServiceConstants.ACCESS_KEY_NOT_EXIST);
	status.setType(WebServiceConstants.FIVE);

	return status;
	}
	
	
	
	
	
	/***
	 * get Status if no user found in database
	 * 
	 * @param errorcode
	 * @return Status object
	 */
	private TransactionInitiateRespDTO userNull(String errorcode) {

		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();

	status.setCode(errorcode);
	status.setMessage(WebServiceConstants.USER_NOT_REGISTER);
	status.setType(WebServiceConstants.FIVE);

	return status;
	}
	
	
	
	
	
	

	/***
	 * get Status if any server side error occour
	 * 
	 * @param errorcode
	 * @return Status object
	 */
	private TransactionInitiateRespDTO serverError(String  errorcode ){
		
		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();
		
		status.setCode(errorcode);
		status.setMessage(WebServiceConstants.CLOUD_SERVER_ERROR);
		status.setType(WebServiceConstants.FIVE);
		
		return status;

	}

	
	
	
	/***
	 *  to get status of transaction history report
	 * 
	 * @param txnList List<CloudSvrTxn> object
	 * @return status  TransactionInitiateRespDTO object
	 * 
	 */
	private TransactionInitiateRespDTO getUserHistoryReport(List<CloudSvrTxn> txnList) {
		
		
		TransactionInitiateRespDTO status = null;
		
		if (txnList.size() > 0) {

			status = WebServiceUtil.getPaymentHistory(
					WebServiceConstants.RECORDS_FOUND,
					WebServiceConstants.NUMBER_ONE, txnList);

		} else {

			status = WebServiceUtil.getPaymentHistory(
					WebServiceConstants.RECORDS_NOT_FOUND,
					WebServiceConstants.NUMBER_TWO, txnList);

		}
		
		return status;

	}
	
	
	
	
	
	
	
	
	
	
	/***
	 * to get status for ping request from unicenta
	 * 
	 * @param tranDTO TransactionDTO object
	 * @return status  TransactionInitiateRespDTO object
	 * 
	 */	
/*private TransactionInitiateRespDTO pingRequestFromStoreCommon(TransactionDTO tranDTO) {

		
		CloudSvrQrCodeTxn dbDetail = null;

		TransactionInitiateRespDTO status = null;

		// DTO to BO conversion
		CloudSvrQrCodeTxn amountInfo = TransactionHelper.convertFromDTOtoBOAmountForNormalTxn(tranDTO);

		//TransactionService transactionService = CommonGetTransactionService();

		dbDetail = mapQrCode.get(amountInfo.getTid());
		

		// dbDetail =
		// getDetailOfInComingRequest(tranDTO,transactionService,amountInfo);

		status = pingRequestFromStoreCommonCheck(dbDetail, amountInfo, tranDTO);

		return status;

	}
	*/
	
	
	
/***
 * to get status for ping request from unicenta
 * 
 * @param dbDetail CloudSvrQrCodeTxn object
 * @param amountInfo CloudSvrQrCodeTxn object
 * @param tranDTO TransactionDTO object
 * @param transactionService TransactionService object
 * @return status  TransactionInitiateRespDTO object
 * 
 */	
	
	private TransactionInitiateRespDTO pingRequestFromStoreCommonCheck(
			CloudSvrQrCodeTxn dbDetail, CloudSvrQrCodeTxn amountInfo,
			TransactionDTO tranDTO) {

		TransactionInitiateRespDTO status = null;

		PosDTO posdata = null;

		// this is when we get request from mobile for amount confirmation
		if (dbDetail != null
				&& dbDetail.getIsProcessReq().equalsIgnoreCase(
						WebServiceConstants.IS_PROCESS_REQ_YES)
		) {

			
			String METHOD_NAME = "pingRequestFromStore";

			CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
					METHOD_NAME);
			
			// getting account type exp card or bank
			posdata = getDataForUnicent(tranDTO,dbDetail);

			// if (posdata != null) {

			status = WebServiceUtil.getProcessDataForUnicenta(
					WebServiceConstants.PAYNOW, WebServiceConstants.ONE,
					posdata, dbDetail);

			// }
		} else {

			status = WebServiceUtil.getProcessDataForUnicenta(
					WebServiceConstants.NO_REQUEST_FROM_MOBILE,
					WebServiceConstants.TWO, null, dbDetail);

		}
		
			

		return status;
	}
	
	
	/***
	 * to get status for timeout from unicenta
	 * 
	 * @param dbDetail CloudSvrQrCodeTxn object
	 * @return status  TransactionInitiateRespDTO object
	 * 
	 */	
	private TransactionInitiateRespDTO timeOutServiceStop(CloudSvrQrCodeTxn dbDetail) {

		
		TransactionInitiateRespDTO status =null;
		
		
		if (dbDetail == null) {
			

			status = (TransactionInitiateRespDTO) WebServiceUtil.getStatus(
					WebServiceConstants.FIELD_UPDATED,
					WebServiceConstants.ONE);

		}
		
		return status;
	}
	

	
	
	/***
	 * to get status for e-commerce Amount Save
	 * 
	 * @param isSave True/false
	 * @return status  TransactionInitiateRespDTO object
	 * 
	 */	
	private TransactionInitiateRespDTO ecommerceAmountSaveMethod(boolean isSave) {

		TransactionInitiateRespDTO status = null;

		if (isSave) {
			status =  WebServiceUtil.getTxnStatus(
					WebServiceConstants.AMOUNT_SAVED, WebServiceConstants.ONE);
		} else {
			status = WebServiceUtil.getTxnStatus(
					WebServiceConstants.AMOUNT_NOT_SAVED,
					WebServiceConstants.TWO);
		}

		return status;

	}
	
	
	
	
	/***
	 * to get status for e-commerce Waiting thread
	 * 
	 * @param isResp String object
	 * @return status  TransactionInitiateRespDTO object
	 * 
	 */	
	private TransactionInitiateRespDTO ecommerceWaiting(String  isResp) {

		TransactionInitiateRespDTO status = null;

		if (isResp.equalsIgnoreCase(WebServiceConstants.NUMBER_ZERO)) {
			status = WebServiceUtil.getTxnStatus(
					WebServiceConstants.TRANSACTION_PROCESS,
					WebServiceConstants.NUMBER_ZERO);
		} else if (isResp.equalsIgnoreCase(WebServiceConstants.NUMBER_ONE)) {
			status = WebServiceUtil.getTxnStatus(
					WebServiceConstants.TRANSACTION_PROCESS_NOT,
					WebServiceConstants.NUMBER_ONE);
		} else if (isResp.equalsIgnoreCase(WebServiceConstants.NUMBER_TWO)) {

			status = WebServiceUtil.getTxnStatus(
					WebServiceConstants.PROCESS_WAIT,
					WebServiceConstants.NUMBER_TWO);

		}

		
		return status;
	}
	
	
	
	
	
	
	
	
	
	
///////////////////////////                 end         ///////////////////////////////////////////////////////////		
////////////////////////////////////////////////////status object //////////////////////////////////////////////////		
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
////////////////////////////////////////////////////status object //////////////////////////////////////////////////	
	
	
	

	
	
	
	
	
	
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
///////////////////////////                 start         ///////////////////////////////////////////////////////////	

	
	private String getTalkToAuthNet() {
		return "success";
	}

	

	/***
	 * to get status for E-commerce Amount Confirmation request
	 * 
	 * @param tranDTO TransactionDTO object
	 * @return status  TransactionInitiateRespDTO object
	 * @throws  CloudServiceException, IntegrationException
	 * 
	 */	
	private TransactionInitiateRespDTO isEcommerceAmountConfirmationTxn(
			TransactionDTO tranDTO) throws CloudServiceException, IntegrationException {

		TransactionInitiateRespDTO status = null;

		String amount = null;

		// get required amount for ecommerce
		amount = isAmountForEcommerce(tranDTO);

		if (amount != null) {

			status = WebServiceUtil.getInitiatePayment(
					WebServiceConstants.TRANSACTION_INITIATE_AMOUNT,
					WebServiceConstants.NUMBER_ONE, amount, null,
					WebServiceConstants.IS_ECOMMERCE_TXN);
		} else {

			status = WebServiceUtil.getInitiatePayment(
					WebServiceConstants.TRANSACTION_INITIATE_AMOUNT_NOT,
					WebServiceConstants.NUMBER_TWO, null, null,
					WebServiceConstants.IS_ECOMMERCE_TXN);

		}

		return status;

	}
	
	
	/***
	 * to get status for E-commerce initiate transaction
	 * 
	 * @param tranDTO TransactionDTO object
	 * @return amount  String object
	 * @throws  CloudServiceException, IntegrationException
	 * @throws IntegrationException 
	 * 
	 */	
	private String isAmountForEcommerce(TransactionDTO tranDTO) throws CloudServiceException, IntegrationException {

		String amount = null;

		String merchantid = null;

		//CardOnFileRespDTO accounttype = null;

		// conversion from DTO to BO
		CloudSvrEcommerceTxn info = TransactionHelper
				.convertFromDTOtoCOFDTOEcommAmount(tranDTO);

		CloudSvrEcommerceTxn ecommTxn = mapEcommCode.get(info.getIpAddress());

		// getting Details based upon pos id coming from request
		merchantid = getMerchantIdForEcomm(mapEcommCode
				.get(info.getIpAddress()).getMerchantAccessKey());

		// null check for given merchnat id
		if (merchantid != null && merchantid.length() != 0) {

			CloudSvrUser user = this.getUser(tranDTO);

			CardOnFileRespDTO accounttype = this.getAccount(tranDTO,null);

			HceTokenRespDTO  token = this.getOnlineToken(tranDTO, accounttype.getAcctype(), user);
			
			
			
			List<Map<String, String>> tokenLists = token.getTokens();

			// to get the token number
			if (tokenLists != null) {

				ecommTxn.setTokenId(this.getTokenId(tokenLists));

				if (!accounttype.getAcctype().equalsIgnoreCase(
						BusinessConstants.BANK_TYPE)) {

					ecommTxn.setTokenExp(token.getTokenexpDate());
				}

			}
			
			

			ecommTxn.setUserId(user.getUserId());
			ecommTxn.setAccId(Long.valueOf(tranDTO.getAccId()));
			ecommTxn.setAccType(accounttype.getAcctype());
			// ecommTxn.setMerchantName(posConDetail.getMerchantName());
			// ecommTxn.setMerchantAddress(posConDetail.getMerchantAddress());
			// map.put(amountInfo.getPosId(),dbDetails);
			mapEcommCode.put(info.getIpAddress(), ecommTxn);

			amount = mapEcommCode.get(info.getIpAddress()).getAmount();

		}

		

		if (amount != null) {
			// for making amount 12 digit and putting point after 2 digit from
			// right
			amount = TransactionHelper.getFormattedAmount(amount);

		}

		return amount;

	}
	
	
	
	
	
	
	
	/***
	 * to get status for QrCOde initiate transaction
	 * 
	 * @param tranDTO TransactionDTO object
	 * @return status  TransactionInitiateRespDTO object
	 * @throws  CloudServiceException, IntegrationException
	 * 
	 */	
	private TransactionInitiateRespDTO isQrCOdeAmountConfirmationTxn(
			TransactionDTO tranDTO) throws IntegrationException, CloudServiceException {

		TransactionInitiateRespDTO status = null;

		CloudSvrQrCodeTxn dbDetails = null;

		String formattedAmount = null;

		// HceTokenRespDTO token = null;

		Double defaultAmount = 0.0;

		CloudSvrPosCon posConDetail = null;

		CardOnFileRespDTO accounttype = null;

		// get details from database based on posId

		// conversion from DTO to BO
		CloudSvrQrCodeTxn amountInfo = TransactionHelper
				.convertFromDTOtoCOFDTONormalTxn(tranDTO);

		// new code

		// business layer integration
		//TransactionService transactionService = CommonGetTransactionService();
		/*
		 * // getting details from db based on posId dbDetails =
		 * transactionService.getAmountForNormalTxnService(amountInfo);
		 * 
		 * // dbDetails = getDetailsForNormalTxn(tranDTO);
		 * 
		 * // if record having default value then so the message to mobile if
		 * (dbDetails != null && Double.compare(dbDetails.getAmount(),
		 * defaultAmount) == 0 && (dbDetails.getIsAmountReq()
		 * .equalsIgnoreCase(WebServiceConstants.IS_AMOUNT_REQ_YES)) &&
		 * (dbDetails.getIsProcessReq()
		 * .equalsIgnoreCase(WebServiceConstants.IS_PROCESS_REQ_YES))
		 * 
		 * && (dbDetails.getProcessPayment()
		 * .equalsIgnoreCase(WebServiceConstants.PROCESS_PAYMENT_NOT_DONE))
		 * 
		 * ) {
		 * 
		 * status = WebServiceUtil.getInitiatePayment(
		 * WebServiceConstants.TRANSACTION_INITIATE_AMOUNT_NOT,
		 * WebServiceConstants.NUMBER_TWO, null, null,
		 * WebServiceConstants.IS_NOT_ECOMMERCE_TXN);
		 * 
		 * } else {
		 */

		// getting Details based upon pos id coming from request
				
		posConDetail = txnService.getMidFromDbService(amountInfo);

		// new code //danger point// discussed with sir
		dbDetails = mapQrCode.get(posConDetail.gettId());
		

		if ((posConDetail != null && dbDetails != null )
				&& (posConDetail.getPosId().equalsIgnoreCase(dbDetails.getPosId()) && posConDetail.getQrCode().equalsIgnoreCase(dbDetails.getQrCode())
				&& posConDetail.getMerchantAccKey().equalsIgnoreCase(dbDetails.getMerchantAccessKey()))		
						
				) {

		//1	CloudSvrUser user = getUser(tranDTO);

			// getting account type exp card or bank
			//2 accounttype = getAccount(tranDTO,null);

			// get token service required
			// new code
			// token = getOnlineToken(tranDTO, accounttype.getAcctype(), user);

			//3 getOnlineToken(tranDTO, accounttype.getAcctype(), user, dbDetails);

			// new code
			// local param
			// updateLocalParam(user,dbDetails,transactionService,tranDTO.getAccId(),accounttype.getAcctype());

			//3dbDetails.setUserId(user.getUserId());
			//4dbDetails.setAccId(Long.valueOf(tranDTO.getAccId()));
			//5dbDetails.setAccType(accounttype.getAcctype());
			dbDetails.setMerchantName(posConDetail.getMerchantName());
			dbDetails.setMerchantAddress(posConDetail.getMerchantAddress());
			dbDetails.setMid(posConDetail.getMerchantId());
			mapQrCode.put(posConDetail.gettId(), dbDetails);
			
			

			// );
			// amount display for mete
			formattedAmount = TransactionHelper.getFormattedAmount(String.valueOf(dbDetails.getAmount()));

			status = WebServiceUtil.getInitiatePayment(
					WebServiceConstants.TRANSACTION_INITIATE_AMOUNT,
					WebServiceConstants.NUMBER_ONE, formattedAmount, null,
					// token.getTokens(),
					WebServiceConstants.IS_NOT_ECOMMERCE_TXN);

		} else {
			status = WebServiceUtil.getInitiatePayment(
					WebServiceConstants.TRANSACTION_INITIATE_AMOUNT_NOT,
					WebServiceConstants.NUMBER_TWO, null, null,
					WebServiceConstants.IS_NOT_ECOMMERCE_TXN);

		}
		// }

		return status;

	}
	
	
	
	
	
	
	
	
	
	/***
	 * to get online token for transaction
	 * 
	 * @param tokenLists  List<Map<String, String>> object
	 * @return tokenId String object
	 * @throws  CloudServiceException, IntegrationException
	 * 
	 */	
	
	private String getTokenId(List<Map<String, String>> tokenLists) {

		String tokenId = null;

		if (tokenLists != null) {
			for (Map<String, String> toknList : tokenLists) {
				for (Map.Entry<String, String> tokenVal : toknList.entrySet()) {
					tokenId = tokenVal.getValue();
				}
			}

		}
		return tokenId;
	}
	
	
	
	
	
	
	
	
	
	
	/***
	 * to check either posId containing only number or not
	 * 
	 * @param tokenLists  List<Map<String, String>> object
	 * @return tokenId String object
	 * @throws  CloudServiceException, IntegrationException
	 * 
	 */	
	
	public  boolean checkPosIdContainNumber(String posId) {
		for (int i = 0; i < posId.length(); i++) {
			if (!Character.isDigit(posId.charAt(i))) {

				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	/***
	 * store Request Of Amount Save from unicenta pos
	 * 
	 * @param tranDTO  TransactionDTO object
	 * @return isTrue True/false
	 *  
	 */	
	
	private boolean storeRequestOfAmountSaveCommon(TransactionDTO tranDTO) {

		CloudSvrQrCodeTxn amount = null;

		boolean isTrue = false;

		// DTO to BO conversion
		CloudSvrQrCodeTxn amountInfo = TransactionHelper
				.convertFromDTOtoBOAmountForNormalTxn(tranDTO);

		// new code putting in to map

		if (amountInfo != null) {
			// pos will be id unique for one terminal
			mapQrCode.put(amountInfo.getTid(), amountInfo);
			
			System.out.print("size "+mapQrCode.size());
			
			

			isTrue = true;

		}

		return isTrue;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * to stop timeOut Request from unicenta pos
	 * 
	 * @param tranDTO  TransactionDTO object
	 * @return dbDetail CloudSvrQrCodeTxn object
	 *  
	 */	
		private CloudSvrQrCodeTxn timeOutServiceStop(TransactionDTO tranDTO) {

			
			CloudSvrQrCodeTxn dbDetail = mapQrCode.remove(tranDTO.getTid());

			 return dbDetail;
		}
	
	
	
	
	
		/***
		 * e-commerce Amount Save
		 * 
		 * @param transDTO  TransactionDTO object
		 * @return isSave True/false
		 *  
		 */	
	private boolean ecommerceAmountSaveMethod(TransactionDTO transDTO) {

		boolean isSave = false;

		CloudSvrEcommerceTxn info = EcommerceHelper
				.convertFromDTOtoBOAmountForEcomm(transDTO);

		// amount save time status will be processing
		info.setStatus(WebServiceConstants.NUMBER_TWO);

		mapEcommCode.put(info.getIpAddress(), info);

		isSave = true;

		return isSave;
	}
	
	
	/***
	 * e-commerce Waiting thread
	 * 
	 * @param transDTO
	 *            TransactionDTO object
	 * @return isSave String object
	 * 
	 */
	private String ecommerceWaiting(TransactionDTO transDTO) {

		String isSave = null;

		isSave = mapEcommCode.get(transDTO.getIpAddress()).getStatus();

		if (isSave.equalsIgnoreCase((WebServiceConstants.NUMBER_ZERO))) {
			mapEcommCode.remove(transDTO.getIpAddress());
			// System.out.println("-------------------------------++++++++++++++++++++++++++++++==transDTO"+transDTO.getIpAddress());
		}

		return isSave;
	}
	
	
	
	/**
	 * 
	 * this is use for get pos amount data
	 * 
	 * @param tranDTO TransactionDTO object
	 * @return posData PosRespDTO object
	 *//*
	private PosRespDTO getPosAmountConnector(TransactionDTO tranDTO) {

		String METHOD_NAME = "getPosAmountConnector";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		PosRespDTO posData = null;

		// Set DTO for amount
		PosDTO posDTO = TransactionHelper.convertFromDTOtoBOPosAmount(tranDTO);

		// POS Service
		PosService posService = CommonGetPosService();

		posData = posService.posAmountConfirmConnectorService(posDTO);

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return posData;

	}
*/
	

	

	
	



	



	

	/**
	 * 
	 * this is use for get connect e-Ecommerce 
	 * 
	 * @param tranDTO TransactionDTO object
	 * @param posdata  PosDTO object
	 * @return ecommTxnRespData EcommTxnRespDTO object
	 * @throws CloudServiceException 
	 */
	private SwitchTxnRespDTO sendDataToSwitchService(
			TransactionDTO tranDTO, PosDTO posdata) throws CloudServiceException {

		
		SwitchTxnRespDTO switchTxnRespData = null;
		
		// POS Service
		//PosService posService = CommonGetPosService();

		switchTxnRespData = posService.sendToSwitch(posdata);

		return switchTxnRespData;
	}

	
	
	

	


	
	
	
	/**
	 * 
	 * this is use waiting the thread for a transaction process
	 * 
	 * @param tranDTO    TransactionDTO object
	 * @param i          integer required for generating specific time for waiting a thread
	 * @param txnService TransactionService object
	 * @param amountInfo CloudSvrQrCodeTxn object
	 * 
	 * @return dbDetail  CloudSvrQrCodeTxn object
	 */
	private CloudSvrQrCodeTxn makingThreadToWait(TransactionDTO tranDTO, int i,String tid) {

		String METHOD_NAME = "makingThreadToWait";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrQrCodeTxn dbDetail = null;

		boolean con = true;

		while (con) {

			try {

				Thread.sleep(i * i);// 1000 mil==1 second

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			dbDetail = mapQrCode.get(tid);

			if (dbDetail.getProcessPayment().equalsIgnoreCase(
					WebServiceConstants.PROCESS_PAYMENT_NOT_DONE)) {
				if (i == 0) {

					break;
				}
				i = i--;
			} else {
				con = false;
				break;
			}
		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return dbDetail;
	}

	
	/**
	 * 
	 * this is to get data for unicenta and sent as response back
	 * 
	 * @param tranDTO    TransactionDTO object
	 * @param dbDetail CloudSvrQrCodeTxn object
	 * 
	 * @return posData  PosDTO object
	 */
	private PosDTO getDataForUnicent(TransactionDTO tranDTO,
	CloudSvrQrCodeTxn dbDetail) {

		String METHOD_NAME = "getDataForUnicent";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		PosDTO posData = null;

		// getting accId which inserted during initiate and settig to
		// txnDTO & object

		tranDTO.setAccId(String.valueOf(dbDetail.getAccId()));

		posData = TransactionHelper.convertFromDTOtoBOPosData(tranDTO, 	dbDetail.getAccType(), dbDetail.getTokenId(),
				dbDetail.getTokenExp());

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return posData;
	}

	
	
	/**
	 * 
	 * this is to get data for e-commerce and sent as response back
	 * 
	 * @param tranDTO    TransactionDTO object
	 * @param ecomm      CloudSvrEcommerceTxn object
	 * 
	 * @return posData  PosDTO object
	 */
	private PosDTO getDataForEcomm(TransactionDTO tranDTO,
	CloudSvrEcommerceTxn ecomm) {

		String METHOD_NAME = "getDataForEcomm";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		PosDTO posData = null;

		// getting accId which inserted during initiate and settig to
		// txnDTO & object

		tranDTO.setAccId(String.valueOf(ecomm.getAccId()));

		posData = TransactionHelper.convertFromDTOtoBOPosData(tranDTO, /*
																		 * token,
																		 */
				ecomm.getAccType(), ecomm.getTokenId(), ecomm.getTokenExp());

		// merchant id setting

		posData.setMid(ecomm.getMerchantName());

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return posData;
	}

	
	/**
	 * Testing only not for develpoment
	 * this is to get data for e-commerce and sent as response back
	 * 
	 * @param tranDTO    TransactionDTO object
	 * @param ecomm      CloudSvrEcommerceTxn object
	 * 
	 * @return posData  PosDTO object
	 */
	private PosDTO getDataForEcomm(TransactionDTO tranDTO,
			CloudSvrQrCodeTxn ecomm) {

		String METHOD_NAME = "getDataForEcomm";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		PosDTO posData = null;

		// getting accId which inserted during initiate and settig to
		// txnDTO & object

		tranDTO.setAccId(String.valueOf(ecomm.getAccId()));

		posData = TransactionHelper.convertFromDTOtoBOPosData(tranDTO, /*
																		 * token,
																		 */
				ecomm.getAccType(), ecomm.getTokenId(), ecomm.getTokenExp());

		// merchant id setting

		posData.setMid(ecomm.getMerchantName());

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return posData;
	}



	

	
	

	

	


	/**
	 * 
	 * this is to get status if rrn number update or not and sent  response back to unicenta
	 * 
	 * @param tranDTO    TransactionDTO object
	 * @param dbDetail   CloudSvrQrCodeTxn object
	 * 
	 * @return isUpdate  True/false
	 */
	private boolean rrnRequestFromStroeCommon(TransactionDTO txnDTO) {

		CloudSvrQrCodeTxn dbDetail = null;

		boolean isUpdate = false;

		//TransactionService transactionService = CommonGetTransactionService();

		// DTO to BO conversion
		CloudSvrQrCodeTxn amountInfo = TransactionHelper
				.convertFromDTOtoBOAmountForNormalTxn(txnDTO);
		// new code
		// get details from db
		// dbDetail =
		// getDetailOfInComingRequest(txnDTO,transactionService,amountInfo);

		dbDetail = mapQrCode.get(amountInfo.getTid());

		if (dbDetail != null) {

			dbDetail.setStatusCode(txnDTO.getStatusCode());
			dbDetail.setTxnId(Long.valueOf(txnDTO.getRrn()));

			dbDetail.setProcessPayment(WebServiceConstants.IS_PROCESS_REQ_YES);

			// isUpdate = transactionService.updateRrnService(dbDetail);
			// new code

			dbDetail = mapQrCode.put(amountInfo.getTid(), dbDetail);

			isUpdate = true;

		}

		return isUpdate;

	}
	
	
	
	private void setTokenForTnx(TransactionDTO tranDTO,CloudSvrQrCodeTxn dbDetails) throws CloudServiceException, IntegrationException{
		
		
		 
		
		CloudSvrUser user = getUser(tranDTO);

		// getting account type exp card or bank
		CardOnFileRespDTO accounttype = getAccount(tranDTO,null);

		// get token service required
		// new code
		// token = getOnlineToken(tranDTO, accounttype.getAcctype(), user);

		 getOnlineToken(tranDTO, accounttype.getAcctype(), user, dbDetails);

		// new code
		// local param
		// updateLocalParam(user,dbDetails,transactionService,tranDTO.getAccId(),accounttype.getAcctype());

		dbDetails.setUserId(user.getUserId());
		dbDetails.setAccId(Long.valueOf(tranDTO.getAccId()));
		dbDetails.setAccType(accounttype.getAcctype());
		
		
		// coupon setting
		if(tranDTO.getCouponId()!=null && tranDTO.getCouponId().length()!=0){
		dbDetails.setCouponId(tranDTO.getCouponId());
		}
		
		// reward  setting
		if(tranDTO.getRewardPoint()!=null && tranDTO.getRewardPoint().length()!=0){
			dbDetails.setRewardPoint(tranDTO.getRewardPoint());
		}
			
		
	}

	
///////////////////////////                 end         ///////////////////////////////////////////////////////////	
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
////////////////////////////////////////////////////private method //////////////////////////////////////////////////
	


	
}
