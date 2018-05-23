package com.optum.tops.J5427HC1.JP835RED.DAO.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.tops.J5427HC1.JP835RED.DAO.IRedProcessor;
import com.optum.tops.J5427HC1.JP835RED.DAO.Iprocessing7700;
import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.JP54RedReturn;

@Service
public class RedProcessor implements IRedProcessor {
	/* evaluate the DSM-Function Code then pass it to either 2100 or 2200 based on that 
	 * error out if there isn't a proper function code (1-7)
	 *  we could just put the 2100 and 2200 functions in here ? depends how much we want to break the
	 *  functions up
	 *  
	 */
	@Autowired
	Iprocessing7700 processing7700; //DAO
	
	
	
	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.JP835RED.DAO.Imp.IRedProcessor#InstClaim2100(com.optum.tops.JP835RED.models.JP54RedRequest, java.lang.String)
	 */
	@Override
	public JP54RedReturn InstClaim2100(JP54RedRequest request_to_red, String logId){
	     /*read data from adjudicated tables for Institutional Claims 
		  *  It then calls the following sections:
		  *  7701, 7702, 7703, 7708 
		  *  did not add logic for the following: program = d5427MID, then it calls 7712) 
		  */
		JP54RedReturn return_to_D54Hc1 = new JP54RedReturn(); 
		return_to_D54Hc1.setRet835ClmRedTbl(processing7700.do7701(request_to_red,logId));
		return_to_D54Hc1.setRet835ClmRarcTbl(processing7700.do7702(request_to_red,logId));
		return_to_D54Hc1.setRet835ClmErrTbl(processing7700.do7703(request_to_red,logId));
		return_to_D54Hc1.setRetUB92_835_AdjdSvcInfo(processing7700.do7708(request_to_red,logId));

		return return_to_D54Hc1;
	}
	
	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.JP835RED.DAO.Imp.IRedProcessor#ProClaim2200(com.optum.tops.JP835RED.models.JP54RedRequest, java.lang.String)
	 */
	@Override
	public JP54RedReturn ProClaim2200(JP54RedRequest request_to_red, String logId){
		/*read data from adjudicated tables for a professional claim
		 * 
		 * This calls: 
		 * 7704, 7705, 7706, 7702, 7703, 7708  
		 * 
		 * did not add logic for the following: d5427MID call 7716)
		 */
		JP54RedReturn return_to_D54Hc1 = new JP54RedReturn();
		
		return_to_D54Hc1.setRet835ReductArea(processing7700.do7704(request_to_red,logId));
		return_to_D54Hc1.setRet835LineLvl(processing7700.do7705(request_to_red,logId));
		return_to_D54Hc1.setRet835PrcLvl(processing7700.do7706(request_to_red,logId));
		return_to_D54Hc1.setRet835ClmRarcTbl(processing7700.do7702(request_to_red,logId));
		return_to_D54Hc1.setRet835ClmErrTbl(processing7700.do7703(request_to_red,logId));
		return_to_D54Hc1.setRetUB92_835_AdjdSvcInfo(processing7700.do7708(request_to_red,logId));
		
		return return_to_D54Hc1;

	}
}
