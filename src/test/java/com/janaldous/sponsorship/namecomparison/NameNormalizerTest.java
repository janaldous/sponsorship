package com.janaldous.sponsorship.namecomparison;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NameNormalizerTest {

	@TestConfiguration
    static class ContextConfiguration {
  
        @Bean
        public NameNormalizer nameNormalizer() {
            return new NameNormalizer();
        }
    }
	
	@Autowired
	private NameNormalizer nameNormalizer;
	
	@Test
	public void testNormalizationWithSpecialCharacters_parenthesis() {
		String normalized = nameNormalizer.normalize("ENDAVA (UK) LIMITED");
		assertEquals("ENDAVAUKLTD", normalized);
	}
	
	@Test
	public void testNormalizationWithSpecialCharacters_punctuation() {
		String normalized = nameNormalizer.normalize("EPIQ EUROPE, LTD.");
		assertEquals("EPIQEUROPELTD", normalized);
	}
	
}
