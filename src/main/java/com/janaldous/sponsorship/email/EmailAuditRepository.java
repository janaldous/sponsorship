package com.janaldous.sponsorship.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.email.domain.EmailAudit;

@Repository
public interface EmailAuditRepository extends JpaRepository<EmailAudit, Long> {

}
