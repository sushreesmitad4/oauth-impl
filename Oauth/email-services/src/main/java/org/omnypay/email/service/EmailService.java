package org.omnypay.email.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;






@Service("emailService")
public class EmailService
{
	//private final static Log4jAdapter logger = Log4jAdapter.getInstance();

	/** The CLAS s_ name. */
	private static String CLASS_NAME = EmailService.class.getName();

	public void postMail(String recipients[], String subject, String message)
	{
		// final String METHOD_NAME = "postMail";
		//logger.debug(CLASS_NAME);
		try
		{
			boolean debug = false;
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", PropertiesUtil.getMailProperty(APCConstants.SMTP_HOST_NAME));
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getDefaultInstance(props, auth);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(PropertiesUtil.getMailProperty(APCConstants.EMAIL_FROM_ADDRESS));
			msg.setFrom(addressFrom);
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++){
				if (null != recipients[i]){
					addressTo[i] = new InternetAddress(recipients[i]);
				}
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		}
		catch (Exception e)
		{
			//logger.error(e);
		}
	}
	
	public void postMail(String recipients[], String subject, String message, String language)
	{
		// final String METHOD_NAME = "postMail";
		//logger.debug(CLASS_NAME);
		try
		{
			boolean debug = false;
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", PropertiesUtil.getMailProperty(APCConstants.SMTP_HOST_NAME));
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getDefaultInstance(props, auth);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(PropertiesUtil.getMailProperty(APCConstants.EMAIL_FROM_ADDRESS));
			msg.setFrom(addressFrom);
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++){
				if (null != recipients[i]){
					addressTo[i] = new InternetAddress(recipients[i]);
				}
			}
			
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(message, "text/html; charset=UTF-8");
			multipart.addBodyPart(bodyPart);
			
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			// Setting the Subject and Content Type
			msg.setSubject(subject, "UTF-8");
			msg.setContent(multipart);
//			msg.setText(message, "UTF-8");
			Transport.send(msg);
		}
		catch (Exception e)
		{
			//logger.error(e);
		}
	}

	/**
	 * The Class SMTPAuthenticator.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.mail.Authenticator#getPasswordAuthentication()
		 */
		public PasswordAuthentication getPasswordAuthentication()
		{
			String username=null;
			String password=null;
			try {
				username = PropertiesUtil.getMailProperty(APCConstants.SMTP_AUTH_USER);
				 password = PropertiesUtil.getMailProperty(APCConstants.SMTP_AUTH_PWD);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new PasswordAuthentication(username, password);
		}
	}
}
