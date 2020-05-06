package com.janaldous.sponsorship.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.janaldous.sponsorship.application.domain.EmailLog;
import com.janaldous.sponsorship.application.domain.EmailQueueItem;
import com.janaldous.sponsorship.application.domain.EmailQueueStatus;
import com.janaldous.sponsorship.application.domain.EmailSentStatus;
import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.checksponsor.CheckingSponsorRepository;
import com.janaldous.sponsorship.webfacade.exception.ResourceNotFoundException;

import lombok.NonNull;

@Component
public class ApplicationServiceImpl implements IApplicationService {

	private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);
	
	@Autowired
	private EmailQueueItemRepository emailQueueItemRepository;
	
	@Autowired
	private EmailLogRepository emailLogRepository;
	
	@Autowired
	private IEmailApplicationService emailApplicationService;
	
	@Autowired
	private CheckingSponsorRepository checkingSponsorRepository;
	
	@Override
	public void draftApplicationByEmail(String toAddress, String jobName, CheckingSponsor sponsor) {
		Objects.requireNonNull(toAddress, "To email must not be null");
		Objects.requireNonNull(jobName, "job name must not be null");
		Objects.requireNonNull(sponsor, "sponsor must not be null");
		
		// add new entry to table
		EmailQueueItem queueItem = new EmailQueueItem();
		queueItem.setToAddress(toAddress);
		queueItem.setJobName(jobName);
		queueItem.setSponsor(sponsor);
		queueItem.setStatus(EmailQueueStatus.WAITING);
		
		emailQueueItemRepository.save(queueItem);
	}

	@Override
	public void sendApplicationByEmail(Long id) throws ApplicationException {
		EmailQueueItem emailQueueItem = emailQueueItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id does not exist"));
		
		try {
			emailApplicationService.sendDefaultEmailApplication(emailQueueItem.getToAddress(), emailQueueItem.getJobName());

			// delete from queue
			emailQueueItemRepository.delete(emailQueueItem);
			
			// move to log
			EmailLog emailLogItem = new EmailLog();
			BeanUtils.copyProperties(emailQueueItem, emailLogItem);
			emailLogItem.setStatus(EmailSentStatus.SUCCESS);
			emailLogRepository.save(emailLogItem);
		} catch (MessagingException e) {
			log.error("Email application sent unsuccessful", e);

			// audit
			EmailLog emailLogItem = new EmailLog();
			BeanUtils.copyProperties(emailQueueItem, emailLogItem);
			emailLogItem.setStatus(EmailSentStatus.FAIL);
			emailLogRepository.save(emailLogItem);
			
			throw new ApplicationException("Email application sending unsuccessful", e);
		}
	}

	@Override
	public List<EmailQueueItem> getEmailApplicationByStatus(EmailQueueStatus emailQueueStatus) {
		if (emailQueueStatus == null) {
			return emailQueueItemRepository.findAll();
		}
		return emailQueueItemRepository.findByStatus(emailQueueStatus);
	}

	@Override
	public Optional<EmailQueueItem> getEmailApplicationById(Long id) {
		return emailQueueItemRepository.findById(id);
	}

	@Override
	public boolean hasApplicationByEmail(@NonNull Long sponsorId) {
		CheckingSponsor checkingSponsor = checkingSponsorRepository.findById(sponsorId).orElseThrow(() -> new ResourceNotFoundException());
		return emailQueueItemRepository.existsBySponsor(checkingSponsor) || !emailLogRepository.isProcessedSuccessfully(checkingSponsor).isEmpty();
		
	}

}
