package com.janaldous.sponsorship.companieshouse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.companieshouse.apidomain.SearchResult;
import com.janaldous.sponsorship.integrationtest.Integration;

@Category(Integration.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyHouseAPIIT {
	
	private static final Logger log = LoggerFactory.getLogger(CompanyHouseAPIIT.class);
	
	@Autowired
	private ICompanyHouseAPI companiesHouseService;
	
	@Test
	public void testSearchCompany() {
		String companyName = "BLUE+STATE+DIGITAL+UK+LIMITED";
		
		SearchResult result = companiesHouseService.searchCompany(companyName);
		log.info("result = " + result.toString());
		
		assertNotNull(result);
		assertEquals("BLUE STATE DIGITAL UK LIMITED", result.getItems().get(0).getTitle());
	}
	
	@Test
	public void testGetCompanySIC() {
		String companyId = "06873977";
		List<String> expected = Arrays.asList("63120");
		
		List<String> result = companiesHouseService.getCompanySIC(companyId);
		
		assertThat(result, is(expected));
	}

}
