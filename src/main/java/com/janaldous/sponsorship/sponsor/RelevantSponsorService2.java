package com.janaldous.sponsorship.sponsor;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.webfacade.dto.CheckedDto;

@Qualifier("second")
@Component
public class RelevantSponsorService2 implements IRelevantSponsorService {

	private Logger log = LoggerFactory.getLogger(RelevantSponsorService2.class);
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<RelevantSponsor> getNextBatch(int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyResult> findAllRelevantSponsors(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
		return null;
	}
	
	public List<SponsorChecklist> findAllRelevantSponsors3(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
		List<Object[]> result = findAllRelevantSponsors2(pageNumber, pageSize);
		return result .stream().map((x) -> {
			Long sponsorId = ((BigInteger) x[1]).longValue();
			Long chCompanyId = ((BigInteger) x[3]).longValue();
			SponsorChecklist sc = new SponsorChecklist(
					(String) x[0], 
					sponsorId, 
					(String) x[2], 
					chCompanyId, 
					(Boolean) x[4], 
					(Boolean) x[5],
					(Boolean) x[6],
					castBool(x[7]),
					castBool(x[8]),
					castBool(x[9]),
					castBool(x[10]),
					castBool(x[11]),
					castBool(x[12]),
					castBool(x[13]),
					castBool(x[14]),
					castBool(x[15]),
					castBool(x[16]),
					castString(x[17]),
					castString(x[18]),
					false);
			return sc;
		}).collect(Collectors.toList());
	}
	
	private Boolean castBool(Object b) {
		return b != null ? (Boolean) b: false;
	}
	
	private String castString(Object s) {
		return s != null ? (String) s: "";
	}
	
	public List<Object[]> findAllRelevantSponsors2(Optional<Integer> optPageNumber, Optional<Integer> optPageSize) {
		int pageNumber = optPageNumber.orElse(0);
		int pageSize = optPageSize.orElse(10);
		
		log.info(MessageFormat.format("pageNumber={0} pageSize={1}", pageNumber, pageSize));
		
		EntityManager em = entityManagerFactory
				.createEntityManager();

		//cs as c_sponsor, chc as ch_company, 
		Query q = em.createNativeQuery("SELECT "
				+ "chc.company_house_name, "
				+ "chc.id as company_house_id, "
				+ "s.name, "
				+ "s.id as sponsor_id, "
				+ "cs.applied, "
				+ "cs.check_later, "
				+ "cs.no_careers, "
				+ "cs.checked, "
				+ "cs.need_right_to_work, "
				+ "cs.no_tech_jobs, "
				+ "cs.incorrect_likeness, "
				+ "cs.nice_site, "
				+ "cs.applied_by_email, "
				+ "cs.abroad, "
				+ "cs.interesting_idea, "
				+ "cs.no_openings, "
				+ "case when eqi.status = 'WAITING' then true else false end as email_status, "
				+ "cs.categories, "
				+ "cs.other_info "
				+ "FROM checking_sponsor cs "
				+ "JOIN sponsor s ON cs.sponsor_id=s.id "
				+ "JOIN relevant_sponsor rs ON rs.sponsor_id=s.id "
				+ "JOIN company_house_company chc ON chc.sponsor_id=s.id "
				+ "JOIN company_house_company_sic chcs ON chcs.company_house_company_id=chc.id "
				+ "LEFT JOIN email_queue_item eqi ON eqi.sponsor_id=cs.id "
				+ "LEFT JOIN sponsor_tier st ON st.sponsor_id=s.id "
				+ "WHERE st.tier_id=:tier2GeneralId "
				+ "AND chcs.sic_code IN (SELECT code FROM sic WHERE interested=true) "
				+ "AND rs.status='SUCCESS' "
				+ "OFFSET :pageOffset "
				+ "LIMIT :pageSize");
		q.setParameter("tier2GeneralId", 4353);
		q.setParameter("pageOffset", pageNumber * pageSize);
		q.setParameter("pageSize", pageSize);
		List<Object[]> result = q.getResultList();
		
		return result;
	}

	@Override
	public CheckingSponsor checked(Long id, CheckedDto checked) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyResult> getCompanyResultsWithSchedule(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
		return null;
	}

	@Override
	public List<SponsorChecklist> getCompanyResultsWithSchedule2(Optional<Integer> pageNumber,
			Optional<Integer> pageSize) {
		List<SponsorChecklist> findAllRelevantSponsors = findAllRelevantSponsors3(pageNumber, pageSize);
		if (findAllRelevantSponsors == null) {
			throw new IllegalStateException("cannot have null sponsors");
		}

		return findAllRelevantSponsors;
	}

}
