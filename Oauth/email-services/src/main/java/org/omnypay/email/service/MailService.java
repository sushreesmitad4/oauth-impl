/*
  * This code contains copyright information which is the proprietary property


  * of Tarang Software Technologies Pvt Ltd . No part of this code may be reproduced,
  * stored or transmitted in any form without the prior written permission.
  *
  * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
  * 
------------------------------------------------------------------------------
  * Author : Niranjan
  * Description: This Service handles the functionalities for Mails   
  * 
------------------------------------------------------------------------------
  * Change History
  * 
------------------------------------------------------------------------------
  *
  * 
------------------------------------------------------------------------------
  */
package org.omnypay.email.service;





import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;







/**
 * The Class MailService.
 */
//@Service("mailService")
public class MailService
{
	/** The logger. */
	//private final static Log4jAdapter logger = Log4jAdapter.getInstance();

	/** The CLAS s_ name. */
	private final String CLASS_NAME = this.getClass().getName();

	/** The link. */
	private static String link = null;

	/** The display. */
	private static String display = null;

	/** The mail sender. */
	@Autowired
	private JavaMailSenderImpl mailSender;

	/**
	 * Send mail.
	 *
	 * @param from the from
	 * @param to the to
	 * @param subject the subject
	 * @param pass the pass
	 * @param userName the user name
	 */
	public void sendMail(String from, String to, String subject, String pass,String userName)
	{
		String METHOD_NAME = "sendMail";
		//logger.debug(CLASS_NAME, METHOD_NAME, "Entering into sendMail");

		mailSender.setUsername(MessageUtil.getMessage("mail.username"));
		mailSender.setPassword(MessageUtil.getMessage("mail.password"));

		try
		{
			String scheme = MessageUtil.getMessage("label.scheme");
			String ipAddress = MessageUtil.getMessage("label.ip");
			String port = MessageUtil.getMessage("label.port");
			String context = MessageUtil.getMessage("label.context");
			String appPath = MessageUtil.getMessage("lable.appPath");
			display = MessageUtil.getMessage("label.display");
			link = scheme + ipAddress + port + context + appPath;
			MimeMessage message = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setTo(to);

			helper.setText(buildBody(link, display, pass,userName), true);
			mailSender.send(message);
		}
		catch (Exception e)
		{
			//logger.debug(CLASS_NAME, METHOD_NAME, e.getMessage());
		}
	}
	
	/**
	 * Send mail.
	 *
	 * @param from the from
	 * @param to the to
	 * @param subject the subject
	 * @param pass the pass
	 */
	public void sendMail(String from, String to, String subject, String pass)
	{
		String METHOD_NAME = "sendMail";
		//logger.debug(CLASS_NAME, METHOD_NAME, "Entering into sendMail");

		mailSender.setUsername(MessageUtil.getMessage("mail.username"));
		mailSender.setPassword(MessageUtil.getMessage("mail.password"));
		try
		{
			String scheme = MessageUtil.getMessage("label.scheme");
			String ipAddress = MessageUtil.getMessage("label.ip");
			String port = MessageUtil.getMessage("label.port");
			String context = MessageUtil.getMessage("label.context");
			String appPath = MessageUtil.getMessage("lable.appPath");
			display = MessageUtil.getMessage("label.display");
			link = scheme + ipAddress + port + context + appPath;
			MimeMessage message = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setTo(to);

			helper.setText(buildBody(link, display, pass), true);
			mailSender.send(message);
		}
		catch (Exception e)
		{
			//logger.debug(CLASS_NAME, METHOD_NAME, e.getMessage());
		}
	}
	
	/**
	 * Send alert mail.
	 *
	 * @param from the from
	 * @param to the to
	 * @param subject the subject
	 * @param content the content
	 */
	public void sendAlertMail(String from, String to, String subject, String content)
	{
		String METHOD_NAME = "sendMail";
		//logger.debug(CLASS_NAME, METHOD_NAME, "Entering into sendMail");

		mailSender.setUsername(MessageUtil.getMessage("mail.username"));
		mailSender.setPassword(MessageUtil.getMessage("mail.password"));

		try
		{
			String scheme = MessageUtil.getMessage("label.scheme");
			String ipAddress = MessageUtil.getMessage("label.ip");
			String port = MessageUtil.getMessage("label.port");
			String context = MessageUtil.getMessage("label.context");
			String appPath = MessageUtil.getMessage("lable.appPath");
			display = MessageUtil.getMessage("label.display");
			link = scheme + ipAddress + port + context + appPath;
			MimeMessage message = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setTo(to);

			helper.setText(buildAlertBody(display, content), true);
			mailSender.send(message);
		}
		catch (Exception e)
		{
			//logger.debug(CLASS_NAME, METHOD_NAME, e.getMessage());
		}
	}

