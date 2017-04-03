package com.omnypay.business.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.DiscountService;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.Discount;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.dto.MerchantName;
import com.omnypay.dao.CouponDAO;
import com.omnypay.dao.DiscountDAO;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

public class DiscountServiceImpl implements DiscountService{

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	DiscountServiceImpl()
	{}

	/*DiscountServiceImpl(Discount discount)
	{
		
		this.discount = discount;
	}
	*/
	
	@Autowired
	DiscountDAO discountDAO;
	
	//Discount discount;
	
	
	

	
	/**
	 * use for getting coupon
	 * @param CloudSvrUser users object having all details to add an account
	 * @return String object having response
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
  public String getDiscountAmount(CloudSvrCoupon dbcoupon,String amount ) throws CloudServiceException
	{
		    CloudSvrDiscountType cloudSvrDiscountType = null;
			
			String METHOD_NAME = "getDiscountAmount";
			
			log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
			

			try {
				
				cloudSvrDiscountType =this.discountDAO.getDiscountType(dbcoupon);
				
				switch (Discount.valueOf(cloudSvrDiscountType.getDiscountType()))
				{
				case FLAT:
					
					return this.flatDiscount(cloudSvrDiscountType.getDiscountValue(),amount);
				
					
				case PERCENTAGE:
					
					return this.PercentageDiscount(cloudSvrDiscountType.getDiscountValue(),amount);
					
				default : return null;
					
				}
				
			} catch (CloudDAException accessException) {

				getDiscountCloudServiceException(accessException);
				
			} catch (Exception ex) {
				
				getDiscountException(ex);
			}
				
				String s=null;
			log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);

			return s;
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



/**
 * get cloud service Exception 
 * @param CloudDAException
 * @return CloudServiceException
 * @throws CloudServiceException 
 */
private CloudServiceException getDiscountCloudServiceException(
		CloudDAException accessException) throws CloudServiceException {
	String METHOD_NAME = "getDiscountCloudServiceException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer
			.append("DataAccessException occurred while getting discount ")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.GET_DISCOUNT)
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
private CloudServiceException getDiscountException(
		Exception ex) throws CloudServiceException {
	String METHOD_NAME = "getCouponException";
StringBuilder errorBuffer = new StringBuilder();
errorBuffer.append("Exception occurred while getting discount")
		.append(DaoConstants.COMMA).append("Error Code : ")
		.append(ErrorCodeConstants.GET_DISCOUNT)
		.append(DaoConstants.COMMA).append("Error Message : ")
		.append(ex.getMessage());

log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
throw new CloudServiceException(ErrorCodeConstants.GET_DISCOUNT,
		ex.getMessage());
}





}

	
	


