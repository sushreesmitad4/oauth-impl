/**
 * 
 */
package com.omnypay.api.services;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.Status;

/**
 * @author iliyasm
 *
 */
public interface KohlApiCouponRewardServices {
	
	
	
	
	// get user coupon from merchant  
	CouponRespDTO getUserCoupon(BaseDTO baseDTO);
	
	// get user reward from merchant  
	RewardRespDTO getUserReward(BaseDTO baseDTO);
	
	// get user calculation  coupon from merchant  
	DiscountRespDTO calCoupons(CouponDTO couponDTO);
	
	
	// get user calculation  reward from merchant  
	Status calReward(RewardDTO rewardDTO);

}
