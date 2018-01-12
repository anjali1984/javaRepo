package com.optum.tops.J5427HC1.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.concurrency.OneClaimTask;
import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

//Single Service that relies on all other services to process the incoming request and sends a HC1Response to the Controller
@Service
public class RequestProcessor{

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
		
		List<Thread> threadList = new ArrayList<>(); 
		
		//If NEED to be OPTIMIZED, give each requested claim a thread of its OWN for processing
		//For Each ReqClaimEntry service it by creating a V5427HC1 instance, put it in the response_list_all_claims field of HC1Response
		for(ReqClaimEntry individual_claim : claims_to_be_serviced){
			OneClaimTask task = new OneClaimTask(individual_claim, response, claims_to_be_serviced.indexOf(individual_claim), cobclaimcheck, opshcfacheck,cobln2121,cobln2131,instlRed2140,instlLoad2150) ;
			Thread t = new Thread(task); 
			t.setName("" + claims_to_be_serviced.indexOf(individual_claim));
			threadList.add(t);
			t.start();
		}

		System.out.println("Waiting for Child Threads to die");

	    for (Thread thread : threadList) {
	        try {
	            thread.join();
	            System.out.println(thread.getName() + " Finished its job");             
	        } catch (InterruptedException e) {
	            System.out.println("Interrupted Exception thrown by : "
	                    + thread.getName());                
	        }
	    }
	    System.out.println("All Child Threads Finished their Job");
		System.out.println("Response size is " + response.getResponse_map_all_claims().size());
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


