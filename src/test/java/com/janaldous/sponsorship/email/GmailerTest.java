package com.janaldous.sponsorship.email;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.email.core.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmailerTest {

	@Autowired
	private EmailService gmailer;

	@Ignore
	@Test
	public void test() {
		gmailer.sendEmail("jat.torres@gmail.com", "Testing Gmail", "Dear Mr Crawler, \nPlease do not spam my email");
	}

	@Ignore
	@Test
	public void testWithAttachment() {
		gmailer.sendEmailWithAttachments(
				"etorres@brent.edu.ph", 
				"Application - Software Engineer", 
				"Dear Sir/Madam,\n" + 
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
				"Jat Torres",
				"Jan Aldous Torres CV.pdf",
				"/Users/janaldoustorres/Documents/Careers/back to london/one_page_cv/Jan Aldous Torres CV.pdf"
				);
	}


}
