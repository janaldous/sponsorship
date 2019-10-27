package com.janaldous.sponsorship.companieshouse.apidomain;

import java.util.List;

import lombok.Data;

@Data
public class SearchResult {

	private String kind;
	private String page_number;
	private List<SearchResultItem> items;
	
}
