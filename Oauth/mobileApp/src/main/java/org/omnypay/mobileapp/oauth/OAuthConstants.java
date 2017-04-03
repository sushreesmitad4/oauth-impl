package org.omnypay.mobileapp.oauth;


public class OAuthConstants {
	
	public static final String ACCESS_TOKEN = "access_token";
	public static final String CLIENT_ID = "CLIENT_ID";
	public static final String CLIENT_SECRET = "CLIENT_SECRET";
	public static final String REFRESH_TOKEN = "refresh_token";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String AUTHENTICATION_SERVER_URL = "authentication_server_url";
	public static final String RESOURCE_SERVER_URL = "resource_server_url";
	public static final String GRANT_TYPE = "grant_type";
	public static final String SCOPE = "scope";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer";
	public static final String BASIC = "Basic";
	public static final String JSON_CONTENT = "application/json";
	public static final String XML_CONTENT = "application/xml";
	public static final String URL_ENCODED_CONTENT = "application/x-www-form-urlencoded";
	public static final String EXPIRES_IN = "expires_in";
	public static final String TOKEN_TYPE = "token_type";
	public static final int HTTP_OK = 200;
	public static final int HTTP_FORBIDDEN = 403;
	public static final int HTTP_UNAUTHORIZED = 401;
	public static final String FILTER_ESCAPE_URL = "FILTER_ESCAPE_URL";
	public static final String FILTER_LOGIN_URL = "FILTER_LOGIN_URL";
	public static final String JSON_FOR_VALID_LOGIN = "JSON_FOR_VALID_LOGIN";
	public static final String JSON_FOR_INVALID_LOGIN = "JSON_FOR_INVALID_LOGIN";
	/* below constant is used by oauth server in json response if any validation error occures */
	public static final String OAUTH_ERROR_FIELD =  "error_description";
	public static final String OAUTH_SERVER_URL =  "OAUTH_SERVER_URL";
	public static final String TOKEN_NOT_FOUND =  "TOKEN_NOT_FOUND";
	public static final String INVALID_TOKEN =  "INVALID_TOKEN";
	public static final String OAUTH_TOKEN_VALIDATION_URL="OAUTH_TOKEN_VALIDATION_URL";
	/*below param is return by OauthServer as part of token validation process */
	public static final String IS_TOKEN_VALID="token_valid";
	public static final String TOKEN_EXPIRED =  "TOKEN_EXPIRED";
	public static final String JSON_PARSE_EXCEPTION =  "JSON_PARSE_EXCEPTION";
	public static final String FILTER_LOGOUT_URL = "FILTER_LOGOUT_URL";
	public static final String SUCCESSFULLY_LOGOUT="SUCCESSFULLY_LOGOUT";
	

	
	
	
	

}
