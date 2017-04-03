package com.omnypay.common.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.CouponSubfieldDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.RewardSubfieldDTO;
import com.omnypay.common.services.dto.RewardSubfieldDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.bo.CloudSvrRewardPoint;

public class CouponRewardHelper {

	
	
	
	
	
	
	
	
	
	/***
	 * check Mandatory Fields for Coupon
	 * 
	 * @param obj represent all details
	 * 
	 * 
	 * @return true/false 
	 */	
	public static boolean checkMandatoryFieldsCoupon(Object obj) {

		if (obj instanceof CouponDTO) {

			CouponDTO coupondDTO = (CouponDTO) obj;

			if (coupondDTO.getCouponId().trim().isEmpty()
						|| coupondDTO.getTotalAmount().trim().isEmpty()) {

				return false;
			}

		}

		return true;
	}

	
	
	
	
	/***
	 * check Mandatory Fields for Rewards
	 * 
	 * @param obj represent all details
	 * 
	 * 
	 * @return true/false 
	 */	
	public static boolean checkMandatoryFieldsReward(Object obj) {

		if (obj instanceof RewardDTO) {

			RewardDTO rewardDTO = (RewardDTO) obj;

			if (rewardDTO.getRewardPontTotal().trim().isEmpty()) {

				return false;
			}

		}

		return true;
	}

	
	
	
	/***
	 * checkMandatoryFields CouponDiscount
	 * 
	 * @param obj represent all details
	 * 
	 * 
	 * @return true/false 
	 */	
	public static boolean checkMandatoryFieldsCouponDiscount(Object obj) {

		if (obj instanceof CouponSubfieldDTO) {

			CouponSubfieldDTO couponSubfieldDTO = (CouponSubfieldDTO) obj;

			if (couponSubfieldDTO.getAmount().trim().isEmpty()
					|| couponSubfieldDTO.getCouponId().trim().isEmpty()
					) {

				return false;
			}

		}

		return true;
	}

	
	
	
	

