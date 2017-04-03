/*
 * This code contains copyright information which is the proprietary property
 * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 *
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------
 * Author :Niranjan
 * Description:This class  handles CommonUtil . 
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package org.omnypay.email.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;



/**
 * This class handles CommonUtil .
 */
public class CommonUtil
{
	/** The Constant logger. */
	//private static final Log4jAdapter logger = Log4jAdapter.getInstance();

	/** The Constant CLASS_NAME. */
	//private static final String CLASS_NAME = "com.apc.prepaid.CommonUtil";

	/**
	 * Gets the properties.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the properties
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties getProperties(String filePath) throws IOException
	{
		String METHOD_NAME = "getProperties";
		/*logger.debug(CLASS_NAME, METHOD_NAME, APCConstants.LOG_ENTRY);
		StringBuilder sb = new StringBuilder("FilePath :");
		sb.append(filePath);*/

		/*Properties properties = new Properties();
		InputStream is = CommonUtil.class.getClassLoader().getResourceAsStream(filePath);*/
		Properties properties = new Properties();
		//URL inURL = CommonUtil.class.getResource(filePath); 
		//InputStream is = inURL.openStream();
		InputStream is = new FileInputStream(filePath);
		
		try
		{
			properties.load(is);
			is.close();
		}
		catch (IOException e)
		{
			/*StringBuilder errorBuffer = new StringBuilder("");
			errorBuffer.append("Execption occured while getting the Properties File").append(APCConstants.COMMA).append(sb);
			logger.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
			throw new IOException(ErrorCodeConstants.FILE_NOT_FOUND, e);*/
		}
		finally
		{
			if (null != is)
			{
				try
				{
					is.close();
				}
				catch (IOException e)
				{
					/*StringBuilder errorBuffer = new StringBuilder("");
					errorBuffer.append("Execption occured while getting the Properties File").append(APCConstants.COMMA).append(sb);
					logger.error(CLASS_NAME, METHOD_NAME, e, errorBuffer);
					throw new IOException(ErrorCodeConstants.FILE_NOT_FOUND, e);*/
				}
			}
		}
		//logger.debug(CLASS_NAME, METHOD_NAME, APCConstants.LOG_EXIT);
		return properties;
	}

	public static String getFileContent(String filePath) throws IOException
	{
		String METHOD_NAME = "getFileContent";
		//logger.debug(CLASS_NAME, METHOD_NAME, APCConstants.LOG_ENTRY);

		//InputStream inStrem = CommonUtil.class.getClassLoader().getResourceAsStream(filePath);
		URL inURL = CommonUtil.class.getResource(filePath);
		InputStream inStrem = inURL.openStream();

		int ch;
		StringBuffer strContent = new StringBuffer("");

		while ((ch = inStrem.read()) != -1)
			strContent.append((char) ch);

		inStrem.close();
//		/logger.debug(CLASS_NAME, METHOD_NAME, APCConstants.LOG_EXIT);
		return strContent.toString();
	}

	public static Date parseDate(Date date) throws ParseException
	{
		String format = "dd/mm/yy";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(date.toString());
	}

	public static String getCurrentDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String generateUniqueID()
	{
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	
	
	public static String genPwd() {
    	// Generate alpha numeric password of length 8

        String randomPassword = genRanPass();
        if(!(randomPassword.contains("!") || randomPassword.contains("@") || randomPassword.contains("#") || randomPassword.contains("$") || randomPassword.contains("_") || randomPassword.contains("-"))){
        	randomPassword = genRanPass();
        }else{
        	return randomPassword;
        }
        return null;
    }
	
	private static String genRanPass(){
		int length = 8;
		char[] pw = new char[length];
        int c ='A';
        int  r1 = 0;
        String randomPassword = "";
        for (int i=0; i < length; i++) {
            r1 = (int)(Math.random() * 3);
            // pick a random upper case or lower case letter or a digit
            if(i != 4){
	            switch(r1) {
	            case 0: c = '0' +  (int)(Math.random() * 10); break;
	            case 1: c = 'a' +  (int)(Math.random() * 26); break;
	            case 2: c = 'A' +  (int)(Math.random() * 26); break;
	            }
	            pw[i] = (char)c;
            }else{
            	pw[i] = (char) spclChar();
            }
            randomPassword += pw[i];
        }
		
		return randomPassword;
	}
	
	
	private static int spclChar(){
		int n = (int)(Math.random() * 6);
		int r = 0;
		switch(n){
			case 0: r = '!'; break;
	        case 1: r = '@'; break;
	        case 2: r = '#'; break;
	        case 3: r = '$'; break;
	        case 4: r = '_'; break;
	        case 5: r = '-'; break;
		}
		return r;
	}
	
}
