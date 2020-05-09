package com.janaldous.sponsorship.sponsor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.sponsor.data.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

	@Query("select s from Sponsor s where s.inLondon = true")
	List<Sponsor> findLondonSponsors();
	
}
