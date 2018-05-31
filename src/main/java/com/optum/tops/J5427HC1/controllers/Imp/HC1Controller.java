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
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.IRequestProcessor;

@Controller
public class HC1Controller implements IHC1Controller{

	@Autowired
	IRequestProcessor requestProcessorService; 
	@Autowired
	private DataSource ds; 
	@Autowired
	ErrorResponse errResp;
	Logger logger=Logger.getLogger("genLogger");
	Connection con;


	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.controllers.IHC1Controller#getCOBDetails(com.optum.tops.J5427HC1.models.Hc1Request)
	 */
	@Override
	@RequestMapping(value = "/COB" , method = RequestMethod.POST,
	consumes="application/json",produces="application/json")
	
	@ResponseBody
	public ResponseEntity<?> getCOBDetails(@RequestBody Hc1Request request){
		logger.info("request in controller:"+request);
		System.out.println("requestProcessorServic:"+requestProcessorService);
		HC1Response response = requestProcessorService.process(request); //to be sent back to the calling program as a Response
		int noCobLnRequest=0;
		List respArray=new ArrayList();
		HashMap<String,List> RespClaimsToBeSent=new HashMap<String,List>();
		
		for (V5427HC1 res:response.getResponse_map_all_claims().values())
		{
			respArray.add(res);
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
