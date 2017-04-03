/**
 * 
 */
package com.omnypay.business.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.dao.DataAccessException;

import com.omnypay.business.service.PosService;
import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.CloudServiceException;
import com.omnypay.business.util.IntegrationException;
import com.omnypay.business.util.PropertiesUtil;
import com.omnypay.common.services.dto.PosDTO;
import com.omnypay.common.services.dto.SwitchTxnRespDTO;
import com.omnypay.dao.util.DaoConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/**
 * @author iliyasm
 *
 */
public class PosServiceImpl implements PosService{
	
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	
	

   /*    public PosRespDTO posConnectorService(PosDTO posDTO,HceTokenRespDTO token,String acctype) {
		
		String METHOD_NAME="posConnectorService";
		
		log.info(posDTO+Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		String serverURL= PropertiesUtil.getProjectProperties().getProperty(ConnectorConstants.POS_SERVICE_URL);
		
		
		String makePayment = null;
		
		
		HttpClient client = new DefaultHttpClient();
		
		PosRespDTO posRespDTO = null;
		
		if(acctype.equalsIgnoreCase(ConnectorConstants.BANK_TYPE)){
			
			makePayment="{\"REQ_TYPE\":\"" +ConnectorConstants.REQ_TYPE_PROCESS_BANK + "\",\"TOKEN\":\"" +token.getOnlinetokens() + "\",\"AMOUNT\":\"" +posDTO.getAmount() + "\",\"TID\":\"" +posDTO.getTid() + "\",\"MID\":\"" +posDTO.getMid() + "\",\"TXN_TYPE\":\"" +ConnectorConstants.TXN_TYPE_PROCESS_BANK + "\"}";	
			
		} else {

			makePayment = "{\"REQ_TYPE\":\""
					+ ConnectorConstants.REQ_TYPE_PROCESS_CARD
					+ "\",\"TRACK\":\"" + posDTO.getTrack2() + "\",\"AMOUNT\":\"" +posDTO.getAmount() + "\",\"TXN_TYPE\":\""
					+ ConnectorConstants.TXN_TYPE_PROCESS_CARD + "\"}";

		}
		
				
		
		HttpPost p = new HttpPost(serverURL); 
		

		
		try{
						
			 p.setEntity(new StringEntity(makePayment, 
		                ContentType.create(ConnectorConstants.JSON_HEADER_TYPE)));
	        
	        HttpResponse r = client.execute(p);		
		
		    String value =null;
		    
			BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
			
	        String line = "";
	        
	        while ((line = rd.readLine()) != null) {
	           //Parse our JSON response 
	        	value = line;
	        	
	        	JSONParser j = new JSONParser();
		       	 
			    JSONObject jsonObject = (JSONObject)j.parse(line);	
			     
			    String   resCode  = (String) jsonObject.get(ConnectorConstants.RSP_CODE);
			     
			     if (resCode.equalsIgnoreCase(ConnectorConstants.POS_STATUS_CODE_SUCCESS)){
			    				    			    	
			    	 posRespDTO  = new PosRespDTO();
			    	 
			    	 posRespDTO.setRestype(resCode);
			    	 
			    	 String   rrn  = (String) jsonObject.get(ConnectorConstants.RRN);
			    	 
			    	 posRespDTO.setRrn(rrn);
				     
			    	 
			     } else{
			    	 
			    	 posRespDTO  = new PosRespDTO();
			    	 
			         posRespDTO.setMessage((String) jsonObject.get(ConnectorConstants.ERR_MSG));
			         
			    	 posRespDTO.setRestype(resCode);
			     }
			     
			    
		         	        	
		        	
		        }
		    } catch(ParseException e) {
		        
		    	log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
						new StringBuilder(METHOD_NAME));
		    } catch(IOException e) {
		    	
		    	log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
						new StringBuilder(METHOD_NAME));
		    } finally {
			
			 client.getConnectionManager().shutdown();
			 log.info(posRespDTO+Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		    }
			//return value;
			return posRespDTO;
		}
		*/
	//@Override
	/*public PosRespDTO posAmountConfirmConnectorService(PosDTO posDTO) {
	
	
String METHOD_NAME="posAmountConfirmConnectorService";
		
		log.info(posDTO+Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		String serverURL=PropertiesUtil.getProjectProperties().getProperty(ConnectorConstants.POS_SERVICE_URL_CONFIRM_AMOUNT);
		
				
		HttpClient client = new DefaultHttpClient();
		
		PosRespDTO posRespDTO = null;
		
		//Long value =null;
		
		String onLineToken="{\"REQ_TYPE\":\"" +ConnectorConstants.REQ_TYPE + "\",\"POSID\":\"" +posDTO.getPosId() + "\"}";		
		
		HttpPost p = new HttpPost(serverURL); 
		
		try{
		
			
			 p.setEntity(new StringEntity(onLineToken, 
		                ContentType.create(ConnectorConstants.JSON_HEADER_TYPE)));
	        
	        HttpResponse r = client.execute(p);

	        BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
	        
	        String line = "";
	        
	        while ((line = rd.readLine()) != null) {
	           //Parse our JSON response 
	       	 JSONParser j = new JSONParser();
	       	 
		     JSONObject jsonObject = (JSONObject)j.parse(line);	
		    
		     
		     String   resCode  = (String) jsonObject.get(ConnectorConstants.RSP_CODE);
		     String err= (String) jsonObject.get(ConnectorConstants.ERR_MSG);
		    
		     
		     if (resCode.equalsIgnoreCase(ConnectorConstants.POS_STATUS_CODE_SUCCESS)){
		    	 
		    	 posRespDTO  = new PosRespDTO();
		    	 
		    	 BigDecimal bd = new BigDecimal((String)jsonObject.get(ConnectorConstants.AMOUNT)).divide(new BigDecimal(100));
		    	 
                 DecimalFormat df = new DecimalFormat(ConnectorConstants.AMOUNT_FORMAT);
                 
                 String amount= df.format(bd);    
                 
		    	 posRespDTO.setAmount(amount);
		    	
		    	 posRespDTO.setPosId((String) jsonObject.get(ConnectorConstants.POSID));
			    
		    	 posRespDTO.setRestype(resCode);
			     
		    	 
		     } else{
		    	 
		    	 posRespDTO  = new PosRespDTO();
		    	 posRespDTO.setPosId((String) jsonObject.get(ConnectorConstants.POSID));
		    	 posRespDTO.setMessage((String) jsonObject.get(ConnectorConstants.ERR_MSG));
		    	 posRespDTO.setRestype(resCode);
		     }
		     
		    
	         	        	
	        	
	        }
	    } catch(ParseException e) {
	        
	    	log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
					new StringBuilder(METHOD_NAME));
	    } catch(IOException e) {
	    	
	    	log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
					new StringBuilder(METHOD_NAME));
	    } finally {
		
		 client.getConnectionManager().shutdown();
		 log.info(posRespDTO+Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
	    }
		//return value;
		return posRespDTO;
	
	}
*/
	//@Override
	public SwitchTxnRespDTO sendToSwitch(PosDTO posdata)
			throws CloudServiceException {

		String METHOD_NAME = "sendToSwitch";

		log.info(posdata + Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);

		String serverURL = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.SWH_SERVICE_URL);

