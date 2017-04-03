package com.omnypay.dao.bo;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="USER_COUPON")
@NamedQuery(name = "CloudSvrUserCoupon.findAll", query="SELECT c FROM CloudSvrUserCoupon c")
public class CloudSvrUserCoupon {

	
	
    @Id
	@Column(name = "USER_COUPON_ID", nullable=false)
 	private long userCouponId;
    
	
	@Column(name = "REDEMPTION_DATE")
    private Date redemptionDate;
	
	
	@Column(name = "USER_ID")
    private String userId;
	
	
	@Column(name = "COUPON_ID")
    private long couponId;
	
	
	
	@Column(name = "MAX_REDEMPTION")
    private long maxRedemption;

	
	
	/**
	 * @return the couponId
	 */
	public long getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(long couponId) {
		this.couponId = couponId;
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
	 * @return the userCouponId
	 */
	public long getUserCouponId() {
		return userCouponId;
	}

	/**
	 * @param userCouponId the userCouponId to set
	 */
	public void setUserCouponId(long userCouponId) {
		this.userCouponId = userCouponId;
	}

	/**
	 * @return the redemptionDate
	 */
	public Date getRedemptionDate() {
		return redemptionDate;
	}

	/**
	 * @param redemptionDate the redemptionDate to set
	 */
	public void setRedemptionDate(Date redemptionDate) {
		this.redemptionDate = redemptionDate;
	}

	/**
	 * @return the maxRedemption
	 */
	public long getMaxRedemption() {
		return maxRedemption;
	}

	/**
	 * @param maxRedemption the maxRedemption to set
	 */
	public void setMaxRedemption(long maxRedemption) {
		this.maxRedemption = maxRedemption;
	}


	


	
	
	
	
	
}
