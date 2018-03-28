package com.optum.tops.J5427HC1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = J5427Hc1Application.class)

public class CallRestApiTest {
	
	Hc1Request hc1Req;

	@Test
	public void RestApiTest()
	{
		hc1Req=new Hc1Request();
		RestAssured.baseURI ="http://localhost:8080/HC1";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		try {

			//https://stackoverflow.com/questions/30005939/how-to-put-object-into-jsonobject-properly
			requestParams.put("hc1_REQ_NBR_CLAIMS", 1);

			JSONArray jsonclaimReqList=new JSONArray();
			JSONObject jsonclaimReq1=new JSONObject();
			jsonclaimReq1.put("reqPolNbr", "710711");
			jsonclaimReq1.put("reqEEId", "S071240969");
			jsonclaimReq1.put("hc1_REQ_CLM_INVN_CTL_NBR", "5949529216");
			jsonclaimReq1.put("hc1_REQ_CLM_DRFT_NBR", "0133928370");
			jsonclaimReq1.put("hc1_REQ_CLM_PROC_DT", "2016-04-29");
			jsonclaimReq1.put("hc1_REQ_CLM_PROC_TM", "21.09.31");
			jsonclaimReq1.put("hc1_REQ_CLM_TRANS_CD", "00");
			jsonclaimReq1.put("hc1_REQ_CLM_COB_IND", "M");
			jsonclaimReq1.put("hc1_REQ_RESPONSE_CODE",0);
			
			jsonclaimReqList.put(0,jsonclaimReq1);
			
			requestParams.put("claimEntries",jsonclaimReqList);
			
			System.out.println("json req====::"+requestParams.toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		request.body(requestParams.toString());

		// Post the request and check the response
		Response response = request.post("/COB");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("Response "+response.getBody().asString());
		//to deserialize the json string to HC1Response java object
		System.out.println("Response "+response.getBody().as(HC1Response.class));
	}

}
