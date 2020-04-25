package com.janaldous.sponsorship.email;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.janaldous.sponsorship.email.core.EmailService;

@Component
public class EmailApplicationServiceImpl implements EmailApplicationService {

	@Autowired
	private EmailService emailService;
	
	@Override
	public void sendDefaultEmailApplication(String to, String jobName) {
		Objects.requireNonNull(to, "To email must not be null");
		Objects.requireNonNull(jobName, "job name must not be null");
		
		String subject = "Application - " + jobName;
		String body = "Dear Sir/Madam,\n" + 
				"\n" + 
				"Please find attached copy of my CV for consideration for a software engineer position in London.\n" + 
				"\n" + 
				"I have been a software developer with experience with Java Spring and Javascript among other technologies "
				+ "however I would like to transition into a role where both customer facing and technical skills are needed.\n" + 
				"\n" + 
				"I look forward to hearing from you.\n" + 
				"\n" + 
				"Yours sincerely,\n" + 
				"\n" + 
				"Jat Torres";
		String attachmentFilename = "Jan Aldous Torres CV.pdf";
		String pathToAttachment = "/Users/janaldoustorres/Documents/Careers/back to london/one_page_cv/Jan Aldous Torres CV.pdf";
		emailService.sendEmailWithAttachments(to, subject, body, attachmentFilename, pathToAttachment);
	}

}
