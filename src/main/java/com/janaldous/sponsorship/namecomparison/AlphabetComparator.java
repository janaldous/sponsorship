package com.janaldous.sponsorship.namecomparison;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import opennlp.tools.tokenize.SimpleTokenizer;

@Primary
@Qualifier("alphabetComparator")
@Component
public class AlphabetComparator implements NameComparator {

	private final static SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
	
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
		
		
		String normalizedName1 = normalize(name1);
		String normalizedName2 = normalize(name2);
		
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

	private String normalize(String str) {
		str = str.toLowerCase().replace("ltd.", "ltd");

		List<String> tokens = Arrays.asList(tokenizer.tokenize(str.toUpperCase()));

		// limited -> ltd || ltd. -> ltd
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			if ("LIMITED".equals(token) || "LTD.".equals(token)) {
				token = "LTD";
				tokens.set(i, token);
			}
		}
		
		String output = tokens.stream().collect(Collectors.joining(""));
		// limited liability partnership -> llp
		output = output.replace("LTDLIABILITYPARTNERSHIP", "LLP");
		output = output.replace("PUBLICLTDCOMPANY", "PLC");
		
		return output;
	}

}
