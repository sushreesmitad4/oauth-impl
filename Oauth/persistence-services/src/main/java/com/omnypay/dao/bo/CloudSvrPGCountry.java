/**
 * 
 */
package com.omnypay.dao.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author iliyasm
 *
 */

@Entity
@Table(name="PG_COUNTRY")
public class CloudSvrPGCountry implements Serializable {
	 
	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @Column(name="COUNTRY_ID")
	 private Long countryId;
	 
	 
	 @Column(name = "COUNTRY_NAME")
	 private String countryName;
	 
	 @Column(name = "STATUS")
	 private Long status;
	 
	 
	 
	 
	 

	/**
	 * 
	 */
	public CloudSvrPGCountry() {
		
	}

	/**
	 * @return the countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}
	 
	 
	 
	 
}
