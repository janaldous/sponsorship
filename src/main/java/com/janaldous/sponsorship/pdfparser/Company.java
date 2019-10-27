package com.janaldous.sponsorship.pdfparser;

import java.util.List;

import com.janaldous.sponsorship.sponsor.data.Tier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Company {
	private String name;
	private String city;
	private String county;
	private boolean inLondon;
	private List<Tier> tier;
}
