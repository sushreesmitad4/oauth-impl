/*package org.omnypay.mobileapp.webservices.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.omnypay.mobileapp.util.CloudServiceExceptionLogger;
import org.omnypay.mobileapp.util.CloudServiceStatus;
import org.omnypay.mobileapp.webservices.DiscountWebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.business.service.CouponService;
import com.omnypay.business.service.DiscountService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.CouponHelper;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.CouponSubfieldDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
*//**
 * 
 * @author sushreesmita
 *
 *//*


@Component
@Path("/discount")
public class DiscountWebserviceImpl implements  DiscountWebservice{

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private CouponService couponService;
	
	
	*//**
	 * 
	 * getDiscounts request from mobile send response back to mobile Returns
	 * response object that will send to the mobile containing all the details
	 * about user discount amount. send both success and failure
	 * message to the mobile
	 * 
	 * @param CouponSubfieldDTO
	 *            object containing couponId and the amount for getting discount
	 *            
	 * @return response object to the mobile regarding discount amount
	 * @see org.omnypay.mobileapp.webservices.DiscountWebservice#getDiscounts(org.omnypay
	 *      .mobileapp.dto.CouponSubfieldDTO)
	 * 
	 * 
	 * 
	 *//*
	
	@Path("/getDiscounts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiscountsAmount(CouponSubfieldDTO couponSubDTO)
	{

		String METHOD_NAME = "getDiscountsAmount";

		CloudServiceExceptionLogger.LogCloudServiceExecuting(CLASS_NAME,
				METHOD_NAME);

		DiscountRespDTO status = null;
		
		Response response = null;
		try {

			// check mandatory fields
			if (CouponHelper.checkMandatoryFieldsCouponDiscount(couponSubDTO)) {


          String amount = this.getDiscountAmount(couponSubDTO);
          
          status = this.getDiscountStatus(amount);
			}

		} 
			catch (CloudServiceException cloudServiceException) {

			status = CloudServiceStatus.discountStatus(CloudServiceStatus
					.getStatusByException(cloudServiceException));

			String message = "businessException occured while getting discount";

			CloudServiceExceptionLogger.LogCloudServiceException(
					cloudServiceException, CLASS_NAME, METHOD_NAME, message,
					null);

		} catch (Exception ex) {

			status = CloudServiceStatus
					.discountStatus(CloudServiceStatus
							.getStatusByErrorCode(com.omnypay.log.ErrorCodeConstants.GET_DISCOUNT));

			String message = "Exception occured while  getting discount";

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

		








	*//***
	 * to get user deatils
	 * 
	 * @param couponSubDTO
	 * @return String object
	 * @throws CloudServiceException
	 *//*
	private String getDiscountAmount(CouponSubfieldDTO couponSubDTO)
			throws CloudServiceException {

		CloudSvrCoupon dbcoupon = couponService.getCouponsById(couponSubDTO
				.getCouponId());
          
		String amount = discountService.getDiscountAmount(dbcoupon,	couponSubDTO.getAmount());
		return amount;

	}



	private DiscountRespDTO getDiscountStatus(String amount) {
		DiscountRespDTO status = null;

		if (amount != null) {
			// get status to send to client
			status = (DiscountRespDTO) WebServiceUtil.getDiscountResp(
					WebServiceConstants.DISCOUNT_SUCCESS,
					WebServiceConstants.ONE, amount);
		} else {
			status = (DiscountRespDTO) WebServiceUtil.getDiscountResp(
					WebServiceConstants.DISCOUNT_FAIELD,
					WebServiceConstants.TWO);
		}

		return status;

	}

}*/