		HttpClient client = new DefaultHttpClient();

		SwitchTxnRespDTO posRespDTO = null;

		String senddata = this.sendToSwitchLocal(posdata);

		HttpPost p = new HttpPost(serverURL);

		try {

			p.setEntity(new StringEntity(senddata, ContentType
					.create(BusinessConstants.JSON_HEADER_TYPE)));

			HttpResponse r = client.execute(p);

			BufferedReader rd = new BufferedReader(new InputStreamReader(r
					.getEntity().getContent()));

			String line = "";

			while ((line = rd.readLine()) != null) {
				// Parse our JSON response
				JSONParser j = new JSONParser();

				JSONObject jsonObject = (JSONObject) j.parse(line);

				String resCode = (String) jsonObject
						.get(BusinessConstants.HCE_STSTUS_CODE);
				String err = (String) jsonObject.get(BusinessConstants.ERR_MSG);
				// System.out.println(err);

				if (resCode
						.equalsIgnoreCase(BusinessConstants.POS_STATUS_CODE_SUCCESS)) {

					posRespDTO = new SwitchTxnRespDTO();

					posRespDTO.setMessage((String) jsonObject
							.get(BusinessConstants.MESSAGE));
					posRespDTO.setStatusCode((String) jsonObject
							.get(BusinessConstants.HCE_STSTUS_CODE));
					posRespDTO.setSwitchTxnDate((String) jsonObject
							.get(BusinessConstants.SWITCH_TXN_DATE));

				} else {

					posRespDTO = new SwitchTxnRespDTO();
					posRespDTO.setMessage((String) jsonObject
							.get(BusinessConstants.MESSAGE));
					posRespDTO.setStatusCode((String) jsonObject
							.get(BusinessConstants.HCE_STSTUS_CODE));
				}

			}
			/*
			 * } catch(ParseException e) {
			 * 
			 * log.error(Log4jConstants.LOG_EXIT,CLASS_NAME,
			 * e.fillInStackTrace(), new StringBuilder(METHOD_NAME)); }
			 * catch(IOException e) {
			 * 
			 * log.error(Log4jConstants.LOG_EXIT,CLASS_NAME,
			 * e.fillInStackTrace(), new StringBuilder(METHOD_NAME)); } finally
			 * {
			 * 
			 * client.getConnectionManager().shutdown();
			 * log.info(posRespDTO+Log4jConstants.LOG_EXIT,CLASS_NAME,
			 * METHOD_NAME); }
			 */

		} catch (ParseException e) {

			getSendToSwitchParseException(e);

		} catch (NullPointerException e) {
			
			getSendToSwitchNullPointerException(e);
		}

