package com.omnypay.merchant.services;
import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CustomerRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.merchant.common.IMerchantAccountService;
/**
 * @author jagdishm
 *
 */
import com.omnypay.merchant.common.IMerchantCustomerService;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;

public class MerchantCustomerService {
	
	
	
	
	
	@Autowired
	private MerchantServiceRoute merchantServiceRoute;
	
	
	public CustomerRespDTO fetchCustomerDetails(MerchantAccessDTO merchantDto,BaseDTO baseDTO) throws MerchantServiceException{
		
		if(merchantServiceRoute!= null)
		{
			IMerchantCustomerService merchantCustomerService = merchantServiceRoute.createServiceCustomerObject(merchantDto);
			
			if(merchantCustomerService != null) {
				
				return merchantCustomerService.fetchCustomerDetails(baseDTO);
			
			}
		}
		return MerchantUtil.getCustomerRespStatus(ErrorCodeConstants.MERCHANT_CODE, ErrorCodeConstants.MERCHANT_MESSAGE, ErrorCodeConstants.MERCHANT_TYPE) ;
		}
	

}
