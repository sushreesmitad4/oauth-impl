package com.omnypay.common.services.dto;

public class SwitchTxnRespDTO {

	private String statusCode;

	private String message;

	private String switchTxnDate;

	private String txnId;

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the switchTxnDate
	 */
	public String getSwitchTxnDate() {
		return switchTxnDate;
	}

	/**
	 * @param switchTxnDate
	 *            the switchTxnDate to set
	 */
	public void setSwitchTxnDate(String switchTxnDate) {
		this.switchTxnDate = switchTxnDate;
	}

	/**
	 * @return the txnId
	 */
	public String getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId
	 *            the txnId to set
	 */
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String toString() {

		return "[ Response Message=> statusCode : " + getStatusCode()
				+ " , message : " + getMessage() + " , switchTxnDAte : "
				+ getSwitchTxnDate() + " , txnId : " + getTxnId() + "]";

	}
}
