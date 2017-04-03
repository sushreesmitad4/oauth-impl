package com.omnypay.common.services.dto;

/**
 * 
 * @author Kirank
 *
 */
public class LoginDTO extends BaseDTO {

	private String passcode;

	// constructor
	public LoginDTO() {

	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + ",passcode : " + "xxxxxxxx" + " ,  merchantAccessKey : "
				+ getMerchantAccessKey() + "]";
	}
}
