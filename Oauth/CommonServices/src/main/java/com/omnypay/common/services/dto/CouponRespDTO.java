package com.omnypay.common.services.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;

/**
 * 
 * @author sushreesmita
 *
 */

public class CouponRespDTO extends Status {
	
	
	public CouponRespDTO() {
	}

	private List<CouponSubfieldDTO> requestedData;
	
	

	public List<CouponSubfieldDTO> getRequestedData() {
		return requestedData;
	}

	public void setRequestedData(List<CouponSubfieldDTO> requestedData) {
		this.requestedData = requestedData;
	}


	

}
