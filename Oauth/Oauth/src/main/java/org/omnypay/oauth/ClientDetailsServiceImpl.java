package org.omnypay.oauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.omnypay.oauth.util.CommonUtil;
import org.omnypay.oauth.util.OAuthConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.BaseClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

/** @author Susil Rayaguru
 *   
 */

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {


	public ClientDetails loadClientByClientId(String clientId)throws OAuth2Exception {
		
		BaseClientDetails clientDetails = new BaseClientDetails();
		try{
			Properties oauthProperty = CommonUtil.getProperties(System.getProperty(OAuthConstants.FILE_PROPERTY)+OAuthConstants.OAUTH_PROPERTY_FILENAME);
			String client_id_Secret = oauthProperty.getProperty(OAuthConstants.CLIENTID_AND_CLIENTSECRET);
			JSONObject client_Details = new JSONObject(client_id_Secret);	
			if(client_Details.has(clientId)){
				
				List<String> authorizedGrantTypes=new ArrayList<>();
				authorizedGrantTypes.add("password");
				authorizedGrantTypes.add("refresh_token");
				authorizedGrantTypes.add("client_credentials");

			    clientDetails = new BaseClientDetails();
				clientDetails.setClientId(clientId);
				clientDetails.setClientSecret(client_Details.getString(clientId));
				clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
								
				System.out.println("client id and secret"+clientDetails);
			}	
				else{
					throw new NoSuchClientException("No client with requested id: "
							+ clientId);
				}
			}
	
	catch(Exception e){
		
	}
		return clientDetails;
		
	}
	

}
