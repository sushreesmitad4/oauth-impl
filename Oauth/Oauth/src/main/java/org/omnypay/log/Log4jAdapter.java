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
 * Description : This Class will have CommonUtil methods.
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package org.omnypay.log;



import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;




public class Log4jAdapter implements Log {

	/** Log4j Logger instance. */
	private volatile Logger logger;
	private static final Logger log = Logger.getLogger(Log4jAdapter.class);
    
	private  static final String CLASS_NAME = "Log4jAdapter";
	/** Logger name. */
	private final String name;

	/** Output log messages to System.out? */
	private static final boolean ENABLE_SYSTEM_OUT;

	private static Properties configProp;

	static {
		configProp = new Properties();
		////
	/*	try {
			   Properties properties = new Properties();
		       URL inURL = Log4jAdapter.class.getResource(WebServiceConstants.COMMONS_PROPERTY_FILE_PATH); 
		       InputStream is = inURL.openStream();
			   properties.load(is);
			   is.close();
		} catch (Exception exp) {
			log.error(CLASS_NAME, exp);
		}*/

		try {
			final String className = Log4jAdapter.class.getName();
			String enable = configProp.getProperty(className
					+ ".enable.system.out");

			if (enable == null || enable.length() <= 0)
				enable = "true"; 

			ENABLE_SYSTEM_OUT = "true".equalsIgnoreCase(enable);
			
			enable = (String) configProp.getProperty(className
					+ ".enable.async");
			if (enable == null || enable.length() <= 0)
				enable = "true"; 

		} catch (RuntimeException runTimeExp) {
			log.error(CLASS_NAME, runTimeExp);
			throw runTimeExp;
		} catch (Exception exp) {
			throw new ExceptionInInitializerError(exp);
		}
		try {
			//InputStream config = Log4jAdapter.class.getResourceAsStream(Log4jConstants.LOG4J_XML_FILE);
			//InputStream config = Log4jAdapter.class.getResourceAsStream(Log4jConstants.LOG4J_XML_FILE);
			InputStream config = new FileInputStream(System.getProperty("propfilepath")+Log4jConstants.LOG4J_XML_FILE);
			Log4jAdapter ljAdaptor = new Log4jAdapter();
			ljAdaptor.configure(config);
			
		} catch (Exception exp) {
			
			log.error(CLASS_NAME, exp);
		}
	}

	public Log4jAdapter() {
		this(Log4jAdapter.class.getName());
	}

	public static Log4jAdapter getInstance() {

		return new Log4jAdapter();

	}

	/**
	 * Constructor for Log4jAdapter.
	 * 
	 * @param name
	 *            String
	 */
	public Log4jAdapter(String name) {
		if (name == null || name.length() <= 0)
			throw new IllegalArgumentException("name");

		this.name = name;
	}

	/**
	 * Returns Log4j logger instance to do actual logging. Lazily initialized to
	 * make "static" Log objects created prior to initializing with log4j.xml
	 * work properly.
	 * 
	 * @return Log4j logger
	 */
	private Logger getLogger() {
		if (logger == null) {
			synchronized (this) {
				if (logger == null) {
					logger = Logger.getLogger(name);
				}
			}
		}

		return logger;
	}

	/**
	 * Re/load log4j configuration using specified log4j configuration.
	 * 
	 * @param config
	 *            Log4j configuration stream
	 */
	public void configure(InputStream config) {
		if (config == null)
			throw new IllegalArgumentException("config");

		new DOMConfigurator().doConfigure(config, getLogger()
				.getLoggerRepository());
	}

	/**
	 * Change the global log level.
	 * 
	 * @param level
	 */
	public void changeLogLevel(Level level) {
		getLogger().getLoggerRepository().getRootLogger().setLevel(level);
	}

	/**
	 * Write log message output and stack trace to System.out formatting
	 * appropriately.
	 */
	private static void out(Logger logger, Object msg, Throwable t) {
		out(logger, msg);
		if (t != null)
			log.debug(t.getStackTrace());
	}

