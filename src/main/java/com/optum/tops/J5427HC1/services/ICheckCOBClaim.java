package com.optum.tops.J5427HC1.services;

import java.sql.SQLException;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface ICheckCOBClaim {

	/**
	 * 
	 * @param individual_claim2
	 * @return
	 */
	V5427HC1 COB_claim_check(ReqClaimEntryVO individual_claim2) ;

}