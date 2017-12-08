package com.optum.tops.J5427HC1.models;

import java.util.List;

//Class for keeping track of the extra indicator fields which decide what processing path will the claim go through in 
//Further processing 2000-Processing etc.. 
//Sits inside the claim object that's returned back. 
public class ClaimIndicatorValues{

	private String DBKE2_ICN_SUFX_CD ;
	private String DBKE2_ICN_SUFX_VERS_NBR ;
	private String DBKE2_COB_LOGC_CD ;
	private String DBKE2_835_COB_PROC_IND; 
	private String DBKE2_FACL_OR_PROF_CD ;
	private String DBKE2_ALLW_AMT_DTRM_CD ; 
	private String DBKE2_DIAG_B_NBR ; 
	private String DBKE2_SUFX_TOT_CHRG_AMT ; 
	private String DBKE2_EMC_IND ;
	private String NYSTATE_COB_CLAIM ; 
	private String NYSTATE_COB_CLAIM_PAIDTO ; // Can be paid to State ('S') or Provider ("P") 
	private String PENNY_PROC_INDICATOR  ; // Claim level field "Y" or "N" 
	private String OPS_HCFA_INDICATOR ; 
	private String CALL_OIMC_TBL_INDICATOR ; // "Y" or "N" , corresponds to WS-CALL-OIMC-TBL-SW 
	
	private List<ADJD_CLMSF_ORIGHDR_LINE> HC1_ADJD_CLMSF_ORIGHDR_DATAAREA ; //ADJD_CLMSF_ORIGHDR Lines for the claim IF ANY, Max SIZE can be 150  
	
	public void setDBKE2_ICN_SUFX_CD(String dBKE2_ICN_SUFX_CD) {
		DBKE2_ICN_SUFX_CD = dBKE2_ICN_SUFX_CD;
	}
	public void setDBKE2_ICN_SUFX_VERS_NBR(String dBKE2_ICN_SUFX_VERS_NBR) {
		DBKE2_ICN_SUFX_VERS_NBR = dBKE2_ICN_SUFX_VERS_NBR;
	}
	public void setDBKE2_COB_LOGC_CD(String dBKE2_COB_LOGC_CD) {
		DBKE2_COB_LOGC_CD = dBKE2_COB_LOGC_CD;
	}
	public void setDBKE2_835_COB_PROC_IND(String dBKE2_835_COB_PROC_IND) {
		DBKE2_835_COB_PROC_IND = dBKE2_835_COB_PROC_IND;
	}
	public void setDBKE2_FACL_OR_PROF_CD(String dBKE2_FACL_OR_PROF_CD) {
		DBKE2_FACL_OR_PROF_CD = dBKE2_FACL_OR_PROF_CD;
	}
	public void setDBKE2_ALLW_AMT_DTRM_CD(String dBKE2_ALLW_AMT_DTRM_CD) {
		DBKE2_ALLW_AMT_DTRM_CD = dBKE2_ALLW_AMT_DTRM_CD;
	}
	public void setDBKE2_DIAG_B_NBR(String dBKE2_DIAG_B_NBR) {
		DBKE2_DIAG_B_NBR = dBKE2_DIAG_B_NBR;
	}
	public void setDBKE2_SUFX_TOT_CHRG_AMT(String dBKE2_SUFX_TOT_CHRG_AMT) {
		DBKE2_SUFX_TOT_CHRG_AMT = dBKE2_SUFX_TOT_CHRG_AMT;
	}
	public void setDBKE2_EMC_IND(String dBKE2_EMC_IND) {
		DBKE2_EMC_IND = dBKE2_EMC_IND;
	}
	public String getDBKE2_ICN_SUFX_CD() {
		return DBKE2_ICN_SUFX_CD;
	}
	public String getDBKE2_ICN_SUFX_VERS_NBR() {
		return DBKE2_ICN_SUFX_VERS_NBR;
	}
	public String getDBKE2_COB_LOGC_CD() {
		return DBKE2_COB_LOGC_CD;
	}
	public String getDBKE2_835_COB_PROC_IND() {
		return DBKE2_835_COB_PROC_IND;
	}
	public String getDBKE2_FACL_OR_PROF_CD() {
		return DBKE2_FACL_OR_PROF_CD;
	}
	public String getDBKE2_ALLW_AMT_DTRM_CD() {
		return DBKE2_ALLW_AMT_DTRM_CD;
	}
	public String getDBKE2_DIAG_B_NBR() {
		return DBKE2_DIAG_B_NBR;
	}
	public String getDBKE2_SUFX_TOT_CHRG_AMT() {
		return DBKE2_SUFX_TOT_CHRG_AMT;
	}
	public String getDBKE2_EMC_IND() {
		return DBKE2_EMC_IND;
	}
	public String getNYSTATE_COB_CLAIM() {
		return NYSTATE_COB_CLAIM;
	}
	public void setNYSTATE_COB_CLAIM(String nYSTATE_COB_CLAIM) {
		NYSTATE_COB_CLAIM = nYSTATE_COB_CLAIM;
	}
	public String getNYSTATE_COB_CLAIM_PAIDTO() {
		return NYSTATE_COB_CLAIM_PAIDTO;
	}
	public void setNYSTATE_COB_CLAIM_PAIDTO(String nYSTATE_COB_CLAIM_PAIDTO) {
		NYSTATE_COB_CLAIM_PAIDTO = nYSTATE_COB_CLAIM_PAIDTO;
	}
	public String getPENNY_PROC_INDICATOR() {
		return PENNY_PROC_INDICATOR;
	}
	public void setPENNY_PROC_INDICATOR(String pENNY_PROC_INDICATOR) {
		PENNY_PROC_INDICATOR = pENNY_PROC_INDICATOR;
	}
	public String getOPS_HCFA_INDICATOR() {
		return OPS_HCFA_INDICATOR;
	}
	public void setOPS_HCFA_INDICATOR(String oPS_HCFA_INDICATOR) {
		OPS_HCFA_INDICATOR = oPS_HCFA_INDICATOR;
	}
	public List<ADJD_CLMSF_ORIGHDR_LINE> getHC1_ADJD_CLMSF_ORIGHDR_DATAAREA() {
		return HC1_ADJD_CLMSF_ORIGHDR_DATAAREA;
	}
	public void setHC1_ADJD_CLMSF_ORIGHDR_DATAAREA(List<ADJD_CLMSF_ORIGHDR_LINE> hC1_ADJD_CLMSF_ORIGHDR_DATAAREA) {
		HC1_ADJD_CLMSF_ORIGHDR_DATAAREA = hC1_ADJD_CLMSF_ORIGHDR_DATAAREA;
	}
	public String getCALL_OIMC_TBL_INDICATOR() {
		return CALL_OIMC_TBL_INDICATOR;
	}
	public void setCALL_OIMC_TBL_INDICATOR(String cALL_OIMC_TBL_INDICATOR) {
		CALL_OIMC_TBL_INDICATOR = cALL_OIMC_TBL_INDICATOR;
	}
	
}
