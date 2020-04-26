package com.janaldous.sponsorship.webfacade;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.janaldous.sponsorship.email.EmailApplicationService;
import com.janaldous.sponsorship.email.core.Gmailer;
import com.janaldous.sponsorship.webfacade.dto.EmailApplicationRequest;

@Controller
public class EmailController {

	private static final Logger log = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailApplicationService emailApplicationService;
	
	@PostMapping("/application/{id}")
	public ResponseEntity<String> sendEmailApplication(EmailApplicationRequest emailRequest,
			@PathParam(value = "id") String id) {
		
		log.info("received send email application request");
		// TODO make audit
		emailApplicationService.sendDefaultEmailApplication(emailRequest.getToAddress(), emailRequest.getJobName());
		return ResponseEntity.ok("email id?");
	}
	
}
