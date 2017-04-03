/**
 * 
 */
package com.omnypay.api.services;

import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;

/**
 * @author iliyasm
 *
 */
public interface KohlApiAccountServices {
	
	
	
	AccountRespDTO addCard(AccountDTO accountDTO);
	
	AccountRespDTO addBank(AccountDTO accountDTO);
	
	AccountRespDTO deleteAccount(AccountDTO accountDTO);
	
	AccountRespDTO getMoreOffLineToken(AccountDTO accountDTO);
	
	
	AccountRespDTO checkScanRequest(AccountDTO accountDTO);

}
