package com.omnypay.business.service;

import com.omnypay.business.util.CloudServiceException;
import com.omnypay.dao.bo.CloudSvrCoupon;

public interface DiscountService {
	
	String getDiscountAmount(CloudSvrCoupon dbcoupon,String amount )throws CloudServiceException;
}
