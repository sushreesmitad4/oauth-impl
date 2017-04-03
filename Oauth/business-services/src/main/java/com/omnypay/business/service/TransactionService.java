package com.omnypay.business.service;

import java.util.List;










import com.omnypay.business.util.CloudServiceException;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrPosCon;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.dao.bo.CloudSvrTempToken;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrTxnEntity;
import com.omnypay.dao.bo.CloudSvrUser;

/**
 * 
 * @author Kirank
 *
 */
public interface TransactionService {

	
	// this method we have to get user history report
	List<CloudSvrTxn> getHistoryService(CloudSvrTxn txn) throws CloudServiceException; 
	
	// this method we have to get Account Based Transaction Summary report
	List<CloudSvrTxn> getAcctBasedTransactionSummaryService(CloudSvrTxn txn) throws CloudServiceException;
	
	// this method we have to get mid from database
	CloudSvrPosCon getMidFromDbService(CloudSvrQrCodeTxn amount) throws CloudServiceException;
	
	
	
	
	
}
