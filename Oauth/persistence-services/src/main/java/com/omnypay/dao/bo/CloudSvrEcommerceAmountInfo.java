package com.omnypay.dao.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "CLOUD_SVR_ECOMM_AMOUNT_INFO")
@NamedQuery(name = "CloudSvrEcommerceAmountInfo.findAll", query = "SELECT c FROM CloudSvrUser c")
public class CloudSvrEcommerceAmountInfo implements Serializable {
	
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue
		@Column(name = "ID")
		private long Id;

	

		@Column(name = "IP_ADDRESS")
		private String ipAddress;

		@Column(name = "SESSION_ID")
		private String sessionId;

		@Column(name = "AMOUNT")
		private String amount;
		
		
		@Column(name = "TRANSACTION_TIME")
		private String transactionDateTime;

		@Column(name = "MERCHANT_NAME")
		private String MerchantName;
		
		@Column(name = "status")
		private String status;
		

		/**
		 * @return the id
		 */
		public long getId() {
			return Id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(long id) {
			Id = id;
		}

		/**
		 * @return the ipAddress
		 */
		public String getIpAddress() {
			return ipAddress;
		}

		/**
		 * @param ipAddress the ipAddress to set
		 */
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		/**
		 * @return the sessionId
		 */
		public String getSessionId() {
			return sessionId;
		}

		/**
		 * @param sessionId the sessionId to set
		 */
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		/**
		 * @return the amount
		 */
		public String getAmount() {
			return amount;
		}

		/**
		 * @param amount the amount to set
		 */
		public void setAmount(String amount) {
			this.amount = amount;
		}
		
		

		
		/**
		 * @return the transactionDateTime
		 */
		public String getTransactionDateTime() {
			return transactionDateTime;
		}

		/**
		 * @param transactionDateTime the transactionDateTime to set
		 */
		public void setTransactionDateTime(String transactionDateTime) {
			this.transactionDateTime = transactionDateTime;
		}

		/**
		 * @return the merchantName
		 */
		public String getMerchantName() {
			return MerchantName;
		}

		/**
		 * @param merchantName the merchantName to set
		 */
		public void setMerchantName(String merchantName) {
			MerchantName = merchantName;
		}

		/**
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}

	
		
}
