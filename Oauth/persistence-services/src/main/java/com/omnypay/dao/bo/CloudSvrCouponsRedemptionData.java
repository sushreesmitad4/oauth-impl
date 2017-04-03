/**
 * 
 */
package com.omnypay.dao.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author iliyasm
 *
 */

@Entity
@Table(name="COUPON_REDEMPTION_DATA")
@NamedQuery(name = "CloudSvrCouponsRedemptionData.findAll", query="SELECT c FROM CloudSvrCouponsRedemptionData c")
public class CloudSvrCouponsRedemptionData {
	
	
	@Id
	@Column(name = "COUPON_REDEMPTION_ID")
 	@GeneratedValue
 	private long couponRedId;
	

	@Column(name = "USER_ID")
    private String userId;
	
	@Column(name = "COUPON_ID")
    private String couponId;
	
	
	@Column(name = "COUPON_STATUS")
    private String couponStatus;
	
	
	@Column(name = "REDEMPTION_COUNT")
    private long redemptionCount;


	/**
	 * @return the couponRedId
	 */
	public long getCouponRedId() {
		return couponRedId;
	}


	/**
	 * @param couponRedId the couponRedId to set
	 */
	public void setCouponRedId(long couponRedId) {
		this.couponRedId = couponRedId;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the couponId
	 */
	public String getCouponId() {
		return couponId;
	}


	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}


	/**
	 * @return the couponStatus
	 */
	public String getCouponStatus() {
		return couponStatus;
	}


	/**
	 * @param couponStatus the couponStatus to set
	 */
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}


	/**
	 * @return the redemptionCount
	 */
	public long getRedemptionCount() {
		return redemptionCount;
	}


	/**
	 * @param redemptionCount the redemptionCount to set
	 */
	public void setRedemptionCount(long redemptionCount) {
		this.redemptionCount = redemptionCount;
	}


	
	
	
	
	

}
