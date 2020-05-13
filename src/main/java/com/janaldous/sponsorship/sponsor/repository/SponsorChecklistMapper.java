package com.janaldous.sponsorship.sponsor.repository;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;
import com.janaldous.sponsorship.sponsor.data.Sponsor;

public class SponsorChecklistMapper {

	public static SponsorChecklist to(CompanyResult companyResult) {

		CheckingSponsor cs = companyResult.getChecking();
		Sponsor sponsor = cs.getSponsor();
		CompanyHouseCompany chc = companyResult.getCompany();

		SponsorChecklist output = new SponsorChecklist();
		output.setAbroad(cs.isAbroad());
		output.setApplied(cs.isApplied());
		output.setAppliedByEmail(cs.isAppliedByEmail());
		output.setCategories(cs.getCategories());
		output.setSponsorId(sponsor.getId());
		output.setSponsorName(sponsor.getName());
		output.setCheckLater(cs.isCheckLater());
		output.setCompanyHouseId(chc.getCompaniesHouseId());
		output.setCompanyHouseName(chc.getCompanyHouseName());
		output.setFinished(cs.getChecked());
//		output.setHasDraftEmail();
		output.setInterestingIdea(cs.isInterestingIdea());
		output.setNeedRightToWork(cs.isNeedRightToWork());
		output.setNiceSite(cs.isNiceSite());
		output.setNoCareers(cs.isNoCareers());
		output.setNoOpenings(cs.isNoOpenings());
		output.setNoTechJobs(cs.isNoTechJobs());
		output.setOtherInfo(cs.getOtherInfo());
		output.setPossibleIncorrectLikeness(cs.isIncorrectLikeness());

		return output;
	}

}
