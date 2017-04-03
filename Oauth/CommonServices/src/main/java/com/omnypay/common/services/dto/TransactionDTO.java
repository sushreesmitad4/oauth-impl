package com.omnypay.common.services.dto;

/**
 * 
 * @author iliyasm
 *
 */
public class TransactionDTO extends BaseDTO {

	private String accNo;
	
	private String accId;
	
	private String fromDate;
	
	private String toDate;
	
	private String amount;
	
	private String totalAmount;
	
	private String requestType;
	
	private String switchTxnId;
	
	private String isProcessReq;
	
	private String rrn;
	
	private String statusCode;
	
	private String qrCode;
	
	private String posId;
	
	private String tid;
	
	/*private String mid;*/
	
	private boolean split;

	private String customerId;

	private String txnStatus;

	private String terminalId;

	private String txnStartDate;

	// e commerce tnx use field
	private String ipAddress;

	private String sessionId;

	private String firstName;

	private String lastName;

	private String address1;

	private String address2;

	private String address3;

	private String zipCode;

	private String state;

	private String city;

	// private String amount;
	private String transactionDateTime;
	
	private String merchantName;
	
	private String couponId;
	
	private String rewardPoint;
	
	

	// constructor
	public TransactionDTO() {
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the accId
	 */
	public String getAccId() {
		return accId;
	}

	/**
	 * @param accId
	 *            the accId to set
	 */
	public void setAccId(String accId) {
		this.accId = accId;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getSwitchTxnId() {
		return switchTxnId;
	}

	public void setSwitchTxnId(String switchTxnId) {
		this.switchTxnId = switchTxnId;
	}
	
	


	/**
	 * @return the isProcess
	 */

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the isProcessReq
	 */
	public String getIsProcessReq() {
		return isProcessReq;
	}

	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * @param isProcessReq
	 *            the isProcessReq to set
	 */
	public void setIsProcessReq(String isProcessReq) {
		this.isProcessReq = isProcessReq;
	}

	/**
	 * @return the rrn
	 */
	public String getRrn() {
		return rrn;
	}

	/**
	 * @param rrn
	 *            the rrn to set
	 */
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getPosId() {
		return posId;
	}

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
	 * @param qrCode
	 *            the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	/**
	 * @return the mid
	 *//*
	public String getMid() {
		return mid;
	}

	*//**
	 * @param mid
	 *            the mid to set
	 *//*
	public void setMid(String mid) {
		this.mid = mid;
	}*/

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the txnStatus
	 */
	public String getTxnStatus() {
		return txnStatus;
	}

	/**
	 * @param txnStatus
	 *            the txnStatus to set
	 */
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	/**
	 * @return the terminalId
	 */
	public String getTerminalId() {
		return terminalId;
	}

	/**
	 * @param terminalId
	 *            the terminalId to set
	 */
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	/**
	 * @return the txnStartDate
	 */
	public String getTxnStartDate() {
		return txnStartDate;
	}

	/**
	 * @param txnStartDate
	 *            the txnStartDate to set
	 */
	public void setTxnStartDate(String txnStartDate) {
		this.txnStartDate = txnStartDate;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
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
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the transactionDateTime
	 */
	public String getTransactionDateTime() {
		return transactionDateTime;
	}

	/**
	 * @param transactionDateTime
	 *            the transactionDateTime to set
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
	 * @param merchantName
	 *            the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3
	 *            the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + ", merchantAccessKey : "
				+ getMerchantAccessKey() + " , firstName : " + getFirstName()
				+ " , lastName : " + getLastName() + " , address1 : "
				+ getAddress1() + " , address2 : " + getAddress2()
				+ ", address3 : " + getAddress3() + " , state : " + getState()
				+ " , city : " + getCity() + " , zipCode : " + getZipCode()
				+ " , accNo : " + getAccNo() + " , accId : " + getAccId()
				+ " , fromDate : " + getFromDate() + " , toDate : "
				+ getToDate() + " , amount : " + getAmount()
				+ " , requestType : " + getRequestType() + " , switchTxnId : "
				+ getSwitchTxnId() + " , isProcessReq : " + getIsProcessReq()
				+ " , rrn : " + getRrn() + " , statusCode : " + getStatusCode()
				+ " , qrCode : " + getQrCode() + " , posId : " + getPosId()
				+ " , tid : " + getTid() 
				+ " , customerId : " + getCustomerId() + " , txnStatus : "
				+ getTxnStatus() + " , terminalId : " + getTerminalId()
				+ " , txnStartDate : " + getTxnStartDate() + " , ipAddress : "
				+ getIpAddress() + " , sessionId : " + getSessionId()
				+ " , transactionDateTime : " + getTransactionDateTime()
				+ " , merchantName : " + getMerchantName() + "]";
	}

}
