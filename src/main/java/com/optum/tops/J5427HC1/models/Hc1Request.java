package com.optum.tops.J5427HC1.models;

import java.util.List;

//Mimics the incoming request in accordance with Q5427HC1 Copybook
public class Hc1Request {

	private int hc1_REQ_NBR_CLAIMS ; 
	
	//Variable Area
	private List<ReqClaimEntry> claimEntries ; //Size of this can be 100 at max, depends on HC1_REQ_NBR_CLAIMS

	public int getHc1_REQ_NBR_CLAIMS() {
		return hc1_REQ_NBR_CLAIMS;
	}

	public void setHc1_REQ_NBR_CLAIMS(int hc1_REQ_NBR_CLAIMS) {
		this.hc1_REQ_NBR_CLAIMS = hc1_REQ_NBR_CLAIMS;
	}

	public List<ReqClaimEntry> getClaimEntries() {
		return claimEntries;
	}

	public void setClaimEntries(List<ReqClaimEntry> claimEntries) {
		this.claimEntries = claimEntries;
	}

	@Override
	public String toString() {
		return "Hc1Request [hc1_REQ_NBR_CLAIMS=" + hc1_REQ_NBR_CLAIMS + ", claimEntries=" + claimEntries + "]";
	}
	
	
	
}
