package com.omnypay.dao.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jagdishm
 *
 */
@Entity
@Table(name="COUPON_EXCEPTION")
@NamedQuery(name = "CouposException.findAll", query="SELECT c FROM CouposException c")
public class CouposException {

	
	@Column(name = "COUPON_ID")
	private String coupponId;
	
	@Id
	@Column(name = "EXCEPTION_ID")
    private String exceptionId;
	
	@Column(name = "EXCEPTION_DEPARTMENT")
	private String exceptionDepartment;
	
	/**
	 * @return the coupponId
	 */
	public String getCoupponId() {
		return coupponId;
	}

	/**
	 * @param coupponId the coupponId to set
	 */
	public void setCoupponId(String coupponId) {
		this.coupponId = coupponId;
	}

	/**
	 * @return the exceptionId
	 */
	public String getExceptionId() {
		return exceptionId;
	}

	/**
	 * @param exceptionId the exceptionId to set
	 */
	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	/**
	 * @return the exceptionDepartment
	 */
	public String getExceptionDepartment() {
		return exceptionDepartment;
	}

	/**
	 * @param exceptionDepartment the exceptionDepartment to set
	 */
	public void setExceptionDepartment(String exceptionDepartment) {
		this.exceptionDepartment = exceptionDepartment;
	}

	
	
	
	
	
}