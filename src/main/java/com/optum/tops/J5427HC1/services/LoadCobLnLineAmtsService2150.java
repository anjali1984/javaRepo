package com.optum.tops.J5427HC1.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.models.ClaimIndicatorValues;
import com.optum.tops.J5427HC1.models.HC1_COB_LINE_ENTRY;
import com.optum.tops.J5427HC1.models.LineReductionHold;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

@Service
public class LoadCobLnLineAmtsService2150 {
	Logger logger=Logger.getLogger("genLogger");


	public V5427HC1 do2150Section(V5427HC1 claimToBeSent, ReqClaimEntryVO individual_claim2){
		String location="J5427HC1.services.LoadCobLnLineAmtsService2150.do2150Section(V5427HC1, String)";
		
		try {
			List<LineReductionHold> lines = claimToBeSent.getMy_indicator().getWS_LINE_REDUCTION_TABLE(); 
			List<HC1_COB_LINE_ENTRY> lines_in_return = claimToBeSent.getHC1_COB_LNE_DATA_AREA(); 
			
			logger.info(location.concat(" Size of the Reduction table is:").concat(Integer.toString(lines.size())).concat("]").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

			for (LineReductionHold each_line : lines){

				if(each_line.getLN_ID() <= 0){
					continue; //Move onto the next line, this is a dummy one added to avoid the indexOutofBound Exception for adding to the ArrayList
				}
				
				HC1_COB_LINE_ENTRY line_to_be_added = new HC1_COB_LINE_ENTRY(); 
				line_to_be_added.setHC1_COB_LN_ALLOW_AMT_IND(each_line.getLN_ALLW_AMT_IND());
				line_to_be_added.setHC1_COB_LN_835_RPT_ALLOW_AMT(each_line.getLN_RPT_ALLOW_AMT());
				
				//Claim level amount
				claimToBeSent.setHC1_COB_835_RPT_ALLOW_AMT(claimToBeSent.getHC1_COB_835_RPT_ALLOW_AMT().add(each_line.getLN_RPT_ALLOW_AMT()));
				
				line_to_be_added.setHC1_COB_LN_EOB_OI_PAID_AMT(each_line.getLN_OI_PAID_AMT());
				if(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().trim().equals("69")){
					if(each_line.getLN_OI_PAID_AMT().compareTo(BigDecimal.ZERO) > 0 ){
						each_line.setLN_OI_PAID_AMT(each_line.getLN_OI_PAID_AMT().multiply(new BigDecimal(-1)));
						claimToBeSent.setHC1_COB_OI_PAID_AMT(claimToBeSent.getHC1_COB_OI_PAID_AMT().add(each_line.getLN_OI_PAID_AMT()));
					}
				}else{
					claimToBeSent.setHC1_COB_OI_PAID_AMT(claimToBeSent.getHC1_COB_OI_PAID_AMT().add(each_line.getLN_OI_PAID_AMT()));
				}
				
				line_to_be_added.setHC1_COB_LN_EOB_MEDC_PAID_AMT(each_line.getLN_MEDC_PAID_AMT());
				if(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().trim().equals("69")){
					if(each_line.getLN_MEDC_PAID_AMT().compareTo(BigDecimal.ZERO) > 0 ){
						each_line.setLN_MEDC_PAID_AMT(each_line.getLN_MEDC_PAID_AMT().multiply(new BigDecimal(-1)));
						claimToBeSent.setHC1_COB_MEDC_PAID_AMT(claimToBeSent.getHC1_COB_MEDC_PAID_AMT().add(each_line.getLN_MEDC_PAID_AMT()));
					}
				}else{
					claimToBeSent.setHC1_COB_MEDC_PAID_AMT(claimToBeSent.getHC1_COB_MEDC_PAID_AMT().add(each_line.getLN_MEDC_PAID_AMT()));
				}
				
				line_to_be_added.setHC1_COB_LN_COB_PRV_WRT_OFF(each_line.getLN_PRV_WRT_OFF());
				if(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().trim().equals("00")){
					if(claimToBeSent.getMy_indicator().getCXINT_CLAIM_INDICATOR().equals("Y")
						|| (claimToBeSent.getMy_indicator().getNYSTATE_COB_CLAIM_PAIDTO().equals("S") && claimToBeSent.getMy_indicator().isNYS_SERV_LINE_SW() )){
						//Dont do anything
					}else{
						ClaimIndicatorValues ind = claimToBeSent.getMy_indicator(); 
						claimToBeSent.setHC1_COB_PRV_WRT_OFF(ind.getDBKE2_SUFX_TOT_CHRG_AMT().subtract(ind.getLN_TOT_RPT_ALL_AMT()));
					}
				}else{
					claimToBeSent.setHC1_COB_PRV_WRT_OFF(claimToBeSent.getHC1_COB_PRV_WRT_OFF().add(each_line.getLN_PRV_WRT_OFF()));
				}
				
				line_to_be_added.setHC1_COB_LINE_LN_ID(each_line.getLN_ID());
				line_to_be_added.setHC1_COB_LINE_SRVC_CD(each_line.getLINE_SRVC_CD());
				line_to_be_added.setHC1_COB_LINE_PMT_SVC_CD(each_line.getLINE_PMT_SVC_CD());
				line_to_be_added.setHC1_COB_LINE_AUTH_PROC_CD(each_line.getLINE_AUTH_PROC_CD());
				line_to_be_added.setHC1_COB_LINE_FST_DT(each_line.getLINE_FST_DT());
				line_to_be_added.setHC1_COB_LINE_LST_SRVC_DT(each_line.getLINE_LST_SRVC_DT());
				line_to_be_added.setHC1_COB_LINE_CHRG_AMT(each_line.getLINE_CHRG_AMT());
				
				lines_in_return.add(line_to_be_added); //This line will be sent along in the response.
			}
					
			claimToBeSent.setHC1_COB_NBR_LINES(lines_in_return.size());
			logger.info(location.concat(" No of Reduction lines to be returned:").concat("[").concat(Integer.toString(claimToBeSent.getHC1_COB_NBR_LINES())).concat("]").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"),e);
			e.printStackTrace();
		}

		return claimToBeSent;
		
	}
}
