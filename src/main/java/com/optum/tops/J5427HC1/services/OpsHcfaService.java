package com.optum.tops.J5427HC1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.dao.D54DAO.CheckOPS_HCFADAO;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

//If Penny Process Claim, determines OPS_HCFA_INDICATOR for the claim, 
//then For Institutional OR OPS_HCFA_IND="Y" PERFORM 2002-GET-ORIGHDR-DETAILS-SECT 
//
@Service
public class OpsHcfaService {
	
	@Autowired 
	CheckOPS_HCFADAO OPSHCFA_claim_checker ; 
	
	public V5427HC1 Ops_Hcfa_claim_check(ReqClaimEntryVO individual_claim2, V5427HC1 claim){
		//ITs a COB Claim, Do 2000-Processing Section (Penny Proc indicator already set in CheckCOBDao) 
		if(claim.getMy_indicator().getPENNY_PROC_INDICATOR().equals("Y") && (claim.getHC1_COB_INST_OR_PROF().trim().equals("P") || claim.getHC1_COB_INST_OR_PROF().trim().equals("") )){
			claim = OPSHCFA_claim_checker.am_i_OPS_HCFA(individual_claim2, claim) ; 
		}
		
		return claim ; 
	}
	
	public V5427HC1 get_CSR_ORIGHDR_DATA(ReqClaimEntryVO individual_claim2, V5427HC1 claim){
		//FOR PENNY-PROCESS-YES, GET ORIGHDR DETAILS IF INST OR OPS-HCFA 
		if(		claim.getMy_indicator().getPENNY_PROC_INDICATOR().equals("Y") && 
				(claim.getHC1_COB_INST_OR_PROF().equals("I") || claim.getMy_indicator().getOPS_HCFA_INDICATOR().equals("Y")) ){
			claim = OPSHCFA_claim_checker.get_CSR_ORIGHDR(individual_claim2, claim) ; 
		}
		
		claim.getMy_indicator().setCALL_OIMC_TBL_INDICATOR("N"); // 815 MOVE 'N' TO WS-CALL-OIMC-TBL-SW. sets default for this indicator
		
		return claim ; 
	}
}
