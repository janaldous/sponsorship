package com.janaldous.sponsorship.pdfparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.janaldous.sponsorship.datacollection.pdfparser.PdfDocumentParserException;
import com.janaldous.sponsorship.datacollection.pdfparser.PdfVisaSponsor;
import com.janaldous.sponsorship.datacollection.pdfparser.SponsorPagesText;
import com.janaldous.sponsorship.datacollection.pdfparser.SponsorPagesTextParser;
import com.janaldous.sponsorship.datacollection.pdfparser.SponsorPdfReader;
import com.janaldous.sponsorship.sponsor.data.TierSub;

public class TextParserAdvancedReaderTest {
	
	private String root = "/Users/janaldoustorres/Documents/Code/Java/stuff2019";
	private File file = new File(
			root
			+ "/code-kata/src/main/resources/2019-07-12_Tier_2_5_Register_of_Sponsors.pdf");

	private SponsorPdfReader reader = new SponsorPdfReader();
	private SponsorPagesTextParser parser = new SponsorPagesTextParser();
	
	@Test
	public void test() throws IOException, PdfDocumentParserException {

		List<PdfVisaSponsor> original = new ArrayList<>();

		SponsorPagesText text = reader.read(file, 364, 366);

		original = parser.extractCompanies(text);
		
		original.forEach(System.out::println);
		
		System.out.println("\nall" + original.size() + "\n");
		
		List<PdfVisaSponsor> incorrect = original.stream().filter(x -> {
			return !(!x.getTier().isEmpty() && x.getTier().stream().allMatch(y -> y != null));
		}).collect(Collectors.toList());
		
		incorrect.forEach(System.out::println);
		
		System.out.println("\nincorrect" + incorrect.size() + "\n");
		
		List<PdfVisaSponsor> filtered = original.stream().filter(x -> {
			return x.isInLondon() && (x.getTier().isEmpty() || x.getTier().stream().anyMatch(y -> y.getSubTier() == TierSub.GENERAL));
		}).collect(Collectors.toList());
		
		filtered.forEach(System.out::println);
		
		System.out.println("\nfiltered " + filtered.size() + "\n");
		
		assertTrue(original.size() > 16);
		
		PdfVisaSponsor company = original.get(0);
		PdfVisaSponsor companyLondon = original.get(6);
		
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