		catch (Exception ex) {
			getSendToSwitchException(ex);
		} finally {

			client.getConnectionManager().shutdown();
			log.info(posRespDTO + Log4jConstants.LOG_EXIT, CLASS_NAME,
					METHOD_NAME);
		}

		// return value;
		return posRespDTO;

	}
	
	private Map<String, String> getCardType(){
		Map<String, String> map = new HashMap<String, String>();
		map.put(BusinessConstants.ZERO, BusinessConstants.BANK);
		map.put(BusinessConstants.NUMBER_ONE, BusinessConstants.DEBIT);
		map.put(BusinessConstants.NUMBER_TWO, BusinessConstants.CREDIT);
		return map;
	}

	
	
	
	
	private String nullCheckCardExp(String cardExpiry){
		
		
		if (cardExpiry!=null && cardExpiry.length()!=0){
			
			 return cardExpiry.replace("/", "");
		} else {
			
			return cardExpiry;
		}
		
		
	}
	
/*	public static void main(String as[]){
		
		
		PosDTO pos = new PosDTO();
		pos.setAmount("12345678901");
		new PosServiceImpl().posAmountConfirmConnector(pos);
		
	}*/
	

	private String getToken(String track2data )
	{	
		 String tokendata=track2data.substring(track2data.indexOf(";")+1, track2data.indexOf("="));
		 
		 log.info("TOKEN=="+tokendata);

		 return tokendata;
	}
	
	
	
	private String sendToSwitchLocal(PosDTO posdata) {

		String ecommTxn = null;

		String accType = posdata.getAccType();

		String track2data = posdata.getTrack2().substring(
				posdata.getTrack2().indexOf(";"), posdata.getTrack2().length());

		if (posdata.getShippingAddress2() != null
				&& posdata.getShippingAddress2().length() != 0
				&& posdata.getShippingAddress3() != null
				&& posdata.getShippingAddress3().length() != 0) {

			ecommTxn = "{\"track2data\":\"" + track2data + "\",\"accType\":\""
					+ accType + "\",\"amount\":\"" + posdata.getAmount()
					+ "\",\"merchantId\":\"" + posdata.getMid()
					+ "\",\"shippingAddress1\":\""
					+ posdata.getShippingAddress1()
					+ "\",\"shippingAddress2\":\""
					+ posdata.getShippingAddress2()
					+ "\",\"shippingAddress3\":\""
					+ posdata.getShippingAddress3() + "\",\"shippingCity\":\""
					+ posdata.getShippingCity() + "\",\"shippingZipCode\":\""
					+ posdata.getShippingZipCode() + "\",\"shippingState\":\""
					+ posdata.getShippingState() + "\"}";

		} else if (posdata.getShippingAddress2() != null
				&& posdata.getShippingAddress2().length() != 0
				&& posdata.getShippingAddress3() == null) {

			ecommTxn = "{\"track2data\":\"" + track2data + "\",\"accType\":\""
					+ accType + "\",\"amount\":\"" + posdata.getAmount()
					+ "\",\"merchantId\":\"" + posdata.getMid()
					+ "\",\"shippingAddress1\":\""
					+ posdata.getShippingAddress1()
					+ "\",\"shippingAddress2\":\""
					+ posdata.getShippingAddress2() + "\",\"shippingCity\":\""
					+ posdata.getShippingCity() + "\",\"shippingZipCode\":\""
					+ posdata.getShippingZipCode() + "\",\"shippingState\":\""
					+ posdata.getShippingState() + "\"}";

		} else if (posdata.getShippingAddress3() != null
				&& posdata.getShippingAddress3().length() != 0
				&& posdata.getShippingAddress2() == null) {

			ecommTxn = "{\"track2data\":\"" + track2data + "\",\"accType\":\""
					+ accType + "\",\"amount\":\"" + posdata.getAmount()
					+ "\",\"merchantId\":\"" + posdata.getMid()
					+ "\",\"shippingAddress1\":\""
					+ posdata.getShippingAddress1()
					+ "\",\"shippingAddress3\":\""
					+ posdata.getShippingAddress3() + "\",\"shippingCity\":\""
					+ posdata.getShippingCity() + "\",\"shippingZipCode\":\""
					+ posdata.getShippingZipCode() + "\",\"shippingState\":\""
					+ posdata.getShippingState() + "\"}";

		} else if (posdata.getShippingAddress3() == null
				&& posdata.getShippingAddress2() == null) {

			ecommTxn = "{\"track2data\":\"" + track2data + "\",\"accType\":\""
					+ accType + "\",\"amount\":\"" + posdata.getAmount()
					+ "\",\"merchantId\":\"" + posdata.getMid()
					+ "\",\"shippingAddress1\":\""
					+ posdata.getShippingAddress1() + "\",\"shippingCity\":\""
					+ posdata.getShippingCity() + "\",\"shippingZipCode\":\""
					+ posdata.getShippingZipCode() + "\",\"shippingState\":\""
					+ posdata.getShippingState() + "\"}";

		}

		
		return ecommTxn;
	}
	
	//////////////////// private method for Exception/////////////////
	
	/**
	 * get ParseException for  SendToSwitch
	 * @param ParseException
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getSendToSwitchParseException(
			ParseException e) throws CloudServiceException {
		String METHOD_NAME = "getSendToSwitchParseException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("ParseException occurred while  sendToSwitch for transaction process ")
				.append(BusinessConstants.COMMA)
				.append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA)
				.append(BusinessConstants.ERROR_MSG_STR)
				.append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.PARSE_EXCEPTION_DURING_SENDTOSWITCH,
				e.getMessage());
	}
	
	/**
	 * get NullPointerException for  SendToSwitch
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getSendToSwitchNullPointerException(
			NullPointerException e) throws CloudServiceException {
		String METHOD_NAME = "getSendToSwitchNullPointerException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("NullPointerException occurred while sendToSwitch for transaction process")
				.append(BusinessConstants.COMMA)
				.append(BusinessConstants.ERROR_CODE_STR)
				.append(BusinessConstants.COMMA)
				.append(BusinessConstants.ERROR_MSG_STR)
				.append(e.getMessage());
		log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.NULLPOINTER_EXCEPTION_DURING_SENDTOSWITCH,
				e.getMessage());
	}
	
	
	
	
	
	/**
	 * get NullPointerException for  SendToSwitch
	 * @param Exception
	 * @return CloudServiceException
	 * @throws CloudServiceException 
	 */
	private CloudServiceException getSendToSwitchException(
			Exception ex) throws CloudServiceException {
		String METHOD_NAME = "getSendToSwitchException";
		StringBuilder errorBuffer = new StringBuilder();
		errorBuffer
				.append("Exception occurred while sendToSwitch for transaction process ")
				.append(BusinessConstants.COMMA).append("Error Code : ")
				.append(ErrorCodeConstants.CONNECTING_WITH_SWITCH)
				.append(BusinessConstants.COMMA).append("Error Message : ")
				.append(ex.getMessage());

		log.error(CLASS_NAME, METHOD_NAME, ex, errorBuffer);
		throw new CloudServiceException(
				ErrorCodeConstants.CONNECTING_WITH_SWITCH, ex.getMessage());
	}
	

}
