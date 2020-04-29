package com.janaldous.sponsorship.webfacade;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.janaldous.sponsorship.application.ApplicationService;
import com.janaldous.sponsorship.checksponsor.CheckingSponsorRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailApplicationController.class)
public class EmailApplicationControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ApplicationService applicationService;
	
	@MockBean
	private CheckingSponsorRepository repository;
	
	

}
