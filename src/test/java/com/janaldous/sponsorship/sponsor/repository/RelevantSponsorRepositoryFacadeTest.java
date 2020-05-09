package com.janaldous.sponsorship.sponsor.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelevantSponsorRepositoryFacadeTest {

	private final static Logger log = LoggerFactory.getLogger(RelevantSponsorRepositoryFacadeTest.class);
	
	@Autowired
	private RelevantSponsorRepositoryFacade relevantSponsorRepositoryFacade;
	
	@Test
	public void test() {
		List<CompanyResult> result = relevantSponsorRepositoryFacade.findAllRelevantSponsors(0, 10);
		log.info(result.get(0).toString());
		assertEquals(10, result.size());
	}

}
