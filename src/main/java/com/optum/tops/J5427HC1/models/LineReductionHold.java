package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;

//Model class for each line of the Claim corresponds to WS_LINE_REDUCTION_HOLD_TBL in D5427HC1
//An array of Size 7 of this class, will be present as a field in the Claim's Indicator Object.

public class LineReductionHold {
	
	private BigDecimal CAT_ID = new BigDecimal(0) ;
	private BigDecimal SVC_LN_ID = new BigDecimal(0) ;
	private BigDecimal COB_PRIM_IMPAC = new BigDecimal(0);
	private BigDecimal PR_OVERC = new BigDecimal(0);
	private BigDecimal PR_DEDUC = new BigDecimal(0);
	private BigDecimal PR_COPAY = new BigDecimal(0);
	private BigDecimal PR_COINS = new BigDecimal(0);
	private BigDecimal PR_NTCOV = new BigDecimal(0);
	private BigDecimal PR_MEDC_EST_AMT = new BigDecimal(0);
	private BigDecimal PR_TOTAL= new BigDecimal(0);
	private BigDecimal PRV_NC_AMT= new BigDecimal(0);
	private BigDecimal PR_DENY_AMT= new BigDecimal(0); 
	
	private String LN_ALLW_AMT_IND = "" ; 
	private BigDecimal LN_RPT_ALLOW_AMT = new BigDecimal(0); 
	private BigDecimal LN_OI_PAID_AMT = new BigDecimal(0);
	private BigDecimal LN_MEDC_PAID_AMT = new BigDecimal(0);
	private BigDecimal LN_PRV_WRT_OFF = new BigDecimal(0);
	
	private int LN_ID ; 
	private String LINE_SRVC_CD ; 
	private String LINE_PMT_SVC_CD; 
	private String LINE_AUTH_PROC_CD; 
	private String LINE_FST_DT; 
	private String LINE_LST_SRVC_DT;
	
	private BigDecimal LINE_CHRG_AMT = new BigDecimal(0); 
	private BigDecimal LINE_NC_AMT = new BigDecimal(0);
	
