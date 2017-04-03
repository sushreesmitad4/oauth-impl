package com.omnypay.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.TransactionService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.dao.TransactionDao;
import com.omnypay.dao.UserDao;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrPosCon;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.dao.bo.CloudSvrTempToken;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrUser;
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
@Service("txnService")
public class TransactionServiceImpl implements TransactionService{

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	public TransactionServiceImpl() {
	}
	
	
	@Autowired UserDao userDao;
	@Autowired TransactionDao txnDao;

	

	/**
	 * for getting user transaction history report
	 * @param txn CloudSvrTxn object
	 * @return txnList  List<CloudSvrTxn> object having all the history report
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public List<CloudSvrTxn> getHistoryService(CloudSvrTxn txn)
			throws CloudServiceException {

		String METHOD_NAME = "getHistoryService";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		List<CloudSvrTxn> txnList = null;

		try {

			txnList = this.txnDao.getHistoryDao(txn, txn.getCustomerId());

		} catch (CloudDAException accessException) {
         
			getHistoryServiceCloudServiceException(accessException);	

		} catch (Exception ex) {
			getHistoryServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return txnList;
	}

	
	
	
		
	/**
	 * for getting account based user history report
	 * @param txn CloudSvrTxn object
	 * @return txnList  List<CloudSvrTxn> object having all the history report
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public List<CloudSvrTxn> getAcctBasedTransactionSummaryService(CloudSvrTxn txn) throws CloudServiceException{
		
		String METHOD_NAME="getAcctBasedTransactionSummaryService";
			
		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
	
		List<CloudSvrTxn> tnxList = null;
		
		try {
			
			
			tnxList = this.txnDao.getAcctBasedTransactionSummaryDao(txn);
				
			
		} catch (CloudDAException accessException) {
			
			getAcctBasedTransactionSummaryCloudServiceException(accessException);
			

		} catch (Exception ex) {
			
			getAcctBasedTransactionSummaryException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return tnxList;
	}

	
	
	
	
	/**
	 * for getting get Mid From database
	 * @param txn CloudSvrQrCodeTxn object having all details to get mid
	 * @return txndb  CloudSvrPosCon object having all details related to mid
	 * @throws CloudServiceException in case of business validation failures
	 */
	@Transactional
	public CloudSvrPosCon getMidFromDbService(CloudSvrQrCodeTxn txn) throws CloudServiceException{
		
		String METHOD_NAME="getMidFromDbService";
		
		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		CloudSvrPosCon txndb=null;
		
		try {

			
			txndb = this.txnDao.getMidFromDbDao(txn);
			
			
		

		} catch (CloudDAException accessException) {
			
			getMidFromDbServiceCloudServiceException(accessException);
			

		} catch (Exception ex) {
			getMidFromDbServiceException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return txndb;
	}
	
	
	
	/////////////////// private methods for Exception ///////////////
	
	
	/**
	 * get cloudserviceException for AcctBasedTransactionSummary
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getAcctBasedTransactionSummaryCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getAcctBasedTransactionSummaryCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while user report history account based")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception for AcctBasedTransactionSummary
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getAcctBasedTransactionSummaryException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getAcctBasedTransactionSummaryException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while  user report history account based")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_REPORT_ACCOUNT_BASED_HISTORY,
				ex.getMessage());
	}
	
	
	
	
	
	
	/**
	 * get cloudserviceException for user report history
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getHistoryServiceCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getHistoryServiceCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while try to get merchant details  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());

	}
	
	/**
	 * get Exception for user report history
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getHistoryServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getHistoryServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while  user report history ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_REPORT_HISTORY)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.USER_REPORT_HISTORY,
				ex.getMessage());
	}
	
	
	
	/**
	 * get cloudserviceException for  get Mid From Db
	 * @param CloudDAException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getMidFromDbServiceCloudServiceException(
			CloudDAException accessException) throws CloudServiceException {
		String METHOD_NAME = "getMidFromDbServiceCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while try to get merchant details  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO,
				accessException.getMessage());
	}
	
	/**
	 * get Exception for get Mid From Db
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getMidFromDbServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getMidFromDbServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while try to get merchant details  ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.GET_MERCHANT_DETAILS_INFO,
				ex.getMessage());
	}
	
}
