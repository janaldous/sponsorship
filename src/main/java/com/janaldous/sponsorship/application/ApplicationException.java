package com.janaldous.sponsorship.application;

import javax.mail.MessagingException;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4068032099658457600L;

	public ApplicationException(String message, MessagingException e) {
		super(message, e);
	}
}
