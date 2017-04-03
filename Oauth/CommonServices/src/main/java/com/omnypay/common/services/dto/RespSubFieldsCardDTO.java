/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class RespSubFieldsCardDTO {

	private long cId;

	private String cardNo;

	private String cardType;

	public RespSubFieldsCardDTO() {
	}

	/**
	 * @return the cId
	 */
	public long getcId() {
		return cId;
	}

	/**
	 * @param cId
	 *            the cId to set
	 */
	public void setcId(long cId) {
		this.cId = cId;
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

	public String toString() {

		return "[ Request Message=> cId : " + getcId() + " , cardNo : "
				+ getCardNo() + " , cardType : " + getCardType() + "]";
	}

}
