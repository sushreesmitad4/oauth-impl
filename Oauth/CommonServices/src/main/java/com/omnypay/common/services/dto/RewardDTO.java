package com.omnypay.common.services.dto;



public class RewardDTO extends BaseDTO{
	
	
	 private String rewardId;
	 
	 private String rewardPontTotal;
	
	

	/**
	 * @return the rewardId
	 */
	public String getRewardId() {
		return rewardId;
	}

	/**
	 * @param rewardId the rewardId to set
	 */
	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	/**
	 * @return the rewardPontTotal
	 */
	public String getRewardPontTotal() {
		return rewardPontTotal;
	}

	/**
	 * @param rewardPontTotal the rewardPontTotal to set
	 */
	public void setRewardPontTotal(String rewardPontTotal) {
		this.rewardPontTotal = rewardPontTotal;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RewardDTO [rewardId=" + rewardId + ", rewardPontTotal="
				+ rewardPontTotal + ", rewardPontPending=" +  ", expireDate="
				+ "]";
	}
	 
	 
	 
	 

}
