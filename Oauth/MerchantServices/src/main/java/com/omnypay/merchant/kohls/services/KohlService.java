package com.omnypay.merchant.kohls.services;


import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.merchant.kohls.util.MerchantServiceException;

/**
 * 
 * @author jagdishm
 *
 */

public interface KohlService {
	

	public String kohlLoginService(LoginDTO loginDTO) throws MerchantServiceException;
	

}
