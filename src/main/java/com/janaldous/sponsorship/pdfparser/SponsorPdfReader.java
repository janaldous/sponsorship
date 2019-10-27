package com.janaldous.sponsorship.pdfparser;

import java.awt.Rectangle;
import java.io.IOException;

import lombok.Data;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class SponsorPdfReader {

	@Data
	public class SponsorText {
		private String names;
		private String towns;
		private String townCounties;
		private String tiers;
	}

	public SponsorText read(PDDocument document, int pageFrom, int pageTo)
			throws IOException {
		
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

		SponsorText sponsorText = new SponsorText();
		sponsorText.setNames(names.toString());
		sponsorText.setTowns(towns.toString());
		sponsorText.setTownCounties(townCounties.toString());
		sponsorText.setTiers(tiers.toString());

		return sponsorText;
	}

}
