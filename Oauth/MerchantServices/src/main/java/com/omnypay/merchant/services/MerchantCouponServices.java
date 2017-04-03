/**
 * 
 */
package com.omnypay.merchant.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.merchant.common.IMerchantCouponRewardService;
import com.omnypay.merchant.kohls.util.MerchantServiceException;
import com.omnypay.merchant.kohls.util.MerchantUtil;

/**
 * @author iliyasm
 *
 */
public class MerchantCouponServices {
	
	
	
	@Autowired
	private MerchantServiceRoute merchantServiceRoute;
	
	
	
	
	
	public CouponRespDTO getUserCoupon(MerchantAccessDTO merchantDto,
			BaseDTO baseDTO) throws MerchantServiceException {

		if (merchantServiceRoute != null) {
			IMerchantCouponRewardService merchantCouponRewardService = merchantServiceRoute
					.createServiceCouponRewardObject(merchantDto);

			if (merchantCouponRewardService != null) {

				return merchantCouponRewardService.getUserCoupon(baseDTO);

			}
		}
		return MerchantUtil.getCouponRespStatus(
				ErrorCodeConstants.MERCHANT_CODE,
				ErrorCodeConstants.MERCHANT_MESSAGE,
				ErrorCodeConstants.MERCHANT_TYPE);
	}

	
	
	
	
	
	
	public RewardRespDTO getUserReward(MerchantAccessDTO merchantDto,
			BaseDTO baseDTO) throws MerchantServiceException {

		if (merchantServiceRoute != null) {
			IMerchantCouponRewardService merchantCouponRewardService = merchantServiceRoute
					.createServiceCouponRewardObject(merchantDto);

			if (merchantCouponRewardService != null) {

				return merchantCouponRewardService.getUserReward(baseDTO);

			}
		}
		return MerchantUtil.getRewardRespStatus(
				ErrorCodeConstants.MERCHANT_CODE,
				ErrorCodeConstants.MERCHANT_MESSAGE,
				ErrorCodeConstants.MERCHANT_TYPE);
	}
	
	
	
	
	
	
	public DiscountRespDTO calCoupons(MerchantAccessDTO merchantDto,
			CouponDTO couponDTO) throws MerchantServiceException {

		if (merchantServiceRoute != null) {
			IMerchantCouponRewardService merchantCouponRewardService = merchantServiceRoute
					.createServiceCouponRewardObject(merchantDto);

			if (merchantCouponRewardService != null) {

				return merchantCouponRewardService.calCoupons(couponDTO);

			}
		}
		return MerchantUtil.getCouponCalRespStatus(
				ErrorCodeConstants.MERCHANT_CODE,
				ErrorCodeConstants.MERCHANT_MESSAGE,
				ErrorCodeConstants.MERCHANT_TYPE);
	}
	
	
	
	
	
	public Status calReward(MerchantAccessDTO merchantDto,
			RewardDTO rewardDTO) throws MerchantServiceException {

		if (merchantServiceRoute != null) {
			IMerchantCouponRewardService merchantCouponRewardService = merchantServiceRoute
					.createServiceCouponRewardObject(merchantDto);

			if (merchantCouponRewardService != null) {

				return merchantCouponRewardService.calReward(rewardDTO);

			}
		}
		return MerchantUtil.getStatus(
				ErrorCodeConstants.MERCHANT_CODE,
				ErrorCodeConstants.MERCHANT_MESSAGE,
				ErrorCodeConstants.MERCHANT_TYPE);
	}
	
	
	
	
	
	
	
	
	
}
