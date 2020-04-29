package com.janaldous.sponsorship.application;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.janaldous.sponsorship.email.EmailService;

import lombok.NonNull;

@Component
class EmailApplicationServiceImpl implements EmailApplicationService {

	@Autowired
	private EmailService emailService;
	
	@Override
	public void sendDefaultEmailApplication(@NonNull String to, @NonNull String jobName) throws MessagingException {
		
		String subject = "Application - " + jobName;
		String body = "Dear Sir/Madam,\n" + 
				"\n" + 
				"Please find attached copy of my CV for consideration for the " + jobName + " position in London.\n" + 
				"\n" + 
				"I have been a software developer with experience with Java Spring and React among other technologies working "
				+ "on insurance applications for 3 Baltic countries in SEB. I am looking for a role that to expand my skills "
				+ "in a more challenging role. I see my skills and experience are a good fit with your company and would be "
				+ "grateful if you consider me for this role.\n" + 
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
