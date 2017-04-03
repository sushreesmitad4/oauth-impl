package com.omnypay.dao.bo;

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
@Table(name="DISCOUNT_TYPE")
@NamedQuery(name = "CloudSvrDiscountType.findAll", query="SELECT c FROM CloudSvrDiscountType c")
public class CloudSvrDiscountType {

	
	
	@Id
 	@GeneratedValue
 	@Column(name ="DISCOUNT_ID")
 	private long discountId;
	

	@Column(name = "DISCOUNT_TYPE")
    private String discountType;
	
	@Column(name = "DISCOUNT_VALUE")
    private String discountValue;
	
	
	@Column(name = "MERCHANT_ID")
    private String merchantId;
	
	
	@Column(name = "DISCOUNT_DESC")
    private String discountDesc;

	
	

	/**
	 * @return the discountId
	 */
	public long getDiscountId() {
		return discountId;
	}


	/**
	 * @param discountId the discountId to set
	 */
	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}


	/**
	 * @return the discountType
	 */
	public String getDiscountType() {
		return discountType;
	}


	/**
	 * @param discountType the discountType to set
	 */
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}


	/**
	 * @return the discountValue
	 */
	public String getDiscountValue() {
		return discountValue;
	}


	/**
	 * @param discountValue the discountValue to set
	 */
	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
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


	/**
	 * @return the discountDesc
	 */
	public String getDiscountDesc() {
		return discountDesc;
	}


	/**
	 * @param discountDesc the discountDesc to set
	 */
	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}
	
	
	
	
	
	
	
	
}
