package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;

//Model class representing each record retrieved by the COBLN-LINE-FLDS CURSOR in D5427HC1 DSM 
public class COBLN_LINE_FLDS {

	private int LN_ID; 
	
	private BigDecimal RPTG_LN_ALLW_AMT=BigDecimal.ZERO ; //DEfault Value for Null from the database is  0 , set in the DAO query
	private String LN_SRVC_CD  ; 
	private String LN_PMT_SVC_CD;
	private String LN_RMRK_CD   ; 
	private String LN_OVR_CD;
	private String LN_AUTH_PROC_CD; 
	
	private BigDecimal LN_CHRG_AMT =BigDecimal.ZERO; 
	private BigDecimal LN_NC_AMT=BigDecimal.ZERO; 
	private String LN_FST_DT;
	private String LN_LST_SRVC_DT;
	private BigDecimal LN_OI_PD_LN_AMT=BigDecimal.ZERO; 
	private BigDecimal LN_MEDC_L04_AMT=BigDecimal.ZERO; 
	
	private String LN_ALLW_AMT_DTRM_CD; 
	private BigDecimal LN_LN_PROV_WRITE_OFF=BigDecimal.ZERO; 
	private BigDecimal LN_MM_DED_AMT=BigDecimal.ZERO; 
	private BigDecimal LN_NYSCHG_DED_MM_AMT=BigDecimal.ZERO; 
	private int LN_ORIG_LN_CORR_ID   ;
	private BigDecimal LN_ORIG_LN_CHRG_AMT=BigDecimal.ZERO ;
	
	private boolean NYS_SERV_LINE ; // Corresponds to WS-NYS-SERV-LINE-SW variable in original program
	
	
	public int getLN_ID() {
		return LN_ID;
	}
	public void setLN_ID(int lN_ID) {
		LN_ID = lN_ID;
	}
	public BigDecimal getRPTG_LN_ALLW_AMT() {
		return RPTG_LN_ALLW_AMT;
	}
	public void setRPTG_LN_ALLW_AMT(BigDecimal rPTG_LN_ALLW_AMT) {
		RPTG_LN_ALLW_AMT = rPTG_LN_ALLW_AMT;
	}
	public String getLN_SRVC_CD() {
		return LN_SRVC_CD;
	}
	public void setLN_SRVC_CD(String lN_SRVC_CD) {
		LN_SRVC_CD = lN_SRVC_CD;
	}
	public String getLN_PMT_SVC_CD() {
		return LN_PMT_SVC_CD;
	}
	public void setLN_PMT_SVC_CD(String lN_PMT_SVC_CD) {
		LN_PMT_SVC_CD = lN_PMT_SVC_CD;
	}
	public String getLN_RMRK_CD() {
		return LN_RMRK_CD;
	}
	public void setLN_RMRK_CD(String lN_RMRK_CD) {
		LN_RMRK_CD = lN_RMRK_CD;
	}
	public String getLN_OVR_CD() {
		return LN_OVR_CD;
	}
	public void setLN_OVR_CD(String lN_OVR_CD) {
		LN_OVR_CD = lN_OVR_CD;
	}
	public String getLN_AUTH_PROC_CD() {
		return LN_AUTH_PROC_CD;
	}
	public void setLN_AUTH_PROC_CD(String lN_AUTH_PROC_CD) {
		LN_AUTH_PROC_CD = lN_AUTH_PROC_CD;
	}
	public BigDecimal getLN_CHRG_AMT() {
		return LN_CHRG_AMT;
	}
	public void setLN_CHRG_AMT(BigDecimal lN_CHRG_AMT) {
		LN_CHRG_AMT = lN_CHRG_AMT;
	}
	public BigDecimal getLN_NC_AMT() {
		return LN_NC_AMT;
	}
	public void setLN_NC_AMT(BigDecimal lN_NC_AMT) {
		LN_NC_AMT = lN_NC_AMT;
	}
	public String getLN_FST_DT() {
		return LN_FST_DT;
	}
	public void setLN_FST_DT(String lN_FST_DT) {
		LN_FST_DT = lN_FST_DT;
	}
	public String getLN_LST_SRVC_DT() {
		return LN_LST_SRVC_DT;
	}
	public void setLN_LST_SRVC_DT(String lN_LST_SRVC_DT) {
		LN_LST_SRVC_DT = lN_LST_SRVC_DT;
	}
	public BigDecimal getLN_OI_PD_LN_AMT() {
		return LN_OI_PD_LN_AMT;
	}
	public void setLN_OI_PD_LN_AMT(BigDecimal lN_OI_PD_LN_AMT) {
		LN_OI_PD_LN_AMT = lN_OI_PD_LN_AMT;
	}
	public BigDecimal getLN_MEDC_L04_AMT() {
		return LN_MEDC_L04_AMT;
	}
	public void setLN_MEDC_L04_AMT(BigDecimal lN_MEDC_L04_AMT) {
		LN_MEDC_L04_AMT = lN_MEDC_L04_AMT;
	}
	public String getLN_ALLW_AMT_DTRM_CD() {
		return LN_ALLW_AMT_DTRM_CD;
	}
	public void setLN_ALLW_AMT_DTRM_CD(String lN_ALLW_AMT_DTRM_CD) {
		LN_ALLW_AMT_DTRM_CD = lN_ALLW_AMT_DTRM_CD;
	}
	public BigDecimal getLN_LN_PROV_WRITE_OFF() {
		return LN_LN_PROV_WRITE_OFF;
	}
	public void setLN_LN_PROV_WRITE_OFF(BigDecimal lN_LN_PROV_WRITE_OFF) {
		LN_LN_PROV_WRITE_OFF = lN_LN_PROV_WRITE_OFF;
	}
	public BigDecimal getLN_MM_DED_AMT() {
		return LN_MM_DED_AMT;
	}
	public void setLN_MM_DED_AMT(BigDecimal lN_MM_DED_AMT) {
		LN_MM_DED_AMT = lN_MM_DED_AMT;
	}
	public BigDecimal getLN_NYSCHG_DED_MM_AMT() {
		return LN_NYSCHG_DED_MM_AMT;
	}
	public void setLN_NYSCHG_DED_MM_AMT(BigDecimal lN_NYSCHG_DED_MM_AMT) {
		LN_NYSCHG_DED_MM_AMT = lN_NYSCHG_DED_MM_AMT;
	}
	public int getLN_ORIG_LN_CORR_ID() {
		return LN_ORIG_LN_CORR_ID;
	}
	public void setLN_ORIG_LN_CORR_ID(int lN_ORIG_LN_CORR_ID) {
		LN_ORIG_LN_CORR_ID = lN_ORIG_LN_CORR_ID;
	}
	public BigDecimal getLN_ORIG_LN_CHRG_AMT() {
		return LN_ORIG_LN_CHRG_AMT;
	}
	public void setLN_ORIG_LN_CHRG_AMT(BigDecimal lN_ORIG_LN_CHRG_AMT) {
		LN_ORIG_LN_CHRG_AMT = lN_ORIG_LN_CHRG_AMT;
	}
	public boolean isNYS_SERV_LINE() {
		return NYS_SERV_LINE;
	}
	public void setNYS_SERV_LINE(boolean nYS_SERV_LINE) {
		NYS_SERV_LINE = nYS_SERV_LINE;
	} 
	
	
}
