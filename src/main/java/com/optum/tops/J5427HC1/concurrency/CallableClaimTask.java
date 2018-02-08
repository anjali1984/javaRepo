package com.optum.tops.J5427HC1.concurrency;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.COBLN2121Service;
import com.optum.tops.J5427HC1.services.COBLN2131Service;
import com.optum.tops.J5427HC1.services.COBONL2200Service;
import com.optum.tops.J5427HC1.services.CheckCOBClaim;
import com.optum.tops.J5427HC1.services.InstlReduction2140Service;
import com.optum.tops.J5427HC1.services.LoadCobLnLineAmtsService2150;
import com.optum.tops.J5427HC1.services.LoadSumForReductService2170;
import com.optum.tops.J5427HC1.services.OpsHcfaService;
import com.optum.tops.J5427HC1.services.ProfReduction2160Service;

public class CallableClaimTask implements Callable<V5427HC1> {
	ReqClaimEntryVO individual_claim;
	HC1Response response_this_thread_will_add_to; // COMMON resource across all
													// threads
	int index_to_put; // The key for the Concurrent HashMap in which the
						// returned claim object will be put to.

	CheckCOBClaim cobclaimcheck; // Service that checks if a claim is a COB
									// claim, NYSTATECOB, Penny Process claim
	OpsHcfaService opshcfacheck; // Service for checking OPS_HCFA
	COBLN2121Service cobln2121;
	COBLN2131Service cobln2131;
	InstlReduction2140Service instlRed2140; // A Service that utilizes another
											// service RedProcessor for getting
											// DP835RED Data
	LoadCobLnLineAmtsService2150 instlLoad2150;
	ProfReduction2160Service profRed2160;
	LoadSumForReductService2170 profLoad2170;
COBONL2200Service cOBONL2200Service;
	
	Logger logger=Logger.getLogger("genLogger");

	// Constructor for the Task,
	public CallableClaimTask(ReqClaimEntryVO individual_claim, HC1Response reponse, int index, CheckCOBClaim cobclaimcheck,
			OpsHcfaService opshcfacheck, COBLN2121Service cobln2121, COBLN2131Service cobln2131,
			InstlReduction2140Service instlRed2140, LoadCobLnLineAmtsService2150 instlLoad2150,
			ProfReduction2160Service profRed2160, LoadSumForReductService2170 profLoad2170,COBONL2200Service cOBONL2200Service) {
		// store parameter for later user
		this.individual_claim = individual_claim;
		this.response_this_thread_will_add_to = reponse;
		this.index_to_put = index;
		this.cobclaimcheck = cobclaimcheck;
		this.opshcfacheck = opshcfacheck;
		this.cobln2121 = cobln2121;
		this.cobln2131 = cobln2131;
		this.instlRed2140 = instlRed2140;
		this.instlLoad2150 = instlLoad2150;
		this.profRed2160=profRed2160;
		this.profLoad2170=profLoad2170;
		this.cOBONL2200Service=cOBONL2200Service;

	}

	@Override
	public V5427HC1 call() {
		// TODO Auto-generated method stub
		V5427HC1 returned_claim = processOneClaimTask(this.individual_claim, response_this_thread_will_add_to, index_to_put);
		return returned_claim;

	}

