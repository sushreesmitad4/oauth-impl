package org.omnypay.mobileapp.oauth;

import javax.servlet.http.HttpServletRequest;
/* @author Susil Rayaguru
 *  
 */
public interface AccessValidator {
	


	public UserResponse validate(UserRequest userRequest,HttpServletRequest httpRequest)throws Exception;


	
	
}
