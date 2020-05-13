package com.janaldous.sponsorship.sponsor.repository;

import lombok.Data;

@Data
public class SponsorChecklist {
	
//	public SponsorChecklist(String companyHouseName, Long companyHouseId, String sponsorName, Long sponsorId, Boolean applied, Boolean hasDraftEmail) {
//		this.companyHouseName = companyHouseName;
//		this.companyHouseId = companyHouseId;
//		this.sponsorName = sponsorName;
//		this.sponsorId = sponsorId;
//		this.applied = applied;
//		this.hasDraftEmail = hasDraftEmail;
//		
//		this.checkLater = false;
//		this.noCareers = false;
//		this.finished = false;
//		this.otherInfo = "";
//		this.shouldCheckToday = false;
//		this.needRightToWork = false;
//		this.noTechJobs = false;
//		this.possibleIncorrectLikeness = false;
//		this.categories = "";
//		this.niceSite = false;
//		this.appliedByEmail = false;
//		this.abroad = false;
//		this.noOpenings = false;
//	}
	
	private String companyHouseName;
	private String companyHouseId;
	private String sponsorName;
	private Long sponsorId;
	
	private boolean applied;
	private boolean checkLater;
	private boolean noCareers;
	private Boolean finished;
	private boolean needRightToWork;
	
	private boolean noTechJobs;
	private boolean possibleIncorrectLikeness;
	private boolean niceSite;
	private boolean appliedByEmail;
	private boolean abroad;
	
	private boolean interestingIdea;
	private boolean noOpenings;
	private boolean hasDraftEmail;
	
	private String categories;
	private String otherInfo;

}
