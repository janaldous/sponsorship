package com.janaldous.sponsorship.companieshouse;

import java.util.List;

import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.companieshouse.dto.Company;

/**
 * Defines some services provided by Company House API.
 * <a href="https://developer.companieshouse.gov.uk/api/docs/">https://developer.companieshouse.gov.uk/api/docs/</a>
 * 
 * @author janaldoustorres
 *
 */
public interface ICompanyHouseAPI {

	/**
	 * Searches company by name
	 * 
	 * @param companyName Company name
	 * @return first company in results or null
	 */
	public Company searchCompany(String companyName);

	/**
	 * Gets company's SIC by company Id
	 * 
	 * @param companyId Company id
	 * @return list of SIC of the company
	 */
	public List<SIC> getCompanySIC(String companyId);
	
	/**
	 * Gets company's SIC by company name
	 * 
	 * @param companyName
	 * @return
	 */
	public List<SIC> getCompanySICByName(String companyName);

}