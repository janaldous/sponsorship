package com.janaldous.sponsorship.pdfparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.janaldous.sponsorship.sponsor.TierRepository;
import com.janaldous.sponsorship.sponsor.data.Tier;
import com.janaldous.sponsorship.sponsor.data.TierNum;
import com.janaldous.sponsorship.sponsor.data.TierSub;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TierTest {

	@Autowired
	private TierRepository tierRepository;
	
	@Test
	public void test() {
		for (TierNum tn: TierNum.values()) {
			for (TierSub ts: TierSub.values()) {
				Tier t = Tier.builder().tier(tn).subTier(ts).build();
				tierRepository.save(t);
			}
		}
	}
	
}
