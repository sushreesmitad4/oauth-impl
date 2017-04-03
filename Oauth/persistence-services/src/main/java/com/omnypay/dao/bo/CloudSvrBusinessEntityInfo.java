/**
 * 
 */
package com.omnypay.dao.bo;


import java.io.Serializable;

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
@Table(name="PG_ENTITY_BUSINESS_INFO")
@NamedQuery(name = "CloudSvrBusinessEntityInfo.findAll", query="SELECT c FROM CloudSvrBusinessEntityInfo c")
public class CloudSvrBusinessEntityInfo implements Serializable {
	 
	 private static final long serialVersionUID = 1L;


	 @Id 
	 @Column(name="ENTITY_ID")
	 private Long entityId;
	 
	 @Column(name="BUSINESS_NAME")
	 private String merchantName;
	 
	 @Column(name = "STATUS")
	 private String status;
	 
	 
	 @Column(name="MERCHANT_KEY")
	 private String merchantKey;
	 
	 
	 @Column(name="EXTERNAL_MERCHANT")
	 private String externalMerchant;
	 
	 /**
	 * @return the externalMerchant
	 */
	public String getExternalMerchant() {
		return externalMerchant;
	}


	/**
	 * @param externalMerchant the externalMerchant to set
	 */
	public void setExternalMerchant(String externalMerchant) {
		this.externalMerchant = externalMerchant;
	}


	//constructor
	 public CloudSvrBusinessEntityInfo() { 
	  
	 }
	 

	 public String getMerchantName() {
	  return merchantName;
	 }
	 

	 public void setMerchantName(String merchantName) {
	  this.merchantName = merchantName;
	 }
	 

	 public Long getEntityId() {
	  return entityId;
	 }

	 public void setEntityId(Long entityId) {
	  this.entityId = entityId;
	 }


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	public String getMerchantKey() {
		return merchantKey;
	}


	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}
	
	 
	 
	 
}
