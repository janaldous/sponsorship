package com.janaldous.sponsorship.namecomparison;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Parameterized.class)
public class NameComparisonTest {
	
	private Logger log = LoggerFactory.getLogger(NameComparisonTest.class);
	
	@Parameterized.Parameter(0)
    public String name1;
    @Parameterized.Parameter(1)
    public String name2;
    @Parameterized.Parameter(2)
    public int score;
    
    private NameComparator comparator = new AlphabetComparator();
	
    @Parameterized.Parameters(name = "{index}: Test with name1={0}, name2 ={1}, score ={2} ")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
        	{"BEYOND INTERNATIONAL SERVICES LTD", "BEYOND DISTRIBUTION (UK) LIMITED", 19},
        	{"BEYOND INTERNATIONAL SERVICES LTD.", "BEYOND INTERNATIONAL SERVICES LTD", 0},
    		{"Beyond the Deal LLP", "BEYOND THE DEAL LIMITED LIABILITY PARTNERSHIP", 0},
        };
        return Arrays.asList(data);
    }
    
    @Test
    public void test() {
        int calculateLikeness = comparator.calculateLikeness(name1, name2);
        log.info(name1 + " " + name2 + " " + calculateLikeness);
		assertEquals("Result", score, calculateLikeness);
    }
    
}
