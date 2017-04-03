package org.omnypay.mobileapp.oauth;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;

public interface LoginMapService {
	
public  void addUser(String key,LoginDTO login);
public LoginDTO getUser(String key);
public LoginDTO removeUser(String key);

}
