/**
 * 
 */
package com.omnypay.merchant.kohls.servicesImpl;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.api.services.KohlApiUserServices;
import com.omnypay.api.servicesImpl.KohlApiUserServicesImpl;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.dao.UserDao;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersProfile;
import com.omnypay.dao.bo.CloudSvrUsersSecQuestion;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;
import com.omnypay.merchant.kohls.services.KohlService;
import com.omnypay.merchant.kohls.util.MerchantConstants;
import com.omnypay.merchant.kohls.util.MerchantServiceException;


/**
 * @author jagdishm
 *
 */

//@Service("kohlService")
public class KohlServiceImpl implements KohlService{	
	

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	public KohlServiceImpl() {
	}

	
	



 
	@Transactional
	public String kohlLoginService(LoginDTO user)
			throws MerchantServiceException {
	
		KohlApiUserServices server = new 	KohlApiUserServicesImpl();
		
		String  as = null; //server.login("Hi");
		
		return as;
	}

		
	
}
