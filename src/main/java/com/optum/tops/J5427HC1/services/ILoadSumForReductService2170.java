package com.optum.tops.J5427HC1.services;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface ILoadSumForReductService2170 {

	/**
	 * 2170-LOAD-SUM-FOR-REDUCTIONS
	 * Load each of the prof reductions from the reduction  table(LineReductionHold) to the associated respective fields in the return claim, 
	 * V5427HC1 only when one of the reduction each occurrence has dollar amts > zeros.
	 * @param req
	 * @param claimToBeSent
	 * @param logId 
	 * @param trnsCd
	 * @return
	 */
	V5427HC1 do2170(V5427HC1 claimToBeSent, ReqClaimEntryVO individual_claim);

}