	/** Write log message output to System.out formatting appropriately. */
	private static void out(Logger logger, Object msg) {
		StringBuilder sb = new StringBuilder(1024);
		sb.append(getName(logger.getName())).append(" - ");
		sb.append(msg != null ? msg.toString() : "");
		
		if (msg instanceof Throwable)
			((Throwable) msg).printStackTrace();
	}

	/** Get the class name alone (remove package prefixes). */
	private static String getName(Object obj) {
		String name = null;
		if (obj instanceof Class)
			name = ((Class<?>) obj).getName();
		else if (obj instanceof String)
			name = (String) obj;
		else
			name = obj.getClass().getName();

		int pos = name.lastIndexOf('.');
		if (pos >= 0)
			name = name.substring(pos + 1);

		return name;
	}

	/**
	 * Do the logging either asynchronously or in same thread.
	 * <p>
	 * NOTE: level checks should have been done before calling this low level
	 * doLog(...) method.
	 * </p>
	 * 
	 * @param level
	 *            the log level
	 * @param msg
	 *            message to be logged
	 * @param t
	 *            the Throwable
	 * @param components
	 *            targeted Exodys component log files
	 */
	private void doLog(final Level level, final Object msg, final Throwable t) {
		logIt(level, msg, t);
	}

	/**
	 * @param logType
	 * @param msg
	 * @param t
	 */
	private void doLog(final String logType, final Object msg, final Throwable t) {

		StringBuilder inputBuffer = (StringBuilder) msg;
		if (inputBuffer != null && inputBuffer.length() > 0) {
			if (logType.equalsIgnoreCase(LogConstants.ERROR)) {
				String[] strParams = inputBuffer.toString().split(",");
				LogUtils.printErrorLog(logType, msg.toString(), t, strParams);
			}
		}
	}

	/**
	 * This method is used to print the error logs
	 * 
	 * @param logType
	 * @param className
	 * @param methodName
	 * @param t
	 * @param msg
	 */
	private void doLog(final String logType, final String className,
			final String methodName, final Throwable t, final StringBuilder msg) {

		StringBuilder inputBuilder = (StringBuilder) msg;
		if (inputBuilder != null && inputBuilder.length() > 0) {
			String[] strParams = inputBuilder.toString().split(",");
			LogUtils.printErrorLog(className, methodName, t, strParams);
		}
	}

	/**
	 * This method used to print the INFO,DEBUG,TRACE logs.
	 * 
	 * @param logType
	 * @param className
	 * @param methodName
	 */
	private void doLog(final String logType, final String className,
			final String methodName, final String message) {

		if (logType.equalsIgnoreCase(LogConstants.INFO)) {
			LogUtils.printInfoLog(className, methodName, message);
		} else if (logType.equalsIgnoreCase(LogConstants.DEBUG)) {
			LogUtils.printDebugLog(className, methodName, message);
		} else if (logType.equalsIgnoreCase(LogConstants.TRACE)) {
			LogUtils.printTraceLog(className, methodName, message);
		}
	}

	/**
	 * Do the actual logging to desired locations.
	 * 
	 * @param level
	 *            the log level
	 * @param msg
	 *            message to be logged
	 * @param t
	 *            the Throwable
	 * @param components
	 *            targeted Exodys component log files
	 */
	private void logIt(final Level level, final Object msg, final Throwable t) {
		Logger logger = getLogger();
		String METHOD_NAME = "logIt";  
		try {
			logger.log(name, level, msg, t == null ? null : t);
		} catch (Exception exp) {
			debug(CLASS_NAME, METHOD_NAME, exp.getMessage());
		}
		
		if (ENABLE_SYSTEM_OUT)
			out(logger, msg, t);
	}

	// =========== methods from org.apache.commons.logging.Log and ExodysLog
	// ===========
	/**
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object)
	 */
	public void debug(Object message) {
		if (!isDebugEnabled())
			return;

		doLog(Level.DEBUG, message, null);

	}

	/**
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void debug(Object message, Throwable t) {
		if (!isDebugEnabled())
			return;

		doLog(LogConstants.DEBUG, message, t);
	}

	/**
	 * @param className
	 * @param methodName
	 */
	public void debug(String className, String methodName, String message) {
		if (!isDebugEnabled())
			return;

		doLog(LogConstants.DEBUG, className, methodName, message);
	}

