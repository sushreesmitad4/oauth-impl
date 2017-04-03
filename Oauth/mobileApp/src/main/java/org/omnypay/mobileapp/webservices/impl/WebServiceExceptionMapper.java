package org.omnypay.mobileapp.webservices.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.ws.WebServiceException;

import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.common.services.WebServiceUtil;
import com.omnypay.common.services.dto.Status;
import com.omnypay.log.Log4jAdapter;






/**
 * 
 * @author Kirank
 *
 */
@Provider
public class WebServiceExceptionMapper implements 
					ExceptionMapper<WebServiceException>{

	private static Log4jAdapter log = Log4jAdapter.getInstance();
	private final String CLASS_NAME = this.getClass().getName();
	/**
	 * Sends custom response to a client in case of exception occurred. 
	 */
	public Response toResponse(WebServiceException exception) {
		
		Status status = WebServiceUtil.getStatus(exception.getMessage(), 
													WebServiceConstants.TWO);
		return Response.status(javax.ws.rs.core.Response.Status.ACCEPTED).
				entity(status).build();
	}
	
}
