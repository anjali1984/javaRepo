package com.optum.tops.J5427HC1.services;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.JP835RED.models.JP54RedReturn;

public interface IProfReduction2160Service {

	V5427HC1 do2160Section(ReqClaimEntryVO individual_claim2, V5427HC1 claimToBeSent);

	V5427HC1 do2161Section(V5427HC1 claimToBeSent, JP54RedReturn red_return, int clm_sub, String logId);

	/**
	 *  
	 * @param claimToBeSent
	 * @param red_return
	 * @param logId 
	 * @return
	 */
	V5427HC1 do2003PennySection(V5427HC1 claimToBeSent, JP54RedReturn red_return, String logId);

}