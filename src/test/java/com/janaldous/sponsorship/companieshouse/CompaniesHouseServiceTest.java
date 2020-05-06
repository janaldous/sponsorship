package com.janaldous.sponsorship.companieshouse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.companieshouse.ICompanyHouseAPI;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.companieshouse.dto.Company;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompaniesHouseServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(CompaniesHouseServiceTest.class);
	
	@Autowired
	private ICompanyHouseAPI companiesHouseService;
	
	@Test
	public void testSearchCompany() {
		String queryParam = "BLUE+STATE+DIGITAL+UK+LIMITED";
		Company result = companiesHouseService.searchCompany(queryParam);
		log.info("result =", result);
		assertNotNull(result);
		
		assertEquals("06873977", result);
	}
	
	@Test
	public void testGetCompanySIC() {
		String queryParam = "06873977";
		List<SIC> result = companiesHouseService.getCompanySIC(queryParam);
		assertTrue(!result.isEmpty());
	}

}
