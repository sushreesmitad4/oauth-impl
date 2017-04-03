package com.omnypay.merchant.services;



import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.merchant.common.IMerchantUserServices;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;

public class MerchantUserServices {
	
	
	@Autowired
	private MerchantServiceRoute merchantServiceRoute;
	
	
	
	
	public Status login(MerchantAccessDTO merchantDto, LoginDTO loginDto) throws MerchantServiceException{
			
		if(merchantServiceRoute!= null)
		{
			IMerchantUserServices mechantUserServices = merchantServiceRoute.createServiceUserObject(merchantDto);
			if(mechantUserServices != null) {
				
			return mechantUserServices.login(loginDto);
			
			}
		}
		return MerchantUtil.getStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
		
	
	
	public Status register(MerchantAccessDTO merchantDto, UserDTO UserDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantUserServices mechantUserServices = merchantServiceRoute.createServiceUserObject(merchantDto);
			if(mechantUserServices != null) {
				
				return mechantUserServices.register(UserDTO);
			
			}
		}
		return MerchantUtil.getStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
	public Status logout(MerchantAccessDTO merchantDto,BaseDTO baseDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantUserServices mechantUserServices = merchantServiceRoute.createServiceUserObject(merchantDto);
			if(mechantUserServices != null) {
				
				return mechantUserServices.logout(baseDTO);
			
			}
		}
		return MerchantUtil.getStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
	
	
	
	
	public Status updateUserProfile(MerchantAccessDTO merchantDto,UpdateUserDTO updateUserDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantUserServices mechantUserServices = merchantServiceRoute.createServiceUserObject(merchantDto);
			if(mechantUserServices != null) {
				
				return mechantUserServices.updateUserProfile(updateUserDTO);
			
			}
		}
		return MerchantUtil.getStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	
	
	
	
		
	
		
	}
	
	
	
	
	
	
	


