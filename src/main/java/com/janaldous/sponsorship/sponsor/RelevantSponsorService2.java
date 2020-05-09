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
import com.janaldous.sponsorship.sponsor.repository.CompanyResult;
import com.janaldous.sponsorship.sponsor.repository.SponsorChecklist;
import com.janaldous.sponsorship.webfacade.dto.CheckedDto;

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
			String chName = castString(x[0]);
			String chId = castString(x[1]);
			String sponsorName = castString(x[2]);
			Long sponsorId = ((BigInteger) x[3]).longValue();
			boolean applied = castBool(x[4]);
			boolean checkLater = castBool(x[5]);
			boolean noCareers = castBool(x[6]);
			boolean checked = castBool(x[7]);
			boolean needRightToWork = castBool(x[8]);
			boolean noTechJobs = castBool(x[9]);
			boolean incorrectLikeness = castBool(x[10]);
			boolean niceSite = castBool(x[11]);
			boolean appliedByEmail = castBool(x[12]);
			boolean abroad = castBool(x[13]);
			boolean interestingIdea = castBool(x[14]);
			boolean noOpenings = castBool(x[15]);
			boolean hasDraftEmail = castBool(x[16]);
			String categories = castString(x[17]);
			String otherInfo = castString(x[18]);
		
			SponsorChecklist sc = new SponsorChecklist();
			sc.setCompanyHouseName(chName);
			sc.setCompanyHouseId(chId);
			sc.setCheckingSponsorName(sponsorName);
			sc.setCheckingSponsorId(sponsorId);
			sc.setApplied(applied);
			sc.setCheckLater(checkLater);
			sc.setNoCareers(noCareers);
			sc.setFinished(checked);
			sc.setNeedRightToWork(needRightToWork);
			sc.setNoTechJobs(noTechJobs);
			sc.setPossibleIncorrectLikeness(incorrectLikeness);
			sc.setNiceSite(niceSite);
			sc.setAppliedByEmail(appliedByEmail);
			sc.setAbroad(abroad);
			sc.setInterestingIdea(interestingIdea);
			sc.setNoOpenings(noOpenings);
			sc.setHasDraftEmail(hasDraftEmail);
			sc.setCategories(categories);
			sc.setOtherInfo(otherInfo);
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
		
		String queryStr = "SELECT "
		+ "chc.company_house_name, "
		+ "chc.companies_house_id, "
		+ "s.name, "
		+ "cs.id as checking_sponsor_id, "
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
		+ "FROM company_house_company chc "
		+ "JOIN company_house_company_sic chcs ON chcs.company_house_company_id=chc.id "
		+ "INNER JOIN sponsor s ON chc.sponsor_id=s.id "
		+ "JOIN relevant_sponsor rs ON rs.sponsor_id=s.id "
		+ "JOIN checking_sponsor cs ON cs.sponsor_id=s.id "
		+ "LEFT JOIN sponsor_tier st ON st.sponsor_id=s.id "
		+ "LEFT JOIN email_queue_item eqi ON eqi.sponsor_id=cs.id "
		+ "WHERE st.tier_id=:tier2GeneralId "
		+ "AND chcs.sic_code IN (SELECT code FROM sic WHERE interested=true) "
		+ "AND rs.status='SUCCESS' "
		+ "OFFSET :pageOffset "
		+ "LIMIT :pageSize";
		
		//cs as c_sponsor, chc as ch_company, 
		Query q = em.createNativeQuery(queryStr);
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
