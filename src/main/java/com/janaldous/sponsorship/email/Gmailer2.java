package com.janaldous.sponsorship.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Gmailer2 {

	@Autowired
    private JavaMailSender javaMailSender;

	public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("jat.torres@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
	
}
