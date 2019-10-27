package com.janaldous.sponsorship.pdfparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;

import com.janaldous.sponsorship.pdfparser.SponsorPdfReader.SponsorText;
import com.janaldous.sponsorship.sponsor.data.TierSub;

public class TextParserAdvancedReaderTest {

	@Test
	public void test() throws IOException {
		String root = "/Users/janaldoustorres/Documents/Code/Java/stuff2019";
		File file = new File(
				root
						+ "/code-kata/src/main/resources/2019-07-12_Tier_2_5_Register_of_Sponsors.pdf");

		List<Company> original = new ArrayList<>();
		try (PDDocument document = PDDocument.load(file)) {

			SponsorPdfReader reader = new SponsorPdfReader();
			SponsorText text = reader.read(document, 364, 366);

			TextParserAdvanced parser = new TextParserAdvanced();
			parser.setName(text.getNames());
			parser.setCity(text.getTowns());
			parser.setCityCounty(text.getTownCounties());
			parser.setTier(text.getTiers());
			original = parser.generateCompanies();
		}
		
		original.forEach(System.out::println);
		
		System.out.println("\nall" + original.size() + "\n");
		
		List<Company> incorrect = original.stream().filter(x -> {
			return !(!x.getTier().isEmpty() && x.getTier().stream().allMatch(y -> y != null));
		}).collect(Collectors.toList());
		
		incorrect.forEach(System.out::println);
		
		System.out.println("\nincorrect" + incorrect.size() + "\n");
		
		List<Company> filtered = original.stream().filter(x -> {
			return x.isInLondon() && (x.getTier().isEmpty() || x.getTier().stream().anyMatch(y -> y.getSubTier() == TierSub.GENERAL));
		}).collect(Collectors.toList());
		
		filtered.forEach(System.out::println);
		
		System.out.println("\nfiltered " + filtered.size() + "\n");
		
		assertTrue(original.size() > 16);
		
		Company company = original.get(0);
		Company companyLondon = original.get(6);
		
		assertEquals("Bethshan (North East)", company.getName());
		assertEquals("Washington", company.getCity());
		assertEquals("Tyne & Wear", company.getCounty());
		assertEquals(false, company.isInLondon());
		
		assertEquals("Betts Recruiting Limited", companyLondon.getName());
		assertEquals("London", companyLondon.getCity());
		assertEquals("", companyLondon.getCounty());
		assertEquals(true, companyLondon.isInLondon());
	}
}
