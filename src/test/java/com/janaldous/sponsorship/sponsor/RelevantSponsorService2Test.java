package com.janaldous.sponsorship.sponsor;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelevantSponsorService2Test {

	@Autowired
	private RelevantSponsorService2 relevantSponsorService;
	
//	@Test
	public void test() {
		List<CompanyResult> companies = relevantSponsorService.findAllRelevantSponsors(Optional.of(11), Optional.of(50));
		System.out.println(companies.size());
		companies.stream().forEach(System.out::println);
	}
	
	@Test
	public void test2() {
		List<SponsorChecklist> companies = relevantSponsorService.findAllRelevantSponsors3(Optional.of(11), Optional.of(50));
		System.out.println(companies.size());
		companies.stream().forEach(System.out::println);

	}

}
