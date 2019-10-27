package com.janaldous.sponsorship.sponsor.data;


public enum TierNum {

	TIER_2("Tier 2 (A rating)"), TIER_5("Tier 5 (A rating)");

	String value;

	TierNum(String value) {
		this.value = value;
	}

	public static TierNum toEnum(String tier) {
		switch (tier) {
		case "Tier 2 (A rating)":
			return TIER_2;
		case "Tier 5 (A rating)":
			return TIER_5;
		default:
			return null;
//			throw new IllegalArgumentException(tier);
		}
	}

}
