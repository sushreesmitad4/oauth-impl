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

public class CloudSvrEcommerceTxn implements Serializable {
	
		private static final long serialVersionUID = 1L;

		private long Id;

	

		//@Column(name = "IP_ADDRESS")
		private String ipAddress;

		//@Column(name = "SESSION_ID")
		private String sessionId;

		//@Column(name = "AMOUNT")
		private String amount;
		
		
		//@Column(name = "TRANSACTION_TIME")
		private String transactionDateTime;

		//@Column(name = "MERCHANT_NAME")
		private String merchantName;
		
		//@Column(name = "status")
		private String status;
		
		
		//@Transient
		private String tokenId;
		
		//@Transient
		private String tokenExp;
		
		//@Transient
		private String 	txnStartDate;
		
		
		
		//@Transient
		private String 	merchantAddress;
		
		//@Transient
		private String 	mcc;
		
		private String statusCode;
		
		private String tid;
		
		
		//@Column(name = "ACC_ID")
		private Long accId;
		
		//@Column(name = "USER_ID")
		private String userId;
		
		
		//@Column(name = "ACC_TYPE")
		private String accType;
		
		
		private String merchantAccessKey;
		
		

		/**
		 * @return the merchantAccessKey
		 */
		public String getMerchantAccessKey() {
			return merchantAccessKey;
		}

		/**
		 * @param merchantAccessKey the merchantAccessKey to set
		 */
		public void setMerchantAccessKey(String merchantAccessKey) {
			this.merchantAccessKey = merchantAccessKey;
		}

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
			return merchantName;
		}

		/**
		 * @param merchantName the merchantName to set
		 */
		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
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

		/**
		 * @return the tokenId
		 */
		public String getTokenId() {
			return tokenId;
		}

		/**
		 * @param tokenId the tokenId to set
		 */
		public void setTokenId(String tokenId) {
			this.tokenId = tokenId;
		}

		/**
		 * @return the tokenExp
		 */
		public String getTokenExp() {
			return tokenExp;
		}

		/**
		 * @param tokenExp the tokenExp to set
		 */
		public void setTokenExp(String tokenExp) {
			this.tokenExp = tokenExp;
		}

		/**
		 * @return the txnStartDate
		 */
		public String getTxnStartDate() {
			return txnStartDate;
		}

		/**
		 * @param txnStartDate the txnStartDate to set
		 */
		public void setTxnStartDate(String txnStartDate) {
			this.txnStartDate = txnStartDate;
		}

		/**
		 * @return the merchantAddress
		 */
		public String getMerchantAddress() {
			return merchantAddress;
		}

		/**
		 * @param merchantAddress the merchantAddress to set
		 */
		public void setMerchantAddress(String merchantAddress) {
			this.merchantAddress = merchantAddress;
		}

		/**
		 * @return the mcc
		 */
		public String getMcc() {
			return mcc;
		}

		/**
		 * @param mcc the mcc to set
		 */
		public void setMcc(String mcc) {
			this.mcc = mcc;
		}

		/**
		 * @return the statusCode
		 */
		public String getStatusCode() {
			return statusCode;
		}

		/**
		 * @param statusCode the statusCode to set
		 */
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}

		/**
		 * @return the tid
		 */
		public String getTid() {
			return tid;
		}

		/**
		 * @param tid the tid to set
		 */
		public void setTid(String tid) {
			this.tid = tid;
		}

		/**
		 * @return the accId
		 */
		public Long getAccId() {
			return accId;
		}

		/**
		 * @param accId the accId to set
		 */
		public void setAccId(Long accId) {
			this.accId = accId;
		}

		

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		/**
		 * @return the accType
		 */
		public String getAccType() {
			return accType;
		}

		/**
		 * @param accType the accType to set
		 */
		public void setAccType(String accType) {
			this.accType = accType;
		}
		
		

		
		
		
		
		
}
