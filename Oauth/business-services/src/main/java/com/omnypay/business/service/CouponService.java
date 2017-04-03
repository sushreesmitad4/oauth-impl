package com.omnypay.business.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrRewardPoint;
import com.omnypay.dao.bo.CloudSvrUser;


public interface CouponService {

	//to get the bussiness id
	String getBussinessId(String merchantAccessKey) throws CloudServiceException;
	
	// this method is used to get coupons based upon user
	List<CloudSvrCoupon> getUserCoupons(BaseDTO baseDTO) throws CloudServiceException;
	
	
	// this method is used to get coupons based upon user
	CouponDTO calCoupons(CouponDTO couponDTO) throws CloudServiceException;
	

	// this method is used to get rewards based upon userId
	CloudSvrRewardPoint getUserRewards(BaseDTO baseDTO) throws CloudServiceException;
	
	
	// this method is used to calculate reward
	RewardDTO calReward(RewardDTO rewardDTO) throws CloudServiceException;
	
	
	// this method is used to get coupons based upon couponId
	CloudSvrCoupon getCouponsById(String couponId) throws CloudServiceException;
	
	// this method is used to get coupon id and insert in to the COUPON_REDEMPTION_DATA table
	
	boolean setCouponRedemptionData(String couponId,String userId) throws CloudServiceException;
	
	
	// this method is used to update and insert in to the USER_REWARS  table
	
	boolean updateRewardPoint(String rewardPoint,String userId,String totalamount) throws CloudServiceException;
		
	
}
