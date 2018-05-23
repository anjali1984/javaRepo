package com.optum.tops.J5427HC1.services;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface ICOBLN2131Service {

	V5427HC1 do2131Logic(ReqClaimEntryVO individual_claim2, V5427HC1 outbound_claim);

}