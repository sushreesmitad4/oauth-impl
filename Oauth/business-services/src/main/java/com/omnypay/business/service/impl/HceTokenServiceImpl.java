/**
 * 
 */
package com.omnypay.business.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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




import com.omnypay.business.service.HceTokenService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.business.util.PropertiesUtil;
import com.omnypay.common.services.dto.HceTokenDTO;
import com.omnypay.common.services.dto.HceTokenRespDTO;
import com.omnypay.dao.util.CloudDAException;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/**
 * @author iliyasm
 *
 */
public class HceTokenServiceImpl implements HceTokenService{
	
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	
	
	private final String CLASS_NAME = this.getClass().getName();
	
	

	/**
	 * to get OnlineToken for a transaction
	 * @param hceTokenDTO packet send to hce  - mandatory
	 * @param accType - Account type of the account
	 * @return respDTO - represents response having all  details
	 * @throws CloudServiceException 
	 * @throws BusinessException in case of business validation failures
	 */
	public HceTokenRespDTO getOnlineTokenService(HceTokenDTO hceTokenDTO,String accType) throws  IntegrationException{
		// TODO Auto-generated method stub

		String METHOD_NAME = "getOnlineTokenService";

		log.info(hceTokenDTO
				+ Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		String serverURL = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.HCE_SERVICE_GET_ONLINE_TOKEN_URL);

		HttpClient client = new DefaultHttpClient();
		
		HceTokenRespDTO respDTO = null;
		String getToken = null;
		//String value = null;

		HttpPost p = new HttpPost(serverURL);
		
		if(hceTokenDTO.getTokenType().equalsIgnoreCase(BusinessConstants.HCE_ONLINE_T_TYPE)){
	     
			getToken = "{\"userId\":\"" + hceTokenDTO.getUserId()
				+ "\",\"accountRefId\":\"" + hceTokenDTO.getAccountRefId()
				+ "\",\"tokenType\":\"" + hceTokenDTO.getTokenType()
				+ "\",\"serviceRefId\":\"" + hceTokenDTO.getServiceRefId()
				+ "\",\"tokenRequestorId\":\""
				+ hceTokenDTO.getTokenRequestorId() + "\"}";
		}
		else{
			
		    getToken = "{\"userId\":\"" + hceTokenDTO.getUserId()
		    		+ "\",\"imeiNo\":\"" + hceTokenDTO.getImeiNo()
		    		+ "\",\"password\":\"" + hceTokenDTO.getPasscode()
		    		+ "\",\"accountRefId\":\"" + hceTokenDTO.getAccountRefId()
					+ "\",\"isFirstCard\":\"" + hceTokenDTO.getIsFirstCard()
					+ "\",\"tokenType\":\"" + hceTokenDTO.getTokenType()
					+ "\",\"serviceRefId\":\"" + hceTokenDTO.getServiceRefId()
					+ "\",\"tokenRequestorId\":\""
					+ hceTokenDTO.getTokenRequestorId() + "\"}";
			
		}

		try {

			p.setEntity(new StringEntity(getToken, ContentType
					.create(BusinessConstants.JSON_HEADER_TYPE)));

			HttpResponse r = client.execute(p);

			BufferedReader rd = new BufferedReader(new InputStreamReader(r
					.getEntity().getContent()));

			String line = "";

			while ((line = rd.readLine()) != null) {
				// Parse our JSON response
				// value = line;
				JSONParser j = new JSONParser();
				JSONObject jsonObject = (JSONObject) j.parse(line);
				
				// if status code 1 means success else  0 (fail)
				String statusCode = (String) jsonObject.get(BusinessConstants.HCE_STSTUS_CODE);

				if (statusCode
						.equalsIgnoreCase(BusinessConstants.HCE_STATUS_CODE_SUCCESS)) {

					respDTO =  new HceTokenRespDTO();
					
					JSONArray jArray = (JSONArray) jsonObject.get(BusinessConstants.TOKENS);
					
					Iterator<Map<String,String>> iterator = jArray.iterator();
					
					List<Map<String,String>> tokenList = new ArrayList<Map<String,String>>();
					
					
					while(iterator.hasNext())
					{
						JSONObject jsonObjectt=(JSONObject) iterator.next();
						
						//tokenList=(List<Map<String, String>>) jsonObjectt;
						Map<String,String> jsonmap= jsonObjectt;
						
						Map< String, String> linmap=(Map<String, String>) jsonmap;
						
						tokenList.add(linmap);
						
					}
					
					respDTO.setTokens(tokenList);
					
					respDTO.setStatusCode(statusCode);
					
					respDTO.setTokenexpDate((String) jsonObject.get(BusinessConstants.TOKEN_EXP_DATE));
			
					if(hceTokenDTO.getTokenType().equalsIgnoreCase(BusinessConstants.HCE_OFFLINE_T_TYPE)
							&& ( (!accType.equalsIgnoreCase(BusinessConstants.ACCOUNT_BANK_TYPE))&& hceTokenDTO.getIsFirstCard().equalsIgnoreCase(BusinessConstants.IS_FIRST_CARD))){  						
						
						respDTO.setUdk((String) jsonObject.get(BusinessConstants.UDK));
					}
					
					
				} else {
					
					
					respDTO =  new HceTokenRespDTO();
					
					respDTO.setStatusCode(statusCode);
					
					String message = (String) jsonObject.get(BusinessConstants.MESSAGE);
					
					respDTO.setMessage(message);
					
							
					
				}
			}
			} catch (ParseException e) {
				getOnlineTokenServiceIntegrationException(e);
			}catch (NullPointerException e) {
				getOnlineTokenServiceNullException(e);
			}catch (Exception ex) {
				getOnlineTokenServiceException(ex);
				}
		  finally {

			client.getConnectionManager().shutdown();

			log.info(respDTO + Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		}

		return respDTO;
	}
	
	

	/**
	 * to delete OnlineToken give request to hce
	 * @param hceTokenDTO packet send to hce  - mandatory
	 * @return respDTO - represents response having all  details from hce
	 * @throws BusinessException in case of business validation failures
	 */
	
	public HceTokenRespDTO deleteOnlineToken(HceTokenDTO hceTokenDTO) {
		// TODO Auto-generated method stub

		String METHOD_NAME = "deleteOnlineToken";

		log.info(hceTokenDTO
				+ Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		String serverURL = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.HCE_SERVICE_DELETE_ONLINE_TOKEN_URL);

		HttpClient client = new DefaultHttpClient();
		
		HceTokenRespDTO respDTO = null;

		//String value = null;

		HttpPost p = new HttpPost(serverURL);

		String onLineToken = "{\"userId\":\"" + hceTokenDTO.getUserId()
				+ "\",\"accountRefId\":\"" + hceTokenDTO.getAccountRefId() + "\"}";

		try {

			p.setEntity(new StringEntity(onLineToken, ContentType
					.create(BusinessConstants.JSON_HEADER_TYPE)));

			HttpResponse r = client.execute(p);

			BufferedReader rd = new BufferedReader(new InputStreamReader(r
					.getEntity().getContent()));

			String line = "";

			while ((line = rd.readLine()) != null) {
				// Parse our JSON response
				// value = line;
				JSONParser j = new JSONParser();
				JSONObject jsonObject = (JSONObject) j.parse(line);
				
				// if status code 1 means success else  0 (fail)
				String statusCode = (String) jsonObject.get(BusinessConstants.HCE_STSTUS_CODE);

				if (statusCode
						.equalsIgnoreCase(BusinessConstants.HCE_STATUS_CODE_SUCCESS)) {

					respDTO =  new HceTokenRespDTO();
					String message = (String) jsonObject.get(BusinessConstants.MESSAGE);
					respDTO.setMessage(message);
					respDTO.setStatusCode(statusCode);
					
					
				} else {
					
					
					respDTO =  new HceTokenRespDTO();
					
					respDTO.setStatusCode(statusCode);
					
					String message = (String) jsonObject.get(BusinessConstants.MESSAGE);
					
					respDTO.setMessage(message);
					
					
					
					
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


	
	/**
	 * to get offlineToken giving request to hce
	 * @param hceTokenDTO packet send to hce  - mandatory
	 * @return respDTO - represents response having all  details from hce
	 * @throws BusinessException in case of business validation failures
	 */
	//@Override
	public List<HceTokenRespDTO> getOfflineTokenForAllCardsService(List<HceTokenDTO> hceTokenDTO) throws IntegrationException{
		// TODO Auto-generated method stub

		String METHOD_NAME = "getOfflineTokenForAllCardsService";

		log.info(hceTokenDTO
				+ Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);

		String serverURL = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.HCE_SERVICE_GET_OFFLINE_TOKEN_FOR_ALLCARDS_URL);

		HttpClient client = new DefaultHttpClient();
		
		List<HceTokenRespDTO> respDTO = new ArrayList<HceTokenRespDTO>();
		
		HceTokenRespDTO	responseDTO=null;
		
		String getToken = null;
		
		StringBuilder builder = new StringBuilder();;
		
		HttpPost p = new HttpPost(serverURL);
		
		for( HceTokenDTO hce : hceTokenDTO)
		{
			
			getToken = "{\"userId\":\"" + hce.getUserId()
		    		+ "\",\"accountRefId\":\"" + hce.getAccountRefId()
		    		+ "\",\"serviceRefId\":\"" + hce.getServiceRefId()
		    		+ "\",\"imeiNo\":\"" + hce.getImeiNo()
		    		+ "\",\"password\":\"" + hce.getPasscode()
					+ "\",\"isFirstCard\":\"" + hce.getIsFirstCard()
					+ "\",\"tokenType\":\"" + hce.getTokenType() + "\"}";
			
		
		

		try {

			p.setEntity(new StringEntity(getToken, ContentType
					.create(BusinessConstants.JSON_HEADER_TYPE)));
					
				

			HttpResponse r = client.execute(p);

			BufferedReader rd = new BufferedReader(new InputStreamReader(r
					.getEntity().getContent()));

			String line = "";

			while ((line = rd.readLine()) != null) {
				// Parse our JSON response
				// value = line;
				JSONParser j = new JSONParser();
				JSONObject jsonObject = (JSONObject) j.parse(line);
				
				// if status code 1 means success else  0 (fail)
				String statusCode = (String) jsonObject.get(BusinessConstants.HCE_STSTUS_CODE);

				if (statusCode
						.equalsIgnoreCase(BusinessConstants.HCE_STATUS_CODE_SUCCESS)) {

				

                    responseDTO =  new HceTokenRespDTO();
                    
					JSONArray jArray = (JSONArray) jsonObject.get(BusinessConstants.TOKENS);
					
					Iterator<Map<String,String>> iterator = jArray.iterator();
					
					List<Map<String,String>> tokenList = new ArrayList<Map<String,String>>();
					
					
					while(iterator.hasNext())
					{
						JSONObject jsonObjectt=(JSONObject) iterator.next();
						
						Map<String,String> jsonmap= jsonObjectt;
						
						Map< String, String> linmap=(Map<String, String>) jsonmap;
						
						tokenList.add(linmap);
						
					}
					
					responseDTO.setTokens(tokenList);
					if((String) jsonObject.get(BusinessConstants.UDK)!=null)
					{
					responseDTO.setUdk((String) jsonObject.get(BusinessConstants.UDK));
					}
					responseDTO.setAccountRefId((String) jsonObject.get(BusinessConstants.ACCOUNT_REFID));
					
					responseDTO.setStatusCode(statusCode);
					
					responseDTO.setTokenexpDate((String) jsonObject.get(BusinessConstants.TOKEN_EXP_DATE));
					
			    		  
					
				} else {
					
					
					responseDTO = new HceTokenRespDTO();
					
					responseDTO.setStatusCode(statusCode);
					
					String message = (String) jsonObject.get(BusinessConstants.MESSAGE);
					
					responseDTO.setMessage(message);
					
							
					
				}
				respDTO.add(responseDTO);
			}
		} catch (ParseException e) {
			getOfflineTokenForAllCardsIntegrationException(e);
		}catch (NullPointerException e) {
			getOfflineTokenForAllCardsNullException(e);
		}catch (Exception ex) {
			getOfflineTokenForAllCardsException(ex);
			} finally {

			client.getConnectionManager().shutdown();

			log.info(respDTO + Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		}

	}
		
		return respDTO;
	}
	
	
	
	
	
	
	
	/**
	 * get IntegrationException for getonlineToken
	 * 
	 * @param ParseException
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException getOnlineTokenServiceIntegrationException(
			ParseException e) throws IntegrationException {
		String METHOD_NAME = "getOnlineTokenServiceCloudServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("ParseException occurred while get OnlineToken from hce")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new  IntegrationException(ErrorCodeConstants.PARSE_EXCEPTION_DURING_GETONLINETOKEN, e.getMessage());
	}
	
	
	

	/**
	 * get NullPointerException for getonlineToken
	 * 
	 * @param ParseException
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException getOnlineTokenServiceException(
			Exception ex) throws IntegrationException {
		String METHOD_NAME = "getOnlineTokenServiceException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while get OnlineToken from hce")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_HCE)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new IntegrationException(
				ErrorCodeConstants.CONNECTING_WITH_HCE,
				ex.getMessage());
	}
	
	
	

	/**
	 * get IntegrationException for getonlineToken
	 * 
	 * @param NullPointerException
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException getOnlineTokenServiceNullException(
			NullPointerException e) throws IntegrationException {
		String METHOD_NAME = "forgotPasswordException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("NullPointerException occurred while getting cof data:")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.NULLPOINTER_EXCEPTION_GETONLINETOKEN, e.getMessage());
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * get IntegrationException for getOfflineTokenForAllCards
	 * 
	 * @param ParseException
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException getOfflineTokenForAllCardsIntegrationException(
			ParseException e) throws IntegrationException {
		String METHOD_NAME = "getOfflineTokenForAllCardsIntegrationException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("NullPointerException occurred while getting HCE data from HCE Server")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.PARSE_EXCEPTION_FETCH_TOKEN_FROM_HCE, e.getMessage());
	}
	
	
	

	/**
	 * get NullPointerException for getOfflineToken
	 * 
	 * @param ParseException
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException getOfflineTokenForAllCardsNullException(
			NullPointerException e) throws IntegrationException {
		String METHOD_NAME = "getOfflineTokenForAllCardsNullException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer.append("NullPointerException occurred while getting HCE data from HCE Server")
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA).append(BusinessConstants.ERROR_MSG_STR).append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new IntegrationException(ErrorCodeConstants.NULLPOINTER_FETCH_TOKEN_FROM_HCE, e.getMessage());
	}
	
	
	

	/**
	 * get IntegrationException for getOfflineTokenForAllCardsException
	 * 
	 * @param Exception
	 * @return IntegrationException
	 * @throws IntegrationException
	 */
	private IntegrationException getOfflineTokenForAllCardsException(
			Exception ex) throws IntegrationException {
		String METHOD_NAME = "getOfflineTokenForAllCardsException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while getting offline token from HCE server")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_HCE)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new IntegrationException(
				ErrorCodeConstants.CONNECTING_WITH_HCE,
				ex.getMessage());
	}
	
	
}
