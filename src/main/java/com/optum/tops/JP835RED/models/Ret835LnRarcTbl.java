package com.optum.tops.JP835RED.models;

import java.math.BigDecimal;

/**
 * :RET:-835-LN-RARC-TBL portion of cobol copybook,VY835RET
 * @author ayadav38
 *
 */
public class Ret835LnRarcTbl {
	private String RET_835_LN_RARC_CD;
	private String RET_835_LN_RMRK_CD;
	private BigDecimal RET_835_LN_REV_ID=BigDecimal.ZERO;
	private BigDecimal RET_835_LN_REV_CD=BigDecimal.ZERO;
	private String RET_835_LN_PROC_CD;
	private String RET_835_LN_PROC_TYP_CD;
	
	//getters & setters
	public String getRET_835_LN_RMRK_CD() {
		return RET_835_LN_RMRK_CD;
	}
	public void setRET_835_LN_RMRK_CD(String rET_835_LN_RMRK_CD) {
		RET_835_LN_RMRK_CD = rET_835_LN_RMRK_CD;
	}
	public BigDecimal getRET_835_LN_REV_ID() {
		return RET_835_LN_REV_ID;
	}
	public void setRET_835_LN_REV_ID(BigDecimal rET_835_LN_REV_ID) {
		RET_835_LN_REV_ID = rET_835_LN_REV_ID;
	}
	public BigDecimal getRET_835_LN_REV_CD() {
		return RET_835_LN_REV_CD;
	}
	public void setRET_835_LN_REV_CD(BigDecimal rET_835_LN_REV_CD) {
		RET_835_LN_REV_CD = rET_835_LN_REV_CD;
	}
	public String getRET_835_LN_PROC_CD() {
		return RET_835_LN_PROC_CD;
	}
	public void setRET_835_LN_PROC_CD(String rET_835_LN_PROC_CD) {
		RET_835_LN_PROC_CD = rET_835_LN_PROC_CD;
	}
	public String getRET_835_LN_PROC_TYP_CD() {
		return RET_835_LN_PROC_TYP_CD;
	}
	public void setRET_835_LN_PROC_TYP_CD(String rET_835_LN_PROC_TYP_CD) {
		RET_835_LN_PROC_TYP_CD = rET_835_LN_PROC_TYP_CD;
	}
	public String getRET_835_LN_RARC_CD() {
		return RET_835_LN_RARC_CD;
	}
	public void setRET_835_LN_RARC_CD(String rET_835_LN_RARC_CD) {
		RET_835_LN_RARC_CD = rET_835_LN_RARC_CD;
	}
	@Override
	public String toString() {
		return "Ret835LnRarcTbl [RET_835_LN_RARC_CD=" + RET_835_LN_RARC_CD + ", \n RET_835_LN_RMRK_CD="
				+ RET_835_LN_RMRK_CD + ", \n RET_835_LN_REV_ID=" + RET_835_LN_REV_ID + ", \n RET_835_LN_REV_CD="
				+ RET_835_LN_REV_CD + ", \n RET_835_LN_PROC_CD=" + RET_835_LN_PROC_CD + ", \n RET_835_LN_PROC_TYP_CD="
				+ RET_835_LN_PROC_TYP_CD + "]";
	}

}