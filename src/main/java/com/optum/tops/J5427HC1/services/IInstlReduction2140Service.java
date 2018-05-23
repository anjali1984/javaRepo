package com.optum.tops.J5427HC1.services;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface IInstlReduction2140Service {

	V5427HC1 do2140Section(ReqClaimEntryVO individual_claim2, V5427HC1 claimToBeSent);

}