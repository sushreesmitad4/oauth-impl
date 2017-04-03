package org.omnypay.mobileapp.oauth;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.omnypay.email.service.APCConstants;
import org.omnypay.email.service.CommonUtil;
import org.springframework.stereotype.Component;

import com.omnypay.common.services.dto.LoginDTO;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;


/**
 * @author Susil Rayaguru
 *
 * 
 */
@Component
public class OAuthValidator implements AccessValidator {
UserResponse userResponse = null;
private static Log4jAdapter log = Log4jAdapter.getInstance();
private final String CLASS_NAME = this.getClass().getName();

public OAuthValidator(){

	
}

	public UserResponse validate(UserRequest userRequest,HttpServletRequest req) throws Exception {
		userResponse = new UserResponse();
		String access_token =  null;
		userResponse.setContentType(OAuthConstants.JSON_CONTENT);
		String METHOD_NAME="validate";
		log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
		

		
			/* fetches oauth token if present */
			Enumeration<String> headers = req.getHeaders(OAuthConstants.ACCESS_TOKEN);
			while (headers.hasMoreElements()) {
				access_token = headers.nextElement();
				log.info("access_token value is:" + access_token);
				
			}

		

		
		/* fetching the OAuthServer URL from resource bundle */
		Properties oauthProperties = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILTER_OAUTH_PROP);
		String oauthServerUrl =  oauthProperties.getProperty(OAuthConstants.OAUTH_SERVER_URL);
		log.info(oauthProperties.getProperty(OAuthConstants.JSON_FOR_INVALID_LOGIN));
		log.info(oauthProperties.getProperty(OAuthConstants.JSON_FOR_VALID_LOGIN));
		
		String accessURLType =  OAuthUtils.getUrlType(userRequest.getUrl());
		
		
		/*below tests for escape url */
		if(accessURLType!=null && accessURLType.equalsIgnoreCase(OAuthConstants.FILTER_ESCAPE_URL)){
			log.info("user accessing some Escape resource ");
			userResponse.setFILTER_STATUS(FilterConstant.REQUEST_ALLOWED);
			
     	}
		
     	/* here we need to validate the user for login */
     	else if (accessURLType!=null && accessURLType.equalsIgnoreCase(OAuthConstants.FILTER_LOGIN_URL)){
     		log.info("user accessing login resource ");
     		
     		/*get the clientId and clientSecret from property file */
     		String clientId =oauthProperties.getProperty(OAuthConstants.CLIENT_ID);
     		String clientSecret = oauthProperties.getProperty(OAuthConstants.CLIENT_SECRET);
     		log.info("clientid and clientSecret"+clientId+":"+clientSecret);
     		
     		
     		/* get data from user, convert it to OAuth Compatiable and send to oauth server */
     		
     		ObjectMapper mapper = new ObjectMapper();
    		LoginDTO loginDTO =  mapper.readValue(userRequest.getContent(), LoginDTO.class);
		    		
		    		if (loginValidate(loginDTO)) {
		    			
		    			System.out.println("validation is ok");
		    			
		    			/* send data to Oauth server and get the responses */
		    			
		    			/*here userid contians a json with userid, merchant key, imeino as JSON and treated as one paramter,
		    			 * and password as another parameter.
		    			 * 
		    			 */
		    			
		    			JSONObject userData = new JSONObject(userRequest.getContent());
		    			String  password= (String)userData.get("passcode");
		    			userData.remove("passcode");
		    			log.info("userid"+userData.toString());
		    			String userid = userData.toString();
		    			//Map response =  OAuthUtils.getAuthenticated(oauthServerUrl, userid, password,clientId,clientSecret);
		    			OAuth2Config oauthConfig  =  new OAuth2Client(userid,password,clientId,clientSecret,oauthServerUrl).getOauth2Config();
		    			
		    			 Map response =  OAuthUtils.isAuthenticUser(oauthConfig);
		    			log.info("data of map"+response.toString());
		    			log.info("access_token value"+response.get(OAuthConstants.ACCESS_TOKEN));
		    			
		    			//------------Response Generation for Client -------------
		    			
		    			/*Generates error response for invalid user credential
		    			 * 
		    			 */
		    				    				    			
		    			if(response.get(OAuthConstants.OAUTH_ERROR_FIELD)!=null || response.get(OAuthConstants.OAUTH_ERROR_FIELD.toUpperCase())!=null){
		    				userResponse.setContent(oauthProperties.getProperty(OAuthConstants.JSON_FOR_INVALID_LOGIN));
		    				log.info(oauthProperties.getProperty(OAuthConstants.JSON_FOR_INVALID_LOGIN));
		    			}
		    		    
		    			
		    			/* Generation valid response for  client response and keep the token in ConcurrentMap*/
		    			else if(response.get(OAuthConstants.ACCESS_TOKEN)!=null || response.get(OAuthConstants.ACCESS_TOKEN.toUpperCase())!=null)
		    			{
		    				/*fetching valid response from property file */
		    				
		    				String jsonData = oauthProperties.getProperty(OAuthConstants.JSON_FOR_VALID_LOGIN);
		    				JSONObject jsonUserData = new JSONObject(jsonData);
		    				/*adding accessToken to json data*/
		    				log.info("the response token....."+response.get(OAuthConstants.ACCESS_TOKEN));
		    				jsonUserData.put(OAuthConstants.ACCESS_TOKEN,response.get(OAuthConstants.ACCESS_TOKEN));
		    				userResponse.setContent(jsonUserData.toString());
		    				
		    				//put the user details in LoginMap
		    				LoginMap.addUser((String)response.get(OAuthConstants.ACCESS_TOKEN), loginDTO);
		    				log.info("After adding the user the size"+LoginMap.userMap.size());
		    			}
		    			
		    			
		    			
		    		}
		    		
		    		else{ //this is for loginValidate Fails, some data are missing
		    			 log.info("invald data");//generate invalid response.
		    			 //Generates a response object and set JSON data as inavlid credential
		    			
		    			 userResponse.setContent(oauthProperties.getProperty(OAuthConstants.JSON_FOR_INVALID_LOGIN));
		    			
		    		}
		     				    		

     
     	}
		
