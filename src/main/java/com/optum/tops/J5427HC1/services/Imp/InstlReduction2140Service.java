package com.optum.tops.J5427HC1.services.Imp;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.JP835RED.DAO.IRedProcessor;
import com.optum.tops.J5427HC1.models.ADJD_CLMSF_ORIGHDR_LINE;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.IInstlReduction2140Service;
import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.JP54RedReturn;
import com.optum.tops.JP835RED.models.Ret835ClmErr;
import com.optum.tops.JP835RED.models.Ret835ClmRed;

@Service
public class InstlReduction2140Service implements IInstlReduction2140Service {

	@Autowired
	IRedProcessor red_Processor ; 
	
	Logger logger=Logger.getLogger("genLogger");

	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.services.IInstlReduction2140Service#do2140Section(com.optum.tops.J5427HC1.models.ReqClaimEntryVO, com.optum.tops.J5427HC1.models.V5427HC1)
	 */
	@Override
	public V5427HC1 do2140Section(ReqClaimEntryVO individual_claim2, V5427HC1 claimToBeSent){
		String location="J5427HC1.services.InstlReduction2140Service.do2140Section(ReqClaimEntryVO, V5427HC1)";
		JP54RedRequest request_to_RED = new JP54RedRequest() ; 
		try {
			request_to_RED.setRED_INV_CTL_NBR(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_INVN_CTL_NBR());
			request_to_RED.setRED_ICN_SUFX_CD(claimToBeSent.getMy_indicator().getDBKE2_ICN_SUFX_CD());
			request_to_RED.setRED_PROC_DT(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_DT());
			request_to_RED.setRED_PROC_TM(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_TM());
			logger.info(location.concat(" Start red_Processor.InstClaim2100 ")
					.concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));
			JP54RedReturn red_return = red_Processor.InstClaim2100(request_to_RED,individual_claim2.getLogId()); //Data returned from 835RED
			logger.info(location.concat(" red_Processor.InstClaim2100 Completed  ").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

			List<Ret835ClmRed> ret835ClmRedTbl = red_return.getRet835ClmRedTbl(); 
			int clm_sub = 0 ; 
			//while (clm_sub < 10 && !ret835ClmRedTbl.get(clm_sub).getCLM_RD_CATGY_ID().equals("")) {
			while(clm_sub < ret835ClmRedTbl.size() ){

				// PERFORM 2141-SUM-CATEGORIES THRU 2141-EXIT only if its not a
				// NY-COB-PAOD-TO-STATE and NYS-SERV-LINE
				/*if (claimToBeSent.getMy_indicator().getNYSTATE_COB_CLAIM_PAIDTO().equals("S")
						&& claimToBeSent.getMy_indicator().isNYS_SERV_LINE_SW() )*/ 
				if (ret835ClmRedTbl.get(clm_sub).getCLM_RD_CATGY_ID().equals("0")){ /// ASSUMPTION WS-NYS-SERV-LINE-SW (NYS_SERV_LINE) is a Claim level field that get overridden by the loop in 2121-Section																									/// ******
					// Go onto the next record, No need to do 2141
					clm_sub++;
					continue;
				}
				logger.info(location.concat(" Start do2141Section for ret835ClmRedTbl element:")
						.concat(" LOGID:").concat("[").concat(Integer.toString(clm_sub)).concat("]")
						.concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

				claimToBeSent = do2141Section(claimToBeSent,red_return,clm_sub,individual_claim2.getLogId()); 

				logger.info(location.concat(" do2141Section Completed for ret835ClmRedTbl element: ")
						.concat(" LOGID:").concat("[").concat(Integer.toString(clm_sub)).concat("]")
						.concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

				clm_sub++;
			}

			//2140 remaining part
			//anjali:need to be tested :8 feb 2018
			if (claimToBeSent.getMy_indicator().getNYSTATE_COB_CLAIM_PAIDTO().equals("S")
					&& claimToBeSent.getMy_indicator().isNYS_SERV_LINE_SW() ) {
				int counter = 0 ; 
				while (counter < 7){
					claimToBeSent.setHC1_COB_835_PAT_RESP_DEDUC(claimToBeSent.getHC1_COB_835_PAT_RESP_DEDUC().add(claimToBeSent.getMy_indicator().getWS_LINE_DATA_AREA_TABLE().get(counter).getLN_MM_DED_AMT()));
					claimToBeSent.setHC1_COB_835_PAT_RESP_TOTAL(claimToBeSent.getHC1_COB_835_PAT_RESP_TOTAL().add(claimToBeSent.getMy_indicator().getWS_LINE_DATA_AREA_TABLE().get(counter).getLN_MM_DED_AMT()));
					counter ++; 
				}
			}

			//**PROCESS CLAIM LEVEL ERROR CODES 
			List<Ret835ClmErr> Err_table = red_return.getRet835ClmErrTbl(); 
			for(int i = 0; i < Err_table.size() ; i++ ){
				claimToBeSent.getHC1_COB_835_OOB_ERROR().add(Err_table.get(i).getCLM_ERR_CD()); 	
			}
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"),e);
			e.printStackTrace();
		}



		return claimToBeSent;
	}

	/*BUSINESS FUNCTION:                                         *  17240000
	 *81 PROCESS THE INSTL REDUCTIONS AND LOAD TO THE RETURN COPYBOOK  17250000
	 *81 V5427HC1.                                                  *  17260000
	 *81*************************************************************  17270000
	 *82*************************************************************  17280000
	 *82 2141-SUM-CATEGORIES                                        *  17290000
	 *82                                                            *  17300000
	 *82 1. FOR EACH RECORD RETURNED FROM DP835RED, MOVE THE GRP    *  17310000
	 *82    CODE, CARC CD, AND RARC CODE TO WS FIELDS.              *  17320000
	 *82                                                            *  17330000
	 *82 2. EVALUATE EACH REDUCTION AND ADD THE REDUCTION TO THE    *  17340000
	 *82    ASSOCIATED RETURN COPYBOOK FIELDS IN V5427HC1.  
	 */

	private V5427HC1 do2141Section(V5427HC1 claimToBeSent, JP54RedReturn red_return, int clm_sub, String logId) {
		String location="J5427HC1.services.InstlReduction2140Service.do2141Section(V5427HC1, JP54RedReturn, int, String)";
		try {
			List<Ret835ClmRed> ret835ClmRedTbl = red_return.getRet835ClmRedTbl();
			String ws_grp_cd = ret835ClmRedTbl.get(clm_sub).getCLM_RD_GRP_CD().trim();
			String ws_carc_cd = ret835ClmRedTbl.get(clm_sub).getCLM_RD_CARC_CD().trim();
			String ws_rarc_cd = ret835ClmRedTbl.get(clm_sub).getCLM_RD_RARC_CD().trim();
			int rev_sub = 0;
			List<ADJD_CLMSF_ORIGHDR_LINE> HVA_HOST_VARIABLE_LIST = claimToBeSent.getMy_indicator()
					.getHC1_ADJD_CLMSF_ORIGHDR_DATAAREA(); // Possibly of size 150
			// at max.
			while (rev_sub < ret835ClmRedTbl.size()) {
				Integer fmt_rvn_cd;
				try {
					fmt_rvn_cd = Integer.parseInt(HVA_HOST_VARIABLE_LIST.get(rev_sub).getRVNU_CD());
				} catch (NumberFormatException nfe) {
					fmt_rvn_cd = 0;
				}
				/*
				 * this logic  is there in cobol D5427hc1 but in java it doesnt make sense as
				 * Ret835ReductArea area will never be populated bfr this in the institutional flow
				 */
				/*if (red_return.getRet835ReductArea().get(clm_sub).getRET_835_RD_SVC_ID() == claimToBeSent.getMy_indicator()
						.getLast_line_id()
						&& fmt_rvn_cd == red_return.getRet835ReductArea().get(clm_sub).getRET_835_RD_REV_CD()
						&& HVA_HOST_VARIABLE_LIST.get(rev_sub).getPROC_CD().trim()
						.equalsIgnoreCase(red_return.getRet835ReductArea().get(clm_sub).getRET_835_RD_PROC_CD())
						&& HVA_HOST_VARIABLE_LIST.get(rev_sub).getREV_LINE_PENNY_INDICATOR().equalsIgnoreCase("Y")) {
					return claimToBeSent; // Exit out of 2141,
				}*/

				rev_sub++;
			}

			if(ws_grp_cd.trim().equalsIgnoreCase("PR")){
				claimToBeSent.setHC1_COB_835_PAT_RESP_TOTAL(claimToBeSent.getHC1_COB_835_PAT_RESP_TOTAL().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));
				//Switch CASE:
				if(ws_carc_cd.equalsIgnoreCase("45")){//PR-OVER-RC
					claimToBeSent.setHC1_COB_835_PAT_RESP_OVERC(claimToBeSent.getHC1_COB_835_PAT_RESP_OVERC().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));
				}else if(ws_carc_cd.equalsIgnoreCase("1")){//PR-DEDUCT
					claimToBeSent.setHC1_COB_835_PAT_RESP_DEDUC(claimToBeSent.getHC1_COB_835_PAT_RESP_DEDUC().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));
					if(claimToBeSent.getMy_indicator().isNY_COB_PARENT_CLM()){
						BigDecimal pat_resp_dedc = claimToBeSent.getHC1_COB_835_PAT_RESP_DEDUC(); 
						BigDecimal ln_Ny_ded_mm_amt = claimToBeSent.getMy_indicator().getWS_LINE_DATA_AREA_TABLE().get(clm_sub).getLN_NY_DED_MM_AMT();
						BigDecimal temp = pat_resp_dedc.subtract(ln_Ny_ded_mm_amt); 
						claimToBeSent.setHC1_COB_835_PAT_RESP_DEDUC(temp); 

						BigDecimal pat_resp_total = claimToBeSent.getHC1_COB_835_PAT_RESP_TOTAL();
						temp = pat_resp_total.subtract(ln_Ny_ded_mm_amt);
						claimToBeSent.setHC1_COB_835_PAT_RESP_TOTAL(temp); 
					}
				}else if(ws_carc_cd.equalsIgnoreCase("3")){//PR-COPAY
					claimToBeSent.setHC1_COB_835_PAT_RESP_COPAY(claimToBeSent.getHC1_COB_835_PAT_RESP_COPAY().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));	
				}else if(ws_carc_cd.equalsIgnoreCase("2")){//PR-COINS
					claimToBeSent.setHC1_COB_835_PAT_RESP_COINS(claimToBeSent.getHC1_COB_835_PAT_RESP_COINS().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));	
				}else if(ws_carc_cd.equalsIgnoreCase("96")){//PR-MEDC-EST-OR-DENY
					if(ws_rarc_cd.equalsIgnoreCase("N536")){//DENY-RARC
						System.out.println("HC1_COB_835_DENY_NC_AMT:"+claimToBeSent.getHC1_COB_835_DENY_NC_AMT());
						claimToBeSent.setHC1_COB_835_DENY_NC_AMT(claimToBeSent.getHC1_COB_835_DENY_NC_AMT().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));
					}
				}else{//OTHER
					claimToBeSent.setHC1_COB_835_PAT_RESP_NTCOV(claimToBeSent.getHC1_COB_835_PAT_RESP_NTCOV().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));
				}
			}else{
				if(ws_grp_cd.equalsIgnoreCase("OA") && ws_carc_cd.equalsIgnoreCase("23")){//PROVIDER-IMPACT AND PRIM-IMPACT
					claimToBeSent.setHC1_COB_835_COB_PRIM_IMPAC(claimToBeSent.getHC1_COB_835_COB_PRIM_IMPAC().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));
				}else{
					if(ws_grp_cd.equalsIgnoreCase("CO")){ //PROVIDER-ADJUSTMENT 
						if(ws_carc_cd.equalsIgnoreCase("94") || ws_carc_cd.equalsIgnoreCase("97")){
							//Do nothing
						}else{
							System.out.println(" bfr if claimToBeSent.setHC1_COB_835_PRV_NC_AMT"+claimToBeSent.getHC1_COB_835_PRV_NC_AMT());
							claimToBeSent.setHC1_COB_835_PRV_NC_AMT(claimToBeSent.getHC1_COB_835_PRV_NC_AMT().add(ret835ClmRedTbl.get(clm_sub).getCLM_RD_PD_AMT()));
							System.out.println(" aftr if claimToBeSent.setHC1_COB_835_PRV_NC_AMT"+claimToBeSent.getHC1_COB_835_PRV_NC_AMT());

						}
					}

				}

			}
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		}
		return claimToBeSent;
	}

}
