package com.janaldous.sponsorship.sponsor;

import java.util.List;
import java.util.Optional;

import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;

public interface IRelevantSponsorService {

	List<RelevantSponsor> getNextBatch(int batchSize);
	
	List<CompanyResult> findAllRelevantSponsors(Optional<Integer> pageNumber);
	
}