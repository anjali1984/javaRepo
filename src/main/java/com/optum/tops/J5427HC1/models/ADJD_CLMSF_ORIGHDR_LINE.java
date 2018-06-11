package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

//A list of these lines is present in the ClaimIndicatorValues object
public class ADJD_CLMSF_ORIGHDR_LINE {
	
	private String RVNU_CD ; 
	private String PROC_CD ; 
	private BigDecimal CHRG_AMT; 
	private int LN_CORR_ID; 
	private int LN_NBR ; 
	private String ORIG_PL_OF_SRVC_CD ; 
	private String UB92_RVNU_CD ; 
	private BigDecimal UB92_CHRG_AMT=BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY); 
	private BigDecimal UB92_NOT_COV_AMT=BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY); 
	private String  UB92_PROC_CD  ;
	private String HCFA_SRVC_PLC_CD ; 
	private String UB92_OVR_CD ; 
	private String UB92_RMK_CD;
	private String ORIG_UB92_RMRK_CD ;
	private String REV_LINE_PENNY_INDICATOR ; 
	 
	
	public String getRVNU_CD() {
		return RVNU_CD;
	}
	public void setRVNU_CD(String rVNU_CD) {
		RVNU_CD = rVNU_CD;
	}
	public String getPROC_CD() {
		return PROC_CD;
	}
	public void setPROC_CD(String pROC_CD) {
		PROC_CD = pROC_CD;
	}
	public BigDecimal getCHRG_AMT() {
		return CHRG_AMT;
	}
	public void setCHRG_AMT(BigDecimal cHRG_AMT) {
		CHRG_AMT = cHRG_AMT;
	}
	public int getLN_CORR_ID() {
		return LN_CORR_ID;
	}
	public void setLN_CORR_ID(int lN_CORR_ID) {
		LN_CORR_ID = lN_CORR_ID;
	}
	public int getLN_NBR() {
		return LN_NBR;
	}
	public void setLN_NBR(int lN_NBR) {
		LN_NBR = lN_NBR;
	}
	public String getORIG_PL_OF_SRVC_CD() {
		return ORIG_PL_OF_SRVC_CD;
	}
	public void setORIG_PL_OF_SRVC_CD(String oRIG_PL_OF_SRVC_CD) {
		ORIG_PL_OF_SRVC_CD = oRIG_PL_OF_SRVC_CD;
	}
	public String getUB92_RVNU_CD() {
		return UB92_RVNU_CD;
	}
	public void setUB92_RVNU_CD(String uB92_RVNU_CD) {
		UB92_RVNU_CD = uB92_RVNU_CD;
	}
	public BigDecimal getUB92_CHRG_AMT() {
		return UB92_CHRG_AMT;
	}
	public void setUB92_CHRG_AMT(BigDecimal uB92_CHRG_AMT) {
		UB92_CHRG_AMT = uB92_CHRG_AMT;
	}
	public BigDecimal getUB92_NOT_COV_AMT() {
		return UB92_NOT_COV_AMT;
	}
	public void setUB92_NOT_COV_AMT(BigDecimal uB92_NOT_COV_AMT) {
		UB92_NOT_COV_AMT = uB92_NOT_COV_AMT;
	}
	public String getUB92_PROC_CD() {
		return UB92_PROC_CD;
	}
	public void setUB92_PROC_CD(String uB92_PROC_CD) {
		UB92_PROC_CD = uB92_PROC_CD;
	}
	public String getHCFA_SRVC_PLC_CD() {
		return HCFA_SRVC_PLC_CD;
	}
	public void setHCFA_SRVC_PLC_CD(String hCFA_SRVC_PLC_CD) {
		HCFA_SRVC_PLC_CD = hCFA_SRVC_PLC_CD;
	}
	public String getUB92_OVR_CD() {
		return UB92_OVR_CD;
	}
	public void setUB92_OVR_CD(String uB92_OVR_CD) {
		UB92_OVR_CD = uB92_OVR_CD;
	}
	public String getUB92_RMK_CD() {
		return UB92_RMK_CD;
	}
	public void setUB92_RMK_CD(String uB92_RMK_CD) {
		UB92_RMK_CD = uB92_RMK_CD;
	}
	public String getORIG_UB92_RMRK_CD() {
		return ORIG_UB92_RMRK_CD;
	}
	public void setORIG_UB92_RMRK_CD(String oRIG_UB92_RMRK_CD) {
		ORIG_UB92_RMRK_CD = oRIG_UB92_RMRK_CD;
	}
	public String getREV_LINE_PENNY_INDICATOR() {
		return REV_LINE_PENNY_INDICATOR;
	}
	public void setREV_LINE_PENNY_INDICATOR(String rEV_LINE_PENNY_INDICATOR) {
		REV_LINE_PENNY_INDICATOR = rEV_LINE_PENNY_INDICATOR;
	}
	@Override
	public String toString() {
		return "ADJD_CLMSF_ORIGHDR_LINE [RVNU_CD=" + RVNU_CD + ", \n PROC_CD=" + PROC_CD + ", \n CHRG_AMT=" + CHRG_AMT
				+ ", \n LN_CORR_ID=" + LN_CORR_ID + ", \n LN_NBR=" + LN_NBR + ", \n ORIG_PL_OF_SRVC_CD=" + ORIG_PL_OF_SRVC_CD
				+ ", \n UB92_RVNU_CD=" + UB92_RVNU_CD + ", \n\n UB92_CHRG_AMT=" + UB92_CHRG_AMT + ", \n UB92_NOT_COV_AMT="
				+ UB92_NOT_COV_AMT + ", \n UB92_PROC_CD=" + UB92_PROC_CD + ", \n HCFA_SRVC_PLC_CD=" + HCFA_SRVC_PLC_CD
				+ ", \n UB92_OVR_CD=" + UB92_OVR_CD + ", \n UB92_RMK_CD=" + UB92_RMK_CD + ", \n ORIG_UB92_RMRK_CD="
				+ ORIG_UB92_RMRK_CD + ", \n REV_LINE_PENNY_INDICATOR=" + REV_LINE_PENNY_INDICATOR + "]";
	}
	
	
	
}
