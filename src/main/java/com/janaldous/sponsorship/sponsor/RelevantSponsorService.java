package com.janaldous.sponsorship.sponsor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.criteria.internal.predicate.MemberOfPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.sponsor.data.ProcessStatus;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.sponsor.data.Tier;
import com.janaldous.sponsorship.sponsor.data.TierNum;
import com.janaldous.sponsorship.sponsor.data.TierSub;

@Service
public class RelevantSponsorService implements IRelevantSponsorService {

	private static final int PAGE_SIZE = 10;

	@Autowired
	private RelevantSponsorRepository relevantSponsorRepository;

	private List<RelevantSponsor> cache;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<RelevantSponsor> getNextBatch(int batchSize) {
		if (cache == null) {
			cache = relevantSponsorRepository.findUnprocessed().stream()
					.collect(Collectors.toList());
		}

		List<RelevantSponsor> output = cache.stream().limit(batchSize)
				.collect(Collectors.toList());
		cache.removeAll(output);
		return output;
	}

	@Override
	public List<CompanyResult> findAllRelevantSponsors(Optional<Integer> pageNumber) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// find tier
		CriteriaQuery<Tier> cqTier = cb.createQuery(Tier.class);
	    Root<Tier> tierFrom = cqTier.from(Tier.class);
	    cqTier.select(tierFrom)
        	.where(cb.and(cb.equal(tierFrom.get("tier"), TierNum.TIER_2), 
        			cb.equal(tierFrom.get("subTier"), TierSub.GENERAL)));
	    Tier tier = entityManager.createQuery(cqTier).getSingleResult();

	    CriteriaQuery<SIC> cqSic = cb.createQuery(SIC.class);
	    Root<SIC> sicFrom = cqSic.from(SIC.class);
	    cqSic.select(sicFrom).where(cb.equal(sicFrom.get("interested"), true));
	    List<SIC> interestingSic = entityManager.createQuery(cqSic).getResultList();
	    
		CriteriaQuery<CompanyResult> cq = cb.createQuery(CompanyResult.class);
		
		Root<CompanyHouseCompany> chCompany = cq.from(CompanyHouseCompany.class);
		Join<Object, Object> sponsor = chCompany.join("sponsor");
		Root<RelevantSponsor> relevantSponsor = cq.from(RelevantSponsor.class);
		Root<CheckingSponsor> checkingSponsor = cq.from(CheckingSponsor.class);

		Predicate predSponsor = cb.equal(relevantSponsor.get("sponsor"), sponsor);
		Predicate predSuccess = cb.equal(relevantSponsor.get("status"), ProcessStatus.SUCCESS);
		Predicate predTier2Gen = cb.isMember(tier, sponsor.get("tier"));
		Predicate predChecking = cb.equal(checkingSponsor.get("sponsor"), sponsor);
		Predicate[] preds = interestingSic.stream().map(x -> cb.isMember(x, chCompany.get("sic"))).toArray(MemberOfPredicate[]::new);
		
		cq.select(cb.construct(CompanyResult.class, 
				chCompany, checkingSponsor))
			.distinct(true)
			.where(cb.and(cb.and(predSponsor, cb.and(cb.or(preds), predChecking)), cb.and(predSuccess, predTier2Gen)));
		
		TypedQuery<CompanyResult> typedQuery = entityManager.createQuery(cq);
		if (pageNumber != null && pageNumber.isPresent()) {
			int pageNo = pageNumber.get();
			typedQuery.setFirstResult((pageNo-1) * PAGE_SIZE); 
			typedQuery.setMaxResults(PAGE_SIZE);
		}
		return typedQuery.getResultList();
	}
}
