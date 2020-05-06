package com.janaldous.sponsorship.datacollection.pdfparser;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Component;

/**
 * Reads file as pdf using apache.pdfbox. Extracts text as SponsorPagesText object.
 * 
 * @author janaldoustorres
 *
 */
@Component
public class SponsorPdfReader implements IPDDocumentParser {

	@Override
	public SponsorPagesText read(File pdfFile, int pageFrom, int pageTo) throws PdfDocumentParserException {
		String extension = FilenameUtils.getExtension(pdfFile.getName());

		if (!extension.endsWith("pdf")) {
			throw new IllegalArgumentException();
		}

		try (PDDocument document = PDDocument.load(pdfFile)) {

			return read(document, pageFrom, pageTo);
		} catch (IOException e) {
			throw new PdfDocumentParserException(e);
		}
	}
	
	private SponsorPagesText read(PDDocument document, int pageFrom, int pageTo) throws IOException {
		
		StringBuffer names = new StringBuffer();
		StringBuffer towns = new StringBuffer();
		StringBuffer townCounties = new StringBuffer();
		StringBuffer tiers = new StringBuffer();

		for (int pageNo = pageFrom; pageNo <= pageTo; pageNo++) {
			PDFTextStripperByArea pdfStripper = new PDFTextStripperByArea();
			PDPage firstPage = document.getPage(pageNo);

			int top = 36;
			int height = 530;
			
			// get names and town
			Rectangle nameCol = new Rectangle(25, top, 325, height);
			pdfStripper.addRegion("nameCol", nameCol);

			Rectangle townCol = new Rectangle(350, top, 121, height);
			pdfStripper.addRegion("townCol", townCol);

			pdfStripper.extractRegions(firstPage);

			String nameColText = pdfStripper.getTextForRegion("nameCol");
			String townColText = pdfStripper.getTextForRegion("townCol");

			// get counties
			Rectangle townCountyCol = new Rectangle(350, top, 224, height);
			
			// to prevent overlap
			pdfStripper.removeRegion("townCol");
			pdfStripper.addRegion("townCountyCol", townCountyCol);
			pdfStripper.extractRegions(firstPage);
			
			String townCountyColText = pdfStripper
					.getTextForRegion("townCountyCol");

			// get counties
			Rectangle tierCols = new Rectangle(350, top, 475, height);

			// to prevent overlap
			pdfStripper.removeRegion("townCol");
			pdfStripper.removeRegion("townCountyCol");
			pdfStripper.addRegion("tierCols", tierCols);
			pdfStripper.extractRegions(firstPage);

			String tierColsText = pdfStripper.getTextForRegion("tierCols");
			
			names.append(nameColText);
			towns.append(townColText);
			townCounties.append(townCountyColText);
			tiers.append(tierColsText);
		}

		SponsorPagesText sponsorText = new SponsorPagesText();
		sponsorText.setNames(names.toString());
		sponsorText.setTowns(towns.toString());
		sponsorText.setTownCounties(townCounties.toString());
		sponsorText.setTiers(tiers.toString());

		return sponsorText;
	}

}
