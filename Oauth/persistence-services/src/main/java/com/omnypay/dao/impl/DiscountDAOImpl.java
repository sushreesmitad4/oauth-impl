package com.omnypay.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.omnypay.dao.DiscountDAO;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;


@Repository("discountDAO")
public class DiscountDAOImpl extends BaseDaoImpl implements DiscountDAO{

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	
	// constructor
	public DiscountDAOImpl() {
	}

	
  public CloudSvrDiscountType getDiscountType(CloudSvrCoupon dbcoupon ) throws CloudDAException
  {
	  
	  
	  String METHOD_NAME = "getDiscountType";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		CloudSvrDiscountType discountType = null;
		
		StringBuilder queryString  = this.getDiscountTypeByIdQuery(dbcoupon);
	 
		
		try {
			@SuppressWarnings("unchecked")
			List<CloudSvrDiscountType> discountTypes = (List<CloudSvrDiscountType>) super
					.getHibernateTemplate().find(queryString.toString());
			if(discountTypes.size()!= 0)
			{
				discountType = discountTypes.get(0);
			}
			
			
		}  catch (DataAccessException accessException) {
			
			getDiscountDaoDAException(accessException);
			
		} catch (Exception ex) {
			
			getDiscountDaoException(ex);
			
			}
		
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return discountType;
	}
	
	
	  
	
	 
	
/***
 * to user getDiscountTypeByIdQuery
 * 
 * @param String  having couponID
 * @return StringBuilder represent the query
 * 
 */	
private StringBuilder getDiscountTypeByIdQuery(CloudSvrCoupon dbcoupon) {

	StringBuilder queryString = new StringBuilder(
			"from CloudSvrDiscountType c where 1=1");

	if (dbcoupon != null && dbcoupon.getDiscountId()!=0) {
		queryString.append(" and c.discountId =").append(dbcoupon.getDiscountId());

	}

	return queryString;

}




/**
 * get CloudDAException
 * 
 * @param DataAccessException
 * @return CloudDAException
 * @throws CloudDAException
 */
private CloudDAException getDiscountDaoDAException(
		DataAccessException accessException) throws CloudDAException {
	String METHOD_NAME = "getCouponDaoDAException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer
			.append("DataAccessException occurred while getting discount ")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.GET_DISCOUNT)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(accessException.getMessage());
	log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
	throw new CloudDAException(ErrorCodeConstants.GET_DISCOUNT,
			accessException.getMessage());

}

/**
 * get CloudDAException
 * 
 * @param DataAccessException
 * @return CloudDAException
 * @throws CloudDAException
 */
private CloudDAException getDiscountDaoException(Exception ex)
		throws CloudDAException {
	String METHOD_NAME = "getCouponDaoException";
	StringBuilder errorBuffer = new StringBuilder();
	errorBuffer.append("Exception occurred while getting discount ")
			.append(DaoConstants.COMMA).append("Error Code : ")
			.append(ErrorCodeConstants.GET_DISCOUNT)
			.append(DaoConstants.COMMA).append("Error Message : ")
			.append(ex.getMessage());
	log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
	throw new CloudDAException(ErrorCodeConstants.GET_DISCOUNT,
			ex.getMessage());

}



		
}
