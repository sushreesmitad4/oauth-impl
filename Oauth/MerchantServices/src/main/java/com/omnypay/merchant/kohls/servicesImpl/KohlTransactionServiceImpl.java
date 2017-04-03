/**
 * 
 */
package com.omnypay.merchant.kohls.servicesImpl;




import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.service.CouponService;
import com.omnypay.business.service.HceTokenService;
import com.omnypay.business.service.TransactionService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.business.util.PropertiesUtil;
import com.omnypay.common.services.AccountHelper;
import com.omnypay.common.services.TransactionHelper;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.common.services.dto.HceTokenDTO;
import com.omnypay.common.services.dto.HceTokenRespDTO;
import com.omnypay.common.services.dto.TransactionDTO;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrPosCon;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;
import com.omnypay.merchant.common.IMerchantTransactionService;
import com.omnypay.merchant.kohls.util.MerchantConstants;
import com.omnypay.merchant.kohls.util.MerchantUtil;

/**
 * @author iliyasm
 *
 */
public class KohlTransactionServiceImpl implements  IMerchantTransactionService {
	
	
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	
	@Autowired
	private TransactionService txnService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	
	@Autowired
	private HceTokenService hceService;
	
	
	
	
	
	private ConcurrentMap<String, CloudSvrQrCodeTxn> mapQrCode = null;

	private ConcurrentMap<String, CloudSvrEcommerceTxn> mapEcommCode = null;