	public BigDecimal getCAT_ID() {
		return CAT_ID;
	}
	public void setCAT_ID(BigDecimal cAT_ID) {
		CAT_ID = cAT_ID;
	}
	public BigDecimal getSVC_LN_ID() {
		return SVC_LN_ID;
	}
	public void setSVC_LN_ID(BigDecimal sVC_LN_ID) {
		SVC_LN_ID = sVC_LN_ID;
	}
	public BigDecimal getCOB_PRIM_IMPAC() {
		return COB_PRIM_IMPAC;
	}
	public void setCOB_PRIM_IMPAC(BigDecimal cOB_PRIM_IMPAC) {
		COB_PRIM_IMPAC = cOB_PRIM_IMPAC;
	}
	public BigDecimal getPR_OVERC() {
		return PR_OVERC;
	}
	public void setPR_OVERC(BigDecimal pR_OVERC) {
		PR_OVERC = pR_OVERC;
	}
	public BigDecimal getPR_DEDUC() {
		return PR_DEDUC;
	}
	public void setPR_DEDUC(BigDecimal pR_DEDUC) {
		PR_DEDUC = pR_DEDUC;
	}
	public BigDecimal getPR_COPAY() {
		return PR_COPAY;
	}
	public void setPR_COPAY(BigDecimal pR_COPAY) {
		PR_COPAY = pR_COPAY;
	}
	public BigDecimal getPR_COINS() {
		return PR_COINS;
	}
	public void setPR_COINS(BigDecimal pR_COINS) {
		PR_COINS = pR_COINS;
	}
	public BigDecimal getPR_NTCOV() {
		return PR_NTCOV;
	}
	public void setPR_NTCOV(BigDecimal pR_NTCOV) {
		PR_NTCOV = pR_NTCOV;
	}
	public BigDecimal getPR_MEDC_EST_AMT() {
		return PR_MEDC_EST_AMT;
	}
	public void setPR_MEDC_EST_AMT(BigDecimal pR_MEDC_EST_AMT) {
		PR_MEDC_EST_AMT = pR_MEDC_EST_AMT;
	}
	public BigDecimal getPR_TOTAL() {
		return PR_TOTAL;
	}
	public void setPR_TOTAL(BigDecimal pR_TOTAL) {
		PR_TOTAL = pR_TOTAL;
	}
	public BigDecimal getPRV_NC_AMT() {
		return PRV_NC_AMT;
	}
	public void setPRV_NC_AMT(BigDecimal pRV_NC_AMT) {
		PRV_NC_AMT = pRV_NC_AMT;
	}
	public BigDecimal getPR_DENY_AMT() {
		return PR_DENY_AMT;
	}
	public void setPR_DENY_AMT(BigDecimal pR_DENY_AMT) {
		PR_DENY_AMT = pR_DENY_AMT;
	}
	public String getLN_ALLW_AMT_IND() {
		return LN_ALLW_AMT_IND;
	}
	public void setLN_ALLW_AMT_IND(String lN_ALLW_AMT_IND) {
		LN_ALLW_AMT_IND = lN_ALLW_AMT_IND;
	}
	public BigDecimal getLN_RPT_ALLOW_AMT() {
		return LN_RPT_ALLOW_AMT;
	}
	public void setLN_RPT_ALLOW_AMT(BigDecimal lN_RPT_ALLOW_AMT) {
		LN_RPT_ALLOW_AMT = lN_RPT_ALLOW_AMT;
	}
	public BigDecimal getLN_OI_PAID_AMT() {
		return LN_OI_PAID_AMT;
	}
	public void setLN_OI_PAID_AMT(BigDecimal lN_OI_PAID_AMT) {
		LN_OI_PAID_AMT = lN_OI_PAID_AMT;
	}
	public BigDecimal getLN_MEDC_PAID_AMT() {
		return LN_MEDC_PAID_AMT;
	}
	public void setLN_MEDC_PAID_AMT(BigDecimal lN_MEDC_PAID_AMT) {
		LN_MEDC_PAID_AMT = lN_MEDC_PAID_AMT;
	}
	public BigDecimal getLN_PRV_WRT_OFF() {
		return LN_PRV_WRT_OFF;
	}
	public void setLN_PRV_WRT_OFF(BigDecimal lN_PRV_WRT_OFF) {
		LN_PRV_WRT_OFF = lN_PRV_WRT_OFF;
	}
	public int getLN_ID() {
		return LN_ID;
	}
	public void setLN_ID(int lN_ID) {
		LN_ID = lN_ID;
	}
	public String getLINE_SRVC_CD() {
		return LINE_SRVC_CD;
	}
	public void setLINE_SRVC_CD(String lINE_SRVC_CD) {
		LINE_SRVC_CD = lINE_SRVC_CD;
	}
	public String getLINE_PMT_SVC_CD() {
		return LINE_PMT_SVC_CD;
	}
	public void setLINE_PMT_SVC_CD(String lINE_PMT_SVC_CD) {
		LINE_PMT_SVC_CD = lINE_PMT_SVC_CD;
	}
	public String getLINE_AUTH_PROC_CD() {
		return LINE_AUTH_PROC_CD;
	}
	public void setLINE_AUTH_PROC_CD(String lINE_AUTH_PROC_CD) {
		LINE_AUTH_PROC_CD = lINE_AUTH_PROC_CD;
	}
	public String getLINE_FST_DT() {
		return LINE_FST_DT;
	}
	public void setLINE_FST_DT(String lINE_FST_DT) {
		LINE_FST_DT = lINE_FST_DT;
	}
	public String getLINE_LST_SRVC_DT() {
		return LINE_LST_SRVC_DT;
	}
	public void setLINE_LST_SRVC_DT(String lINE_LST_SRVC_DT) {
		LINE_LST_SRVC_DT = lINE_LST_SRVC_DT;
	}
	public BigDecimal getLINE_CHRG_AMT() {
		return LINE_CHRG_AMT;
	}
	public void setLINE_CHRG_AMT(BigDecimal lINE_CHRG_AMT) {
		LINE_CHRG_AMT = lINE_CHRG_AMT;
	}
	public BigDecimal getLINE_NC_AMT() {
		return LINE_NC_AMT;
	}
	public void setLINE_NC_AMT(BigDecimal lINE_NC_AMT) {
		LINE_NC_AMT = lINE_NC_AMT;
	} 
	
	
	
	
}
