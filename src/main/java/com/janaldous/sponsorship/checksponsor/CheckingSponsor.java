package com.janaldous.sponsorship.checksponsor;

import java.util.Date;

import javax.persistence.Column;
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
	private boolean niceSite;
	private boolean interestingIdea;
	private boolean noCareers;
	private boolean incorrectLikeness;
	@Column(columnDefinition = "boolean default false")
	private boolean noTechJobs;
	@Column(columnDefinition = "boolean default false")
	private boolean needRightToWork;
	@Column(columnDefinition = "boolean default false")
	private boolean abroad;
	@Column(columnDefinition = "boolean default false")
	private boolean appliedByEmail;
	@Column(columnDefinition = "boolean default false")
	private boolean noOpenings;
	private String categories;
	private String otherInfo;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
}
