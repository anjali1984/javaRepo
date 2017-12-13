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
	OpsHcfaService opshcfacheck ; //Service for checking OPS_HCFA
	
	@Autowired
	COBLN2121Service cobln2121 ; 
	
	@Autowired
	COBLN2131Service cobln2131; 
	
	
	public HC1Response process (Hc1Request request){
		HC1Response response = new HC1Response() ; //to be sent back to the HC1Controller
		List<ReqClaimEntry> claims_to_be_serviced = request.getClaimEntries() ; 
		
		System.out.println("REQUEST with " + claims_to_be_serviced.size() + " Recieved");
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
			temp= cobln2121.getResultsCobln_Line_Flds(individual_claim, temp);  //2121-FETCH-COBLN-LINE-AMTS and all business logic for this claim, CALL_OIMC_TBL_INDICATOR must be set
			
			if(temp.getMy_indicator().getCALL_OIMC_TBL_INDICATOR().equals("Y")){
				//PERFORM 2130-GET-COB-SERV-CALC-DATA  (2131-FETCH-COB-SERV-CALCS) 
				temp = cobln2131.do2131Logic(individual_claim, temp);	
			}
			
			if(temp.getHC1_COB_INST_OR_PROF().equals("I") && temp.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y")){
				//Perform 2140-GET-Instl-Reductions
				
				
			}else{
				if( (temp.getHC1_COB_INST_OR_PROF().equals("P") || temp.getHC1_COB_INST_OR_PROF().trim().equals("") && (temp.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y") || temp.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M"))) 
						|| (temp.getHC1_COB_INST_OR_PROF().equals("I") && temp.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M")) 
				){
					//Perform 2160 , 2170 
				}
			}
			
			//Make Sure the indicator object is wiped off!!!!!!!!!!!!!
			response.getResponse_list_all_claims().add(temp) ; 
			
		}
		
		return response ;
		
	}
}
