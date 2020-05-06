package com.janaldous.sponsorship.pdfparser;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.janaldous.sponsorship.datacollection.pdfparser.PdfDocumentParserException;
import com.janaldous.sponsorship.datacollection.pdfparser.SponsorPagesText;
import com.janaldous.sponsorship.datacollection.pdfparser.SponsorPdfReader;

public class SponsorPdfReaderTest {

	private static SponsorPdfReader sponsorPdfReader;
	private static String PATH = "src/test/resources/";
	private static String FILENAME = PATH + "2020-05-05_Tier_2_5_Register_of_Sponsors.pdf";
	private static Logger log = LoggerFactory.getLogger(SponsorPdfReaderTest.class);
	
	private static File file;
	
	@BeforeClass
	public static void beforeAll() throws InvalidPasswordException, IOException {
		log.debug("Starting to read document");
		file = new File(FILENAME);
		sponsorPdfReader = new SponsorPdfReader();
	}
	
	@Test
	public void testBeginsWithPartialContent() throws PdfDocumentParserException {
		SponsorPagesText result = sponsorPdfReader.read(file, 1, 2);
//		log.info(result.toString());
		assertThat(result.getNames(), startsWith("10 GROUP LTD"));
		assertThat(result.getTiers(), startsWith("Tier 2 GeneralTier 2 (A rating)"));
		assertThat(result.getTownCounties(), startsWith("LONDON"));
		
		assertThat(result.getNames(), containsString("1508 London Limited"));
	}
	
	@Test
	public void testBeginsAndEndsWithFullContent() throws PdfDocumentParserException {
		SponsorPagesText result = sponsorPdfReader.read(file, 2, 4);
		assertThat(result.getNames(), startsWith("15GIFTS LTD"));
		assertThat(result.getTiers(), startsWith("East SussexBrighton\nTier 2 GeneralTier 2 (A rating)"));
		assertThat(result.getTownCounties(), startsWith("East SussexBrighton"));
	}

}
