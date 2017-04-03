package com.omnypay.common.services.dto;

public class HistorySubFieldsDTO {

	private String tranId;
	private String tranDate;
	private String merchantId;
	private String terminalId;
	private String batchId;
	private String invoiceNo;
	private String accNo;
	private String accType;
	private String accExp;
	private String authNo;
	private String rRNNo;
	private String amount;
	private String merchantName;
	private String channel;
	private String txnStatus;

	public HistorySubFieldsDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the tranDate
	 */
	public String getTranDate() {
		return tranDate;
	}

	/**
	 * @param tranDate
	 *            the tranDate to set
	 */
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId
	 *            the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId
	 *            the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo
	 *            the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the accNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * @param accNo
	 *            the accNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 * @return the accType
	 */
	public String getAccType() {
		return accType;
	}

	/**
	 * @param accType
	 *            the accType to set
	 */
	public void setAccType(String accType) {
		this.accType = accType;
	}

	/**
	 * @return the authNo
	 */
	public String getAuthNo() {
		return authNo;
	}

	/**
	 * @param authNo
	 *            the authNo to set
	 */
	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}

	/**
	 * @return the rRNNo
	 */
	public String getrRNNo() {
		return rRNNo;
	}

	/**
	 * @param rRNNo
	 *            the rRNNo to set
	 */
	public void setrRNNo(String rRNNo) {
		this.rRNNo = rRNNo;
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

	/**
	 * @return the tranId
	 */
	public String getTranId() {
		return tranId;
	}

	/**
	 * @param tranId
	 *            the tranId to set
	 */
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	/**
	 * @return the accExp
	 */
	public String getAccExp() {
		return accExp;
	}

	/**
	 * @param accExp
	 *            the accExp to set
	 */
	public void setAccExp(String accExp) {
		this.accExp = accExp;
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
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
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

	public String toString() {

		return "[ Request Message=> tranId : " + getTranId() + " , tranDate : "
				+ getTranDate() + " , merchantId : " + getMerchantId()
				+ " , terminalId : " + getTerminalId() + " ,   batchId : "
				+ getBatchId() + " ,  invoiceNo : " + getInvoiceNo()
				+ " , accNo : " + getAccNo() + " ,accType : " + getAccType()
				+ " , accExp : " + getAccExp() + ", authNo : " + getAuthNo()
				+ ", rRNNo : " + getrRNNo() + " ,amount : " + getAmount()
				+ " , merchantName : " + getMerchantName() + " , channel : " + getChannel()
				+ " , txnStatus : " + getTxnStatus() + "]";
	}

}
