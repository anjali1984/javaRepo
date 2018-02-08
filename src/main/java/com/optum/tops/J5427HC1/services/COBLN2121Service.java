package com.optum.tops.J5427HC1.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.dao.D54DAO.FetchCoblnAmtsDao;
import com.optum.tops.J5427HC1.models.ADJD_CLMSF_ORIGHDR_LINE;
import com.optum.tops.J5427HC1.models.COBLN_LINE_FLDS;
import com.optum.tops.J5427HC1.models.ClaimIndicatorValues;
import com.optum.tops.J5427HC1.models.LineHold;
import com.optum.tops.J5427HC1.models.LineReductionHold;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.VYCKSERV.enums.ACN_NO_SURCHRG_SERV;
import com.optum.tops.VYCKSERV.enums.COB_835_INFO;
import com.optum.tops.VYCKSERV.enums.INFO_SERV;
import com.optum.tops.VYCKSERV.enums.NYTAX_SERV;
import com.optum.tops.VYCKSERV.enums.PROMPT_PAY_SERV;

@Service
public class COBLN2121Service {
	@Autowired 
	FetchCoblnAmtsDao CoblnAmtsDao ;
	Logger logger=Logger.getLogger("genLogger");
	
	public V5427HC1 getResultsCobln_Line_Flds(ReqClaimEntryVO individual_claim2, V5427HC1 outboundClaim){
		String location="J5427HC1.services.COBLN2121Service.getResultsCobln_Line_Flds(ReqClaimEntryVO, V5427HC1)";
		
		List<COBLN_LINE_FLDS> results = CoblnAmtsDao.getCoblnFlds(individual_claim2, outboundClaim.getMy_indicator().getDBKE2_ICN_SUFX_CD()) ; // result of query in COBLN-LINE-FLDS cursor
		
		//All business logic in 2121-FETCH_COBLN-LINE-AMTS 
		
		ClaimIndicatorValues indicatorObject = outboundClaim.getMy_indicator();
		BigDecimal WS_TEMP_RPT_ALLOW_AMT = new BigDecimal(0); // Corresponds to  WS-TEMP-RPT-ALLOW-AMT working Storage variable
		
		for(COBLN_LINE_FLDS line: results){
			indicatorObject.setLast_line_id(line.getLN_ID());// Used in 2141 logic to skip last line 
			
			String line_pmt_svc_cd = line.getLN_PMT_SVC_CD(); // MOVE DCLNE-PMT-SVC-CD to DCLNE-PMT-SVC-CD TO CK-SERV-CLS, WRK-NY-SERV-CD
			
			
			// Move DCLNE-LN-ID to WS-SUB index into the 2 Line tables/ArrayLists in the ClaimIndicatorValues 
			int index = line.getLN_ID() - 1; // 1 to 7  

			LineHold line_data = new LineHold(); //One of the lines that goes into Arraylist representing WS-LINE-HOLD-TBL.
			LineReductionHold line_reduction_data = new LineReductionHold() ; //One of the lines that goes into ArrayList representing WS-LINE-REDUCTION-HOLD-TBL
			
			//If Penny Process perform 2122-PENNY-PROCESS-ADJUST-SECT
			if(indicatorObject.getPENNY_PROC_INDICATOR().equals("Y")){
				logger.info(location.concat(" Start Penny Process as PENNY_PROC_INDICATOR is  ").concat("[").concat(indicatorObject.getPENNY_PROC_INDICATOR()).concat("]").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));
				outboundClaim = penny_process_adjustment (line, outboundClaim); 
				logger.info(location.concat(" Penny Process Performed  ").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

			}
			
			if( (line_pmt_svc_cd.contains("OI") || line_pmt_svc_cd.contains("OIM") || line_pmt_svc_cd.contains("OIMEDI")) 
					|| (individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().contains("69") && !line.getLN_RMRK_CD().contains("69"))){
				//System.out.println("Line " + line.getLN_ID() + " will ONLY be added for avoiding exception of adding to Arraylist, redcution table. OI,OM, OIMEDI COBLN2121Service ");
				logger.info(location.concat(" Line at index:").concat("[").concat(Integer.toString(line.getLN_ID())).concat("]").concat("is not a cob line and will not be added to Response object").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

				indicatorObject.getWS_LINE_DATA_AREA_TABLE().add(index, line_data);
				indicatorObject.getWS_LINE_REDUCTION_TABLE().add(index, line_reduction_data);
				continue; 
			}
			
																			
			List<COB_835_INFO> enumValues = Arrays.asList(COB_835_INFO.values()); // Gets all 
			if (enumValues.contains("COB_835_INFO." + line_pmt_svc_cd)) {
				//System.out.println("Line " + line.getLN_ID() + " will ONLY be added for avoiding exception of adding to Arraylist, redcution table. COB_835_INFO COBLN2121Service ");
				indicatorObject.getWS_LINE_DATA_AREA_TABLE().add(index, line_data);
				indicatorObject.getWS_LINE_REDUCTION_TABLE().add(index, line_reduction_data);
				continue;
			} else {
				if (line_pmt_svc_cd.trim().equals("CXINT")) {
					indicatorObject.setCXINT_CLAIM_INDICATOR("Y");
				}

				// NYSTATE COB CLAIM
				if (indicatorObject.getNYSTATE_COB_CLAIM().equals("Y")) {
					if (line_pmt_svc_cd.charAt(0) == 'C' && (line_pmt_svc_cd.substring(3).equalsIgnoreCase("NYS")
							|| line_pmt_svc_cd.substring(3).equalsIgnoreCase("NYA"))) {
						line.setNYS_SERV_LINE(true);
						indicatorObject.setNYS_SERV_LINE_SW(true);
					} else {
						line.setNYS_SERV_LINE(false);
						indicatorObject.setNYS_SERV_LINE_SW(false);
					}
					if (indicatorObject.getCXINT_CLAIM_INDICATOR().equals("Y")) {
						//System.out.println("Line " + line.getLN_ID() + " will ONLY be added for avoiding exception of adding to Arraylist, redcution table. CXINT_CLAIM_INDICATOR Y COBLN2121Service ");
						indicatorObject.getWS_LINE_DATA_AREA_TABLE().add(index, line_data);
						indicatorObject.getWS_LINE_REDUCTION_TABLE().add(index, line_reduction_data);
						continue;
					} else {
						if (indicatorObject.getNYSTATE_COB_CLAIM_PAIDTO().equals("S") && (line.isNYS_SERV_LINE())) {
							// MOVE 'N' TO WS-NY-COB-PARENT-CLM-SW, set in the
							// claim indicator
							indicatorObject.setNY_COB_PARENT_CLM(false);
						} else if (indicatorObject.getNYSTATE_COB_CLAIM_PAIDTO().equals("S")
								&& !(line.isNYS_SERV_LINE())) {
							indicatorObject.setNY_COB_PARENT_CLM(true);
						}
					}
				}

				 
				
				if(indicatorObject.getNYSTATE_COB_CLAIM_PAIDTO().equals("S")){
					if(indicatorObject.isNY_COB_PARENT_CLM()){ 
						line_data.setLN_NY_DED_MM_AMT(line.getLN_NYSCHG_DED_MM_AMT());
						indicatorObject.getWS_LINE_DATA_AREA_TABLE().add(index, line_data);
					}else{
						line_data.setLN_MM_DED_AMT(line.getLN_MM_DED_AMT());
						indicatorObject.getWS_LINE_DATA_AREA_TABLE().add(index, line_data);
					}
				}
				
				//Checks if the RPTG_LN_ALLW_AMT == -1 
				if(line.getRPTG_LN_ALLW_AMT().compareTo( new BigDecimal(-1)) == 0 ){
					line.setRPTG_LN_ALLW_AMT(BigDecimal.ZERO);
				}
				line_reduction_data.setLN_RPT_ALLOW_AMT(line.getRPTG_LN_ALLW_AMT());
				indicatorObject.getWS_LINE_REDUCTION_TABLE().add(index, line_reduction_data);
				
				if(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().equals("00")){
					indicatorObject.setLN_TOT_RPT_ALL_AMT( indicatorObject.getLN_TOT_RPT_ALL_AMT().add(line_reduction_data.getLN_RPT_ALLOW_AMT()));
				}
				
				List<NYTAX_SERV> nyTax_serve_values = Arrays.asList(NYTAX_SERV.values());
				if(indicatorObject.getNYSTATE_COB_CLAIM().equals("Y") &&
						indicatorObject.getNYSTATE_COB_CLAIM_PAIDTO().equals("P") && 
						indicatorObject.getDBKE2_FACL_OR_PROF_CD().equals("P") && 
						nyTax_serve_values.contains("NYTAX_SERV."+line_pmt_svc_cd)
						){
					//POSSIBLE INDEX OUT OF BOUNDS FOR 1st LINE ID **************** 
					BigDecimal temp = indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index - 1).getLN_RPT_ALLOW_AMT().subtract(indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).getLN_RPT_ALLOW_AMT()); 
					indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index - 1).setLN_RPT_ALLOW_AMT(temp);
				}
				
				indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_ALLW_AMT_IND(line.getLN_ALLW_AMT_DTRM_CD());
				
				if(line.getLN_OI_PD_LN_AMT().compareTo(new BigDecimal(-1)) == 0 ){
					line.setLN_OI_PD_LN_AMT(BigDecimal.ZERO);
				}
				indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_OI_PAID_AMT(line.getLN_OI_PD_LN_AMT());
				
				if(line.getLN_MEDC_L04_AMT().compareTo(new BigDecimal(-1)) < 0){
					line.setLN_MEDC_L04_AMT(BigDecimal.ZERO);
				}
				indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_MEDC_PAID_AMT(line.getLN_MEDC_L04_AMT());
				
				if(line.getLN_PMT_SVC_CD().trim().equals("CXINT")){
					indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_PRV_WRT_OFF(BigDecimal.ZERO);
				}else{
					if(indicatorObject.getNYSTATE_COB_CLAIM().equals("Y")){
						if(nyTax_serve_values.contains("NYTAX_SERV."+line_pmt_svc_cd)){
							indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_PRV_WRT_OFF(BigDecimal.ZERO);
						}else{
							BigDecimal temp = line.getLN_CHRG_AMT().subtract(indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).getLN_RPT_ALLOW_AMT()); 
							indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_PRV_WRT_OFF(temp);
						}
					}else{
						BigDecimal temp ; 
						BigDecimal temp2 ;
						if(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().trim().equals("69")){
							temp = line.getLN_CHRG_AMT().subtract(WS_TEMP_RPT_ALLOW_AMT);
							indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_PRV_WRT_OFF(temp);
							WS_TEMP_RPT_ALLOW_AMT = indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).getLN_RPT_ALLOW_AMT().multiply
													(temp); 
						}else{
							temp2 = indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).getLN_RPT_ALLOW_AMT();
							indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_PRV_WRT_OFF(line.getLN_CHRG_AMT().subtract(temp2));
						}
					}
					
					if(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().trim().equals("00") && 
						(line.getLN_OVR_CD().trim().equals("20") || line.getLN_OVR_CD().trim().equals("30") || line.getLN_OVR_CD().trim().equals("X0") || line.getLN_OVR_CD().trim().equals("Y0") )	){
						indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index).setLN_PRV_WRT_OFF(BigDecimal.ZERO);
					}
				}
				
				
				line_reduction_data = indicatorObject.getWS_LINE_REDUCTION_TABLE().get(index) ; 
				line_reduction_data.setLN_ID(line.getLN_ID());
				line_reduction_data.setLINE_SRVC_CD(line.getLN_SRVC_CD());
				line_reduction_data.setLINE_PMT_SVC_CD(line.getLN_PMT_SVC_CD());
				line_reduction_data.setLINE_AUTH_PROC_CD(line.getLN_AUTH_PROC_CD());
				line_reduction_data.setLINE_FST_DT(line.getLN_FST_DT());
				line_reduction_data.setLINE_LST_SRVC_DT(line.getLN_LST_SRVC_DT());
				line_reduction_data.setLINE_CHRG_AMT(line.getLN_CHRG_AMT());
				line_reduction_data.setLINE_NC_AMT(line.getLN_NC_AMT());
				if(line.getLN_CHRG_AMT().compareTo(line.getLN_NC_AMT()) == 0 && line.getLN_CHRG_AMT().compareTo(BigDecimal.ZERO) > 0 ){
					indicatorObject.setCALL_OIMC_TBL_INDICATOR("Y"); // This will determine that the claim should go through 2130-GET-COB-SERV-CALC-DATA
				}
			}

		}
		
		return outboundClaim;
		
	}
	
	//2122-PENNY-PROCESS-ADJUST-SECT
	private V5427HC1 penny_process_adjustment(COBLN_LINE_FLDS line , V5427HC1 outboundClaim){
		ClaimIndicatorValues my_indicator = outboundClaim.getMy_indicator(); 
		      
		List<ADJD_CLMSF_ORIGHDR_LINE> orig_HDR_DATA = my_indicator.getHC1_ADJD_CLMSF_ORIGHDR_DATAAREA(); 
		String line_pmt_svc_cd = line.getLN_PMT_SVC_CD() ;
		
		
		List<INFO_SERV> info_serve_values = Arrays.asList(INFO_SERV.values());
		List<ACN_NO_SURCHRG_SERV> acnNoSurchrg_serve_values = Arrays.asList(ACN_NO_SURCHRG_SERV.values());
		List<NYTAX_SERV> nyTax_serve_values = Arrays.asList(NYTAX_SERV.values());
		List<PROMPT_PAY_SERV> promptPay_serve_values = Arrays.asList(PROMPT_PAY_SERV.values());
		
		if(info_serve_values.contains("INFO_SERV."+line_pmt_svc_cd) 
				|| acnNoSurchrg_serve_values.contains("ACN_NO_SURCHRG_SERV."+line_pmt_svc_cd)
				|| nyTax_serve_values.contains("NYTAX_SERV."+line_pmt_svc_cd)
				|| promptPay_serve_values.contains("PROMPT_PAY_SERV."+line_pmt_svc_cd)
				|| line.getLN_SRVC_CD().trim().length() == 0 
				|| line.getLN_SRVC_CD().equals("--")
				|| line.getLN_RMRK_CD().equals("70")
				|| line.getLN_OVR_CD().equals("13")
				|| line_pmt_svc_cd.trim().length() == 0 
				|| line_pmt_svc_cd.equals("------")
			) {
			return outboundClaim; //Dont have to do this section. 
			
		}
		
		//FOR INSTITUTIONAL AND OPS-HCFA CLAIMS
		if(my_indicator.getOPS_HCFA_INDICATOR().equals("Y") || outboundClaim.getHC1_COB_INST_OR_PROF().equals("I")){
			int svc_sub = line.getLN_ID() - 1; //Index into the array for marking it as SVC-LINE-PENNY-YES, its an array of size 7 so indexes are from 0 to 6.
			
			//SCAN ORIGHDR DATA TABLES TO FIND ENTRIES FOR THIS SVC LINE
			for(ADJD_CLMSF_ORIGHDR_LINE a_hdr_line : orig_HDR_DATA ){
				//MATCH ON CORR-ID'S  -------* 
				if(a_hdr_line.getLN_CORR_ID() == line.getLN_ORIG_LN_CORR_ID()){
					 //FOR MATCHED ENTRIES -------*            
					//-- CHECK FOR $0 ISSUE  ----*   
					if(a_hdr_line.getCHRG_AMT() == BigDecimal.ZERO  && a_hdr_line.getUB92_CHRG_AMT() != BigDecimal.ZERO){
						a_hdr_line.setUB92_CHRG_AMT(BigDecimal.ZERO);
						a_hdr_line.setUB92_NOT_COV_AMT(BigDecimal.ZERO);
						a_hdr_line.setREV_LINE_PENNY_INDICATOR("Y");
						my_indicator.getSVC_LINE_PENNY_IND_ENTRY()[svc_sub] = true;
					}
				}
			}
			
			if (my_indicator.getSVC_LINE_PENNY_IND_ENTRY()[svc_sub] == true) {
				// IF THERE WAS ANY PENNY ISSUE FOR THE SVE LINE RE-CALCULATE
				// THE
				// * SERVICE LINE CHARGE AND NCOV AMOUNTS AMTS AS SUM OF
				// REV-LEVEL AMOUNTS
				line.setLN_CHRG_AMT(BigDecimal.ZERO);
				line.setLN_NC_AMT(BigDecimal.ZERO);

				// RE-CALCULATE SVC-LEVEL AMTS AS SUM OF REV-LEVEL AMOUNTS
				for (ADJD_CLMSF_ORIGHDR_LINE hrd_line_iterator : orig_HDR_DATA) {
					if (hrd_line_iterator.getLN_CORR_ID() == line.getLN_ORIG_LN_CORR_ID()) {
						line.setLN_CHRG_AMT(line.getLN_CHRG_AMT().add(hrd_line_iterator.getUB92_CHRG_AMT()));
						line.setLN_NC_AMT(line.getLN_NC_AMT().add(hrd_line_iterator.getUB92_NOT_COV_AMT()));
					}
				}
			}
		}
		
		//For Professional Claims check the $0 issue
		if(line.getLN_ORIG_LN_CHRG_AMT() == BigDecimal.ZERO && line.getLN_CHRG_AMT() != BigDecimal.ZERO){
			line.setLN_CHRG_AMT(BigDecimal.ZERO);
			line.setLN_NC_AMT(BigDecimal.ZERO);
			
			/*-	SET SVC LINE LVL FLAG --* 
         	 	MOVE DCLNE-LN-ID TO SVC-SUB  
         		SET  SVC-LINE-PENNY-YES (SVC-SUB) TO TRUE
         	 */ 
			int svc_sub = line.getLN_ID() - 1; //Index into the array for marking it as SVC-LINE-PENNY-YES, its an array of size 7 so indexes are from 0 to 6.
			my_indicator.getSVC_LINE_PENNY_IND_ENTRY()[svc_sub] = true;
		}
		
		return outboundClaim;
		
	}
}
