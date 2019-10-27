package com.janaldous.sponsorship.webfacade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.janaldous.sponsorship.checksponsor.CheckingSponsorRepository;
import com.janaldous.sponsorship.sponsor.CompanyResult;
import com.janaldous.sponsorship.sponsor.IRelevantSponsorService;

@RestController
public class CheckingSponsorController {

	@Autowired
	private CheckingSponsorRepository checkingSponsorRepository;
	
	@Autowired
	private IRelevantSponsorService relevantSponsorService;
	
	@GetMapping("/sponsors")
	public List<CompanyResult> getAll(@RequestParam(required = false) Optional<Integer> pageNumber) {
		return relevantSponsorService.findAllRelevantSponsors(pageNumber);
	}
	
}
