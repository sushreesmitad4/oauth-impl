package org.omnypay.mobileapp.oauth;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ReadListener;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;
import org.omnypay.email.service.APCConstants;
import org.omnypay.email.service.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/** @author Susil Rayaguru
 *  OAuthFiler intercepts request coming from client and redirect them to OauthServer for validation 
 */

public final class OAuthFilter implements Filter {

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();

	public OAuthFilter(){
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to do
	}


	/**
	 * It intercepts and read the data from incoming stream and reset it for validation.
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*Don't make below variable as instance , once it becomes instance variable it will be shared by set of
		 * user request and thread sync issues will come
		 */
		AccessValidator accessValidator = new OAuthValidator();
		String METHOD_NAME =  "doFilter";
		String accessURLType=null;
		UserRequest userRequest  = null;
		UserResponse userResponse=null;
		
		try{

			log.info(Log4jConstants.LOG_ENTRY, CLASS_NAME, METHOD_NAME);
			ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
					(HttpServletRequest) request);


			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String requestData = IOUtils.toString(wrappedRequest.getReader());
			JSONObject jsonRequest =  new JSONObject(requestData);


			log.info("inside filter"+requestData);
			String accessUrl = httpRequest.getRequestURI();
			log.info("Request Path URI"+httpRequest.getRequestURI());

			/* below line will reset the stream data*/
			wrappedRequest.resetInputStream();

			//accessUrl =  OAuthUtils.getUrlType(accessUrl);

			//accessURLType = accessUrl==null?"/accessingSomeResouce":accessUrl.trim();
			userRequest = new UserRequest(accessUrl, null, requestData);
		    userResponse = accessValidator.validate(userRequest,httpRequest);

			if(userResponse.FILTER_STATUS==FilterConstant.REQUEST_ALLOWED){
				chain.doFilter(wrappedRequest, response);
				System.out.println("--------inside if ------");	
			}

			else{

				generateResponse(userResponse,response);
				System.out.println("--------inside else ------"+userResponse.getContent());	

			}

		} //end of try

		catch(org.codehaus.jettison.json.JSONException json){

			StringBuilder errorBuffer =  new StringBuilder();
			errorBuffer.append(json.getMessage());
			log.error(CLASS_NAME, METHOD_NAME, json, errorBuffer);
			Properties oauthProperties = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILTER_OAUTH_PROP);
			String jsonResponse = oauthProperties.getProperty(OAuthConstants.JSON_PARSE_EXCEPTION);
			userResponse = (userResponse==null)?new UserResponse():userResponse;
			userResponse.setContentType(OAuthConstants.JSON_CONTENT);
			userResponse.setContent(jsonResponse);
			generateResponse(userResponse, response);
		}
		catch(Exception fitlerException){
			StringBuilder errorBuffer =  new StringBuilder();
			errorBuffer.append(fitlerException.getMessage());
			log.error(CLASS_NAME, METHOD_NAME, fitlerException, errorBuffer);

		}
		log.info(Log4jConstants.LOG_EXIT,CLASS_NAME, METHOD_NAME);

	}
/*
 * it generates json response
 * 
 */
	public void generateResponse(UserResponse userResponse,ServletResponse response){
		String METHOD_NAME="generateResponse";
		try {
			response.setContentType(userResponse.getContentType());
			ServletOutputStream sos = response.getOutputStream();
			sos.print(userResponse.getContent());
			sos.flush();
			sos.close();
		} catch (IOException e) {
			StringBuilder errorBuffer =  new StringBuilder();
			errorBuffer.append(e.getMessage());
			log.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
			e.printStackTrace();
		}

	}
	/*
	 * Create a wrapper around inputstream so that it can be modified or read and forwarded.
	 * 
	 */
	private static class ResettableStreamHttpServletRequest extends
	HttpServletRequestWrapper {

		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;

		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}


		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return servletStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return new BufferedReader(new InputStreamReader(servletStream));
		}


		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream stream;


			public int read() throws IOException {
				return stream.read();
			}

			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}



			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}



			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub

			}


		}
	}
	public void destroy() {
		// Nothing to do
	}




}
