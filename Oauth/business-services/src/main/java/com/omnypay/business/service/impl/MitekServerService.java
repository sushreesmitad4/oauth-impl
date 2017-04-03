package com.omnypay.business.service.impl;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
/*import org.apache.http.client.HttpClient;*/
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;



/*import org.omnypay.httpclient.dto.MitekServerRespDTO;
import org.omnypay.httpclient.util.ConnectorConstants;
import org.omnypay.httpclient.util.PropertiesUtil;*/
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.omnypay.business.util.BusinessConstants;
import com.omnypay.business.util.PropertiesUtil;
import com.omnypay.common.services.dto.MitekServerRespDTO;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;
import com.omnypay.log.Log4jConstants;


public class MitekServerService {
	
	
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();


	/**
	 * Sends base64String to Mitek with the given URL
	 * @param packetsend to cof  - mandatory
	 * @param url - Mitek server url
	 * @return String - represents status whether true/false
	 * @throws BusinessException in case of business validation failures
	 */
	public  String sendRequestToMitek(String base64String) {
		
		String METHOD_NAME = "sendRequestToMitek";
		
		log.info(base64String + Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
		String responseData = null;
		
		StringBuilder xmlRequestBuilder = new StringBuilder();
		
		String uri = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.MITECH_SERVER_URL);
		
		String username = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.MITECH_USERNAME);
		
		String password = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.MITECH_PASSWORD);
		
		String phoneKey = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.MITECH_PHONE_KEY);
		
		String orgName = PropertiesUtil.getProjectProperties().getProperty(
				BusinessConstants.MITECH_ORG_NAME);
		
		HttpClient httpClient = null;
		
		HttpPost httpPost = null;
		
		HttpResponse httpResponse = null;
		
		int statusCode = 0;
		
				
		xmlRequestBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><InsertPhoneTransaction xmlns=\"http://www.miteksystems.com/\">");
		xmlRequestBuilder.append("<userName>").append(username).append("</userName>")
		.append("<password>").append(password).append("</password>")
		.append("<phoneKey>").append(phoneKey).append("</phoneKey>")
		.append("<orgName>").append(orgName).append("</orgName>")
		.append("<base64Image>").append(base64String).append("</base64Image>")
		.append("<compressionLevel>").append("30").append("</compressionLevel>")
		.append("<documentIdentifier>").append("ACH").append("</documentIdentifier>")
		.append("<dataReturnLevel>").append("30").append("</dataReturnLevel>")
		.append("<returnImageType>").append("0").append("</returnImageType>")
		.append("<rotateImage>").append("0").append("</rotateImage>")
		.append("</InsertPhoneTransaction></soap:Body></soap:Envelope>");
		
		HttpEntity httpEntity;
		
		try { 
			
			httpEntity = new StringEntity(xmlRequestBuilder.toString()); 
			
			httpPost = new HttpPost(uri);
			
			httpPost.addHeader("Content-Type", "text/xml");
			
			httpPost.setEntity(httpEntity);
			
			httpClient = new DefaultHttpClient();
			
					
			httpResponse =  httpClient.execute(httpPost);
			
			statusCode = httpResponse.getStatusLine().getStatusCode();
			
			if(statusCode == 200){
				
				BufferedReader rd = new BufferedReader(new InputStreamReader(
													httpResponse.getEntity().getContent()));
				
				responseData = rd.readLine();
			}
			
			
			
		}  catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
					new StringBuilder( METHOD_NAME));
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
					new StringBuilder( METHOD_NAME));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			log.error(Log4jConstants.LOG_EXIT,CLASS_NAME, e.fillInStackTrace(),
					new StringBuilder( METHOD_NAME));
			e.printStackTrace();
		}
		
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME+"MiTech reponse :"+responseData);
		
		return responseData;
		
	}
	
	
	
	/**
	 * parsing XmlResponse getting from mitek
	 * @param xmlResponse  from mitek
	 * @return checkData - represents data after parsing xml
	 *
	 */
	public  MitekServerRespDTO parseXmlResponse(String xmlResponse){
		
		
       String METHOD_NAME = "parseXmlResponse";
		
		log.info(Log4jConstants.LOG_ENTRY,CLASS_NAME, METHOD_NAME);
		
				
		
		MitekServerRespDTO checkData = new MitekServerRespDTO();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

		
			
			
			Document 	dom = db.parse(new InputSource(new ByteArrayInputStream(xmlResponse.getBytes("utf-8"))));


						
			NodeList nList11 = dom.getElementsByTagName("Transaction");
			
			NodeList nList1 = dom.getElementsByTagName("ExtractedFields");
			
			for (int temp = 0; temp < nList1.getLength(); temp++) {
				 
				Node nNode = nList1.item(temp);
		 
				
				
				NodeList nList12 = nNode.getChildNodes();
				
				for (int temp1 = 0; temp < nList12.getLength(); temp++) {
				
					Node nNode1 = nList12.item(temp);
					
					
		 
								
						Element eElement = (Element) nNode1;
		 				
						if(eElement.getElementsByTagName("Name").item(0).getTextContent().equalsIgnoreCase("MICR account#")){
							
							
							 checkData.setAccNo(eElement.getElementsByTagName("Value").item(0).getTextContent());
								
							
						}else if(eElement.getElementsByTagName("Name").item(0).getTextContent().equalsIgnoreCase("MICR check#")){
							
							
							checkData.setCheckNo(eElement.getElementsByTagName("Value").item(0).getTextContent());
							
						}else if(eElement.getElementsByTagName("Name").item(0).getTextContent().equalsIgnoreCase("MICR routing#")){
							
							
							
							checkData.setAccBankRout(eElement.getElementsByTagName("Value").item(0).getTextContent());
						}
						
					}
			
			
			
			}
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		
		
		 log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);
		 return checkData;
	}
	
	
	
}
