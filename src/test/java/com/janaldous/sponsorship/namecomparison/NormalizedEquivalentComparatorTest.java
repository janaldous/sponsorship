package com.janaldous.sponsorship.namecomparison;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.janaldous.sponsorship.namecomparison.NameNormalizer;
import com.janaldous.sponsorship.namecomparison.NormalizedEquivalentComparator;

@RunWith(MockitoJUnitRunner.class)
public class NormalizedEquivalentComparatorTest {

	@Spy
	private NameNormalizer normalizer;
	
	@InjectMocks
	private NormalizedEquivalentComparator normalizedEquivlanetComparator;
	
	@Test
	public void testCheckingPossibleIncorrectLikeness_exact() {
		String companyName = "company house name";
		boolean result = normalizedEquivlanetComparator.isEqual(companyName, companyName);
		assertTrue(result);

	}

	@Test
	public void testCheckingPossibleIncorrectLikeness_normalized() {
		String companyName = "company house name limited";
		String normlaizedCompanyName = "company house name ltd";
		boolean result = normalizedEquivlanetComparator.isEqual(companyName, normlaizedCompanyName);
		assertTrue(result);
	}
	
	@Test
	public void testCheckingPossibleIncorrectLikeness_negativeTest1() {
		String companyName = "company house name";
		String normlaizedCompanyName = "some other name";
		boolean result = normalizedEquivlanetComparator.isEqual(companyName, normlaizedCompanyName);
		assertFalse(result);
	}
	
	@Test(expected = NullPointerException.class)
	public void testCheckingPossibleIncorrectLikeness_negativeTest_nullSponsor() {
		String companyName = "company house name";
		String normlaizedCompanyName = null;
		normalizedEquivlanetComparator.isEqual(companyName, normlaizedCompanyName);
	}
	
	@Test(expected = NullPointerException.class)
	public void testCheckingPossibleIncorrectLikeness_negativeTest_nullCompanyHouse() {
		String companyName = null;
		String normlaizedCompanyName = "some other name";
		normalizedEquivlanetComparator.isEqual(companyName, normlaizedCompanyName);
	}
	
}
