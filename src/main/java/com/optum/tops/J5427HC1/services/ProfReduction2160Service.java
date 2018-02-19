package com.optum.tops.J5427HC1.services;
import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.JP835RED.RedProcessor;
import com.optum.tops.J5427HC1.models.LineReductionHold;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.JP54RedReturn;
import com.optum.tops.JP835RED.models.Ret835Reduct;



@Service
public class ProfReduction2160Service {

	@Autowired
	RedProcessor red_Processor ;
	Logger logger=Logger.getLogger("genLogger");

	public V5427HC1 do2160Section(ReqClaimEntryVO individual_claim2, V5427HC1 claimToBeSent)
	{
		String location="com.optum.tops.J5427HC1.services.ProfReduction2160Service.do2160Section(ReqClaimEntryVO, V5427HC1)";
		JP54RedRequest request_to_RED = new JP54RedRequest() ; 
		request_to_RED.setRED_INV_CTL_NBR(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_INVN_CTL_NBR());
		request_to_RED.setRED_ICN_SUFX_CD(claimToBeSent.getMy_indicator().getDBKE2_ICN_SUFX_CD());
		request_to_RED.setRED_PROC_DT(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_DT());
		request_to_RED.setRED_PROC_TM(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_TM());
		logger.info(location.concat(" Start red_Processor.ProClaim2200").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

		JP54RedReturn red_return = red_Processor.ProClaim2200(request_to_RED,individual_claim2.getLogId());
		
		logger.info(location.concat(" red_Processor.ProClaim2200 Completed  ").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

		List<Ret835Reduct> ret835Reduct = red_return.getRet835ReductArea(); 
		int clm_sub = 0 ; 
		logger.info(location.concat(" size of ret835Reduct returned from ProClaim2200:").concat("[").concat(Integer.toString(ret835Reduct.size())).concat("]").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

		while (clm_sub < ret835Reduct.size())
		{
			if (claimToBeSent.getMy_indicator().getNYSTATE_COB_CLAIM_PAIDTO().equals("S")
					&& claimToBeSent.getMy_indicator().isNYS_SERV_LINE_SW() ) { /// ASSUMPTION WS-NYS-SERV-LINE-SW (NYS_SERV_LINE) is a Claim level field that get overridden by the loop in 2121-Section																									/// ******
				// Go onto the next record, No need to do 2141
				clm_sub++;
				continue;
			}
			//perform 2161
			claimToBeSent=do2161Section(claimToBeSent,red_return,clm_sub,individual_claim2.getLogId());
			clm_sub++;
		}
		claimToBeSent=do2003PennySection(claimToBeSent,red_return,individual_claim2.getLogId());
		return claimToBeSent;
	}

	public V5427HC1 do2161Section(V5427HC1 claimToBeSent, JP54RedReturn red_return, int clm_sub, String logId)
	{
		
		String location="J5427HC1.services.ProfReduction2160Service.do2161Section(V5427HC1, JP54RedReturn, int, String)";
		try {
			List<Ret835Reduct> ret835Reduct = red_return.getRet835ReductArea(); 

			String ws_group_cd=ret835Reduct.get(clm_sub).getRET_835_RD_GRP_ID().trim();
			String ws_carc_cd=ret835Reduct.get(clm_sub).getRET_835_RD_CARC_CD().trim();
			String ws_rarc_Cd=ret835Reduct.get(clm_sub).getRET_835_RD_RARC_CD().trim();
			int svc_ln_sub=ret835Reduct.get(clm_sub).getRET_835_RD_SVC_ID() - 1;
			/*anjali:test BigDecimal.ONE.negate()*/
			
			LineReductionHold LINE_REDUCTION_HOLD =claimToBeSent.getMy_indicator().getWS_LINE_REDUCTION_TABLE().get(svc_ln_sub);

			if (ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT().equals(BigDecimal.ONE.negate()))
			{
				return claimToBeSent;
			}                                                                                                       

			
			int svc_sub=ret835Reduct.get(clm_sub).getRET_835_RD_SVC_ID() - 1;
			
			boolean svc_line_penny_ind[]=claimToBeSent.getMy_indicator().getSVC_LINE_PENNY_IND_ENTRY();
			if(svc_line_penny_ind[svc_sub] && ret835Reduct.get(clm_sub).getRET_835_RD_PROC_CD().equals(""))
			{
				return claimToBeSent;
			}
			logger.info(location.concat(" ws_group_cd:").concat("[").concat(ws_group_cd.trim()).concat("]").concat(" ws_carc_cd:").concat("[").concat(ws_carc_cd.trim()).concat("]").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			if(ws_group_cd.trim().equalsIgnoreCase("PR")){
				LINE_REDUCTION_HOLD.setPR_TOTAL(LINE_REDUCTION_HOLD.getPR_TOTAL().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
				switch(ws_carc_cd){
				case "45"://PR-OVER-RC
					LINE_REDUCTION_HOLD.setPR_OVERC(LINE_REDUCTION_HOLD.getPR_OVERC().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
					break;
				case "1"://PR-DEDUCT
					LINE_REDUCTION_HOLD.setPR_DEDUC(LINE_REDUCTION_HOLD.getPR_DEDUC().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
					break;
				case "3"://PR-COPAY
					LINE_REDUCTION_HOLD.setPR_COPAY(LINE_REDUCTION_HOLD.getPR_COPAY().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
					break;
				case "2"://PR-COINS
					LINE_REDUCTION_HOLD.setPR_COINS(LINE_REDUCTION_HOLD.getPR_COINS().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
					break;
				case "96"://PR-MEDC-EST-DENY
					if (ws_rarc_Cd.equals("N536"))
					{
						LINE_REDUCTION_HOLD.getPR_DENY_AMT().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT());
					}else{
						if (claimToBeSent.getHC1_COB_COB_835_PROC_IND().equals("M") && ws_rarc_Cd.equals("N12"))
						{

							LINE_REDUCTION_HOLD.setPR_MEDC_EST_AMT(LINE_REDUCTION_HOLD.getPR_MEDC_EST_AMT().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
						}
					}
					break;
				default:
					LINE_REDUCTION_HOLD.setPR_NTCOV(LINE_REDUCTION_HOLD.getPR_NTCOV().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
					break;
				}
			}else
			{
				if(ws_group_cd.equals("OA") && ws_carc_cd.equals("23"))
				{
					LINE_REDUCTION_HOLD.setCOB_PRIM_IMPAC(LINE_REDUCTION_HOLD.getCOB_PRIM_IMPAC().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
				}
				else
				{
					if(ws_group_cd.equals("CO"))
					{
						if(ws_carc_cd.equals("94") || ws_carc_cd.equals("97"))
						{
							LINE_REDUCTION_HOLD.setCAT_ID(LINE_REDUCTION_HOLD.getCAT_ID().add(BigDecimal.ONE));
						}else
						{
							LINE_REDUCTION_HOLD.setPRV_NC_AMT(LINE_REDUCTION_HOLD.getPRV_NC_AMT().add(ret835Reduct.get(clm_sub).getRET_835_RD_PD_AMT()));
						}
					}
				}
			}

			LINE_REDUCTION_HOLD.setSVC_LN_ID(new BigDecimal(ret835Reduct.get(clm_sub).getRET_835_RD_SVC_ID()));
			logger.info(location.concat(" SVC_LN_ID:").concat("[").concat(LINE_REDUCTION_HOLD.getSVC_LN_ID().toString()).concat("]").concat(" LOGID:").concat("[").concat(logId).concat("]"));
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		}

		return claimToBeSent;
	}
	/**
	 *  
	 * @param claimToBeSent
	 * @param red_return
	 * @param logId 
	 * @return
	 */
	public V5427HC1 do2003PennySection(V5427HC1 claimToBeSent, JP54RedReturn red_return, String logId)
	{
		String location="J5427HC1.services.ProfReduction2160Service.do2003PennySection(V5427HC1, JP54RedReturn, String)";
		int rev_sub=0;
		int svc_sub=0;		
		try {
			boolean SVC_LINE_PENNY_IND_ENTRY[]=claimToBeSent.getMy_indicator().getSVC_LINE_PENNY_IND_ENTRY();
			logger.info(location.concat(" size of Ret835PrcLvl :").concat("[").concat(Integer.toString(red_return.getRet835PrcLvl().size())).concat("]").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			while(rev_sub < red_return.getRet835PrcLvl().size())
				/*	anjali to be done:|| red_return.getRet835PrcLvl().get(rev_sub).getRET_835_ERR_SVC_ID()!=numeric)*/ 
			{
				if(red_return.getRet835PrcLvl().get(rev_sub).getRET_835_ERR_CD().equals("H30211"))
				{
					svc_sub=red_return.getRet835PrcLvl().get(rev_sub).getRET_835_ERR_SVC_ID().intValue();
					claimToBeSent.getMy_indicator().getSVC_LINE_PENNY_IND_ENTRY();
					if(!SVC_LINE_PENNY_IND_ENTRY[svc_sub])
					{
						return claimToBeSent;
					}
				}
				rev_sub++;
			}
			svc_sub=0;
			while(svc_sub<7)
			{
				if(SVC_LINE_PENNY_IND_ENTRY[svc_sub])
				{
					int clm_sub=0;
					while(clm_sub<3)
					{
						if(claimToBeSent.getHC1_COB_835_OOB_ERROR().get(clm_sub).equals("H30203")
								||claimToBeSent.getHC1_COB_835_OOB_ERROR().get(clm_sub).equals("H30205")
								||claimToBeSent.getHC1_COB_835_OOB_ERROR().get(clm_sub).equals("H30206")
								)
						{
							claimToBeSent.getHC1_COB_835_OOB_ERROR().set(clm_sub, "");
							clm_sub=7;
						}
					}
				}
				svc_sub++;
			}
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		}
		return claimToBeSent;
	}

}