package com.janaldous.sponsorship.companieshouse.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.String;
import java.util.List;

@Repository
public interface SICRepository extends JpaRepository<SIC, Long> {
	
	List<SIC> findByCode(String code);
	
}
