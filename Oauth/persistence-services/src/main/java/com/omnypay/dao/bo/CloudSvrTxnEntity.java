/**
 * 
 */
package com.omnypay.dao.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author iliyasm
 *
 */
@Entity
@Table(name = "PG_ENTITY_INFO")
@NamedQuery(name = "CloudSvrTxnEntity.findAll", query="SELECT c FROM CloudSvrTxnEntity c")
public class CloudSvrTxnEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "ID")
	private Long Id;
	
		
	@Column(name = "MID")
	private String merchantId;
	
	
	@Column(name = "STATUS")
	private String status;


	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}


	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}


	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
