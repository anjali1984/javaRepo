package com.optum.tops.J5427HC1.services.Imp;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.ICOBONL2200Service;

@Service
public class COBONL2200Service implements ICOBONL2200Service {
	
	private  Logger logger=Logger.getLogger("genLogger");
	

	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.services.ICOBONL2200Service#WriteOff2200(com.optum.tops.J5427HC1.models.V5427HC1, com.optum.tops.J5427HC1.models.ReqClaimEntryVO)
	 */
		@Override
		public V5427HC1 WriteOff2200 (V5427HC1 currentClaim, ReqClaimEntryVO individual_claim){
			String location="J5427HC1.services.COBONL2200Service.WriteOff2200(V5427HC1)";
						
		  /*anjali:sysout and compute can be removed once tested.*/
		 
		try {
			currentClaim.setHC1_COB_PRV_WRT_OFF(BigDecimal.ZERO);
			/*
			 * Recalculation for professional or medicare estimated   service lines
			 */
			logger.info(location.concat("HC1_COB_INST_OR_PROF:").concat("[").concat(currentClaim.getHC1_COB_INST_OR_PROF()).concat("]")
					.concat("HC1_COB_INST_OR_PROF:").concat("[").concat(currentClaim.getHC1_COB_INST_OR_PROF()).concat("]")
					.concat("HC1_COB_COB_835_PROC_IND:").concat("[").concat(currentClaim.getHC1_COB_COB_835_PROC_IND()).concat("]")
					.concat(" LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"));

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
					System.out.println("in 2200 med amnt "+currentClaim.getHC1_COB_MEDC_PAID_AMT());
				}
				int cobDxCnt=0;
				
				while(cobDxCnt < currentClaim.getHC1_COB_NBR_LINES()){
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
					System.out.println("In 2200, modifying the prv_wrt_off amount of the line, based on the 4 other line level amounts:  " + compute);
					currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_COB_PRV_WRT_OFF(compute);
					/*
					 * Provider write-off changed to BigDecimal.ZERO at line   level for inst. medicare estimated claims
					 */
					if(currentClaim.getHC1_COB_COB_835_PROC_IND().equals("M") && currentClaim.getHC1_COB_INST_OR_PROF().equals("I"))
					{
						currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_COB_PRV_WRT_OFF(BigDecimal.ZERO);
					}
					currentClaim.setHC1_COB_PRV_WRT_OFF(currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).getHC1_COB_LN_COB_PRV_WRT_OFF().add(currentClaim.getHC1_COB_PRV_WRT_OFF()));
					cobDxCnt++;
				}
			}
			if(currentClaim.getHC1_COB_INST_OR_PROF().equals("I") && currentClaim.getHC1_COB_COB_835_PROC_IND().equals("Y"))
			{
				BigDecimal compute=((currentClaim.getHC1_COB_835_PRV_NC_AMT().add(currentClaim.getHC1_COB_835_COB_PRIM_IMPAC())).subtract(currentClaim.getHC1_COB_MEDC_PAID_AMT()))
						.subtract(currentClaim.getHC1_COB_OI_PAID_AMT());
				currentClaim.setHC1_COB_PRV_WRT_OFF(compute);
System.out.println("currentClaim.getHC1_COB_835_PRV_NC_AMT() "+currentClaim.getHC1_COB_835_PRV_NC_AMT());
System.out.println("currentClaim.getHC1_COB_835_COB_PRIM_IMPAC() "+currentClaim.getHC1_COB_835_COB_PRIM_IMPAC());
System.out.println("currentClaim.getHC1_COB_MEDC_PAID_AMT() "+currentClaim.getHC1_COB_MEDC_PAID_AMT());
System.out.println("currentClaim.getHC1_COB_OI_PAID_AMT() "+currentClaim.getHC1_COB_OI_PAID_AMT());
				
				for (int i=0;i<currentClaim.getHC1_COB_LNE_DATA_AREA().size();i++)
					currentClaim.getHC1_COB_LNE_DATA_AREA().get(i).setHC1_COB_LN_COB_PRV_WRT_OFF(BigDecimal.ZERO);
			}
			return currentClaim;
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim.getLogId()).concat("]"),e);
			e.printStackTrace();
		}
		finally{

		}
		return currentClaim;
	}
		
		
}

	


