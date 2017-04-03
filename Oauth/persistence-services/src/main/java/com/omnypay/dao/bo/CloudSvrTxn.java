package com.omnypay.dao.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author iliyasm
 *
 */
@Entity
@Table(name = "SWITCH_SVR_TXN")
@NamedQuery(name = "CloudSvrTxn.findAll", query = "SELECT c FROM CloudSvrTxn c")
public class CloudSvrTxn implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "SWITCH_TXN_ID")
	private Long Id;
	
	@Column(name = "TXN_TYPE")
	private String txnType;
	
		
	@Column(name = "TXN_ID")
	private Long txnId;
	
	
	@Column(name = "TXN_AMOUNT")
	private Double amount;
	
	
	@Column(name = "TXN_START_DATE")
	private Timestamp txnDate;
	
	

	@Column(name = "CARD_TYPE")
	private String cardType;
	
	@Column(name = "CARD_NO")
	private String cardNo;
	
	@Column(name = "CARD_EXPIRY")
	private String cardExp;
	

	
	@Column(name = "BANK_NAME")
	private String bankName;
	
	
	@Column(name = "BANK_ACC_NO")
	private String bankAcc_No;
	
	
	@Column(name = "BANK_ROUTING_NO")
	private String bankRNo;
	
	@Column(name = "MERCHANT_ID")
	private String merId;
	
		
	@Column(name = "TERMINAL_ID")
	private String tId;
	
	
	@Column(name = "INVOICE_NO")
	private String invoiceNo;
	
	
	@Column(name = "ACC_TYPE")
	private String acctype;
	
	
	
	@Column(name="AUTH_CODE")
	private Long authCode;
	
	
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	
	@Column(name = "MERCHANT_NAME")
	private String merchantName;
	
	
	@Column(name = "CHANNEL")
	private String channel;
	
	
	@Column(name = "TXN_STATUS")
	private String txnStatus;
	
		
	
	@Transient
	private String toDate; 
	
	
	@Transient
	private String fromDate; 
	
	
	
	
	public CloudSvrTxn() {
	}
			

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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}


	
	



	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}



	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}




	/**
	 * @return the txnType
	 *//*
	public Long getTxnType() {
		return txnType;
	}




	*//**
	 * @param txnType the txnType to set
	 *//*
	public void setTxnType(Long txnType) {
		this.txnType = txnType;
	}*/




/*	*//**
	 * @return the bankId
	 *//*
	public Long getBankId() {
		return bankId;
	}




	*//**
	 * @param bankId the bankId to set
	 *//*
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}*/





	/**
	 * @return the tId
	 */
	public String gettId() {
		return tId;
	}




	/**
	 * @param tId the tId to set
	 */
	public void settId(String tId) {
		this.tId = tId;
	}




	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}




	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}




/*	*//**
	 * @return the rRNNo
	 *//*
	public String getrRNNo() {
		return rRNNo;
	}




	*//**
	 * @param rRNNo the rRNNo to set
	 *//*
	public void setrRNNo(String rRNNo) {
		this.rRNNo = rRNNo;
	}*/





	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}


	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


/*	*//**
	 * @return the txnId
	 *//*
	public String getTxnId() {
		return txnId;
	}


	*//**
	 * @param txnId the txnId to set
	 *//*
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}*/


	

	/**
	 * @return the authCode
	 */
	public Long getAuthCode() {
		return authCode;
	}


	/**
	 * @param authCode the authCode to set
	 */
	public void setAuthCode(Long authCode) {
		this.authCode = authCode;
	}

	
	
	/**
	 * @return the merId
	 */
	public String getMerId() {
		return merId;
	}


	/**
	 * @param merId the merId to set
	 */
	public void setMerId(String merId) {
		this.merId = merId;
	}


	/**
	 * @return the txnDate
	 */
	public Timestamp getTxnDate() {
		return txnDate;
	}


	/**
	 * @param txnDate the txnDate to set
	 */
	public void setTxnDate(Timestamp txnDate) {
		this.txnDate = txnDate;
	}


	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}


	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}


	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	/**
	 * @return the merchantId
	 *//*
	public String getMerchantId() {
		return merchantId;
	}


	*//**
	 * @param merchantId the merchantId to set
	 *//*
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}*/


	/**
	 * @return the cardExp
	 */
	public String getCardExp() {
		return cardExp;
	}


	/**
	 * @param cardExp the cardExp to set
	 */
	public void setCardExp(String cardExp) {
		this.cardExp = cardExp;
	}


	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}


	/**
	 * @param txnType the txnType to set
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}


	/**
	 * @return the txnId
	 */
	public Long getTxnId() {
		return txnId;
	}


	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}




	/**
	 * @return the bankAcc_No
	 */
	public String getBankAcc_No() {
		return bankAcc_No;
	}


	/**
	 * @param bankAcc_No the bankAcc_No to set
	 */
	public void setBankAcc_No(String bankAcc_No) {
		this.bankAcc_No = bankAcc_No;
	}


	/**
	 * @return the bankRNo
	 */
	public String getBankRNo() {
		return bankRNo;
	}


	/**
	 * @param bankRNo the bankRNo to set
	 */
	public void setBankRNo(String bankRNo) {
		this.bankRNo = bankRNo;
	}


	/**
	 * @return the acctype
	 */
	public String getAcctype() {
		return acctype;
	}


	/**
	 * @param acctype the acctype to set
	 */
	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}


	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}


	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}


	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}


	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	
	
	
	/**
	 * @return the txnStatus
	 */
	public String getTxnStatus() {
		return txnStatus;
	}


	/**
	 * @param txnStatus the txnStatus to set
	 */
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}


	
	

	/**
	 * @return the accNo
	 *//*
	public String getAccNo() {
		return accNo;
	}


	*//**
	 * @param accNo the accNo to set
	 *//*
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}*/



	
	
}
