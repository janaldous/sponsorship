package com.janaldous.sponsorship.companieshouse.sic;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.companieshouse.data.SICUpload;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class SICUploadTest {

	@Autowired
	private SICUpload upload;
	
//	@Ignore
	/**
	 * http://resources.companieshouse.gov.uk/sic/
	 */
	@Test
	public void test() {
		upload.something();
	}
	
}