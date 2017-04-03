/*
 * This code contains copyright information which is the proprietary property
 * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 *
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------
 * Version : 1.0
 * Created on : 01 August 2012
 * Author : Saravanan P
 * Description : This Class will load all the Properties file.
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package org.omnypay.email.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



/**
 * This Class will load all the Properties file.
 */
public class PropertiesUtil
{
	private static Properties errorCodeProperties;
	private static Properties controllerErrorProperties;
	private static Properties serviceErrorProperties;
	private static Properties dAOErrorProperties;
	private static Properties projectProp;
	private static Properties mailProps;
	private static String userMail;
	private static String userMailPassword;
	private static String forgotPassword;
	private static String apiUserMail;
	private static ArrayList<Object> objList = null;
	Map<String, String> mapObj = new HashMap<String, String>();
	//Add card config properties 
	private static Properties cardProperties;

	/**
	 * Load all project properties.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void loadAllProjectProperties() throws IOException
	{
		String METHOD_NAME = "loadAllProjectProperties";
		//Log4jAdapter logger = Log4jAdapter.getInstance();
		String CLASS_NAME = "org.omnypay.email.service";
		//logger.debug(CLASS_NAME, METHOD_NAME, APCConstants.LOG_ENTRY);
		// for window mechine 
		errorCodeProperties = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_ERR_PROP);
		controllerErrorProperties = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_CNT_ERR_PROP);
		serviceErrorProperties = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_SERV_ERR_PROP);
		dAOErrorProperties = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_DAO_ERR_PROP);
		mailProps = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_MAIL_PROP);
		userMail = CommonUtil.getFileContent(System.getProperty("propfilepath")+APCConstants.USER_MAIL);
		userMailPassword = CommonUtil.getFileContent(System.getProperty("propfilepath")+APCConstants.USER_MAIL_PASSWORD);
		forgotPassword = CommonUtil.getFileContent(System.getProperty("propfilepath")+APCConstants.FORGOT_MAIL_PASSWORD);
		projectProp = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_PROJ_PROP);
		apiUserMail = CommonUtil.getFileContent(System.getProperty("propfilepath")+APCConstants.API_USER_MAIL);
		//quartz properties file
		cardProperties = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_CARD_CONFIG_PROP);
		//logger.debug(CLASS_NAME, METHOD_NAME, APCConstants.LOG_EXIT);
	}

	/**
	 * Gets the controller error code.
	 * 
	 * @param key the key
	 * @return the controller error code
	 */
	public static String getErrorCode(String key)
	{
		return errorCodeProperties.getProperty(key);
	}

	/**
	 * Gets the controller error code.
	 * 
	 * @param key the key
	 * @return the controller error code
	 */
	public static String getControllerErrorCode(String key)
	{
		return controllerErrorProperties.getProperty(key);
	}

	/**
	 * Gets the service error code.
	 * 
	 * @param key the key
	 * @return the service error code
	 */
	public static String getServiceErrorCode(String key)
	{
		return serviceErrorProperties.getProperty(key);
	}

	/**
	 * Gets the dAO error code.
	 * 
	 * @param key the key
	 * @return the dAO error code
	 */
	public static String getDAOErrorCode(String key)
	{
		return dAOErrorProperties.getProperty(key);
	}

	public static String getMailProperty(String key) throws IOException
	{
		if(mailProps == null){
			mailProps = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_MAIL_PROP);
		}
		
		return mailProps.getProperty(key);
	}

	/**
	 * Gets the project properties.
	 * 
	 * @return the project properties
	 */
	
/*	public static Properties getProjectProperties()
	{
		if(null == projectProp)
		{
			try {
				loadAllProjectProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return projectProp;
	}*/

	public static String getUserMailContent()
	{
		return userMail;
	}

	public static String getUserMailPasswordContent()
	{
		return userMailPassword;
	}

	public static String getForgotPasswordContent()
	{
		return forgotPassword;
	}

	/**
	 * Gets the project properties.
	 * 
	 * @return the project properties
	 * @throws IOException 
	 */
	public static Properties getProjectProperties() throws IOException
	{
		if(projectProp == null){
		 projectProp = CommonUtil.getProperties(System.getProperty("propfilepath")+APCConstants.FILE_PATH_PROJ_PROP);
		}
		
		return projectProp;
	}

	public static void add(Object obj)
	{
		objList = new ArrayList<Object>();
		objList.add(obj);
	}

	public static ArrayList<Object> get()
	{
		return objList;
	}

	/**
	 * @return the apiUserMail
	 */
	public static String getApiUserMail() {
		return apiUserMail;
	}
	
	public static Properties getCardProperties()
	{
		return cardProperties;
	}
	
}
