package com.omnypay.common.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;




















import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.AccountRespDTO;
import com.omnypay.common.services.dto.BankDTO;
import com.omnypay.common.services.dto.BaseDTO;
import com.omnypay.common.services.dto.CouponDTO;
import com.omnypay.common.services.dto.CouponRespDTO;
import com.omnypay.common.services.dto.CouponSubfieldDTO;
import com.omnypay.common.services.dto.CustomerRespDTO;
import com.omnypay.common.services.dto.DiscountRespDTO;
import com.omnypay.common.services.dto.HistorySubFieldsDTO;
import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.common.services.dto.MitekServerRespDTO;
import com.omnypay.common.services.dto.PasswordDTO;
import com.omnypay.common.services.dto.PosDTO;
import com.omnypay.common.services.dto.ProcessSubFieldDTO;
import com.omnypay.common.services.dto.RespSubFieldsDTO;
import com.omnypay.common.services.dto.RewardDTO;
import com.omnypay.common.services.dto.RewardRespDTO;
import com.omnypay.common.services.dto.RewardSubfieldDTO;
import com.omnypay.common.services.dto.SecurityQuestionsRespDTO;
import com.omnypay.common.services.dto.Status;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;
import com.omnypay.common.services.dto.UpdateUserDTO;
import com.omnypay.common.services.dto.UserDTO;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.dao.bo.CloudSvrTxn;

/**
 * 
 * @author iliyasm
 *
 */
public class WebServiceUtil {

	public static boolean checkMandatoryFields(Object object) {

		if (object instanceof BaseDTO) {
			
			BaseDTO baseDTO = (BaseDTO)object;
			
			}
		if (object instanceof LoginDTO) {
			
			LoginDTO loginDTO = (LoginDTO)object;
			
			if (loginDTO.getEmailId().trim().isEmpty()
					|| loginDTO.getPasscode().trim().isEmpty()
					|| loginDTO.getPhoneNumber().trim().isEmpty()) {

				return false;
			}
		}
		else if(object instanceof UserDTO) {
			
			UserDTO userDTO = (UserDTO)object;
			
			boolean isEmailCheckRequired = false;
			
			if ( (userDTO.getPhoneNumber().trim().isEmpty()) ) {
 
				isEmailCheckRequired = true;
			}
			if(isEmailCheckRequired && userDTO.getEmailId().trim().isEmpty()){
				
				return true;
			}
			
		}
		else if(object instanceof PasswordDTO) {
			
			PasswordDTO forgotPwdDTO = (PasswordDTO)object;
					
			if (forgotPwdDTO.getAnswer1().trim().isEmpty()
					|| forgotPwdDTO.getEmailId().trim().isEmpty()
					|| forgotPwdDTO.getAnswer2().trim().isEmpty()
					|| forgotPwdDTO.getPhoneNumber().trim().isEmpty()
					|| forgotPwdDTO.getSecurityQuestion1().trim().isEmpty()
					|| forgotPwdDTO.getSecurityQuestion2().trim().isEmpty()
					
				) {

				return false;
			}
						
		}
		else if(object instanceof BankDTO) {
			
			BankDTO bankDTO = (BankDTO)object;
					
					if (bankDTO.getBankName().trim().isEmpty()
							|| bankDTO.getBankAcctNo().trim().isEmpty()
							|| bankDTO.getAcctHolderName().trim().isEmpty()
							|| bankDTO.getAcctType().trim().isEmpty()
							|| bankDTO.getBankRoutingNo().trim().isEmpty()
							|| bankDTO.getCity().trim().isEmpty()
							|| bankDTO.getEmailId().trim().isEmpty()
							|| bankDTO.getPhoneNumber().trim().isEmpty()
							|| bankDTO.getState().trim().isEmpty()
							|| bankDTO.getStreet().trim().isEmpty()
							|| bankDTO.getZip().trim().isEmpty()
						) {
		
						return false;
					}
						
				}
		
     
		return true;
	}
	
	public static Status getStatus(String message,String type) {
		
		Status status = new Status();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		
		return status;
	}
	
	
	
	public static TransactionInitiateRespDTO getStatusForAmountSave(String message,String type) {
		
		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		
		return status;
	}
	
