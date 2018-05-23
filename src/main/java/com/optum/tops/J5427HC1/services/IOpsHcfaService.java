package com.optum.tops.J5427HC1.services;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface IOpsHcfaService {

	V5427HC1 Ops_Hcfa_claim_check(ReqClaimEntryVO individual_claim2, V5427HC1 claim);

	V5427HC1 get_CSR_ORIGHDR_DATA(ReqClaimEntryVO individual_claim2, V5427HC1 claim);

}