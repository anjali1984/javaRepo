package com.optum.tops.J5427HC1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.CheckCOBClaim;
import com.optum.tops.J5427HC1.services.Ops_Hcfa;

@Controller
public class HC1Controller {

	@Autowired 
	CheckCOBClaim cobclaimcheck ; //Service that checks if a claim is a COB claim 
	
	@Autowired
	Ops_Hcfa opshcfacheck ; //Service
	
	@RequestMapping(value = "/COB" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HC1Response> get_COB_details(@RequestBody Hc1Request request){
		
		HC1Response response = new HC1Response() ; //to be sent back to the calling program
		List<ReqClaimEntry> claims_to_be_serviced = request.getClaimEntries() ; 
		
		//For Each ReqClaimEntry service it by creating a V5427HC1 instance, put it in the response_list_all_claims field of HC1Response
		for(ReqClaimEntry individual_claim : claims_to_be_serviced){
			V5427HC1 temp ; //Claim instance to be put in the return object 
			
			temp = cobclaimcheck.COB_claim_check(individual_claim) ; //Sets the field in the indicator object [corresponds to 1100-GET-SuFX-CD] 
			
			if(temp.getHC1_COB_COB_CLAIM_INDICATOR().equals("N")){
				response.getResponse_list_all_claims().add(temp) ;
				continue ; //Move onto the next claim
			}
			
			temp = opshcfacheck.Ops_Hcfa_claim_check(individual_claim, temp) ; //At this point PENNY_PROCESS_IND and PS_HCFA_IND must be set
			
			
			
			
		}
		
		
		
		return new ResponseEntity<HC1Response>(response, HttpStatus.OK);
	}
}
