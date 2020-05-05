package com.janaldous.sponsorship.application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.janaldous.sponsorship.application.domain.EmailQueueItem;
import com.janaldous.sponsorship.application.domain.EmailQueueStatus;
import com.janaldous.sponsorship.checksponsor.CheckingSponsor;

public interface EmailQueueItemRepository extends JpaRepository<EmailQueueItem, Long> {

	List<EmailQueueItem> findByStatus(EmailQueueStatus status);
	
	boolean existsBySponsor(CheckingSponsor sponsorId);
	
}
