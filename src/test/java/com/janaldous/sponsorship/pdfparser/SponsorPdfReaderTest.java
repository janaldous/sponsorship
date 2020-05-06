package com.janaldous.sponsorship.pdfparser;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.janaldous.sponsorship.datacollection.pdfparser.SponsorPdfReader;
import com.janaldous.sponsorship.datacollection.pdfparser.SponsorPagesText;

public class SponsorPdfReaderTest {

	private SponsorPdfReader sponsorPdfReader;
	private static String PATH = "src/test/resources/";
	private static String FILENAME = PATH + "2020-05-05_Tier_2_5_Register_of_Sponsors.pdf";
	private static Logger log = LoggerFactory.getLogger(SponsorPdfReaderTest.class);
	
	private static File file;
	private static PDDocument document;
	
	@BeforeClass
	public static void beforeAll() throws InvalidPasswordException, IOException {
		log.debug("Starting to read document");
		file = new File(FILENAME);
		document = PDDocument.load(file);
	}
	
	@AfterClass
	public static void afterAll() throws IOException {
		log.debug("Closing pdf document");
		document.close();
	}
	
	@Before
	public void beforeEach() {
		sponsorPdfReader = new SponsorPdfReader();
	}
	
	@Test
	public void testBeginsWithPartialContent() throws InvalidPasswordException, IOException {
		SponsorPagesText result = sponsorPdfReader.read(document, 1, 2);
//		log.info(result.toString());
		assertThat(result.getNames(), startsWith("10 GROUP LTD"));
		assertThat(result.getTiers(), startsWith("Tier 2 GeneralTier 2 (A rating)"));
		assertThat(result.getTownCounties(), startsWith("LONDON"));
		
		assertThat(result.getNames(), containsString("1508 London Limited"));
	}
	
	@Test
	public void testBeginsAndEndsWithFullContent() throws InvalidPasswordException, IOException {
		SponsorPagesText result = sponsorPdfReader.read(document, 2, 4);
		assertThat(result.getNames(), startsWith("15GIFTS LTD"));
		assertThat(result.getTiers(), startsWith("Tier 2 GeneralTier 2 (A rating)"));
		assertThat(result.getTownCounties(), startsWith("LONDON"));
		
		assertThat(result.getNames(), containsString("1508 London Limited"));
	}

}