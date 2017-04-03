package org.omnypay.mobileapp.oauth;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.omnypay.email.service.APCConstants;
import org.omnypay.email.service.CommonUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/** @author Susil Rayaguru
 *   
 */

import org.apache.commons.codec.binary.Base64;

import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

public class OAuthUtils {
	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private  static final String CLASS_NAME = "OAuthUtils";
	
	public static Map getAccessToken(OAuth2Config oauthDetails) {
		HttpPost post = new HttpPost(oauthDetails.getTokenEndPointUrl());
		String clientId = oauthDetails.getClientId();
		String clientSecret = oauthDetails.getClientSecret();
		String scope = oauthDetails.getScope();
		Map<String, Object> map = null;
		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
		parametersBody.add(new BasicNameValuePair(OAuthConstants.GRANT_TYPE,
				oauthDetails.getGrantType()));
		parametersBody.add(new BasicNameValuePair(OAuthConstants.USERNAME,
				oauthDetails.getUsername()));
		parametersBody.add(new BasicNameValuePair(OAuthConstants.PASSWORD,
				oauthDetails.getPassword()));

		if (isValid(clientId)) {
			parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID,
					clientId));
		}
		if (isValid(clientSecret)) {
			parametersBody.add(new BasicNameValuePair(
					OAuthConstants.CLIENT_SECRET, clientSecret));
		}
		if (isValid(scope)) {
			parametersBody.add(new BasicNameValuePair(OAuthConstants.SCOPE,
					scope));
		}

		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
	
		try {
			post.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));

			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (code >= 400) {
				 map =handleResponse(response);
				 System.out.println("response....."+map);
				
				// Add Basic Authorization header
				
				
				try {
					response.getEntity().consumeContent();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

				response = client.execute(post);
				code = response.getStatusLine().getStatusCode();
				

			}
			else{
			 map = handleResponse(response);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}


	
	public static Map handleResponse(HttpResponse response) {
		String contentType = OAuthConstants.JSON_CONTENT;
		if (response.getEntity().getContentType() != null) {
			contentType = response.getEntity().getContentType().getValue();
			System.out.println(response.getEntity().getContentType().getValue());
			
		}
		if (contentType.contains(OAuthConstants.JSON_CONTENT)) {
			return handleJsonResponse(response);
		} else if (contentType.contains(OAuthConstants.URL_ENCODED_CONTENT)) {
			return handleURLEncodedResponse(response);
		} else if (contentType.contains(OAuthConstants.XML_CONTENT)) {
			return handleXMLResponse(response);
		} else {
			// Unsupported Content type
			throw new RuntimeException(
					"Cannot handle "
							+ contentType
							+ " content type. Supported content types include JSON, XML and URLEncoded");
		}

	}

	public static Map handleJsonResponse(HttpResponse response) {
		JSONObject oauthLoginResponse = null;
		String contentType = response.getEntity().getContentType().getValue();
		try {
			oauthLoginResponse = new JSONObject(EntityUtils.toString(response.getEntity()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//System.out.println();
		//System.out.println("********** JSON Response Received **********");
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		Iterator<String> keysIterator = oauthLoginResponse.keys();
		while (keysIterator.hasNext()) 
		{
		        String keyStr = (String)keysIterator.next();
		        Object value = null;
				try {
					value = oauthLoginResponse.get(keyStr);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outMap.put(keyStr,value);
		        //System.out.println(String.format("  %s = %s", keyStr, value));
		}
			
		return outMap;
	}

	public static Map handleURLEncodedResponse(HttpResponse response) {
		Map<String, Charset> map = Charset.availableCharsets();
		Map<String, String> oauthResponse = new HashMap<String, String>();
		Set<Map.Entry<String, Charset>> set = map.entrySet();
		Charset charset = null;
		HttpEntity entity = response.getEntity();

		System.out.println();
		System.out.println("********** URL Encoded Response Received **********");

		for (Map.Entry<String, Charset> entry : set) {
			System.out.println(String.format("  %s = %s", entry.getKey(),
					entry.getValue()));
			if (entry.getKey().equalsIgnoreCase(HTTP.UTF_8)) {
				charset = entry.getValue();
			}
		}

		try {
			List<NameValuePair> list = URLEncodedUtils.parse(entity);
			for (NameValuePair pair : list) {
				System.out.println(String.format("  %s = %s", pair.getName(),
						pair.getValue()));
				oauthResponse.put(pair.getName(), pair.getValue());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not parse URLEncoded Response");
		}

		return oauthResponse;
	}

	public static Map handleXMLResponse(HttpResponse response) {
		Map<String, String> oauthResponse = new HashMap<String, String>();
		try {

			String xmlString = EntityUtils.toString(response.getEntity());
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(inStream);

			System.out.println("********** XML Response Received **********");
			parseXMLDoc(null, doc, oauthResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Exception occurred while parsing XML response");
		}
		return oauthResponse;
	}

	public static void parseXMLDoc(Element element, Document doc,
			Map<String, String> oauthResponse) {
		NodeList child = null;
		if (element == null) {
			child = doc.getChildNodes();

		} else {
			child = element.getChildNodes();
		}
		for (int j = 0; j < child.getLength(); j++) {
			if (child.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child
						.item(j);
				if (childElement.hasChildNodes()) {
					System.out.println(childElement.getTagName() + " : "
							+ childElement.getTextContent());
					oauthResponse.put(childElement.getTagName(),
							childElement.getTextContent());
					parseXMLDoc(childElement, null, oauthResponse);
				}

			}
		}
	}



	
	public static String encodeCredentials(String username, String password) {
		String cred = username + ":" + password;
		String encodedValue = null;
		byte[] encodedBytes = Base64.encodeBase64(cred.getBytes());
		encodedValue = new String(encodedBytes);
		System.out.println("encodedBytes " + new String(encodedBytes));

		byte[] decodedBytes =  Base64.decodeBase64(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));

		return encodedValue;

	}

	public static boolean isValid(String str) {
		return (str != null && str.trim().length() > 0);
	}
	
	public static String getUrlType(String accessUrl) throws IOException
		
		{
			String urlAccessType =  null;
					
				Properties p = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILTER_OAUTH_PROP);
				Set s = p.keySet();
				Iterator keys =  s.iterator();
				
				while(keys.hasNext()){
				String key = (String) keys.next();
				String value = (String) p.get(key);
				String splitValues []=  value.split(",");
					for(String splitValue:splitValues){
						if(splitValue.equalsIgnoreCase(accessUrl)){
							
							urlAccessType = key;
							break;
						}
									
					 }	
				}	
								
				
			return urlAccessType;
		
		}
		
	
	 
	 public static Map isAuthenticUser(OAuth2Config oauthConfig) throws Exception{
	        // Create a new HttpClient and Post Header
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(oauthConfig.getTokenEndPointUrl());
	        Map map = null;
	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

	            nameValuePairs.add(new BasicNameValuePair("username", oauthConfig.getUsername()));
	            nameValuePairs.add(new BasicNameValuePair("password", oauthConfig.getPassword()));
	            nameValuePairs.add(new BasicNameValuePair("client_id", oauthConfig.getClientId()));
	            nameValuePairs.add(new BasicNameValuePair("client_secret", oauthConfig.getClientSecret()));
	            nameValuePairs.add(new BasicNameValuePair("grant_type",oauthConfig.getGrantType() ));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            System.out.println("OAuthConfiguration sending to server..."+oauthConfig);
	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
	            System.out.println("here is the status code "+ response.getStatusLine().getStatusCode());
	            System.out.println("content type"+response.getEntity().getContentType());
	             map = handleJsonResponse(response);
	            System.out.println("response value");
	            /* release the connection */
	            if( response.getEntity() != null ) {
	                response.getEntity().consumeContent();
	             }
	            
	        } catch (ClientProtocolException e) {
	           throw e;
	        } catch (IOException e) {
	           throw e;
	        }
	        return map;
	    } 
	 
	 public static Map validateToken(String tokenValidationURL,String access_token)throws Exception {
		 
		 
		 
		  HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(tokenValidationURL);
	        log.debug("accessing the oauth server with url"+tokenValidationURL);
	        Map map = null;
	        try {
	           
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	            nameValuePairs.add(new BasicNameValuePair(OAuthConstants.ACCESS_TOKEN, access_token));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
	            System.out.println("here is the status code "+ response.getStatusLine().getStatusCode());
	            System.out.println("content type"+response.getEntity().getContentType());
	             map = handleJsonResponse(response);
	           
	            /* release the connection */
	            if( response.getEntity() != null ) {
	                response.getEntity().consumeContent();
	             }
	            
	        } catch (ClientProtocolException e) {
	           throw e;
	        } catch (IOException e) {
	           throw e;
	        }
	        return map;
	 }
	 
	 public static Map jsonToMapConverter(org.codehaus.jettison.json.JSONObject jdata){
		 Map<String, Object> outMap = new HashMap<String, Object>();
			try{
			
			Iterator<String> keysIterator = jdata.keys();
			while (keysIterator.hasNext()) 
			{
			        String keyStr = (String)keysIterator.next();
			        Object value = null;
					try {
						value = jdata.get(keyStr);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outMap.put(keyStr,value);
			        
			}
			
		}
		 catch(Exception e){
			 log.error(CLASS_NAME,"jsonToMapConverter",e,null);
			 
		 }
			return outMap;
}



	 
}
