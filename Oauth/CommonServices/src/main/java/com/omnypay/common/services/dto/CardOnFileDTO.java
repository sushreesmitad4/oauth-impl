/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class CardOnFileDTO {

	private String userId;

	private String accId;

	private String accNo;

	private String accHolderName;

	private String accBankName;

	private String accCardExpDate;

	private String accCardExpYear;

	private String accCardCvvCode;

	// savings,current
	private String accBankType;

	// credit,debit,bank
	private String accType;

	// Visa,Master,card nickname
	private String cardNickName;

	private String accBankRout;

	private String accStreet;

	private String accCity;

	private String accState;

	private String accZipcode;

	private String isFirstCard;

	private String accTransactionPwd;

	private String accBrand;

	/**
	 * @return the accCardExpYear
	 */
	public String getAccCardExpYear() {
		return accCardExpYear;
	}

	/**
	 * @param accCardExpYear
	 *            the accCardExpYear to set
	 */
	public void setAccCardExpYear(String accCardExpYear) {
		this.accCardExpYear = accCardExpYear;
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
	 * @return the acctNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * @param acctNo
	 *            the acctNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
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
	 * @return the accBankName
	 */
	public String getAccBankName() {
		return accBankName;
	}

	/**
	 * @param accBankName
	 *            the accBankName to set
	 */
	public void setAccBankName(String accBankName) {
		this.accBankName = accBankName;
	}

	/**
	 * @return the accCardExpDate
	 */
	public String getAccCardExpDate() {
		return accCardExpDate;
	}

	/**
	 * @param accCardExpDate
	 *            the accCardExpDate to set
	 */
	public void setAccCardExpDate(String accCardExpDate) {
		this.accCardExpDate = accCardExpDate;
	}

	/**
	 * @return the accCardCvvCode
	 */
	public String getAccCardCvvCode() {
		return accCardCvvCode;
	}

	/**
	 * @param accCardCvvCode
	 *            the accCardCvvCode to set
	 */
	public void setAccCardCvvCode(String accCardCvvCode) {
		this.accCardCvvCode = accCardCvvCode;
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

	@Override
	public String toString() {
		return "[ Request Message=> userId : " + getUserId() + " , accId : "
				+ getAccId() + " , accNo : " + "xxxxxxxxxx"
				+ " , accHolderName : " + getAccHolderName()
				+ " , accBankName : " + getAccBankName()
				+ " , accCardExpDate : " + "xx" + " , accCardExpYear : " + "xx"
				+ " , accCardCvvCode : " + "xxxx" + " , accBankType : "
				+ getAccBankType() + " , accType : " + getAccType()
				+ " , cardNickName : " + getCardNickName()
				+ " , accBankRout : " + getAccBankRout() + " , accStreet : "
				+ getAccStreet() + " , accCity : " + getAccCity()
				+ " , accState : " + getAccState() + " , accZipcode : "
				+ getAccZipcode() + " , isFirstCard : " + getIsFirstCard()
				+ " , accTransactionPwd : " + getAccTransactionPwd()
				+ " , accBrand : " + getAccBrand() + "]";
	}

}
