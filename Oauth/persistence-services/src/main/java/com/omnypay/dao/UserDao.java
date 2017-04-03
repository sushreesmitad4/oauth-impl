package com.omnypay.dao;

import java.util.List;




import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersSecQuestion;
import com.omnypay.dao.util.CloudDAException;

/**
 * 
 * @author Kirank
 *
 */
public interface UserDao {

	//this method use for user register
	boolean registerUserDao(CloudSvrUser user)throws CloudDAException;
	
	//this method use for login
	CloudSvrUser loginDao(CloudSvrUser user)throws CloudDAException;
	
	//to get user updated email
	CloudSvrUser getUserupdateEmailDao(CloudSvrUser user)throws CloudDAException;
	
	//to get user updated security questions
	CloudSvrUsersSecQuestion getUserupdatesec(CloudSvrUsersSecQuestion user)throws CloudDAException;
	
	// this method is used for logout 
	void logoutDao(CloudSvrUser user) throws CloudDAException;
	// for checking user exist in db or not
	boolean isUserExistsDao(CloudSvrUser user)throws CloudDAException;
	
	// this method is use to get the user 
	CloudSvrUser getUserDao(CloudSvrUser user) throws CloudDAException;
	
	// this method is used when user trying to update wrong user name 
	void updateUserWrongAttemptDao(CloudSvrUser user) throws CloudDAException ;
	
	// this method when user trying to update his security answer with wrong answer
	boolean updateWrongAttemptSecurityAnsDao(CloudSvrUser user)throws CloudDAException ;
	
	// this method use  to get user list
	List<CloudSvrUser> getUserListDao(CloudSvrUser user)throws CloudDAException;
	
	// this method user account locked 
	void userLockDao(CloudSvrUser user) throws CloudDAException;
	// this method user for user login
	void userLoginDao(CloudSvrUser user) throws CloudDAException;
	
	//// this method user for update user
	void updateUserDao(CloudSvrUser user) throws CloudDAException;
	
	String getBusinessId(String merchantAccesskey) throws CloudDAException; 
	
	
	

}
