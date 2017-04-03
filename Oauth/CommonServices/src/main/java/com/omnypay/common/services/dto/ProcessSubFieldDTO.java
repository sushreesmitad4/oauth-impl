package com.omnypay.common.services.dto;

public class ProcessSubFieldDTO {

	private String track2;
	private String mid;
	private String mname;
	private String mtype;
	private String maddress;
	private String amount;
	private String tid;
	private String token;
	private String isProcessReq;
	private String accType;
	private boolean split;
	private String splitAmount;
	
	
	

	/**
	 * @return the isProcess
	 */

	/**
	 * @return the split
	 */
	public boolean isSplit() {
		return split;
	}

	/**
	 * @param split the split to set
	 */
	public void setSplit(boolean split) {
		this.split = split;
	}

	/**
	 * @return the splitAmount
	 */
	public String getSplitAmount() {
		return splitAmount;
	}

	/**
	 * @param splitAmount the splitAmount to set
	 */
	public void setSplitAmount(String splitAmount) {
		this.splitAmount = splitAmount;
	}

	/**
	 * @return the track2
	 */
	public String getTrack2() {
		return track2;
	}

	/**
	 * @return the isProcessReq
	 */
	public String getIsProcessReq() {
		return isProcessReq;
	}

	/**
	 * @param isProcessReq
	 *            the isProcessReq to set
	 */
	public void setIsProcessReq(String isProcessReq) {
		this.isProcessReq = isProcessReq;
	}

	/**
	 * @param track2
	 *            the track2 to set
	 */
	public void setTrack2(String track2) {
		this.track2 = track2;
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
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}

	/**
	 * @param mname
	 *            the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}

	/**
	 * @return the mtype
	 */
	public String getMtype() {
		return mtype;
	}

	/**
	 * @param mtype
	 *            the mtype to set
	 */
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	/**
	 * @return the maddress
	 */
	public String getMaddress() {
		return maddress;
	}

	/**
	 * @param maddress
	 *            the maddress to set
	 */
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String toString() {

		return "[ Request Message=> track2 : " + getTrack2() + " , mid : "
				+ getMid() + " , mname : " + getMname() + ",mtype : "
				+ getMtype() + ",maddress : " + getMaddress() + ",amount : "
				+ getAmount() + ",tid : " + getTid() + " , token : "
				+ getToken() + " , isProcessReq : " + getIsProcessReq()
				+ " , accType : " + getAccType() + "]";
	}

}
