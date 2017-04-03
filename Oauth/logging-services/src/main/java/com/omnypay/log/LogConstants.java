/*
 * This code contains copyright information which is the proprietary property


 * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------
 * Version : 1.0
 * Created on : 28 April 2014
 * Author : Aruna C
 * Description : This Class is used for logging the information.
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 */

package com.omnypay.log;

import java.io.PrintWriter;
import java.io.StringWriter;


public class LogConstants {
	
	public static final String INFO ="info";
	
	public static final String ERROR ="error";
	
	public static final String TRACE ="trace";
	
	public static final String DEBUG ="debug";
	
	
	/**
	 * Gets the stack trace as string.
	 * 
	 * @param exception
	 *            the exception
	 * 
	 * @return the stack trace as string
	 */
	public static String getStackTraceAsString(Throwable exception) {
		StringWriter sw = new StringWriter();
		if (exception != null) {

			PrintWriter pw = new PrintWriter(sw);
			pw.print(exception.getMessage());
		} else {
			sw.write("No Exception Details Available");
		}
		return sw.toString();
	}
	

}