	private V5427HC1 processOneClaimTask(ReqClaimEntryVO individual_claim, HC1Response response, int index) {
		// TODO Auto-generated method stub
		//System.out.println("========================================NEW REQUESTED CLAIM==============================");
		String location="J5427HC1.concurrency.CallableClaimTask.processOneClaimTask(ReqClaimEntryVO, HC1Response, int)";
		int position_of_claim_in_requestlist = index; // claims_to_be_serviced.indexOf(individual_claim);
		V5427HC1 currentClaim; // Claim instance to be put in the return object
		currentClaim = cobclaimcheck.COB_claim_check(individual_claim); // Sets
																		// the
																		// field
																		// in
																		// the
																		// indicator
																		// object
																		// [corresponds
																		// to
																		// 1100-GET-SuFX-CD]
		if (currentClaim.getHC1_COB_COB_CLAIM_INDICATOR().equals("N")) {
			// Doing this because these working storage fields are not required
			// by the request
			currentClaim.setMy_indicator(null);
			synchronized(response){
				response.getResponse_map_all_claims().put(position_of_claim_in_requestlist, currentClaim);
			}
			return currentClaim; // Move onto the next claim
		}
		currentClaim = opshcfacheck.Ops_Hcfa_claim_check(individual_claim, currentClaim); // At
																							// this
																							// point
																							// PENNY_PROCESS_IND
																							// and
																							// OPS_HCFA_IND
																							// must
																							// be
																							// set
		currentClaim = opshcfacheck.get_CSR_ORIGHDR_DATA(individual_claim, currentClaim); // ORIGHDR
																							// details
																							// retrieved
																							// if
																							// it
																							// meets
																							// the
																							// condition
																							// in
																							// Ops_Hcfa
																							// Service
		currentClaim = cobln2121.getResultsCobln_Line_Flds(individual_claim, currentClaim); // 2121-FETCH-COBLN-LINE-AMTS
																							// and
																							// all
																							// business
																							// logic
																							// for
																							// this
																							// claim,
																							// CALL_OIMC_TBL_INDICATOR
																							// must
																							// be
																							// set

		if (currentClaim.getMy_indicator().getCALL_OIMC_TBL_INDICATOR().equals("Y")) {
			// PERFORM 2130-GET-COB-SERV-CALC-DATA (2131-FETCH-COB-SERV-CALCS)
			logger.info(location.concat(" Start do2131Logic as CALL_OIMC_TBL_INDICATOR is  ")
					.concat("[").concat(currentClaim.getMy_indicator().getCALL_OIMC_TBL_INDICATOR()).concat("]").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

			currentClaim = cobln2131.do2131Logic(individual_claim, currentClaim);
			logger.info(location.concat(" do2131Logic Completed  ").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

		}
		// Institutional Claims
		if (currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
				&& currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y")) {
			//System.out.println("In 2140, 2150 sections");
			logger.info(location.concat("Inside If- Institutional Claim before 2140/2150 ").concat("COB_INST_OR_PROF():").concat("[").concat(currentClaim.getHC1_COB_INST_OR_PROF()).concat("]").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

			if (currentClaim.getMy_indicator().getCXINT_CLAIM_INDICATOR().equals("N")) { // If
																							// this
																							// is
																							// a
																							// Yes
																							// you
																							// dont
																							// have
																							// to
																							// do
																							// 2140
																							// and
																							// 2141
																							// sections
				// Perform 2140-GET-Instl-Reductions [i.e. Call DP835RED with
				// func cd = 1]
				logger.info(location.concat(" Start do2140Section as CXINT_CLAIM_INDICATOR is  ")
						.concat("[").concat(currentClaim.getMy_indicator().getCXINT_CLAIM_INDICATOR()).concat("]").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

				currentClaim = instlRed2140.do2140Section(individual_claim, currentClaim);
				logger.info(location.concat(" do2140Section Completed  ").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

			}
			// 2150-LOAD-COBLN-LINE-AMTS
			logger.info(location.concat(" Start do2150Section ")
					.concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));
		
			currentClaim = instlLoad2150.do2150Section(currentClaim, individual_claim);
			logger.info(location.concat(" do2150Section Completed  ").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

		} else {
			// Professional Claims
			logger.info(location.concat("Inside else  ").concat("COB_INST_OR_PROF():").concat("[").concat(currentClaim.getHC1_COB_INST_OR_PROF()).concat("]").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

			if ((currentClaim.getHC1_COB_INST_OR_PROF().equals("P")
					|| currentClaim.getHC1_COB_INST_OR_PROF().trim().equals("")
							&& (currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y")
									|| currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M")))
					|| (currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
							&& currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M"))) {
				//System.out.println("In 2160, 2170 sections");
				// Perform 2160 , 2170 [i.e. Call DP835RED with func cd = 2]
				logger.info(location.concat(" Start do2160Section ")
						.concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));
				currentClaim = profRed2160.do2160Section(individual_claim, currentClaim);
				logger.info(location.concat(" do2160Section Completed  ").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));


				logger.info(location.concat(" Start do2170Section ")
						.concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

				profLoad2170.do2170(currentClaim, individual_claim);
				logger.info(location.concat(" do2170Section Completed  ").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

			}
		}

		/*
		 * 2200-WRTOFF-CALC
		 */
		if (currentClaim.getHC1_COB_COB_CLAIM_INDICATOR().equals("Y")) {
			logger.info(location.concat("Perform 2200-WRTOFF-CALC if HC1_COB_COB_CLAIM_INDICATOR= ").concat("[").concat(currentClaim.getHC1_COB_COB_CLAIM_INDICATOR()).concat("]").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));
			currentClaim = WriteOff2200(currentClaim);
			logger.info(location.concat(" 2200-WRTOFF-CALC Completed ").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

		}
		
		// Doing this because these working storage fields are not required by
		// the request
		currentClaim.setMy_indicator(null);
		
		
		//System.out.println("Thread " + Thread.currentThread() + " adding V5427HC1 object to ConcurrentMapofResponse");
		
		synchronized(response){
		response.getResponse_map_all_claims().put(position_of_claim_in_requestlist, currentClaim);
		}
		
		//System.out.println("Thread " + Thread.currentThread() + " Added V5427HC1 object to ConcurrentMapofResponse");
		
		
		return currentClaim;
	}

	/**
	 * java version of 2200-WRTOFF-CALC section of COBOL program D5427HC1
	 * Functionality: 1.When a cob claim, initializes the write off for
	 * recalculation 2.Separate calculations based on if the claim a
	 * professional or institutional claim
	 * 
	 * @param currentClaim
	 */
	private V5427HC1 WriteOff2200(V5427HC1 currentClaim) {

		/* anjali:sysout and compute can be removed once tested. */

		try {
			currentClaim.setHC1_COB_PRV_WRT_OFF(BigDecimal.ZERO);
			/*
			 * Recalculation for professional or medicare estimated service
			 * lines
			 */

			if (((currentClaim.getHC1_COB_INST_OR_PROF().equals("P")
					|| currentClaim.getHC1_COB_INST_OR_PROF().equals(""))
					&& (currentClaim.getHC1_COB_COB_835_PROC_IND().equals("Y")
							|| currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M")))
					|| (currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
							&& currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M"))) {
				/*
				 * BigDecimal.ZEROing out medicare paid on claim leve for
				 * institutional and professional medicare claims
				 */
				if ((currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
						|| currentClaim.getHC1_COB_INST_OR_PROF().equals("P"))
						&& currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M")) {
					currentClaim.setHC1_COB_MEDC_PAID_AMT(BigDecimal.ZERO);
					//System.out.println("in 2200 med amnt " + currentClaim.getHC1_COB_MEDC_PAID_AMT());
				}
				int cobDxCnt = 0;

				while (cobDxCnt < currentClaim.getHC1_COB_NBR_LINES()) {
					/*
					 * For inst. and prof medicare claims BigDecimal.ZEROes to
					 * medicare paid amount on all line levels
					 */
					if ((currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
							|| currentClaim.getHC1_COB_INST_OR_PROF().equals("P"))
							&& currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M")) {
						currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt)
								.setHC1_COB_LN_EOB_MEDC_PAID_AMT(BigDecimal.ZERO);
					}
					BigDecimal compute = currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt)
							.getHC1_COB_LN_835_PRV_NC_AMT()
							.add(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt)
									.getHC1_COB_LN_835_COB_PRIM_IMPAC())
							.subtract(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt)
									.getHC1_COB_LN_EOB_MEDC_PAID_AMT())
							.subtract(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt)
									.getHC1_COB_LN_EOB_OI_PAID_AMT());
//					System.out.println(
//							"In 2200, modifying the prv_wrt_off amount of the line, based on the 4 other line level amounts:  "
//									+ compute);
					currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_COB_PRV_WRT_OFF(compute);
					/*
					 * Provider write-off changed to BigDecimal.ZERO at line
					 * level for inst. medicare estimated claims
					 */
					if (currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M")
							&& currentClaim.getHC1_COB_INST_OR_PROF().equals("I")) {
						currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt)
								.setHC1_COB_LN_COB_PRV_WRT_OFF(BigDecimal.ZERO);
					}
					currentClaim.setHC1_COB_PRV_WRT_OFF(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt)
							.getHC1_COB_LN_COB_PRV_WRT_OFF().add(currentClaim.getHC1_COB_PRV_WRT_OFF()));
					cobDxCnt++;
				}
			}
			if (currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
					&& currentClaim.getHC1_COB_COB_835_PROC_IND().equals("Y")) {
				BigDecimal compute = ((currentClaim.getHC1_COB_835_PRV_NC_AMT()
						.add(currentClaim.getHC1_COB_835_COB_PRIM_IMPAC()))
								.subtract(currentClaim.getHC1_COB_MEDC_PAID_AMT()))
										.subtract(currentClaim.getHC1_COB_OI_PAID_AMT());
				currentClaim.setHC1_COB_PRV_WRT_OFF(compute);
				//System.out.println("currentClaim.setHC1_COB_PRV_WRT_OFF() " + compute);

				for (int i = 0; i < 7; i++)
					currentClaim.getHC1_COB_LNE_DATA_AREA().get(i).setHC1_COB_LN_COB_PRV_WRT_OFF(BigDecimal.ZERO);
			}
			return currentClaim;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return currentClaim;
	}
}

