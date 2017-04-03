package com.omnypay.dao;

import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.util.CloudDAException;

public interface DiscountDAO {

	
	CloudSvrDiscountType getDiscountType(CloudSvrCoupon dbcoupon)throws CloudDAException;
	
}
