/**
 * 
 */
package com.omnypay.dao.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jagdishm
 *
 */
@Entity
@Table(name="KOLHS_USER_CARD_DETAILS")
@NamedQuery(name = "KolhsUserCardDetails.findAll", query="SELECT c FROM KolhsUserCardDetails c")
public class KolhsUserCardDetails {

	@Column(name = "USER_ID")
	private String userId;
	
	@Id
	@Column(name = "CARD_DETAIL_ID")
	private String cardDetailId;
	
	@Column(name = "CARD_POINTER")
	private String cardPointer;
	
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
	 * @return the cardDetailId
	 */
	public String getCardDetailId() {
		return cardDetailId;
	}
	/**
	 * @param cardDetailId the cardDetailId to set
	 */
	public void setCardDetailId(String cardDetailId) {
		this.cardDetailId = cardDetailId;
	}
	/**
	 * @return the cardPointer
	 */
	public String getCardPointer() {
		return cardPointer;
	}
	/**
	 * @param cardPointer the cardPointer to set
	 */
	public void setCardPointer(String cardPointer) {
		this.cardPointer = cardPointer;
	}
	
	
	
	
	
	
	
}
