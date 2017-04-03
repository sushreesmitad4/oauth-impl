/**
 * 
 */
package com.omnypay.dao.bo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author iliyasm
 *
 */


//@Entity
//@Table(name = "CLOUD_SVR_TEMP_TOKEN")
//@NamedQuery(name = "CloudSvrTempToken.findAll", query="SELECT c FROM CloudSvrTempToken c")
public class CloudSvrTempToken {
	
	
	private static final long serialVersionUID = 1L;
	
	
	/*@Id
	@GeneratedValue
	@Column(name = "ID")*/
	private Long Id;
	
	//@Column(name = "SERVICE_ID")
	private String sId;
	
		
	//@Column(name = "TOKEN")
	private String token;
	
	
	//@Column(name = "TOKEN_CREATED_DATE")
	private Timestamp tokenCreatedDate;
	
	//@Column(name = "TOKEN_MODIFIED_DATE")
	private Timestamp tokenModifiedDate;
	
	
	//@Column(name = "TOKEN_EXPIRY_DATE")
	private String tokenExpiryDate;
	
	

	//@Column(name = "USER_ID")
	private String userId;



	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}



	/**
	 * @return the sId
	 */
	public String getsId() {
		return sId;
	}



	/**
	 * @param sId the sId to set
	 */
	public void setsId(String sId) {
		this.sId = sId;
	}



	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}



	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}



	



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	/**
	 * @return the tokenCreatedDate
	 */
	public Timestamp getTokenCreatedDate() {
		return tokenCreatedDate;
	}



	/**
	 * @param tokenCreatedDate the tokenCreatedDate to set
	 */
	public void setTokenCreatedDate(Timestamp tokenCreatedDate) {
		this.tokenCreatedDate = tokenCreatedDate;
	}



	/**
	 * @return the tokenModifiedDate
	 */
	public Timestamp getTokenModifiedDate() {
		return tokenModifiedDate;
	}



	/**
	 * @param tokenModifiedDate the tokenModifiedDate to set
	 */
	public void setTokenModifiedDate(Timestamp tokenModifiedDate) {
		this.tokenModifiedDate = tokenModifiedDate;
	}



	/**
	 * @return the tokenExpiryDate
	 */
	public String getTokenExpiryDate() {
		return tokenExpiryDate;
	}



	/**
	 * @param tokenExpiryDate the tokenExpiryDate to set
	 */
	public void setTokenExpiryDate(String tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}



	
	
	
	
	

}
