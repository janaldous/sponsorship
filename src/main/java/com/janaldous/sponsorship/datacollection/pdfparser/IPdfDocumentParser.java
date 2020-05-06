package com.janaldous.sponsorship.datacollection.pdfparser;

import java.io.File;
import java.util.List;

public interface IPdfDocumentParser {

	List<PdfVisaSponsor> parse(File file, int pageFrom, int pageTo) throws PdfDocumentParserException;
	
}
