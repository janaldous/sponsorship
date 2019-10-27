package com.janaldous.sponsorship.checksponsor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.UpdateTimestamp;

import com.janaldous.sponsorship.sponsor.data.Sponsor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckingSponsor {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Sponsor sponsor;
	private Boolean checked;
	private boolean applied;
	private boolean checkLater;
	private boolean noCareers;
	private String otherInfo;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
}
