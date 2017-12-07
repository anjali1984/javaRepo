package com.optum.tops.J5427HC1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.dao.D54DAO.CheckOPS_HCFADAO;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

//If Penny Process Claim, determines OPS_HCFA_INDICATOR for the claim, 
//then For Institutional OR OPS_HCFA_IND="Y" PERFORM 2002-GET-ORIGHDR-DETAILS-SECT 
@Service
public class Ops_Hcfa {
	
	@Autowired 
	CheckOPS_HCFADAO OPSHCFA_claim_checker ; 
	
	public V5427HC1 Ops_Hcfa_claim_check(ReqClaimEntry incoming_claim, V5427HC1 claim){
		//ITs a COB Claim, Do 2000-Processing Section (Penny Proc indicator already set in CheckCOBDao) 
		if(claim.getMy_indicator().getPENNY_PROC_INDICATOR().equals("Y") && (claim.getHC1_COB_INST_OR_PROF().trim().equals("P") || claim.getHC1_COB_INST_OR_PROF().trim().equals("") )){
			claim = OPSHCFA_claim_checker.am_i_OPS_HCFA(incoming_claim, claim) ; 
		}
		
		return claim ; 
	}
}
