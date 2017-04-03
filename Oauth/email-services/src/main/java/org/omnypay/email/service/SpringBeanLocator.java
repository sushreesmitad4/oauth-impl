/*
 * This code contains copyright information which is the proprietary property
 * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 *
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------
 * Author : 
 * Description:This class  handles  the SpringBeanLocator .
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package org.omnypay.email.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This class handles the SpringBeanLocator .
 */
public class SpringBeanLocator implements ApplicationContextAware
{
	/** The CONTEXT. */
	private static ApplicationContext CONTEXT;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext context) throws BeansException
	{
		CONTEXT = context;
	}

	/**
	 * Gets the bean.
	 * 
	 * @param beanName
	 *            the bean name
	 * @return the bean
	 */
	public static Object getBean(String beanName)
	{
		return CONTEXT.getBean(beanName);
	}
}
