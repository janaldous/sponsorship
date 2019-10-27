package com.janaldous.sponsorship.companieshouse;

import java.util.List;

import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.companieshouse.dto.Company;

public interface ICompanyHouseService {

	/**
	 * Returns the first company in the results or null.
	 * 
	 * @param companyName
	 * @return
	 */
	public Company searchCompany(String companyName);

	public List<SIC> getCompanySIC(String companyId);
	
	public List<SIC> getCompanySICByName(String companyName);

}