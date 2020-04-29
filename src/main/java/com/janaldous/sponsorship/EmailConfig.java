package com.janaldous.sponsorship;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.janaldous.sponsorship.application.domain.EmailProperties;

@Configuration
public class EmailConfig {

	private final static Logger log = LoggerFactory.getLogger(EmailConfig.class);

	@Value("${email.service.attachmentname}")
	private String attachmentFilename;

	@Value("${email.service.cvpath}")
	private String pathToAttachment;

	@Value("${email.service.emailtemplate}")
	private String pathToEmailTemplate;

	@Bean
	public EmailProperties emailProperties() throws URISyntaxException, IOException {
		return EmailProperties.builder().attachmentFilename(attachmentFilename).attachment(new File(pathToAttachment))
				.emailTemplate(readFileToString(pathToEmailTemplate)).build();
	}

	private String readFileToString(String filename) throws URISyntaxException, IOException {
		Objects.requireNonNull(filename, "filename should not be null");
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource(filename).toURI());

			Stream<String> lines = Files.lines(path);
			String data = lines.collect(Collectors.joining("\n"));
			lines.close();

			return data;
		} catch (URISyntaxException | IOException e) {
			log.error("filename is invalid: " + filename, e);
			throw new RuntimeException(e);
		}
	}
}
