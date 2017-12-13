package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;

public class ReqClaimEntry {

	// Fixed Area in the Request
	//private Hc1ReqServiceInfo ReqServiceView;  
	//private String Hc1ReqViewTypeInd; // Indicator either "D" or "S" Detailed or Summary
	private String reqPolNbr;
	private String reqEEId;
	
	private String hc1_REQ_CLM_INVN_CTL_NBR;
	private String hc1_REQ_CLM_DRFT_NBR;
	private String hc1_REQ_CLM_PROC_DT;
	private String hc1_REQ_CLM_PROC_TM;
	private String hc1_REQ_CLM_TRANS_CD;
	private String hc1_REQ_CLM_COB_IND;
	private BigDecimal hc1_REQ_RESPONSE_CODE;
	public String getReqPolNbr() {
		return reqPolNbr;
	}
	public void setReqPolNbr(String reqPolNbr) {
		this.reqPolNbr = reqPolNbr;
	}
	public String getReqEEId() {
		return reqEEId;
	}
	public void setReqEEId(String reqEEId) {
		this.reqEEId = reqEEId;
	}
	public String getHc1_REQ_CLM_INVN_CTL_NBR() {
		return hc1_REQ_CLM_INVN_CTL_NBR;
	}
	public void setHc1_REQ_CLM_INVN_CTL_NBR(String hc1_REQ_CLM_INVN_CTL_NBR) {
		this.hc1_REQ_CLM_INVN_CTL_NBR = hc1_REQ_CLM_INVN_CTL_NBR;
	}
	public String getHc1_REQ_CLM_DRFT_NBR() {
		return hc1_REQ_CLM_DRFT_NBR;
	}
	public void setHc1_REQ_CLM_DRFT_NBR(String hc1_REQ_CLM_DRFT_NBR) {
		this.hc1_REQ_CLM_DRFT_NBR = hc1_REQ_CLM_DRFT_NBR;
	}
	public String getHc1_REQ_CLM_PROC_DT() {
		return hc1_REQ_CLM_PROC_DT;
	}
	public void setHc1_REQ_CLM_PROC_DT(String hc1_REQ_CLM_PROC_DT) {
		this.hc1_REQ_CLM_PROC_DT = hc1_REQ_CLM_PROC_DT;
	}
	public String getHc1_REQ_CLM_PROC_TM() {
		return hc1_REQ_CLM_PROC_TM;
	}
	public void setHc1_REQ_CLM_PROC_TM(String hc1_REQ_CLM_PROC_TM) {
		this.hc1_REQ_CLM_PROC_TM = hc1_REQ_CLM_PROC_TM;
	}
	public String getHc1_REQ_CLM_TRANS_CD() {
		return hc1_REQ_CLM_TRANS_CD;
	}
	public void setHc1_REQ_CLM_TRANS_CD(String hc1_REQ_CLM_TRANS_CD) {
		this.hc1_REQ_CLM_TRANS_CD = hc1_REQ_CLM_TRANS_CD;
	}
	public String getHc1_REQ_CLM_COB_IND() {
		return hc1_REQ_CLM_COB_IND;
	}
	public void setHc1_REQ_CLM_COB_IND(String hc1_REQ_CLM_COB_IND) {
		this.hc1_REQ_CLM_COB_IND = hc1_REQ_CLM_COB_IND;
	}
	public BigDecimal getHc1_REQ_RESPONSE_CODE() {
		return hc1_REQ_RESPONSE_CODE;
	}
	public void setHc1_REQ_RESPONSE_CODE(BigDecimal hc1_REQ_RESPONSE_CODE) {
		this.hc1_REQ_RESPONSE_CODE = hc1_REQ_RESPONSE_CODE;
	}
	
	

	/*public Hc1ReqServiceInfo getReqServiceView() {
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
	}*/

	
}
