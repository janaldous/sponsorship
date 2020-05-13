package com.janaldous.sponsorship.sponsor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.text.CaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.checksponsor.CheckingSponsorRepository;
import com.janaldous.sponsorship.namecomparison.INormalizedEquivalentComparator;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.sponsor.data.Sponsor;
import com.janaldous.sponsorship.sponsor.repository.IRelevantSponsorRepositoryFacade;
import com.janaldous.sponsorship.sponsor.repository.RelevantSponsorRepository;
import com.janaldous.sponsorship.sponsor.repository.SponsorChecklist;
import com.janaldous.sponsorship.sponsor.repository.SponsorRepository;
import com.janaldous.sponsorship.webfacade.dto.CheckedDto;

@Service
public class RelevantSponsorService implements IRelevantSponsorService {

	private static final Logger log = LoggerFactory.getLogger(RelevantSponsorService.class);

	private static final int DEFAULT_PAGE_SIZE = 20;

	@Autowired
	private RelevantSponsorRepository relevantSponsorRepository;
	
	@Autowired
	private IRelevantSponsorRepositoryFacade releavantSponsorRepositoryFacade;

	@Autowired
	private CheckingSponsorRepository checkingSponsorRepository;

	@Autowired
	private SponsorRepository sponsorRepository;

	private List<RelevantSponsor> cache;

	private LocalDateTime dueDate = LocalDateTime.of(2019, 12, 18, 0, 0);
	
	@Autowired
	private INormalizedEquivalentComparator normalizedComparator;

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
	public List<SponsorChecklist> findAllRelevantSponsors(
			Optional<Integer> optPageNumber, Optional<Integer> optPageSize) {

		log.info("pageNumber = " + optPageNumber.toString() + " pageSize = " + optPageSize.toString());
		
		int pageNumber = optPageNumber.orElse(0);
		int pageSize = optPageSize.orElse(DEFAULT_PAGE_SIZE);
		
		return releavantSponsorRepositoryFacade.getRelevantSponsors(pageNumber, pageSize);
	}

	@Override
	public CheckingSponsor checked(Long sponsorId, CheckedDto checked) {
		log.info("id = " + sponsorId + " checked = " + checked.toString());

		Sponsor sponsor = sponsorRepository.findById(sponsorId)
				.orElseThrow(() -> new IllegalArgumentException("sponsor not found"));
		
		List<CheckingSponsor> checkingSponsors = checkingSponsorRepository.findBySponsor(sponsor);
		if (checkingSponsors.isEmpty()) {
			throw new IllegalArgumentException("checking sponsor not found");
		}
		
		CheckingSponsor checkingSponsor = checkingSponsors.get(0);
		
		String changedField = checked.getChangedField();
		Objects.requireNonNull(changedField, "changedField cannot be null");
		setField(changedField, checked, checkingSponsor);

		boolean isChecked = isChecked(checkingSponsor);
		checkingSponsor.setChecked(isChecked);

		return checkingSponsorRepository.save(checkingSponsor);
	}
	
	void setField(String fieldName, CheckedDto input, CheckingSponsor output) {
		String changedFieldName = CaseUtils.toCamelCase(fieldName, true);
		
		// get is"changedFieldName" and get"changedFieldName" methods from input and output objects
		Class<? extends CheckedDto> inputClazz = CheckedDto.class;
		Class<? extends CheckingSponsor> outputClazz = CheckingSponsor.class;
		Method getMethod = Arrays.asList(inputClazz.getMethods()).stream()
				.filter(m -> {
					Class<?> returnType = m.getReturnType();
					if (boolean.class == returnType) {
						return m.getName().equals("is" + changedFieldName);
					} else if (String.class == returnType) {
						return m.getName().equals("get" + changedFieldName);
					} else {
						return false;
					}
				})
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid changedField: " + changedFieldName));
		Method setMethod = Arrays.asList(outputClazz.getMethods()).stream()
				.filter(m -> m.getName().equals("set" + changedFieldName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid changedField: " + changedFieldName));

		try {
			// get input field value
			Boolean fieldValue = (Boolean) getMethod.invoke(input);
			// set output field value
			setMethod.invoke(output, fieldValue);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isChecked(CheckingSponsor checkingSponsor) {
		return checkingSponsor.isApplied() || checkingSponsor.isAppliedByEmail() || checkingSponsor.isNoCareers() || checkingSponsor.isNoTechJobs() || checkingSponsor.isNeedRightToWork()
				|| checkingSponsor.isAbroad() || checkingSponsor.isNoOpenings();
	}

	@Override
	public List<SponsorChecklistSchedule> getCompanyResultsWithSchedule(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
		List<SponsorChecklist> findAllRelevantSponsors = findAllRelevantSponsors(pageNumber, pageSize);
		if (findAllRelevantSponsors == null) {
			throw new IllegalStateException("cannot have null sponsors");
		}
		
		long notCheckedSponsors = findAllRelevantSponsors.stream().filter(x -> x.getFinished() != null && !x.getFinished()).count();

		long needToCheckSponsors = notCheckedSponsors / (Duration.between(LocalDateTime.now(), dueDate).toDays()*7);
		
		List<SponsorChecklistSchedule> output = new ArrayList<>();
		
		for (int i = 0, ntcsCtr = 0; i < findAllRelevantSponsors.size() && ntcsCtr < needToCheckSponsors; i++) {
			SponsorChecklist sc = findAllRelevantSponsors.get(i);

			SponsorChecklistSchedule scs = new SponsorChecklistSchedule();
			BeanUtils.copyProperties(sc, scs);
			output.add(scs);
			
			if (!sc.getFinished()) {
				
				scs.setCheckToday(true);
				
				ntcsCtr++;
			}
		}

		return output.stream()
				.map(x -> {
					x.setPossibleIncorrectLikeness(normalizedComparator.isEqual(x.getSponsorName(), x.getCompanyHouseName()));
					return x;
				})
				.collect(Collectors.toList());
	}

}
