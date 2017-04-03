/**
 * 
 */
package com.omnypay.common.services.dto;

import java.util.List;

/**
 * @author iliyasm
 *
 */
public class CustomerRespDTO extends Status {

	private List<AccountDTO> requestedData;

	private String isSecurity;

	private UpdateUserDTO updateProfile;

	private String emailId;

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the requestedData
	 */
	public List<AccountDTO> getRequestedData() {
		return requestedData;
	}

	/**
	 * @param requestedData
	 *            the requestedData to set
	 */
	public void setRequestedData(List<AccountDTO> requestedData) {
		this.requestedData = requestedData;
	}

	/**
	 * @return the isSecurity
	 */
	public String getIsSecurity() {
		return isSecurity;
	}

	/**
	 * @param isSecurity
	 *            the isSecurity to set
	 */
	public void setIsSecurity(String isSecurity) {
		this.isSecurity = isSecurity;
	}

	/**
	 * @return the updateProfile
	 */
	public UpdateUserDTO getUpdateProfile() {
		return updateProfile;
	}

	/**
	 * @param updateProfile
	 *            the updateProfile to set
	 */
	public void setUpdateProfile(UpdateUserDTO updateProfile) {
		this.updateProfile = updateProfile;
	}

	public String toString() {

		return "[ Response Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType()
				+ " , requestedData : " + getRequestedData() + " ,  emailId : "
				+ getEmailId() + " , isSecurity : " + getIsSecurity()
				+ " , updateProfile : " + getUpdateProfile() + "]";

	}

}
