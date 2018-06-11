package com.optum.tops.J5427HC1.controllers.Imp;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.tops.J5427HC1.controllers.IHC1Controller;
import com.optum.tops.J5427HC1.exception.ErrorResponse;
import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.HC1_COB_LINE_ENTRY;
import com.optum.tops.J5427HC1.models.HC1_COB_LINE_ENTRY_String;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.models.V5427HC1String;
import com.optum.tops.J5427HC1.services.IRequestProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class HC1Controller implements IHC1Controller{

	@Autowired
	IRequestProcessor requestProcessorService; 
	@Autowired
	private DataSource ds; 
	@Autowired
	ErrorResponse errResp;
	@Autowired
	V5427HC1String respString;
	@Autowired 
	HC1_COB_LINE_ENTRY_String cobLineString;

	Logger logger=Logger.getLogger("genLogger");
	Connection con;


	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.controllers.IHC1Controller#getCOBDetails(com.optum.tops.J5427HC1.models.Hc1Request)
	 */
	@Override
	@RequestMapping(value = "/COB" , method = RequestMethod.POST,
	consumes="application/json",produces="application/json")

	@ResponseBody
	public ResponseEntity<?> getCOBDetails(@RequestBody Hc1Request request) throws JsonProcessingException{
		logger.info("request in controller:"+request);
		System.out.println("deny amount:"+new V5427HC1().getHC1_COB_835_DENY_NC_AMT() );
		HC1Response response = requestProcessorService.process(request); //to be sent back to the calling program as a Response

		int noCobLnRequest=0;

		List respArray=new ArrayList();
		HashMap<String,List> RespClaimsToBeSent=new HashMap<String,List>();
		for (V5427HC1 res:response.getResponse_map_all_claims().values())
		{

			respString=convertBigdecimalToString(res);
			respArray.add(respString);

			if (res.getHC1_COB_NBR_LINES()==0)
			{
				noCobLnRequest++;
			}


		}
		RespClaimsToBeSent.put("response_map_all_claims",respArray);
		if(response.getResponse_map_all_claims().size()==noCobLnRequest && noCobLnRequest!=0)

		{
			errResp.setErrMsg("Requested data not found.");
			errResp.setAdvice("Try with different request data");
			errResp.setErrorCd("404");
			return new ResponseEntity<ErrorResponse>(errResp, HttpStatus.NOT_FOUND);//http 404

		}
		else if(!testDbConn(ds))
		{

			errResp.setErrMsg("Service currently not available");
			errResp.setAdvice("Please try after sometime");
			errResp.setErrorCd("503");
			return new ResponseEntity<ErrorResponse>(errResp, HttpStatus.SERVICE_UNAVAILABLE);//http 503
		}
		else
		{
			return new ResponseEntity<HashMap>(RespClaimsToBeSent, HttpStatus.OK);//http 200
		}
	}

	private V5427HC1String convertBigdecimalToString(V5427HC1 res) {
		// TODO Auto-generated method stub
		List cobLinesString=new ArrayList();
		HC1_COB_LINE_ENTRY_String cobLineString=new HC1_COB_LINE_ENTRY_String();
		V5427HC1String respStr=new V5427HC1String();
		//set cob line data

		for(HC1_COB_LINE_ENTRY eachCobline:res.getHC1_COB_LNE_DATA_AREA())
		{
			if(res.getHC1_COB_LNE_DATA_AREA().size()>0)
			{
				cobLineString.setHC1_COB_LINE_AUTH_PROC_CD(eachCobline.getHC1_COB_LINE_AUTH_PROC_CD().toString());
				cobLineString.setHC1_COB_LINE_CHRG_AMT(eachCobline.getHC1_COB_LINE_CHRG_AMT().toString());
				cobLineString.setHC1_COB_LINE_FST_DT(eachCobline.getHC1_COB_LINE_FST_DT().toString());
				cobLineString.setHC1_COB_LINE_LN_ID(eachCobline.getHC1_COB_LINE_LN_ID());
				cobLineString.setHC1_COB_LINE_LST_SRVC_DT(eachCobline.getHC1_COB_LINE_SRVC_CD());
				cobLineString.setHC1_COB_LINE_PMT_SVC_CD(eachCobline.getHC1_COB_LINE_PMT_SVC_CD());
				cobLineString.setHC1_COB_LINE_SRVC_CD(eachCobline.getHC1_COB_LINE_SRVC_CD());
				cobLineString.setHC1_COB_LN_835_COB_PRIM_IMPAC(eachCobline.getHC1_COB_LN_835_COB_PRIM_IMPAC().toString());
				cobLineString.setHC1_COB_LN_835_DENY_NC_AMT(eachCobline.getHC1_COB_LN_835_DENY_NC_AMT().toString());
				cobLineString.setHC1_COB_LN_835_MEDC_EST_AMT(eachCobline.getHC1_COB_LN_835_MEDC_EST_AMT().toString());
				cobLineString.setHC1_COB_LN_835_PAT_RESP_COINS(eachCobline.getHC1_COB_LN_835_PAT_RESP_COINS().toString());
				cobLineString.setHC1_COB_LN_835_PAT_RESP_COPAY(eachCobline.getHC1_COB_LN_835_PAT_RESP_COPAY().toString());
				cobLineString.setHC1_COB_LN_835_PAT_RESP_DEDUC(eachCobline.getHC1_COB_LN_835_PAT_RESP_DEDUC().toString());
				cobLineString.setHC1_COB_LN_835_PAT_RESP_NTCOV(eachCobline.getHC1_COB_LN_835_PAT_RESP_NTCOV().toString());
				cobLineString.setHC1_COB_LN_835_PAT_RESP_OVERC(eachCobline.getHC1_COB_LN_835_PAT_RESP_NTCOV().toString());
				cobLineString.setHC1_COB_LN_835_PAT_RESP_TOTAL(eachCobline.getHC1_COB_LN_835_PAT_RESP_TOTAL().toString());
				cobLineString.setHC1_COB_LN_835_PRV_NC_AMT(eachCobline.getHC1_COB_LN_835_PAT_RESP_TOTAL().toString());
				cobLineString.setHC1_COB_LN_835_RPT_ALLOW_AMT(eachCobline.getHC1_COB_LN_835_RPT_ALLOW_AMT().toString());
				cobLineString.setHC1_COB_LN_ALLOW_AMT_IND(eachCobline.getHC1_COB_LN_ALLOW_AMT_IND());
				cobLineString.setHC1_COB_LN_COB_PRV_WRT_OFF(eachCobline.getHC1_COB_LN_COB_PRV_WRT_OFF().toString());
				cobLineString.setHC1_COB_LN_EOB_MEDC_PAID_AMT(eachCobline.getHC1_COB_LN_EOB_MEDC_PAID_AMT().toString());
				cobLineString.setHC1_COB_LN_EOB_OI_PAID_AMT(eachCobline.getHC1_COB_LN_EOB_OI_PAID_AMT().toString());
				cobLineString.setHC1_COB_LN_SRV_IND(eachCobline.getHC1_COB_LN_SRV_IND());


			}
			cobLinesString.add(cobLineString);

		}
		respStr.setHC1_COB_LINE_DATA_AREA(cobLinesString);

		//set claim level fields

		respStr.setError(res.getError());
		respStr.setHC1_COB_835_COB_PRIM_IMPAC(res.getHC1_COB_835_COB_PRIM_IMPAC().toString());
		respStr.setHC1_COB_835_DENY_NC_AMT(res.getHC1_COB_835_DENY_NC_AMT().toString());
		respStr.setHC1_COB_835_OOB_ERROR(res.getHC1_COB_835_OOB_ERROR());
		respStr.setHC1_COB_835_PAT_RESP_COINS(res.getHC1_COB_835_PAT_RESP_COINS().toString());
		respStr.setHC1_COB_835_PAT_RESP_COPAY(res.getHC1_COB_835_PAT_RESP_COPAY().toString());
		respStr.setHC1_COB_835_PAT_RESP_DEDUC(res.getHC1_COB_835_PAT_RESP_DEDUC().toString());
		respStr.setHC1_COB_835_PAT_RESP_MCEST(res.getHC1_COB_835_PAT_RESP_MCEST().toString());
		respStr.setHC1_COB_835_PAT_RESP_NTCOV(res.getHC1_COB_835_PAT_RESP_NTCOV().toString());
		respStr.setHC1_COB_835_PAT_RESP_OVERC(res.getHC1_COB_835_PAT_RESP_OVERC().toString());
		respStr.setHC1_COB_835_PAT_RESP_TOTAL(res.getHC1_COB_835_PAT_RESP_TOTAL().toString());
		respStr.setHC1_COB_835_PRV_NC_AMT(res.getHC1_COB_835_PRV_NC_AMT().toString());
		respStr.setHC1_COB_835_RPT_ALLOW_AMT(res.getHC1_COB_835_RPT_ALLOW_AMT().toString());
		respStr.setHC1_COB_ALLOW_AMT_IND(res.getHC1_COB_ALLOW_AMT_IND());
		respStr.setHC1_COB_COB_CLAIM_INDICATOR(res.getHC1_COB_COB_CLAIM_INDICATOR());
		respStr.setHC1_COB_INST_OR_PROF(res.getHC1_COB_INST_OR_PROF());
		respStr.setHC1_COB_INVENTORY_CONTROL_DT(res.getHC1_COB_INVENTORY_CONTROL_DT());
		respStr.setHC1_COB_MEDC_PAID_AMT(res.getHC1_COB_MEDC_PAID_AMT().toString());
		respStr.setHC1_COB_NBR_LINES(res.getHC1_COB_NBR_LINES());
		respStr.setHC1_COB_NEW_COB_835_PROC_IND(res.getHC1_COB_NEW_COB_835_PROC_IND().toString());
		respStr.setHC1_COB_NEW_COB_CALC_IND(res.getHC1_COB_NEW_COB_CALC_IND());
		respStr.setHC1_COB_OI_PAID_AMT(res.getHC1_COB_OI_PAID_AMT().toString());
		respStr.setHC1_COB_PRV_WRT_OFF(res.getHC1_COB_PRV_WRT_OFF().toString());
		respStr.setHC1_COB_835_PAT_RESP_MCEST(res.getHC1_COB_835_PAT_RESP_MCEST().toString());

		return respStr;
	}

	public Boolean testDbConn(DataSource ds)
	{
		Boolean conSw=true;

		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			conSw=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conSw;
	}

}