	public static TransactionInitiateRespDTO getTxnStatus(String message,String type) {
		
		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		
		return status;
	}
	
	
	public static AccountRespDTO getDeleteStatus(String message,String type) {
		
	AccountRespDTO status = new AccountRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		
		return status;
	}

public static AccountRespDTO getAccount(String message,String type,String accountId,List<Map<String,String>> token,String tokenExpDate,String udk) {
		
		AccountRespDTO status = new AccountRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setAccountId(accountId);
		status.setTokens(token);
		status.setTokenExpDate(tokenExpDate);
		status.setUdk(udk);
	
		
		return status;
	}
    public static AccountRespDTO getAccountAddBank(String message,String type,String accountId) {
		
		AccountRespDTO status = new AccountRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setAccountId(accountId);
		
		
		return status;
	}
	
	public static TransactionInitiateRespDTO getInitiatePayment(String message,String type,String txnAmount,List<Map<String,String>> token,String isTxnEcomm) {
		
	
		TransactionInitiateRespDTO status =  new TransactionInitiateRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setTxnAmount(txnAmount);
		status.setToken(token);
		status.setIsTxnEcomm(isTxnEcomm);
		return status;
	}
	
	
	public static TransactionInitiateRespDTO getProcessPaymentNotDone(String message,String type) {
		
		
		TransactionInitiateRespDTO status =  new TransactionInitiateRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		
		
		
		return status;
	}
	

	public static TransactionInitiateRespDTO getPaymentHistory(String message,String type,List<CloudSvrTxn> svrTxns) {
		
		
		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();
		List<HistorySubFieldsDTO> subFieldsDTOs = new ArrayList<HistorySubFieldsDTO>();
		
		
			
		for(CloudSvrTxn txn:svrTxns){
			
			HistorySubFieldsDTO subFieldsDTO = new HistorySubFieldsDTO();
			subFieldsDTO.setTranId(txn.getTxnId().toString());
			subFieldsDTO.setTranDate(WebServiceUtil.timeField(txn.getTxnDate()));
			//subFieldsDTO.setTranDate(txn.getTxnDate().toString());
			subFieldsDTO.setMerchantId(txn.getMerId());
			
			if(txn.gettId()!=null && txn.gettId().length()!=0){
				subFieldsDTO.setTerminalId(txn.gettId().toString());
			}else {
			
				subFieldsDTO.setTerminalId(WebServiceConstants.NOT_APPLICABLE);
			}
			
			subFieldsDTO.setBatchId(WebServiceConstants.BATCH_ID);
			subFieldsDTO.setInvoiceNo(txn.getInvoiceNo());
			//subFieldsDTO.setAccNo(txn.getCardNo());
			//subFieldsDTO.setAccNo(txn.getAccNo());
			
			if(txn.getAcctype().equalsIgnoreCase(WebServiceConstants.CARD)){
				
				subFieldsDTO.setAccNo(txn.getCardNo());
				//subFieldsDTO.setAccExp(txn.getCardExp());
				if(txn.getCardExp()!=null){
				
					subFieldsDTO.setAccExp(txn.getCardExp());
					
				} else {
					
					subFieldsDTO.setAccExp(WebServiceConstants.NOT_APPLICABLE);
				}
				
				subFieldsDTO.setAccType(txn.getAcctype());
				
			} else if (txn.getAcctype().equalsIgnoreCase(WebServiceConstants.BANK)){
				
				subFieldsDTO.setAccNo(txn.getBankAcc_No());
				subFieldsDTO.setAccExp(WebServiceConstants.NOT_APPLICABLE);
				subFieldsDTO.setAccType(txn.getAcctype());
			}
			
			//subFieldsDTO.setAccExp(txn.getCardExp());
			//subFieldsDTO.setAccType(txn.getCardType());
			subFieldsDTO.setAuthNo(txn.getAuthCode().toString());
			subFieldsDTO.setrRNNo(txn.getTxnId().toString());
			subFieldsDTO.setAmount(txn.getAmount().toString());
			subFieldsDTO.setMerchantName(txn.getMerchantName());
			subFieldsDTO.setChannel(txn.getChannel());
			subFieldsDTOs.add(subFieldsDTO);
			status.setRequestedData(subFieldsDTOs);
		}
		
		
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		
		return status;
	}
	
	
	
	
public static List<HistorySubFieldsDTO> getDefaultPaymentHistory(List<CloudSvrTxn> svrTxns) {
		
		
		//HistoryRespDTO status = new HistoryRespDTO();
		List<HistorySubFieldsDTO> subFieldsDTOs = new ArrayList<HistorySubFieldsDTO>();
		
		
			
		for(CloudSvrTxn txn:svrTxns){
			
			HistorySubFieldsDTO subFieldsDTO = new HistorySubFieldsDTO();
			subFieldsDTO.setTranId(txn.getTxnId().toString());
			subFieldsDTO.setTranDate(WebServiceUtil.timeField(txn.getTxnDate()));
			//subFieldsDTO.setTranDate(txn.getTxnDate().toString());
			subFieldsDTO.setMerchantId(txn.getMerId());
			if(txn.gettId()!=null && txn.gettId().length()!=0){
				subFieldsDTO.setTerminalId(txn.gettId().toString());
			} else {
				
				subFieldsDTO.setTerminalId(WebServiceConstants.NOT_APPLICABLE);
			}
			
			subFieldsDTO.setBatchId(WebServiceConstants.BATCH_ID);
			subFieldsDTO.setInvoiceNo(txn.getInvoiceNo());
			//subFieldsDTO.setAccNo(txn.getCardNo());
			//subFieldsDTO.setAccNo(txn.getAccNo());
			if(txn.getAcctype().equalsIgnoreCase(WebServiceConstants.CARD)){
				
				subFieldsDTO.setAccNo(txn.getCardNo());
				subFieldsDTO.setAccExp(txn.getCardExp());
				subFieldsDTO.setAccType(txn.getAcctype());
				
			} else if (txn.getAcctype().equalsIgnoreCase(WebServiceConstants.BANK)){
				
				subFieldsDTO.setAccNo(txn.getBankAcc_No());
				subFieldsDTO.setAccExp(WebServiceConstants.NOT_APPLICABLE);
				subFieldsDTO.setAccType(txn.getAcctype());
			}
			
			subFieldsDTO.setMerchantName(txn.getMerchantName());
			subFieldsDTO.setChannel(txn.getChannel());
			subFieldsDTO.setAuthNo(txn.getAuthCode().toString());
			subFieldsDTO.setrRNNo(txn.getTxnId().toString());
			subFieldsDTO.setAmount(txn.getAmount().toString());

			subFieldsDTOs.add(subFieldsDTO);
			//status.setRequestedData(subFieldsDTOs);
		}
		
		
		
		
		return subFieldsDTOs;
	}
	
