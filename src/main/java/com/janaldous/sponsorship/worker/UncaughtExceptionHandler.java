package com.janaldous.sponsorship.worker;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.NotFound;

public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	
	private static final Logger log = LoggerFactory
			.getLogger(SponsorshipThreadFactory.class);

	private AtomicInteger tooManyRequests;
	
	public UncaughtExceptionHandler(AtomicInteger tooManyRequests) {
		this.tooManyRequests = tooManyRequests;
	}
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		log.error("=========== Uncaught exception: " + e);
		if (e.getCause() instanceof NotFound) {
			log.error("Uncaught HttpClientErrorException exception: " + e);
			tooManyRequests.incrementAndGet();
		}
	}

}
