package com.optum.tops.J5427HC1.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.JP835RED.RedProcessor;
import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;
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
	InstlReduction2140Service instlRed2140; //A Service that utilizes another service RedProcessor for getting DP835RED Data

	@Autowired 
	LoadCobLnLineAmtsService2150 instlLoad2150; 

	public HC1Response process (Hc1Request request){
		HC1Response response = new HC1Response() ; //to be sent back to the HC1Controller
		List<ReqClaimEntry> claims_to_be_serviced = request.getClaimEntries() ; 
		//If NEED to be OPTIMIZED, give each requested claim a thread of its OWN for processing
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
				if(currentClaim.getMy_indicator().getCXINT_CLAIM_INDICATOR().equals("N")){ // If this is a Yes you dont have to do 2140 and 2141 sections
					//Perform 2140-GET-Instl-Reductions [i.e. Call DP835RED with func cd = 1] 
					currentClaim = instlRed2140.do2140Section(individual_claim, currentClaim);
				}
				//2150-LOAD-COBLN-LINE-AMTS
				currentClaim = instlLoad2150.do2150Section(currentClaim, individual_claim.getHc1_REQ_CLM_TRANS_CD().trim());
			}else{
				//Professional Claims
				if( (currentClaim.getHC1_COB_INST_OR_PROF().equals("P") || currentClaim.getHC1_COB_INST_OR_PROF().trim().equals("") && (currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y") || currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M"))) 
						|| (currentClaim.getHC1_COB_INST_OR_PROF().equals("I") && currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M")) 
						){
					//Perform 2160 , 2170  [i.e. Call DP835RED with func cd = 2] 
				}
			}
			//??? PUTthis in RED PORTION for both Insti and Professional
			// 2003-PENNY-PROCESS-ERROR-SECT  
			/*55367A*82 1. CHECK FOR SELECTIVE SERVICE LINE ERRORS ON THE BASIS OF  * 09760000
			55367A*82    SVC-LINE-PENNY-YES FLAG, IF IT IS EXIST THEN MOVE THE    * 09770000
			55367A*82    SPACES INTO DHC1-COB-835-OOB-ERROR. 
			 */
			//private List<String> HC1_COB_835_OOB_ERROR = new ArrayList<String>(); in the ClaimtobeSent i.e. currentClaim
			//currentClaim.getHC1_COB_835_OOB_ERROR() its a List<String>

			/*
			 * 2200-WRTOFF-CALC 
			 */
			if(currentClaim.getHC1_COB_COB_CLAIM_INDICATOR().equals("Y")){
				WriteOff2200(currentClaim);
			}
			//Doing this because these working storage fields are not required by the request
			currentClaim.setMy_indicator(null);
			response.getResponse_list_all_claims().add(currentClaim) ; 
		}
		return response ;
	}
	/**
	 * java version of 2200-WRTOFF-CALC section of COBOL program D5427HC1
	 * Functionality:
	 * 1.When a cob claim, initializes the write off for recalculation
	 * 2.Separate calculations based on if the claim a professional or institutional claim
	 * @param currentClaim
	 */
	public void WriteOff2200 (V5427HC1 currentClaim){
		
		  /*anjali:sysout and compute can be removed once tested.*/
		 
		try {
			currentClaim.setHC1_COB_PRV_WRT_OFF(BigDecimal.ZERO);
			/*
			 * Recalculation for professional or medicare estimated   service lines
			 */
			if (((currentClaim.getHC1_COB_INST_OR_PROF().equals("P") || currentClaim.getHC1_COB_INST_OR_PROF().equals("")) && (currentClaim.getHC1_COB_COB_835_PROC_IND().equals("Y") || currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M")))
					||
					(currentClaim.getHC1_COB_INST_OR_PROF().equals("I") && currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M")))
			{
				/*
				 * BigDecimal.ZEROing out medicare paid on claim leve  for institutional and professional medicare claims  
				 */
				if((currentClaim.getHC1_COB_INST_OR_PROF().equals("I") || currentClaim.getHC1_COB_INST_OR_PROF().equals("P"))
						&&
						currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M"))
				{
					currentClaim.setHC1_COB_MEDC_PAID_AMT(BigDecimal.ZERO);
				}
				int cobDxCnt=0;
				do {
					/*
					 * For inst. and prof medicare claims BigDecimal.ZEROes to medicare paid amount on all line levels   
					 */
					if((currentClaim.getHC1_COB_INST_OR_PROF().equals("I") || currentClaim.getHC1_COB_INST_OR_PROF().equals("P"))
							&&
							currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M"))
					{
						currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_EOB_MEDC_PAID_AMT(BigDecimal.ZERO);
					}
					BigDecimal compute=currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).getHC1_COB_LN_835_PRV_NC_AMT()
							.add(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).getHC1_COB_LN_835_COB_PRIM_IMPAC())
							.subtract(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).getHC1_COB_LN_EOB_MEDC_PAID_AMT())
							.subtract(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).getHC1_COB_LN_EOB_OI_PAID_AMT());
					currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_COB_PRV_WRT_OFF(compute);
					/*
					 * Provider write-off changed to BigDecimal.ZERO at line   level for inst. medicare estimated claims
					 */
					if(currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M") && currentClaim.getHC1_COB_INST_OR_PROF().equals("I"))
					{
						currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_COB_PRV_WRT_OFF(BigDecimal.ZERO);
					}
					currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).getHC1_COB_LN_COB_PRV_WRT_OFF().add(currentClaim.getHC1_COB_PRV_WRT_OFF());
					cobDxCnt++;
				}while(cobDxCnt < currentClaim.getHC1_COB_NBR_LINES());
			}
			if(currentClaim.getHC1_COB_INST_OR_PROF().equals("I") && currentClaim.getHC1_COB_COB_835_PROC_IND().equals("Y"))
			{
				BigDecimal compute=((currentClaim.getHC1_COB_835_PRV_NC_AMT().add(currentClaim.getHC1_COB_835_COB_PRIM_IMPAC())).subtract(currentClaim.getHC1_COB_MEDC_PAID_AMT()))
						.subtract(currentClaim.getHC1_COB_OI_PAID_AMT());
				currentClaim.setHC1_COB_PRV_WRT_OFF(compute);
				System.out.println("currentClaim.setHC1_COB_PRV_WRT_OFF() "+compute);

				for (int i=0;i<7;i++)
					currentClaim.getHC1_COB_LNE_DATA_AREA().get(i).setHC1_COB_LN_COB_PRV_WRT_OFF(BigDecimal.ZERO);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}
	}
}
