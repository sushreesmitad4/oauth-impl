/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class PosRespDTO {

	String amount;

	String restype;

	String posId;

	String message;

	String rrn;

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
	 * @return the posId
	 */
	public String getPosId() {
		return posId;
	}

	/**
	 * @param posId
	 *            the posId to set
	 */
	public void setPosId(String posId) {
		this.posId = posId;
	}

	/**
	 * @return the restype
	 */
	public String getRestype() {
		return restype;
	}

	/**
	 * @param restype
	 *            the restype to set
	 */
	public void setRestype(String restype) {
		this.restype = restype;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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

	@Override
	public String toString() {

		return "[ Response Message=> amount : " + getAmount() + ", restype : "
				+ getRestype() + " ,  posId : " + getPosId() + ",  message : "
				+ getMessage() + ",  rrn : " + getRrn() + " ]";
	}

}
