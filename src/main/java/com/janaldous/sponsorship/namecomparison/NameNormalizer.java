package com.janaldous.sponsorship.namecomparison;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import opennlp.tools.tokenize.SimpleTokenizer;

import org.springframework.stereotype.Component;

@Component
public class NameNormalizer {

	private final static SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
	
	public String normalize(String str) {
		str = str.toLowerCase().replace("ltd.", "ltd").replace("(", "").replace(")", "").replaceAll(",", "");

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
