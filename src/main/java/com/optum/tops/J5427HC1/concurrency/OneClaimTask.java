package com.optum.tops.J5427HC1.concurrency;

import java.math.BigDecimal;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.COBLN2121Service;
import com.optum.tops.J5427HC1.services.COBLN2131Service;
import com.optum.tops.J5427HC1.services.CheckCOBClaim;
import com.optum.tops.J5427HC1.services.InstlReduction2140Service;
import com.optum.tops.J5427HC1.services.LoadCobLnLineAmtsService2150;
import com.optum.tops.J5427HC1.services.LoadSumForReductService2170;
import com.optum.tops.J5427HC1.services.OpsHcfaService;
import com.optum.tops.J5427HC1.services.ProfReduction2160Service;

public class OneClaimTask implements Runnable {
	ReqClaimEntry individual_claim;
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

	// Constructor for the Task,
	public OneClaimTask(ReqClaimEntry individual_claim, HC1Response reponse, int index, CheckCOBClaim cobclaimcheck,
			OpsHcfaService opshcfacheck, COBLN2121Service cobln2121, COBLN2131Service cobln2131,
			InstlReduction2140Service instlRed2140, LoadCobLnLineAmtsService2150 instlLoad2150,
			ProfReduction2160Service profRed2160, LoadSumForReductService2170 profLoad2170) {
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
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		processOneClaimTask(this.individual_claim, response_this_thread_will_add_to, index_to_put);

	}

	private void processOneClaimTask(ReqClaimEntry individual_claim, HC1Response response, int index) {
		// TODO Auto-generated method stub
		System.out.println("========================================NEW REQUESTED CLAIM==============================");
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
			response.getResponse_map_all_claims().put(position_of_claim_in_requestlist, currentClaim);
			return; // Move onto the next claim
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
			currentClaim = cobln2131.do2131Logic(individual_claim, currentClaim);
		}
		// Institutional Claims
		if (currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
				&& currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y")) {
			System.out.println("In 2140, 2150 sections");
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
				currentClaim = instlRed2140.do2140Section(individual_claim, currentClaim);
			}
			// 2150-LOAD-COBLN-LINE-AMTS
			currentClaim = instlLoad2150.do2150Section(currentClaim, individual_claim.getHc1_REQ_CLM_TRANS_CD().trim());
		} else {
			// Professional Claims
			if ((currentClaim.getHC1_COB_INST_OR_PROF().equals("P")
					|| currentClaim.getHC1_COB_INST_OR_PROF().trim().equals("")
							&& (currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("Y")
									|| currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M")))
					|| (currentClaim.getHC1_COB_INST_OR_PROF().equals("I")
							&& currentClaim.getMy_indicator().getDBKE2_835_COB_PROC_IND().equals("M"))) {
				System.out.println("In 2160, 2170 sections");
				// Perform 2160 , 2170 [i.e. Call DP835RED with func cd = 2]
				currentClaim = profRed2160.do2160Section(individual_claim, currentClaim);
				profLoad2170.do2170(currentClaim, individual_claim.getHc1_REQ_CLM_TRANS_CD().trim());

			}
		}

		/*
		 * 2200-WRTOFF-CALC
		 */
		if (currentClaim.getHC1_COB_COB_CLAIM_INDICATOR().equals("Y")) {
			System.out.println("Doing 2200-WRTOFF-CALC");
			currentClaim = WriteOff2200(currentClaim);
		}
		
		// Doing this because these working storage fields are not required by
		// the request
		currentClaim.setMy_indicator(null);
		System.out.println("Thread " + Thread.currentThread() + " adding V5427HC1 object to ConcurrentMapofResponse");
		response.getResponse_map_all_claims().put(position_of_claim_in_requestlist, currentClaim);
		System.out.println("Thread " + Thread.currentThread() + " Added V5427HC1 object to ConcurrentMapofResponse");

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
					System.out.println("in 2200 med amnt " + currentClaim.getHC1_COB_MEDC_PAID_AMT());
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
					System.out.println(
							"In 2200, modifying the prv_wrt_off amount of the line, based on the 4 other line level amounts:  "
									+ compute);
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
				System.out.println("currentClaim.setHC1_COB_PRV_WRT_OFF() " + compute);

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