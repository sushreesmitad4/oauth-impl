/**
 * 
 */
package com.omnypay.merchant.kohls.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author iliyasm
 *
 */
public class CommonUtil {
	
	
	
	/**
	 * Gets the properties.
	 *
	 * @param filePath the file path
	 * @return the properties
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Properties getProperties(String filePath) throws IOException {
		//String METHOD_NAME = "getProperties";
		//logger.debug(CLASS_NAME, METHOD_NAME, APCConstants.LOG_ENTRY);
		StringBuilder sb = new StringBuilder(MerchantConstants.FILE_PATH);
		
		sb.append(filePath);
		
		Properties properties = new Properties();
		InputStream is = new FileInputStream(filePath);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			
		} finally {
			
		return properties;
	}
	}

}
