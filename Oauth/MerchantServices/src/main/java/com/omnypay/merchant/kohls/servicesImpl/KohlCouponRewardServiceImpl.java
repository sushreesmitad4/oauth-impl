/**
 * 
 */
package com.omnypay.merchant.kohls.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.omnypay.business.service.CouponService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.CouponRewardHelper;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrRewardPoint;
import com.omnypay.merchant.common.IMerchantCouponRewardService;

/**
 * @author iliyasm
 *
 */
public class KohlCouponRewardServiceImpl implements IMerchantCouponRewardService{

	
	
	@Autowired
	private CouponService couponService;
	
	
	
	

	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public CouponRespDTO getUserCoupon(BaseDTO baseDTO) {
		
		CouponRespDTO status = null;
				
		try {
			
			List<CloudSvrCoupon> cloudSvrCoupon = couponService.getUserCoupons(baseDTO);
			
			status = CouponRewardHelper.fetchCoupons(cloudSvrCoupon);
			
					
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
		
		
	}






	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public RewardRespDTO getUserReward(BaseDTO baseDTO) {
		// TODO Auto-generated method stub
		
		RewardRespDTO status = null;
		
		try {
			
			CloudSvrRewardPoint dbRewardInfo = couponService.getUserRewards(baseDTO);
			
			status = CouponRewardHelper.fetchRewards(dbRewardInfo);
			
			
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}






	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public DiscountRespDTO calCoupons(CouponDTO couponDTO) {
		// TODO Auto-generated method stub

		DiscountRespDTO status = null;

		try {
			CouponDTO couponDto = couponService.calCoupons(couponDTO);

			status = CouponRewardHelper.getDiscountStatus(couponDto);

		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;

	}






	/*
	 * @Transactional public Status kohlLoginService(LoginDTO user) throws
	 * MerchantServiceException {
	 * 
	 * 
	 * Status status = new Status(); KohlApiServices server = new
	 * KohlApiServicesImpl();
	 * 
	 * String as = server.login("Hi");
	 * 
	 * status.setMessage(as);
	 * 
	 * return status; }
	 */
	public Status calReward(RewardDTO rewardDTO) {
		// TODO Auto-generated method stub
		
		Status status = null;

		try {
			
			RewardDTO rewardDto = couponService.calReward(rewardDTO);
			
			status = CouponRewardHelper.calcRewardStatus(rewardDto);
			
		} catch (CloudServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	

}
