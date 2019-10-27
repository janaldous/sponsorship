package com.janaldous.sponsorship.sponsor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CompanyResult {

//	@NonNull
//	private String companiesHouseId;
//	@NonNull
//	private String companyHouseName;
//	@NonNull
//	private Long sponsorId;
//	@NonNull
//	private String sponsorName;
//	@NonNull
//	private String city;
//	private Set<SIC> sics;
//	private Set<Tier> tiers;
	@NonNull
	private CompanyHouseCompany company;
	private CheckingSponsor checking;
}
