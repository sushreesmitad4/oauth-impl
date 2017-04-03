package com.omnypay.dao.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the CLOUD_SVR_USERS database table.
 * 
 */
@Entity
@Table(name = "CLOUD_SVR_USERS")
@NamedQuery(name = "CloudSvrUser.findAll", query="SELECT c FROM CloudSvrUser c")
public class CloudSvrUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*
	 @Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private long userId;*/
	
	@Id
	@Nonnull
	@Column(name = "USER_ID")
	@GenericGenerator(name = "db-uuid", strategy = "guid")
	@GeneratedValue(generator = "db-uuid")
	private String userId;
	
	
	
	//BUSINESS_ID 
	@Column(name = "BUSINESS_ID")
	private String businessId;
	
	@Column(name = "Client_User_ID ")
	private String clientUserId ;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	
	@Column(name = "HOME_PHONE_NO")
	private String homephoneNo;
	

	@Column(name = "IMEI_NO")
	private String imeiNo;
	
	
	
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "PASS_CODE")
	private String passCode;

	
	@Column(name = "BADLOGINCOUNT")
	private BigDecimal badLoginCount;
	
	@Column(name = "ISLOGIN")
	private String isLogin;
	
	@Column(name = "ISLOCKED")
	private String isLocked;
	
	
	@Column(name = "BAD_SECURITY_ANS_COUNT")
	private BigDecimal badSeccurityAnsCount;
	
	@Column(name = "RESET_PASS_VAL_STRING")
	private String resetPassValString;
	
	
	@Column(name = "RESET_PASS_LINK_CREATEDTIME")
	private Timestamp resetPassLinkCreatedTime;

	
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER,cascade=CascadeType.ALL)	
	private List<CloudSvrUsersSecQuestion> listSecQuestion;
	
	
		
	@OneToOne(mappedBy = "user", fetch=FetchType.LAZY,cascade=CascadeType.ALL)	
	//@OneToMany(mappedBy = "user", fetch=FetchType.EAGER,cascade=CascadeType.ALL)	
	private CloudSvrUsersProfile usersProfile;
	

	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CloudSvrPasswordsHistory> userPassList;

	
	public CloudSvrUser() {
	}



	public Object getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getImeiNo() {
		return this.imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	

	public Object getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPassCode() {
		return this.passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	

	/**
	 * @return the badLoginCount
	 */
	public BigDecimal getBadLoginCount() {
		return badLoginCount;
	}

	/**
	 * @param badLoginCount the badLoginCount to set
	 */
	public void setBadLoginCount(BigDecimal badLoginCount) {
		this.badLoginCount = badLoginCount;
	}

	/**
	 * @return the isLogin
	 */
	public String getIsLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin the isLogin to set
	 */
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}



	/**
	 * @return the isLocked
	 */
	public String getIsLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked the isLocked to set
	 */
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	


	public BigDecimal getBadSeccurityAnsCount() {
		return badSeccurityAnsCount;
	}

	public void setBadSeccurityAnsCount(BigDecimal badSeccurityAnsCount) {
		this.badSeccurityAnsCount = badSeccurityAnsCount;
	}



	/**
	 * @return the resetPassValString
	 */
	public String getResetPassValString() {
		return resetPassValString;
	}

	/**
	 * @param resetPassValString the resetPassValString to set
	 */
	public void setResetPassValString(String resetPassValString) {
		this.resetPassValString = resetPassValString;
	}

	/**
	 * @return the resetPassLinkCreatedTime
	 */
	public Timestamp getResetPassLinkCreatedTime() {
		return resetPassLinkCreatedTime;
	}

	/**
	 * @param resetPassLinkCreatedTime the resetPassLinkCreatedTime to set
	 */
	public void setResetPassLinkCreatedTime(Timestamp resetPassLinkCreatedTime) {
		this.resetPassLinkCreatedTime = resetPassLinkCreatedTime;
	}

	

	
	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return the clientUserId
	 */
	public String getClientUserId() {
		return clientUserId;
	}

	/**
	 * @param clientUserId the clientUserId to set
	 */
	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the homephoneNo
	 */
	public String getHomephoneNo() {
		return homephoneNo;
	}

	/**
	 * @param homephoneNo the homephoneNo to set
	 */
	public void setHomephoneNo(String homephoneNo) {
		this.homephoneNo = homephoneNo;
	}



	/**
	 * @return the listSecQuestion
	 */
	public List<CloudSvrUsersSecQuestion> getListSecQuestion() {
		return listSecQuestion;
	}



	/**
	 * @param listSecQuestion the listSecQuestion to set
	 */
	public void setListSecQuestion(List<CloudSvrUsersSecQuestion> listSecQuestion) {
		this.listSecQuestion = listSecQuestion;
	}



	/**
	 * @return the usersProfile
	 */
	public CloudSvrUsersProfile getUsersProfile() {
		return usersProfile;
	}



	/**
	 * @param usersProfile the usersProfile to set
	 */
	public void setUsersProfile(CloudSvrUsersProfile usersProfile) {
		this.usersProfile = usersProfile;
	}



	/**
	 * @return the userPassList
	 */
	public List<CloudSvrPasswordsHistory> getUserPassList() {
		return userPassList;
	}



	/**
	 * @param userPassList the userPassList to set
	 */
	public void setUserPassList(List<CloudSvrPasswordsHistory> userPassList) {
		this.userPassList = userPassList;
	}
	
	
	


	
	





}