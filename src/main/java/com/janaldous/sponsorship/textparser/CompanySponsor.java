package com.janaldous.sponsorship.textparser;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanySponsor {

	private String name;
	private boolean isInLondon;
	private boolean tier2;
	
}
