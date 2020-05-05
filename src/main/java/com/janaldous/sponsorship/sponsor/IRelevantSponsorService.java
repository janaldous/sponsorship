package com.janaldous.sponsorship.sponsor;

import java.util.List;
import java.util.Optional;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.webfacade.dto.CheckedDto;

public interface IRelevantSponsorService {

	List<RelevantSponsor> getNextBatch(int batchSize);
	
	List<CompanyResult> findAllRelevantSponsors(Optional<Integer> pageNumber, Optional<Integer> pageSize);

	CheckingSponsor checked(Long id, CheckedDto checked);

	List<CompanyResult> getCompanyResultsWithSchedule(Optional<Integer> pageNumber,
			Optional<Integer> pageSize);
	
	List<SponsorChecklist> getCompanyResultsWithSchedule2(Optional<Integer> pageNumber,
			Optional<Integer> pageSize);
	
}