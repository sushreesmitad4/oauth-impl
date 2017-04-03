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
public class MitekServerRespDTO {

	// these fields we are setting
	// as response from miteckserver to mobile
	private String accBankRout;
	private String accNo;
	private String checkNo;

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

	public String toString() {

		return "[ Response Message=> acctNo : " + getAccNo()
				+ " , accBankRout : " + getAccBankRout() + " , checkNo : "
				+ getCheckNo() + "]";

	}

}
