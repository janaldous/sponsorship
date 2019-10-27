package com.janaldous.sponsorship.pdfparser;

import java.util.ArrayList;
import java.util.List;

import com.janaldous.sponsorship.sponsor.data.Tier;

import lombok.Setter;

public class TextParserAdvanced implements ITextParser {

	@Setter
	private String name;
	
	@Setter
	private String city;
	
	@Setter
	private String cityCounty;
	
	@Setter
	private String tier;
	
	@Override
	public List<Company> generateCompanies() {
		String[] names = name.split("\n");
		String[] cities = city.split("\n");
		String[] cityCounties = cityCounty.split("\n");
		String[] tiers = tier.split("\n");
		
		if (names.length != cities.length || cities.length != cityCounties.length) {
			throw new IllegalArgumentException("length of inputs are not the same " + names.length + " " + cities.length + " " + " " + cityCounties.length);
		}
		
		List<List<Tier>> tiersList = new ArrayList<>();
		List<Tier> tempTier = new ArrayList<>();

		for (int i = 0, cityCountyCtr = 0; i < tiers.length && cityCountyCtr <= cityCounties.length; i++) {
			String smth = cityCountyCtr == cityCounties.length ? "" : cityCounties[cityCountyCtr];
			String tierRaw = tiers[i];
			if (compare(smth, tierRaw)) {
				if (cityCountyCtr != 0) {
					tiersList.add(tempTier);
					tempTier = new ArrayList<>();
				}
				cityCountyCtr++;
			} else {
				tempTier.add(Tier.extractTier(tierRaw));
			}
		}
		tiersList.add(tempTier);
		
		if (names.length != cities.length || cities.length != cityCounties.length || cities.length != tiersList.size()) {
			throw new IllegalArgumentException("length of inputs are not the same " + names.length + " " + cities.length + " " 
						+ " " + cityCounties.length + " " + tiersList.size());
		}
		
		List<Company> company = new ArrayList<>();
		for (int i = 0; i < cityCounties.length; i++) {
			String county = cityCounties[i].replace(cities[i], "");
			boolean isInLondon = cities[i].equalsIgnoreCase("london");
			List<Tier> tierOffering = tiersList.get(i);
			
			company.add(Company.builder()
					.name(names[i])
					.city(cities[i])
					.county(county)
					.inLondon(isInLondon)
					.tier(tierOffering)
					.build());
		}
		
		return company;
	}

	private boolean compare(String smth, String tierRaw) {
		if (tierRaw.length() > 13 && smth.length() > 13) {
			return tierRaw.substring(0, 13).equals(smth.substring(0, 13));
		}
		return tierRaw.equals(smth);
	}

}

