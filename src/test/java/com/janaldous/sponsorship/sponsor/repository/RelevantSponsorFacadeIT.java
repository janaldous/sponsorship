package com.janaldous.sponsorship.sponsor.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.integrationtest.Integration;
import com.janaldous.sponsorship.sponsor.repository.NewRelevantSponsorRepositoryFacade;
import com.janaldous.sponsorship.sponsor.repository.RelevantSponsorRepositoryFacade;
import com.janaldous.sponsorship.sponsor.repository.SponsorChecklist;

@Category(Integration.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class RelevantSponsorFacadeIT {

	private Logger log = LoggerFactory.getLogger(RelevantSponsorFacadeIT.class);
	
	@Autowired
	private NewRelevantSponsorRepositoryFacade newRelevantSponsorService;
	
	@Autowired
	private RelevantSponsorRepositoryFacade relevantSponsorService;
	
	public void test() {
		List<SponsorChecklist> companies = newRelevantSponsorService.getRelevantSponsors(11, 50);
		System.out.println(companies.size());
		companies.stream().forEach(System.out::println);
	}
	
	@Test
	public void test3() {
		List<SponsorChecklist> companies = newRelevantSponsorService.getRelevantSponsors(0, 50);
		assertThat(companies.size(), greaterThan(0));
		System.out.println(companies.size());
//		companies.stream().forEach(System.out::println);
	}
	
	@Test
	public void test2() {
		int pageNumber = 0;
		int pageSize = 20;
		
		List<SponsorChecklist> beforeResult = relevantSponsorService.getRelevantSponsors(pageNumber, pageSize);
		List<SponsorChecklist> newResult = newRelevantSponsorService.getRelevantSponsors(pageNumber, pageSize);
		
		log.info(beforeResult.size() + "");
		log.info(newResult.size() + "");
		
		assertThat(beforeResult.size(), greaterThan(0));
		assertEquals(beforeResult.size(), newResult.size());
		
		for (int i = 0; i < beforeResult.size(); i++) {
			SponsorChecklist before = beforeResult.get(i);
			SponsorChecklist after = newResult.get(i);
			
			assertEquals("not equals at " + i, before.getSponsorId(), after.getSponsorId());
			assertEquals("not equals at " + i, before.getCompanyHouseId(), after.getCompanyHouseId());
		}
//		companies.stream().forEach(System.out::println);

	}

}
