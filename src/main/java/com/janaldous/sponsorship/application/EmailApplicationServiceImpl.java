package com.janaldous.sponsorship.application;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.janaldous.sponsorship.application.domain.EmailProperties;
import com.janaldous.sponsorship.email.IEmailService;

import lombok.NonNull;

@Component
class EmailApplicationServiceImpl implements IEmailApplicationService {

	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private EmailProperties emailProperties;

	@Override public void sendDefaultEmailApplication(@NonNull String to, @NonNull String jobName)
			throws MessagingException {

		String subject = "Application - " + jobName;
		String body = emailProperties.getEmailTemplate().replace("{jobName}", jobName);
		
		emailService.sendEmailWithAttachments(to, subject, body, emailProperties.getAttachmentFilename(), emailProperties.getAttachment());
	}

}