	/**
	 * Builds the body.
	 *
	 * @param link the link
	 * @param display the display
	 * @param pass the pass
	 * @param userName the user name
	 * @return the string
	 */
	private String buildBody(String link, String display, String pass,String userName)
	{
		String METHOD_NAME = "buildBody";
		//logger.debug(CLASS_NAME, METHOD_NAME, "Entering into buildBody");

		StringBuffer bodyData = new StringBuffer();

		bodyData.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + "\r\n");
		bodyData.append("<html>" + "\r\n");
		bodyData.append("<head>" + "\r\n");
		bodyData.append("<title>Password Recovery</title>" + "\r\n");
		bodyData.append("</head>" + "\r\n");
		bodyData.append("<body>" + "\r\n");
		bodyData.append("<p>" + "\r\n");
		bodyData.append("Hello from APC.com" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Please use this temporary password to log in tAPCTM" + " " + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Your User Name is :" + userName + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Your tempory password is :" + pass + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Click on the following link to login with your new password");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("</p>" + "\r\n");
		bodyData.append("<a href=\"").append(link).append("\"").append(">");
		bodyData.append(display);
		bodyData.append("</a>");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("</body>" + "\r\n");
		bodyData.append("</html>" + "\r\n");
		bodyData.append("\r\n");

		return bodyData.toString();
	}
	
	
	/**
	 * Builds the body.
	 *
	 * @param link the link
	 * @param display the display
	 * @param pass the pass
	 * @return the string
	 */
	public String buildBody(String link, String display, String pass)
	{
		String METHOD_NAME = "buildBody";
		//logger.debug(CLASS_NAME, METHOD_NAME, "Entering into buildBody");

		StringBuffer bodyData = new StringBuffer();

		bodyData.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + "\r\n");
		bodyData.append("<html>" + "\r\n");
		bodyData.append("<head>" + "\r\n");
		bodyData.append("<title>Password Recovery</title>" + "\r\n");
		bodyData.append("</head>" + "\r\n");
		bodyData.append("<body>" + "\r\n");
		bodyData.append("<p>" + "\r\n");
		bodyData.append("Hello from APC.com" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Please use this temporary password to log in to APC" + " " + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Your tempory password is :" + pass + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Click on the following link to login with your new password");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("</p>" + "\r\n");
		bodyData.append("<a href=\"").append(link).append("\"").append(">");
		bodyData.append(display);
		bodyData.append("</a>");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("</body>" + "\r\n");
		bodyData.append("</html>" + "\r\n");
		bodyData.append("\r\n");

		return bodyData.toString();

	}
	
	
	/**
	 * Builds the alert body.
	 *
	 * @param display the display
	 * @param content the content
	 * @return the string
	 */
	private String buildAlertBody(String display, String content)
	{
		String METHOD_NAME = "buildAlertBody";
		//logger.debug(CLASS_NAME, METHOD_NAME, "Entering into buildBody");

		StringBuffer bodyData = new StringBuffer();

		bodyData.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + "\r\n");
		bodyData.append("<html>" + "\r\n");
		bodyData.append("<head>" + "\r\n");
		bodyData.append("<title>Alert Mail</title>" + "\r\n");
		bodyData.append("</head>" + "\r\n");
		bodyData.append("<body>" + "\r\n");
		bodyData.append("<p>" + "\r\n");
		bodyData.append("Hello from APC.com" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("You are getting this email because of this reason" + " " + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("Alert mail has been sent because :" + content + "\r\n");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("<br>" + "\r\n");
		//bodyData.append("Click on the following link to login with your new password");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("</p>" + "\r\n");
		//bodyData.append("<a href=\"").append(link).append("\"").append(">");
		//bodyData.append(display);
		//bodyData.append("</a>");
		bodyData.append("<br>" + "\r\n");
		bodyData.append("</body>" + "\r\n");
		bodyData.append("</html>" + "\r\n");
		bodyData.append("\r\n");

		return bodyData.toString();
	}
}
