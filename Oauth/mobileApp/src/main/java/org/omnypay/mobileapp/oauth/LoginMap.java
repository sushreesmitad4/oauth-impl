package org.omnypay.mobileapp.oauth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.omnypay.common.services.dto.LoginDTO;
/** 
 *  @author Susil Rayaguru
 *  LoginMap acts as a  user cache, which keep logged in  user details along with OauthServer generated token as key.
 *    
 *  
 */
public class LoginMap {

	public static ConcurrentMap<String,LoginDTO> userMap = new ConcurrentHashMap();
	
	public  static void addUser(String key, LoginDTO login) {
		
		userMap.put(key, login);
		
	}

	public static  LoginDTO getUser(String key) {
		
		return userMap.get(key);
	}

	public static   LoginDTO removeUser(String key) {
		
		return userMap.remove(key);
	}
	
	
	
	
	
}
