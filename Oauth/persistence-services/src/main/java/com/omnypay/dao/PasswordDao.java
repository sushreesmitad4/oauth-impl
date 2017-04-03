package com.omnypay.dao;





import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.util.CloudDAException;

public interface PasswordDao 
{
	
	// this method we use to get password history
	String getPWDHistoryDao(String inputNewPwd)throws CloudDAException;
	

	
	// this method we use for updating the pass val string of the user
	void updateResetPassValStringDao(CloudSvrUser user)  throws CloudDAException;
	
	
	// this method is used for when user want to change his password
	void ChangedPwdDao(CloudSvrUser user) throws CloudDAException;
	
	
}
