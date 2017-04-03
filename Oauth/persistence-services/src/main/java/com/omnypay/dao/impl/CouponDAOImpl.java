package com.omnypay.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.omnypay.dao.CouponDAO;
import com.omnypay.dao.bo.CloudSvrCoupon;
import com.omnypay.dao.bo.CloudSvrCouponsRedemptionData;
import com.omnypay.dao.bo.CloudSvrDiscountType;
import com.omnypay.dao.bo.CloudSvrRewardPoint;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrUsersProfile;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;


@Repository("couponDAO")
public class CouponDAOImpl extends BaseDaoImpl implements CouponDAO{
	 
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	
	// constructor
	public CouponDAOImpl() {
	}

	
	
	/***
	 * to get user coupons
	 * 
	 * @param user CloudSvrUser object having all details of user
	 * @return CloudSvrCoupon object containing coupon details
	 * @throws CloudDAException
	 */
	@SuppressWarnings("unchecked")
	public List<CloudSvrCoupon> getUserCoupons(CloudSvrUser users) throws CloudDAException
	 {

	String METHOD_NAME = "getUserCoupons";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		List<CloudSvrCoupon> couponList = null;
		
		StringBuilder queryString  = this.getUserCouponQuery(users);
	 
		
		try {
			
			@SuppressWarnings("unused")
			Query query =super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(queryString.toString());
							
			
			couponList = (List<CloudSvrCoupon>)query.list();
			
			
		}  catch (DataAccessException accessException) {
			
			getCouponDaoDAException(accessException);
			
		} catch (Exception ex) {
			
			getCouponDaoException(ex);
			
			}
		
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return couponList;
	}
	
	
	
	
	
	
	

	/***
	 * to get user coupons based on Id
	 * 
	 * @param couponId String 
	 * @return CloudSvrCoupon object containing coupon details
	 * @throws CloudDAException
	 */
	@SuppressWarnings("unchecked")
	public CloudSvrCoupon getCouponsById(String couponId) throws CloudDAException
	 {

	String METHOD_NAME = "getUserCouponsById";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

	   CloudSvrCoupon couponDetail = null;
		
		StringBuilder queryString  = this.getCouponsByIdQuery(couponId);
	 
		
		try {
			
			@SuppressWarnings("unchecked")
			List<CloudSvrCoupon> couponDetails = (List<CloudSvrCoupon>) super
					.getHibernateTemplate().find(queryString.toString());
							
			if(couponDetails.size()!=0)
			{
				couponDetail = couponDetails.get(0);
			}
			
			
		}  catch (DataAccessException accessException) {
			
			getCouponDaoDAException(accessException);
			
		} catch (Exception ex) {
			
			getCouponDaoException(ex);
			
			}
		
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		return couponDetail;
	}
	
	
	
	
	

