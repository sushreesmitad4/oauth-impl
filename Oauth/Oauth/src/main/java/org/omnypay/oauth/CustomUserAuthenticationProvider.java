package org.omnypay.oauth;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.omnypay.oauth.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/** @author Susil Rayaguru
 *   
 */

@Component(value="customUserAuthenticationProvider")
public class CustomUserAuthenticationProvider implements AuthenticationProvider{
	
	public CustomUserAuthenticationProvider() {
		System.out.println("***** object of CustomerAuthenticationpriver created");
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UserAuthenticationService userAuthenticate; 
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		System.out.println("***************** inside Authentication");
				String username = authentication.getPrincipal().toString();
				String password = authentication.getCredentials().toString();		
					if(userAuthenticate.authenticateUser(username,password )){
					List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
					CustomUserPasswordAuthenticationToken auth=new CustomUserPasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),grantedAuthorities);
					return auth;
					}
					else
						 throw new BadCredentialsException("Bad User Credentials.");
		
			}


	public boolean supports(Class<? extends Object> authentication) {
		
		return true;
		
	}


	public UserAuthenticationService getUserAuthenticate() {
		return userAuthenticate;
	}


	public void setUserAuthenticate(UserAuthenticationService userAuthenticate) {
		this.userAuthenticate = userAuthenticate;
	}

	
	
	

}

	
