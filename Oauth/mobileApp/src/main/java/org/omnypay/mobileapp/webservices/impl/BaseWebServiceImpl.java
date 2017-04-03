package org.omnypay.mobileapp.webservices.impl;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;



import org.springframework.context.ApplicationContext;

import com.omnypay.common.services.WebServiceConstants;

/**
 * 
 * @author iliyasm
 *
 */
public abstract class BaseWebServiceImpl {

	@Context
	private ServletContext servletContext;

	private ApplicationContext applicationContext ;
	
	public ApplicationContext getApplicationContext() {
	
		applicationContext = (ApplicationContext)servletContext.
										getAttribute(WebServiceConstants.BUSINESS_CONTEXT);
		
		return applicationContext;
	}
	
	
}
