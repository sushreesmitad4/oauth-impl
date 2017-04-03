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
public class CardOnFileRespDTO {

	private String accId;

	private String isFirstCard;

	private String accNo;

	private String type;

	private String acctype;

	private String msg;

	private List<Map<String, String>> tokens;

	private String udk;

	private String tokenexpDate;

	private String bankName;

	private String bankRoutingNumber;

	private String acctHolderName;

	private String userId;

	private String cardExpiry;

	/**
	 * @return the acctype
	 */
	public String getAcctype() {
		return acctype;
	}

	/**
	 * @param acctype
	 *            the acctype to set
	 */
	public void setAcctype(String acctype) {
		this.acctype = acctype;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Map<String, String>> getTokens() {
		return tokens;
	}

	public void setToken(List<Map<String, String>> tokens) {
		this.tokens = tokens;
	}

	/**
	 * @return the isFirstCard
	 */
	public String getIsFirstCard() {
		return isFirstCard;
	}

	/**
	 * @param isFirstCard
	 *            the isFirstCard to set
	 */
	public void setIsFirstCard(String isFirstCard) {
		this.isFirstCard = isFirstCard;
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
	 * @return the bankRoutingNumber
	 */
	public String getBankRoutingNumber() {
		return bankRoutingNumber;
	}

	/**
	 * @param bankRoutingNumber
	 *            the bankRoutingNumber to set
	 */
	public void setBankRoutingNumber(String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}

	/**
	 * @return the acctHolderName
	 */
	public String getAcctHolderName() {
		return acctHolderName;
	}

	/**
	 * @param acctHolderName
	 *            the acctHolderName to set
	 */
	public void setAcctHolderName(String acctHolderName) {
		this.acctHolderName = acctHolderName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @param tokens
	 *            the tokens to set
	 */
	public void setTokens(List<Map<String, String>> tokens) {
		this.tokens = tokens;
	}

	/**
	 * @return the udk
	 */
	public String getUdk() {
		return udk;
	}

	/**
	 * @param udk
	 *            the udk to set
	 */
	public void setUdk(String udk) {
		this.udk = udk;
	}

	/**
	 * @return the tokenexpDate
	 */
	public String getTokenexpDate() {
		return tokenexpDate;
	}

	/**
	 * @param tokenexpDate
	 *            the tokenexpDate to set
	 */
	public void setTokenexpDate(String tokenexpDate) {
		this.tokenexpDate = tokenexpDate;
	}

	@Override
	public String toString() {

		return "[ Response Message=> accountId : " + getAccId() + ", type : "
				+ getType() + " , accNo : " + getAccNo() + ",  message : "
				+ getMsg() + ",tokens : " + getTokens() + ",acctype : "
				+ getAcctype() + ",isFirstCard : " + getIsFirstCard()
				+ " , udk : " + getUdk() + " , tokenexpDate : "
				+ getTokenexpDate() + " , bankName : " + getBankName()
				+ " , bankRoutingNumber : " + getBankRoutingNumber()
				+ " , acctHolderName : " + getAcctHolderName() + " , userId : "
				+ getUserId() + " , cardExpiry : " + getCardExpiry() + "]";
	}

}
