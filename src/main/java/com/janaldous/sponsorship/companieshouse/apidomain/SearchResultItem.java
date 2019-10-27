package com.janaldous.sponsorship.companieshouse.apidomain;

import lombok.Data;

@Data
public class SearchResultItem {
	
	private String company_number;
	private Address address;
	private String title;
	private Links links;
	
}
