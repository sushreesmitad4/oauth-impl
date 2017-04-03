package com.omnypay.common.services.dto;

import java.util.Set;

/**
 * 
 * @author Kirank
 *
 */
public class UserDTO extends BaseDTO {

	private String firstName;
	private String lastName;
	private String passcode;
	private String address1;
	private String address2;
	private String address3;
	private String zipCode;
	private String state;
	private String mercId;
	private boolean isUpdate;
	
	private String clientUserId;
	
	
	private Set<RespSubFieldsDTO> secQuestionSet;

	public UserDTO() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMercId() {
		return mercId;
	}

	public void setMercId(String mercId) {
		this.mercId = mercId;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Set<RespSubFieldsDTO> getSecQuestionSet() {
		return secQuestionSet;
	}

	public void setSecQuestionSet(Set<RespSubFieldsDTO> secQuestionSet) {
		this.secQuestionSet = secQuestionSet;
	}
	
	
	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + ",firstName : " + getFirstName()
				+ " , lastName : " + getLastName() + " , passcode : "
				+ "xxxxxxxx" + " , address1 : " + getAddress1()
				+ " , address2 : " + getAddress2() + " , address3 : "
				+ getAddress3() + " , zipCode : " + getZipCode()
				+ " , state : " + getState() + " , mercId : " + getMercId()
				+ " , secQuestionSet : " + getSecQuestionSet() + " , state : "
				+ getState() + " , merchantAccessKey : "
				+ getMerchantAccessKey() + "]";
	}
}
