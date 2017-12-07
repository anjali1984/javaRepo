package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;
import java.util.List;

//Mimics the incoming request in accordance with Q5427HC1 Copybook
public class Hc1Request {

	private BigDecimal HC1_REQ_NBR_CLAIMS ; 
	
	//Variable Area
	private List<ReqClaimEntry> ClaimEntries ; //Size of this can be 100 at max, depends on HC1_REQ_NBR_CLAIMS
	
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
