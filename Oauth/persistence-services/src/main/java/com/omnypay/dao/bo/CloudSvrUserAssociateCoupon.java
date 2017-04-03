package com.omnypay.dao.bo;

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
@Table(name="COUPON_ASSOCIATE_TO_USER")
@NamedQuery(name = "CloudSvrUserAssociateCoupon.findAll", query="SELECT c FROM CloudSvrUserAssociateCoupon c")
public class CloudSvrUserAssociateCoupon {

	
	
    @Id
	@Column(name = "COUPON_ID", nullable=false)
    //@GeneratedValue(generator = "gen")
   // @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value="userCoupon"))
	private long couponId;
	
	@Column(name = "REDEMPTION_DATE")
    private Timestamp redemptionDate;
	
	@Column(name = "USER_ID")
    private String userId;
	
	
/*	@ManyToOne
	@PrimaryKeyJoinColumn
	private CloudSvrCoupon userCoupon;*/
	
	
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

/*	*//**
	 * @return the redemptionDate
	 *//*
	public long getRedemptionDate() {
		return redemptionDate;
	}

	*//**
	 * @param redemptionDate the redemptionDate to set
	 *//*
	public void setRedemptionDate(long redemptionDate) {
		this.redemptionDate = redemptionDate;
	}
*/
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

/*	*//**
	 * @return the userCoupon
	 *//*
	public CloudSvrCoupon getUserCoupon() {
		return userCoupon;
	}

	*//**
	 * @param userCoupon the userCoupon to set
	 *//*
	public void setUserCoupon(CloudSvrCoupon userCoupon) {
		this.userCoupon = userCoupon;
	}
	*/
	
	
	
	
}
