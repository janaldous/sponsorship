package com.janaldous.sponsorship.pdfparser;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadSponsorsWorkTest {

	@Autowired
	private ReadSponsorsWork work;
	
	@Test
	public void test() throws IOException {
		int interval = 1;
		for (int i = 1992; i <= 1998; i += interval) {
			work.read(i, i);
			System.out.println(i);
		}
	}
	
}
