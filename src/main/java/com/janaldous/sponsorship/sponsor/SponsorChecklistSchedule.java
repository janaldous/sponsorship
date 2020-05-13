package com.janaldous.sponsorship.sponsor;

import com.janaldous.sponsorship.sponsor.repository.SponsorChecklist;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SponsorChecklistSchedule extends SponsorChecklist {

	private boolean checkToday;
	
}
