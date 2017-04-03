/**
 * 
 */
package com.omnypay.common.services.dto;

import java.util.List;

import javax.persistence.Transient;

/**
 * @author iliyasm
 *
 */
public class ProcessPaymentRespDTO extends Status {

	private String tranDate;

	private String terminalId;

	private String amount;

	private String txnStatus;

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

		return "[ Request Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType() + " , tranDate : "
				+ getTranDate() + " , terminalId : " + getTerminalId()
				+ " , amount : " + getAmount() + ",txnStatus : "
				+ getTxnStatus() + "]";
	}

}
