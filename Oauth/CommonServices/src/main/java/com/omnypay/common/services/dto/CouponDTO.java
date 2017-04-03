/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class CouponDTO  extends BaseDTO{
	
	
	private String couponId;
	
	
	private String totalAmount;
	
	
	private String rewardId;
		 
	private String expireDate;
			
	private String rewardPontTotal;
	
	
	


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
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}


	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}


	/**
	 * @return the rewardId
	 */
	public String getRewardId() {
		return rewardId;
	}


	/**
	 * @param rewardId the rewardId to set
	 */
	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}


	/**
	 * @return the expireDate
	 */
	public String getExpireDate() {
		return expireDate;
	}


	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}


	/**
	 * @return the rewardPontTotal
	 */
	public String getRewardPontTotal() {
		return rewardPontTotal;
	}


	/**
	 * @param rewardPontTotal the rewardPontTotal to set
	 */
	public void setRewardPontTotal(String rewardPontTotal) {
		this.rewardPontTotal = rewardPontTotal;
	}
	
	
	
	
	

}
