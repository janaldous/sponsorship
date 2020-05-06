package com.janaldous.sponsorship.companieshouse;

import java.util.List;

import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.companieshouse.dto.Company;

public interface ICompanyHouseFacade {

	int LIKENESS_THRESHOLD = 5;

	Company searchCompany(String companyName);

	List<SIC> getCompanySIC(String companyId);

	List<SIC> getCompanySICByName(String companyName);

}