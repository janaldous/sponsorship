package com.janaldous.sponsorship.textparser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.janaldous.sponsorship.textparser.TextParser;

public class TextParserTest {

	@Test
	public void test() {
		String name = "Betbull Social Sports UK Limited ";
		String expected = "Betbull+Social+Sports+UK+Limited";
		
		String searchableName = TextParser.toSearchableName(name);
		
		assertEquals(expected, searchableName);
	}
	
}
