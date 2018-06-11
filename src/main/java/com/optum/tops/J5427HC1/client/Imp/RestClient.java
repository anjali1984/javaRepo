package com.optum.tops.J5427HC1.client.Imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;

public class RestClient {

	static final String uri="http://localhost:8080/HC1/COB";
	public static void main(String[] args)
	{

		Hc1Request req=new Hc1Request();
		ReqClaimEntry clmEntry=new ReqClaimEntry();

		req.setHc1_REQ_NBR_CLAIMS(1);
		clmEntry.setHc1_REQ_CLM_COB_IND("M");
		clmEntry.setHc1_REQ_CLM_DRFT_NBR("0133928370");
		clmEntry.setHc1_REQ_CLM_INVN_CTL_NBR("5949529216");
		clmEntry.setHc1_REQ_CLM_PROC_DT("2016-04-29");
		clmEntry.setHc1_REQ_CLM_PROC_TM("21.09.31");
		clmEntry.setHc1_REQ_CLM_TRANS_CD("00");
		clmEntry.setHc1_REQ_RESPONSE_CODE(BigDecimal.valueOf(0.00));
		List <ReqClaimEntry> clmEntryList=new ArrayList<ReqClaimEntry>();
		clmEntryList.add(clmEntry);
		req.setClaimEntries(clmEntryList);

		RestTemplate restTemp=new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		//tells that request format is json
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Data attached to the request
		HttpEntity<Hc1Request> requestBody=new HttpEntity<Hc1Request>(req);

		ResponseEntity<HC1Response> resp=restTemp.postForEntity(uri, requestBody, HC1Response.class);

		if(resp.getStatusCode()==HttpStatus.OK)
		{
			System.out.println("Response :: "+resp.getBody());
		}

	}

}
