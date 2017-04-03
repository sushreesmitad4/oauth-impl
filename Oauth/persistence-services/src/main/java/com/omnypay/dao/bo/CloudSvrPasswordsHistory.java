package com.omnypay.dao.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the CLOUD_SVR_PASSWORDS_HISTORY database table.
 * 
 */
@Entity
@Table(name = "CLOUD_SVR_PASSWORDS_HISTORY")
@NamedQuery(name = "CloudSvrPasswordsHistory.findAll", query = "SELECT c FROM CloudSvrPasswordsHistory c")
public class CloudSvrPasswordsHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "USER_PASSWORD_ID", nullable=false)
	private long userPasswordId;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PWD_CHANGE_TYPE")
	private String pwdChangeType;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable=false)
	private CloudSvrUser user;
	
	


	/**
	 * @return the userPasswordId
	 */
	public long getUserPasswordId() {
		return userPasswordId;
	}


	/**
	 * @param userPasswordId the userPasswordId to set
	 */
	public void setUserPasswordId(long userPasswordId) {
		this.userPasswordId = userPasswordId;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	public String getPwdChangeType() {
		return pwdChangeType;
	}


	public void setPwdChangeType(String pwdChangeType) {
		this.pwdChangeType = pwdChangeType;
	}


	public Timestamp getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	public CloudSvrUser getUser() {
		return user;
	}


	public void setUser(CloudSvrUser user) {
		this.user = user;
	}

    
	

	

}