	public static TransactionInitiateRespDTO porccessPaymentDone(String message,String type,CloudSvrQrCodeTxn dbDetails) {
	
	
	
	/*HistoryRespDTO status = new HistoryRespDTO();
	
	List<HistorySubFieldsDTO> subFieldsDTOs = new ArrayList<HistorySubFieldsDTO>();
	
	//for(CloudSvrTxn txn:svrTxns){
		
		HistorySubFieldsDTO subFieldsDTO = new HistorySubFieldsDTO();
		
		subFieldsDTO.setTranId(txn.getTxnId().toString()); 
		//for removing milliseconds from the time what we get
		subFieldsDTO.setTranDate(WebServiceUtil.timeField(txn.getTxnDate()));
		//subFieldsDTO.setTranDate(txn.getTxnDate().toString());
		subFieldsDTO.setMerchantId(txn.getMerId());
		if(txn.gettId()!= null && txn.gettId().length()!= 0){
			subFieldsDTO.setTerminalId(txn.gettId().toString());
		}else{
			
			subFieldsDTO.setTerminalId(WebServiceConstants.NOT_APPLICABLE);
			
		}
		subFieldsDTO.setBatchId(WebServiceConstants.BATCH_ID);
		subFieldsDTO.setInvoiceNo(txn.getInvoiceNo());
		//subFieldsDTO.setAccNo(txn.getCardNo());
		//subFieldsDTO.setAccNo(txn.getAccNo());
		if(txn.getAcctype().equalsIgnoreCase(WebServiceConstants.CARD)){
			
			subFieldsDTO.setAccNo(txn.getCardNo());
			subFieldsDTO.setAccExp(txn.getCardExp());
			subFieldsDTO.setAccType(txn.getAcctype());
			
		} else if (txn.getAcctype().equalsIgnoreCase(WebServiceConstants.BANK)){
			
			subFieldsDTO.setAccNo(txn.getBankAcc_No());
			subFieldsDTO.setAccExp(WebServiceConstants.NOT_APPLICABLE);
			subFieldsDTO.setAccType(txn.getAcctype());
		}
		subFieldsDTO.setAuthNo(txn.getAuthCode().toString());
		subFieldsDTO.setrRNNo(txn.getTxnId().toString());
		subFieldsDTO.setAmount(txn.getAmount().toString());
		subFieldsDTO.setMerchantName(txn.getMerchantName());
		subFieldsDTO.setChannel(txn.getChannel());
		subFieldsDTO.setTxnStatus(txn.getTxnStatus());
		subFieldsDTOs.add(subFieldsDTO);
		status.setRequestedData(subFieldsDTOs);
	//}
	
	
	status.setCode(WebServiceConstants.CODE);
	status.setMessage(message);
	status.setType(type);
	
	return status;*/
	
	
	
	//HistoryRespDTO status = new HistoryRespDTO();
	
	//List<HistorySubFieldsDTO> subFieldsDTOs = new ArrayList<HistorySubFieldsDTO>();
	
	//for(CloudSvrTxn txn:svrTxns){
		
		//HistorySubFieldsDTO subFieldsDTO = new HistorySubFieldsDTO();
		
		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();
		
		//subFieldsDTO.setTranId(txn.getTxnId().toString()); 
		//for removing milliseconds from the time what we get
		//time being
		status.setTranDate(WebServiceUtil.timeField2(dbDetails.getTxnStartDate()));
		//subFieldsDTO.setTranDate(txn.getTxnDate().toString());
		//subFieldsDTO.setMerchantId(txn.getMerId());
		//if(txn.gettId()!= null && txn.gettId().length()!= 0){
		status.setTerminalId(dbDetails.getTid());
		//}else{
			
		//	subFieldsDTO.setTerminalId(WebServiceConstants.NOT_APPLICABLE);
			
		//}
		//subFieldsDTO.setBatchId(WebServiceConstants.BATCH_ID);
		//subFieldsDTO.setInvoiceNo(txn.getInvoiceNo());
		//subFieldsDTO.setAccNo(txn.getCardNo());
		//subFieldsDTO.setAccNo(txn.getAccNo());
		//if(txn.getAcctype().equalsIgnoreCase(WebServiceConstants.CARD)){
			
		//	subFieldsDTO.setAccNo(txn.getCardNo());
		//	subFieldsDTO.setAccExp(txn.getCardExp());
		//	subFieldsDTO.setAccType(txn.getAcctype());
			
		//} else if (txn.getAcctype().equalsIgnoreCase(WebServiceConstants.BANK)){
			
		//	subFieldsDTO.setAccNo(txn.getBankAcc_No());
		//	subFieldsDTO.setAccExp(WebServiceConstants.NOT_APPLICABLE);
		//	subFieldsDTO.setAccType(txn.getAcctype());
		//}
		//subFieldsDTO.setAuthNo(txn.getAuthCode().toString());
		//subFieldsDTO.setrRNNo(txn.getTxnId().toString());
		status.setAmount(dbDetails.getAmount().toString());
		//subFieldsDTO.setMerchantName(txn.getMerchantName());
		//subFieldsDTO.setChannel(txn.getChannel());
		status.setTxnStatus(dbDetails.getStatusCode());
		//subFieldsDTOs.add(subFieldsDTO);
		//status.setRequestedData(subFieldsDTOs);
	//}
	
	
	status.setCode(WebServiceConstants.CODE);
	status.setMessage(message);
	status.setType(type);
	
	return status;
	
	
}
	
	
	
