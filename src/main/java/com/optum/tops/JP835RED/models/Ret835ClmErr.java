package com.optum.tops.JP835RED.models;

public class Ret835ClmErr {

	
	private String CLM_ERR_CD ; 
	private String CLM_ERR_TYP_CD;
	
	public String getCLM_ERR_CD() {
		return CLM_ERR_CD;
	}
	public void setCLM_ERR_CD(String cLM_ERR_CD) {
		CLM_ERR_CD = cLM_ERR_CD;
	}
	public String getCLM_ERR_TYP_CD() {
		return CLM_ERR_TYP_CD;
	}
	public void setCLM_ERR_TYP_CD(String cLM_ERR_TYP_CD) {
		CLM_ERR_TYP_CD = cLM_ERR_TYP_CD;
	} 
	@Override
	public String toString() {
		return "Ret835ClmErr [CLM_ERR_CD=" + CLM_ERR_CD + ", CLM_ERR_TYP_CD=" + CLM_ERR_TYP_CD + "]";
	}
}
