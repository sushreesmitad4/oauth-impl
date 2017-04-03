package com.omnypay.merchant.services;


import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.merchant.common.IMerchantAccountService;
import com.omnypay.merchant.common.IMerchantCouponRewardService;
import com.omnypay.merchant.common.IMerchantCustomerService;
import com.omnypay.merchant.common.IMerchantHceTokenService;
import com.omnypay.merchant.common.IMerchantMitekServerConnector;
import com.omnypay.merchant.common.IMerchantMitekServerService;
import com.omnypay.merchant.common.IMerchantPasswordService;
import com.omnypay.merchant.common.IMerchantPosService;
import com.omnypay.merchant.common.IMerchantSecurityQuestionService;
import com.omnypay.merchant.common.IMerchantTransactionService;
import com.omnypay.merchant.common.IMerchantUserServices;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;

/**
 * 
 * @author jagdishm
 *
 */
public class MerchantServiceRoute {
	
	
	@Autowired
	private IMerchantUserServices kohlUserService;
	
	@Autowired
	private IMerchantSecurityQuestionService kohlSecurityQuestionService;
	
	@Autowired
	private IMerchantPasswordService kohlPasswordService;
	
	
	@Autowired
	private IMerchantAccountService kohlAccountService;
	
	@Autowired
	private IMerchantCustomerService kohlCustomerService;
	
	
	@Autowired
	IMerchantTransactionService kohlTransactionService;
	
	
	@Autowired
	IMerchantCouponRewardService kohlCouponRewardService;
	
	
	
	
	
	/**
	 * 
	 * @param merchantDto
	 * @return
	 * @throws MerchantServiceException
	 */
public IMerchantUserServices createServiceUserObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
		
	switch (MerchantUtil.getEnumFromString(merchantDto.getMerchantNAme()))
	{
	case khols:
		return kohlUserService;
	default : return null;
		
	}
	

	
}
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */

public IMerchantTransactionService createServiceTransactionObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		//IMerchantTransactionService merchantTransactionServiceImpl = new MerchantTransactionService();
		return kohlTransactionService;
	default : return null;
		
	}
	
}

/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */
public IMerchantSecurityQuestionService createServiceSecurityQuestionObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		//IMerchantSecurityQuestionService merchantSecurityQuestionServiceImpl = new MerchantSecurityQuestionService();
		return kohlSecurityQuestionService;
	default : return null;
		
	}
	
}
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */
public IMerchantPosService createServicePosObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		IMerchantPosService merchantPosServiceImpl = new MerchantPosServiceImpl();
		return merchantPosServiceImpl;
	default : return null;
		
	}
	
}
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */
public IMerchantPasswordService createServicePasswordObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		//IMerchantPasswordService merchantPasswordServiceImpl = new MerchantPasswordService();
		return kohlPasswordService;
	default : return null;
		
	}
	
}
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */
public IMerchantMitekServerService createServiceMerchantMitexObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		IMerchantMitekServerService merchantMitekServerService = new MerchantMitekServerService();
		return merchantMitekServerService;
	default : return null;
		
	}
	
}
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */
public IMerchantMitekServerConnector createServiceMitexServerConnectorObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		IMerchantMitekServerConnector merchantTransactionServiceImpl = new MerchantMitekServerConnector();
		return merchantTransactionServiceImpl;
	default : return null;
		
	}
	
}
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */
public IMerchantAccountService createServiceAccountObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		//IMerchantAccountService merchantAccountServiceImpl = new MerchantAccountService();
		return kohlAccountService;
	default : return null;
		
	}
	
}

/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */




public IMerchantCustomerService createServiceCustomerObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		//IMerchantCustomerService merchantCommonServiceImpl = new MerchantCustomerService();
		return kohlCustomerService;
	default : return null;
		
	}
	
}
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */

public IMerchantHceTokenService createServiceHceTokenObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		IMerchantHceTokenService merchantHceTokenServiceImpl = new MerchantHceTokenServiceImpl();
		return merchantHceTokenServiceImpl;
	default : return null;
		
	}
	
}



	
/**
 * 
 * @param merchantDto
 * @return
 * @throws MerchantServiceException
 */

public IMerchantCouponRewardService createServiceCouponRewardObject(MerchantAccessDTO merchantDto) throws MerchantServiceException{
	
	switch (merchantDto.getMerchantNAme())
	{
	case khols:
		//IMerchantCouponRewardService merchantHceTokenServiceImpl = new MerchantHceTokenServiceImpl();
		return kohlCouponRewardService;
	default : return null;
		
	}
	
}






}
