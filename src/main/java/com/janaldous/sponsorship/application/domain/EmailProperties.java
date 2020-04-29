package com.janaldous.sponsorship.application.domain;

import java.io.File;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class EmailProperties {

	@NonNull
	private String attachmentFilename;
	@NonNull
	private File attachment;
	@NonNull
	private String emailTemplate;
	
}
