package com.janaldous.sponsorship.sponsor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.checksponsor.CheckingSponsorRepository;
import com.janaldous.sponsorship.companieshouse.data.CompanyHouseCompany;
import com.janaldous.sponsorship.companieshouse.data.SIC;
import com.janaldous.sponsorship.namecomparison.NameNormalizer;
import com.janaldous.sponsorship.sponsor.data.ProcessStatus;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.sponsor.data.Sponsor;
import com.janaldous.sponsorship.sponsor.data.Tier;
import com.janaldous.sponsorship.sponsor.data.TierNum;
import com.janaldous.sponsorship.sponsor.data.TierSub;
import com.janaldous.sponsorship.webfacade.CheckedDto;

@Service
public class RelevantSponsorService implements IRelevantSponsorService {

	private static final Logger log = LoggerFactory.getLogger(RelevantSponsorService.class);

	private static final int PAGE_SIZE = 20;

	@Autowired
	private RelevantSponsorRepository relevantSponsorRepository;

	@Autowired
	private CheckingSponsorRepository checkingSponsorRepository;

	@Autowired
	private SponsorRepository sponsorRepository;

	@Autowired
	private NameNormalizer normalizer;

	private List<RelevantSponsor> cache;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	private LocalDateTime dueDate = LocalDateTime.of(2019, 12, 18, 0, 0);


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
	public List<CompanyResult> findAllRelevantSponsors(
			Optional<Integer> pageNumber, Optional<Integer> pageSize) {

		log.info("pageNumber = " + pageNumber.toString() + " pageSize = " + pageSize.toString());
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			// find tier
			CriteriaQuery<Tier> cqTier = cb.createQuery(Tier.class);
			Root<Tier> tierFrom = cqTier.from(Tier.class);
			cqTier.select(tierFrom)
			.where(cb.and(
					cb.equal(tierFrom.get("tier"), TierNum.TIER_2),
					cb.equal(tierFrom.get("subTier"), TierSub.GENERAL)));
			Tier tier = entityManager.createQuery(cqTier).getSingleResult();

			CriteriaQuery<SIC> cqSic = cb.createQuery(SIC.class);
			Root<SIC> sicFrom = cqSic.from(SIC.class);
			cqSic.select(sicFrom).where(
					cb.equal(sicFrom.get("interested"), true));
			List<SIC> interestingSic = entityManager.createQuery(cqSic)
					.getResultList();

			CriteriaQuery<CompanyResult> cq = cb
					.createQuery(CompanyResult.class);

			Root<CompanyHouseCompany> chCompany = cq
					.from(CompanyHouseCompany.class);
			Join<Object, Object> sponsor = chCompany.join("sponsor");
			Root<RelevantSponsor> relevantSponsor = cq
					.from(RelevantSponsor.class);
			Root<CheckingSponsor> checkingSponsor = cq
					.from(CheckingSponsor.class);

			Predicate predSponsor = cb.equal(relevantSponsor.get("sponsor"),
					sponsor);
			Predicate predSuccess = cb.equal(relevantSponsor.get("status"),
					ProcessStatus.SUCCESS);
			Predicate predTier2Gen = cb.isMember(tier, sponsor.get("tier"));
			Predicate predChecking = cb.equal(checkingSponsor.get("sponsor"),
					sponsor);
			Predicate[] preds = interestingSic.stream()
					.map(x -> cb.isMember(x, chCompany.get("sic")))
					.toArray(MemberOfPredicate[]::new);

			cq.select(
					cb.construct(CompanyResult.class, chCompany,
							checkingSponsor))
			.distinct(true)
			.where(cb.and(
					cb.and(predSponsor,
							cb.and(cb.or(preds), predChecking)),
					cb.and(predSuccess, predTier2Gen)));

			TypedQuery<CompanyResult> typedQuery = entityManager
					.createQuery(cq);

			int realPageSize = PAGE_SIZE;
			if (pageSize.isPresent()) {
				realPageSize = pageSize.get();
			}
			if (pageNumber != null && pageNumber.isPresent()) {
				int pageNo = pageNumber.get();
				typedQuery.setFirstResult((pageNo) * realPageSize);
				typedQuery.setMaxResults(realPageSize);
			}
			return typedQuery.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
		return null;
	}

