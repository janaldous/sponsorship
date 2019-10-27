package com.janaldous.sponsorship.companieshouse.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SICUpload {

	private static final Logger log = LoggerFactory.getLogger(SICUpload.class);
	
	@Autowired
	private SICRepository sicRepository;
	
	@Autowired
	private ObjectMapper mapper;
		
	public void something() {
		try {
			List<Thing> sics = mapper.readValue(new File("/Users/janaldoustorres/Documents/Code/Java/stuff2019/sponorship/src/main/resources/sic.json"), mapper.getTypeFactory().constructCollectionType(List.class, Thing.class));
			log.info("size " + sics.size());
			List<Thing> sicsAfter = sics.stream().filter(x -> !x.getKey().contains("Section")).collect(Collectors.toList());
			log.info("size " + sicsAfter.size());
			List<SIC> sicsEntities = new ArrayList<>();
			
			sicsAfter.stream().forEach(x ->
				sicsEntities.add(SIC.builder().code(x.getKey()).description(x.getValue()).build()));
			
			sicRepository.saveAll(sicsEntities);
			
		} catch (IOException e) {
			log.error("sics", e);
		}
	}

}
