package com.janaldous.sponsorship.companieshouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.janaldous.sponsorship.companieshouse.apidomain.CompanyProfile;
import com.janaldous.sponsorship.companieshouse.apidomain.SearchResult;
import com.janaldous.sponsorship.webfacade.exception.ResourceNotFoundException;

@Service
public class CompanyHouseAPI implements ICompanyHouseAPI {

	@Qualifier("companiesHouse")
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public SearchResult searchCompany(String searchableName) {
		SearchResult result = restTemplate.getForObject(
				"https://api.companieshouse.gov.uk/search/companies?q="
						+ searchableName, SearchResult.class);
		if (result != null) {
			return result;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<String> getCompanySIC(String companyId) {
		CompanyProfile companyProfile = restTemplate.getForObject(
				"https://api.companieshouse.gov.uk/company/" + companyId,
				CompanyProfile.class);
		return companyProfile.getSic_codes();
	}

}
