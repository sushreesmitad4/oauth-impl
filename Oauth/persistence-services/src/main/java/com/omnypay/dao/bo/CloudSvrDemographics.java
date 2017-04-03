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
@Table(name="PG_ENTITY_DEMOGRAPHICS")
public class CloudSvrDemographics implements Serializable {
	 
	 private static final long serialVersionUID = 1L;
	 
	 
	 
	 @Id
	 @Column(name="ENTITY_DEMOGRAPHICS_ID")
	 private Long demoId;
	 
	 	
	 @Column(name="ENTITY_ID")
	 private Long entityId;
	 
	 
	 @Column(name = "ADDRESS_1")
	 private String address;
	 
	 
	 @Column(name = "ADDRESS_2")
	 private String address2;
	 
	 
	 @Column(name = "CITY")
	 private String city;
	 
	 
	 @Column(name = "STATE_ID")
	 private Long state;
	 
	 
	 @Column(name = "COUNTRY")
	 private Long   country;
	 
	 @Column(name = "ZIPCODE")
	 private String zipcode;
	 
	 
	 
	 @Column(name = "STATUS")
	 private String status;
	 



	 //constructor
	 public CloudSvrDemographics() { 
	  
	 }
	 
	/**
	 * @return the demoId
	 */
	public Long getDemoId() {
		return demoId;
	}

	/**
	 * @param demoId the demoId to set
	 */
	public void setDemoId(Long demoId) {
		this.demoId = demoId;
	}

	/**
	 * @return the entityId
	 */
	public Long getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
		
	
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public Long getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Long state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public Long getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Long country) {
		this.country = country;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
	 
	 

}
