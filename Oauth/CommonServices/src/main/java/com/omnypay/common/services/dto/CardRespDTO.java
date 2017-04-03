/**
 * 
 */
package com.omnypay.common.services.dto;

import java.util.List;

/**
 * @author iliyasm
 *
 */
public class CardRespDTO extends Status{
	
	
	public CardRespDTO() {
	}

	private List<RespSubFieldsCardDTO> requestedData ;

	
	
	
	
	/**
	 * @return the requestedData
	 */
	public List<RespSubFieldsCardDTO> getRequestedData() {
		return requestedData;
	}





	/**
	 * @param requestedData the requestedData to set
	 */
	public void setRequestedData(List<RespSubFieldsCardDTO> requestedData) {
		this.requestedData = requestedData;
	}





	public String toString() {

		return "[ Request Message=> code : "+getCode()+" , message : "+getMessage()+" , type : "+getType()+" , requestedData : " + getRequestedData()+ "]";
	}
	
	
	

}
