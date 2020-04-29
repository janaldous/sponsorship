package com.janaldous.sponsorship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.janaldous.sponsorship.worker.WorkerBean;

@SpringBootApplication
public class SponorshipApplication {

	private static final Logger log = LoggerFactory.getLogger(SponorshipApplication.class);

//	@Autowired
//	private WorkerBean worker;
	
	public static void main(String[] args) {
		SpringApplication.run(SponorshipApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		if (args.length > 0 && args[0].equals("fetch")) {
//			log.info("starting workerbean");
//			worker.start();
//		}
//	}
	
}
