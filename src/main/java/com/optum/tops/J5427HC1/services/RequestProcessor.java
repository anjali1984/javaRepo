package com.optum.tops.J5427HC1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

//Single Service that relies on all other services to process the incoming request and sends a HC1Response to the Controller
@Service
public class RequestProcessor {

	@Autowired 
	CheckCOBClaim cobclaimcheck ; //Service that checks if a claim is a COB claim, NYSTATECOB, Penny Process claim
	
	@Autowired
	Ops_Hcfa opshcfacheck ; //Service for checking OPS_HCFA
	
	@Autowired
	COBLN2121Service cobln2121 ; 
	
	public HC1Response process (Hc1Request request){
		HC1Response response = new HC1Response() ; //to be sent back to the HC1Controller
		List<ReqClaimEntry> claims_to_be_serviced = request.getClaimEntries() ; 
		
		//For Each ReqClaimEntry service it by creating a V5427HC1 instance, put it in the response_list_all_claims field of HC1Response
		for(ReqClaimEntry individual_claim : claims_to_be_serviced){
			V5427HC1 temp ; //Claim instance to be put in the return object 
			
			temp = cobclaimcheck.COB_claim_check(individual_claim) ; //Sets the field in the indicator object [corresponds to 1100-GET-SuFX-CD] 
			
			if(temp.getHC1_COB_COB_CLAIM_INDICATOR().equals("N")){
				response.getResponse_list_all_claims().add(temp) ;
				continue ; //Move onto the next claim
			}
			
			temp = opshcfacheck.Ops_Hcfa_claim_check(individual_claim, temp) ; //At this point PENNY_PROCESS_IND and OPS_HCFA_IND must be set
			temp = opshcfacheck.get_CSR_ORIGHDR_DATA(individual_claim, temp) ; //ORIGHDR details retrieved if it meets the condition in Ops_Hcfa Service 
			
			temp= cobln2121.getResultsCobln_Line_Flds(individual_claim, temp);  //2121-FETCH-COBLN-LINE-AMTS and all business logic for this claim 
			
			
		}
		
		return response ;
		
	}
}
