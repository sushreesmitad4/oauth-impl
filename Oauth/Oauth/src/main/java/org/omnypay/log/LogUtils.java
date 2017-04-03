/*
 * This code contains copyright information which is the proprietary property

 * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 *
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------
 * Version : 1.0
 * Created on : 28 April 2014
 * Author : Aruna C
 * Description : This Class will have Log utils.
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */

package org.omnypay.log;




import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/*import org.omnypay.mobileapp.util.WebServiceConstants;
import org.omnypay.mobileapp.util.WebServiceUtil;*/

public class LogUtils {

	private static final Logger LOGGER = Logger.getLogger(LogUtils.class);

	/**
	 * This method will print the Debug logs 
	 * 
	 * @param className
	 * @param ex
	 */
	public static void printDebugLog(final String className, final String methodName,final String message) {
		LOGGER.log(Level.DEBUG,"["+className+"]:["+methodName+"]:["+message+"]");
	}

	/**
	 * This method will print the Error logs 
	 * 
	 * @param className
	 * @param ex
	 * @param inputParameters
	 */
	public static void printErrorLog(final String className,final String methodName, final Throwable ex,
			final String[] inputParameters) {

		final StringBuilder content = new StringBuilder();
		
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(StringUtils.repeat(Log4jConstants.LINESEPARATOR, 80));
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(className);
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(methodName);
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(ex.getMessage());
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(LogConstants.getStackTraceAsString(ex));
		content.append(Log4jConstants.LINESEPARATOR);
		for (String param : inputParameters) {
				content.append(param + ":");
		}
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(StringUtils.repeat(Log4jConstants.LINESEPARATOR, 80));
		content.append(Log4jConstants.LINESEPARATOR);
		LOGGER.log(Level.ERROR,content);
	}
	
	/**
	 * This method will print the Error logs 
	 * 
	 * @param className
	 * @param ex
	 * @param inputParameters
	 */
	public static void printErrorLog(final String className,final String methodName, final Throwable ex,
			final String[] inputParameters, final String uniqueId) {

		final StringBuilder content = new StringBuilder();
		
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(StringUtils.repeat(Log4jConstants.LINESEPARATOR, 80)+"\t["+Log4jConstants.LOG_ENTRY_ID+uniqueId+"]");
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(className+"\t["+Log4jConstants.LOG_ENTRY_ID+uniqueId+"]");
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(methodName+"\t["+Log4jConstants.LOG_ENTRY_ID+uniqueId+"]");
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(ex.getMessage()+"\t["+Log4jConstants.LOG_ENTRY_ID+uniqueId+"]");
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(LogConstants.getStackTraceAsString(ex)+"\t["+Log4jConstants.LOG_ENTRY_ID+uniqueId+"]");
		content.append(Log4jConstants.LINESEPARATOR);
		for (String param : inputParameters) {
				content.append(param + ":");
		}
		content.append("\t["+Log4jConstants.LOG_ENTRY_ID+uniqueId+"]");
		content.append(Log4jConstants.LINESEPARATOR);
		content.append(StringUtils.repeat(Log4jConstants.LINESEPARATOR, 80)+"\t["+Log4jConstants.LOG_ENTRY_ID+uniqueId+"]");
		content.append(Log4jConstants.LINESEPARATOR);
		LOGGER.log(Level.ERROR,content);
	}
	
	/**
	 * This method will print the Trace logs 
	 * 
	 * @param className
	 * @param ex
	 * @param inputParameters
	 */
	public static void printTraceLog(final String className, final String methodName,final String message) {
		LOGGER.log(Level.TRACE,"["+className+"]:["+methodName+"]:["+message+"]");
	}

	/**
	 * This method will print the Info logs 
	 * 
	 * @param className
	 * @param ex
	 */
	public static void printInfoLog(final String className, final String methodName,final String message) {
		LOGGER.log(Level.INFO,"["+className+"]:["+methodName+"]:"+message);
	}
	
	

}
