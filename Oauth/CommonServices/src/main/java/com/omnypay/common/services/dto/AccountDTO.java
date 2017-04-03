package com.omnypay.common.services.dto;

import java.util.List;
import java.util.Map;

public class AccountDTO extends BaseDTO {

	private String acctId;

	private String acctNo;

	private String acctHolderName;

	private String acctBankName;

	private String acctCardExpDate;

	private String acctCardExpYear;

	private String accCardVfcCode;

	// Visa or mastor,card nick name
	private String cardNickName;

	private String accBankType;

	// CC DB card
	private String accType;

	private String accBankRout;

	private String accStreet;

	private String accCity;

	private String accState;

	private String accZipcode;

	private String isFirstCard;

	private String accTransactionPwd;

	private String accBrand;

	private String tokenexpDate;

	private List<Map<String, String>> tokens;

	private String udk;

	private String accCheckScanData;

	/**
	 * @return the accCheckScanData
	 */
	public String getAccCheckScanData() {
		return accCheckScanData;
	}

	/**
	 * @param accCheckScanData
	 *            the accCheckScanData to set
	 */
	public void setAccCheckScanData(String accCheckScanData) {
		this.accCheckScanData = accCheckScanData;
	}

	/**
	 * @return the acctId
	 */
	public String getAcctId() {
		return acctId;
	}

	/**
	 * @param acctId
	 *            the acctId to set
	 */
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	/**
	 * @return the acctNo
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * @param acctNo
	 *            the acctNo to set
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
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
	 * @return the acctBankName
	 */
	public String getAcctBankName() {
		return acctBankName;
	}

	/**
	 * @param acctBankName
	 *            the acctBankName to set
	 */
	public void setAcctBankName(String acctBankName) {
		this.acctBankName = acctBankName;
	}

	/**
	 * @return the acctCardExpDate
	 */
	public String getAcctCardExpDate() {
		return acctCardExpDate;
	}

	/**
	 * @param acctCardExpDate
	 *            the acctCardExpDate to set
	 */
	public void setAcctCardExpDate(String acctCardExpDate) {
		this.acctCardExpDate = acctCardExpDate;
	}

	/**
	 * @return the acctCardExpYear
	 */
	public String getAcctCardExpYear() {
		return acctCardExpYear;
	}

	/**
	 * @param acctCardExpYear
	 *            the acctCardExpYear to set
	 */
	public void setAcctCardExpYear(String acctCardExpYear) {
		this.acctCardExpYear = acctCardExpYear;
	}

	/**
	 * @return the accCardVfcCode
	 */
	public String getAccCardVfcCode() {
		return accCardVfcCode;
	}

	/**
	 * @param accCardVfcCode
	 *            the accCardVfcCode to set
	 */
	public void setAccCardVfcCode(String accCardVfcCode) {
		this.accCardVfcCode = accCardVfcCode;
	}

	/**
	 * @return the cardNickName
	 */
	public String getCardNickName() {
		return cardNickName;
	}

	/**
	 * @param cardNickName
	 *            the cardNickName to set
	 */
	public void setCardNickName(String cardNickName) {
		this.cardNickName = cardNickName;
	}

	/**
	 * @return the accBankType
	 */
	public String getAccBankType() {
		return accBankType;
	}

	/**
	 * @param accBankType
	 *            the accBankType to set
	 */
	public void setAccBankType(String accBankType) {
		this.accBankType = accBankType;
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
	 * @return the accStreet
	 */
	public String getAccStreet() {
		return accStreet;
	}

	/**
	 * @param accStreet
	 *            the accStreet to set
	 */
	public void setAccStreet(String accStreet) {
		this.accStreet = accStreet;
	}

	/**
	 * @return the accCity
	 */
	public String getAccCity() {
		return accCity;
	}

	/**
	 * @param accCity
	 *            the accCity to set
	 */
	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	/**
	 * @return the accState
	 */
	public String getAccState() {
		return accState;
	}

	/**
	 * @param accState
	 *            the accState to set
	 */
	public void setAccState(String accState) {
		this.accState = accState;
	}

	/**
	 * @return the accZipcode
	 */
	public String getAccZipcode() {
		return accZipcode;
	}

	/**
	 * @param accZipcode
	 *            the accZipcode to set
	 */
	public void setAccZipcode(String accZipcode) {
		this.accZipcode = accZipcode;
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

	/**
	 * @return the tokens
	 */
	public List<Map<String, String>> getTokens() {
		return tokens;
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
	 * @return the accTransactionPwd
	 */
	public String getAccTransactionPwd() {
		return accTransactionPwd;
	}

	/**
	 * @param accTransactionPwd
	 *            the accTransactionPwd to set
	 */
	public void setAccTransactionPwd(String accTransactionPwd) {
		this.accTransactionPwd = accTransactionPwd;
	}

	/**
	 * @return the accBrand
	 */
	public String getAccBrand() {
		return accBrand;
	}

	/**
	 * @param accBrand
	 *            the accBrand to set
	 */
	public void setAccBrand(String accBrand) {
		this.accBrand = accBrand;
	}

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + ",acctId : " + getAcctId() + " , acctNo : "
				+ getAcctNo() + " ,acctHolderName : " + getAcctHolderName()
				+ " , acctBankName : " + getAcctBankName()
				+ " , acctCardExpDate : " + getAcctCardExpDate()
				+ " , acctCardExpYear : " + getAcctCardExpYear()
				+ " ,accCardVfcCode : " + getAccCardVfcCode()
				+ " , cardNickName : " + getCardNickName()
				+ " , accBankType : " + getAccBankType() + " , accType : "
				+ getAccType() + " , accBankRout : " + getAccBankRout()
				+ " , accStreet : " + getAccStreet() + " , accCity : "
				+ getAccCity() + " , accState : " + getAccState()
				+ " , accZipcode : " + getAccZipcode() + " , isFirstCard : "
				+ getIsFirstCard() + "  accTransactionPwd : "
				+ getAccTransactionPwd() + " , accBrand : " + getAccBrand()
				+ " , tokenexpDate : " + getTokenexpDate() + " , tokens : "
				+ getTokens() + " , udk : " + getUdk()
				+ " , accCheckScanData : " + getAccCheckScanData()
				+ " , merchantAccessKey : " + getMerchantAccessKey() + "]";
	}

}
