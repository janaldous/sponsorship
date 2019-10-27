package com.janaldous.sponsorship.sponsor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.sponsor.data.Tier;

@Repository
public interface TierRepository extends JpaRepository<Tier, Long> {

}
