package com.optum.tops.JP835RED;

public class FuncCodeProcessing {
	/* evaluate the DSM-Function Code then pass it to either 2100 or 2200 based on that 
	 * error out if there isn't a proper function code (1-7)
	 *  we could just put the 2100 and 2200 functions in here ? depends how much we want to break the
	 *  functions up
	 *  
	 */
	
	public void initialize(){
		/* do DAO stuff and initialize stuff
		 * 
		 * have if statement to decide if the function code is 1 or 2 
		 * 
		 * if its 1 then u go to 2100 
		 * if its 2 then u go to 2200
		 */
		
	}
	
	public void InstClaim2100(){
	     /*read data from adjudicated tables for Institutional Claims 
		  *
		  *
		  *  It then calls the following programs:
		  *  7701, 7702, 7703, 7708 (and IF program = d5427MID, then it calls 7712) 
		  */
		
	}
	
	public void ProClaim2200(){
		/*read data from adjudicated tables for a professional claim
		 * 
		 * This calls: 
		 * 7704, 7705, 7706, 7702, 7703, 7708 and then if its d5427MID call 7716)
		 */
	}
}
