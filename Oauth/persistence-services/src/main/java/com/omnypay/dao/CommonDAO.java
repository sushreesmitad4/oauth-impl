package com.omnypay.dao;

import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;
import com.omnypay.dao.util.CloudDAException;

public interface CommonDAO {

	//method use for getting merchant access key from database
	CloudSvrBusinessEntityInfo getAccessKeyForMerchantDao(
			String entityInfo)throws CloudDAException ;
}
