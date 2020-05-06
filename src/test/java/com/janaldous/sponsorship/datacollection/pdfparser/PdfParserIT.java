package com.janaldous.sponsorship.datacollection.pdfparser;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class PdfParserIT {

	private IPdfDocumentParser pdfParser;
	
	@Before
	public void beforeEach() {
		pdfParser = new PdfParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFileShouldBePdf() throws PdfDocumentParserException {
		File mockfile = new File("application.properties");
		
		pdfParser.parse(mockfile, 0, 0);
	}
	
	@Test
	public void testReadFile() throws PdfDocumentParserException {
		String root = "/Users/janaldoustorres/Documents/Code/Java/stuff2019";
		File file = new File(
				root
						+ "/code-kata/src/main/resources/2019-07-12_Tier_2_5_Register_of_Sponsors.pdf");
		
		pdfParser.parse(file, 0, 1);
	}

}
