package com.janaldous.sponsorship.worker;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.janaldous.sponsorship.companieshouse.ICompanyEntityService;
import com.janaldous.sponsorship.companieshouse.ICompanyHouseService;
import com.janaldous.sponsorship.sponsor.IRelevantSponsorService;
import com.janaldous.sponsorship.sponsor.RelevantSponsorRepository;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;

@AllArgsConstructor
public class BatchGetSICCodeWorker implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(BatchGetSICCodeWorker.class);

	private IRelevantSponsorService relevantSponsorService;
	private RelevantSponsorRepository relevantSponsorRepository;
	private ICompanyHouseService companiesHouseService;
	private ICompanyEntityService companyEntityService;
	private AtomicInteger tooManyRequests;
	private CountDownLatch finishedBatch;

	@Override
	public void run() {
		try {
			List<RelevantSponsor> companies = relevantSponsorService.getNextBatch(10);
			
			CountDownLatch countDownLatch = new CountDownLatch(companies.size());

			UncaughtExceptionHandler eh = new UncaughtExceptionHandler(tooManyRequests);
			SponsorshipThreadFactory factory = new SponsorshipThreadFactory(eh);
			
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4, factory);
			log.info("pool size " + executor.getPoolSize());
			List<GetSICCodeTask> tasks = companies.stream().map(companyName -> {
				return new GetSICCodeTask(companiesHouseService, companyEntityService, 
						relevantSponsorRepository, companyName, countDownLatch);
			}).collect(Collectors.toList());
			
			tasks.stream().forEach(x -> executor.execute(x));
			
			countDownLatch.await(10, TimeUnit.SECONDS);
			log.info("shutting down executor");
			executor.shutdown();
			
			finishedBatch.countDown();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
