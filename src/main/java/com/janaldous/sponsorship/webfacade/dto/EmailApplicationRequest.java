package com.janaldous.sponsorship.webfacade.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class EmailApplicationRequest {

	@NonNull
	private String toAddress;
	@NonNull
	private String jobName;
	@NonNull
	private Long sponsorId;
	
}
