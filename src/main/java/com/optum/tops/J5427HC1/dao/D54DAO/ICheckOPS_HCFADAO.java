package com.optum.tops.J5427HC1.dao.D54DAO;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface ICheckOPS_HCFADAO {

	V5427HC1 am_i_OPS_HCFA(ReqClaimEntryVO individual_claim2, V5427HC1 claim);

	//Gets the rows from ADJD_CLMSF_ORIGHDR, puts at max 150 of them into the IndicatorObject of the claim to be returned 
	//Corresponds to 2002-GET-ORIGHDR-DETAILS-SECT SECTION. 
	V5427HC1 get_CSR_ORIGHDR(ReqClaimEntryVO incoming_claim, V5427HC1 claim);

}