package com.janaldous.sponsorship.sponsor.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sponsor {

	@Id
	@GeneratedValue
	private Long id;
	/**
	 * Name of sponsor in sponsorship pdf
	 */
	private String name;
	private String city;
	private String county;
	private boolean inLondon;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Tier> tier;
}
