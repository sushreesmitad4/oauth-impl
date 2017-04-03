package com.omnypay.merchant.common;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;
/**
 * @author jagdishm
 *
 */
public interface IMerchantUserServices {
	
	
	
	
	
	Status register(UserDTO UserDTO);
	
	
	Status login(LoginDTO loginDto);
	
	Status updateUserProfile(UpdateUserDTO updateUserDTO);
	
	Status logout(BaseDTO baseDTO);
	
	
	

}