	// constructor
	public KohlTransactionServiceImpl() {

		mapQrCode = new ConcurrentHashMap<String, CloudSvrQrCodeTxn>();

		mapEcommCode = new ConcurrentHashMap<String, CloudSvrEcommerceTxn>();
	}
	
	
	
	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public TransactionInitiateRespDTO initiate(TransactionDTO tranDTO) {

		String METHOD_NAME = "initiate";

		// CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,METHOD_NAME);

		TransactionInitiateRespDTO status = null;

		try {

			CloudSvrQrCodeTxn amountInfo = TransactionHelper
					.convertFromDTOtoCOFDTONormalTxn(tranDTO);

			CloudSvrPosCon posConDetail = txnService.getMidFromDbService(amountInfo);
			// new code //danger point// discussed with sir
			CloudSvrQrCodeTxn dbDetails = mapQrCode.get(posConDetail.gettId());

			if (posConDetail != null && dbDetails != null && (posConDetail.getPosId().equalsIgnoreCase(dbDetails
							.getPosId()))
					&& (posConDetail.getQrCode().equalsIgnoreCase(dbDetails
							.getQrCode()))
					/*&& (posConDetail.getMerchantId().equalsIgnoreCase(dbDetails
							.getMid()))*/

					&& (posConDetail.getMerchantAccKey()
							.equalsIgnoreCase(tranDTO.getMerchantAccessKey()))

			) {

				dbDetails.setMerchantName(posConDetail.getMerchantName());
				dbDetails.setMerchantAddress(posConDetail.getMerchantAddress());
				dbDetails.setMid(posConDetail.getMerchantId());
				mapQrCode.put(posConDetail.gettId(), dbDetails);

				// );
				// amount display for mete
			String	formattedAmount = TransactionHelper.getFormattedAmount(String.valueOf(dbDetails
						.getAmount()));

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

		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public TransactionInitiateRespDTO process(TransactionDTO tranDTO) {
		
		
		TransactionInitiateRespDTO status = null;
		
		try {
			
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
				dbDetails.setSplit(tranDTO.isSplit());
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

				status = MerchantUtil.getTransactionStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);

			}
		}  catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		
		
		// TODO Auto-generated method stub
		return status;
	}

	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public TransactionInitiateRespDTO getUserHistory(TransactionDTO tranDTO) {
		// TODO Auto-generated method stub
		
		
		TransactionInitiateRespDTO status = null;
		
		CloudSvrUser user =null;
		try {
			user = this.getUser(tranDTO);
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null) {

			List<CloudSvrTxn> reportList = null;
			try {
				reportList = this.getUserHistoryReport(tranDTO, user);
			} catch (CloudServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			status = this.getUserHistoryReport(reportList);

		} else {

			status = MerchantUtil.getTransactionStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);

		}
		
		
		return status;
	}

	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public TransactionInitiateRespDTO getAccountBasedTransactionSummary(
			TransactionDTO tranDTO) {
		// TODO Auto-generated method stub
		
		TransactionInitiateRespDTO status = null;
		
		CloudSvrUser user =null;
		try {
			user = this.getUser(tranDTO);
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null) {

			// based upon account id we are getting account details
			CardOnFileRespDTO accountno =null;;
			try {
				accountno = this.getAccount(tranDTO,user.getUserId());
			} catch (CloudServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			List<CloudSvrTxn> reportList =null;
			try {
				reportList = this
						.getAccountBasedUserHistoryReport(tranDTO,
								user, accountno);
			} catch (CloudServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			status = this.getUserHistoryReport(reportList);

		} else {

			status =  MerchantUtil.getTransactionStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);

		}
		
		
		
		
		return status;
	}


	
	
	
	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public TransactionInitiateRespDTO storeRequestOfAmountSave(
			TransactionDTO tranDTO) {
	
	
		TransactionInitiateRespDTO status = null;
		
		// DTO to BO conversion
		CloudSvrQrCodeTxn amountInfo = TransactionHelper
				.convertFromDTOtoBOAmountForNormalTxn(tranDTO);
		
		if (amountInfo != null) {
			
			// pos will be id unique for one terminal
			
			mapQrCode.put(amountInfo.getTid(), amountInfo);
			
		}
		
		status = TransactionHelper.storeRequestOfAmountSaveStatus(true);

		
		
		
		return status;
	}



	/**
	 * @return the mapQrCode
	 */
	public ConcurrentMap<String, CloudSvrQrCodeTxn> getMapQrCode() {
		return mapQrCode;
	}



	/**
	 * @param mapQrCode the mapQrCode to set
	 */
	public void setMapQrCode(ConcurrentMap<String, CloudSvrQrCodeTxn> mapQrCode) {
		this.mapQrCode = mapQrCode;
	}



	/**
	 * @return the mapEcommCode
	 */
	public ConcurrentMap<String, CloudSvrEcommerceTxn> getMapEcommCode() {
		return mapEcommCode;
	}



	/**
	 * @param mapEcommCode the mapEcommCode to set
	 */
	public void setMapEcommCode(
			ConcurrentMap<String, CloudSvrEcommerceTxn> mapEcommCode) {
		this.mapEcommCode = mapEcommCode;
	}
	
	
	
	
	private void setTokenForTnx(TransactionDTO tranDTO,CloudSvrQrCodeTxn dbDetails) {
		
		
		 
		
		CloudSvrUser user =null;
		CardOnFileRespDTO accounttype = null;
		try {
			user = getUser(tranDTO);
			// getting account type exp card or bank
			accounttype = getAccount(tranDTO,null);
			 try {
				getOnlineToken(tranDTO, accounttype.getAcctype(), user, dbDetails);
			} catch (IntegrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		// get token service required
		// new code
		// token = getOnlineToken(tranDTO, accounttype.getAcctype(), user);



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
	
	
	
	
	private List<CloudSvrTxn> getUserHistoryReport(TransactionDTO transDTO, CloudSvrUser user) throws CloudServiceException{
		
		
		
		
		// transactionService
		//TransactionService transService = this.getTransactionService();
		// Convert DTO to BO
		CloudSvrTxn txn = TransactionHelper.converDTOtoBO(transDTO,	user.getUserId());

		// call business layer
		List<CloudSvrTxn> txnList = txnService.getHistoryService(txn);
		
		return  txnList;

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
	
	
	

	
}
