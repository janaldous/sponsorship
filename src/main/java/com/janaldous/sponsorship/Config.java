package com.janaldous.sponsorship;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import liquibase.integration.spring.SpringLiquibase;
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

	@Autowired
	private Environment env;

	@Qualifier("companiesHouse")
	@Bean
	public RestTemplate companiesHouseRestTemplate(RestTemplateBuilder builder) {
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

	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:liquibase-changeLog.yml");
		liquibase.setDataSource(dataSource());
		return liquibase;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.jpa.properties.hibernate.dialect"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

}
