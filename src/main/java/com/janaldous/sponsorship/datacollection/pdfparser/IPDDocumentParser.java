package com.janaldous.sponsorship.datacollection.pdfparser;

import java.io.File;

interface IPDDocumentParser {

	SponsorPagesText read(File pdfFile, int pageFrom, int pageTo) throws PdfDocumentParserException;
	
}
