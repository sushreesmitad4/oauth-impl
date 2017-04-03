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
@Table(name="PG_STATE")
public class CloudSvrPGState implements Serializable {
	 
	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @Column(name="STATE_ID")
	 private Long stateId;
	 
	 
	 @Column(name = "STATE_NAME")
	 private String stateName;
	 
	 @Column(name = "STATUS")
	 private Long status;
	 
	 
	 
	  

	/**
	 * 
	 */
	public CloudSvrPGState() {
		
	}

	
	
	

	/**
	 * @return the stateId
	 */
	public Long getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}


	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
