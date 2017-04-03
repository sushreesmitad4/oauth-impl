package com.omnypay.merchant.kohls.servicesImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.api.services.KohlApiUserServices;
import com.omnypay.api.servicesImpl.KohlApiUserServicesImpl;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.merchant.common.IMerchantUserServices;
import com.omnypay.merchant.kohls.util.MerchantConstants;
import com.omnypay.merchant.kohls.util.MerchantUtil;


/**
 * @author jagdishm
 *
 */

@Component
public class KohlUserServiceImpl implements IMerchantUserServices {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	


	/**/

	@Autowired
	UserService userService;
	
	@Autowired
	KohlApiUserServices kohlApiUserService;
	
	

	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public Status login(LoginDTO loginDto) {

		Status returnStatus = null;

		boolean isRegistered;

		try {

			
			
			// this the initial level checking 
			isRegistered = userService.isRegisteredUser(loginDto);

			
			if (isRegistered) {

				returnStatus = kohlApiUserService.login(loginDto);

			} else {

				// first part

				UserDTO userDTO = kohlApiUserService.getUserData(loginDto);

				if (userDTO != null) {

					// second part
					String status = userService.registerUser(userDTO);

					if (status.equalsIgnoreCase(BusinessConstants.ONE)) {

						returnStatus = kohlApiUserService.login(loginDto);

					}

					

				}

		}

		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnStatus;
	}

	
	
	
	
	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public Status register(UserDTO UserDTO) {
		
		
		return MerchantUtil.getStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
		
		
		
	}





	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public Status logout(BaseDTO baseDTO) {
		// TODO Auto-generated method stub
		return kohlApiUserService.logout(baseDTO);
	}





	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public Status updateUserProfile(UpdateUserDTO updateUserDTO) {
		// TODO Auto-generated method stub
		return MerchantUtil.getStatus(MerchantConstants.CODE, MerchantConstants.MESSAGE, MerchantConstants.TWO);
	}

	

	

}
