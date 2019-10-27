package com.janaldous.sponsorship.companieshouse.apidomain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CompanyProfile {

	private List<String> sic_codes;
	
	public List<String> getSic_codes() {
		if (sic_codes == null) {
			return new ArrayList<>();
		}
		return sic_codes;
	}
}