	/***
	 * check Mandatory Fields for Rewards
	 * 
	 * @param obj represent all details
	 * 
	 * 
	 * @return true/false 
	 */	
	public static CloudSvrRewardPoint convertFromDTOtoBO(Object obj,String userId) {

		CloudSvrRewardPoint rewardBO = new CloudSvrRewardPoint();
		
		if(obj instanceof RewardDTO ){
			
			RewardDTO rewardDTO = (RewardDTO) obj;
			
			/*if(rewardDTO!=null && rewardDTO.getRewardPontActive()!= null 
					&& rewardDTO.getRewardPontCancelled()!= null && rewardDTO.getRewardPontConverted()!=null
					&& rewardDTO.getRewardPontPending()!= null && rewardDTO.getRewardPontExpire()!=null
					&& rewardDTO.getActivationDate()!= null && rewardDTO.getExpireDate()!=null && rewardDTO.getRewardPontTotal()!= null
					)
			{
			
			//rewardBO.setRewardId(Long.valueOf(rewardDTO.getRewardId()));
			
			rewardBO.setRewardPontActive(Long.valueOf(rewardDTO.getRewardPontActive()));
			
			rewardBO.setRewardPontCancelled(Long.valueOf(rewardDTO.getRewardPontCancelled()));
			
			rewardBO.setRewardPontConverted(Long.valueOf(rewardDTO.getRewardPontConverted()));
			
			rewardBO.setRewardPontPending(Long.valueOf(rewardDTO.getRewardPontPending()));
			
			rewardBO.setRewardPontTotal(Long.valueOf(rewardDTO.getRewardPontTotal()));
			
			rewardBO.setRewardPontExpire(Long.valueOf(rewardDTO.getRewardPontExpire()));
			
			rewardBO.setActivationDate(Timestamp.valueOf(rewardDTO.getActivationDate()));
			
			rewardBO.setExpireDate(Timestamp.valueOf(rewardDTO.getExpireDate()));
			}
			*/
			rewardBO.setUserId(userId);
		}
		return rewardBO;
	}
	
	
	
	
	
	
/*	*//***
	 * check Mandatory Fields for discount
	 * 
	 * @param obj represent all details
	 * 
	 * 
	 * @return true/false 
	 *//*	
	public static CloudSvrDiscountType convertFromDTOtoBOForDiscount(Object obj,CloudSvrCoupon usercoupon) {
	
		CloudSvrDiscountType couponBo = new CloudSvrDiscountType();
		
		if(obj instanceof CouponSubfieldDTO ){
			
			CouponSubfieldDTO couponSubDTO = (CouponSubfieldDTO) obj;
			
			if(couponSubDTO!=null && couponSubDTO.getAmount()!= null 
					&& usercoupon.getCouponId()!= 0)
			{
			
			
			couponBo.setDiscountId(usercoupon.getCouponId());
			
			rewardBO.setRewardPontCancelled(Long.valueOf(rewardDTO.getRewardPontCancelled()));
			
			rewardBO.setRewardPontConverted(Long.valueOf(rewardDTO.getRewardPontConverted()));
			
			rewardBO.setRewardPontPending(Long.valueOf(rewardDTO.getRewardPontPending()));
			
			rewardBO.setRewardPontTotal(Long.valueOf(rewardDTO.getRewardPontTotal()));
			
			rewardBO.setRewardPontExpire(Long.valueOf(rewardDTO.getRewardPontExpire()));
			
			rewardBO.setActivationDate(Timestamp.valueOf(rewardDTO.getActivationDate()));
			
			rewardBO.setExpireDate(Timestamp.valueOf(rewardDTO.getExpireDate()));
			}
			
			rewardBO.setUserId(userId);
		}
		return rewardBO;
	}
			
	
	*/
	
	

	
	
	
	
/*	*//***
	 * convert From BO List ToDTO List for getting coupons
	 * @param coList List<CloudSvrCoupon> object having all coupon details
	 *  
	 * 
	 * @return  List<CouponSubfieldDTO>
	 *//*
	public static List<CouponSubfieldDTO> converFromBOListToDTOListForCoupon(
								List<CloudSvrCoupon> coList){
		
		List<CouponSubfieldDTO> couponSubFieldsDTOs = new ArrayList<CouponSubfieldDTO>();
		CouponSubfieldDTO couponSubFieldsDTO =null;
		
		
		//uncomment when data came from db
		for (CloudSvrCoupon cloudSvrCoupon : coList) {
			
			couponSubFieldsDTO = new CouponSubfieldDTO();
			
			couponSubFieldsDTO.setCouponId(Long.toString(cloudSvrCoupon.getCouponId()));
			couponSubFieldsDTO.setCouponTitle(cloudSvrCoupon.getCouponTitle());			
			couponSubFieldsDTO.setCouponDescription(cloudSvrCoupon.getCouponDescription());
			couponSubFieldsDTO.setValidityEndDate(cloudSvrCoupon.getValidityEndDate().toString());
			couponSubFieldsDTO.setTermsAndCondition(cloudSvrCoupon.getTermsAndCondition());
						
			couponSubFieldsDTOs.add(couponSubFieldsDTO);
			
		}*/
		
			
			
			
			
			/*//time being hard coded for response to mobile need to remove
			couponSubFieldsDTO.setCouponId("11");
			couponSubFieldsDTO.setCatagoryName("diwali");
			couponSubFieldsDTO.setMaxRedemption("122");
			couponSubFieldsDTO.setCouponIssuerId("1234");
			couponSubFieldsDTO.setCouponIssuerName("flipkart");
			couponSubFieldsDTO.setCouponTitle("55");
			couponSubFieldsDTO.setExceptionId("12");
			couponSubFieldsDTO.setStoreId("2345666");
			couponSubFieldsDTO.setDiscountId("124");
			couponSubFieldsDTO.setValidityStartDate("10/10/2015 11:33:23");
			couponSubFieldsDTO.setValidityEndDate("10/10/2015 11:33:23");
			couponSubFieldsDTO.setTermsAndCondition("new user");
			couponSubFieldsDTO.setCountryCode("91");
			couponSubFieldsDTO.setRedemptionChannel("1sale");
			couponSubFieldsDTO.setUrlIdentifier("hhhhhhhh");
			couponSubFieldsDTO.setSkuId("sku78");
			couponSubFieldsDTO.setSubCatagory("sub");
			couponSubFieldsDTO.setCouponDescription("half");
			couponSubFieldsDTO.setMerchantId("dxf234444fffff");
			
			//time being hard coded for response to mobile need to remove
			couponSubFieldsDTO1.setCouponId("11");
			couponSubFieldsDTO1.setCatagoryName("diwali");
			couponSubFieldsDTO1.setMaxRedemption("122");
			couponSubFieldsDTO1.setCouponIssuerId("1234");
			couponSubFieldsDTO1.setCouponIssuerName("flipkart");
			couponSubFieldsDTO1.setCouponTitle("55");
			couponSubFieldsDTO1.setExceptionId("12");
			couponSubFieldsDTO1.setStoreId("2345666");
			couponSubFieldsDTO1.setDiscountId("124");
			couponSubFieldsDTO1.setValidityStartDate("10/10/2015 11:33:23");
			couponSubFieldsDTO1.setValidityEndDate("10/10/2015 11:33:23");
			couponSubFieldsDTO1.setTermsAndCondition("new user");
			couponSubFieldsDTO1.setCountryCode("91");
			couponSubFieldsDTO1.setRedemptionChannel("1sale");
			couponSubFieldsDTO1.setUrlIdentifier("hhhhhhhh");
			couponSubFieldsDTO1.setSkuId("sku78");
			couponSubFieldsDTO1.setSubCatagory("sub");
			couponSubFieldsDTO1.setCouponDescription("half");
			couponSubFieldsDTO1.setMerchantId("dxf234444fffff");*/
			//couponSubFieldsDTOs.add(couponSubFieldsDTO);
			
