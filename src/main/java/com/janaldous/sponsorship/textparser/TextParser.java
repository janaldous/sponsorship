package com.janaldous.sponsorship.textparser;

public interface TextParser {

	public static String toSearchableName(String name) {
		name = name.trim();
		return name.replace(' ', '+');
	}
	
}
