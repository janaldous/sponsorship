package com.janaldous.sponsorship.email;

import javax.mail.MessagingException;

public interface EmailService {

	void sendEmail(String to, String subject, String body) throws MessagingException;

	void sendEmailWithAttachments(String to, String subject, String body, String attachmentFilename, String pathToAttachment) throws MessagingException;
	
}
