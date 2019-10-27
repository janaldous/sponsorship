package com.janaldous.sponsorship.namecomparison;

public interface NameComparator {
	
	/**
	 * Compare strings. The closer to 0 the more like the inputs are.
	 * 
	 * @param name1
	 * @param name2
	 * @return
	 */
	int calculateLikeness(String name1, String name2);
	
}
