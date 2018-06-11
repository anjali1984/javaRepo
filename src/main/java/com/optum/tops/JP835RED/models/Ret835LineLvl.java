package com.optum.tops.JP835RED.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * :RET:-835-LINE-LVL  portion of cobol copybook,VY835RET
 * Model Class for the record returned by CURS-LN-PD-AMT-RDUC, a list of these objects is populated
 * by the 7705-GET-LNE-RARC  section.
 * @author ayadav38
 *
 */
public class Ret835LineLvl {
	
	private BigDecimal RET_835_LN_SVC_ID=BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY);
	private  Ret835LnRarcTbl[] ret835LnRarcTbl=new Ret835LnRarcTbl[3];
	
	private BigDecimal RET_835_20_LN_PD_AMT =BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY); 
	private BigDecimal RET_835_20LN_SVC_ID =BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY);  
	private BigDecimal RET_835_SUPL_PRI_PD_AMT=BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY);
	
	//getters and setters
	public Ret835LnRarcTbl[] getRet835LnRarcTbl() {
		return ret835LnRarcTbl;
	}
	public void setRet835LnRarcTbl(Ret835LnRarcTbl[] ret835LnRarcTbl) {
		this.ret835LnRarcTbl = ret835LnRarcTbl;
	}
	public BigDecimal getRET_835_LN_SVC_ID() {
		return RET_835_LN_SVC_ID;
	}
	public void setRET_835_LN_SVC_ID(BigDecimal rET_835_LN_SVC_ID) {
		RET_835_LN_SVC_ID = rET_835_LN_SVC_ID;
	}
	
	public BigDecimal getRET_835_20_LN_PD_AMT() {
		return RET_835_20_LN_PD_AMT;
	}
	public void setRET_835_20_LN_PD_AMT(BigDecimal rET_835_20_LN_PD_AMT) {
		RET_835_20_LN_PD_AMT = rET_835_20_LN_PD_AMT;
	}
	public BigDecimal getRET_835_20LN_SVC_ID() {
		return RET_835_20LN_SVC_ID;
	}
	public void setRET_835_20LN_SVC_ID(BigDecimal rET_835_20LN_SVC_ID) {
		RET_835_20LN_SVC_ID = rET_835_20LN_SVC_ID;
	}
	public BigDecimal getRET_835_SUPL_PRI_PD_AMT() {
		return RET_835_SUPL_PRI_PD_AMT;
	}
	public void setRET_835_SUPL_PRI_PD_AMT(BigDecimal rET_835_SUPL_PRI_PD_AMT) {
		RET_835_SUPL_PRI_PD_AMT = rET_835_SUPL_PRI_PD_AMT;
	}
	@Override
	public String toString() {
		return "Ret835LineLvl [RET_835_LN_SVC_ID=" + RET_835_LN_SVC_ID + ", \n ret835LnRarcTbl="
				+ Arrays.toString(ret835LnRarcTbl) + ", \n RET_835_20_LN_PD_AMT=" + RET_835_20_LN_PD_AMT
				+ ", \n RET_835_20LN_SVC_ID=" + RET_835_20LN_SVC_ID + ", \n RET_835_SUPL_PRI_PD_AMT="
				+ RET_835_SUPL_PRI_PD_AMT + "]";
	}

	
	
}