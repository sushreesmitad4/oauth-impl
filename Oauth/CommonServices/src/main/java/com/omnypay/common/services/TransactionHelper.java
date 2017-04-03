package com.omnypay.common.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
//import org.omnypay.httpclient.dto.EcommTxnDTO;
/*import org.omnypay.httpclient.dto.CardOnFileDTO;
import org.omnypay.httpclient.dto.CardOnFileRespDTO;
import org.omnypay.httpclient.dto.EcommTxnDTO;
import org.omnypay.httpclient.dto.HceTokenDTO;
import org.omnypay.httpclient.dto.HceTokenRespDTO;
import org.omnypay.httpclient.util.ConnectorConstants;*/















import com.omnypay.common.services.dto.AccountDTO;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.common.services.dto.EcommTxnDTO;
import com.omnypay.common.services.dto.HceTokenDTO;
import com.omnypay.common.services.dto.HistoryRespDTO;
import com.omnypay.common.services.dto.HistorySubFieldsDTO;
import com.omnypay.common.services.dto.PosDTO;
import com.omnypay.common.services.dto.TransactionDTO;
import com.omnypay.common.services.dto.TransactionInitiateRespDTO;
import com.omnypay.dao.bo.CloudSvrEcommerceTxn;
import com.omnypay.dao.bo.CloudSvrPosCon;
import com.omnypay.dao.bo.CloudSvrTempToken;
import com.omnypay.dao.bo.CloudSvrTxn;
import com.omnypay.dao.bo.CloudSvrTxnEntity;
import com.omnypay.dao.bo.CloudSvrUser;
import com.omnypay.dao.bo.CloudSvrQrCodeTxn;
import com.omnypay.log.Log4jConstants;

/**
 * 
 * @author iliyasm
 *
 */
public class TransactionHelper {

	public TransactionHelper() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	/***
	 * convert From DTO to BO for user history report
	 * 
	 * @param transactionDTO TransactionDTO object having all details
	 * @param userId userId of the user
	 * @return cloudSvrTxn  CloudSvrTxn object having history report
	 */
	
	public static CloudSvrTxn converDTOtoBO(TransactionDTO transactionDTO,String userId){
		
			
		
		CloudSvrTxn cloudSvrTxn = new CloudSvrTxn();
		
		cloudSvrTxn.setCustomerId(userId);
		
		String fromDate = transactionDTO.getFromDate();
		
		String toDate = transactionDTO.getToDate();
		
		if(fromDate != null && fromDate.length() != 0){
			
			cloudSvrTxn.setFromDate(convertStringDateToTimestamp(fromDate));
						
		}
		
		if(toDate!=null && toDate.length()!=0){
			
			cloudSvrTxn.setToDate(convertStringDateToTimestamp(toDate));
						
		}
		
		
		
		
		
		return cloudSvrTxn;
	}
	
