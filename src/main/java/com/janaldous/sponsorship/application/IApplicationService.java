package com.janaldous.sponsorship.application;

import java.util.List;
import java.util.Optional;

import com.janaldous.sponsorship.application.domain.EmailQueueItem;
import com.janaldous.sponsorship.application.domain.EmailQueueStatus;
import com.janaldous.sponsorship.checksponsor.CheckingSponsor;

import lombok.NonNull;

public interface IApplicationService {

	/**
	 * Application via email will be added to the "email queue".
	 * <p>
	 * <b>Email queue</b> is represented by EmailAudit table. 
	 * 
	 * @param toAddress address of company's HR
	 * @param jobName name of job one is applying to in a company
	 * @param sponsor company's sponsor entity
	 */
	void draftApplicationByEmail(String toAddress, String jobName, CheckingSponsor sponsor);
	
	/**
	 * Attempt to send application by email that is already in "email queue". 
	 * @see IApplicationService#draftApplicationByEmail(String, String, CheckingSponsor)
	 * 
	 * @param id of EmailAudit to be sent
	 * @throws ApplicationException 
	 */
	void sendApplicationByEmail(Long id) throws ApplicationException;

	/**
	 * Get email application by status, if no status given, will provide all email applications
	 * 
	 * @param emailSendingStatus
	 */
	List<EmailQueueItem> getEmailApplicationByStatus(EmailQueueStatus emailQueueStatus);

	Optional<EmailQueueItem> getEmailApplicationById(Long id);

	boolean hasApplicationByEmail(@NonNull Long sponsorId);

}
