package com.janaldous.sponsorship.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.webfacade.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceImplTest {

	@Autowired
	private IApplicationService applicationService;
	
	@Test
	public void testDraftInQueue() {
		boolean result = applicationService.hasApplicationByEmail(Long.valueOf(99638));
		
		assertTrue(result);
	}
	
	@Test
	public void testDraftInLog() {
		boolean result = applicationService.hasApplicationByEmail(Long.valueOf(99631));
		
		assertTrue(result);
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testCheckingSponsorDoesntExist() {
		applicationService.hasApplicationByEmail(Long.valueOf(631));
	}
	
	@Test
	public void testNoEmailApplication() {
		boolean result = applicationService.hasApplicationByEmail(Long.valueOf(99115));
		
		assertFalse(result);
	}

}
