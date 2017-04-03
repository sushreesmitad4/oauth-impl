package com.omnypay.dao.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Entity
//@Table(name = "CLOUD_SVR_QR_CODE_TXN")
//@NamedQuery(name = "CloudSvrQrCodeTxn.findAll",query="SELECT c FROM CloudSvrQrCodeTxn c")
public class CloudSvrQrCodeTxn {

private static final long serialVersionUID = 1L;
	
	/*@Id
	@GeneratedValue
	@Column(name = "ID", nullable=false)*/
	private long Id;
	
		
	//@Column(name = "AMOUNT")
	private Double amount;
	
	//@Column(name = "IS_AMOUNT_REQ")
	private String isAmountReq;
	
	//@Column(name = "AMOUNT_CONFIRMATION")
	private String amountConfirmation;
	
	
	
	//@Column(name = "IS_PROCESS_REQ")
	private String isProcessReq;
	
	//@Column(name = "PROCESS_PAYMENT")
	private String processPayment;
	
	
	//@Column(name = "ACC_ID")
	private Long accId;
	
	//@Column(name = "USER_ID")
	private String userId;

	//@Column(name = "TXN_ID")
	private Long txnId;

	
	//@Column(name = "STATUS_CODE")
	private String statusCode;
	
	
	//@Column(name = "ACC_TYPE")
	private String accType;
	
	
	//@Column(name = "POS_ID")
	private String posId;
	

	//@Column(name = "QR_CODE")
	private String qrCode;
	
	
	//@Column(name = "TID")
	private String tid;
	
	
	//@Column(name = "MID")
	private String mid;
	
	
	
	//@Transient
	private String tokenId;
	
	//@Transient
	private String tokenExp;
	
	//@Transient
	private String 	txnStartDate;
	
	//@Transient
	private String 	merchantName;
	
	//@Transient
	private String 	merchantAddress;
	
	
	private String 	merchantAccessKey;
	
	//@Transient
	private String 	mcc;
	
	
	private boolean split;
	
	
	private String splitAmount;
	
	private String couponId;
	
	
	private String rewardPoint;
	
	
	
	
	
	
	/**
	 * @return the splitAmount
	 */
	public String getSplitAmount() {
		return splitAmount;
	}

	/**
	 * @param splitAmount the splitAmount to set
	 */
	public void setSplitAmount(String splitAmount) {
		this.splitAmount = splitAmount;
	}

	/**
	 * @return the split
	 */
	public boolean isSplit() {
		return split;
	}

	/**
	 * @param split the split to set
	 */
	public void setSplit(boolean split) {
		this.split = split;
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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the isAmountReq
	 */
	public String getIsAmountReq() {
		return isAmountReq;
	}

	/**
	 * @param isAmountReq the isAmountReq to set
	 */
	public void setIsAmountReq(String isAmountReq) {
		this.isAmountReq = isAmountReq;
	}

	/**
	 * @return the amountConfirmation
	 */
	public String getAmountConfirmation() {
		return amountConfirmation;
	}

	/**
	 * @param amountConfirmation the amountConfirmation to set
	 */
	public void setAmountConfirmation(String amountConfirmation) {
		this.amountConfirmation = amountConfirmation;
	}
	

	/**
	 * @return the isProcessReq
	 */
	public String getIsProcessReq() {
		return isProcessReq;
	}

	/**
	 * @param isProcessReq the isProcessReq to set
	 */
	public void setIsProcessReq(String isProcessReq) {
		this.isProcessReq = isProcessReq;
	}

	/**
	 * @return the processPayment
	 */
	public String getProcessPayment() {
		return processPayment;
	}

	/**
	 * @param processPayment the processPayment to set
	 */
	public void setProcessPayment(String processPayment) {
		this.processPayment = processPayment;
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
	 * @return the txnId
	 */
	public Long getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(Long txnId) {
		this.txnId = txnId;
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

	/**
	 * @return the posId
	 */
	public String getPosId() {
		return posId;
	}

	/**
	 * @param posId the posId to set
	 */
	public void setPosId(String posId) {
		this.posId = posId;
	}

	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}

	/**
	 * @param qrCode the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param mid the mid to set
	 */
	public void setMid(String mid) {
		this.mid = mid;
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
	 * @return the couponId
	 */
	public String getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	/**
	 * @return the rewardPoint
	 */
	public String getRewardPoint() {
		return rewardPoint;
	}

	/**
	 * @param rewardPoint the rewardPoint to set
	 */
	public void setRewardPoint(String rewardPoint) {
		this.rewardPoint = rewardPoint;
	}


	
	
	
}
	
	
	

	

