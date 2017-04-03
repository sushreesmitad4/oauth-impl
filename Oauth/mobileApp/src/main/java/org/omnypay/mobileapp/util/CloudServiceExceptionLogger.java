/**
 * 
 */
package org.omnypay.mobileapp.util;

import javax.ws.rs.core.Response;




import com.omnypay.business.util.CloudServiceException;
import com.omnypay.common.services.WebServiceConstants;
import com.omnypay.log.ErrorCodeConstants;
import com.omnypay.log.Log4jAdapter;
import com.omnypay.log.Log4jConstants;

/**
 * @author iliyasm
 *
 */
public class CloudServiceExceptionLogger {
	
	
	
	private static Log4jAdapter log = Log4jAdapter.getInstance();
		
	
	
	
	public static void LogCloudServiceException(Exception ex, String className,
			String methodName, String message, String errCode) {

		StringBuilder errorBuffer = new StringBuilder();

		errorBuffer.append(message).append(WebServiceConstants.COMMA);

		if (errCode != null) {

			errorBuffer.append("Error Code : ").append(errCode)
					.append(WebServiceConstants.COMMA);
			
		}
		else if(((CloudServiceException)ex).getErrorCode() != null){
			errorBuffer.append("Error Code : ").append(errCode)
			.append(WebServiceConstants.COMMA);
		}

		errorBuffer.append("Error Message : ").append(ex.getMessage());

		log.error(className, methodName, ex, errorBuffer);

	}
	

	
	public static void LogCloudServiceExecuted(String className,
			String methodName) {
		log.info(Log4jConstants.LOG_EXIT, className, methodName);

	}
	
	
	public static void LogCloudServiceExecuting( String className,
			String methodName) {

		log.info(Log4jConstants.LOG_ENTRY, className,methodName);
	}
	
	
	

}
