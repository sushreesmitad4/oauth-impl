package org.omnypay.oauth.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpClientUtil {
	public static Map handleResponse(HttpResponse response) {
		String contentType = OAuthConstants.JSON_CONTENT;
		if (response.getEntity().getContentType() != null) {
			contentType = response.getEntity().getContentType().getValue();
			System.out.println(response.getEntity().getContentType().getValue());
			
		}
		if (contentType.contains(OAuthConstants.JSON_CONTENT)) {
			return handleJsonResponse(response);
		} /*else if (contentType.contains(OAuthConstants.URL_ENCODED_CONTENT)) {
			return handleURLEncodedResponse(response);
		} else if (contentType.contains(OAuthConstants.XML_CONTENT)) {
			return handleXMLResponse(response);
		} */else {
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
		       // System.out.println(String.format("  %s = %s", keyStr, value));
		}
			
		return outMap;
	}
	
	
}
