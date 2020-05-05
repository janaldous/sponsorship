package com.janaldous.sponsorship.webfacade;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.sponsor.CompanyResult;
import com.janaldous.sponsorship.sponsor.IRelevantSponsorService;
import com.janaldous.sponsorship.sponsor.SponsorChecklist;
import com.janaldous.sponsorship.webfacade.dto.CheckedDto;

@RestController
public class CheckingSponsorController {

	@Autowired
	private IRelevantSponsorService relevantSponsorService;
	
	@Qualifier("second")
	@Autowired
	private IRelevantSponsorService relevantSponsorService2;
	
	@GetMapping("/sponsors")
	public List<CompanyResult> getAll(@RequestParam(required = false) Optional<Integer> pageNumber, @RequestParam(required = false) Optional<Integer> pageSize) {
		return relevantSponsorService.getCompanyResultsWithSchedule(pageNumber, pageSize);
	}
	
	@GetMapping("/sponsors2")
	public List<SponsorChecklist> getAll2(@RequestParam(required = false) Optional<Integer> pageNumber, @RequestParam(required = false) Optional<Integer> pageSize) {
		return relevantSponsorService2.getCompanyResultsWithSchedule2(pageNumber, pageSize);
	}
	
	@PostMapping("/sponsors/{id}")
	public CheckingSponsor checked(@PathVariable("id") String id, @RequestBody CheckedDto checked) {
		if (Strings.isBlank(id)) {
			throw new IllegalArgumentException("id is required");
		}
		return relevantSponsorService.checked(Long.valueOf(id), checked);
	}
}
