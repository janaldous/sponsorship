package com.janaldous.sponsorship.companieshouse;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.janaldous.sponsorship.companieshouse.apidomain.SearchResult;
import com.janaldous.sponsorship.companieshouse.apidomain.SearchResultItem;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.companieshouse.data.SICRepository;
import com.janaldous.sponsorship.companieshouse.dto.Company;
import com.janaldous.sponsorship.namecomparison.NameComparator;

@Service
public class CompanyHouseFacade implements ICompanyHouseFacade {

	private static final Logger log = LoggerFactory
			.getLogger(CompanyHouseFacade.class);

	@Autowired
	private SICRepository sicRepository;

	@Autowired
	private ICompanyHouseAPI companyHouseAPI;

	@Autowired
	@Qualifier("alphabetComparator")
	private NameComparator comparator;

	@Override
	public Company searchCompany(String companyName) {
		SearchResult result = companyHouseAPI.searchCompany(companyName);
		if (!result.getItems().isEmpty()) {
			SearchResultItem output = result.getItems().get(0);
			int minLikeness = Integer.MAX_VALUE;
			for (SearchResultItem sri : result.getItems()) {
				int curLikeness = comparator.calculateLikeness(sri.getTitle(), companyName);
				boolean inLondon = sri.getAddress() != null && "London".equalsIgnoreCase(sri.getAddress().getLocality());
				//				log.debug("===> likeness = " + curLikeness + " inLondon = " + inLondon + " result [" + sri.getTitle() + "]:");
				if (curLikeness < minLikeness && inLondon) {
					minLikeness = curLikeness;
					output = sri;
				}
			}
			log.info("request [" + companyName + "]: result size = "
					+ result.getItems().size() + " likeness = " + minLikeness);

			return Company.builder().id(output.getCompany_number())
					.name(output.getTitle()).build();
		}
		return null;
	}

	@Override
	public List<SIC> getCompanySIC(String companyId) {
		List<String> sicsStr = companyHouseAPI.getCompanySIC(companyId);
		return sicsStr.stream()
				.map(sicCode -> sicRepository.findByCode(sicCode).get(0))
				.collect(Collectors.toList());
	}

	@Override
	public List<SIC> getCompanySICByName(String companyName) {
		Company company = searchCompany(companyName);
		if (company == null) {
			return null;
		}
		return getCompanySIC(company.getId());
	}

}
