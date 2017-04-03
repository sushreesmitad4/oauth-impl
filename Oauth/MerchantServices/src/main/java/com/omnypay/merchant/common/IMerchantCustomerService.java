package com.omnypay.merchant.common;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CustomerRespDTO;

/**
 * @author jagdishm
 *
 */
public interface IMerchantCustomerService {
	
	
	CustomerRespDTO fetchCustomerDetails(BaseDTO baseDTO);

}
