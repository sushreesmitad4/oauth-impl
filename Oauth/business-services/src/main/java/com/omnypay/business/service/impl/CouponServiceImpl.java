package com.omnypay.business.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.CouponService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.Discount;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.dao.CouponDAO;
import com.omnypay.dao.UserDao;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrCouponsRedemptionData;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.bo.CloudSvrRewardPoint;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersProfile;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;


@Service("couponService")
public class CouponServiceImpl implements CouponService {

	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	CouponServiceImpl()
	{}

	@Autowired
	CouponDAO couponDAO;
	
	@Autowired
	UserDao userDao;
	
	
	
	/**
	 * use for getting coupon
	 * @param CloudSvrUser users object having all details to add an account
	 * @return List<CloudSvrCoupon> CardOnFileRespDTO object having response
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public List<CloudSvrCoupon> getUserCoupons(BaseDTO baseDTO) throws CloudServiceException
	{
		   
	
			String METHOD_NAME = "getUserCoupons";
			
			log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
			
			CloudSvrUser user = null;
			
			List<CloudSvrCoupon> cloudSvrCoupon = null;
			

			try {
				
				user = new CloudSvrUser();
				
				user.setEmailId(baseDTO.getEmailId());
				
				user.setMobileNo(baseDTO.getPhoneNumber());
				
				String merchantAccessKey = this.userDao.getBusinessId(baseDTO.getMerchantAccessKey());
				
				user.setBusinessId(merchantAccessKey);
				
				user = userDao.getUserDao(user);
				
				if (user.getUserId()!=null && user.getUserId().length()!=0){
					
					
					cloudSvrCoupon = couponDAO.getUserCoupons(user);
					
				}
				
				
				
				
				//users = this.userDao.getBusinessId(merchantAccesskey);

			} catch (CloudDAException accessException) {

				getCouponCloudServiceException(accessException);
				
			} catch (Exception ex) {
				
				getCouponException(ex);
			}
			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

			return cloudSvrCoupon;
		}
	
	
	
	
	
	
	
	
	
	
	/**
	 * use for getting coupon
	 * 
	 * @param CloudSvrUser
	 *            users object having all details to add an account
	 * @return List<CloudSvrCoupon> CardOnFileRespDTO object having response
	 * @throws CloudServiceException
	 *             if business validation failed
	 * 
	 */
	@SuppressWarnings("unused")
	public CouponDTO calCoupons(CouponDTO couponDTO)
			throws CloudServiceException {
		
		String METHOD_NAME = "calCoupons";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CouponDTO couponDto = null;

		try {

			CloudSvrCoupon coupon = couponDAO.getCouponsById(couponDTO
					.getCouponId());

			if (coupon != null && coupon.getDiscountId() != 0l) {

				CloudSvrDiscountType discountType = null;

				String discountAmount = null;

				discountType = couponDAO.getDiscountTypeByDiscountId(coupon.getDiscountId());

				if (discountType != null
						&& discountType.getDiscountType() != null) {

					switch (Discount.valueOf(discountType.getDiscountType())) {

					case FLAT:

						discountAmount = flatDiscount(discountType.getDiscountValue(),couponDTO.getTotalAmount());
						
						break;

					case PERCENTAGE:

						discountAmount = this.PercentageDiscount(discountType.getDiscountValue(),couponDTO.getTotalAmount());
						break;

					default:
						discountAmount = null;

					}

				}
				if (discountAmount != null) {

					couponDTO.setTotalAmount(discountAmount);

					couponDto = couponDTO;

				}

			}

			// users = this.userDao.getBusinessId(merchantAccesskey);

		} catch (CloudDAException accessException) {

			getCouponCloudServiceException(accessException);

		} catch (Exception ex) {

			getCouponException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return couponDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * use for getting BussinessId
	 * @param merchantAccessKey object having all details to add an account
	 * @return List<CloudSvrCoupon> CardOnFileRespDTO object having response
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public String getBussinessId(String merchantAccessKey) throws CloudServiceException
	{
		String METHOD_NAME = "getBussinessId";
		
		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		
		try {
			merchantAccessKey = this.userDao.getBusinessId(merchantAccessKey);

		} catch (CloudDAException accessException) {

			getBussnessIdCloudServiceException(accessException);
			
		} catch (Exception ex) {
			
			getBussnessIdException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return merchantAccessKey;
	}
	


	
	/**
	 * use for getting UserRewards
	 * @param users CloudSvrUser object having all details to add an account
	 * @return CloudSvrRewardPoint CardOnFileRespDTO object having response
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public CloudSvrRewardPoint getUserRewards(BaseDTO baseDTO) throws CloudServiceException
	
	{
		 
			
			String METHOD_NAME = "getUserRewards";
			
			log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
			
			CloudSvrRewardPoint rewardPoint = null;
			
			CloudSvrUser user = null;

			try {
				
				user = new CloudSvrUser();
				
				user.setEmailId(baseDTO.getEmailId());
				
				user.setMobileNo(baseDTO.getPhoneNumber());
				
				String merchantAccessKey = this.userDao.getBusinessId(baseDTO.getMerchantAccessKey());
				
				user.setBusinessId(merchantAccessKey);
				
				user = userDao.getUserDao(user);
				
				if (user.getUserId()!=null && user.getUserId().length()!=0){
				
				
				
					rewardPoint =this.couponDAO.getUserRewards(user);
				
				}
				

			} catch (CloudDAException accessException) {

				getUserRewardCloudServiceException(accessException);
				
			} catch (Exception ex) {
				
				getUserRewardException(ex);
			}
			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

			return rewardPoint;
		}
		
	
	
	
	
	/**
	 * use for update UserRewards
	 * @param rewardInfo CloudSvrRewardPoint object having all reward related details
	 * @return String
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public RewardDTO calReward(RewardDTO rewardDTO) throws CloudServiceException {
		   	
			CloudSvrRewardPoint dbRewardPoint = null;
			
			String METHOD_NAME = "calReward";
			
			log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
			
			RewardDTO rewardDto = null;
			
			CloudSvrUser user = null;
			
			CloudSvrRewardPoint rewardPoint = null;
			

			try {
				
				
				
				
				
				user = new CloudSvrUser();
				
				user.setEmailId(rewardDTO.getEmailId());
				
				user.setMobileNo(rewardDTO.getPhoneNumber());
				
				String merchantAccessKey = this.userDao.getBusinessId(rewardDTO.getMerchantAccessKey());
				
				user.setBusinessId(merchantAccessKey);
				
				user = userDao.getUserDao(user);
				
				if (user.getUserId()!=null && user.getUserId().length()!=0){
				
				
				
					rewardPoint =this.couponDAO.getUserRewards(user);
					
					
					if (rewardPoint !=null ){
						
						
						Long comingRewardpoint = Long.parseLong(rewardDTO.getRewardPontTotal()); 
						
						Long dbRewardpoint = rewardPoint.getRewardPontTotal();
						
						// coming amount should be less then and equal to the amount 
						if ( comingRewardpoint <= dbRewardpoint ){
							
							
							rewardDto = rewardDTO;
							
							
						}
						
						
						
					}
				
				}
				
				
				
				
				
				
				
				
				
				
				
				
			
			}	

			/*} catch (CloudDAException accessException) {

				getUserRewardCloudServiceException(accessException);
				
			}*/ catch (Exception ex) {
				
				getUserRewardException(ex);
			}
			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);


			return rewardDto;
		}
		
	
	
	
	
	
	/**
	 * use for getting coupon details
	 * @param String represent couponId
	 * @return CloudSvrCoupon having all couponDetail
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public CloudSvrCoupon getCouponsById(String  couponId) throws CloudServiceException
	{
		    CloudSvrCoupon cloudSvrCoupon = null;
	
			String METHOD_NAME = "getCouponsById";
			
			log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
			

			try {
				
				cloudSvrCoupon = this.couponDAO.getCouponsById(couponId);
			

			} catch (CloudDAException accessException) {

				getCouponCloudServiceException(accessException);
				
			} catch (Exception ex) {
				
				getCouponException(ex);
			}
			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

			return cloudSvrCoupon;
		}
	
	
	
	
	
	
	
	
	
	
	/**
	 * use for getting coupon details
	 * @param String represent couponId
	 * @return CloudSvrCoupon having all couponDetail
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public boolean setCouponRedemptionData(String couponId, String userId)
			throws CloudServiceException {
		// TODO Auto-generated method stub

		CloudSvrCoupon cloudSvrCoupon = null;

		boolean isRedemption = false;
		
		CloudSvrCouponsRedemptionData couponsRedemptionData =null;

		String METHOD_NAME = "setCouponRedemptionData";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		try {

			cloudSvrCoupon = couponDAO.getCouponsById(couponId);
			
			if (cloudSvrCoupon !=null  ){
				
				couponsRedemptionData = couponDAO.getCouponsRedempById(couponId);
				
				
					if(couponsRedemptionData!=null)
				{
					
					
					couponsRedemptionData.setRedemptionCount(couponsRedemptionData.getRedemptionCount()+1);
					couponDAO.setCouponRedemptionData(couponsRedemptionData,userId);
				}
					else
					{
					couponsRedemptionData = new CloudSvrCouponsRedemptionData();
					couponsRedemptionData.setCouponId(couponId);
					couponsRedemptionData.setRedemptionCount(cloudSvrCoupon.getMaxRedemption());
					couponDAO.setCouponRedemptionData(couponsRedemptionData,userId);
					}
				isRedemption = true;
				
			}
			

		} catch (CloudDAException accessException) {

			getCouponCloudServiceException(accessException);

		} catch (Exception ex) {

			getCouponException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

		return isRedemption;
	}
	
	
	
	
	
	
	
	
	

	/**
	 * use for getting coupon details
	 * @param String represent couponId
	 * @return CloudSvrCoupon having all couponDetail
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public boolean updateRewardPoint(String rewardPoint, String userId,
			String totalamount) throws CloudServiceException {
	
	
	String METHOD_NAME = "updateRewardPoint";

	log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
	
	
	CloudSvrUser user = null;
	
	CloudSvrRewardPoint reward = null;
	
	Long  addRewardPoint = 0l;
	
	Long mydedectedRewardPoint =0l;
	
	boolean  returnType = false;
	
	try {
		
		user = new CloudSvrUser();
		
		user.setUserId(userId);
		
	if (user.getUserId()!=null && user.getUserId().length()!=0){
		
		
		reward = couponDAO.getUserRewards(user);
		
		
		
		if (reward !=null ){
			
			
			Long totalRewardpoint = reward.getRewardPontTotal(); 
			
			Long rwrdpoint = Long.parseLong(rewardPoint);
			
			// coming amount should be less then and equal to the amount 
			if ( rwrdpoint <= totalRewardpoint ) {
				
				 mydedectedRewardPoint = totalRewardpoint-rwrdpoint;
				
								
			}
			
			// for this coming total amount reward calculation (0.1)
			
			if (totalamount!=null && totalamount.length()!=0){
				
				
				// addRewardPoint = Double.doubleToLongBits(Double.parseDouble(totalamount)*0.1);
				
				Double totalIncomingAmount = Double.parseDouble(totalamount);
				
				addRewardPoint  = (long) (totalIncomingAmount*0.1);
				
			//	 addRewardPoint = Double.parseDouble(totalamount*0.1));
				
				
			}
			
			
			
			if (mydedectedRewardPoint!=0l || addRewardPoint!=0l) {
				
				Long totalRewardCalc = mydedectedRewardPoint + addRewardPoint;
				reward.setRewardPontTotal(totalRewardCalc);
				reward.setUserId(userId);
				couponDAO.updateUserRewardsDAO(reward);
				
				returnType = true;
				
			}
			
			
			
			
			
		} else {
			
			
			 reward =  new CloudSvrRewardPoint();
			
			// for this coming total amount reward calculation (0.1)
			
			if (totalamount!=null && totalamount.length()!=0){
				
				
				 addRewardPoint = Double.doubleToLongBits(Double.parseDouble(totalamount)*0.1);
				 
				 reward.setUserId(userId);
				 reward.setRewardPontTotal(addRewardPoint);
				 
				 couponDAO.updateUserRewardsDAO(reward);
				 
				 returnType = true;
				 
				
				
			}
			
			
			
			
		}
		
		
	}
	} catch (CloudDAException accessException) {

		getCouponCloudServiceException(accessException);

	} catch (Exception ex) {

		getCouponException(ex);
	}
	log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
	
	 return returnType;
}

	
	
	

	/**
	 * to set common reward fields
	 * @param newRewardPoint CloudSvrRewardPoint object having all details of reward
	 *  @param userReward CloudSvrRewardPoint object having all details of reward
	 * 
	 * 
	 */
	
	
	private void commonSetUserRewardPoint(CloudSvrRewardPoint newRewardPoint,CloudSvrRewardPoint userReward) {

		newRewardPoint.setActivationDate(userReward.getActivationDate());
		newRewardPoint.setRewardPontActive(userReward.getRewardPontActive());
		newRewardPoint.setRewardPontTotal(userReward.getRewardPontTotal());
		newRewardPoint.setRewardPontCancelled(userReward.getRewardPontCancelled());
		newRewardPoint.setRewardPontConverted(userReward.getRewardPontConverted());
		newRewardPoint.setRewardPontExpire(userReward.getRewardPontExpire());
		newRewardPoint.setRewardPontPending(userReward.getRewardPontPending());
		newRewardPoint.setExpireDate(userReward.getExpireDate());
	
		
		
	}
	
	
	
	
	

	/**
	 * to update User Rewards
	 * @param user CloudSvrUser object having all details to Update user
	 * 
	 * @throws CloudServiceException in case of business validation failures
	 * 
	 */
	private void commonUpdateReward(CloudSvrRewardPoint rewardPoint) throws CloudServiceException {

		
		try {

			this.couponDAO.updateUserRewardsDAO(rewardPoint);

		} 
		catch (CloudDAException accessException) {
       
			updateReawrdServiceException(accessException);
			
		}

		catch (Exception ex) {
			
			
			updateReawrdException(ex);
			
		}
	}



////////////////////////////////

/**
 * get cloud service Exception 
 * @param CloudDAException
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException getCouponCloudServiceException(
		CloudDAException accessException) throws CloudServiceException {
	String METHOD_NAME = "getCouponCloudServiceException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer
			.append("DataAccessException occurred while getting coupons ")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.GET_COUPON)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(accessException.getMessage());
	log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
	throw new CloudServiceException(accessException.getErrorCode(),
			accessException.getMessage());
}



/**
 * get Exception 
 * @param Exception
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException getCouponException(
		Exception ex) throws CloudServiceException {
	String METHOD_NAME = "getCouponException";
StringBuilder errorBuffer = new StringBuilder();
errorBuffer.append("Exception occurred while getting coupons")
		.append(DaoConstants.COMMA).append("Error Code : ")
		.append(ErrorCodeConstants.GET_COUPON)
		.append(DaoConstants.COMMA).append("Error Message : ")
		.append(ex.getMessage());

log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
throw new CloudServiceException(ErrorCodeConstants.GET_COUPON,
		ex.getMessage());
}
	




/**
 * get cloud service Exception 
 * @param CloudDAException
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException getUserRewardCloudServiceException(
		CloudDAException accessException) throws CloudServiceException {
	String METHOD_NAME = "getUserRewardCloudServiceException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer
			.append("DataAccessException occurred while getting rewards ")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.GET_REWARD)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(accessException.getMessage());
	log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
	throw new CloudServiceException(accessException.getErrorCode(),
			accessException.getMessage());
}



/**
 * get Exception 
 * @param Exception
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException getUserRewardException(
		Exception ex) throws CloudServiceException {
	String METHOD_NAME = "getUserRewardException";
StringBuilder errorBuffer = new StringBuilder();
errorBuffer.append("Exception occurred while getting rewards")
		.append(DaoConstants.COMMA).append("Error Code : ")
		.append(ErrorCodeConstants.GET_REWARD)
		.append(DaoConstants.COMMA).append("Error Message : ")
		.append(ex.getMessage());

log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
throw new CloudServiceException(ErrorCodeConstants.GET_COUPON,
		ex.getMessage());
}



/**
 * get cloud service Exception 
 * @param CloudDAException
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException  getBussnessIdCloudServiceException(
		CloudDAException accessException) throws CloudServiceException {
	String METHOD_NAME = "getBussnessIdCloudServiceException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer
			.append("DataAccessException occurred while getting businessId ")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.GET_BUSSINESS)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(accessException.getMessage());
	log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
	throw new CloudServiceException(accessException.getErrorCode(),
			accessException.getMessage());
}



/**
 * get Exception 
 * @param Exception
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException getBussnessIdException(
		Exception ex) throws CloudServiceException {
	String METHOD_NAME = "getBussnessIdException";
StringBuilder errorBuffer = new StringBuilder();
errorBuffer.append("Exception occurred while getting businessId")
		.append(DaoConstants.COMMA).append("Error Code : ")
		.append(ErrorCodeConstants.GET_BUSSINESS)
		.append(DaoConstants.COMMA).append("Error Message : ")
		.append(ex.getMessage());

log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
throw new CloudServiceException(ErrorCodeConstants.GET_BUSSINESS,
		ex.getMessage());
}
	




/**
 * get Exception 
 * @param Exception
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException   updateReawrdServiceException(
		Exception ex) throws CloudServiceException {
	String METHOD_NAME = "updateReawrdServiceException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer.append("Exception occurred while update user profile")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.USER_UPADTE_PROFILE)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(ex.getMessage());

	log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
	throw new CloudServiceException(ErrorCodeConstants.USER_UPADTE_PROFILE,
			ex.getMessage());
}




/**
 * get Exception 
 * @param Exception
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException   updateReawrdException(
		Exception ex) throws CloudServiceException {
	String METHOD_NAME = "updateReawrdException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer.append("Exception occurred while update user profile")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.USER_UPADTE_PROFILE)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(ex.getMessage());

	log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
	throw new CloudServiceException(ErrorCodeConstants.USER_UPADTE_PROFILE,
			ex.getMessage());
}



private String flatDiscount( String discountValue,String amount)
{
	BigDecimal usrAmount = new BigDecimal(amount);
	BigDecimal discount = new BigDecimal(discountValue);
	BigDecimal discountAmount= usrAmount.subtract(discount);
	String finalAmount = discountAmount.toString();

	return formatAmount(finalAmount,discountAmount);
	
}







private String PercentageDiscount(String discountValue,String amount)
{
	BigDecimal usrAmount =new BigDecimal(amount);
	BigDecimal discount =new BigDecimal(discountValue);
	BigDecimal percent = usrAmount.multiply(discount).divide(new BigDecimal(100));
	
	BigDecimal discountAmount= usrAmount.subtract(percent);
	String finalAmount = discountAmount.toString();
	
    return formatAmount(finalAmount,discountAmount);

}



private String formatAmount(String finalAmount,BigDecimal discountAmount)
{
	if (finalAmount.contains(WebServiceConstants.DOT))
	{
		return finalAmount;
	}
	else{
		//finalAmount = finalAmount + "00";
		DecimalFormat df = new DecimalFormat(WebServiceConstants.AMOUNT_FORMAT);
		finalAmount =df.format(discountAmount);
		return finalAmount;
	}

	}

















	}