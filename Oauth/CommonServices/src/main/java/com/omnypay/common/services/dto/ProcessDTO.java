package com.omnypay.common.services.dto;

import java.util.List;

public class ProcessDTO extends Status {

	private List<ProcessSubFieldDTO> requestedData;

	private String isAmountReq;

	private String isProcessReq;

	/**
	 * @return the isAmountReq
	 */
	public String getIsAmountReq() {
		return isAmountReq;
	}

	/**
	 * @param isAmountReq
	 *            the isAmountReq to set
	 */
	public void setIsAmountReq(String isAmountReq) {
		this.isAmountReq = isAmountReq;
	}

	/**
	 * @return the isProcessReq
	 */
	public String getIsProcessReq() {
		return isProcessReq;
	}

	/**
	 * @param isProcessReq
	 *            the isProcessReq to set
	 */
	public void setIsProcessReq(String isProcessReq) {
		this.isProcessReq = isProcessReq;
	}

	/**
	 * @return the requestedData
	 */
	public List<ProcessSubFieldDTO> getRequestedData() {
		return requestedData;
	}

	/**
	 * @param requestedData
	 *            the requestedData to set
	 */
	public void setRequestedData(List<ProcessSubFieldDTO> requestedData) {
		this.requestedData = requestedData;
	}

	public String toString() {

		return "[ Request Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType()
				+ " , requestedData : " + getRequestedData()
				+ " , isAmountReq : " + getIsAmountReq() + " , isProcessReq : "
				+ getIsProcessReq() + " ]";
	}
}
