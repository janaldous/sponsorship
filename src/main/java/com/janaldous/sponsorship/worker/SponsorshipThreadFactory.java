package com.janaldous.sponsorship.worker;

import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SponsorshipThreadFactory implements ThreadFactory {
	private static final Logger log = LoggerFactory
			.getLogger(SponsorshipThreadFactory.class);

	private int threadId;
	private String name;
	private UncaughtExceptionHandler exceptionHandler;

	public SponsorshipThreadFactory(UncaughtExceptionHandler eh) {
		this.exceptionHandler = eh;
		threadId = 1;
		this.name = "sponsorship";
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + "-thread-" + threadId);
		t.setUncaughtExceptionHandler(exceptionHandler);
		log.info("created new thread with id : " + threadId + " and name : "
				+ t.getName());
		threadId++;
		return t;
	}
}
