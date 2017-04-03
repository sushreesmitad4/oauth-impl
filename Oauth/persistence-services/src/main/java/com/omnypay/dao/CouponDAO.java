package com.omnypay.dao;


import java.util.List;

import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrCouponsRedemptionData;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.bo.CloudSvrRewardPoint;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.util.CloudDAException;

public interface CouponDAO {
	
	//to get user coupons
	List<CloudSvrCoupon> getUserCoupons(CloudSvrUser users) throws CloudDAException;
	
	
	//to get user coupons by couponId
	CloudSvrCoupon getCouponsById(String  couponId) throws CloudDAException;
	
	//to get user rewards
	CloudSvrRewardPoint getUserRewards(CloudSvrUser users) throws CloudDAException;
		
	// for updating rewards of an existing user	
	void updateUserRewardsDAO(CloudSvrRewardPoint rewardPoint)throws CloudDAException;
	
	// for insert/update  CouponRedemptionData of an new  user	
	boolean setCouponRedemptionData(CloudSvrCouponsRedemptionData couponRedData,String userId) throws CloudDAException;
	
	
	// for get getDiscountTypeByDiscountId of an   user
	CloudSvrDiscountType getDiscountTypeByDiscountId(Long dicountId) throws CloudDAException;
	
	// for get getDiscountTypeByDiscountId of an   user
	CloudSvrCouponsRedemptionData getCouponsRedempById(String couponId) throws CloudDAException;
	
	
}
