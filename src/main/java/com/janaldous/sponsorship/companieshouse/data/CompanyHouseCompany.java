package com.janaldous.sponsorship.companieshouse.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.janaldous.sponsorship.sponsor.data.Sponsor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyHouseCompany {
	
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * Name of company in Companies House API
	 */
	private String companyHouseName;
	private boolean inLondon;
	private boolean tier2;
//	@Column(unique = true)
	private String companiesHouseId;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<SIC> sic;
	@OneToOne//(fetch = FetchType.EAGER)
	private Sponsor sponsor;
}
