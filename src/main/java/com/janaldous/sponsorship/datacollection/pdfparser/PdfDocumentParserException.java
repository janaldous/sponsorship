package com.janaldous.sponsorship.datacollection.pdfparser;

import java.io.IOException;

public class PdfDocumentParserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8010980717332803343L;

	public PdfDocumentParserException(IOException e) {
		super(e);
	}
}
