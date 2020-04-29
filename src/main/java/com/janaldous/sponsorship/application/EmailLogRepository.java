package com.janaldous.sponsorship.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.application.domain.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

}
