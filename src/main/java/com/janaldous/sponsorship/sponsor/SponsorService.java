package com.janaldous.sponsorship.sponsor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaldous.sponsorship.sponsor.data.Sponsor;
import com.janaldous.sponsorship.sponsor.data.Tier;
import com.janaldous.sponsorship.sponsor.repository.SponsorRepository;
import com.janaldous.sponsorship.sponsor.repository.TierRepository;

@Service
public class SponsorService implements ISponsorService {
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private TierRepository tierRepository;
	
	@Override
	public void createSponsor(String name, String city, String county,
			boolean inLondon, List<Tier> tiers) {
		Sponsor sponsor = Sponsor.builder()
				.name(name)
				.city(city)
				.county(county)
				.inLondon(inLondon)
				.build();
		
		Set<Tier> newTiers = new HashSet<>();
		for (int i = 0; i < tiers.size(); i++) {
			Tier tier = tiers.get(i);
			for (Tier t: tierRepository.findAll()) {
				if (t.getTier() == tier.getTier() && t.getSubTier() == tier.getSubTier()) {
					newTiers.add(t);
				}
			}
		}
		sponsor.setTier(newTiers);
		sponsorRepository.save(sponsor);
	}

}
