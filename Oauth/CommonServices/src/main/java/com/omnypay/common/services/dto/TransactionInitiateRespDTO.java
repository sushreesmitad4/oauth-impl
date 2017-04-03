/**
 * 
 */
package com.omnypay.common.services.dto;

import java.util.List;
import java.util.Map;

/**
 * @author iliyasm
 *
 */
public class TransactionInitiateRespDTO extends Status {

	private String txnAmount;

	private String isTxnEcomm;

	private List<Map<String, String>> token;
	
	
	private String tranDate;

	private String terminalId;

	private String amount;

	private String txnStatus;
	
	
	private List<HistorySubFieldsDTO> requestedData;
	
	
	
	private List<ProcessSubFieldDTO> posRequestedData;

	private String isAmountReq;

	private String isProcessReq;
	
	/*private boolean split;
	
	private String splitAmount;*/
	
	
	
	
	
	
	



	/**
	 * @return the tranDate
	 */
	public String getTranDate() {
		return tranDate;
	}

	/**
	 * @param tranDate the tranDate to set
	 */
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	/**
	 * @return the terminalId
	 */
	public String getTerminalId() {
		return terminalId;
	}

	/**
	 * @param terminalId the terminalId to set
	 */
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
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
	 * @return the txnStatus
	 */
	public String getTxnStatus() {
		return txnStatus;
	}

	/**
	 * @param txnStatus the txnStatus to set
	 */
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	/**
	 * @return the txnAmount
	 */
	public String getTxnAmount() {
		return txnAmount;
	}

	/**
	 * @param txnAmount
	 *            the txnAmount to set
	 */
	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	/**
	 * @return the token
	 */
	/*
	 * public List<String> getToken() { return token; }
	 *//**
	 * @param token
	 *            the token to set
	 */
	/*
	 * public void setToken(List<String> token) { this.token = token; }
	 */

	public List<Map<String, String>> getToken() {
		return token;
	}

	public void setToken(List<Map<String, String>> token) {
		this.token = token;
	}

	/**
	 * @return the isTxnEcomm
	 */
	public String getIsTxnEcomm() {
		return isTxnEcomm;
	}

	/**
	 * @param isTxnEcomm
	 *            the isTxnEcomm to set
	 */
	public void setIsTxnEcomm(String isTxnEcomm) {
		this.isTxnEcomm = isTxnEcomm;
	}

	
	
	
	/**
	 * @return the requestedData
	 */
	public List<HistorySubFieldsDTO> getRequestedData() {
		return requestedData;
	}

	/**
	 * @param requestedData the requestedData to set
	 */
	public void setRequestedData(List<HistorySubFieldsDTO> requestedData) {
		this.requestedData = requestedData;
	}
	
	
	
	
	
	

	/**
	 * @return the posRequestedData
	 */
	public List<ProcessSubFieldDTO> getPosRequestedData() {
		return posRequestedData;
	}

	/**
	 * @param posRequestedData the posRequestedData to set
	 */
	public void setPosRequestedData(List<ProcessSubFieldDTO> posRequestedData) {
		this.posRequestedData = posRequestedData;
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

	public String toString() {

		return "[ Response Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType() + " , txnAmount : "
				+ getTxnAmount() + ", token : " + getToken()
				+ " , isTxnEcomm : " + getIsTxnEcomm() + "]";

	}

}
