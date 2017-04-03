/**
 * 
 */
package com.omnypay.api.servicesImpl;


import java.util.UUID;

import com.omnypay.api.services.KohlApiUserServices;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;


/**
 * @author iliyasm
 *
 */
public class KohlApiUserServicesImpl implements KohlApiUserServices {

	@Override
	public Status register(UserDTO UserDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  login api 
	 * 
	 */
	public Status login(LoginDTO loginDto) {
		
		Status status= new Status();
		
		status.setMessage(WebServiceConstants.LOGIN_SUCCESS);
		
		status.setCode(WebServiceConstants.CODE);
		
		status.setType(WebServiceConstants.ONE);
		
		return status;
	}

	@Override
	public Status updateUserProfile(UpdateUserDTO updateUserDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status logout(BaseDTO baseDTO) {
		
		Status status= new Status();
		
		status.setMessage(WebServiceConstants.LOG_OUT);
		
		status.setCode(WebServiceConstants.CODE);
		
		status.setType(WebServiceConstants.ONE);
		
		return status;
	}

	
	
	// to get the user data 
	public UserDTO getUserData(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		UserDTO userDTO = new UserDTO() ;
		
		userDTO.setClientUserId(UUID.randomUUID().toString());
		userDTO.setMerchantAccessKey(loginDTO.getMerchantAccessKey());
		userDTO.setPhoneNumber(loginDTO.getPhoneNumber());
		userDTO.setEmailId(loginDTO.getEmailId());
		userDTO.setPasscode(loginDTO.getPasscode());
		return userDTO;
	}



}
