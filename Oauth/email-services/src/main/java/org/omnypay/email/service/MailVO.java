/**
 * 
 */
package org.omnypay.email.service;

import java.io.Serializable;

/**
 * @author iliyas
 *
 */
public class MailVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3777917489788757L;
	private String[] recipients;
	private String subject;
	private String message;
	private String language;
	
	/**
	 * No argument constructor
	 */
	public MailVO() {
		super();
	}

	/**
	 * @param recipients
	 * @param subject
	 * @param message
	 */
	public MailVO(String[] recipients, String subject, String message) {
		super();
		this.recipients = recipients;
		this.subject = subject;
		this.message = message;
	}

	
	/**
	 * @param recipients
	 * @param subject
	 * @param message
	 * @param language
	 */
	public MailVO(String[] recipients, String subject, String message, String language) {
		super();
		this.recipients = recipients;
		this.subject = subject;
		this.message = message;
		this.language = language;
	}

	/**
	 * @return the recipients
	 */
	public String[] getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients the recipients to set
	 */
	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
