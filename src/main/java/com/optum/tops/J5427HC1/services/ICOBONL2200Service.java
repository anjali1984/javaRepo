package com.optum.tops.J5427HC1.services;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface ICOBONL2200Service {

	/**
	 * java version of 2200-WRTOFF-CALC section of COBOL program D5427HC1
	 * Functionality:
	 * 1.When a cob claim, initializes the write off for recalculation
	 * 2.Separate calculations based on if the claim a professional or institutional claim
	 * @param currentClaim
	 * @param individual_claim 
	 */
	V5427HC1 WriteOff2200(V5427HC1 currentClaim, ReqClaimEntryVO individual_claim);

}