package com.janaldous.sponsorship.application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.application.domain.EmailLog;
import com.janaldous.sponsorship.checksponsor.CheckingSponsor;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

	@Query("SELECT e FROM EmailLog e WHERE e.sponsor=:sponsor AND e.status!='FAIL'")
	List<EmailLog> isProcessedSuccessfully(@Param("sponsor") CheckingSponsor sponsor);

}