	/***
	 * convert From DTO to BO for RRN
	 * 
	 * @param rrn RRN number 
	 * 
	 * @return cloudSvrTxn  CloudSvrTxn object
	 */	
public static CloudSvrTxn converDTOtoBORRN(String rrn){
		
			
		
		CloudSvrTxn cloudSvrTxn = new CloudSvrTxn();
		
		cloudSvrTxn.setTxnId(Long.parseLong(rrn));
	
		cloudSvrTxn.setFromDate(convertStringDateToTimestamp(WebServiceUtil.currentDate()));
		
		return cloudSvrTxn;
	}
	

/***
 * convert From DTO to BO for Ecommerce transaction
 * 
 * @param switchId String
 * 
 * @return cloudSvrTxn  CloudSvrTxn object 
 */	
	public static CloudSvrTxn converDTOtoBOEcommerce(String switchId){
		
			
		
		CloudSvrTxn cloudSvrTxn = new CloudSvrTxn();
		
		cloudSvrTxn.setId(Long.parseLong(switchId));
	
		cloudSvrTxn.setFromDate(convertStringDateToTimestamp(WebServiceUtil.currentDate()));
		
		return cloudSvrTxn;
	}
	
	

	
	/***
	 * convert From DTO to BO for transaction process
	 * 
	 * @param svrTxns CloudSvrTxn object having details to process txn
	 * 
	 * @return respDTO  having details of txn process
	 */	
	public static HistoryRespDTO converBOToDTOProcess(CloudSvrTxn svrTxns) {

		HistoryRespDTO respDTO = new HistoryRespDTO();
		List<HistorySubFieldsDTO> subFieldsDTOs = new ArrayList<HistorySubFieldsDTO>();

		

			HistorySubFieldsDTO subFieldsDTO = new HistorySubFieldsDTO();
			
			subFieldsDTO.setTranId(svrTxns.getTxnId().toString());
			subFieldsDTO.setTranDate(WebServiceUtil.timeField(svrTxns.getTxnDate()));
			
			subFieldsDTO.setTerminalId(svrTxns.getId().toString());
			subFieldsDTO.setBatchId(WebServiceConstants.BATCH_ID);
			subFieldsDTO.setInvoiceNo(svrTxns.getInvoiceNo());
			
			subFieldsDTO.setAccType(svrTxns.getCardType());
			
			subFieldsDTO.setrRNNo(svrTxns.getTxnId().toString());
			
			subFieldsDTO.setAmount(svrTxns.getAmount().toString());

			subFieldsDTOs.add(subFieldsDTO);
			
		return respDTO;
	}
	
	
	/***
	 * private method use internally
	 * 
	 */	
	public static CloudSvrTxn converDTOtoBOHeardCode() {

		CloudSvrTxn svrTxns = new CloudSvrTxn();

		
		Long id = 69l;
	
		Double amount = 25.10;

		svrTxns.setTxnId(654879l);
		svrTxns.setTxnDate(new Timestamp(System.currentTimeMillis()));
		svrTxns.setId(id);
		svrTxns.setInvoiceNo(WebServiceConstants.INVOICE_NO);
		svrTxns.setCardType(WebServiceConstants.MASTER_CARD);
		svrTxns.setAmount(amount);

		return svrTxns;
	}
	
	
	/***
	 * check Mandatory Fields for history
	 * @param transDTO TransactionDTO object having details to get history
	 * 
	 * @return true/false
	 */	
	public static boolean checkMandatoryFields(TransactionDTO transDTO)
												 {
		boolean istrue =true;
	
		if(transDTO.getFromDate().isEmpty() && transDTO.getToDate().isEmpty() ){
			istrue = false;
		}
		return istrue;
	}
	
	
	/***
	 * check Mandatory Fields for  acc base  history
	 * @param transDTO TransactionDTO object having details to get acc base history
	 * 
	 * @return true/false
	 */	
	public static boolean checkMandatoryFieldsAccountBased(TransactionDTO transDTO) {
		boolean istrue = true;

		if (transDTO.getAccNo().isEmpty()) {
			
			istrue = false;
		}
		return istrue;
	}
	
	
	/***
	 * internal use for date formatting
	 * @param stringDate represent date
	 * 
	 * @return String represent formatted date
	 */	
	private static String convertStringDateToTimestamp(String stringDate){
		
		
		String mydate = null;
	
		
		SimpleDateFormat fromUser = new SimpleDateFormat(WebServiceConstants.DATE_FORMAT);
		SimpleDateFormat myFormat = new SimpleDateFormat(WebServiceConstants.DATE_FORMAT_MMM);

		try {

			mydate = myFormat.format(fromUser.parse(stringDate));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		
		
		return mydate;
	}
	
	/*--------------- delete cards list------*/
	/***
	 *  delete cards list
	 * @param userId represent userId
	 * @param accNo represent account Number
	 * @param acctype represent account type
	 * 
	 * @return tnxBO CloudSvrTxn
	 */	
	public static CloudSvrTxn ConvertFromDTOtoBO(String userId,String accNo,String acctype) {

		CloudSvrTxn tnxBO = new CloudSvrTxn();
		
		tnxBO.setCustomerId(userId);
		//tnxBO.setCardNo(accNo);
		if(acctype!=null && acctype.length()!=0){
			
			tnxBO.setAcctype(acctype);
			
			if(acctype.equalsIgnoreCase(WebServiceConstants.CREDIT_CARD_TYPE)
					||acctype.equalsIgnoreCase(WebServiceConstants.DEBIT_CARD_TYPE)
					||acctype.equalsIgnoreCase(WebServiceConstants.GIFT_PRIVATE_LAB_CARD_TYPE)
					){
			tnxBO.setCardNo(accNo);
			}else if(acctype.equalsIgnoreCase(WebServiceConstants.BANK_TYPE)){
				
				tnxBO.setBankAcc_No(accNo);
			}
		}
		
		
		
		return tnxBO;
	}
	
	
	
	
	
	
	
	
	
	/***
	 *  from switch side same way masking happening
	 * @param accOrCardnumber represent account Number
	 * 
	 * @return String represent mask account Number
	 */	
	public static String AccountNoMasking(String accOrCardnumber) {

		 int accOrCardNoLength = accOrCardnumber.length();
		  int startlen = 0, endlen = 0;
		  
		  if(accOrCardNoLength <= 8) {
		   
		   startlen = 2; 
		   endlen = 2;
		  
		  } else if(accOrCardNoLength >8 && accOrCardNoLength <= 10){
		   
		   startlen = 3; 
		   endlen = 3;
		  }
		  else{
		  
		   startlen = 4; 
		   endlen = 4;
		  }
		  int masklen = accOrCardNoLength - (startlen + endlen);
		  StringBuilder maskedbuf = new StringBuilder(accOrCardnumber.substring(0, startlen));
		  
		  for (int i = 0; i < masklen; i++) {
		   maskedbuf.append('X');
		  }
		  maskedbuf.append(accOrCardnumber.substring(startlen + masklen, accOrCardNoLength));
		  String masked = maskedbuf.toString();

		  return masked;
		 }
	
	
	/***
	 * check Mandatory Field For Details From Unicenta
	 * @param transDTO TransactionDTO having all fields
	 * 
	 * @return true/false
	 */	
	public static boolean checkMandatoryFieldForDetailsFromUnicenta(
			TransactionDTO transDTO) {

		boolean istrue = true;

		if (transDTO.getTid().isEmpty() && transDTO.getAmount().isEmpty()
				&& transDTO.getPosId().isEmpty()
				&& transDTO.getQrCode().isEmpty()
				&& transDTO.getMerchantAccessKey().isEmpty()) {

			istrue = false;
		}
		return istrue;
	}
	
	

	/***
	 * check Mandatory Field For Get Rrn From Unicenta
	 * @param transDTO TransactionDTO having all fields
	 * 
	 * @return true/false
	 */	

public static boolean checkMandatoryFieldForGetRrnFromUnicenta(TransactionDTO transDTO) {
	
	boolean istrue = true;

	if (transDTO.getTid().isEmpty() 
			&& transDTO.getRrn().isEmpty()
			&& transDTO.getStatusCode().isEmpty()
			&& transDTO.getPosId().isEmpty() 
			&& transDTO.getQrCode().isEmpty() 
			&& transDTO.getMerchantAccessKey().isEmpty()
			
			)  {
		
		istrue = false;
	}
	return istrue;
}

/***
 * check Mandatory Field For Ping Request From Unicenta
 * @param transDTO TransactionDTO having all fields
 * 
 * @return true/false
 */	
public static boolean checkMandatoryFieldForPingRequestFromUnicenta(TransactionDTO transDTO) {
	
	boolean istrue = true;

	if (transDTO.getTid().isEmpty() 
			&& transDTO.getIsProcessReq().isEmpty()
			&& transDTO.getPosId().isEmpty() 
			&& transDTO.getQrCode().isEmpty() 
			&& transDTO.getMerchantAccessKey().isEmpty()) {
		
		istrue = false;
	}
	return istrue;
}

/***
 * check Mandatory Field For Initiate txn
 * @param transDTO TransactionDTO having all fields
 * 
 * @return true/false
 */
	public static boolean checkMandatoryInitiateFields(TransactionDTO transDTO) {
		
		boolean istrue = true;

		if (transDTO.getAccId().isEmpty() && transDTO.getPosId().isEmpty()) {
			
			istrue = false;
		}
		return istrue;
	}
	
	/***
	 * check Mandatory Field For process txn
	 * @param transDTO TransactionDTO having all fields
	 * 
	 * @return true/false
	 */
	public static boolean checkMandatoryProcessPaymentFields(TransactionDTO transDTO) {
		
		boolean istrue = true;

		if (transDTO.getAmount().isEmpty() && transDTO.getPosId().isEmpty()
				&& transDTO.getAccId().isEmpty()
				) {
			
			istrue = false;
		}
		return istrue;
	}
	
	

	
	
	
	/***
	 * conversion from DTO to BO for Bank
	 * @param object  having all fields
	 * @param userId user id
	 * @return accBO HceTokenDTO
	 */
	public static HceTokenDTO convertFromDTOtoBOOnlineToken(Object object,String userId) {
		
		HceTokenDTO accBO = new HceTokenDTO();
		
		if(object instanceof TransactionDTO ){
			
			TransactionDTO tnxTO = (TransactionDTO) object;
			
			accBO.setUserId(userId);
			
			accBO.setAccountRefId(tnxTO.getAccId());
			
			accBO.setTokenType(WebServiceConstants.HCE_ONLINE_T_TYPE);
			
			// service reference id is a random number and unique for each request
			accBO.setServiceRefId(userId);
			
			// pos id and optional
			accBO.setTokenRequestorId(tnxTO.getPosId());
			
			}
		
		return accBO;
	}
	
	

	/***
	 * conversion from DTO to BO	for Bank
	 * @param object  having all fields
	 * @param userId user id
	 * @return accBO HceTokenDTO
	 */
		public static CloudSvrTempToken convertFromDTOtoBOGetToken(Object object,String userId) {
			
			CloudSvrTempToken accBO = new CloudSvrTempToken();
			
			if(object instanceof TransactionDTO ){
				
				
				
				accBO.setUserId(userId);
				
				
				
				
				}
			
			return accBO;
		}
	
	
		/***
		 * conversion from PickingRandomNumbers
		 * 
		 * @param userId user id
		 * @return integer
		 */
	public static int PickingRandomNumbersFromCertainRange(String userId) {

		Random rand = new Random();
		String as ="100000";
		int pickedNumber = rand.nextInt(Integer.parseInt(as) + 1);
		
		return pickedNumber;
	}
	
	

	/***
	 * conversion from DTO to BO	for Bank
	 * 
	 * 
	 * @return integer
	 */
			public static PosDTO convertFromDTOtoBOPosData(Object object,/*HceTokenRespDTO token,*/String accType,String tokenId,String tokenExp) {
				
				PosDTO accBO = new PosDTO();
				
				if(object instanceof TransactionDTO ){
					
					TransactionDTO accountTO = (TransactionDTO) object;
					
					// this for bank
					if(accType.equalsIgnoreCase(WebServiceConstants.BANK_TYPE)){
						
						accBO.setAccType(WebServiceConstants.BANK_TYPE);
					
					
					   }
				   else
					   {
					  accBO.setAccType(accType);
							
					   }
						
						
						StringBuilder sb = new StringBuilder();
						sb.append(WebServiceConstants.SEMICOLON);
						//sb.append(token.getOnlinetokens());
						sb.append(tokenId);
						sb.append(WebServiceConstants.EQUAL_OPERATOR);
						sb.append(tokenExp);
						sb.append(WebServiceConstants.SERVICE_CODE);
						
						sb.append(WebServiceConstants.TERNURY_OPERATOR);
						
						String tracString = sb.toString();
						
						int tracklength = tracString.length();
						
						String tlength = Integer.toString(tracklength);
						
						String finaltrackString = tlength+tracString;
						
						accBO.setTrack2(finaltrackString);	
						
						accBO.setAmount(accountTO.getAmount());
						
						if(accountTO.getAddress1()!=null){
							
							accBO.setShippingAddress1(accountTO.getAddress1());
							//accBO.setcAddress(accountTO.getAddress1()+accountTO.getAddress2()+accountTO.getCity()+accountTO.getState());
						}
						
						if (accountTO.getAddress2()!=null){
							
							accBO.setShippingAddress2(accountTO.getAddress2());
						}
						
						if (accountTO.getAddress3()!=null){
							
							accBO.setShippingAddress3(accountTO.getAddress3());
						}
						
						if (accountTO.getCity()!=null){
							
							accBO.setShippingCity(accountTO.getCity());
						}
						if (accountTO.getZipCode()!=null){
							
							accBO.setShippingZipCode(accountTO.getZipCode());
						}
						if (accountTO.getState()!=null){
							
							accBO.setShippingState(accountTO.getState());
						}
					
					
				}
				
				return accBO;
			}
			
		
	
			/***
			 * conversion from DTO to BO	for PosAmount
			 * @param object having all details
			 * 
			 * @return accBO PosDTO
			 */
				
	public static PosDTO convertFromDTOtoBOPosAmount(Object object) {

		PosDTO accBO = new PosDTO();

		if (object instanceof TransactionDTO) {

			TransactionDTO accountTO = (TransactionDTO) object;

			accBO.setPosId(accountTO.getPosId());

		}

		return accBO;
	}

	/***
	 * conversion from DTO to BO	for Offline Token
	 * @param object having all details
	 * @param user CloudSvrUser object
	 * 
	 * @return HceTokenDTO
	 */
		
	public static HceTokenDTO convertFromDTOtoBOOfflineToken(Object object,
			CloudSvrUser user) {

		HceTokenDTO accBO = new HceTokenDTO();

		String userId = null;

		userId = user.getUserId();

		if (object instanceof CardOnFileRespDTO) {

			CardOnFileRespDTO tnxTO = (CardOnFileRespDTO) object;

			accBO.setUserId(userId.toString());

			// pass code
			accBO.setPasscode(user.getPassCode());

			// imeino

			accBO.setImeiNo(user.getImeiNo());

			// is first card

			if (tnxTO.getIsFirstCard() != null
					&& tnxTO.getIsFirstCard().equalsIgnoreCase(
							WebServiceConstants.FIRSTCARD)) {

				accBO.setIsFirstCard(WebServiceConstants.IS_FIRST_CARD);

			} else {

				accBO.setIsFirstCard(WebServiceConstants.IS_FIRST_CARD_NOT);

			}

			accBO.setAccountRefId(tnxTO.getAccId());
			

			accBO.setTokenType(WebServiceConstants.HCE_OFFLINE_T_TYPE);

			// service reference id is a random number and unique for each
			// request
			accBO.setServiceRefId(String
					.valueOf(PickingRandomNumbersFromCertainRange(userId)));

			

		}

		return accBO;
	}

	/***
	 * conversion from DTO to BO	for Delete Token
	 * @param cofDto CardOnFileDTO having all details
	 * @param userId userId
	 * 
	 * @return HceTokenDTO
	 */
	public static HceTokenDTO convertFromDTOtoBODeleteToken(
			CardOnFileDTO cofDto, Long userId) {

		HceTokenDTO accBO = new HceTokenDTO();

		accBO.setUserId(userId.toString());

		accBO.setAccountRefId(cofDto.getAccId());

		return accBO;
	}

	/***
	 * conversion from DTO to BO	for Offline Token For All Cards
	 * @param cardList List<AccountDTO> having all details
	 * @param userId userId
	 * 
	 * @return List<HceTokenDTO>
	 */
	public static List<HceTokenDTO> convertFromDTOtoBOOfflineTokenForAllCards(List<AccountDTO> cardList,CloudSvrUser userId) {


		List<HceTokenDTO> cardLists = new ArrayList<HceTokenDTO>();
		
		          
			for ( AccountDTO accdto : cardList) {
				HceTokenDTO accBO = new HceTokenDTO();
				accBO.setIsFirstCard(accdto.getIsFirstCard());
				accBO.setAccountRefId(accdto.getAcctId());
				accBO.setUserId(userId.getUserId());
				accBO.setPasscode(userId.getPassCode());
				accBO.setImeiNo(userId.getImeiNo());
				accBO.setServiceRefId("1000");				
				accBO.setTokenType(WebServiceConstants.HCE_OFFLINE_T_TYPE);
				cardLists.add(accBO);
				}
			

			
		

		return cardLists;
	}
	
	/***
	 * conversion from DTO to BO	for Ecomm Amount
	 * @param tranDTO TransactionDTO having all details
	 * 
	 * 
	 * @return  CloudSvrEcommerceTxn 
	 */	
	public static CloudSvrEcommerceTxn convertFromDTOtoCOFDTOEcommAmount(
			TransactionDTO tranDTO) {

		String posId = tranDTO.getPosId();

		CloudSvrEcommerceTxn accBO = new CloudSvrEcommerceTxn();

		if (posId !=null && posId.contains(WebServiceConstants.SEMICOLON)) {

			String parts[] = posId.split(WebServiceConstants.SEMICOLON_WITH_BACKSPACE);

			if (parts != null && parts.length != 0) {
				accBO.setIpAddress(parts[0]);
				accBO.setSessionId(parts[1]);
			}
		}

		return accBO;

	}
	

	/***
	 * conversion from DTO to BO	for EcommTxn
	 * @param tranDTO TransactionDTO having all details
	 * @param accType account type
	 * 
	 * @return  EcommTxnDTO 
	 */	
		/*public static EcommTxnDTO convertFromDTOtoBODataForEcommTxn(TransactionDTO tranDTO,String accType) {
			
			EcommTxnDTO ecommTxnBO = new EcommTxnDTO();
			


				
				CardOnFileRespDTO cardOnFileRespDTO = new CardOnFileRespDTO();
				
				// this for bank
				
				if(accType.equalsIgnoreCase(WebServiceConstants.ACCOUNT_BANK_TYPE))	
				{
					ecommTxnBO.setAccType(WebServiceConstants.BANK);
					//set the transactionId 12 digit of numerice
					ecommTxnBO.setTxnId(PasswordHelper.PickingRandomNumbers(12));
				}
				else
				{
					ecommTxnBO.setAccType(WebServiceConstants.CARD);
				}
					ecommTxnBO.setTxnType(WebServiceConstants.SALE);
					ecommTxnBO.setChannel(WebServiceConstants.ECOMM);
					ecommTxnBO.setServiceCode(WebServiceConstants.SERVICE_CODE);
					ecommTxnBO.setRequestType(WebServiceConstants.REQUEST_TYPE_INSERT);
					ecommTxnBO.setProcessingCode(WebServiceConstants.PROCESSING_CODE);
					ecommTxnBO.setNetworkId(WebServiceConstants.NETWORK_ID);
					ecommTxnBO.setPosEntryMode(WebServiceConstants.POS_ENTRY_MODE);
					ecommTxnBO.setMerchantId(tranDTO.getMid());
					ecommTxnBO.setInvoiceNo(WebServiceConstants.INVOICE_NO);
					ecommTxnBO.setAmount(tranDTO.getAmount());
					ecommTxnBO.setRequestType(tranDTO.getRequestType());
					ecommTxnBO.setSwitchTxnId(tranDTO.getSwitchTxnId());
			return ecommTxnBO;
		}
		*/
		
		
		
		/***
		 * conversion from DTO to BO	for Txn
		 * @param tranDTO TransactionDTO having all details
		 * 
		 * 
		 * @return  CloudSvrQrCodeTxn 
		 */	
		public static CloudSvrQrCodeTxn convertFromDTOtoCOFDTONormalTxn(
				TransactionDTO tranDTO) {

			

			CloudSvrQrCodeTxn accBO = new CloudSvrQrCodeTxn();

			
			accBO.setPosId(tranDTO.getPosId());
			
			return accBO;

		}
		
		


		
		
		/***
		 * conversion from DTO to BO	for getting mid
		 * @param tranDTO TransactionDTO having all details
		 * 
		 * 
		 * @return  CloudSvrPosCon 
		 */	
		public static CloudSvrPosCon convertFromDTOtoCOFDTOMidForUnicenta(
				TransactionDTO tranDTO) {

			

			CloudSvrPosCon accBO = new CloudSvrPosCon();

			accBO.setQrCode(tranDTO.getQrCode());
			
			return accBO;

		}

	

		/***
		 * conversion from DTO to BO	for qr code txn
		 * @param amountDTO TransactionDTO having all details
		 * 
		 * 
		 * @return  CloudSvrQrCodeTxn 
		 */		
	public static CloudSvrQrCodeTxn convertFromDTOtoBOAmountForNormalTxn(
			TransactionDTO amountDTO) {

		CloudSvrQrCodeTxn amountBO = new CloudSvrQrCodeTxn();

		if (amountDTO.getAmount() != null && amountDTO.getAmount().length() != 0) {
			
			amountBO.setAmount(Double.valueOf(amountDTO.getAmount()));
		}
		if (amountDTO.getTid() != null && amountDTO.getTid().length() != 0) {
			
			amountBO.setTid(amountDTO.getTid());
		}
		if (amountDTO.getPosId() != null && amountDTO.getPosId().length() != 0) {
			
			amountBO.setPosId(amountDTO.getPosId());
		}
		if (amountDTO.getQrCode() != null && amountDTO.getQrCode().length() != 0) {
			
			amountBO.setQrCode(amountDTO.getQrCode());
		} 		
		if (amountDTO.getMerchantAccessKey()!= null && amountDTO.getMerchantAccessKey().length() != 0) {

			amountBO.setMerchantAccessKey(amountDTO.getMerchantAccessKey());
		}
		
		
		
		
		
		/*if (amountDTO.getMid() != null && amountDTO.getMid().length() != 0) {

			amountBO.setMid(amountDTO.getMid());
		}*/

		// default setting
		amountBO.setIsProcessReq(WebServiceConstants.IS_PROCESS_REQ_NOT);
		amountBO.setProcessPayment(WebServiceConstants.PROCESS_PAYMENT_NOT_DONE);
		amountBO.setMcc(WebServiceConstants.MERCHANT_CATEGORY_CODES);
		return amountBO;
	}
		
	/***
	 * conversion from DTO to BO for Ping qr code txn
	 * @param amountDTO TransactionDTO having all details
	 * 
	 * 
	 * @return  CloudSvrQrCodeTxn 
	 */
		public static CloudSvrQrCodeTxn convertFromDTOtoBOAmountPingForNormalTxn(TransactionDTO amountDTO) {
			
			CloudSvrQrCodeTxn amountBO=new CloudSvrQrCodeTxn();
			
			if(amountDTO.getTid()!= null && amountDTO.getTid().length()!= 0){
				amountBO.setTid(amountDTO.getTid());
			}
			
			return amountBO;
		}
		/***
		 * conversion from DTO to BO for SwitchReq
		 * @param transDTO TransactionDTO having all details
		 * 
		 * 
		 * @return  true/false
		 */
		public static boolean checkMandatorySwitchReqFields(TransactionDTO transDTO) {
			
			boolean istrue = true;

			if (transDTO.getCustomerId().isEmpty() 
					&& transDTO.getTxnStatus().isEmpty()
					&& transDTO.getTerminalId().isEmpty()
					&& transDTO.getTxnStartDate().isEmpty()) {
				
				istrue = false;
			}
			return istrue;
		}

		
		
		
		
		
		/***
		 *  to get status for the Request Of AmountSave from unicenta
		 * 
		 * @param isTrue True/false
		 * @return status  TransactionInitiateRespDTO object
		 * 
		 */
		public static  TransactionInitiateRespDTO storeRequestOfAmountSaveStatus(boolean isTrue) {

			TransactionInitiateRespDTO status = null;

			if (isTrue) {

				status = WebServiceUtil.getStatusForAmountSave(WebServiceConstants.AMOUNT_SAVED,WebServiceConstants.ONE);

			} else {

				status = WebServiceUtil.getStatusForAmountSave(
						WebServiceConstants.AMOUNT_NOT_SAVED,
						WebServiceConstants.TWO);
			}

			return status;

		}
		
		
		
		
		
		/**
		 * 
		 * this is use for formatting the amount 
		 * 
		 * @param amount String object
		 * @return formattedAmount String object
		 */
		
		public static  String getFormattedAmount(String amount) {

			
			String tweleveDigitAmount = null;

			String amountWithoutDot = null;

			String formattedAmount = null;

			amountWithoutDot = removeDotFromAmount(amount);

			// making amount as 12 digit appending zeros from left
			tweleveDigitAmount = StringUtils.leftPad(amountWithoutDot, 12,
					WebServiceConstants.SINGLE_ZERO);

			BigDecimal bd = new BigDecimal(tweleveDigitAmount)
					.divide(new BigDecimal(100));

			// putting point after 2 digit from right
			DecimalFormat df = new DecimalFormat(WebServiceConstants.AMOUNT_FORMAT);

			formattedAmount = df.format(bd);

			
			return formattedAmount;

		}
		
		
		
		
		
		/**
		 * 
		 * this is use for formatting the amount 
		 * 
		 * @param amount String object
		 * @return amountWithoutDot String object
		 */
		public static  String removeDotFromAmount(String amount) {

			

			String amountWithoutDot = null;

			// if amount contain dot do below check
			if (amount.contains(WebServiceConstants.DOT)) {
				// if after dot if one digit is there add one zero to the amount
				if (amount.substring(amount.indexOf(WebServiceConstants.DOT),
						amount.length() - 1).length() == 1) {

					amount = amount + WebServiceConstants.SINGLE_ZERO;

				}
				// replace dot
				amountWithoutDot = amount.replace(WebServiceConstants.DOT,
						WebServiceConstants.EMPTY_STRING);

			} else {

				// if no dot is there in amount add two zeros
				amount = amount + "00";
				amountWithoutDot = amount;
			}
			
			return amountWithoutDot;
		}

}
