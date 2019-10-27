package com.janaldous.sponsorship.companieshouse;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.janaldous.sponsorship.companieshouse.apidomain.CompanyProfile;
import com.janaldous.sponsorship.companieshouse.apidomain.SearchResult;
import com.janaldous.sponsorship.companieshouse.apidomain.SearchResultItem;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.companieshouse.data.SICRepository;
import com.janaldous.sponsorship.companieshouse.dto.Company;
import com.janaldous.sponsorship.namecomparison.NameComparator;

@Service
public class CompaniesHouseService implements ICompanyHouseService {

	private static final Logger log = LoggerFactory
			.getLogger(CompaniesHouseService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SICRepository sicRepository;

	@Autowired
	@Qualifier("alphabetComparator")
	private NameComparator comparator;

	public final static int LIKENESS_THRESHOLD = 5;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.janaldous.sponorship.companieshouse.ICompanyHouseService#searchCompanyId
	 * (java.lang.String)
	 */
	@Override
	public Company searchCompany(String companyName) {
		String searchableName = companyName;// TextParser.toSearchableName(companyName);
		SearchResult result = restTemplate.getForObject(
				"https://api.companieshouse.gov.uk/search/companies?q="
						+ searchableName, SearchResult.class);
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
			log.info("request [" + searchableName + "]: result size = "
					+ result.getItems().size() + " likeness = " + minLikeness);

			return Company.builder().id(output.getCompany_number())
					.name(output.getTitle()).build();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.janaldous.sponorship.companieshouse.ICompanyHouseService#getCompanySIC
	 * (java.lang.String)
	 */
	@Override
	public List<SIC> getCompanySIC(String companyId) {
		List<SIC> sics = new ArrayList<>();
		CompanyProfile companyProfile = restTemplate.getForObject(
				"https://api.companieshouse.gov.uk/company/" + companyId,
				CompanyProfile.class);
		if (companyProfile != null && !companyProfile.getSic_codes().isEmpty()) {
			companyProfile.getSic_codes().stream().forEach(sicCode -> {
				sics.add(sicRepository.findByCode(sicCode).get(0));
			});
		}
		return sics;
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
