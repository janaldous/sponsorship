package com.janaldous.sponsorship.namecomparison;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.companieshouse.CompaniesHouseAPI;
import com.janaldous.sponsorship.companieshouse.dto.Company;
import com.janaldous.sponsorship.sponsor.RelevantSponsorRepository;
import com.janaldous.sponsorship.sponsor.SponsorRepository;
import com.janaldous.sponsorship.sponsor.data.ProcessStatus;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.sponsor.data.Sponsor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NameComparisonIT {
	
	private Logger log = LoggerFactory.getLogger(NameComparisonIT.class);
	
	@Autowired
    private SponsorRepository sponsorRepository;
	
	@Autowired
	private RelevantSponsorRepository relevantSponsorRepository;
	
	@Autowired
	private CompaniesHouseAPI chService;
	
	@Test
	public void test() {
		List<Sponsor> sponsors = sponsorRepository.findAll().stream().skip(50).limit(50).collect(Collectors.toList());
		
		NameComparator comparator = new AlphabetComparator();
		
		for (Sponsor sponsor: sponsors) {
//			String name1 = sponsor.getSponsorName();
//			String name2 = sponsor.getCompanyHouseName();
//			int score = comparator.calculateLikeness(name1, name2);
//			log.info("score = " + score + " " + name1 + " " + name2);
		}
	}
	
	@Test
	public void test2() {
		List<RelevantSponsor> sponsors = relevantSponsorRepository.findByStatus(ProcessStatus.FAILED_LIKENESS);
		
		NameComparator comparator = new FuzzyComparator();
		
		for (RelevantSponsor sponsor: sponsors) {
			String name = sponsor.getSponsor().getName();
			Company company = chService.searchCompany(name);
			int calculateLikeness = comparator.calculateLikeness(name, company.getName());
			log.info("result = " + company.getName() + " likeness = " + calculateLikeness);
			if (calculateLikeness <= CompaniesHouseAPI.LIKENESS_THRESHOLD) {
				log.info("improvement ***********");
			} else {
				log.info("same");
			}
		}
	}
	
}
