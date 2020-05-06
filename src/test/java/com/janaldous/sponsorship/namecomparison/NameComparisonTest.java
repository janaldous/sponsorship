package com.janaldous.sponsorship.namecomparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Parameterized.class)
public class NameComparisonTest {
	
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(NameComparisonTest.class);
	
	@Parameterized.Parameter(0)
    public String name1;
    @Parameterized.Parameter(1)
    public String name2;
    @Parameterized.Parameter(2)
    public int score;
    
    @InjectMocks
    private AlphabetComparator comparator;

    @Spy
    private NameNormalizer normalizer;
    
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Parameterized.Parameters(name = "{index}: Test with name1={0}, name2 ={1}, score ={2} ")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
        	{"BEYOND INTERNATIONAL SERVICES LTD", "BEYOND DISTRIBUTION (UK) LIMITED", 17},
        	{"BEYOND INTERNATIONAL SERVICES LTD.", "BEYOND INTERNATIONAL SERVICES LTD", 0},
    		{"Beyond the Deal LLP", "BEYOND THE DEAL LIMITED LIABILITY PARTNERSHIP", 0},
        };
        return Arrays.asList(data);
    }
    
    @Test
    public void test() {
    	assertThat(comparator, is(notNullValue()));
    	
        int calculateLikeness = comparator.calculateLikeness(name1, name2);
		assertEquals("Result", score, calculateLikeness);
    }
    
}