	public static TransactionInitiateRespDTO porccessPaymentForECommerceDone(String message,String type,CloudSvrEcommerceTxn  dbDetails) {
		
		
		
		
			
		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();
			
			//subFieldsDTO.setTranId(txn.getTxnId().toString()); 
			//for removing milliseconds from the time what we get
			
			
			//time being
			
			//MM/dd/yyyy hh.mm.ss aa
			//status.setTranDate(dbDetails.getTxnStartDate());
			//status.setTerminalId(dbDetails.getTid());
			status.setTranDate(WebServiceUtil.timeField2(dbDetails.getTxnStartDate()));
			//status.setTranDate("05/05/2015 12.23.23 AM");
			status.setTerminalId(WebServiceConstants.NOT_APPLICABLE);
			
			//subFieldsDTO.setTranDate(txn.getTxnDate().toString());
			//subFieldsDTO.setMerchantId(txn.getMerId());
			//if(txn.gettId()!= null && txn.gettId().length()!= 0){
			
			//}else{
				
			//	subFieldsDTO.setTerminalId(WebServiceConstants.NOT_APPLICABLE);
				
			//}
			//subFieldsDTO.setBatchId(WebServiceConstants.BATCH_ID);
			//subFieldsDTO.setInvoiceNo(txn.getInvoiceNo());
			//subFieldsDTO.setAccNo(txn.getCardNo());
			//subFieldsDTO.setAccNo(txn.getAccNo());
			//if(txn.getAcctype().equalsIgnoreCase(WebServiceConstants.CARD)){
				
			//	subFieldsDTO.setAccNo(txn.getCardNo());
			//	subFieldsDTO.setAccExp(txn.getCardExp());
			//	subFieldsDTO.setAccType(txn.getAcctype());
				
			//} else if (txn.getAcctype().equalsIgnoreCase(WebServiceConstants.BANK)){
				
			//	subFieldsDTO.setAccNo(txn.getBankAcc_No());
			//	subFieldsDTO.setAccExp(WebServiceConstants.NOT_APPLICABLE);
			//	subFieldsDTO.setAccType(txn.getAcctype());
			//}
			//subFieldsDTO.setAuthNo(txn.getAuthCode().toString());
			//subFieldsDTO.setrRNNo(txn.getTxnId().toString());
			status.setAmount(dbDetails.getAmount().toString());
			//subFieldsDTO.setMerchantName(txn.getMerchantName());
			//subFieldsDTO.setChannel(txn.getChannel());
			status.setTxnStatus(dbDetails.getStatusCode());
			//subFieldsDTOs.add(subFieldsDTO);
			//status.setRequestedData(subFieldsDTOs);
		//}
		
		
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		
		return status;
		
		
	}
	
	
	public static SecurityQuestionsRespDTO getSecurityQuestionResp(String message,
								String type,List<RespSubFieldsDTO> subfields)
	{
		SecurityQuestionsRespDTO status = new SecurityQuestionsRespDTO();
		
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setRequestedData(subfields);
		return status;
	}
	
	
	
