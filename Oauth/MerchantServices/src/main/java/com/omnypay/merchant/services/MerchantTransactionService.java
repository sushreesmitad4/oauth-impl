package com.omnypay.merchant.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.TransactionDTO;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.merchant.common.IMerchantTransactionService;
import com.omnypay.merchant.common.IMerchantUserServices;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;

public class MerchantTransactionService {
	
	
	
	
	@Autowired
	private MerchantServiceRoute merchantServiceRoute;
	
	
	
	
	public TransactionInitiateRespDTO initiate(MerchantAccessDTO merchantDto, TransactionDTO tranDTO) throws MerchantServiceException{
			
		if(merchantServiceRoute!= null)
		{
			IMerchantTransactionService merchantTransactionService = merchantServiceRoute.createServiceTransactionObject(merchantDto);
			if(merchantTransactionService != null) {
				
			return merchantTransactionService.initiate(tranDTO);
			
			}
		}
		return MerchantUtil.getTransactionStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
		
	
	
	
	public TransactionInitiateRespDTO process(MerchantAccessDTO merchantDto, TransactionDTO tranDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantTransactionService merchantTransactionService = merchantServiceRoute.createServiceTransactionObject(merchantDto);
			if(merchantTransactionService != null) {
				
			return merchantTransactionService.process(tranDTO);
			
			}
		}
		return MerchantUtil.getTransactionStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
	
	public TransactionInitiateRespDTO getUserHistory(MerchantAccessDTO merchantDto, TransactionDTO tranDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantTransactionService merchantTransactionService = merchantServiceRoute.createServiceTransactionObject(merchantDto);
			if(merchantTransactionService != null) {
				
			return merchantTransactionService.getUserHistory(tranDTO);
			
			}
		}
		return MerchantUtil.getTransactionStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}

	
	
	public TransactionInitiateRespDTO getAccountBasedTransactionSummary(MerchantAccessDTO merchantDto, TransactionDTO tranDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantTransactionService merchantTransactionService = merchantServiceRoute.createServiceTransactionObject(merchantDto);
			if(merchantTransactionService != null) {
				
			return merchantTransactionService.getAccountBasedTransactionSummary(tranDTO);
			
			}
		}
		return MerchantUtil.getTransactionStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
	
	public TransactionInitiateRespDTO storeRequestOfAmountSave(MerchantAccessDTO merchantDto, TransactionDTO tranDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantTransactionService merchantTransactionService = merchantServiceRoute.createServiceTransactionObject(merchantDto);
			if(merchantTransactionService != null) {
				
			return merchantTransactionService.storeRequestOfAmountSave(tranDTO);
			
			}
		}
		return MerchantUtil.getTransactionStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
}
