package com.janaldous.sponsorship.datacollection.pdfparser;

import java.util.List;

public interface ISponsorPagesTextParser {
	
	List<PdfVisaSponsor> extractCompanies(SponsorPagesText spt);

}