	public static Status getSecurityQuestionResp(String message, String type) {
		Status status = new Status();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		//status.setRequestedData(subfields);
		return status;
	}
	
	
	
	
	
	
	public static CustomerRespDTO getCustomerResp(
			String message, String type, List<AccountDTO> accountList,String isScuirty,UpdateUserDTO updateProfile) {
	
		
		
		CustomerRespDTO status = new CustomerRespDTO();

		status.setCode(WebServiceConstants.CODE);
		
		status.setMessage(message);
		
		status.setType(type);
		
		status.setRequestedData(accountList);
		
		status.setIsSecurity(isScuirty);
		
		status.setUpdateProfile(updateProfile);
		
		
		
			
		return status;
	}
	
	
	
	
	public static boolean baseCheckMandatoryFields(BaseDTO baseDTO) {

				
		boolean isEmpty = true;
		
		if ( baseDTO.getPhoneNumber() != null &&
				baseDTO.getPhoneNumber().trim().isEmpty())  {

						
			isEmpty = false;
		}
		if(baseDTO.getEmailId() != null
				&& baseDTO.getEmailId().trim().isEmpty()){
			
			isEmpty = false;
		}
	
		if(baseDTO.getMerchantAccessKey() != null && 
				baseDTO.getMerchantAccessKey().trim().isEmpty()) {
			
			isEmpty = false;
		}
		return isEmpty;
	}
	
	public static boolean baseCheckRegistartionMandatoryFields(BaseDTO baseDTO) {

		
		boolean isEmpty = true;
		
		if ( baseDTO.getPhoneNumber() != null &&
				baseDTO.getPhoneNumber().trim().isEmpty())  {

						
			isEmpty = false;
		}
		if(baseDTO.getEmailId() != null
				&& baseDTO.getEmailId().trim().isEmpty()){
			
			isEmpty = false;
		}
		
		if(baseDTO.getImeiNo() != null
				&& baseDTO.getImeiNo().trim().isEmpty()){
			
			isEmpty = false;
		}
	
		if(baseDTO.getMerchantAccessKey() != null && 
				baseDTO.getMerchantAccessKey().trim().isEmpty()) {
			
			isEmpty = false;
		}
		
	
		
		
		return isEmpty;
	}
	
	
	
