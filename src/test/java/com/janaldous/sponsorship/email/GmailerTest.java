package com.janaldous.sponsorship.email;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmailerTest {
	
	@Autowired
	private Gmailer gmailer;
	
	@Test
	public void test() {
		assertNotNull(gmailer);
		
		String role = "Software Engineer";
		
		gmailer.sendEmail(role);
	}

}
