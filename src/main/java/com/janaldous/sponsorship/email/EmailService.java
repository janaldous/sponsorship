package com.janaldous.sponsorship.email;

import java.io.File;

import javax.mail.MessagingException;

public interface EmailService {

	void sendEmail(String to, String subject, String body) throws MessagingException;

	void sendEmailWithAttachments(String to, String subject, String text, String attachmentFilename, File attachment)
			throws MessagingException;
	
}
