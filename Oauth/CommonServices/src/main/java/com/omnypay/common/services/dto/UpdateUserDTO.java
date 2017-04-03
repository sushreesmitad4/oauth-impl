package com.omnypay.common.services.dto;

public class UpdateUserDTO extends BaseDTO {

	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String address3;
	private String street;
	private String city;
	private String zipCode;
	private String state;
	private String newEmailId;

	private boolean isUpdate;

	/**
	 * @return the isUpdate
	 */
	public boolean isUpdate() {
		return isUpdate;
	}

	/**
	 * @param isUpdate
	 *            the isUpdate to set
	 */
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3
	 *            the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the newEmailId
	 */
	public String getNewEmailId() {
		return newEmailId;
	}

	/**
	 * @param newEmailId
	 *            the newEmailId to set
	 */
	public void setNewEmailId(String newEmailId) {
		this.newEmailId = newEmailId;
	}

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + ", firstName : " + getFirstName()
				+ " , lastName : " + getLastName() + " , address1 : "
				+ getAddress1() + " , address2 : " + getAddress2()
				+ ", address3 : " + getAddress3() + " , street : "
				+ getStreet() + " , city : " + getCity() + " , zipCode : "
				+ getZipCode() + " , state : " + getState() + " , isUpdate : "
				+ isUpdate() + " ,newEmailId : " + getNewEmailId() + " ,merchantAccessKey : "
				+ getMerchantAccessKey() + "]";
	}

}
