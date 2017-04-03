package com.omnypay.common.services.dto;

public class TransactionRespDTO extends Status {

	private TranSubFieldsDTO requestedData;

	public TransactionRespDTO() {
		// TODO Auto-generated constructor stub
	}

	public TranSubFieldsDTO getRequestedData() {
		return requestedData;
	}

	public void setRequestedData(TranSubFieldsDTO requestedData) {
		this.requestedData = requestedData;
	}

	public String toString() {

		return "[ Request Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType()
				+ ", requestedData : " + getRequestedData() + "]";
	}

}
