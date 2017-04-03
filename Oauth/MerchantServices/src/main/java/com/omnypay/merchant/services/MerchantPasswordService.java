package com.omnypay.merchant.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.PasswordDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.merchant.common.IMerchantPasswordService;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;


public class MerchantPasswordService {
	
	
	@Autowired
	private MerchantServiceRoute merchantServiceRoute;
	
	
	public Status forgotPassword(MerchantAccessDTO merchantDto,PasswordDTO passwordDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantPasswordService merchantPasswordService = merchantServiceRoute.createServicePasswordObject(merchantDto);
			
			if(merchantPasswordService != null) {
				
				return merchantPasswordService.forgotPassword(passwordDTO);
			
			}
		}
		return MerchantUtil.getStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
	
	public Status changePassword(MerchantAccessDTO merchantDto,PasswordDTO passwordDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantPasswordService merchantPasswordService = merchantServiceRoute.createServicePasswordObject(merchantDto);
			
			if(merchantPasswordService != null) {
				
				return merchantPasswordService.changePassword(passwordDTO);
			
			}
		}
		return MerchantUtil.getStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
}
