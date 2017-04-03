package com.omnypay.dao.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the CLOUD_SVR_LOGIN_HISTORY database table.
 * 
 */
@Entity
@Table(name = "CLOUD_SVR_LOGIN_HISTORY")
@NamedQuery(name = "CloudSvrLoginHistory.findAll", query = "SELECT c FROM CloudSvrLoginHistory c")
public class CloudSvrLoginHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LOGIN_HIST_ID")
	private long loginHistId;

	@Column(name = "ACCESS_TIME")
	private Object accessTime;

	@Column(name = "ACTION_PERFORMED")
	private String actionPerformed;

	@Column(name = "CREATED_BY")
	private BigDecimal createdBy;

	@Column(name = "CREATED_DATE")
	private Object createdDate;

	@Column(name = "SOURCE_IP")
	private String sourceIp;

	@Column(name = "USER_AGENT")
	private String userAgent;

	@Column(name = "USR_ID")
	private BigDecimal usrId;

	public CloudSvrLoginHistory() {
	}

	public long getLoginHistId() {
		return this.loginHistId;
	}

	public void setLoginHistId(long loginHistId) {
		this.loginHistId = loginHistId;
	}

	public Object getAccessTime() {
		return this.accessTime;
	}

	public void setAccessTime(Object accessTime) {
		this.accessTime = accessTime;
	}

	public String getActionPerformed() {
		return this.actionPerformed;
	}

	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Object getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Object createdDate) {
		this.createdDate = createdDate;
	}

	public String getSourceIp() {
		return this.sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public BigDecimal getUsrId() {
		return this.usrId;
	}

	public void setUsrId(BigDecimal usrId) {
		this.usrId = usrId;
	}

}