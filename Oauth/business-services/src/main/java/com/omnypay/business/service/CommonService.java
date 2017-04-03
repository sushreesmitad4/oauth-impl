package com.omnypay.business.service;



import org.springframework.stereotype.Component;

import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;

@Component
public interface CommonService {

	//method use to get merchant access key
	MerchantAccessDTO isAccessKeyForMerchantExistService(String merchantAccess) throws CloudServiceException;
}
