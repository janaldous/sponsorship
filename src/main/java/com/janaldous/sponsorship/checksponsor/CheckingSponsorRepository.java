package com.janaldous.sponsorship.checksponsor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.sponsor.data.Sponsor;

@Repository
public interface CheckingSponsorRepository extends JpaRepository<CheckingSponsor, Long> {

	List<CheckingSponsor> findBySponsor(Sponsor sponsor);

}
