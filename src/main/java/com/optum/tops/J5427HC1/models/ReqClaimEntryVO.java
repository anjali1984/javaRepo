package com.optum.tops.J5427HC1.models;

import org.springframework.stereotype.Component;

@Component
public class ReqClaimEntryVO {
	
	private ReqClaimEntry reqClaimEntry;
	
	private String logId;

	public ReqClaimEntry getReqClaimEntry() {
		return reqClaimEntry;
	}

	public void setReqClaimEntry(ReqClaimEntry reqClaimEntry) {
		this.reqClaimEntry = reqClaimEntry;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
	
}
