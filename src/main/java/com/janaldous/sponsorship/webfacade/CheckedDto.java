package com.janaldous.sponsorship.webfacade;

import lombok.Data;


/**
 * @author janaldoustorres
 *
 */
@Data
public class CheckedDto {

	/**
	 * Applied online and applied by email (before Apr 19). Applied online only (on or after Apr 19)
	 */
	private boolean applied;
	private boolean incorrectLikeness;
	private boolean checkLater;
	private boolean niceSite;
	private boolean interestingIdea;
	/**
	 * no careers page
	 */
	private boolean noCareers;
	private boolean noTechJobs;
	private boolean needRightToWork;
	
	/**
	 * if job is in kl or mnl or sg 
	 */
	private boolean abroad;
	
	/**
	 * Applied by email (starting Apr 19)
	 */
	private boolean appliedByEmail;
	/**
	 * Has careers page but says that there are no openings
	 */
	private boolean noOpenings;
	private String categories;
	private String otherInfo;
	
	private String changedField;
	
}
