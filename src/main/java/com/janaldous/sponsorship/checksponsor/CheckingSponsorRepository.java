package com.janaldous.sponsorship.checksponsor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingSponsorRepository extends JpaRepository<CheckingSponsor, Long> {

}
