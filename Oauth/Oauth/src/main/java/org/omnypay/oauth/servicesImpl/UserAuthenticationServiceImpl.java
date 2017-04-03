package org.omnypay.oauth.servicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.omnypay.oauth.services.UserAuthenticationService;
import org.omnypay.oauth.util.CommonUtil;
import org.omnypay.oauth.util.HttpClientUtil;
import org.omnypay.oauth.util.OAuthConstants;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	public UserAuthenticationServiceImpl(){
		System.out.println(" instance of user authenticate service...");
	}
	/*
	 * @see org.omnypay.oauth.services.UserAuthenticationService#authenticateUser(java.lang.String, java.lang.String)
	 * 
	 * 	json.put("imeiNo", "1234567890");    
		json.put("phoneNumber", "12345678901");
		json.put("emailId", "test@tarangtech.com");
		json.put("passcode", "Tarang01");
		json.put("merchantAccessKey", "pqibrjctgept9ixf0ctrsrsblk0xzbap");
	 * 
	 * 
	 */
	public boolean authenticateUser(String userid, String password) {
		System.out.println("test..");
		System.out.println("userid"+userid);
		System.out.println("password"+password);
		boolean isAuthenticUser = false;
		JSONObject json = new JSONObject(userid);
		json.put("passcode",password);
		System.out.println(json);
		DefaultHttpClient client  = new DefaultHttpClient();        
	    	try {
	    	Properties oauthProperty = CommonUtil.getProperties(System.getProperty(OAuthConstants.FILE_PROPERTY)+OAuthConstants.OAUTH_PROPERTY_FILENAME);
	    	String validationURL =  oauthProperty.getProperty(OAuthConstants.USER_VALIDATION_URL);
	    	String validationParameter =  oauthProperty.getProperty(OAuthConstants.VALIDATION_PARAMETER);
	    	String validtionValues = oauthProperty.getProperty(OAuthConstants.VALIDATION_VALUE);
	    	//below list keeps the comma sepearated values present in property file 
	    	List <String> value =  Arrays.asList(validtionValues.split(","));
	    	System.out.println("list value"+value);
	      	System.out.println("validation url"+validationURL+":"+validationParameter+":"+validtionValues);
	   		HttpPost request = new HttpPost(validationURL);    
		    StringEntity params = new StringEntity(json.toString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response= client.execute(request);
		    Map<String, Object> map = HttpClientUtil.handleResponse(response);
		    System.out.println(map);
		    System.out.println("Valdiation Result:"+value.contains(map.get(validationParameter)));
		    if(value.contains(map.get(validationParameter)) ){
		    	isAuthenticUser=true;
		    }
	    
	    	
		} catch (Exception ex) {
		    ex.printStackTrace();
		} finally {
		    client.getConnectionManager().shutdown(); 
		}
	
		return isAuthenticUser;
	}

}
