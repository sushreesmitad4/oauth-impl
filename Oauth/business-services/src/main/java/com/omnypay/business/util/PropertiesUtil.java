/**
 * 
 */
package com.omnypay.business.util;

import java.io.IOException;
import java.util.Properties;



/**
 * @author iliyasm
 *
 */
public class PropertiesUtil {
	
	
	private static Properties projectProp;
	
	
	public static String FILE_PATH_PROJ_PROP = "/cloud_cr3.properties";
	
	
	
	 /**
	 * Load all project properties.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void loadAllProjectProperties() throws IOException
	{
	

		
		projectProp = CommonUtil.getProperties(System.getProperty(BusinessConstants.PROFILE_PATH)+FILE_PATH_PROJ_PROP);
		
	}
	
	
	
	public static Properties getProjectProperties()
	{
		//if(null == projectProp)
		//{
			try {
				loadAllProjectProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		//}
		return projectProp;
	}

}
