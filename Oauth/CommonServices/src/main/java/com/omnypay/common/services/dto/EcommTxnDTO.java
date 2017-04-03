package com.omnypay.common.services.dto;

public class EcommTxnDTO {

	private String txnId;

	// represents SALE/VOID
	private String txnType;

	private String amount;

	private String merchantId;

	private String cardNo;

	private String cardExpiry;

	private String processingCode;

	private String posEntryMode;

	private String networkId;

	private String serviceCode;

	private String terminalId;

	private String invoiceNo;

	private String cardType;

	private String bankName;

	private String bankRoutingNo;

	private String accHolderName;

	private String bankAccNo;

	private String customerId;

	private String accType;

	private String channel;

	private String requestType;
	private String switchTxnId;

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
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
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the cardExpiry
	 */
	public String getCardExpiry() {
		return cardExpiry;
	}

	/**
	 * @param cardExpiry
	 *            the cardExpiry to set
	 */
	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	/**
	 * @return the processingCode
	 */
	public String getProcessingCode() {
		return processingCode;
	}

	/**
	 * @param processingCode
	 *            the processingCode to set
	 */
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	/**
	 * @return the posEntryMode
	 */
	public String getPosEntryMode() {
		return posEntryMode;
	}

	/**
	 * @param posEntryMode
	 *            the posEntryMode to set
	 */
	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}

	/**
	 * @return the networkId
	 */
	public String getNetworkId() {
		return networkId;
	}

	/**
	 * @param networkId
	 *            the networkId to set
	 */
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * @param serviceCode
	 *            the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankRoutingNo
	 */
	public String getBankRoutingNo() {
		return bankRoutingNo;
	}

	/**
	 * @param bankRoutingNo
	 *            the bankRoutingNo to set
	 */
	public void setBankRoutingNo(String bankRoutingNo) {
		this.bankRoutingNo = bankRoutingNo;
	}

	/**
	 * @return the accHolderName
	 */
	public String getAccHolderName() {
		return accHolderName;
	}

	/**
	 * @param accHolderName
	 *            the accHolderName to set
	 */
	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	/**
	 * @return the bankAccNo
	 */
	public String getBankAccNo() {
		return bankAccNo;
	}

	/**
	 * @param bankAccNo
	 *            the bankAccNo to set
	 */
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

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
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType
	 *            the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getSwitchTxnId() {
		return switchTxnId;
	}

	public void setSwitchTxnId(String switchTxnId) {
		this.switchTxnId = switchTxnId;
	}

	@Override
	public String toString() {

		return "[ Response Message=> txnId : " + getTxnId() + ", txnType : "
				+ getTxnType() + " , amount : " + getAmount()
				+ ",  merchantId : " + getMerchantId() + ", cardNo : "
				+ getCardNo() + ", cardExpiry : " + getCardExpiry()
				+ ", processingCode : " + getProcessingCode()
				+ " , posEntryMode : " + getPosEntryMode() + " , networkId : "
				+ getNetworkId() + " , bankName : " + getBankName()
				+ " , serviceCode : " + getServiceCode() + " , terminalId : "
				+ getTerminalId() + " , invoiceNo : " + getInvoiceNo()
				+ " , cardType : " + getCardType() + " ,  bankRoutingNo : "
				+ getBankRoutingNo() + " , accHolderName : "
				+ getAccHolderName() + " , bankAccNo : " + getBankAccNo()
				+ " ,  customerId : " + getCustomerId() + " , accType : "
				+ getAccType() + " ,  channel : " + getChannel()
				+ " , requestType : " + getRequestType() + " ,  switchTxnId : "
				+ getSwitchTxnId() + "]";
	}

}
