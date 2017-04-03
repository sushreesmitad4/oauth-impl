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
public class AccountRespDTO extends Status {

	private List<Map<String, String>> tokens;
	private String accountId;
	private String tokenExpDate;
	private String udk;
	private String user;
	private String isFirstCard;

	// these fields we are setting
	// as response from miteckserver to mobile
	private String accBankRout;
	private String accNo;
	private String checkNo;

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<Map<String, String>> getTokens() {
		return tokens;
	}

	public void setTokens(List<Map<String, String>> tokens) {
		this.tokens = tokens;
	}

	/*
	 * public List<String> getToken() { return token; }
	 * 
	 * public void setToken(List<String> token) { this.token = token; }
	 */

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the tokenExpDate
	 */
	public String getTokenExpDate() {
		return tokenExpDate;
	}

	/**
	 * @param tokenExpDate
	 *            the tokenExpDate to set
	 */
	public void setTokenExpDate(String tokenExpDate) {
		this.tokenExpDate = tokenExpDate;
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
	 * @return the accBankRout
	 */
	public String getAccBankRout() {
		return accBankRout;
	}

	/**
	 * @param accBankRout
	 *            the accBankRout to set
	 */
	public void setAccBankRout(String accBankRout) {
		this.accBankRout = accBankRout;
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
	 * @return the checkNo
	 */
	public String getCheckNo() {
		return checkNo;
	}

	/**
	 * @param checkNo
	 *            the checkNo to set
	 */
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;

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

	public String toString() {

		return "[ Response Message=> code : " + getCode() + " , message : "
				+ getMessage() + " , type : " + getType() + ", accountId : "
				+ getAccountId() + ", tokens : " + getTokens()
				+ ", tokenExpDate : " + getTokenExpDate() + ", udk : "
				+ getUdk() + " , acctNo : " + getAccNo() + " , routingNo : "
				+ getAccBankRout() + " , checkNo : " + getCheckNo()
				+ " , user : " + getUser() + " , isFirstCard : "
				+ getIsFirstCard() + "]";

	}

}
