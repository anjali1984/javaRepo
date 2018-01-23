package com.optum.tops.JP835RED.models;

import java.math.BigDecimal;

/**
 * :RET:-835-LINE-LVL  portion of cobol copybook,VY835RET
 * Model Class for the record returned by CURS-LN-PD-AMT-RDUC, a list of these objects is populated
 * by the 7705-GET-LNE-RARC  section.
 * @author ayadav38
 *
 */
public class Ret835LineLvl {
	
	private BigDecimal RET_835_LN_SVC_ID=BigDecimal.ZERO;
	private  Ret835LnRarcTbl[] ret835LnRarcTbl=new Ret835LnRarcTbl[3];
	public Ret835LnRarcTbl[] getRet835LnRarcTbl() {
		return ret835LnRarcTbl;
	}
	public void setRet835LnRarcTbl(Ret835LnRarcTbl[] ret835LnRarcTbl) {
		this.ret835LnRarcTbl = ret835LnRarcTbl;
	}
	private BigDecimal RET_835_20_LN_PD_AMT =BigDecimal.ZERO; 
	private BigDecimal RET_835_20LN_SVC_ID =BigDecimal.ZERO;  
	private BigDecimal RET_835_SUPL_PRI_PD_AMT=BigDecimal.ZERO;
	
	//getters and setters
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

	
	
}