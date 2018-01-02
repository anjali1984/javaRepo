package com.optum.tops.J5427HC1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.JP835RED.FuncCodeProcessing;
import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.JP54RedReturn;

//Single Service that relies on all other services to process the incoming request and sends a HC1Response to the Controller
@Service
public class RequestProcessor {

	@Autowired 
	CheckCOBClaim cobclaimcheck ; //Service that checks if a claim is a COB claim, NYSTATECOB, Penny Process claim
	
	@Autowired
	OpsHcfaService opshcfacheck ; //Service for checking OPS_HCFA
	
	@Autowired
	COBLN2121Service cobln2121 ; 
	
	@Autowired
	COBLN2131Service cobln2131; 
	
	@Autowired
	FuncCodeProcessing red_Processor ; 
	
	
	public HC1Response process (Hc1Request request){
		HC1Response response = new HC1Response() ; //to be sent back to the HC1Controller
		List<ReqClaimEntry> claims_to_be_serviced = request.getClaimEntries() ; 
		
		System.out.println("REQUEST with " + claims_to_be_serviced.size() + " Recieved");
		//For Each ReqClaimEntry service it by creating a V5427HC1 instance, put it in the response_list_all_claims field of HC1Response
		for(ReqClaimEntry individual_claim : claims_to_be_serviced){
			V5427HC1 currentClaim ; //Claim instance to be put in the return object 
			
			currentClaim = cobclaimcheck.COB_claim_check(individual_claim) ; //Sets the field in the indicator object [corresponds to 1100-GET-SuFX-CD] 
			
			if(currentClaim.getHC1_COB_COB_CLAIM_INDICATOR().equals("N")){
				//Doing this because these working storage fields are not required by the request
				currentClaim.setMy_indicator(null);
				response.getResponse_list_all_claims().add(currentClaim) ;
				continue ; //Move onto the next claim
			}
			
			currentClaim = opshcfacheck.Ops_Hcfa_claim_check(individual_claim, currentClaim) ; //At this point PENNY_PROCESS_IND and OPS_HCFA_IND must be set
			currentClaim = opshcfacheck.get_CSR_ORIGHDR_DATA(individual_claim, currentClaim) ; //ORIGHDR details retrieved if it meets the condition in Ops_Hcfa Service 
			currentClaim= cobln2121.getResultsCobln_Line_Flds(individual_claim, currentClaim);  //2121-FETCH-COBLN-LINE-AMTS and all business logic for this claim, CALL_OIMC_TBL_INDICATOR must be set
			
			if(currentClaim.getMy_indicator().getCALL_OIMC_TBL_INDICATOR().equals("Y")){
				//PERFORM 2130-GET-COB-SERV-CALC-DATA  (2131-FETCH-COB-SERV-CALCS) 
				currentClaim = cobln2131.do2131Logic(individual_claim, currentClaim);	
			}
			
			//Institutional Claims
			if(currentClaim.getHC1_COB_INST_OR_PROF().equals("I") && currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y")){
				//Perform 2140-GET-Instl-Reductions [i.e. Call DP835RED with func cd = 1] 
				JP54RedRequest request_to_RED = new JP54RedRequest() ; 
				request_to_RED.setRED_INV_CTL_NBR(individual_claim.getHc1_REQ_CLM_INVN_CTL_NBR());
				request_to_RED.setRED_ICN_SUFX_CD(currentClaim.getMy_indicator().getDBKE2_ICN_SUFX_CD());
				request_to_RED.setRED_PROC_DT(individual_claim.getHc1_REQ_CLM_PROC_DT());
				request_to_RED.setRED_PROC_TM(individual_claim.getHc1_REQ_CLM_PROC_TM());
				
				JP54RedReturn red_return = red_Processor.InstClaim2100(request_to_RED);
				
			}else{
				//Professional Claims
				if( (currentClaim.getHC1_COB_INST_OR_PROF().equals("P") || currentClaim.getHC1_COB_INST_OR_PROF().trim().equals("") && (currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y") || currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M"))) 
						|| (currentClaim.getHC1_COB_INST_OR_PROF().equals("I") && currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M")) 
				){
					//Perform 2160 , 2170  [i.e. Call DP835RED with func cd = 2] 
				}
			}
			
			
			//Doing this because these working storage fields are not required by the request
			currentClaim.setMy_indicator(null);
			response.getResponse_list_all_claims().add(currentClaim) ; 
			
		}
		
		return response ;
		
	}
}
