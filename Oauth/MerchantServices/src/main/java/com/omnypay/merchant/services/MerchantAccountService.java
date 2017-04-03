package com.omnypay.merchant.services;
import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.PasswordDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.log.ErrorCodeConstants;
/**
 * @author jagdishm
 *
 */
import com.omnypay.merchant.common.IMerchantAccountService;
import com.omnypay.merchant.common.IMerchantPasswordService;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;

public class MerchantAccountService {
	
	
	
	
	
	@Autowired
	private MerchantServiceRoute merchantServiceRoute;
	
	
	public AccountRespDTO addCard(MerchantAccessDTO merchantDto,AccountDTO accountDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantAccountService merchantAccountService = merchantServiceRoute.createServiceAccountObject(merchantDto);
			
			if(merchantAccountService != null) {
				
				return merchantAccountService.addCard(accountDTO);
			
			}
		}
		return MerchantUtil.getAccountRespStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
	public AccountRespDTO addBank(MerchantAccessDTO merchantDto,AccountDTO accountDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantAccountService merchantAccountService = merchantServiceRoute.createServiceAccountObject(merchantDto);
			
			if(merchantAccountService != null) {
				
				return merchantAccountService.addBank(accountDTO);
			
			}
		}
		return MerchantUtil.getAccountRespStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	public AccountRespDTO deleteAccount(MerchantAccessDTO merchantDto,AccountDTO accountDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantAccountService merchantAccountService = merchantServiceRoute.createServiceAccountObject(merchantDto);
			
			if(merchantAccountService != null) {
				
				return merchantAccountService.deleteAccount(accountDTO);
			
			}
		}
		return MerchantUtil.getAccountRespStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}

	
	
	
	
	
	public AccountRespDTO getMoreOffLineToken(MerchantAccessDTO merchantDto,AccountDTO accountDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantAccountService merchantAccountService = merchantServiceRoute.createServiceAccountObject(merchantDto);
			
			if(merchantAccountService != null) {
				
				return merchantAccountService.getMoreOffLineToken(accountDTO);
			
			}
		}
		return MerchantUtil.getAccountRespStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
	
	
	public AccountRespDTO checkScanRequest(MerchantAccessDTO merchantDto,AccountDTO accountDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantAccountService merchantAccountService = merchantServiceRoute.createServiceAccountObject(merchantDto);
			
			if(merchantAccountService != null) {
				
				return merchantAccountService.checkScanRequest(accountDTO);
			
			}
		}
		return MerchantUtil.getAccountRespStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
}
