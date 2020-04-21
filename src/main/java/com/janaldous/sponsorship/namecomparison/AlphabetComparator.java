package com.janaldous.sponsorship.namecomparison;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Qualifier("alphabetComparator")
@Component
public class AlphabetComparator implements NameComparator {

	@Autowired
	private NameNormalizer normalizer;
	
	@Override
	public int calculateLikeness(String name1, String name2) {
		// tokenize
		// normalize? (limited/ltd)
		// compare same tokens
		// calculate score
		
		int[] alphabet1 = new int[128];
		int[] alphabet2 = new int[128];
		
		Arrays.fill(alphabet1, 0);
		Arrays.fill(alphabet2, 0);
		
		
		String normalizedName1 = normalizer.normalize(name1);
		String normalizedName2 = normalizer.normalize(name2);
		
		for (char c: normalizedName1.toCharArray()) {
			if ((int) c < alphabet1.length) {
				alphabet1[c]++;
			}
		}
		
		for (char c: normalizedName2.toCharArray()) {
			if ((int) c < alphabet1.length) {
				alphabet2[c]++;
			}
		}
		
		int score = 0;
		
		for (int i = 0; i < alphabet2.length; i++) {
			int difference = Math.abs(alphabet1[i] - alphabet2[i]);
			score += difference;
		}
		
		return score;
	}

}
