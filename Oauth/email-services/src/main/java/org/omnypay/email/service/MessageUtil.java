/*
 * This code contains copyright information which is the proprietary property
 * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 *
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------
 * Author : Niranjan
 * Description:This class  handles MessageUtil.
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package org.omnypay.email.service;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;



/**
 * This class handles MessageUtil.
 */
public class MessageUtil
{
	/** The Constant MESSAGE_RESOURCE_KEY. */
	public static final String MESSAGE_RESOURCE_KEY = "messageSource";

	/**
	 * Instantiates a new message util.
	 */
	public MessageUtil()
	{}

	/**
	 * Gets the message.
	 * 
	 * @param messageKey
	 *            the message key
	 * @return the message
	 */
	public static String getMessage(String messageKey)
	{
		String message = null;
		Locale locale = LocaleContextHolder.getLocale();
		ReloadableResourceBundleMessageSource rb = (ReloadableResourceBundleMessageSource) SpringBeanLocator.getBean(MESSAGE_RESOURCE_KEY);
		if (null != rb){
			message = rb.getMessage(messageKey, null, locale);
		}
		return message;
	}
	
	public static String getMessage(String messageKey, Locale locale)
	{
		String message = null;
		//ReloadableResourceBundleMessageSource rb = (ReloadableResourceBundleMessageSource) SpringBeanLocator.getBean(MESSAGE_RESOURCE_KEY);
		
		ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
		
		if (null != rb){
			message = rb.getMessage(messageKey, null, locale);
		}
		return message;
	}
	
	public static String getDynamicMessage(String messageKey, String[] value, Locale locale)
	{
		String message = null;
		//ReloadableResourceBundleMessageSource rb = (ReloadableResourceBundleMessageSource) SpringBeanLocator.getBean(MESSAGE_RESOURCE_KEY);
		ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
		
		if (null != rb){
			message = rb.getMessage(messageKey, value, locale);
		}
		return message;
	}

}
