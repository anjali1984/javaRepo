package com.optum.tops.J5427HC1.services.Imp;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.dao.D54DAO.ICheckCOBDao;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.ICheckCOBClaim;

//Service for Check if the claim is a COB claim or not 

@Service
public class CheckCOBClaim implements ICheckCOBClaim {

	@Autowired 
	ICheckCOBDao COB_claim_checker ; 
	
	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.services.ICheckCOBClaim#COB_claim_check(com.optum.tops.J5427HC1.models.ReqClaimEntryVO)
	 */
	@Override
	public V5427HC1 COB_claim_check(ReqClaimEntryVO individual_claim2){
		return COB_claim_checker.amICobClaim(individual_claim2) ; 
	}
}
