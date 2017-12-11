package com.optum.tops.J5427HC1.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.dao.D54DAO.FetchCoblnAmtsDao;
import com.optum.tops.J5427HC1.models.ADJD_CLMSF_ORIGHDR_LINE;
import com.optum.tops.J5427HC1.models.COBLN_LINE_FLDS;
import com.optum.tops.J5427HC1.models.ClaimIndicatorValues;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
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
	
	public V5427HC1 getResultsCobln_Line_Flds(ReqClaimEntry requestedClaim, V5427HC1 outboundClaim){
		
		List<COBLN_LINE_FLDS> results = CoblnAmtsDao.getCoblnFlds(requestedClaim, outboundClaim.getMy_indicator().getDBKE2_ICN_SUFX_CD()) ; // result of query in COBLN-LINE-FLDS cursor
		//All business logic in 2121-FETCH_COBLN-LINE-AMTS 
		
		for(COBLN_LINE_FLDS line: results){
			
			ClaimIndicatorValues indicatorObject = outboundClaim.getMy_indicator();
			
			//If Penny Process perform 2122-PENNY-PROCESS-ADJUST-SECT
			if(outboundClaim.getMy_indicator().getPENNY_PROC_INDICATOR().equals("Y")){
				//TODO : Complete the implementation 
				penny_process_adjustment (line, outboundClaim); 
				
			}
			
			if( (line.getLN_PMT_SVC_CD().contains("OI") || line.getLN_PMT_SVC_CD().contains("OIM") || line.getLN_PMT_SVC_CD().contains("OIMEDI")) 
					|| (requestedClaim.getHC1_REQ_CLM_TRANS_CD().contains("69") && !line.getLN_RMRK_CD().contains("69"))){
				continue; 
			}else{
				String line_pmt_svc_cd = line.getLN_PMT_SVC_CD() ; //MOVE DCLNE-PMT-SVC-CD TO CK-SERV-CLS //WRK-NY-SERV-CD 
																							
				 
				
				List<COB_835_INFO> enumValues = Arrays.asList(COB_835_INFO.values()); //Gets all possible values of COB_835_INFO as a list of ENUMS 
				
				if(enumValues.contains("COB_835_INFO."+line_pmt_svc_cd)){
					continue ;
				}else{
					if(line_pmt_svc_cd.trim().equals("CXINT")){
						indicatorObject.setCXINT_CLAIM_INDICATOR("Y"); 
					}
					//NYSTATE COB CLAIM 
					if(indicatorObject.getNYSTATE_COB_CLAIM().equals("Y")){
						if( line_pmt_svc_cd.charAt(0) == 'C' && (line_pmt_svc_cd.substring(3).equalsIgnoreCase("NYS")  || line_pmt_svc_cd.substring(3).equalsIgnoreCase("NYA")) ) {
							line.setNYS_SERV_LINE(true);
						}else{
							line.setNYS_SERV_LINE(false);
						}
						if (indicatorObject.getCXINT_CLAIM_INDICATOR().equals("Y")){
							//Nothing 
							
						}else{
							if(indicatorObject.getNYSTATE_COB_CLAIM_PAIDTO().equals("S") && (line.isNYS_SERV_LINE())){
								//MOVE 'N' TO WS-NY-COB-PARENT-CLM-SW, set in the claim indicator 
								indicatorObject.setNY_COB_PARENT_CLM(false);
							}else if(indicatorObject.getNYSTATE_COB_CLAIM_PAIDTO().equals("S") && !(line.isNYS_SERV_LINE()) ){
								indicatorObject.setNY_COB_PARENT_CLM(true);
							}	
						}
					}
					
					//Move DCLNE-LN-ID to WS-SUB index into the 2 Line tables 
					
					
				}
				
			}
			
		}
		return outboundClaim;
		
	}
	
	//2122-PENNY-PROCESS-ADJUST-SECT
	public void penny_process_adjustment(COBLN_LINE_FLDS line , V5427HC1 outboundClaim){
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
			return; //Dont have to do this section. 
			
		}
		
		if(my_indicator.getOPS_HCFA_INDICATOR().equals("Y") || outboundClaim.getHC1_COB_INST_OR_PROF().equals("Y")){
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
						//IF THERE WAS ANY PENNY ISSUE FOR THE SVE LINE RE-CALCULATE THE  
						//* SERVICE LINE CHARGE AND NCOV AMOUNTS AMTS AS SUM OF REV-LEVEL AMOUNTS 
						line.setLN_CHRG_AMT(BigDecimal.ZERO);
						line.setLN_NC_AMT(BigDecimal.ZERO);
						//RE-CALCULATE SVC-LEVEL AMTS AS SUM OF REV-LEVEL AMOUNTS
						for(ADJD_CLMSF_ORIGHDR_LINE hrd_line_iterator : orig_HDR_DATA){
							if(hrd_line_iterator.getLN_CORR_ID() == line.getLN_ORIG_LN_CORR_ID()){
								line.setLN_CHRG_AMT(line.getLN_CHRG_AMT().add(hrd_line_iterator.getUB92_CHRG_AMT()));
								line.setLN_NC_AMT(line.getLN_NC_AMT().add(hrd_line_iterator.getUB92_NOT_COV_AMT()));
							}
						}
						
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
		}
		
		
	}
}
