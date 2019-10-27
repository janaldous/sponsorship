package com.janaldous.sponsorship.random;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.janaldous.sponsorship.worker.SponsorshipThreadFactory;
import com.janaldous.sponsorship.worker.UncaughtExceptionHandler;

public class Stuff {

	private static final Logger log = LoggerFactory.getLogger(Stuff.class);
	
	@Test
	public void test() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new SponsorshipThreadFactory(new UncaughtExceptionHandler(new AtomicInteger(0))));
//		executorService.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//			
//			@Override
//			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//				log.error("execution rejected " + executor.getQueue());
//			}
//		});
		
		ScheduledFuture<?> scheduleAtFixedRate = executorService.scheduleAtFixedRate(() -> {
			log.error("exception about to throw");
			throw new RuntimeException("an exception");
		}, 0, 5, TimeUnit.SECONDS);
	}
	
	@Test
	public void test2() {
		ExecutorService executorService = Executors.newFixedThreadPool(2, new SponsorshipThreadFactory(new UncaughtExceptionHandler(new AtomicInteger(0))));
//		executorService.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//			
//			@Override
//			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//				log.error("execution rejected " + executor.getQueue());
//			}
//		});
		
		executorService.execute(() -> {
			log.error("exception about to throw");
			throw new RuntimeException("an exception");
		});
	}
	
}
