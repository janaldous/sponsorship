package com.janaldous.sponsorship.worker;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.janaldous.sponsorship.companieshouse.ICompanyEntityService;
import com.janaldous.sponsorship.companieshouse.ICompanyHouseService;
import com.janaldous.sponsorship.sponsor.IRelevantSponsorService;
import com.janaldous.sponsorship.sponsor.RelevantSponsorRepository;

@Component
public class WorkerBean {

	private static final Logger log = LoggerFactory.getLogger(WorkerBean.class);

	@Autowired
	private ThreadPoolExecutor executor;

	@Autowired
	private ICompanyHouseService companiesHouseService;

	@Autowired
	private ICompanyEntityService companyEntityService;

	@Autowired
	private IRelevantSponsorService relevantSponsorService;

	@Autowired
	private RelevantSponsorRepository relevantSponsorRepository;

	/**
	 * Max rate given by Companies House API is 600 calls every 5 minutes
	 */
	public void start() {
		ScheduledExecutorService schedExecutor = Executors
				.newSingleThreadScheduledExecutor();

		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
		
		// if too many requests then int should be > 0
		AtomicInteger tooManyRequests = new AtomicInteger(0);
		
		Runnable mainCommand = () -> {
			try {
				while (true) {
					
					CountDownLatch finishedBatch = new CountDownLatch(1);
					schedExecutor.execute(new BatchGetSICCodeWorker(relevantSponsorService,
							relevantSponsorRepository, companiesHouseService,
							companyEntityService, tooManyRequests, finishedBatch));
					finishedBatch.await(1, TimeUnit.MINUTES);
					log.info("sleeping for 8 secs");
					Thread.sleep(TimeUnit.SECONDS.toMillis(8));
					
					log.info("too many requests = " + tooManyRequests.get());
					if (tooManyRequests.get() > 0) {
						// sleep if too many requests
						log.info("sleeping because of too many requests for 4 minutes");
						Thread.sleep(TimeUnit.MINUTES.toMillis(4));
						// reset
						tooManyRequests.set(0);
					}
				}
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
			}
		};

		mainExecutor.execute(mainCommand);

		try {
			mainExecutor.awaitTermination(30, TimeUnit.MINUTES);
			
			schedExecutor.shutdown();
			mainExecutor.shutdown();
			log.info("shut down main executor");
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}
}
