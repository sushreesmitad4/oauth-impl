/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class HceTokenDTO {

	private String userId;

	private String imeiNo;

	private String passcode;

	private String isFirstCard;

	private String accountRefId;

	private String tokenType;

	private String serviceRefId;

	private String tokenRequestorId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the accountRefId
	 */
	public String getAccountRefId() {
		return accountRefId;
	}

	/**
	 * @param accountRefId
	 *            the accountRefId to set
	 */
	public void setAccountRefId(String accountRefId) {
		this.accountRefId = accountRefId;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType
	 *            the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @return the serviceRefId
	 */
	public String getServiceRefId() {
		return serviceRefId;
	}

	/**
	 * @param serviceRefId
	 *            the serviceRefId to set
	 */
	public void setServiceRefId(String serviceRefId) {
		this.serviceRefId = serviceRefId;
	}

	/**
	 * @return the tokenRequestorId
	 */
	public String getTokenRequestorId() {
		return tokenRequestorId;
	}

	/**
	 * @param tokenRequestorId
	 *            the tokenRequestorId to set
	 */
	public void setTokenRequestorId(String tokenRequestorId) {
		this.tokenRequestorId = tokenRequestorId;
	}

	/**
	 * @return the passcode
	 */
	public String getPasscode() {
		return passcode;
	}

	/**
	 * @param passcode
	 *            the passcode to set
	 */
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	/**
	 * @return the imeiNo
	 */
	public String getImeiNo() {
		return imeiNo;
	}

	/**
	 * @param imeiNo
	 *            the imeiNo to set
	 */
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	/**
	 * @return the isFirstCard
	 */
	public String getIsFirstCard() {
		return isFirstCard;
	}

	/**
	 * @param isFirstCard
	 *            the isFirstCard to set
	 */
	public void setIsFirstCard(String isFirstCard) {
		this.isFirstCard = isFirstCard;
	}

	@Override
	public String toString() {

		return "[ Request Message=> userId : " + getUserId()
				+ ", accountRefId : " + getAccountRefId() + " ,  tokenType : "
				+ getTokenType() + " , serviceRefId : " + getServiceRefId()
				+ " , tokenRequestorId : " + getTokenRequestorId()
				+ ",isFirstCard : " + getIsFirstCard() + ",imeiNo : "
				+ getImeiNo() + ",passcode : " + getPasscode() + "]";
	}

}