		/*//}
		return couponSubFieldsDTOs;
	}
	
	*/
	
	
	
	
	
	
	
	
	
	/***
	 * convert From BO List ToDTO List for getting coupons
	 * @param coList CloudSvrRewardPoint object having user reward details
	 *  
	 * 
	 * @return RewardsubfieldDTO
	 *//*
	public static RewardSubfieldDTO converFromBOListToDTOForReward(
								CloudSvrRewardPoint rewardList){
		
		RewardSubfieldDTO rewardsubfieldDTO = null;
		
		if (rewardList!=null ){
			
			
			rewardsubfieldDTO = new  RewardSubfieldDTO();
			
			
			if (rewardList.getActivationDate()!=null){
				
			rewardsubfieldDTO.setExpireDate(timeStampToString(rewardList.getActivationDate()));	
			}
									
			if (rewardList.getRewardId()!=0l){
			rewardsubfieldDTO.setRewardId(Long.toString(rewardList.getRewardId()));
			}
			
			if (rewardList.getRewardId() != 0l) {
				rewardsubfieldDTO.setRewardPontTotal(String.valueOf(rewardList
						.getRewardPontTotal()));
			} else {

				rewardsubfieldDTO
						.setRewardPontTotal(WebServiceConstants.SINGLE_ZERO);
			}
			
		}
		
	
		//rewardsubfieldDTO.setRewardId(String.valueOf(rewardList.getRewardId()));
		//rewardsubfieldDTO.setRewardPontActive(String.valueOf(rewardList.getRewardPontActive()));
		//rewardsubfieldDTO.setRewardPontCancelled(String.valueOf(rewardList.getRewardPontCancelled()));
		//rewardsubfieldDTO.setRewardPontConverted(String.valueOf(rewardList.getRewardPontConverted()));
		//rewardsubfieldDTO.setRewardPontExpire(String.valueOf(rewardList.getRewardPontExpire()));
		//rewardsubfieldDTO.setRewardPontPending(String.valueOf(rewardList.getRewardPontPending()));
		//rewardsubfieldDTO.setActivationDate(timeStampToString(rewardList.getActivationDate()));
	
			return rewardsubfieldDTO;
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * convert From Timestamp to String
	 * 
	 * @param timestamp
	
	 * 
	 * 
	 * @return String 
	 */
	public static  String timeStampToString(Timestamp timestamp) {
		
	       
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WebServiceConstants.DATE_FORMAT_TIMESTAMP);

	        String formateDate =simpleDateFormat.format(timestamp).toString();
	      
