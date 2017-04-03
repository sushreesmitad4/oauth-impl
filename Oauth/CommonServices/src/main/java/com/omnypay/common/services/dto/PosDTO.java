/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class PosDTO {

	String parsedIsoPacket;

	String amount;

	String token;

	String posId;

	String tnxType;

	String tid;

	String mid;

	String track2;

	String accType;

	String shippingAddress1;
	
	String shippingAddress2;
	
	String shippingAddress3;
	
	
	String shippingCity;
	
	String shippingZipCode;
	
	String shippingState;
	

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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the parsedIsoPacket
	 */
	public String getParsedIsoPacket() {
		return parsedIsoPacket;
	}

	/**
	 * @param parsedIsoPacket
	 *            the parsedIsoPacket to set
	 */
	public void setParsedIsoPacket(String parsedIsoPacket) {
		this.parsedIsoPacket = parsedIsoPacket;
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
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param mid
	 *            the mid to set
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * @return the track2
	 */
	public String getTrack2() {
		return track2;
	}

	/**
	 * @param track2
	 *            the track2 to set
	 */
	public void setTrack2(String track2) {
		this.track2 = track2;
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

	

	public String getTnxType() {
		return tnxType;
	}

	public void setTnxType(String tnxType) {
		this.tnxType = tnxType;
	}
	
	
	
	
	

	/**
	 * @return the shippingAddress1
	 */
	public String getShippingAddress1() {
		return shippingAddress1;
	}

	/**
	 * @param shippingAddress1 the shippingAddress1 to set
	 */
	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}

	/**
	 * @return the shippingAddress2
	 */
	public String getShippingAddress2() {
		return shippingAddress2;
	}

	/**
	 * @param shippingAddress2 the shippingAddress2 to set
	 */
	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}

	/**
	 * @return the shippingAddress3
	 */
	public String getShippingAddress3() {
		return shippingAddress3;
	}

	/**
	 * @param shippingAddress3 the shippingAddress3 to set
	 */
	public void setShippingAddress3(String shippingAddress3) {
		this.shippingAddress3 = shippingAddress3;
	}

	/**
	 * @return the shippingCity
	 */
	public String getShippingCity() {
		return shippingCity;
	}

	/**
	 * @param shippingCity the shippingCity to set
	 */
	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	/**
	 * @return the shippingZipCode
	 */
	public String getShippingZipCode() {
		return shippingZipCode;
	}

	/**
	 * @param shippingZipCode the shippingZipCode to set
	 */
	public void setShippingZipCode(String shippingZipCode) {
		this.shippingZipCode = shippingZipCode;
	}

	/**
	 * @return the shippingState
	 */
	public String getShippingState() {
		return shippingState;
	}

	/**
	 * @param shippingState the shippingState to set
	 */
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	@Override
	public String toString() {

		return "[ Request Message=> parsedIsoPacket : " + getParsedIsoPacket()
				+ " , amount : " + getAmount() + " , token : " + getToken()
				+ " , posId : " + getPosId() + ", mId : " + getMid()
				+ ", tId : " + getTid() + ", track2 : " + getTrack2()
				+ " , tnxType : " + getTnxType() + " , accType : "
				+ getAccType() + " , shippingAddress1 : " + getShippingAddress1() + "]";
	}

}
