package com.omnypay.dao.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the CLOUD_SVR_USERS_PROFILE database table.
 * 
 */
@Entity
@Table(name = "CLOUD_SVR_USERS_PROFILE")
@NamedQuery(name = "CloudSvrUsersProfile.findAll", query="SELECT c FROM CloudSvrUsersProfile c")
public class CloudSvrUsersProfile implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	/*@Id
	@Column(name = "USER_ID", nullable=false)
	@GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters=@Parameter(name = "property", value="user"))*/
    
	
	@Id
	@Column(name = "USER_PROFILE_ID")
	@GeneratedValue
	private Long usersProfileId;
	  
	@Column(name = "ADDRESS_1")
	private String address1;

	@Column(name = "ADDRESS_2")
	private String address2;

	@Column(name = "ADDRESS_3")
	private String address3;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	

	
	@Column(name = "CITY")
	private String city;
	
    
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "ZIP_CODE")
	private String zipCode;
	
		
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	
	@Column(name = "STATUS")
	private Long status;
	
	
	
	@OneToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private CloudSvrUser user;


/*	@OneToOne
	@PrimaryKeyJoinColumn
	private CloudSvrUser user;*/

	/*@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private CloudSvrUser user;*/

	@Transient
	private String emailId;
	
	@Transient
	private String newEmailId;


	
	
	/**
	 * @return the usersProfileId
	 */
	public long getUsersProfileId() {
		return usersProfileId;
	}

	/**
	 * @param usersProfileId the usersProfileId to set
	 */
	public void setUsersProfileId(long usersProfileId) {
		this.usersProfileId = usersProfileId;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
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
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	

	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the user
	 */
	public CloudSvrUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(CloudSvrUser user) {
		this.user = user;
	}



	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the newEmailId
	 */
	public String getNewEmailId() {
		return newEmailId;
	}

	/**
	 * @param newEmailId the newEmailId to set
	 */
	public void setNewEmailId(String newEmailId) {
		this.newEmailId = newEmailId;
	}

	/**
	 * @param usersProfileId the usersProfileId to set
	 */
	public void setUsersProfileId(Long usersProfileId) {
		this.usersProfileId = usersProfileId;
	}

	
	
	
}