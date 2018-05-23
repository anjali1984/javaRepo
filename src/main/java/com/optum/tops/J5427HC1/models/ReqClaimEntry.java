package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ReqClaimEntry {

	private String hc1_REQ_CLM_INVN_CTL_NBR;
	private String hc1_REQ_CLM_DRFT_NBR;
	private String hc1_REQ_CLM_PROC_DT;
	
	private String hc1_REQ_CLM_PROC_TM;
	private String hc1_REQ_CLM_TRANS_CD;
	private String hc1_REQ_CLM_COB_IND;
	private BigDecimal hc1_REQ_RESPONSE_CODE=BigDecimal.ZERO;
	
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
	
	@Override
	public String toString() {
		return "ReqClaimEntry [hc1_REQ_CLM_INVN_CTL_NBR="
				+ hc1_REQ_CLM_INVN_CTL_NBR + ", hc1_REQ_CLM_DRFT_NBR=" + hc1_REQ_CLM_DRFT_NBR + ", hc1_REQ_CLM_PROC_DT="
				+ hc1_REQ_CLM_PROC_DT + ", hc1_REQ_CLM_PROC_TM=" + hc1_REQ_CLM_PROC_TM + ", hc1_REQ_CLM_TRANS_CD="
				+ hc1_REQ_CLM_TRANS_CD + ", hc1_REQ_CLM_COB_IND=" + hc1_REQ_CLM_COB_IND + ", hc1_REQ_RESPONSE_CODE="
				+ hc1_REQ_RESPONSE_CODE + "]";
	}

	
}
