package com.janaldous.sponsorship.sponsor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;
import com.janaldous.sponsorship.namecomparison.NameNormalizer;
import com.janaldous.sponsorship.sponsor.data.Sponsor;

@RunWith(SpringRunner.class)
public class RelevantSponsorTest {
	
	@TestConfiguration
    static class ContextConfiguration {
  
        @Bean
        public NameNormalizer nameNormalizer() {
            return new NameNormalizer();
        }
    }
	
	@Autowired
	private NameNormalizer nameNormalizer;
	
	@Test
	public void testCheckingPossibleIncorrectLikeness_exact() {
		String companyName = "company house name";
		CompanyResult output = checkCompanyNameLikeness(companyName, companyName);
		assertTrue(!output.isPossibleIncorrectLikeness());

	}

	private CompanyResult checkCompanyNameLikeness(String name1, String name2) {
		CompanyHouseCompany company = new CompanyHouseCompany();
		company.setCompanyHouseName(name1);
		Sponsor sponsor = new Sponsor();
		sponsor.setName(name1);
		CheckingSponsor checkingSponsor = new CheckingSponsor();
		checkingSponsor.setSponsor(sponsor);
		CompanyResult result = new CompanyResult(company, checkingSponsor);
		return RelevantSponsorService.mapIncorrectLikeness(result, nameNormalizer);
	}
	
	@Test
	public void testCheckingPossibleIncorrectLikeness_normalized() {
		String companyName = "company house name limited";
		CompanyResult output = checkCompanyNameLikeness(companyName, "company house name ltd");
		assertTrue(!output.isPossibleIncorrectLikeness());
	}
	
	@Test
	public void testCheckingPossibleIncorrectLikeness_negativeTest1() {
		String companyName = "company house name";
		CompanyResult output = checkCompanyNameLikeness(companyName, "some other name");
		assertFalse(output.isPossibleIncorrectLikeness());
	}
	
	@Test(expected = RuntimeException.class)
	public void testCheckingPossibleIncorrectLikeness_negativeTest_nullSponsor() {
		String companyName = "company house name";
		CompanyHouseCompany company = new CompanyHouseCompany();
		company.setCompanyHouseName(companyName);
		CheckingSponsor checkingSponsor = new CheckingSponsor();
		CompanyResult result = new CompanyResult(company, checkingSponsor);
		RelevantSponsorService.mapIncorrectLikeness(result, nameNormalizer);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCheckingPossibleIncorrectLikeness_negativeTest_nullCompanyHouse() {
		CompanyHouseCompany company = new CompanyHouseCompany();
		CheckingSponsor checkingSponsor = new CheckingSponsor();
		CompanyResult result = new CompanyResult(company, checkingSponsor);
		RelevantSponsorService.mapIncorrectLikeness(result, nameNormalizer);
	}
	
}
