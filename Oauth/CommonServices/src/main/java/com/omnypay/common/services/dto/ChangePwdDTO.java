package com.omnypay.common.services.dto;

public class ChangePwdDTO extends BaseDTO {
	private String oldPassword;
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + ",oldPassword : " + getOldPassword()
				+ ",newPassword : " + getNewPassword()
				+ " , merchantAccessKey : " + getMerchantAccessKey() + "]";
	}

}
