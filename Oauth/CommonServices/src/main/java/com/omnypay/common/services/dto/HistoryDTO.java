package com.omnypay.common.services.dto;

public class HistoryDTO extends BaseDTO {

	private String startDate;
	private String endDate;
	private String accountId;

	public HistoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String toString() {

		return "[ Request Message=> emailId : " + getEmailId()
				+ " , phoneNumber : " + getPhoneNumber() + " , imeiNo : "
				+ getImeiNo() + " , startDate : " + getStartDate()
				+ " , endDate : " + getEndDate() + " , accountId : "
				+ getAccountId() + " , merchantAccessKey : "
				+ getMerchantAccessKey() + "]";
	}

}
