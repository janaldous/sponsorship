package com.janaldous.sponsorship.companieshouse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.sponsor.data.Sponsor;

@Service
public class CompanyEntityService implements ICompanyEntityService {

	@Autowired
	private ICompanyHouseCompanyRepository repository;

	@Override
	public void createCompany(String id, String name, Sponsor sponsor,
			List<SIC> sics) {
		Set<SIC> sic = new HashSet<>(sics);
		CompanyHouseCompany chCompany = CompanyHouseCompany.builder()
				.companiesHouseId(id)
				.companyHouseName(name)
				.sponsor(sponsor)
				.tier2(true)
				.inLondon(true)
				.sic(sic)
				.build();
		repository.save(chCompany);
	}
	
}
