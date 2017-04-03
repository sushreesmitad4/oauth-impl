/**
 * 
 */
package com.omnypay.common.services.dto;

/**
 * @author iliyasm
 *
 */
public class MerchantAccessDTO {
	
	
	MerchantName merchantNAme;
	
	
	
	Boolean isExternal;
	
	

	/**
	 * @return the merchantNAme
	 */
	public MerchantName getMerchantNAme() {
		return merchantNAme;
	}

	/**
	 * @param merchantNAme the merchantNAme to set
	 */
	public void setMerchantNAme(MerchantName merchantNAme) {
		this.merchantNAme = merchantNAme;
	}

	/**
	 * @return the isExternal
	 */
	public Boolean getIsExternal() {
		return isExternal;
	}

	/**
	 * @param isExternal the isExternal to set
	 */
	public void setIsExternal(Boolean isExternal) {
		this.isExternal = isExternal;
	}
	
	
	

}
