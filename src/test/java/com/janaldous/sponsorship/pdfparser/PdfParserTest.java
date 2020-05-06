package com.janaldous.sponsorship.pdfparser;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.datacollection.pdfparser.PdfDocumentParserException;
import com.janaldous.sponsorship.datacollection.pdfparser.PdfParser;
import com.janaldous.sponsorship.datacollection.pdfparser.PdfVisaSponsor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfParserTest {

	private final Logger log = LoggerFactory.getLogger(PdfParserTest.class);
	
	private static String PATH = "src/test/resources/";
	private static String FILENAME = PATH + "2020-05-05_Tier_2_5_Register_of_Sponsors.pdf";

	@Autowired
	private PdfParser pdfDocumentParser;
	
	@Test
	public void test() throws PdfDocumentParserException {
		File file = new File(FILENAME);
		
		int interval = 1;
		for (int i = 1992; i <= 1998; i += interval) {
			List<PdfVisaSponsor> output = pdfDocumentParser.parse(file, i, i);
			output.forEach(x -> log.info(x.toString()));
		}
	}
	
}
