package org.omnypay.mobileapp.webservices.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.CouponRewardsWebService;
import org.omnypay.mobileapp.webservices.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.CommonService;
import com.omnypay.business.service.CouponService;
import com.omnypay.business.service.UserService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.CouponRewardHelper;
import com.omnypay.common.services.SecurityQuestionHelper;
import com.omnypay.common.services.UserHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.CouponSubfieldDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.MerchantAccessDTO;
import com.omnypay.common.services.dto.RespSubFieldsDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.RewardSubfieldDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrRewardPoint;
import com.omnypay.dao.bo.CloudSvrSecQuesMaster;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.merchant.services.MerchantCouponServices;
import com.omnypay.merchant.services.MerchantUserServices;
import com.sun.jersey.spi.resource.Singleton;

/**
 * 
 * @author sushreesmita
 *
 */

@Component
@Path("/coupon")
public class CouponRewardsWebServiceImpl  implements CouponRewardsWebService {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	/*
	 * // constructor public CouponWebServiceImpl() { }
	 */
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private MerchantCouponServices merchantCouponService;
	

	/**
	 * 
	 * getUserCoupons request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * about user Coupons and other details. send both success and failure
	 * message to the mobile
	 * 
	 * @param RewardDTO
	 *            object containing all the parameters send from mobile of a
	 *            user
	 * @return response object to the mobile regarding user coupons
	 * @see org.omnypay.mobileapp.webservices.CouponRewardsWebService#getUserCoupons(org.omnypay
	 *      .mobileapp.dto.RewardDTO)
	 * 
	 * 
	 * 
	 */

