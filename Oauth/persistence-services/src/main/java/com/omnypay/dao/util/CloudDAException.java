package com.omnypay.dao.util;

/**
 * 
 * @author iliyasm
 *
 */
public class CloudDAException extends Exception{

	/** The Constant serialVersionUID. */
private static final long serialVersionUID = 4198091614354633380L;


/** The error code. */
private String errorCode;

/** The error message. */
private String errorMessage;

/**
 * Instantiates a new Omnypay da exception.
 * 
 * @param errorCode
 *            the error code
 * @param message
 *            the message
 */
public CloudDAException(String errorCode, String message) {
	super(message);
	this.errorCode = errorCode;
	this.errorMessage = message;

}

/**
 * The Constructor.
 * 
 * @param message
 *            the message
 */
public CloudDAException(String message) {
	super(message);
}

/**
 * The Constructor.
 * 
 * @param cause
 *            the cause
 */
public CloudDAException(Throwable cause) {
	super(cause);
}

/**
 * The Constructor.
 * 
 * @param message
 *            the message
 * @param cause
 *            the cause
 */
public CloudDAException(String message, Throwable cause) {
	super(message, cause);
}

/**
 * Gets the error code.
 * 
 * @return the error code
 */
public String getErrorCode() {
	return errorCode;
}

/**
 * Sets the error code.
 * 
 * @param errorCode
 *            the new error code
 */
public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
}

/**
 * Gets the error message.
 * 
 * @return the error message
 */
public String getErrorMessage() {
	return errorMessage;
}

/**
 * Sets the error message.
 * 
 * @param errorMessage
 *            the new error message
 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
}
