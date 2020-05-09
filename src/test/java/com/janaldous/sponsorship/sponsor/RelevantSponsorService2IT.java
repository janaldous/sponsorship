package com.janaldous.sponsorship.sponsor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.integrationtest.Integration;
import com.janaldous.sponsorship.sponsor.repository.CompanyResult;
import com.janaldous.sponsorship.sponsor.repository.SponsorChecklist;

@Category(Integration.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class RelevantSponsorService2IT {

	private Logger log = LoggerFactory.getLogger(RelevantSponsorService2IT.class);
	
	@Autowired
	private RelevantSponsorService2 relevantSponsorService2;
	
	@Autowired
	private RelevantSponsorService relevantSponsorService;
	
	public void test() {
		List<CompanyResult> companies = relevantSponsorService2.findAllRelevantSponsors(Optional.of(11), Optional.of(50));
		System.out.println(companies.size());
		companies.stream().forEach(System.out::println);
	}
	
	@Test
	public void test3() {
		List<SponsorChecklist> companies = relevantSponsorService2.findAllRelevantSponsors3(Optional.of(0), Optional.of(50));
		assertThat(companies.size(), greaterThan(0));
		System.out.println(companies.size());
//		companies.stream().forEach(System.out::println);
	}
	
	@Test
	public void test2() {
		Optional<Integer> optPageNumber = Optional.of(0);
		Optional<Integer> optPageSize = Optional.of(20);
		
		List<CompanyResult> before = relevantSponsorService.findAllRelevantSponsors(optPageNumber, optPageSize);
		List<SponsorChecklist> after = relevantSponsorService2.findAllRelevantSponsors3(optPageNumber, optPageSize);
		
		log.info(before.size() + "");
		log.info(after.size() + "");
		
		assertThat(before.size(), greaterThan(0));
		assertEquals(before.size(), after.size());
		
		for (int i = 0; i < before.size(); i++) {
			CompanyResult companyResult = before.get(i);
			SponsorChecklist sponsorChecklist = after.get(i);
			
			assertEquals("not equals at " + i, companyResult.getChecking().getId(), sponsorChecklist.getCheckingSponsorId());
			assertEquals("not equals at " + i, companyResult.getCompany().getCompaniesHouseId(), sponsorChecklist.getCompanyHouseId());
		}
//		companies.stream().forEach(System.out::println);

	}

}
