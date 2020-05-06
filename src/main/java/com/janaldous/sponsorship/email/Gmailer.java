package com.janaldous.sponsorship.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Gmailer implements IEmailService {

	private static final Logger log = LoggerFactory.getLogger(Gmailer.class);

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendEmail(String to, String subject, String text) {
		log.info("Preparing to send email");

		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setTo(to);
		message.setSubject(subject); 
		message.setText(text);
		emailSender.send(message);

		log.info("Email sending successful");
	}

	@Override
	public void sendEmailWithAttachments(String to, String subject, String text, String attachmentFilename, File attachment) throws MessagingException {
		log.info("preparing to send email");

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);

		FileSystemResource file = new FileSystemResource(attachment);
		helper.addAttachment(attachmentFilename, file);

		emailSender.send(message);

		log.info("sent email");
	}

}
