package com.janaldous.sponsorship.datacollection.pdfparser;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PdfParser implements IPdfDocumentParser {

	@Autowired
	private IPDDocumentParser pdfPageParser;

	@Autowired
	private ISponsorPagesTextParser textParser;

	@Override
	public List<PdfVisaSponsor> parse(File pdfFile, int pageFrom, int pageTo) throws PdfDocumentParserException {

		SponsorPagesText spt = pdfPageParser.read(pdfFile, pageFrom, pageTo);

		return textParser.extractCompanies(spt);
	}

}
