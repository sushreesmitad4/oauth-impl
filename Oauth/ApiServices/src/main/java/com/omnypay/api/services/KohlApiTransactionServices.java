/**
 * 
 */
package com.omnypay.api.services;

import com.omnypay.common.services.dto.TransactionDTO;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;

/**
 * @author iliyasm
 *
 */
public interface KohlApiTransactionServices {
	
	
	TransactionInitiateRespDTO initiate(TransactionDTO tranDTO);
	
	TransactionInitiateRespDTO process(TransactionDTO tranDTO);
	
	TransactionInitiateRespDTO getUserHistory(TransactionDTO tranDTO);
	
	TransactionInitiateRespDTO getAccountBasedTransactionSummary(TransactionDTO tranDTO);
	
	
	TransactionInitiateRespDTO storeRequestOfAmountSave(TransactionDTO tranDTO);
	

}
