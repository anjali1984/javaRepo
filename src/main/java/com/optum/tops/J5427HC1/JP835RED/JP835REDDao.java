package com.optum.tops.J5427HC1.JP835RED;

import org.springframework.beans.factory.annotation.Autowired;

import com.optum.tops.J5427HC1.services.*;

public class JP835REDDao {
	
	@Autowired 
	RequestProcessor requestProc;
	
	public void RedProcess (){
		
	}

/*
 * SQL Commands and what not (joe said he did this ???????)
 * 
 * we only need these to be fed into red from d54 
 *         15 :REQ:-835-INVN-CTL-NBR     PIC X(10). - in req claim entry. individual_claim.getinv_ctl_nbr
           15 :REQ:-835-ICN-SUFX-CD      PIC X(02). - inside currentclaim.my_indicator.get ICN SUFX CD
           15 :REQ:-835-PROC-DT          PIC X(10). - in req claim entry (individual claim)
           15 :REQ:-835-PROC-TM          PIC X(08). - in req claim entry (individual_claim)
 */
}