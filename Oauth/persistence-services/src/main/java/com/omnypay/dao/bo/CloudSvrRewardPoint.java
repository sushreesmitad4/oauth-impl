package com.omnypay.dao.bo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author sushreesmita
 *
 */
@Entity
@Table(name="USER_REWARD_POINT")
@NamedQuery(name = "CloudSvrRewardPoint.findAll", query="SELECT c FROM CloudSvrRewardPoint c")
public class CloudSvrRewardPoint {

	
	 @Id
	 @Column(name = "REWARD_ID")
	 @GeneratedValue
	 private long rewardId;
	

	@Column(name = "REWARD_POINT_TOTAL")
    private long rewardPontTotal;
	
	@Column(name = "REWARD_POINT_PENDING")
    private long rewardPontPending;
	
	
	@Column(name = "REWARD_POINT_ACTIVE")
    private long rewardPontActive;
	
	
	@Column(name = "REWARD_POINT_EXPIRE")
    private long rewardPontExpire;
	
	@Column(name = "REWARD_POINT_CONVERTED")
    private long rewardPontConverted;
	
	@Column(name = "REWARD_POINT_CANCELLED")
    private long rewardPontCancelled;
	
	@Column(name = "ACTIVATION_DATE")
	private Timestamp activationDate;
		
	@Column(name = "EXPIRE_DATE")
	private Timestamp expireDate;

	
	
	@Column(name = "USER_ID")
	private String userId;
	
	/**
	 * @return the rewardId
	 */
	public long getRewardId() {
		return rewardId;
	}

	/**
	 * @param rewardId the rewardId to set
	 */
	public void setRewardId(long rewardId) {
		this.rewardId = rewardId;
	}

	/**
	 * @return the rewardPontTotal
	 */
	public long getRewardPontTotal() {
		return rewardPontTotal;
	}

	/**
	 * @param rewardPontTotal the rewardPontTotal to set
	 */
	public void setRewardPontTotal(long rewardPontTotal) {
		this.rewardPontTotal = rewardPontTotal;
	}

	/**
	 * @return the rewardPontPending
	 */
	public long getRewardPontPending() {
		return rewardPontPending;
	}

	/**
	 * @param rewardPontPending the rewardPontPending to set
	 */
	public void setRewardPontPending(long rewardPontPending) {
		this.rewardPontPending = rewardPontPending;
	}

	/**
	 * @return the rewardPontActive
	 */
	public long getRewardPontActive() {
		return rewardPontActive;
	}

	/**
	 * @param rewardPontActive the rewardPontActive to set
	 */
	public void setRewardPontActive(long rewardPontActive) {
		this.rewardPontActive = rewardPontActive;
	}

	/**
	 * @return the rewardPontExpire
	 */
	public long getRewardPontExpire() {
		return rewardPontExpire;
	}

	/**
	 * @param rewardPontExpire the rewardPontExpire to set
	 */
	public void setRewardPontExpire(long rewardPontExpire) {
		this.rewardPontExpire = rewardPontExpire;
	}

	/**
	 * @return the rewardPontConverted
	 */
	public long getRewardPontConverted() {
		return rewardPontConverted;
	}

	/**
	 * @param rewardPontConverted the rewardPontConverted to set
	 */
	public void setRewardPontConverted(long rewardPontConverted) {
		this.rewardPontConverted = rewardPontConverted;
	}

	/**
	 * @return the rewardPontCancelled
	 */
	public long getRewardPontCancelled() {
		return rewardPontCancelled;
	}

	/**
	 * @param rewardPontCancelled the rewardPontCancelled to set
	 */
	public void setRewardPontCancelled(long rewardPontCancelled) {
		this.rewardPontCancelled = rewardPontCancelled;
	}

	/**
	 * @return the activationDate
	 */
	public Timestamp getActivationDate() {
		return activationDate;
	}

	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(Timestamp activationDate) {
		this.activationDate = activationDate;
	}

	/**
	 * @return the expireDate
	 */
	public Timestamp getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
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
	
	
	
	
	
	
}
