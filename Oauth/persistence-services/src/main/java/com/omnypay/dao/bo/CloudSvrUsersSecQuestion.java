package com.omnypay.dao.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the CLOUD_SVR_USERS_SEC_QUESTIONS database table.
 * 
 */
@Entity
@Table(name = "CLOUD_SVR_USERS_SEC_QUESTIONS")
@NamedQuery(name = "CloudSvrUsersSecQuestion.findAll", 
			query = "SELECT c FROM CloudSvrUsersSecQuestion c")
public class CloudSvrUsersSecQuestion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "USERS_SEC_QUES_ID")
	private long usersSecQuesId;
	
	@Column(name = "SEC_QUES_ID")
	private long secQuesId;

	@Column(name = "SEC_QUES_ANSWER")
	private String secQuesAnswer;
	
	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private CloudSvrUser user;

	public CloudSvrUsersSecQuestion() {
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getSecQuesAnswer() {
		return this.secQuesAnswer;
	}

	public void setSecQuesAnswer(String secQuesAnswer) {
		this.secQuesAnswer = secQuesAnswer;
	}

	public long getSecQuesId() {
		return this.secQuesId;
	}

	public void setSecQuesId(long secQuesId) {
		this.secQuesId = secQuesId;
	}

	public long getUsersSecQuesId() {
		return this.usersSecQuesId;
	}

	public void setUsersSecQuesId(long usersSecQuesId) {
		this.usersSecQuesId = usersSecQuesId;
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


}