	/***
	 * to get user coupons based on Id
	 * 
	 * @param couponId
	 *            String
	 * @return CloudSvrCoupon object containing coupon details
	 * @throws CloudDAException 
	 */
	public boolean setCouponRedemptionData(CloudSvrCouponsRedemptionData couponRedData,String userId) throws CloudDAException {

		String METHOD_NAME = "setCouponRedemptionData";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		
		boolean isRedemption = false;

		try {
			couponRedData.setUserId(userId);
			couponRedData.setCouponStatus(DaoConstants.YES);

			super.getHibernateTemplate().saveOrUpdate(couponRedData);

			isRedemption = true;

		} catch (DataAccessException accessException) {

			getCouponDaoDAException(accessException);

		} catch (Exception ex) {

			getCouponDaoException(ex);

		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return isRedemption;
	}



	
	
	

	/***
	 * to get user coupons based on Id
	 * 
	 * @param couponId
	 *            String
	 * @return CloudSvrCoupon object containing coupon details
	 * @throws CloudDAException
	 */
	public CloudSvrDiscountType getDiscountTypeByDiscountId(Long dicountId)
			throws CloudDAException {

		String METHOD_NAME = "getDiscountType";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrDiscountType discountType = null;

		StringBuilder queryString = this.getDiscountTypeByIdQuery(dicountId);

		try {
			@SuppressWarnings("unchecked")
			List<CloudSvrDiscountType> discountTypes = (List<CloudSvrDiscountType>) super
					.getHibernateTemplate().find(queryString.toString());
			if (discountTypes.size() != 0) {
				discountType = discountTypes.get(0);
			}

		} catch (DataAccessException accessException) {

			getCouponDaoDAException(accessException);

		} catch (Exception ex) {

			getCouponDaoException(ex);

		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return discountType;
		
	}
	
	
	
	
	
	
	
	
	
	

	/***
	 * to get user coupons based on Id
	 * 
	 * @param couponId
	 *            String
	 * @return CloudSvrCoupon object containing coupon details
	 * @throws CloudDAException
	 */
	public CloudSvrCouponsRedemptionData getCouponsRedempById(String dicountId)
			throws CloudDAException {

		String METHOD_NAME = "getCouponsRedempById";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		CloudSvrCouponsRedemptionData redemptionData = null;

		StringBuilder queryString = this.getCouponsRedempByIdQuery(dicountId);

		try {
			@SuppressWarnings("unchecked")
			List<CloudSvrCouponsRedemptionData> redemptionDatas = (List<CloudSvrCouponsRedemptionData>) super
					.getHibernateTemplate().find(queryString.toString());
			if (redemptionDatas.size() != 0) {
				redemptionData = redemptionDatas.get(0);
			}

		} catch (DataAccessException accessException) {

			getCouponDaoDAException(accessException);

		} catch (Exception ex) {

			getCouponDaoException(ex);

		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return redemptionData;
		
	}
	
	
	
	
	
	
	
	
	/***
	 * to user Coupon quary
	 * 
	 * @param user  CloudSvrUser  object having all details to get Coupon quary
	 * @return string represent the query
	 * 
	 */	
	private StringBuilder getUserCouponQuery(CloudSvrUser user){
		
		
		//StringBuilder queryString = new StringBuilder("from CloudSvrCoupon  as c left join CloudSvrUserAssociateCoupon  as u on c.couponId = u.couponId " );
		//StringBuilder queryString = new StringBuilder("select c from CloudSvrCoupon c, CloudSvrUserCoupon uc , CloudSvrCouponsRedemptionData ucr where uc.userId= '"+user.getUserId() + "' and uc.couponId = c.couponId and c.merchantId = '"+user.getBusinessId()+"' and ucr.redemptionCount <= c.maxRedemption" );
		StringBuilder queryString = new StringBuilder("select c from CloudSvrCoupon c, CloudSvrUserCoupon uc  where uc.userId= '"+user.getUserId() + "' and uc.couponId = c.couponId and c.merchantId = '"+user.getBusinessId()+"'" );
		//StringBuilder queryString = new StringBuilder("select c , uc from CloudSvrCoupon c, CloudSvrUserCoupon uc where uc.userId= '"+user.getUserId() + "' and uc.couponId = c.couponId and c.merchantId = '"+user.getBusinessId()+"'" );		
				
				/*if(user.getUserId() != null) {
					
					queryString.append(" and u.userId = '").append(user.getUserId()).append("'");
				} 
		
		
				if(user.getBusinessId() != null) {
					queryString.append(" where c.merchantId = '").append(user.getBusinessId()).append("'");
					
				}*/
				// need to add 
				//queryString.append(" and c.couponId not in");
				//queryString.append("(Select uc1.couponId from CloudSvrUserCoupon uc1 where ");
				//queryString.append(" uc1.userId != '").append(user.getUserId()).append("'").append(")");
				
			
				
				
		
	  return queryString;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * to user getCouponsByIdQuery
	 * 
	 * @param String  having couponID
	 * @return StringBuilder represent the query
	 * 
	 */	
	private StringBuilder getCouponsByIdQuery(String couponId) {

		StringBuilder queryString =null;

		if (couponId != null) {
			
			 queryString = new StringBuilder("from CloudSvrCoupon c ").append(" where   c.couponId =").append(couponId);

		}

		return queryString;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	/***
	 * to get user rewards
	 * 
	 * @param rewardBo CloudSvrRewardPoint object having all details of rewards
	 * @return CloudSvrRewardPoint object containing reward details
	 * @throws CloudDAException
	 */
	public CloudSvrRewardPoint  getUserRewards(CloudSvrUser user)
			throws CloudDAException

	{
		String METHOD_NAME = "getUserRewards";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		String rewardQuery =null;
		CloudSvrRewardPoint rewardpoint = null;
		
			 rewardQuery = this.getUserRewardQuery(user);
		
		try {

			@SuppressWarnings("unchecked")
			List<CloudSvrRewardPoint> rewardinfos = (List<CloudSvrRewardPoint>) super
					.getHibernateTemplate().find(rewardQuery);

			if (rewardinfos.size()!= 0) {

				rewardpoint = rewardinfos.get(0);
				
			
			// in this case user dont have any reward point 	

			} else {
				
				rewardpoint = new CloudSvrRewardPoint();
				rewardpoint.setRewardPontTotal(0l);
				
			}

		} catch (DataAccessException accessException) {

			getRewardDaoDAException(accessException);

		} catch (Exception ex) {

			getRewardDaoException(ex);

		}

		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return rewardpoint;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * to getUser Reward Query
	 * 
	 * @param user  CloudSvrUser  object having all details to get Reward quary
	 * @return string represent the query
	 * 
	 */	
	private String getUserRewardQuery(CloudSvrUser user) {

		StringBuilder queryString = new StringBuilder("from CloudSvrRewardPoint c where  c.userId = '").append(user.getUserId()).append("'");

		
		return queryString.toString();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * to update rewards
	 * 
	 * @param user CloudSvrUser object having all details about user
	 * @throws CloudDAException
	 * 
	 */
	public void updateUserRewardsDAO(CloudSvrRewardPoint rewardPoint) throws CloudDAException {
		String METHOD_NAME = "updateUserRewardsDAO";

		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		

		try {
			
			super.getHibernateTemplate().saveOrUpdate(rewardPoint);
			
		} catch (DataAccessException accessException) {

			updateRewardDaoDAException(accessException);

		} catch (Exception ex) {
			
			updateRewardDaoException(ex);
		}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
	}
	
	
	
	
	
	
	
	
	// ///////////////////private methods for exception////////////////////////

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getCouponDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getCouponDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting coupons ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_COUPON)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_COUPON,
				accessException.getMessage());

	}

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getCouponDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "getCouponDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while getting coupons ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_COUPON)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_COUPON,
				ex.getMessage());

	}
	
	
	
	
	
	/**
	 * getRewardDaoDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getRewardDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "getRewardDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("DataAccessException occurred while getting rewards ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_REWARD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_REWARD,
				accessException.getMessage());

	}

	/**
	 * getRewardDaoException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException getRewardDaoException(Exception ex)
			throws CloudDAException {
		String METHOD_NAME = "getRewardDaoException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while getting rewards ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.GET_REWARD)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudDAException(ErrorCodeConstants.GET_REWARD,
				ex.getMessage());

	}
	
	
	
	

	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  updateRewardDaoDAException(
			DataAccessException accessException) throws CloudDAException {
		String METHOD_NAME = "updateRewardDaoDAException";
		StringBuilder errorBuffer = new StringBuilder();
				errorBuffer
				.append("DataAccessException occurred while update reward")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.UPDATE_REWARD,
				accessException.getMessage());
		
	}
	
	
	
	
	/**
	 * get CloudDAException
	 * 
	 * @param DataAccessException
	 * @return CloudDAException
	 * @throws CloudDAException
	 */
	private CloudDAException  updateRewardDaoException(
			Exception accessException) throws CloudDAException {
		String METHOD_NAME = "updateRewardDaoException";
		StringBuilder errorBuffer = new StringBuilder();
				errorBuffer
				.append("Exception occurred while update rewards ")
				.append(DaoConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.USER_UPADTE)
				.append(DaoConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudDAException(
				ErrorCodeConstants.USER_UPADTE,
				accessException.getMessage());
		
	}



	/***
	 * to user getDiscountTypeByIdQuery
	 * 
	 * @param String  having couponID
	 * @return StringBuilder represent the query
	 * 
	 */	
	private StringBuilder getDiscountTypeByIdQuery(Long  dicountId) {

		StringBuilder queryString = new StringBuilder(
				"from CloudSvrDiscountType c where ").append(" c.discountId =").append(dicountId);


		return queryString;

	}

	
	
	

	/***
	 * to user getDiscountTypeByIdQuery
	 * 
	 * @param String  having couponID
	 * @return StringBuilder represent the query
	 * 
	 */	
	private StringBuilder getCouponsRedempByIdQuery(String  couponId) {

		StringBuilder queryString = new StringBuilder(
				"from CloudSvrCouponsRedemptionData c where ").append(" c.couponId =").append(couponId);


		return queryString;

	}
	
}
