package com.janaldous.sponsorship.email;

public interface EmailApplicationService {

	/**
	 * Send application as email with default body and CV as attachment
	 * 
	 * @param to HR email of company
	 * @param jobName
	 */
	public void sendDefaultEmailApplication(String to, String jobName);
	
}
