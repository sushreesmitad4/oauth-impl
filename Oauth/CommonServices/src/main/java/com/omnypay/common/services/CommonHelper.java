package com.omnypay.common.services;


import com.omnypay.dao.bo.CloudSvrBusinessEntityInfo;

public class CommonHelper {
	
	
	/***
	 * convert From DTO to BO For Merchant AccessKey
	 * 
	 * @param merchantAccessKey represent merchantAccessKey
	
	 * 
	 * 
	 * @return entityBO 
	 */
	public static CloudSvrBusinessEntityInfo convertFromDTOtoBOForMerchantAccessKey(
			String merchantAccessKey) {

		CloudSvrBusinessEntityInfo entityBO = new CloudSvrBusinessEntityInfo();

		if (merchantAccessKey != null
				&& merchantAccessKey.length() != 0) {
			entityBO.setMerchantKey(merchantAccessKey);
		}

		return entityBO;
	}
	
	

}