	public static boolean baseCheckMandatorySetSecurityQuestions(BaseDTO baseDTO) {

	
		
		boolean isEmpty = true;
		
		if ( baseDTO.getPhoneNumber() != null &&
				baseDTO.getPhoneNumber().trim().isEmpty())  {

						
			isEmpty = false;
		}
		if( baseDTO.getEmailId() != null
				&& baseDTO.getEmailId().trim().isEmpty()){
			
			isEmpty = false;
		}
			
		if(baseDTO.getMerchantAccessKey() != null && 
				baseDTO.getMerchantAccessKey().trim().isEmpty()) {
			
			isEmpty = false;
		}
		
		return isEmpty;
	}
	
	
	public static boolean baseCheckMandatoryFieldsUpdateProfile(BaseDTO baseDTO) {

			
		boolean isEmpty = true;
		
		if ( baseDTO.getPhoneNumber() != null &&
				baseDTO.getPhoneNumber().trim().isEmpty())  {

					
			isEmpty = false;
		}
		if(baseDTO.getEmailId() != null
				&& baseDTO.getEmailId().trim().isEmpty()){
			
			isEmpty = false;
		}
				
		if(baseDTO.getMerchantAccessKey() != null && 
				baseDTO.getMerchantAccessKey().trim().isEmpty()) {
			
			isEmpty = false;
		}
		return isEmpty;
	}
	
