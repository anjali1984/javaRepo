package com.optum.tops.J5427HC1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.dao.D54DAO.CheckCOBDao;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

//Service for Check if the claim is a COB claim or not 

@Service
public class CheckCOBClaim {

	@Autowired 
	CheckCOBDao COB_claim_checker ; 
	
	public V5427HC1 COB_claim_check(ReqClaimEntry claim){
		return COB_claim_checker.am_i_COB_claim(claim) ; 
	}
}
