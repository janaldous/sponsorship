package com.janaldous.sponsorship.companieshouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;

@Repository
public interface ICompanyHouseCompanyRepository extends JpaRepository<CompanyHouseCompany, Long> {

}
