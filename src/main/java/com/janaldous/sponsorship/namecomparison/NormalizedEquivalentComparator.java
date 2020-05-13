package com.janaldous.sponsorship.namecomparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component
public class NormalizedEquivalentComparator implements INormalizedEquivalentComparator {

	@Autowired
	private NameNormalizer normalizer;
	
	@Override
	public boolean isEqual(@NonNull String a, @NonNull String b) {
		String normA = normalizer.normalize(a.trim());
		String normB = normalizer.normalize(b.trim());
		
		return normA.equalsIgnoreCase(normB);
	}

}
