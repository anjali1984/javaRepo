package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;
import java.util.List;

//Mimics the incoming request in accordance with Q5427HC1 Copybook
public class Hc1Request {

	//Fixed Area in the Request 
	private Hc1ReqServiceInfo ReqServiceView; 
	private String Hc1ReqViewTypeInd ; //Indicator either "D" or "S" Detailed or Summary
	private String ReqPolNbr ; 
	private String ReqEEId ; 
	private BigDecimal HC1_REQ_NBR_CLAIMS ; 
	
	//Variable Area
	private List<ReqClaimEntry> ClaimEntries ; //Size of this can be 100 at max, depends on HC1_REQ_NBR_CLAIMS

	public Hc1ReqServiceInfo getReqServiceView() {
		return ReqServiceView;
	}

	public void setReqServiceView(Hc1ReqServiceInfo reqServiceView) {
		ReqServiceView = reqServiceView;
	}

	public String getHc1ReqViewTypeInd() {
		return Hc1ReqViewTypeInd;
	}

	public void setHc1ReqViewTypeInd(String hc1ReqViewTypeInd) {
		Hc1ReqViewTypeInd = hc1ReqViewTypeInd;
	}

	public String getReqPolNbr() {
		return ReqPolNbr;
	}

	public void setReqPolNbr(String reqPolNbr) {
		ReqPolNbr = reqPolNbr;
	}

	public String getReqEEId() {
		return ReqEEId;
	}

	public void setReqEEId(String reqEEId) {
		ReqEEId = reqEEId;
	}

	public BigDecimal getHC1_REQ_NBR_CLAIMS() {
		return HC1_REQ_NBR_CLAIMS;
	}

	public void setHC1_REQ_NBR_CLAIMS(BigDecimal hC1_REQ_NBR_CLAIMS) {
		HC1_REQ_NBR_CLAIMS = hC1_REQ_NBR_CLAIMS;
	}

	public List<ReqClaimEntry> getClaimEntries() {
		return ClaimEntries;
	}

	public void setClaimEntries(List<ReqClaimEntry> claimEntries) {
		ClaimEntries = claimEntries;
	}
	
	
	
}
