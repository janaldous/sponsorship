package com.janaldous.sponsorship.sponsor.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tier {
	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private TierNum tier;
	@Enumerated(EnumType.STRING)
	private TierSub subTier;
	
	public static Tier extractTier(String tierRaw) {
		String subTier = "";
		for (String st: TierSub.subTiers) {
			if (tierRaw.contains(st)) {
				subTier = st;
				break;
			}
		}
		tierRaw = tierRaw.replace(subTier, "");
		
		return Tier.builder()
				.tier(TierNum.toEnum(tierRaw))
				.subTier(TierSub.toEnum(subTier))
				.build();
	}
}