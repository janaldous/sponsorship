package com.janaldous.sponsorship.companieshouse;

import java.util.List;

import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.sponsor.data.Sponsor;

public interface ICompanyEntityService {

	void createCompany(String id, String name, Sponsor sponsor,
			List<SIC> sics);

}