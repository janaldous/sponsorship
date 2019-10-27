package com.janaldous.sponsorship.sponsor.data;

import java.util.Arrays;
import java.util.List;

public enum TierSub {
	RELIGIOUS("Religious Workers"), VOLUNETEER("Voluntary Workers"), GENERAL(
			"Tier 2 General"), ICT("Intra Company Transfers (ICT)"), CREATIVE_SPORTING(
			"Creative & Sporting"), EXCHANGE("Exchange"), INTL_AGREEMENTS("International Agreements"),
			SPORT("Sport"), OTHER("");

	public static List<String> subTiers = Arrays.asList("Religious Workers", 
			"Voluntary Workers", "Tier 2 General", "Intra Company Transfers (ICT)",
			"Creative & Sporting", "Exchange", "International Agreements", "Sport");
	
	String value;

	TierSub(String value) {
		this.value = value;
	}

	public static TierSub toEnum(String tier) {
		switch (tier) {
		case "Religious Workers":
			return RELIGIOUS;
		case "Voluntary Workers":
			return VOLUNETEER;
		case "Tier 2 General":
			return GENERAL;
		case "Intra Company Transfers (ICT)":
			return ICT;
		case "Creative & Sporting":
			return CREATIVE_SPORTING;
		case "Exchange":
			return EXCHANGE;
		case "International Agreements":
			return INTL_AGREEMENTS;
		case "Sport":
			return SPORT;
		default:
			return OTHER;
//			throw new IllegalArgumentException(tier);
		}
	}
}