		/* below condition check for log out, and remove token from Map and OauthServer too */
     	else if(accessURLType!=null && accessURLType.equalsIgnoreCase(OAuthConstants.FILTER_LOGOUT_URL)){
     		log.info("user logging out....");
     		FilterConstant error_Message = null;
     		
     		/* validate against a access_token */
     		if(access_token==null){
	     		error_Message=FilterConstant.ACCESS_TOKEN_NOT_FOUND;
	     		userResponse.setContentType(OAuthConstants.JSON_CONTENT);
	     		userResponse.setContent(oauthProperties.getProperty(OAuthConstants.TOKEN_NOT_FOUND));
     		}
     		/* if token is present in url map then check for the same in LoginMap and then remove it */
     		else{
     			
     			/* check for a token presence in LoginMAp*/
     			if(LoginMap.getUser(access_token)==null){
     				error_Message = FilterConstant.INVALID_ACCESS_TOKEN;
     				userResponse.setContentType(OAuthConstants.JSON_CONTENT);
    	     		userResponse.setContent(oauthProperties.getProperty(OAuthConstants.INVALID_TOKEN));
     			}
     			/* remove the token from LoginMap */
     			else{
     				LoginMap.removeUser(access_token);
     				userResponse.setContentType(OAuthConstants.JSON_CONTENT);
    	     		userResponse.setContent(oauthProperties.getProperty(OAuthConstants.SUCCESSFULLY_LOGOUT));
    	     		//talk to OAuthServer and remove the token from that too....
     			}
     			
     			
     			
     		}
     		
     		log.info("State of loginMap"+LoginMap.userMap);
     		
     		
     		
     	}
     	
     	/* here we except the user to have the token before accessing the  url  and token as to be validated
     	 * with the  OAuthserver.
     	 * */

     	else{
     		log.info("user accessing some resource for which token is required. all tkens"+LoginMap.userMap);
     		
     		FilterConstant error_Message = null;
     		

     		
	     		/* validate against a access_token */
	     		if(access_token==null){
	     		error_Message=FilterConstant.ACCESS_TOKEN_NOT_FOUND;
	     		userResponse.setContentType(OAuthConstants.JSON_CONTENT);
	     		userResponse.setContent(oauthProperties.getProperty(OAuthConstants.TOKEN_NOT_FOUND));
	     		}
	     		/* if token is present then check in login map first and than talk to oauth server for token expiration */
	     		else{
	     			
	     			//invalid token if not present in LoginMap
	     			System.out.println("value n length of access token"+access_token);
	     			if(LoginMap.getUser(access_token)==null){
	     				error_Message = FilterConstant.INVALID_ACCESS_TOKEN;
	     				userResponse.setContentType(OAuthConstants.JSON_CONTENT);
	    	     		userResponse.setContent(oauthProperties.getProperty(OAuthConstants.INVALID_TOKEN));
	     			}
	     			// talk to OauthServer for token expiration if expired then ask the user to login again.
	     			
	     			else{
	     				
	     				String tokenValidationURL =  (String)(oauthProperties.get(OAuthConstants.OAUTH_SERVER_URL))+oauthProperties.get(OAuthConstants.OAUTH_TOKEN_VALIDATION_URL);
	     				Map oauthRespose = OAuthUtils.validateToken(tokenValidationURL,access_token);
	     				log.debug(oauthRespose);
	     				if(oauthRespose.get(OAuthConstants.IS_TOKEN_VALID)==null){
	     					error_Message = FilterConstant.TOKEN_EXPIRED;	
	     					userResponse.setContentType(OAuthConstants.JSON_CONTENT);
		    	     		userResponse.setContent(oauthProperties.getProperty(OAuthConstants.TOKEN_EXPIRED));
	     				}
	     				
	     				
	     			}
	     			
	     		}
	     		
	     		//allow the user to access the requested URL
	     		if(error_Message==null){
	     			userResponse.FILTER_STATUS=FilterConstant.REQUEST_ALLOWED;
	     			
	     		}

     		
     	}
		log.info(Log4jConstants.LOG_EXIT, CLASS_NAME, METHOD_NAME);
		return userResponse;
	}
	public static boolean loginValidate(LoginDTO loginDto){
		boolean isValid = true;
		
		if(loginDto.getEmailId()==null || loginDto.getEmailId().trim().length()==0)
		return isValid=false;
		
		else if(loginDto.getPhoneNumber()==null || loginDto.getPhoneNumber().trim().length()==0){
			return isValid=false;
		}
		
		else if(loginDto.getMerchantAccessKey()==null || loginDto.getMerchantAccessKey().trim().length()==0){
			return isValid=false;
		}
		else if(loginDto.getPasscode()==null ||  loginDto.getPasscode().trim().length()==0){
			return isValid =  false;
		}
		else if (loginDto.getImeiNo()==null || loginDto.getImeiNo().trim().length()==0){
			return isValid =  false;
		}
		return isValid;
		
	}
	
	
	
	
		  
		  
		  
		  
		  
		  
		  
		  
		  

	 

	
	
}
