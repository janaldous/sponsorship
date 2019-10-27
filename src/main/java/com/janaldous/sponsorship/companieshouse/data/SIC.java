package com.janaldous.sponsorship.companieshouse.data;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nature of business: Standard Industrial Classification (SIC) codes
 * 
 * @author janaldoustorres
 *
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SIC {
	
    @Id
	private String code;
	private String description;
	private boolean interested;
	
}
