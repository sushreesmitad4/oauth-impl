package com.omnypay.common.services.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

public class RewardSubfieldDTO  {

	
	
	
	   private String rewardId;
	 
		
			
		
		private String expireDate;
		
	
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


		


		/**
		 * @return the expireDate
		 */
		public String getExpireDate() {
			return expireDate;
		}


		/**
		 * @param expireDate the expireDate to set
		 */
		public void setExpireDate(String expireDate) {
			this.expireDate = expireDate;
		}


	
		
		
		
	    /* (non-Javadoc)
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "RewardRespDTO [rewardPontTotal="
						+ rewardPontTotal + ", rewardPontPending="
						+ ", expireDate=" + expireDate + "]";
			}


}
