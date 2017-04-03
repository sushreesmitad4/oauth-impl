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
public class HceTokenRespDTO {

	private String message;

	private String statusCode;

	private List<Map<String, String>> tokens;

	private String udk;

	private String tokenType;

	private String serviceRefId;

	private String tokenexpDate;

	private String onlinetokens;

	private String accountRefId;

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
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType
	 *            the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @return the serviceRefId
	 */
	public String getServiceRefId() {
		return serviceRefId;
	}

	/**
	 * @param serviceRefId
	 *            the serviceRefId to set
	 */
	public void setServiceRefId(String serviceRefId) {
		this.serviceRefId = serviceRefId;
	}

	public List<Map<String, String>> getTokens() {
		return tokens;
	}

	public void setTokens(List<Map<String, String>> tokens) {
		this.tokens = tokens;
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
	 * @return the onlinetokens
	 */
	public String getOnlinetokens() {
		return onlinetokens;
	}

	/**
	 * @param onlinetokens
	 *            the onlinetokens to set
	 */
	public void setOnlinetokens(String onlinetokens) {
		this.onlinetokens = onlinetokens;
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
	 * @return the accountRefId
	 */
	public String getAccountRefId() {
		return accountRefId;
	}

	/**
	 * @param accountRefId
	 *            the accountRefId to set
	 */
	public void setAccountRefId(String accountRefId) {
		this.accountRefId = accountRefId;
	}

	@Override
	public String toString() {

		return "[ Response Message=> message : " + getMessage()
				+ ", statusCode : " + getStatusCode() + " ,  tokenType : "
				+ getTokenType() + " , serviceRefId : " + getServiceRefId()
				+ " , tokens : " + getTokens() + ", tokensExpDate : "
				+ getTokenexpDate() + ", online token : " + getOnlinetokens()
				+ ",udk : " + getUdk() + " , accountRefId : "+getAccountRefId()+"]";
	}

}