	@Path("/getUserCoupons")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserCoupon(BaseDTO baseDTO) {

		String METHOD_NAME = "getUserCoupon";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		CouponRespDTO status = null;
		
		List<CloudSvrCoupon> cloudSvrCoupon = null;
		
		Response response = null;
		
		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(baseDTO)) {
				
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(baseDTO.getMerchantAccessKey());

				if (merchantDTO !=null && merchantDTO.getIsExternal()) {

					status = merchantCouponService.getUserCoupon(merchantDTO, baseDTO);
			
					//for internal merchant
				} else 	if (merchantDTO !=null && (!merchantDTO.getIsExternal())) {
					
					
					cloudSvrCoupon = couponService.getUserCoupons(baseDTO);
					 
					status = CouponRewardHelper.fetchCoupons(cloudSvrCoupon);
					
				}

				//String bussinessId = couponService.getBussinessId(rewardDTO
					//	.getMerchantAccessKey());

				//CloudSvrUser user = this.getUser(rewardDTO, bussinessId);

			//	if (bussinessId != null && user != null) {
					// uncomment when query will run and data will come from db
					
					
				} else {
					status = CloudServiceStatus
							.coupontStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_COUPON));
				}

		//	}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.coupontStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException occured while getting coupons";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.coupontStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_COUPON));

			String message = "Exception occured while  getting coupons";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		}

		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}

	
	
	
	
	

	/**
	 * 
	 * getUserReward request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * about user rewards and other details. send both success and failure
	 * message to the mobile
	 * 
	 * @param RewardDTO
	 *            object containing all the parameters send from mobile of a
	 *            user
	 * @return response object to the mobile regarding user Rewards
	 * @see org.omnypay.mobileapp.webservices.CouponRewardsWebService#getUserRewards(org.omnypay
	 *      .mobileapp.dto.RewardDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/cc")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response calCoupons(CouponDTO couponDTO) {
		
	
		String METHOD_NAME = "calCoupons";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,METHOD_NAME);

		DiscountRespDTO status = null;
		
		CouponDTO couponDto = null;
		
		Response response = null;
		
		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(couponDTO) && CouponRewardHelper.checkMandatoryFieldsCoupon(couponDTO)) {
				
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(couponDTO.getMerchantAccessKey());

				if (merchantDTO !=null && merchantDTO.getIsExternal()) {

					status = merchantCouponService.calCoupons(merchantDTO, couponDTO);
			
					//for internal merchant
				} else 	if (merchantDTO !=null && (!merchantDTO.getIsExternal())) {
					
					
					couponDto = couponService.calCoupons(couponDTO);
					 
					 status = CouponRewardHelper.getDiscountStatus(couponDto);
					
				}

			
					
					
				} else {
					status = CloudServiceStatus
							.discountStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_COUPON));
				}

		//	}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.discountStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException occured while getting coupons";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.discountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_COUPON));

			String message = "Exception occured while  getting coupons";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		}

		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * getUserReward request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * about user rewards and other details. send both success and failure
	 * message to the mobile
	 * 
	 * @param RewardDTO
	 *            object containing all the parameters send from mobile of a
	 *            user
	 * @return response object to the mobile regarding user Rewards
	 * @see org.omnypay.mobileapp.webservices.CouponRewardsWebService#getUserRewards(org.omnypay
	 *      .mobileapp.dto.RewardDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/getUserRewards")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserReward(BaseDTO baseDTO) {

		String METHOD_NAME = "getUserReward";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,METHOD_NAME);

		RewardRespDTO status = null;

		Response response = null;
		
		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(baseDTO)) {
				
				
				
				MerchantAccessDTO merchantDTO =	commonService.isAccessKeyForMerchantExistService(baseDTO.getMerchantAccessKey());

				if (merchantDTO !=null && merchantDTO.getIsExternal()) {

					status = merchantCouponService.getUserReward(merchantDTO, baseDTO);
			
					//for internal merchant
				} else 	if (merchantDTO !=null && (!merchantDTO.getIsExternal())) {
					
				
					CloudSvrRewardPoint dbRewardInfo = couponService.getUserRewards(baseDTO);
					
					status = CouponRewardHelper.fetchRewards(dbRewardInfo);
					
					
				}
					

				//String bussinessId = couponService.getBussinessId(rewardDTO
				//		.getMerchantAccessKey());

				//CloudSvrUser user = this.getUser(rewardDTO, bussinessId);

				//if (user.getUserId() != null) {

						
			
				
				} else {

					status = CloudServiceStatus
							.rewardStatus(CloudServiceStatus
									.getStatusByErrorCode(ErrorCodeConstants.GET_REWARD));
				}

			//}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.rewardStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException occured while getting rewards";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.rewardStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_REWARD));

			String message = "Exception occured while  getting rewards";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		}

		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * updateUserRewards request from mobile send response back to mobile
	 * Returns response object that will send to the mobile containing all the
	 * details about user rewards and other details. send both success and
	 * failure message to the mobile
	 * 
	 * @param RewardDTO
	 *            object containing all the parameters send from mobile of a
	 *            user
	 * @return response object to the mobile regarding user Rewards updated
	 * @see org.omnypay.mobileapp.webservices.CouponRewardsWebService#getUserRewards(org.omnypay
	 *      .mobileapp.dto.RewardDTO)
	 * 
	 * 
	 * 
	 */
	@Path("/cr")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response calReward(RewardDTO rewardDTO) {

		String METHOD_NAME = "calReward";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		Status status = null;

		RewardDTO rewardDto = null;

		Response response = null;
		try {

			// check base fields
			if (WebServiceUtil.baseCheckMandatoryFields(rewardDTO)
					&& CouponRewardHelper.checkMandatoryFieldsReward(rewardDTO)) {

				MerchantAccessDTO merchantDTO = commonService
						.isAccessKeyForMerchantExistService(rewardDTO
								.getMerchantAccessKey());

				if (merchantDTO != null && merchantDTO.getIsExternal()) {

					status = merchantCouponService.calReward(merchantDTO, rewardDTO);

					// for internal merchant
				} else if (merchantDTO != null
						&& (!merchantDTO.getIsExternal())) {

					rewardDto = couponService.calReward(rewardDTO);

					status = CouponRewardHelper.calcRewardStatus(rewardDto);

				}

			} else {

				status = CloudServiceStatus
						.getStatusByErrorCode(ErrorCodeConstants.CALCULATION_REWARD);
			}

		} catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus
					.getStatusByException(cloudServiceException);

			String message = "businessException occured while update/Add rewards";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.UPDATE_REWARD);

			String message = "Exception occured while update/Add rewards";

			CloudServiceExceptionLogger.LogCloudServiceException(ex,
					CLASS_NAME, METHOD_NAME, message, null);

		}

		finally {

			CloudServiceExceptionLogger.LogCloudServiceExecuted(CLASS_NAME,
					METHOD_NAME);
			response = Response.status(javax.ws.rs.core.Response.Status.OK)
					.entity(status).build();

		}

		return response;

	}
	
	
	
	
	

	
	
	
	



	
	
	
	
	
	
	
	
	
	
	








	
	
	
	
	
	
	
	
	
	


}
