package com.janaldous.sponsorship.sponsor;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.checksponsor.CheckingSponsorRepository;
import com.janaldous.sponsorship.sponsor.data.RelevantSponsor;
import com.janaldous.sponsorship.sponsor.data.TierNum;
import com.janaldous.sponsorship.sponsor.data.TierSub;


@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class RelevantSponsorTest {
	
	@Autowired
	private RelevantSponsorRepository relevantSponsorRepository;
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private IRelevantSponsorService relevantSponsorService;
	
	@Autowired
	private CheckingSponsorRepository checkingSponsorRepository;
	
	@Test
	public void test() {
		System.out.println("asdf");

		sponsorRepository.findLondonSponsors().stream()
			.filter(x -> {
				return x.getId() >= 95476 && x.isInLondon() && !x.getTier().isEmpty() 
						&& x.getTier().stream().anyMatch(y -> {
							return y.getTier() == TierNum.TIER_2 && y.getSubTier() == TierSub.GENERAL;
						});
			})
			.forEach(x -> {
				System.out.println(x);
				relevantSponsorRepository.save(RelevantSponsor.builder().sponsor(x).processed(false).likeness(-1).build());
			});
	}
	
	@Test
	public void test2() {
		int size = sponsorRepository.findLondonSponsors().size();
		System.out.println(size);
	}
	
//	@Test
	public void test3() {
		List<CompanyResult> companies = relevantSponsorService.findAllRelevantSponsors(null);
		System.out.println(companies.size());
//		companies.stream().forEach(x -> {
//			CheckingSponsor sponsor = CheckingSponsor.builder().sponsor(x.getCompany().getSponsor()).build();
////			checkingSponsorRepository.save(sponsor);
//		});
	}
}
