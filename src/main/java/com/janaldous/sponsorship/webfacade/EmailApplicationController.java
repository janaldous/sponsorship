package com.janaldous.sponsorship.webfacade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.janaldous.sponsorship.application.ApplicationException;
import com.janaldous.sponsorship.application.IApplicationService;
import com.janaldous.sponsorship.application.domain.EmailQueueItem;
import com.janaldous.sponsorship.application.domain.EmailQueueStatus;
import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.checksponsor.CheckingSponsorRepository;
import com.janaldous.sponsorship.sponsor.SponsorRepository;
import com.janaldous.sponsorship.sponsor.data.Sponsor;
import com.janaldous.sponsorship.webfacade.dto.EmailApplicationRequest;
import com.janaldous.sponsorship.webfacade.dto.EmailApplicationUpdateRequest;
import com.janaldous.sponsorship.webfacade.exception.ResourceNotFoundException;

@Controller
public class EmailApplicationController {

	@Autowired
	private IApplicationService applicationService;
	
	@Autowired
	private CheckingSponsorRepository checkingSponsorRepository;
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	@GetMapping("/application/email")
	public ResponseEntity<List<EmailQueueItem>> getEmailQueue(EmailQueueStatus emailQueueStatus) {
		List<EmailQueueItem> result = applicationService.getEmailApplicationByStatus(emailQueueStatus);
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/application/email/{id}")
	public ResponseEntity<EmailQueueItem> getEmailQueueItem(@PathVariable("id") String idStr) {
		Long id = Long.parseLong(idStr);
		EmailQueueItem result = applicationService.getEmailApplicationById(id).orElseThrow(() -> new ResourceNotFoundException());
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/application/email")
	public ResponseEntity<String> draftEmailApplication(@RequestBody EmailApplicationRequest emailRequest) {
		Sponsor sponsor = sponsorRepository.findById(emailRequest.getSponsorId()).orElseThrow(() -> new ResourceNotFoundException());
		CheckingSponsor checkingSponsor = Optional.ofNullable(checkingSponsorRepository.findBySponsor(sponsor).get(0)).orElseThrow(() -> new ResourceNotFoundException());
		if (applicationService.hasApplicationByEmail(emailRequest.getSponsorId())) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		applicationService.draftApplicationByEmail(emailRequest.getToAddress(), emailRequest.getJobName(), checkingSponsor);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/application/email/{emailLogId}")
	public ResponseEntity<String> sendEmailApplication(@PathVariable String emailLogId, @RequestBody EmailApplicationUpdateRequest emailRequest) {
		try {
			if (EmailQueueStatus.READY_TO_BE_SENT == emailRequest.getStatus()) {
				applicationService.sendApplicationByEmail(Long.parseLong(emailLogId));
			}
			return ResponseEntity.ok().build();
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email log id is cannot be parsed to long: " + emailLogId);
		} catch (ApplicationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
