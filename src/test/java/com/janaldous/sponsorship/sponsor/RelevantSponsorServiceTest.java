package com.janaldous.sponsorship.sponsor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.janaldous.sponsorship.checksponsor.CheckingSponsor;
import com.janaldous.sponsorship.webfacade.dto.CheckedDto;

public class RelevantSponsorServiceTest {

	private RelevantSponsorService service = new RelevantSponsorService();
	
	@Test
	public void test() {
	}
	
	@Test
	public void testChangeBooleanAppliedCamelcase() {
		
		CheckedDto input = new CheckedDto();
		input.setApplied(true);
		
		CheckingSponsor output = new CheckingSponsor();

		service.setField("applied", input, output);
		
		assertEquals(true, output.isApplied());
	}
	
	@Test
	public void testChangeBooleanAppliedTitlecase() {
		
		CheckedDto input = new CheckedDto();
		input.setApplied(true);
		
		CheckingSponsor output = new CheckingSponsor();

		service.setField("Applied", input, output);
		
		assertEquals(true, output.isApplied());
	}
	
	@Test
	public void testChangeStringOtherInfoCamelcase() {
		
		CheckedDto input = new CheckedDto();
		input.setOtherInfo("other info");
		
		CheckingSponsor output = new CheckingSponsor();

		service.setField("otherInfo", input, output);
		
		assertEquals(input.getOtherInfo(), output.getOtherInfo());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeNonExistentField() {
		
		CheckedDto input = new CheckedDto();
		input.setApplied(true);
		
		CheckingSponsor output = new CheckingSponsor();

		service.setField("nonExistent", input, output);
	}

}
