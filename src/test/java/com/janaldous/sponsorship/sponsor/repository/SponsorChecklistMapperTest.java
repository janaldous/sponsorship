package com.janaldous.sponsorship.sponsor.repository;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.sponsor.data.Sponsor;
import com.janaldous.sponsorship.sponsor.data.Tier;
import com.janaldous.sponsorship.sponsor.data.TierNum;
import com.janaldous.sponsorship.sponsor.data.TierSub;

public class SponsorChecklistMapperTest {

	private SponsorChecklistMapper mapper = new SponsorChecklistMapper();

	// tests: arrange, act, assert
	
	@Test
	public void test() {
		Sponsor sponsor = new Sponsor();
		sponsor.setId(Long.valueOf(103));
		sponsor.setCounty("County");
		sponsor.setInLondon(true);
		sponsor.setName("Sponsor name");
		Set<Tier> tiers = new HashSet<>();
		Collections.addAll(tiers, Tier.builder().tier(TierNum.TIER_2).subTier(TierSub.GENERAL).build());
		sponsor.setTier(tiers);
		sponsor.setCity("City");

		CheckingSponsor checking = new CheckingSponsor();
		checking.setId(Long.valueOf(101));
		checking.setSponsor(sponsor);
		checking.setAbroad(true);
		checking.setApplied(false);
		checking.setAppliedByEmail(true);
		checking.setCategories("categories");
		checking.setCheckLater(true);
		checking.setIncorrectLikeness(true);
		checking.setInterestingIdea(true);
		checking.setNeedRightToWork(true);
		checking.setNiceSite(true);
		checking.setNoCareers(true);
		checking.setNoOpenings(true);
		checking.setNoTechJobs(true);
		checking.setOtherInfo("other info");

		CompanyHouseCompany chc = new CompanyHouseCompany();
		chc.setId(Long.valueOf(102));
		chc.setCompanyHouseName("Company house name");
		chc.setInLondon(true);
		Set<SIC> sics = new HashSet<>();
		Collections.addAll(sics, SIC.builder().code("1234").description("SIC").interested(true).build());
		chc.setSic(sics);
		chc.setSponsor(sponsor);
		chc.setTier2(true);
		
		CompanyResult companyResult = new CompanyResult(chc, checking);

		SponsorChecklist sponsorChecklist = mapper.to(companyResult);
		assertEquals(checking.getId(), sponsorChecklist.getCheckingSponsorId());
		assertEquals(sponsor.getName(), sponsorChecklist.getCheckingSponsorName());
		assertEquals(chc.getCompaniesHouseId(), sponsorChecklist.getCompanyHouseId());
		assertEquals(chc.getCompanyHouseName(), sponsorChecklist.getCompanyHouseName());
		assertEquals(checking.getCategories(), sponsorChecklist.getCategories());
		assertEquals(checking.getOtherInfo(), sponsorChecklist.getOtherInfo());
		assertEquals(checking.isAbroad(), sponsorChecklist.isAbroad());
		assertEquals(checking.isApplied(), sponsorChecklist.isApplied());
		assertEquals(checking.isAppliedByEmail(), sponsorChecklist.isAppliedByEmail());
		assertEquals(checking.isCheckLater(), sponsorChecklist.isCheckLater());
		assertEquals(checking.isIncorrectLikeness(), sponsorChecklist.isPossibleIncorrectLikeness());
		assertEquals(checking.isInterestingIdea(), sponsorChecklist.isInterestingIdea());
		assertEquals(checking.isNeedRightToWork(), sponsorChecklist.isNeedRightToWork());
		assertEquals(checking.isNiceSite(), sponsorChecklist.isNiceSite());
		assertEquals(checking.isNoCareers(), sponsorChecklist.isNoCareers());
		assertEquals(checking.isNoOpenings(), sponsorChecklist.isNoOpenings());
		assertEquals(checking.isNoTechJobs(), sponsorChecklist.isNoTechJobs());
	}

}
