package com.omnypay.merchant.common;

import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;

/**
 * @author jagdishm
 *
 */
public interface IMerchantAccountService {
	
	
	AccountRespDTO addCard(AccountDTO accountDTO);
	
	AccountRespDTO addBank(AccountDTO accountDTO);
	
	AccountRespDTO deleteAccount(AccountDTO accountDTO);
	
	AccountRespDTO getMoreOffLineToken(AccountDTO accountDTO);
	
	
	AccountRespDTO checkScanRequest(AccountDTO accountDTO);
	
	
	
	
}
