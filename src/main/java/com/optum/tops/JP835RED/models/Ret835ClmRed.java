package com.optum.tops.JP835RED.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

//Model Class representing record from CURS-FACL-PD-RDUC cursor in RED. Populated by 7701 section
public class Ret835ClmRed {
	private String CLM_RD_CATGY_ID ; 
	private String CLM_RD_GRP_CD ; 
	private String CLM_RD_CARC_CD; 
	private String CLM_RD_RARC_CD; 
	private BigDecimal CLM_RD_PD_AMT = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY);
	
	public String getCLM_RD_CATGY_ID() {
		return CLM_RD_CATGY_ID;
	}
	public void setCLM_RD_CATGY_ID(String cLM_RD_CATGY_ID) {
		CLM_RD_CATGY_ID = cLM_RD_CATGY_ID;
	}
	public String getCLM_RD_GRP_CD() {
		return CLM_RD_GRP_CD;
	}
	public void setCLM_RD_GRP_CD(String cLM_RD_GRP_CD) {
		CLM_RD_GRP_CD = cLM_RD_GRP_CD;
	}
	public String getCLM_RD_CARC_CD() {
		return CLM_RD_CARC_CD;
	}
	public void setCLM_RD_CARC_CD(String cLM_RD_CARC_CD) {
		CLM_RD_CARC_CD = cLM_RD_CARC_CD;
	}
	public String getCLM_RD_RARC_CD() {
		return CLM_RD_RARC_CD;
	}
	public void setCLM_RD_RARC_CD(String cLM_RD_RARC_CD) {
		CLM_RD_RARC_CD = cLM_RD_RARC_CD;
	}
	public BigDecimal getCLM_RD_PD_AMT() {
		return CLM_RD_PD_AMT;
	}
	public void setCLM_RD_PD_AMT(BigDecimal cLM_RD_PD_AMT) {
		CLM_RD_PD_AMT = cLM_RD_PD_AMT;
	} 
	
	
}
