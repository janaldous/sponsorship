package com.janaldous.sponsorship.pdfparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaldous.sponsorship.pdfparser.SponsorPdfReader.SponsorText;
import com.janaldous.sponsorship.sponsor.SponsorService;

@Service
public class ReadSponsorsWork {
	
	@Autowired
	private SponsorService sponsorService;

	public void read(int pageFrom, int pageTo) throws IOException {
		String root = "/Users/janaldoustorres/Documents/Code/Java/stuff2019";
		File file = new File(
				root
						+ "/code-kata/src/main/resources/2019-07-12_Tier_2_5_Register_of_Sponsors.pdf");

		List<Company> original = new ArrayList<>();
		try (PDDocument document = PDDocument.load(file)) {

			SponsorPdfReader reader = new SponsorPdfReader();
			SponsorText text = reader.read(document, pageFrom, pageTo);

			TextParserAdvanced parser = new TextParserAdvanced();
			parser.setName(text.getNames());
			parser.setCity(text.getTowns());
			parser.setCityCounty(text.getTownCounties());
			parser.setTier(text.getTiers());
			original = parser.generateCompanies();
			
			for (Company c: original) {
//				sponsorService.createSponsor(c.getName(), c.getCity(), c.getCounty(), 
//						"london".equalsIgnoreCase(c.getCity()), c.getTier());
			}
		}
	}
	
}
