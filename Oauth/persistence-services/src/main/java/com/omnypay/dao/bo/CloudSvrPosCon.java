package com.omnypay.dao.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;



@Entity
@Table(name = "PG_POS_CONFIG")
@NamedQuery(name = "CloudSvrPosCon.findAll", query="SELECT c FROM CloudSvrPosCon c")
public class CloudSvrPosCon implements Serializable {
	private static final long serialVersionUID = 1L;

	
		
	
	/**
	 * 
	 */
	public CloudSvrPosCon() {
		
	}


	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long Id;
	
		
	@Column(name = "POS_ID")
	private String posId;
	
	@Column(name = "QR_CODE")
	private String qrCode;
	
	
	
	@Column(name = "TERMINAL_ID")
	private String tId;
	
	
	@Column(name = "STORE_ID")
	private String storeId;
	
		
	@Column(name = "MID")
	private Long mId;
	
	
	
	
	
	@Column(name = "STATUS")
	private Long status;
	
	
	
	@Transient
	private String merchantId;
	
	
	@Transient
	private String merchantName;
	
	
	@Transient
	private String merchantAddress;
	
	
	@Transient
	private String merchantAccKey;


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
	 * @return the posId
	 */
	public String getPosId() {
		return posId;
	}


	/**
	 * @param posId the posId to set
	 */
	public void setPosId(String posId) {
		this.posId = posId;
	}


	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}


	/**
	 * @param qrCode the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}


	/**
	 * @return the tId
	 */
	public String gettId() {
		return tId;
	}


	/**
	 * @param tId the tId to set
	 */
	public void settId(String tId) {
		this.tId = tId;
	}


	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}


	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}


	


	/**
	 * @return the mId
	 */
	public Long getmId() {
		return mId;
	}


	/**
	 * @param mId the mId to set
	 */
	public void setmId(Long mId) {
		this.mId = mId;
	}


	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}


	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}


	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	/**
	 * @return the merchantAddress
	 */
	public String getMerchantAddress() {
		return merchantAddress;
	}


	/**
	 * @param merchantAddress the merchantAddress to set
	 */
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}


	/**
	 * @return the merchantAccKey
	 */
	public String getMerchantAccKey() {
		return merchantAccKey;
	}


	/**
	 * @param merchantAccKey the merchantAccKey to set
	 */
	public void setMerchantAccKey(String merchantAccKey) {
		this.merchantAccKey = merchantAccKey;
	}


	


	

	
	
	
}
