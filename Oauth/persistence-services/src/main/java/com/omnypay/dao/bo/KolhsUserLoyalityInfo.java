package com.omnypay.dao.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="KOLHS_USER_LOYALTY_INFO")
@NamedQuery(name = "KolhsUserLoyalityInfo.findAll", query="SELECT c FROM KolhsUserLoyalityInfo c")
public class KolhsUserLoyalityInfo {
	
		
	@Column(name = "USER_ID")
	private String userId;
	
	@Id
	@Column(name = "LOYALTY_ID")
	private String loyalityId;
	
	@Column(name = "LOYALTY_NAMES")
	private String loyalityNames;
	
	
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
	 * @return the loyalityId
	 */
	public String getLoyalityId() {
		return loyalityId;
	}
	/**
	 * @param loyalityId the loyalityId to set
	 */
	public void setLoyalityId(String loyalityId) {
		this.loyalityId = loyalityId;
	}
	/**
	 * @return the loyalityNames
	 */
	public String getLoyalityNames() {
		return loyalityNames;
	}
	/**
	 * @param loyalityNames the loyalityNames to set
	 */
	public void setLoyalityNames(String loyalityNames) {
		this.loyalityNames = loyalityNames;
	}
	

	
	
	
	
	
}
