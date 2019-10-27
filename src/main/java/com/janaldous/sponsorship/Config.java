package com.janaldous.sponsorship;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.persistence.EntityManagerFactory;

import opennlp.tools.tokenize.SimpleTokenizer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	
	@Value("${api_key:235dTXUILwMbp2PcLyfIyKs_tBf9aHiB7uEQRSDn}")
	private String apiKey;
	
//	@Autowired
//	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(apiKey, ""));
		
		return restTemplate;
	}
	
	@Bean
	public ThreadPoolExecutor executor() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		return executor;
	}
	
	@Bean
	public SimpleTokenizer tokenizer() {
		SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
		return tokenizer;
	}
	
//	@Bean
//	public SessionFactory getSessionFactory() {
//	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//	        throw new NullPointerException("factory is not a hibernate factory");
//	    }
//	    return entityManagerFactory.unwrap(SessionFactory.class);
//	}
	
}
