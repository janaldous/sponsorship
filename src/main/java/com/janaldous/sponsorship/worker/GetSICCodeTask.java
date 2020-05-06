package com.janaldous.sponsorship.worker;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;

import com.janaldous.sponsorship.companieshouse.ICompanyEntityService;
import com.janaldous.sponsorship.companieshouse.ICompanyHouseAPI;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.companieshouse.dto.Company;
import com.janaldous.sponsorship.namecomparison.AlphabetComparator;
import com.janaldous.sponsorship.namecomparison.NameComparator;
import com.janaldous.sponsorship.sponsor.RelevantSponsorRepository;
import com.janaldous.sponsorship.sponsor.data.ProcessStatus;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;

@RequiredArgsConstructor
public class GetSICCodeTask implements Runnable {

	private static final Logger log = LoggerFactory
			.getLogger(GetSICCodeTask.class);

	@NonNull
	private ICompanyHouseAPI companiesHouseService;
	@NonNull
	private ICompanyEntityService companyHouseCompanyService;
	@NonNull
	private RelevantSponsorRepository relevantSponsorRepository;
	@NonNull
	private RelevantSponsor relevantSponsor;
	@NonNull
	private CountDownLatch countDownLatch;
	private NameComparator nameComparator = new AlphabetComparator();

	@Override
	public void run() {
		// search
		String companyName = relevantSponsor.getSponsor().getName();
		log.debug("starting search: " + companyName);
		try {
			Company company = companiesHouseService.searchCompany(companyName);
			if (company == null || company.getId() == null) {
				saveRelevantSponsor(ProcessStatus.NOT_FOUND, -1);
				countDownLatch.countDown();
				throw new RuntimeException("no results for " + companyName);
			}
			int likeness = nameComparator.calculateLikeness(companyName,
					company.getName());

			List<SIC> sics = companiesHouseService.getCompanySIC(company
					.getId());
			log.debug("result of search: " + sics.toString());

			countDownLatch.countDown();

			if (likeness > 5) {
				saveRelevantSponsor(ProcessStatus.FAILED_LIKENESS, likeness);
				log.warn("likeness threshold breeched " + likeness);
			} else {
				saveRelevantSponsor(ProcessStatus.SUCCESS, likeness);
			}
			// save
			companyHouseCompanyService.createCompany(company.getId(),
					company.getName(), relevantSponsor.getSponsor(), sics);

		} catch (HttpClientErrorException e) {
			saveRelevantSponsor(ProcessStatus.TOO_MANY_REQUESTS, -1);
			throw new RuntimeException(e);
		}
	}

	private void saveRelevantSponsor(ProcessStatus status, int likeness) {
		relevantSponsor.setStatus(status);
		relevantSponsor.setProcessed(true);
		relevantSponsor.setLikeness(likeness);
		relevantSponsorRepository.save(relevantSponsor);
	}

}
