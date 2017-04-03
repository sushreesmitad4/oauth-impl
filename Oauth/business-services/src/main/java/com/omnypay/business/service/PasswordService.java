package com.omnypay.business.service;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.dao.bo.CloudSvrUser;
public interface PasswordService {

	
	
	// this method is used for when user forget his password
	String forgotPasswordService (CloudSvrUser dbuser, CloudSvrUser user) throws CloudServiceException;
	
	// this method is used for change password when user want to change his old password
	String changePasswordService (CloudSvrUser dbuser, String password , boolean isOldPasscode) throws CloudServiceException;
	
	// this method is used for reset new password 
	void resetPassValStringUpdateService(CloudSvrUser user,String radomPassString) throws CloudServiceException;
	
}
