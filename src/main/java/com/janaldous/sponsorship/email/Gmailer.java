package com.janaldous.sponsorship.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Gmailer {

	private static final Logger logger = LoggerFactory.getLogger(Gmailer.class);

	@Autowired
	private Environment env;

	public void sendEmail(String role) {

		String email = env.getProperty("email");
		String emailPassword = env.getProperty("email.password");
		String oathClientId = env.getProperty("oauth.clientid");
		String oathSecret = env.getProperty("oath.secret");

		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		String from = email;

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.auth.mechanisms", "XOAUTH2");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(email, emailPassword);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Application - " + role);

			// Now set the actual message
			message.setText("This is actual message");

			logger.info("sending...");
			// Send message
			Transport transport = session.getTransport("smpt");
			transport.connect("smtp.gmail.com", oathClientId, oathSecret);
			transport.send(message, message.getAllRecipients());
			logger.info("Sent message successfully....");
		} catch (MessagingException mex) {
			logger.error(mex.toString(), mex);
		}

	}
}
