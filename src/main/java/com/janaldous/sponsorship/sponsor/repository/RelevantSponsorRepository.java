package com.janaldous.sponsorship.sponsor.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.sponsor.data.ProcessStatus;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.sponsor.data.Sponsor;

@Repository
public interface RelevantSponsorRepository extends JpaRepository<RelevantSponsor, Long> {

	@Query("select r from RelevantSponsor r where processed = false")
	List<RelevantSponsor> findUnprocessed();
	
	List<RelevantSponsor> findByStatus(ProcessStatus status);
	
	@Query("select r from RelevantSponsor r join Sponsor s on r.sponsor = s where status = 'SUCCESS'")
	List<RelevantSponsor> findSuccessfulTier2(Pageable pageable);
	
	List<RelevantSponsor> findBySponsor(Sponsor sponsor);
	
}
