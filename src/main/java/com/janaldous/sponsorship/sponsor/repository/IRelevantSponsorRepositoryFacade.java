package com.janaldous.sponsorship.sponsor.repository;

import java.util.List;

public interface IRelevantSponsorRepositoryFacade {

	List<SponsorChecklist> getRelevantSponsors(int pageNumber, int pageSize);
	
}
