package com.omnypay.merchant.common;


import com.omnypay.common.services.dto.TransactionDTO;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;

/**
 * @author iliyasm
 *
 */
public interface IMerchantTransactionService {
	
	
	TransactionInitiateRespDTO initiate(TransactionDTO tranDTO);
	
	TransactionInitiateRespDTO process(TransactionDTO tranDTO);
	
	TransactionInitiateRespDTO getUserHistory(TransactionDTO tranDTO);
	
	TransactionInitiateRespDTO getAccountBasedTransactionSummary(TransactionDTO tranDTO);
	
	
	TransactionInitiateRespDTO storeRequestOfAmountSave(TransactionDTO tranDTO);
	
	
	
	

}
