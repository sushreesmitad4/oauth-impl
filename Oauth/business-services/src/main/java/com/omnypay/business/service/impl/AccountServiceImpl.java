/**
 * 
 */
package com.omnypay.business.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*import org.omnypay.httpclient.dto.CardOnFileDTO;
import org.omnypay.httpclient.dto.CardOnFileRespDTO;
import org.omnypay.httpclient.util.ConnectorConstants;
import org.omnypay.httpclient.util.IntegrationException;
import org.omnypay.httpclient.util.PropertiesUtil;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnypay.business.service.AccountService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.business.util.PropertiesUtil;
import com.omnypay.common.services.dto.CardOnFileDTO;
import com.omnypay.common.services.dto.CardOnFileRespDTO;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/**
 * @author iliyasm
 *
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();

	private final String CLASS_NAME = this.getClass().getName();
	
	
	public AccountServiceImpl() {
	}
	
	

	
	/**
	 * use for Add Card/Bank
	 * @param cardOnFileDTO CardOnFileDTO object having all details to add an account
	 * @return respDTO CardOnFileRespDTO object having response
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public CardOnFileRespDTO addAccountService(CardOnFileDTO cardOnFileDTO) throws CloudServiceException {
		
		
		String METHOD_NAME = "addAccountService";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		CardOnFileRespDTO respDTO =null;
		
		String cofUrl = null;

		try {
			
			
		cofUrl = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.COF_SERVICE_ADD_CARD_URL);
		
		String senddata = this.sendToCof(cardOnFileDTO);
		
		String  stataus = this.sendToCof(cofUrl, senddata);
		
		
		if (stataus != null) {

			respDTO = this.sendToCof(stataus);

		} else {

			respDTO = new CardOnFileRespDTO();

			respDTO.setType(BusinessConstants.FOUR);
		}
		
		}  catch (IntegrationException accessException) {
			
			
			
			addAccountCloudServiceException(accessException);
				
		} catch (Exception ex) {
			
			addAccountException(ex);
		
	}
		
		
		//		respDTO = this.sendToCof(stataus);
				
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);		
		
		
		return respDTO;
	}
	
	
	/**
	 * use for Add Bank
	 * @param cardOnFileDTO CardOnFileDTO object having all details to add an account
	 * @return respDTO CardOnFileRespDTO object having response
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public CardOnFileRespDTO addAchService(CardOnFileDTO cardOnFileDTO) throws CloudServiceException{
		
		String METHOD_NAME = "addAchService";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		CardOnFileRespDTO respDTO =null;
		
		String cofUrl = null;
		
		try {
			
		
		cofUrl = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.COF_SERVICE_ADD_BANK_URL);
		
		
		String senddata = this.sendToCof(cardOnFileDTO);
		
		String  stataus = this.sendToCof(cofUrl, senddata);
		
		// if this is coming null means cof server is down
		
		if (stataus !=null){
			
			respDTO = this.sendToCof(stataus);
			
		} else  {
			
			
			respDTO = new CardOnFileRespDTO();
			
			 respDTO.setType(BusinessConstants.FOUR);
		}
		
		
		}  catch (IntegrationException accessException) {
			
			addAchServiceCloudServiceException(accessException);
			
			
			} catch (Exception ex) {
			
				addAchServiceException(ex);
		}
		
		//respDTO = this.sendToCof(stataus);
		
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);	
		
		return respDTO;
	}
	
	
	
	
	/**
	 * use for update Account(Card/Bank)
	 * @param cardOnFileDTO CardOnFileDTO object having all details to add an account(Card/Bank)
	 * @return respDTO CardOnFileRespDTO object having response
	 * @throws CloudServiceException if business validation failed
	 * 
	 */
	@Transactional
	public CardOnFileRespDTO updateAccountService(CardOnFileDTO cardOnFileDTO) throws CloudServiceException{
		

		String METHOD_NAME = "updateAccountService";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		CardOnFileRespDTO respDTO =null;
		
		String cofUrl = null;
		
		try {
		
		cofUrl = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.COF_SERVICE_UPDATE_URL);
		
		
		String senddata = this.sendToCof(cardOnFileDTO);
		
		String  stataus = this.sendToCof(cofUrl, senddata);
		
		respDTO = this.sendToCof(stataus);
		
		}  catch (IntegrationException accessException) {
			
			updateAccountCloudServiceException(accessException);
			
			
			} catch (Exception ex) {
				
			updateAccountException(ex);
		}
		
		
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);	
		
		return respDTO;}

	
	

	
	
	
	
	/**
	 * Sends cofUrl to COF server with the given URL
	 * @param packetsend to cof  - mandatory
	 * @param url - COF server url
	 * @return String - represents status whether true/false
	 * @throws CloudServiceException in case of business validation failures
	 */
	private String sendToCof(String cofUrl,String senddata) throws CloudServiceException{
		
		
		String METHOD_NAME = "sendToCof";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		
		String status = null;
		
		HttpClient client = new DefaultHttpClient();
		
		
		HttpPost httpPost = new HttpPost(cofUrl);
		
		try {

			httpPost.setEntity(new StringEntity(senddata, ContentType
					.create(BusinessConstants.JSON_HEADER_TYPE)));

			HttpResponse resp = client.execute(httpPost);
			
			InputStream inputStream = resp.getEntity().getContent();

			BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
			
			String line = "";

			while ((line = rd.readLine()) != null) {
				
				status = line;
				
				
			}


				
		} catch (Exception ex) {
			sendToCofException(ex);
		} finally {

			client.getConnectionManager().shutdown();
			
			log.info(status+Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		}
		
		 return status;
		
	}

	/**
	 * Sends cofUrl to COF server with the given URL
	 * @param cardOnFileDTO to cof  - mandatory
	 * @return String - represents the packet string sending to cof
	 * @throws CloudServiceException in case of business validation failures
	 */
	private String sendToCof(CardOnFileDTO cardOnFileDTO){
		
	
		String addcard =null;
		
		
		if(cardOnFileDTO.getAccType().equalsIgnoreCase(BusinessConstants.CREDIT_CARD_TYPE)
				||cardOnFileDTO.getAccType().equalsIgnoreCase(BusinessConstants.DEBIT_CARD_TYPE)
				|| cardOnFileDTO.getAccType().equalsIgnoreCase(BusinessConstants.GIFT_CARD_TYPE)){
			
			
			
			if (cardOnFileDTO.getAccCardCvvCode() != null
					&& cardOnFileDTO.getAccCardCvvCode().length() != 0
					&& cardOnFileDTO.getAccCardExpDate() != null
					&& cardOnFileDTO.getAccCardExpDate().length() != 0
					&& cardOnFileDTO.getAccCardExpYear() != null
					&& cardOnFileDTO.getAccCardExpYear().length() != 0) {

				addcard = "{\"userId\":\"" + cardOnFileDTO.getUserId()
						+ "\",\"accCardCvvCode\":\""
						+ cardOnFileDTO.getAccCardCvvCode()
						+ "\",\"accHolderName\":\""
						+ cardOnFileDTO.getAccHolderName() + "\",\"accType\":\""
						+ cardOnFileDTO.getAccType() + "\",\"cardNickName\":\""
						+ cardOnFileDTO.getCardNickName() + "\",\"accNo\":\""
						+ cardOnFileDTO.getAccNo() + "\",\"accCardExpDate\":\""
						+ cardOnFileDTO.getAccCardExpDate()
						+ "\",\"accTransactionPwd\":\""
						+ cardOnFileDTO.getAccTransactionPwd()
						+ "\",\"accCardExpYear\":\""
						+ cardOnFileDTO.getAccCardExpYear() + "\",\"accBrand\":\""
						+ cardOnFileDTO.getAccBrand() + "\"}";
			} else if (cardOnFileDTO.getAccCardCvvCode() != null
					&& cardOnFileDTO.getAccCardCvvCode().length() != 0
					&& cardOnFileDTO.getAccCardExpDate() == null
					&& cardOnFileDTO.getAccCardExpYear() == null) {

				addcard = "{\"userId\":\"" + cardOnFileDTO.getUserId()
						+ "\",\"accCardCvvCode\":\""
						+ cardOnFileDTO.getAccCardCvvCode()
						+ "\",\"accHolderName\":\""
						+ cardOnFileDTO.getAccHolderName() + "\",\"accType\":\""
						+ cardOnFileDTO.getAccType() + "\",\"cardNickName\":\""
						+ cardOnFileDTO.getCardNickName()
						+ "\",\"accNo\":\""
						+ cardOnFileDTO.getAccNo()
						// + "\",\"accCardExpDate\":\"" +
						// cardOnFileDTO.getAccCardExpDate()
						+ "\",\"accTransactionPwd\":\""
						+ cardOnFileDTO.getAccTransactionPwd()
						// + "\",\"accCardExpYear\":\"" +
						// cardOnFileDTO.getAccCardExpYear()
						+ "\",\"accBrand\":\"" + cardOnFileDTO.getAccBrand()
						+ "\"}";

			} else if (cardOnFileDTO.getAccCardExpDate() != null
					&& cardOnFileDTO.getAccCardExpDate().length() != 0
					&& cardOnFileDTO.getAccCardExpYear() != null
					&& cardOnFileDTO.getAccCardExpYear().length() != 0
					&& cardOnFileDTO.getAccCardCvvCode() == null) {

				addcard = "{\"userId\":\""
						+ cardOnFileDTO.getUserId()
						// + "\",\"accCardCvvCode\":\"" +
						// cardOnFileDTO.getAccCardCvvCode()
						+ "\",\"accHolderName\":\""
						+ cardOnFileDTO.getAccHolderName() + "\",\"accType\":\""
						+ cardOnFileDTO.getAccType() + "\",\"cardNickName\":\""
						+ cardOnFileDTO.getCardNickName() + "\",\"accNo\":\""
						+ cardOnFileDTO.getAccNo() + "\",\"accCardExpDate\":\""
						+ cardOnFileDTO.getAccCardExpDate()
						+ "\",\"accTransactionPwd\":\""
						+ cardOnFileDTO.getAccTransactionPwd()
						+ "\",\"accCardExpYear\":\""
						+ cardOnFileDTO.getAccCardExpYear() + "\",\"accBrand\":\""
						+ cardOnFileDTO.getAccBrand() + "\"}";

			}else if (cardOnFileDTO.getAccCardExpDate() == null
					&& cardOnFileDTO.getAccCardExpYear() == null
					&& cardOnFileDTO.getAccCardCvvCode() == null) {

				addcard = "{\"userId\":\""
						+ cardOnFileDTO.getUserId()
						+ "\",\"accHolderName\":\""
						+ cardOnFileDTO.getAccHolderName() + "\",\"accType\":\""
						+ cardOnFileDTO.getAccType() + "\",\"cardNickName\":\""
						+ cardOnFileDTO.getCardNickName() + "\",\"accNo\":\""
						+ cardOnFileDTO.getAccNo() + "\",\"accTransactionPwd\":\""
						+ cardOnFileDTO.getAccTransactionPwd()+ "\",\"accBrand\":\""
						+ cardOnFileDTO.getAccBrand() + "\"}";

			}
			
			
			
			
			
		} else if (cardOnFileDTO.getAccType().equalsIgnoreCase(BusinessConstants.ACH_BANK_TYPE)){
			
			
			addcard = "{\"userId\":\"" + cardOnFileDTO.getUserId()
					+ "\",\"accBankName\":\"" + cardOnFileDTO.getAccBankName()
					+ "\",\"accHolderName\":\"" + cardOnFileDTO.getAccHolderName()
					+ "\",\"accType\":\"" + cardOnFileDTO.getAccType()
					+ "\",\"accBankType\":\"" + cardOnFileDTO.getAccBankType()
					+ "\",\"accNo\":\"" + cardOnFileDTO.getAccNo() + "\","
					+ "\"accStreet\":\"" + cardOnFileDTO.getAccStreet() + "\","
					+ "\"accCity\":\"" + cardOnFileDTO.getAccCity() + "\","
					+ "\"accTransactionPwd\":\"" + cardOnFileDTO.getAccTransactionPwd() + "\","
					+ "\"accState\":\"" + cardOnFileDTO.getAccState() + "\","
					+ "\"accZipcode\":\"" + cardOnFileDTO.getAccZipcode() + "\","
					+ "\"accBankRout\":\"" + cardOnFileDTO.getAccBankRout() + "\"}";
			
			
		}
		
		
		
		
		return addcard;
	}
	
	
	/**
	 * Sends cofUrl to COF server with the given URL
	 * @param packetsend to cof  - mandatory
	 * @param url - COF server url
	 * @return String - represents status whether true/false
	 * @throws CloudServiceException in case of business validation failures
	 */
	private CardOnFileRespDTO sendToCof(String status) throws IntegrationException{
		
		
		 String METHOD_NAME="sendToCof";
			
		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		
		CardOnFileRespDTO respDTO = null;
		
		try {
			
				JSONParser json = new JSONParser();
				
				JSONObject object  = (JSONObject) json.parse(status);
				
				Map response = (Map) object;
				
                
				if (response.get(BusinessConstants.TYPE).toString()
						.equalsIgnoreCase(BusinessConstants.REQ_TYPE)) {
					
					
					
					respDTO = new CardOnFileRespDTO();
					respDTO.setAccId((String)response.get(BusinessConstants.ACCOUNT_ID));
					respDTO.setIsFirstCard((String)response.get(BusinessConstants.ISFIRSTCARD));
			        respDTO.setType((String)response.get(BusinessConstants.TYPE));
			       // respDTO.setTokens(response.get(ConnectorConstants.TOKENS).toString());
			        respDTO.setTokenexpDate((String)response.get(BusinessConstants.TOKEN_EXP_DATE));
			        
			        if(respDTO!=null && respDTO.getIsFirstCard()!=null && respDTO.getIsFirstCard().equalsIgnoreCase(BusinessConstants.YES)){
			        	
			        	respDTO.setUdk((String)response.get(BusinessConstants.UDK));
			        }
					
					JSONArray jArray = (JSONArray) object.get(BusinessConstants.TOKENS);
					
					if (jArray !=null ){
						
						
						Iterator<Map<String,String>> iterator = jArray.iterator();
						
						List<Map<String,String>> tokenList = new ArrayList<Map<String,String>>();
						
						
						while(iterator.hasNext())	{
							
							JSONObject jsonObjectt=(JSONObject) iterator.next();
							
							
							Map<String,String> jsonmap= jsonObjectt;
							
							Map< String, String> linmap=(Map<String, String>) jsonmap;
							
							tokenList.add(linmap);
							
						}
						
						respDTO.setTokens(tokenList);
						
						
					}
					
					
			
					
					} else {
					
					
					respDTO = new CardOnFileRespDTO();
					
					respDTO.setType(response.get(BusinessConstants.TYPE).toString());

					respDTO.setMsg(response.get(BusinessConstants.MESSAGE).toString());
					

					

				}

			
		} catch (ParseException e) {
			
			sendToCofIntegrtnException(e);
		}catch (NullPointerException e) {
			
			sendToCofNullException(e);
		}
		catch (Exception e) {
		sendToCofACHException(e);
		}
		finally {

			
			log.info(respDTO + Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		}
		
		
		
		return respDTO;
	}



public List<CardOnFileDTO> getAccountDetailsService(CardOnFileDTO cardOnFileDTO) throws IntegrationException{
		
		
		String METHOD_NAME = "getAccountDetailsService";

		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		List<CardOnFileDTO> respDTO =null;
		
		String cofUrl = null;
		
		//try {

		cofUrl =  PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.COF_SERVICE_GETACCOUNT_DETAILS_URL);

		String senddata = this.sendToCofAccountDetail(cardOnFileDTO);
		
		String  status = this.sendToCofAccountDetail(cofUrl, senddata);
						
		respDTO = this.sendToCofAccountDetail(status);
				
				

				
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);		
		
		
		return respDTO;
	}
	
	
