package com.omnypay.common.services.dto;

/**
 * 
 * @author Kirank
 *
 */
public class BaseDTO {

	private String emailId;
	private String phoneNumber;
	private String imeiNo;
	private String merchantAccessKey;
	
	public BaseDTO() {

	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public String getMerchantAccessKey() {
		return merchantAccessKey;
	}

	public void setMerchantAccessKey(String merchantAccessKey) {
		this.merchantAccessKey = merchantAccessKey;
	}



	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + " , merchantAccessKey : "
				+ getMerchantAccessKey() + "]";
	}

}