	/*
	 * This method is user for to validation for IMeiNO
	 * 
	 * 
	 */
	public static boolean baseCheckMandatoryImeiNOField(BaseDTO baseDTO) {

			
		boolean isEmpty = true;
		
	  if(baseDTO.getMerchantAccessKey() != null && 
				baseDTO.getMerchantAccessKey().trim().isEmpty()) {
			
			isEmpty = false;
		}
		return isEmpty;
	}
	
	
	
	
	/*
	 * This method is user for to validation for IMeiNO
	 * 
	 * 
	 */
	public static String URLDecoderField(String field) {
		
		String decodeed = null;
			
		try {
			
			 decodeed = URLDecoder.decode(field, "UTF-8");
			 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return decodeed;
	}
	
	
	

	/*
	 * This method is user for to validation for IMeiNO
	 * 
	 * 
	 */
	public static String URLEcoderField(String field) {
		
		String Ecoder = null;
			
		try {
			
			Ecoder = URLEncoder.encode(field, "UTF-8");
			 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Ecoder;
	}
	
	
	
	/*
	 * This method is user for to validation for IMeiNO
	 * 
	 * 
	 */
	public static String timeField(Timestamp tm) {
		
		String transTime = null;
		
		transTime = new SimpleDateFormat(WebServiceConstants.DATE_FORMAT_TIMESTAMP).format(tm);
		 
		return transTime;
	}
	
	
	/*
	 * This method is user for to validation for IMeiNO
	 */
	public static String timeField2(String dateInString) {

		String transTime = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//2015-05-06 10:12:46.557
		try {
			java.util.Date date = sdf.parse(dateInString);
			//working copy
			/*SimpleDateFormat sdf1 = new SimpleDateFormat(
					"MM/dd/yyyy hh.mm.ss aa");*/
			SimpleDateFormat sdf1 = new SimpleDateFormat(
					"MM/dd/yyyy hh:mm:ss aa");
			
			transTime = sdf1.format(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return transTime;

	}
	
	
	/*
	 * 
	 * get cuurent date 
	 * 
	 * 
	 */
	public static String currentDate(){
	
		SimpleDateFormat sdf = new SimpleDateFormat(WebServiceConstants.DATE_FORMAT);
		String date = sdf.format(new Date()); 
		//15/10/2013
		
		return date;
	}
	
	
	
	
	public static AccountRespDTO getAccountForMoreToken(String message,String type,List<Map<String,String>> token,String tokenExpDate) {
		
		AccountRespDTO status = new AccountRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setTokens(token);
		status.setTokenExpDate(tokenExpDate);
		
		return status;
	}
	
	
	
	public static TransactionInitiateRespDTO getProcessDataForUnicenta(String message,
			String type, PosDTO posDTO, CloudSvrQrCodeTxn dbDetail) {

		TransactionInitiateRespDTO status = new TransactionInitiateRespDTO();

		List<ProcessSubFieldDTO> subFieldsDTOs = new ArrayList<ProcessSubFieldDTO>();

		ProcessSubFieldDTO subFieldsDTO = new ProcessSubFieldDTO();
		//this is for pay now
         if (posDTO != null) {
			
			if (posDTO.getTrack2() != null) {
				subFieldsDTO.setTrack2(posDTO.getTrack2());
				subFieldsDTO.setAccType(posDTO.getAccType());
				subFieldsDTO.setMid(dbDetail.getMid());
				subFieldsDTO.setMname(dbDetail.getMerchantName());
				// time being we are heard coding
				subFieldsDTO.setMtype(dbDetail.getMcc());
				subFieldsDTO.setMaddress(dbDetail.getMerchantAddress());
				subFieldsDTO.setSplit(dbDetail.isSplit());
				subFieldsDTO.setSplitAmount(dbDetail.getSplitAmount());
				
				status.setIsProcessReq(dbDetail.getIsProcessReq());
				status.setIsAmountReq("N");
				
			}
			
		} else {
		// this is for amount confirmation	
			status.setIsAmountReq(dbDetail.getIsProcessReq());
			status.setIsProcessReq("N");
		}
		subFieldsDTOs.add(subFieldsDTO);
		status.setPosRequestedData(subFieldsDTOs);

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);

		return status;
	}

	
public static AccountRespDTO getAccountFieldsFromMitech(String message,String type,MitekServerRespDTO checkData) {
		
		AccountRespDTO status = new AccountRespDTO();
		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		if (checkData!=null)
		{
			status.setAccNo(checkData.getAccNo());
			status.setCheckNo(checkData.getCheckNo());
			status.setAccBankRout(checkData.getAccBankRout());
		}
						
		return status;
	}
	





public static boolean checkMandatoryFieldsRewards(BaseDTO baseDTO) {

	
	boolean isEmpty = true;
	
	if ( baseDTO.getPhoneNumber() != null &&
			baseDTO.getPhoneNumber().trim().isEmpty())  {

					
		isEmpty = false;
	}
	if(baseDTO.getEmailId() != null
			&& baseDTO.getEmailId().trim().isEmpty()){
		
		isEmpty = false;
	}

	if(baseDTO.getMerchantAccessKey() != null && 
			baseDTO.getMerchantAccessKey().trim().isEmpty()) {
		
		isEmpty = false;
	}
	return isEmpty;
}

public static void  main(String qas[]){
	
	
	
	// 1) create a java calendar instance
	//Calendar calendar = Calendar.getInstance();
	 
	// 2) get a java.util.Date from the calendar instance.
//	    this date will represent the current instant, or "now".
	//java.util.Date now = calendar.getTime();
	 
	// 3) a java current time (now) instance
	//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
	
	//timeField(currentTimestamp);
	
	String as ="2015-05-06 13:12:46.557";
	
	as= timeField2(as);
	System.out.println(as);
}





	public static CouponRespDTO getCouponResp(String message, String type,
			List<CouponSubfieldDTO> subfields) {
		CouponRespDTO status = new CouponRespDTO();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setRequestedData(subfields);
		return status;
	}

	
	public static DiscountRespDTO getDiscountResp(String message, String type,
			CouponDTO couponDto) {
		DiscountRespDTO status = new DiscountRespDTO();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setTotalAmount(couponDto.getTotalAmount());
		status.setCouponId(couponDto.getCouponId());
		return status;
	}
	
	
	
	public static DiscountRespDTO getDiscountResp(String message, String type) {
		
		DiscountRespDTO status = new DiscountRespDTO();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
	
		return status;
	}
	

	public static RewardRespDTO getRewardResp(String message, String type,
			RewardSubfieldDTO subfields) {
		RewardRespDTO status = new RewardRespDTO();

		status.setCode(WebServiceConstants.CODE);
		status.setMessage(message);
		status.setType(type);
		status.setRequestedData(subfields);
		return status;
	}

			
}
