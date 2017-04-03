package com.omnypay.common.services.dto;

import java.util.List;

public class HistoryRespDTO extends Status{

	private List<HistorySubFieldsDTO> requestedData;
	
	public HistoryRespDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<HistorySubFieldsDTO> getRequestedData() {
		return requestedData;
	}

	public void setRequestedData(List<HistorySubFieldsDTO> requestedData) {
		this.requestedData = requestedData;
	}
	
	
	public String toString(){
		
		
		return "[ Response Message=> code : "+getCode()+" , message : "+getMessage()+" , type : "+getType()+" , requestedData : "+getRequestedData()+"]";
	
	}
}
