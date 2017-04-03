package com.omnypay.common.services.dto;

public class PasswordDTO extends BaseDTO {

	private String securityQuestion1;
	private String answer1;
	private String securityQuestion2;
	private String answer2;
	private String oldPassword;
	private String newPassword;

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	
	
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
				+ getImeiNo() + ",securityQuestion1 : "
				+ getSecurityQuestion1() + ",answer1 : " + getAnswer1()
				+ ",securityQuestion2 : " + getSecurityQuestion2()
				+ ",answer2 : " + getAnswer2() + " , oldPassword : " + getOldPassword()
				+ ",newPassword : " + getNewPassword() + " ,  merchantAccessKey : "
				+ getMerchantAccessKey() + "]";
	}
}
