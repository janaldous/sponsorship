package com.janaldous.sponsorship;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import opennlp.tools.tokenize.SimpleTokenizer;

@PropertySource("classpath:secrets.properties")
@Configuration
public class Config {
	
	@Value("${api_key}")
	private String apiKey;
	
	@Value("${gmail}")
	private String gmail;
	
	@Value("${gmail.password}")
	private String gmailPassword;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(apiKey, ""));
		
		return restTemplate;
	}
	
	@Bean
	@Qualifier("MyThreadPoolExecutor")
	public ThreadPoolExecutor executor() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		return executor;
	}
	
	@Bean
	public SimpleTokenizer tokenizer() {
		SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
		return tokenizer;
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername(gmail);
	    mailSender.setPassword(gmailPassword);
	     
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	     
	    return mailSender;
	}
	
}
