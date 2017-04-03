package org.omnypay.oauth.services;

import org.springframework.stereotype.Component;

@Component
public interface UserAuthenticationService {



	public boolean authenticateUser(String userid, String password); 
	
	
}
