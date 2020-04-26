package com.janaldous.sponsorship.email.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;

import lombok.Data;

@Entity
@Data
public class EmailAudit {

	@GeneratedValue
	@Id
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date timestamp;
	@Enumerated(EnumType.STRING)
	private EmailSendingStatus status;
	private String toAddress;
	private String jobName;
	@ManyToOne
	private CheckingSponsor sponsor;
	
}
