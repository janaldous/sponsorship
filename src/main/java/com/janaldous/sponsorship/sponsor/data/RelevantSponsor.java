package com.janaldous.sponsorship.sponsor.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Relevant sponsors = in london
 * 
 * @author janaldoustorres
 *
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelevantSponsor {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Sponsor sponsor;
	private boolean processed;
	@Enumerated(EnumType.STRING)
	private ProcessStatus status;
	@ColumnDefault("-1")
	private int likeness;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
}
