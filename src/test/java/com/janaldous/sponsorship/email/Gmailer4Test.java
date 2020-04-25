package com.janaldous.sponsorship.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Gmailer4Test {

	@Autowired
	private Gmailer4 gmailer;

	@Test
	public void test() {
		gmailer.sendSimpleMessage("jat.torres@gmail.com", "Testing Gmail", "Dear Mr Crawler, \nPlease do not spam my email");
	}


}
