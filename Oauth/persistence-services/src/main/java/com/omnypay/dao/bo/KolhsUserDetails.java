package com.omnypay.dao.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="KOLHS_USER_DETAILS")
@NamedQuery(name = "KolhsUserDetails.findAll", query="SELECT c FROM KolhsUserDetails c")
public class KolhsUserDetails {	

	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "BUSINESS_ID")
	private String businessId;
	
	@Column(name = "CLIENT_USER_ID")
	private String clientUserId;
	
	@Column(name = "PHONE_NUMBER")
	private Long phonenumber;
	
	@Column(name = "EMAIL_ID")
	private String mailid;

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
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return the clientUserId
	 */
	public String getClientUserId() {
		return clientUserId;
	}

	/**
	 * @param clientUserId the clientUserId to set
	 */
	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	/**
	 * @return the phonenumber
	 */
	public Long getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the mailid
	 */
	public String getMailid() {
		return mailid;
	}

	/**
	 * @param mailid the mailid to set
	 */
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	

	

	
	
}
