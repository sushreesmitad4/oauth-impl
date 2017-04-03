package com.omnypay.dao.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the CLOUD_SVR_SEC_QUES_MASTER database table.
 * 
 */
@Entity
@Table(name = "CLOUD_SVR_SEC_QUES_MASTER")

public class CloudSvrSecQuesMaster implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "QUESTION")
	private String question;
	
	@Column(name = "ISACTIVE")
	private String isActive;
	
	
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	

	public CloudSvrSecQuesMaster() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsactive() {
		return this.isActive;
	}

	public void setIsactive(String isactive) {
		this.isActive = isactive;
	}

	
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}