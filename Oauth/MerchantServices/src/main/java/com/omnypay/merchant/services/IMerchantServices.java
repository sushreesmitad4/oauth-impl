package com.omnypay.merchant.services;

import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.Status;

public interface IMerchantServices {
	
	
	Status login(LoginDTO loginDto);
	
	

}
