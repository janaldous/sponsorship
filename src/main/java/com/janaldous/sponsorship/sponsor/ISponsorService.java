package com.janaldous.sponsorship.sponsor;

import java.util.List;

import com.janaldous.sponsorship.sponsor.data.Tier;

public interface ISponsorService {
	
	void createSponsor(String name, String city, String county, boolean inLondon, List<Tier> tiers);
	
}