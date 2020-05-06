package com.janaldous.sponsorship.textparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.companieshouse.ICompanyHouseAPI;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.sponsor.IRelevantSponsorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVCompaniesParserTest {

	@Autowired
	private ICompanyHouseAPI companiesHouseService;
	
	@Autowired
	private IRelevantSponsorService sponsorService;
	
	@Test
	public void test() {
		// company name -> searchable name
		String name = "Betbull Social Sports UK Limited ";
		
		// searchable name -> search company
		// company id -> sic code
		List<SIC> sics = companiesHouseService.getCompanySICByName(name);
		
		// sic code, check if IT related
		Set<SIC> sicSet = new HashSet<>(sics);
//		Sponsor sponsor = Sponsor.builder().companyName(name).inLondon(true).tier2(true).sic(sicSet).build();
		assertEquals(1, sicSet.size());
		assertTrue(sicSet.stream().map(x -> x.getCode()).anyMatch(x -> "70229".equals(x)));
//		sponsorRepository.save(sponsor);
	}
	
}