	        return formateDate;
	     
	    }
	
	
	
	
	
	public static  CouponRespDTO fetchCoupons(List<CloudSvrCoupon> coList) {
		
		CouponRespDTO status = null;
		
		
		
		List<CouponSubfieldDTO> couponSubFieldsDTOs = new ArrayList<CouponSubfieldDTO>();
		CouponSubfieldDTO couponSubFieldsDTO =null;
		
		
		//uncomment when data came from db
		for (CloudSvrCoupon cloudSvrCoupon : coList) {
			
			couponSubFieldsDTO = new CouponSubfieldDTO();
			
			couponSubFieldsDTO.setCouponId(Long.toString(cloudSvrCoupon.getCouponId()));
			couponSubFieldsDTO.setCouponTitle(cloudSvrCoupon.getCouponTitle());			
			couponSubFieldsDTO.setCouponDescription(cloudSvrCoupon.getCouponDescription());
			couponSubFieldsDTO.setValidityEndDate(cloudSvrCoupon.getValidityEndDate().toString());
			couponSubFieldsDTO.setTermsAndCondition(cloudSvrCoupon.getTermsAndCondition());
						
			couponSubFieldsDTOs.add(couponSubFieldsDTO);
			
		}
		
		
		
		if (couponSubFieldsDTOs!=null && couponSubFieldsDTOs.size()!=0){
			
			status = (CouponRespDTO) WebServiceUtil.getCouponResp(
					WebServiceConstants.RECORDS_FOUND, WebServiceConstants.ONE,
					couponSubFieldsDTOs);
			
		} else{
			
			
			status = (CouponRespDTO) WebServiceUtil.getCouponResp(
					WebServiceConstants.RECORDS_NOT_FOUND, WebServiceConstants.TWO,
					couponSubFieldsDTOs);
			
			
		}
		
		
		

		

		return status;

	}
	
	
	
	
	
	
	
	
	public static  RewardRespDTO fetchRewards(CloudSvrRewardPoint reward) {

		RewardRespDTO status = null;
		
		//RewardSubfieldDTO rewardSubfield = null;
		
		RewardSubfieldDTO rewardsubfieldDTO = null;
		
		if (reward!=null ){
			
			
			rewardsubfieldDTO = new  RewardSubfieldDTO();
			
			
			if (reward.getActivationDate()!=null){
				
			rewardsubfieldDTO.setExpireDate(timeStampToString(reward.getActivationDate()));	
			}
									
			if (reward.getRewardId()!=0l){
			rewardsubfieldDTO.setRewardId(Long.toString(reward.getRewardId()));
			}
			
			if (reward.getRewardId() != 0l) {
				rewardsubfieldDTO.setRewardPontTotal(String.valueOf(reward
						.getRewardPontTotal()));
			} else {

				rewardsubfieldDTO
						.setRewardPontTotal(WebServiceConstants.SINGLE_ZERO);
			}
			
		}
		
		
		

		if (rewardsubfieldDTO != null) {


			// get status to send to client
			status = WebServiceUtil.getRewardResp(
					WebServiceConstants.RECORDS_FOUND, WebServiceConstants.ONE,
					rewardsubfieldDTO);

		} else {
			// get status to send to client
			status = WebServiceUtil.getRewardResp(
					WebServiceConstants.RECORDS_NOT_FOUND,
					WebServiceConstants.TWO, rewardsubfieldDTO);
		}

		return status;

	}

	
	
	
	
	public static  DiscountRespDTO getDiscountStatus(CouponDTO couponDto) {
		
		DiscountRespDTO status = null;

		if (couponDto != null) {
			// get status to send to client
			status = (DiscountRespDTO) WebServiceUtil.getDiscountResp(
					WebServiceConstants.DISCOUNT_SUCCESS,
					WebServiceConstants.ONE, couponDto);
		} else {
			status = (DiscountRespDTO) WebServiceUtil.getDiscountResp(
					WebServiceConstants.DISCOUNT_FAIELD,
					WebServiceConstants.TWO);
		}

		return status;

	}
	
	
	
	
	/***
	 * get Status for user update profile
	 * 
	 * @param result
	 *            - a response string
	 * @return Status object
	 */
	public static  Status calcRewardStatus(RewardDTO rewardDto) {

		Status status = null;

		if (rewardDto != null) {

			status = WebServiceUtil.getStatus(
					WebServiceConstants.REWARD_CALC_SUCCESS,
					WebServiceConstants.ONE);

		} else {
			status = WebServiceUtil.getStatus(
					WebServiceConstants.REWARD_ADD_FAILED,
					WebServiceConstants.TWO);
		}

		return status;
	}
		
	
	
	
	
	
}
