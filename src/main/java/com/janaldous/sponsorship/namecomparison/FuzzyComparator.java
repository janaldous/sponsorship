package com.janaldous.sponsorship.namecomparison;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import me.xdrop.fuzzywuzzy.FuzzySearch;

@Qualifier("alphabetComparator")
@Component
public class FuzzyComparator implements NameComparator {

	@Override
	public int calculateLikeness(String name1, String name2) {
		return FuzzySearch.ratio(name1 , name2);
	}

}
