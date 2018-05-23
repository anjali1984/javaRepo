package com.optum.tops.J5427HC1.services.Imp;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.dao.D54DAO.IOIMC_CoblnAmtsDao;
import com.optum.tops.J5427HC1.models.COBLN_2131;
import com.optum.tops.J5427HC1.models.LineReductionHold;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.ICOBLN2131Service;

@Service
public class COBLN2131Service implements ICOBLN2131Service {

	@Autowired 
	IOIMC_CoblnAmtsDao OimcCobAmtsDao ;

	List<COBLN_2131> results ; 
	Logger logger=Logger.getLogger("genLogger");
	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.services.ICOBLN2131Service#do2131Logic(com.optum.tops.J5427HC1.models.ReqClaimEntryVO, com.optum.tops.J5427HC1.models.V5427HC1)
	 */
	@Override
	public V5427HC1 do2131Logic(ReqClaimEntryVO individual_claim2, V5427HC1 outbound_claim){
		String location="J5427HC1.services.COBLN2131Service.do2131Logic(ReqClaimEntryVO, V5427HC1)";
		try{
			results = OimcCobAmtsDao.getData(individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_INVN_CTL_NBR(), outbound_claim.getMy_indicator().getDBKE2_ICN_SUFX_CD(),individual_claim2.getLogId()); 
			logger.info(location.concat("No of lines returned from  OimcCobAmtsDao.getData").concat("[").concat(Integer.toString(results.size())).concat("]")
					.concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));
			BigDecimal ln_chrg_amt, ln_nc_amt , ln_medc_paid_amt;
			List<LineReductionHold> lineRedDataTable = outbound_claim.getMy_indicator().getWS_LINE_REDUCTION_TABLE(); //On a claim level
			//System.out.println("Size of the redcution table in 2131 Service " +lineRedDataTable.size());


			for(COBLN_2131 each_record: results){
				//System.out.println(" Each line in COBLN2131Service " + each_record.getOrig_Ln_Corr_Id() );

				/*NEEDS REFACTORING because of 
				 * how Ln_CORR_ID and Ln_Id are 
				 * used to index into the ArrayList */

				int index = each_record.getOrig_Ln_Corr_Id() - 1 ; // index into the Line Reduction table So for ln_id 1 index is 0....
				//int index = each_record.getOrig_Ln_Corr_Id() - 2; //Because Ln_Corr_id started at 2 


				ln_chrg_amt = lineRedDataTable.get(index).getLINE_CHRG_AMT() ;
				ln_nc_amt = lineRedDataTable.get(index).getLINE_NC_AMT(); 
				if(ln_chrg_amt.compareTo(ln_nc_amt) == 0 && ln_chrg_amt.compareTo(BigDecimal.ZERO) > 0){
					ln_medc_paid_amt = lineRedDataTable.get(index).getLN_MEDC_PAID_AMT(); 
					BigDecimal temp = ln_medc_paid_amt.add(each_record.getMedcr_Pd_Amt()); 
					lineRedDataTable.get(index).setLN_MEDC_PAID_AMT(temp);
				}

			}
		
		}catch(Exception e)
		{
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"),e);
			e.printStackTrace();
		}
		return outbound_claim;
	}
}