private String sendToCofAccountDetail(CardOnFileDTO cardOnFileDTO){
	
	String accDetails = null;
	accDetails = "{\"userId\":\"" + cardOnFileDTO.getUserId()
			+ "\"}";
	
	return accDetails;
}



/*
 * Sends cofUrl to COF server with the given URL
 * @param packetsend to cof  - mandatory
 * @param url - COF server url
 * @return String - represents status whether true/false
 * @throws BusinessException in case of business validation failures
 */
private String sendToCofAccountDetail(String cofUrl,String senddata) throws IntegrationException{
	
	
	String METHOD_NAME = "sendToCofAccountDetail";

	log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
	
	
	String status = null;
	
	HttpClient client = new DefaultHttpClient();
	
	
	HttpPost httpPost = new HttpPost(cofUrl);
	
	try {

		httpPost.setEntity(new StringEntity(senddata, ContentType
				.create(BusinessConstants.JSON_HEADER_TYPE)));

		HttpResponse resp = client.execute(httpPost);
		
		InputStream inputStream = resp.getEntity().getContent();

		BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
		
		String line = "";

		while ((line = rd.readLine()) != null) {
			
			status = line;
			
			
		}


			
			

	/*}   catch (IntegrationException accessException) {
		
		
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("IntegrationException occurred while getting connected with COF server ")
				.append(ConnectorConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF)
				.append(ConnectorConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new IntegrationException(accessException.getErrorCode(),
				accessException.getMessage());*/
		
		} catch (Exception ex) {
			
			
			sendToCofAccountDetailServiceException(ex);
		
	} finally {

		client.getConnectionManager().shutdown();
		
		log.info(status+Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
	}
	
	 return status;
	
}



	
	
	
	
	/*
	 * Sends cofUrl to COF server with the given URL
	 * @param packetsend to cof  - mandatory
	 * @param url - COF server url
	 * @return String - represents status whether true/false
	 * @throws BusinessException in case of business validation failures
	 */
	private List<CardOnFileDTO> sendToCofAccountDetail(String status) throws IntegrationException{
		
		
		 String METHOD_NAME="sendToCofAccountDetail";
			
		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		List<CardOnFileDTO> list = new ArrayList<CardOnFileDTO>();
				
		try {
			
				JSONParser json = new JSONParser();
				
				JSONObject object  = (JSONObject) json.parse(status);
				
				// loop array
				JSONArray slideContent = (JSONArray) object
						.get(BusinessConstants.REQUESTED_DATA);
				
				Iterator itr = slideContent.iterator();
				while (itr.hasNext()) {

					JSONObject slide = (JSONObject) itr.next();

					CardOnFileDTO newCof = new CardOnFileDTO();

					newCof.setAccId((String) slide.get(BusinessConstants.ACC_ID));
					newCof.setUserId((String) slide.get(BusinessConstants.USER_ID));
					newCof.setAccHolderName((String) slide.get(BusinessConstants.ACC_HOLDER_NAME));
					newCof.setAccType((String) slide.get(BusinessConstants.ACC_TYPE));
					newCof.setAccNo((String) slide.get(BusinessConstants.ACC_NO));
					newCof.setAccBankName((String) slide.get(BusinessConstants.ACC_BANKNAME));
					newCof.setAccBankRout((String) slide.get(BusinessConstants.ACC_BANK_ROUT));
					newCof.setAccBankType((String) slide.get(BusinessConstants.ACC_BANK_TYPE));
					newCof.setAccStreet((String) slide.get(BusinessConstants.ACC_STREET));
					newCof.setAccCity((String) slide.get(BusinessConstants.ACC_CITY));
					newCof.setAccState((String) slide.get(BusinessConstants.ACC_STATE));
					newCof.setAccZipcode((String) slide.get(BusinessConstants.ACC_ZIPCODE));
					newCof.setAccCardCvvCode((String) slide
							.get(BusinessConstants.ACC_CARD_CVVCODE));
					newCof.setAccCardExpDate((String) slide
							.get(BusinessConstants.ACC_CARD_EXPDATE));
					newCof.setCardNickName((String) slide.get(BusinessConstants.CARD_NICKNAME));
					newCof.setIsFirstCard((String) slide.get(BusinessConstants.ISFIRSTCARD));
					newCof.setAccTransactionPwd((String) slide.get(BusinessConstants.ACC_TRANSACTION_PASSWORD));
					newCof.setAccBrand((String) slide.get(BusinessConstants.ACC_BRAND));
					

					list.add(newCof);
				}

			
		}catch (ParseException e) {
			
			sendToCofAccountDetailParseException(e);
		
		}catch (NullPointerException e) {
			
			sendToCofAccountDetailNullException(e);
			
		}
		
		catch (Exception e) {
			sendToCofAccountDetailException(e);
			
		}
		
		finally {

			//client.getConnectionManager().shutdown();
			log.info(list + Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		}

			
		
		return list;
	}
	
	
	
	
	public CardOnFileRespDTO getAccountService(CardOnFileDTO cardOnFileDTO) throws CloudServiceException {
		
		 String METHOD_NAME="getAccountService";
			
		log.info(cardOnFileDTO+Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		String serverURL= PropertiesUtil.getProjectProperties().getProperty(BusinessConstants.COF_SERVICE_GETACCOUNT_URL);
						
		CardOnFileRespDTO respDTO = null;
		
		HttpClient client = new DefaultHttpClient();
		
		boolean value =false;
		
			
		HttpPost p = new HttpPost(serverURL);  
		
		String accDetails="{\"accId\":\"" +cardOnFileDTO.getAccId() + "\"}";
		
		try {
			      
			 p.setEntity(new StringEntity(accDetails,ContentType.create(BusinessConstants.JSON_HEADER_TYPE)));
			
	        
	        HttpResponse r = client.execute(p);

	        BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
	        String line = "";
	       
	        while ((line = rd.readLine()) != null) {
	           //Parse our JSON response 
	           JSONParser jsonpas = new JSONParser();
	           JSONObject jsonObject = (JSONObject)jsonpas.parse(line);
	           /*// loop array
	           Map response = (Map) jsonObject.get("account");
	           accountno.setAccNo((String) response.get("accNo"));*/
	           
	           Map response = (Map) jsonObject;
	           
	           if (response.get(BusinessConstants.TYPE).toString()
						.equalsIgnoreCase(BusinessConstants.REQ_TYPE)) {
	        	    respDTO = new CardOnFileRespDTO();
	        	 	        	   
	        	    Map jsnaccount= (JSONObject) response.get(BusinessConstants.ACCOUNT);
	        	    
	        	   /* Set<Map.Entry<String,String>> it= jsnaccount.entrySet();
	        	    Iterator< Map.Entry<String,String>> i1=it.iterator();
	        	    while(i1.hasNext())
	        	    {
	        	    	
	        	    	Map.Entry<String,String> ent=i1.next();
	        	    	ent.getKey(accNo);
	        	    }
	        	    */
	        	    /*JSONArray jArray = (Map) jsonObject.get("account");
	 	           
	 	           
	  	          // JSONArray msg = (JSONArray) jsonObject.get("messages");
	  	   			Iterator<String> iterator = jArray.iterator();
	  	   			
	  	   			while (iterator.hasNext()) {
	  	   			
	  	   			//String value = (String)iterator.next();	
	  	   			
	  	   			}*/
					respDTO.setType(response.get(BusinessConstants.TYPE).toString());
					respDTO.setAccNo(jsnaccount.get(BusinessConstants.ACC_NO).toString());
					respDTO.setAcctype(jsnaccount.get(BusinessConstants.ACC_TYPE).toString());
					if(respDTO.getAcctype().equalsIgnoreCase(BusinessConstants.ACCOUNT_BANK_TYPE))	{
					respDTO.setAcctHolderName(jsnaccount.get(BusinessConstants.ACC_HOLDER_NAME).toString());
					respDTO.setBankRoutingNumber(jsnaccount.get(BusinessConstants.ACC_BANK_ROUT).toString());
					respDTO.setBankName(jsnaccount.get(BusinessConstants.ACC_BANKNAME).toString());
					} else {
						
						if(jsnaccount.get(BusinessConstants.ACC_CARD_EXPDATE)!=null ){
						
							respDTO.setCardExpiry(jsnaccount.get(BusinessConstants.ACC_CARD_EXPDATE).toString());
						}
						
						
					}
					respDTO.setUserId(jsnaccount.get(BusinessConstants.USER_ID).toString());
					

				} else {
					
					respDTO = new CardOnFileRespDTO();
					respDTO.setType(response.get(BusinessConstants.TYPE).toString());

					respDTO.setMsg(response.get(BusinessConstants.MESSAGE).toString());

				}
	          
	         	        	
	        	
	        }
	   
		}catch (ParseException e) {
			getAccountCloudServiceException(e);
		}catch (NullPointerException e) {
			getAccountNullException(e);
		} catch (Exception ex) {
			getAccountException(ex);
		}  finally {

		client.getConnectionManager().shutdown();
		log.info(respDTO + Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
	}
	
		
						
		return respDTO;
		
	}
	
	
	
	////////////////////////////////Exception//////////////////////////////
	
	public CardOnFileRespDTO deleteAccountService(CardOnFileDTO cardOnFileDTO) {

		String METHOD_NAME = "deleteAccountService";

		log.info(cardOnFileDTO
				+ Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		String serverURL = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.COF_SERVICE_DELETE_URL);

		HttpClient client = new DefaultHttpClient();

		CardOnFileRespDTO respDTO=null;

		HttpPost p = new HttpPost(serverURL);

		String deleteacc = "{\"userId\":\"" + cardOnFileDTO.getUserId()
				+ "\",\"accId\":\"" + cardOnFileDTO.getAccId() + "\"}";

		try {

			p.setEntity(new StringEntity(deleteacc, ContentType
					.create(BusinessConstants.JSON_HEADER_TYPE)));

			HttpResponse r = client.execute(p);

			BufferedReader rd = new BufferedReader(new InputStreamReader(r
					.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				// Parse our JSON response
				// value = line;
				JSONParser j = new JSONParser();
				JSONObject o = (JSONObject) j.parse(line);
				// Map response = (Map)o.get("response");
				Map response = (Map) o;
				if (response.get(BusinessConstants.TYPE).toString()
						.equalsIgnoreCase(BusinessConstants.REQ_TYPE)) {
					
				    respDTO = new CardOnFileRespDTO();
					respDTO.setType(response.get(BusinessConstants.TYPE).toString());
					respDTO.setAcctype(response.get(BusinessConstants.ACCT_TYPE).toString());

				} else {
					
					respDTO = new CardOnFileRespDTO();
					respDTO.setType(response.get(BusinessConstants.TYPE).toString());

					respDTO.setMsg(response.get(BusinessConstants.MESSAGE).toString());

				}

			}

		} catch (ParseException e) {
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
					new StringBuilder(METHOD_NAME));
		} catch (IOException e) {
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
					new StringBuilder(METHOD_NAME));
		} finally {

			client.getConnectionManager().shutdown();
			log.info(respDTO + Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		}
		return respDTO;
	}
	
	
	///////////////// private methods for exception /////////////
	
	
	/**
	 * get CloudServiceException for getAccount
	 * 
	 * @param ParseException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException getAccountCloudServiceException(
			ParseException e) throws CloudServiceException {
		String METHOD_NAME = "getAccountCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("ParseException occurred while parse the cof data:")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.CONNECTING_WITH_COF, e.getMessage());
	}
	
	
	

	/**
	 * get NullPointerException for getAccountException
	 * 
	 * @param NullPointerException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException getAccountNullException(
			NullPointerException e) throws CloudServiceException {
		String METHOD_NAME = "getAccountNullException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("NullPointerException occurred while getting cof data:")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new CloudServiceException(ErrorCodeConstants.CONNECTING_WITH_COF, e.getMessage());
	}
	
	
	

	/**
	 * get IntegrationException for getAccountException
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException getAccountException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getAccountException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting cof data:")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.CONNECTING_WITH_COF,
				ex.getMessage());
	}
	
	/**
	 * get cloudserviceException for addAccount
	 * 
	 * @param IntegrationException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException addAccountCloudServiceException(
			IntegrationException accessException) throws CloudServiceException {
		String METHOD_NAME = "addAccountCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("IntegrationException occurred while adding Account  ")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACOUNT)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}

	/**
	 * get Exception for addAccount
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException addAccountException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "addAccountException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while adding Account ")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACOUNT)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACOUNT,
				ex.getMessage());
	}
	
	
	
	
	/**
	 * get cloudserviceException for addBank
	 * 
	 * @param IntegrationException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException addAchServiceCloudServiceException(
			IntegrationException accessException) throws CloudServiceException {
		String METHOD_NAME = "addAchServiceCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("IntegrationException occurred while adding bank  ")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}

	/**
	 * get Exception for addBank
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException addAchServiceException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "addAchServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while adding Bank ")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.CONNECTING_WITH_COF,
				ex.getMessage());
	}
	
	
	
	
	
	/**
	 * get cloudserviceException for updateAccount
	 * 
	 * @param IntegrationException
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException updateAccountCloudServiceException(
			IntegrationException accessException) throws CloudServiceException {
		String METHOD_NAME = "updateAccountCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("IntegrationException occurred while updating card/bank  ")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(accessException.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, accessException, errorBuffer);
		throw new CloudServiceException(accessException.getErrorCode(),
				accessException.getMessage());
	}

	/**
	 * get Exception for updateAccount
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException updateAccountException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "updateAccountException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while updating card/bank")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.CONNECTING_WITH_COF,
				ex.getMessage());
	}
	
	
	
	
	
	/**
	 * get IntegrationException for sendToCof
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private IntegrationException sendToCofIntegrtnException(
			ParseException e) throws IntegrationException {
		String METHOD_NAME = "sendToCofIntegrtnException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("ParseException occurred while getting the reponse from cof for adding ACH ")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACH, e.getMessage());
	}
	
	
	
	/**
	 * get IntegrationException for sendToCof
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private IntegrationException sendToCofNullException(
			NullPointerException e) throws IntegrationException {
		String METHOD_NAME = "sendToCofNullException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("NullPointerException occurred while getting the reponse from cof for adding ACH ")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACH, e.getMessage());
	}
	
	
	
	/**
	 * get IntegrationException for sendToCof
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private IntegrationException sendToCofACHException(
			Exception e) throws IntegrationException {
		String METHOD_NAME = "sendToCofACHException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while getting the reponse from cof for adding ACH ")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACH, e.getMessage());
	}
	
	
	/**
	 * get IntegrationException for sendToCof
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private CloudServiceException sendToCofException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "sendToCofException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while sendToCof for AddAccount")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACOUNT)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.CONNECTING_WITH_COF_ADD_ACOUNT,
				ex.getMessage());
	}
	
	
	
	/**
	 * get Exception for sendToCof
	 * 
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException
	 */
	private IntegrationException sendToCofAccountDetailServiceException(
			Exception e) throws IntegrationException {
		String METHOD_NAME = "sendToCofAccountDetailServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while get the reponse from cof ")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.CONNECTING_WITH_COF, e.getMessage());
	}
	
	
	
	
	/**
	 * get IntegrationException for sendToCofAccountDetail
	 * 
	 * @param ParseException
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException sendToCofAccountDetailNullException(
			NullPointerException e) throws IntegrationException {
		String METHOD_NAME = "sendToCofAccountDetailNullException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("NullPointerException occurred while getting cof AccountDetail:")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.FETCH_ALL_CARD_BANK_FROM_COF_, e.getMessage());
	}
	
	
	

	/**
	 * get IntegrationException for sendToCofAccountDetail
	 * 
	 * @param ParseException
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException sendToCofAccountDetailParseException(
			ParseException e) throws IntegrationException {
		String METHOD_NAME = "sendToCofAccountDetailParseException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("NullPointerException occurred while getting cof AccountDetail:")
		.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
		.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
        log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);       
		throw new IntegrationException(ErrorCodeConstants.FETCH_ALL_CARD_BANK_FROM_COF_, e.getMessage());
	}
	
	
	
	/**
	 * get IntegrationException for sendToCofAccountDetail
	 * 
	 * @param Exception
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException sendToCofAccountDetailException(
			Exception e) throws IntegrationException {
		String METHOD_NAME = "sendToCofAccountDetailException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("Exception occurred while getting cof AccountDetail:")
		.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
		.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
        log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);       
		throw new IntegrationException(ErrorCodeConstants.FETCH_ALL_CARD_BANK_FROM_COF_, e.getMessage());
	}
	
	
}
