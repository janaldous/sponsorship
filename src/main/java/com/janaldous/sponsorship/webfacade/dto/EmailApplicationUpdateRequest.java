package com.janaldous.sponsorship.webfacade.dto;

import com.janaldous.sponsorship.application.domain.EmailQueueStatus;

import lombok.Data;

@Data
public class EmailApplicationUpdateRequest {

	private EmailQueueStatus status;
	private String toAddress;
	private String jobName;
	private Long sponsorId;
	
}