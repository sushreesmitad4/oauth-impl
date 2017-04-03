package com.omnypay.common.services.dto;

import java.util.List;

public class SecurityQuestionsRespDTO extends Status {

	public SecurityQuestionsRespDTO() {
	}

	private List<RespSubFieldsDTO> requestedData;

	public List<RespSubFieldsDTO> getRequestedData() {
		return requestedData;
	}

	public void setRequestedData(List<RespSubFieldsDTO> requestedData) {
		this.requestedData = requestedData;
	}

	public String toString() {

		return "[ Response Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType()
				+ " , requestedData : " + getRequestedData() + "]";

	}
}
