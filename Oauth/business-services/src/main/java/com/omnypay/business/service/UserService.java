package com.omnypay.business.service;

import java.util.List;















import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersProfile;
import com.omnypay.dao.bo.CloudSvrUsersSecQuestion;

public interface UserService {
	
	
	// this method is use to get the user 
	CloudSvrUser  getUserService(CloudSvrUser user)throws CloudServiceException;
	
	
	// this method we have to login use 
	boolean loginService(CloudSvrUser user) throws CloudServiceException;

	// this method for logout user
	String logoutService(CloudSvrUser user) throws CloudServiceException ;
	// this method for register user
	String registerUser(UserDTO userDTO) throws CloudServiceException;	
	
	// this method for register user
	Boolean isRegisteredUser(LoginDTO loginDTO) throws CloudServiceException;	
	
	// this method for register userInstore
	String registerUserInStoreService(CloudSvrUser user) throws CloudServiceException;
	
	// this method forgetting security question
	List<CloudSvrSecQuesMaster> getSelectedSecurityQuestionsService(CloudSvrUser user) throws CloudServiceException;
	
	// this method for getting  user by imei
	CloudSvrUser  getUserByIMeino(CloudSvrUser user) throws CloudServiceException;
	
	// this method for update Wrong Attempt Security Ans in case of forgot password
	boolean updateWrongAttemptSecurityAnsService(CloudSvrUser user) throws CloudServiceException;
	
	// this method for checking user exist in database or not
	String isUserExistService(CloudSvrUser user) throws CloudServiceException;
	
	String validatingUserService(CloudSvrUser user) throws CloudServiceException;
	
	String isAlreadyLogedInService(CloudSvrUser user) throws CloudServiceException;
	
    String loginFailedService(CloudSvrUser user) throws CloudServiceException;
	
	String setSelectedSecurityQuestionsService(CloudSvrUser user) throws CloudServiceException;
	
	String updateUserProfileService(CloudSvrUsersProfile user )throws CloudServiceException ;
	
	
	
	
	
	
}