	/**
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void debug(Object[] message, Throwable t) {
		if (!isDebugEnabled())
			return;

		doLog(Level.DEBUG, message, t);
	}

	/**
	 * @see org.apache.commons.logging.Log#error(java.lang.Object)
	 */
	public void error(Object message) {
		if (!isErrorEnabled())
			return;

		doLog(Level.ERROR, message, null);
	}

	/**
	 * @see org.apache.commons.logging.Log#error(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void error(Object message, Throwable t) {
		if (!isErrorEnabled())
			return;
		
		doLog(LogConstants.ERROR, message, t);
	}

	/**
	 * @param className
	 * @param methodName
	 * @param t
	 * @param msg
	 */
	public void error(String className, String methodName, Throwable t,
			StringBuilder msg) {
	/*	if (!isErrorEnabled())
			return;*/

		doLog(LogConstants.ERROR, className, methodName, t, msg);
	}

	/**
	 * @see org.apache.commons.logging.Log#fatal(java.lang.Object)
	 */
	public void fatal(Object message) {
		if (!isFatalEnabled())
			return;

		doLog(Level.FATAL, message, null);
	}

	/**
	 * @see org.apache.commons.logging.Log#fatal(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void fatal(Object message, Throwable t) {
		if (!isFatalEnabled())
			return;

		doLog(Level.FATAL, message, t);
	}

	/**
	 * @see org.apache.commons.logging.Log#info(java.lang.Object)
	 */
	public void info(Object message) {
		if (!isInfoEnabled())
			return;

		doLog(Level.INFO, message, null);
	}

	/**
	 * @see org.apache.commons.logging.Log#info(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void info(Object message, Throwable t) {
		if (!isInfoEnabled())
			return;

		doLog(LogConstants.TRACE, message, t);
	}

	/**
	 * @param className
	 * @param methodName
	 */
	public void info(String className, String methodName, String message) {
		/*if (!isDebugEnabled())
			return;*/

		doLog(LogConstants.INFO, className, methodName, message);
	}

	/**
	 * @see org.apache.commons.logging.Log#trace(java.lang.Object)
	 */
	public void trace(Object message) {
		if (!isTraceEnabled())
			return;

		doLog(Level.TRACE, message, null);
	}

	/**
	 * @see org.apache.commons.logging.Log#trace(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void trace(Object message, Throwable t) {
		if (!isTraceEnabled())
			return;

		doLog(LogConstants.TRACE, message, t);
	}

	/**
	 * @param className
	 * @param methodName
	 */
	public void trace(String className, String methodName, String message) {
		if (!isDebugEnabled())
			return;

		doLog(LogConstants.TRACE, className, methodName, message);
	}

	/**
	 * @see org.apache.commons.logging.Log#warn(java.lang.Object)
	 */
	public void warn(Object message) {
		if (!isWarnEnabled())
			return;

		doLog(Level.WARN, message, null);
	}

	/**
	 * @see org.apache.commons.logging.Log#warn(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void warn(Object message, Throwable t) {
		if (!isWarnEnabled())
			return;

		doLog(Level.WARN, message, t);
	}

	/**
	 * @see org.apache.commons.logging.Log#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return getLogger().isEnabledFor(Level.DEBUG);
	}

	/**
	 * @see org.apache.commons.logging.Log#isErrorEnabled()
	 */
	public boolean isErrorEnabled() {
		return getLogger().isEnabledFor(Level.ERROR);
	}

	/**
	 * @see org.apache.commons.logging.Log#isFatalEnabled()
	 */
	public boolean isFatalEnabled() {
		return getLogger().isEnabledFor(Level.FATAL);
	}

	/**
	 * @see org.apache.commons.logging.Log#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		return getLogger().isEnabledFor(Level.INFO);
	}

	/**
	 * @see org.apache.commons.logging.Log#isTraceEnabled()
	 */
	public boolean isTraceEnabled() {
		return getLogger().isEnabledFor(Level.TRACE);
	}

	/**
	 * @see org.apache.commons.logging.Log#isWarnEnabled()
	 */
	public boolean isWarnEnabled() {
		return getLogger().isEnabledFor(Level.WARN);
	}
	
}
