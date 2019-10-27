package com.janaldous.sponsorship.textparser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class MyCSVReader {
	
	private static final int MAX_REQ_RATE = 10;//600
	private long finishedItems = 0;

	public List<CompanySponsor> read(String filename) throws IOException {
		try (CSVReader reader = new CSVReader(new FileReader(filename))) {
			String[] nextLine;
			List<CompanySponsor> output = new ArrayList<>();
			while ((nextLine = reader.readNext()) != null) {
				CompanySponsor companySponsor = CompanySponsor.builder()
						.name(nextLine[0])
						.isInLondon(Boolean.valueOf(nextLine[1]))
						.tier2(nextLine[2].contains("Tier2")).build();
				output.add(companySponsor);
			}
			return output;
		}
	}

	public List<String> getNextBatch(String filename) throws IOException {
		List<CompanySponsor> companies = read(filename);
		List<String> sponsorsBatch = companies.stream()
				.skip(finishedItems*MAX_REQ_RATE)
				.limit(MAX_REQ_RATE)
				.map(x -> x.getName())
				.collect(Collectors.toList());
		finishedItems++;
		return sponsorsBatch;
	}

}
