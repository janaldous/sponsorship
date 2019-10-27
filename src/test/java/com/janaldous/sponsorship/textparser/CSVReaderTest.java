package com.janaldous.sponsorship.textparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVReaderTest {
	private static final Logger log = LoggerFactory.getLogger(CSVReaderTest.class);
	
	private String filename = "/Users/janaldoustorres/Documents/Code/Java/stuff2019/sponorship/src/main/resources/12-Oct-companies - 2.csv";
	private String filename2 = "/Users/janaldoustorres/Documents/Code/Java/stuff2019/sponorship/src/main/resources/12-Oct-companies - 3.csv";
	
	@Test
	public void test() throws IOException {
		MyCSVReader csvReader = new MyCSVReader();
		List<CompanySponsor> sponsors = csvReader.read(filename);
		assertTrue(!sponsors.isEmpty());
		assertEquals(391, sponsors.size());
	}
	
	@Test
	public void test2() throws FileNotFoundException, IOException {
		MyCSVReader csvReader = new MyCSVReader();
		List<String> sponsors = csvReader.getNextBatch(filename);
		assertTrue(!sponsors.isEmpty());
		assertEquals(391, sponsors.size());
	}
	
	@Test
	public void test_2batches() throws FileNotFoundException, IOException {
		MyCSVReader csvReader = new MyCSVReader();
		List<String> batch1 = csvReader.getNextBatch(filename2);
		assertTrue(!batch1.isEmpty());
		assertTrue(batch1.size() <= 600);
		
		List<String> batch2 = csvReader.getNextBatch(filename2);
		assertThat(batch1.get(0), not(batch2.get(0)));
		assertTrue(!batch2.isEmpty());
		assertTrue(batch2.size() <= 600);
	}
	
}
