package com.optum.tops.JP835RED.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

/**
 * :RET:-835-REDUCT-AREA portion of cobol copybook,VY835RET
 * Model Class for the record returned by CURS-LN-PD-AMT-RDUC, a list of these objects is populated
 * by the 7704-GET-LNE-RDUC section.
 */
@Component
public class Ret835Reduct {

	private int RET_835_RD_SVC_ID;
	private int RET_835_RD_REV_ID;
	private int RET_835_RD_REV_CD;
	private String RET_835_RD_PROC_CD;
	private String RET_835_RD_PROC_TYP_CD;
	private int RET_835_RD_CATGY_ID;
	private String RET_835_RD_GRP_ID;
	private String RET_835_RD_CARC_CD;
	private String RET_835_RD_RARC_CD;
	private BigDecimal RET_835_RD_PD_AMT=BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY);
	
	public int getRET_835_RD_SVC_ID() {
		return RET_835_RD_SVC_ID;
	}
	public void setRET_835_RD_SVC_ID(int rET_835_RD_SVC_ID) {
		RET_835_RD_SVC_ID = rET_835_RD_SVC_ID;
	}
	public int getRET_835_RD_REV_ID() {
		return RET_835_RD_REV_ID;
	}
	public void setRET_835_RD_REV_ID(int rET_835_RD_REV_ID) {
		RET_835_RD_REV_ID = rET_835_RD_REV_ID;
	}
	public int getRET_835_RD_REV_CD() {
		return RET_835_RD_REV_CD;
	}
	public void setRET_835_RD_REV_CD(int rET_835_RD_REV_CD) {
		RET_835_RD_REV_CD = rET_835_RD_REV_CD;
	}
	public String getRET_835_RD_PROC_CD() {
		return RET_835_RD_PROC_CD;
	}
	public void setRET_835_RD_PROC_CD(String rET_835_RD_PROC_CD) {
		RET_835_RD_PROC_CD = rET_835_RD_PROC_CD;
	}
	public String getRET_835_RD_PROC_TYP_CD() {
		return RET_835_RD_PROC_TYP_CD;
	}
	public void setRET_835_RD_PROC_TYP_CD(String rET_835_RD_PROC_TYP_CD) {
		RET_835_RD_PROC_TYP_CD = rET_835_RD_PROC_TYP_CD;
	}
	public int getRET_835_RD_CATGY_ID() {
		return RET_835_RD_CATGY_ID;
	}
	public void setRET_835_RD_CATGY_ID(int rET_835_RD_CATGY_ID) {
		RET_835_RD_CATGY_ID = rET_835_RD_CATGY_ID;
	}
	public String getRET_835_RD_GRP_ID() {
		return RET_835_RD_GRP_ID;
	}
	public void setRET_835_RD_GRP_ID(String rET_835_RD_GRP_ID) {
		RET_835_RD_GRP_ID = rET_835_RD_GRP_ID;
	}
	public String getRET_835_RD_CARC_CD() {
		return RET_835_RD_CARC_CD;
	}
	public void setRET_835_RD_CARC_CD(String rET_835_RD_CARC_CD) {
		RET_835_RD_CARC_CD = rET_835_RD_CARC_CD;
	}
	public String getRET_835_RD_RARC_CD() {
		return RET_835_RD_RARC_CD;
	}
	public void setRET_835_RD_RARC_CD(String rET_835_RD_RARC_CD) {
		RET_835_RD_RARC_CD = rET_835_RD_RARC_CD;
	}
	public BigDecimal getRET_835_RD_PD_AMT() {
		return RET_835_RD_PD_AMT;
	}
	public void setRET_835_RD_PD_AMT(BigDecimal rET_835_RD_PD_AMT) {
		RET_835_RD_PD_AMT = rET_835_RD_PD_AMT;
	}
	@Override
	public String toString() {
		return "Ret835Reduct [RET_835_RD_SVC_ID=" + RET_835_RD_SVC_ID + ", \n RET_835_RD_REV_ID=" + RET_835_RD_REV_ID
				+ ", \n RET_835_RD_REV_CD=" + RET_835_RD_REV_CD + ", \n RET_835_RD_PROC_CD=" + RET_835_RD_PROC_CD
				+ ", \n RET_835_RD_PROC_TYP_CD=" + RET_835_RD_PROC_TYP_CD + ", \n RET_835_RD_CATGY_ID=" + RET_835_RD_CATGY_ID
				+ ", \n RET_835_RD_GRP_ID=" + RET_835_RD_GRP_ID + ", \n RET_835_RD_CARC_CD=" + RET_835_RD_CARC_CD
				+ ", \n RET_835_RD_RARC_CD=" + RET_835_RD_RARC_CD + ", \n RET_835_RD_PD_AMT=" + RET_835_RD_PD_AMT + "]";
	}

	
	
}
