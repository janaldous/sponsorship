package com.janaldous.sponsorship.application;

import javax.mail.MessagingException;

interface IEmailApplicationService {

	/**
	 * Send application as email with default body and CV as attachment
	 * 
	 * @param to HR email of company
	 * @param jobName
	 * @throws MessagingException 
	 */
	public void sendDefaultEmailApplication(String to, String jobName) throws MessagingException;
	
}