	@Override
	public CheckingSponsor checked(Long id, CheckedDto checked) {
		log.info("id = " + id + " checked = " + checked.toString());

		Optional<Sponsor> optSponsor = sponsorRepository.findById(id);
		if (!optSponsor.isPresent()) {
			throw new IllegalArgumentException("sponsor not found");
		}
		Sponsor sponsor = optSponsor.get();
		List<CheckingSponsor> checkingSponsors = checkingSponsorRepository.findBySponsor(sponsor);
		if (checkingSponsors.size() != 1) {
			throw new IllegalArgumentException("checking sponsor not found");
		}
		CheckingSponsor checkingSponsor = checkingSponsors.get(0);
		String changedField = checked.getChangedField();
		
		Objects.requireNonNull(changedField, "changedField cannot be null");
		
		switch (changedField) {
		case "applied":
			checkingSponsor.setApplied(checked.isApplied());
			break;
		case "incorrectLikeness":
			checkingSponsor.setIncorrectLikeness(checked.isIncorrectLikeness());
			break;
		case "checkLater":
			checkingSponsor.setCheckLater(checked.isCheckLater());
			break;
		case "interestingIdea":
			checkingSponsor.setInterestingIdea(checked.isInterestingIdea());
			break;
		case "niceSite":
			checkingSponsor.setNiceSite(checked.isNiceSite());
			break;
		case "noCareers":
			checkingSponsor.setNoCareers(checked.isNoCareers());
			break;
		case "categories":
			checkingSponsor.setCategories(checked.getCategories());
			break;		
		case "otherInfo":
			checkingSponsor.setOtherInfo(checked.getOtherInfo());
			break;
		case "noTechJobs":
			checkingSponsor.setNoTechJobs(checked.isNoTechJobs());
			break;
		case "needRightToWork":
			checkingSponsor.setNeedRightToWork(checked.isNeedRightToWork());
			break;
		case "abroad":
			checkingSponsor.setAbroad(checked.isAbroad());
			break;
		case "appliedByEmail":
			checkingSponsor.setAppliedByEmail(checked.isAppliedByEmail());
			break;
		case "noOpenings":
			checkingSponsor.setNoOpenings(checked.isNoOpenings());
			break;
		default:
			throw new IllegalArgumentException("Invalid changedField " + changedField);
		}

		boolean isChecked = isChecked(checkingSponsor);
		checkingSponsor.setChecked(isChecked);

		return checkingSponsorRepository.save(checkingSponsor);
	}

	private boolean isChecked(CheckingSponsor checkingSponsor) {
		return checkingSponsor.isApplied() || checkingSponsor.isAppliedByEmail() || checkingSponsor.isNoCareers() || checkingSponsor.isNoTechJobs() || checkingSponsor.isNeedRightToWork()
				|| checkingSponsor.isAbroad() || checkingSponsor.isNoOpenings();
	}

	@Override
	public List<CompanyResult> getCompanyResultsWithSchedule(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
		List<CompanyResult> findAllRelevantSponsors = findAllRelevantSponsors(pageNumber, pageSize);
		if (findAllRelevantSponsors == null) {
			throw new IllegalStateException("cannot have null sponsors");
		}
		long notCheckedSponsors = findAllRelevantSponsors.stream().filter(x -> x.getChecking() != null && x.getChecking().getChecked() != null && !x.getChecking().getChecked()).count();

		long needToCheckSponsors = notCheckedSponsors / (Duration.between(LocalDateTime.now(), dueDate).toDays()*7);

		for (int i = 0, ntcsCtr = 0; i < findAllRelevantSponsors.size() && ntcsCtr < needToCheckSponsors; i++) {
			if (!findAllRelevantSponsors.get(i).getChecking().getChecked()) {
				findAllRelevantSponsors.get(i).setShouldCheckToday(true);
				ntcsCtr++;
			}
		}

		return findAllRelevantSponsors.stream().map(x -> mapIncorrectLikeness(x, normalizer)).collect(Collectors.toList());
	}

	static CompanyResult mapIncorrectLikeness(CompanyResult companyResult, NameNormalizer normalizer) {
		boolean isEqual = false;
		if (companyResult == null || companyResult.getCompany() == null || companyResult.getCompany().getCompanyHouseName() == null
				|| companyResult.getChecking() == null || companyResult.getChecking().getSponsor() == null) {
			throw new RuntimeException("null something");
		} else {
			String name1 = normalizer.normalize(companyResult.getCompany().getCompanyHouseName());
			String name2 = normalizer.normalize(companyResult.getChecking().getSponsor().getName().trim());
			isEqual = name1.equalsIgnoreCase(name2);
		}
		companyResult.setPossibleIncorrectLikeness(!isEqual);
		return companyResult;
	}

}
