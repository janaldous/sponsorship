package com.janaldous.sponsorship.email.core;

public interface EmailService {

	void sendEmail(String to, String subject, String body);

	void sendEmailWithAttachments(String to, String subject, String body, String attachmentFilename, String pathToAttachment);
	
}
