package com.omnypay.common.services.dto;

import java.util.List;

public class RewardRespDTO extends Status {
	
	
	private RewardSubfieldDTO requestedData;

	/**
	 * @return the requestedData
	 */
	public RewardSubfieldDTO getRequestedData() {
		return requestedData;
	}

	/**
	 * @param requestedData the requestedData to set
	 */
	public void setRequestedData(RewardSubfieldDTO requestedData) {
		this.requestedData = requestedData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RewardRespDTO [requestedData=" + requestedData
				+ ", getRequestedData()=" + getRequestedData()
				+ "]";
	}
	
	
	
	
	
}
