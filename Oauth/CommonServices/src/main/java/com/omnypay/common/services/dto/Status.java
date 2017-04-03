package com.omnypay.common.services.dto;

import java.util.List;

/**
 * 
 * @author iliyasm
 *
 */
public class Status {

	private String code;

	private String message;

	private String type;
	

	/*private List<RespSubFieldsDTO> requestedData;

	public List<RespSubFieldsDTO> getRequestedData() {
		return requestedData;
	}

	public void setRequestedData(List<RespSubFieldsDTO> requestedData) {
		this.requestedData = requestedData;
	}*/


	public Status() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {

		return "[ Response Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType() + "]";

	}
}
