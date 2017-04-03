/**
 * 
 */
package com.omnypay.api.services;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;

/**
 * @author iliyasm
 *
 */
public interface KohlApiUserServices {
	
	
	
	Status register(UserDTO UserDTO);
	
	
	Status login(LoginDTO loginDto);
	
	Status updateUserProfile(UpdateUserDTO updateUserDTO);
	
	Status logout(BaseDTO baseDTO);
	
	
	UserDTO getUserData(LoginDTO loginDTO);

}
