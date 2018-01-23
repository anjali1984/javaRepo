package com.optum.tops.JP835RED.models;

import java.math.BigDecimal;

public class Ret835PrcLvl {
	private BigDecimal RET_835_ERR_SVC_ID =BigDecimal.ZERO;
	private BigDecimal RET_835_ERR_REV_ID  =BigDecimal.ZERO;
	private BigDecimal RET_835_ERR_REV_CD  =BigDecimal.ZERO;
	private String RET_835_ERR_PROC_CD;    
	private String RET_835_ERR_PROC_TYP_CD;
	private String RET_835_ERR_CD;         
	private String RET_835_ERR_TYP_CD;
	
	//getters and setters
	public BigDecimal getRET_835_ERR_SVC_ID() {
		return RET_835_ERR_SVC_ID;
	}
	public void setRET_835_ERR_SVC_ID(BigDecimal rET_835_ERR_SVC_ID) {
		RET_835_ERR_SVC_ID = rET_835_ERR_SVC_ID;
	}
	public BigDecimal getRET_835_ERR_REV_ID() {
		return RET_835_ERR_REV_ID;
	}
	public void setRET_835_ERR_REV_ID(BigDecimal rET_835_ERR_REV_ID) {
		RET_835_ERR_REV_ID = rET_835_ERR_REV_ID;
	}
	public BigDecimal getRET_835_ERR_REV_CD() {
		return RET_835_ERR_REV_CD;
	}
	public void setRET_835_ERR_REV_CD(BigDecimal rET_835_ERR_REV_CD) {
		RET_835_ERR_REV_CD = rET_835_ERR_REV_CD;
	}
	public String getRET_835_ERR_PROC_CD() {
		return RET_835_ERR_PROC_CD;
	}
	public void setRET_835_ERR_PROC_CD(String rET_835_ERR_PROC_CD) {
		RET_835_ERR_PROC_CD = rET_835_ERR_PROC_CD;
	}
	public String getRET_835_ERR_PROC_TYP_CD() {
		return RET_835_ERR_PROC_TYP_CD;
	}
	public void setRET_835_ERR_PROC_TYP_CD(String rET_835_ERR_PROC_TYP_CD) {
		RET_835_ERR_PROC_TYP_CD = rET_835_ERR_PROC_TYP_CD;
	}
	public String getRET_835_ERR_CD() {
		return RET_835_ERR_CD;
	}
	public void setRET_835_ERR_CD(String rET_835_ERR_CD) {
		RET_835_ERR_CD = rET_835_ERR_CD;
	}
	public String getRET_835_ERR_TYP_CD() {
		return RET_835_ERR_TYP_CD;
	}
	public void setRET_835_ERR_TYP_CD(String rET_835_ERR_TYP_CD) {
		RET_835_ERR_TYP_CD = rET_835_ERR_TYP_CD;
	}     


}