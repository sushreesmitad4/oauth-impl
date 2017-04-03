package com.omnypay.dao;

import java.util.List;

import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrPosCon;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.dao.bo.CloudSvrTempToken;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.util.CloudDAException;


public interface TransactionDao {

	
	
	// this method is used for getting user report of all transaction history
	List<CloudSvrTxn> getHistoryDao(CloudSvrTxn txn,String userId)throws CloudDAException;
	
	// this method is used for getting user report of account based transaction history
	List<CloudSvrTxn> getAcctBasedTransactionSummaryDao(CloudSvrTxn txn) throws CloudDAException;
	
	// this method is used for getting merchant information like merchant id merchant name ect
	CloudSvrPosCon getMidFromDbDao(CloudSvrQrCodeTxn amountInfo)  throws CloudDAException;
	
	
	
}
