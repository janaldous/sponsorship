package com.janaldous.sponsorship.sponsor.repository;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CompanyResult {

	private boolean shouldCheckToday;

	/**
	 * True when the company house name and sponsor name are not equal (case ignored)
	 */
	private boolean possibleIncorrectLikeness;
	@NonNull
	private CompanyHouseCompany company;
	@NonNull
	private CheckingSponsor checking;
	
}
