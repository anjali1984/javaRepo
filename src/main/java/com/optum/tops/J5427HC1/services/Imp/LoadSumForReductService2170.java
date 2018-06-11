package com.optum.tops.J5427HC1.services.Imp;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.optum.tops.J5427HC1.models.HC1_COB_LINE_ENTRY;
import com.optum.tops.J5427HC1.models.LineReductionHold;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.ILoadSumForReductService2170;

@Service
public class LoadSumForReductService2170 implements ILoadSumForReductService2170 {


	Logger logger=Logger.getLogger("genLogger");

	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.services.ILoadSumForReductService2170#do2170(com.optum.tops.J5427HC1.models.V5427HC1, com.optum.tops.J5427HC1.models.ReqClaimEntryVO)
	 */
	@Override
	public V5427HC1 do2170(V5427HC1 claimToBeSent,ReqClaimEntryVO  individual_claim)
	{
		String location="J5427HC1.services.LoadSumForReductService2170.do2170(V5427HC1, String, String)";
		try {
			List<LineReductionHold> reductLines = claimToBeSent.getMy_indicator().getWS_LINE_REDUCTION_TABLE(); //WS table for all line amounts 0 to 6 index
			List<HC1_COB_LINE_ENTRY> lines_in_return = claimToBeSent.getHC1_COB_LNE_DATA_AREA(); 

			logger.info(location.concat(" Size of the Reduction table is:").concat("[").
					concat(Integer.toString(reductLines.size())).concat("]").concat(" LOGID:").concat("[")
					.concat(individual_claim.getLogId()).concat("]"));

			//System.out.println("Size of the Reduction table is" + reductLines.size() ) ;
			//for (int ln_sub=0;ln_sub<7;ln_sub++)
			for(LineReductionHold each_line : reductLines)
			{
				if(each_line.getLN_ID() == 0 ){
					continue ; 
				}

				HC1_COB_LINE_ENTRY line_to_be_added = new HC1_COB_LINE_ENTRY(); 

				if((each_line.getPR_OVERC().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPR_NTCOV().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPR_DEDUC().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPR_COPAY().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPR_COINS().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPR_DENY_AMT().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPR_MEDC_EST_AMT().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getCOB_PRIM_IMPAC().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPRV_NC_AMT().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getPR_TOTAL().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getCAT_ID().compareTo(BigDecimal.ZERO)>0)
						||(each_line.getLN_ID()>0))
				{
					line_to_be_added.setHC1_COB_LN_SRV_IND(each_line.getSVC_LN_ID().toString());

					line_to_be_added.setHC1_COB_LN_835_PAT_RESP_OVERC(each_line.getPR_OVERC());//sets the line level
					claimToBeSent.setHC1_COB_835_PAT_RESP_OVERC(claimToBeSent.getHC1_COB_835_PAT_RESP_OVERC().add(line_to_be_added.getHC1_COB_LN_835_PAT_RESP_OVERC()));//adds line level to the claim level

					line_to_be_added.setHC1_COB_LN_835_PAT_RESP_DEDUC(each_line.getPR_DEDUC());
					claimToBeSent.setHC1_COB_835_PAT_RESP_DEDUC(claimToBeSent.getHC1_COB_835_PAT_RESP_DEDUC().add(line_to_be_added.getHC1_COB_LN_835_PAT_RESP_DEDUC()));//adds line level to the claim level

					line_to_be_added.setHC1_COB_LN_835_PAT_RESP_COPAY(each_line.getPR_COPAY());
					claimToBeSent.setHC1_COB_835_PAT_RESP_COPAY(claimToBeSent.getHC1_COB_835_PAT_RESP_COPAY().add(line_to_be_added.getHC1_COB_LN_835_PAT_RESP_COPAY()));

					line_to_be_added.setHC1_COB_LN_835_PAT_RESP_COINS(each_line.getPR_COINS());
					claimToBeSent.setHC1_COB_835_PAT_RESP_COINS(claimToBeSent.getHC1_COB_835_PAT_RESP_COINS().add(line_to_be_added.getHC1_COB_LN_835_PAT_RESP_COINS()));
					
					System.out.println("line HC1_COB_835_DENY_NC_AMT:"+line_to_be_added.getHC1_COB_LN_835_DENY_NC_AMT());

					line_to_be_added.setHC1_COB_LN_835_DENY_NC_AMT(each_line.getPR_DENY_AMT());
					System.out.println("HC1_COB_835_DENY_NC_AMT:"+claimToBeSent.getHC1_COB_835_DENY_NC_AMT());

					claimToBeSent.setHC1_COB_835_DENY_NC_AMT(claimToBeSent.getHC1_COB_835_DENY_NC_AMT().add(line_to_be_added.getHC1_COB_LN_835_DENY_NC_AMT()));

					line_to_be_added.setHC1_COB_LN_835_MEDC_EST_AMT(each_line.getPR_MEDC_EST_AMT());
					claimToBeSent.setHC1_COB_835_PAT_RESP_MCEST(claimToBeSent.getHC1_COB_835_PAT_RESP_MCEST().add(line_to_be_added.getHC1_COB_LN_835_MEDC_EST_AMT()));

					line_to_be_added.setHC1_COB_LN_835_COB_PRIM_IMPAC(each_line.getCOB_PRIM_IMPAC());
					claimToBeSent.setHC1_COB_835_COB_PRIM_IMPAC(claimToBeSent.getHC1_COB_835_COB_PRIM_IMPAC().add(line_to_be_added.getHC1_COB_LN_835_COB_PRIM_IMPAC()));

					line_to_be_added.setHC1_COB_LN_835_PAT_RESP_NTCOV(each_line.getPR_NTCOV());
					claimToBeSent.setHC1_COB_835_PAT_RESP_NTCOV(claimToBeSent.getHC1_COB_835_PAT_RESP_NTCOV().add(line_to_be_added.getHC1_COB_LN_835_PAT_RESP_NTCOV()));

					line_to_be_added.setHC1_COB_LN_835_PAT_RESP_TOTAL(each_line.getPR_TOTAL());
					claimToBeSent.setHC1_COB_835_PAT_RESP_TOTAL(claimToBeSent.getHC1_COB_835_PAT_RESP_TOTAL().add(line_to_be_added.getHC1_COB_LN_835_PAT_RESP_TOTAL())); 

					line_to_be_added.setHC1_COB_LN_835_PRV_NC_AMT(each_line.getPRV_NC_AMT());
					claimToBeSent.setHC1_COB_835_PRV_NC_AMT(claimToBeSent.getHC1_COB_835_PRV_NC_AMT().add(line_to_be_added.getHC1_COB_LN_835_PRV_NC_AMT()));

					line_to_be_added.setHC1_COB_LN_ALLOW_AMT_IND(each_line.getLN_ALLW_AMT_IND());

					line_to_be_added.setHC1_COB_LN_835_RPT_ALLOW_AMT(each_line.getLN_RPT_ALLOW_AMT());
					claimToBeSent.setHC1_COB_835_RPT_ALLOW_AMT(claimToBeSent.getHC1_COB_835_RPT_ALLOW_AMT().add(line_to_be_added.getHC1_COB_LN_835_RPT_ALLOW_AMT()));

					line_to_be_added.setHC1_COB_LN_EOB_OI_PAID_AMT(each_line.getLN_OI_PAID_AMT());
					if(individual_claim.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().trim().equals("69")){
						if(each_line.getLN_OI_PAID_AMT().compareTo(BigDecimal.ZERO)>0){
							claimToBeSent.setHC1_COB_OI_PAID_AMT(claimToBeSent.getHC1_COB_OI_PAID_AMT().add(each_line.getLN_OI_PAID_AMT().negate()));
						}
					}else
					{
						claimToBeSent.setHC1_COB_OI_PAID_AMT(claimToBeSent.getHC1_COB_OI_PAID_AMT().add(line_to_be_added.getHC1_COB_LN_EOB_OI_PAID_AMT()));
					}

					line_to_be_added.setHC1_COB_LN_EOB_MEDC_PAID_AMT(each_line.getLN_MEDC_PAID_AMT());
					if(individual_claim.getReqClaimEntry().getHc1_REQ_CLM_TRANS_CD().trim().equals("69")){
						if(each_line.getLN_MEDC_PAID_AMT().compareTo(BigDecimal.ZERO)>0)
						{
							claimToBeSent.setHC1_COB_MEDC_PAID_AMT(claimToBeSent.getHC1_COB_MEDC_PAID_AMT().add(each_line.getLN_MEDC_PAID_AMT().negate()));
							//System.out.println("69 transcd");
						}
					}else
					{
						claimToBeSent.setHC1_COB_MEDC_PAID_AMT(claimToBeSent.getHC1_COB_MEDC_PAID_AMT().add(line_to_be_added.getHC1_COB_LN_EOB_MEDC_PAID_AMT()));
						//System.out.println("not 69 transcd"+claimToBeSent.getHC1_COB_MEDC_PAID_AMT());

					}

					line_to_be_added.setHC1_COB_LN_COB_PRV_WRT_OFF(each_line.getLN_PRV_WRT_OFF());
					System.out.println("each_line.getLN_PRV_WRT_OFF() "+each_line.getLN_PRV_WRT_OFF());
					claimToBeSent.setHC1_COB_PRV_WRT_OFF(claimToBeSent.getHC1_COB_PRV_WRT_OFF().add(line_to_be_added.getHC1_COB_LN_COB_PRV_WRT_OFF()));
					System.out.println("claimToBeSent.getHC1_COB_PRV_WRT_OFF "+claimToBeSent.getHC1_COB_PRV_WRT_OFF());
					//All line fields that are not used to set the Claim level stuff
					line_to_be_added.setHC1_COB_LINE_LN_ID(each_line.getLN_ID());
					line_to_be_added.setHC1_COB_LINE_SRVC_CD(each_line.getLINE_SRVC_CD());
					line_to_be_added.setHC1_COB_LINE_PMT_SVC_CD(each_line.getLINE_PMT_SVC_CD());
					line_to_be_added.setHC1_COB_LINE_AUTH_PROC_CD(each_line.getLINE_AUTH_PROC_CD());
					line_to_be_added.setHC1_COB_LINE_FST_DT(each_line.getLINE_FST_DT());			
					line_to_be_added.setHC1_COB_LINE_LST_SRVC_DT(each_line.getLINE_LST_SRVC_DT());
					line_to_be_added.setHC1_COB_LINE_CHRG_AMT(each_line.getLINE_CHRG_AMT());

				}
				lines_in_return.add(line_to_be_added);//will setup the HC1_COB_LNE_DATA_AREA
			}
			claimToBeSent.setHC1_COB_NBR_LINES(lines_in_return.size());
			logger.info(location.concat(" No of Reduction lines to be returned:").concat("[").concat(Integer.toString(claimToBeSent.getHC1_COB_NBR_LINES())).concat("]").concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"),e);
			e.printStackTrace();
		}

		return claimToBeSent;

	}

}