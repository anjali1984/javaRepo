package com.optum.tops.JP835RED.models;

import java.math.BigDecimal;

public class Ub92_835AdjdSvc {
					/*
					 * 10 :RET:_UB92_835_ADJD_SVC_INFO OCCURS 60 TIMES.
				25210C             15 :RET:_UB92_835_ADJD_PAID_AMT
				25210C                                            PIC S9(14)V99 COMP_3.
				25210C             15 :RET:_UB92_835_ADJD_REV_CD
				25210C                                            PIC S9(05) COMP_3.
				25210C             15 :RET:_UB92_835_ADJD_PROC_CD
				25210C                                            PIC X(05).
				25210C             15 :RET:_UB92_835_ADJD_PROC_MOD PIC X(2)
				25210C                                               OCCURS 4 TIMES.
				QC4672             15 :RET:_ORIG_HDR_SEQ_NBR      PIC S9(03) COMP_3.
				25210A             15 :RET:_UB92_835_CHRG    PIC S9(09)V99 COMP_3.
				25210A             15 :RET:_UB92_835_COINS   PIC S9(09)V99 COMP_3.
				25210A             15 :RET:_UB92_835_DED     PIC S9(09)V99 COMP_3.
				25210A             15 :RET:_UB92_835_COPAY   PIC S9(09)V99 COMP_3.
				25210A             15 :RET:_UB92_835_PW      PIC S9(09)V99 COMP_3.
				25210A             15 :RET:_UB92_835_NC      PIC S9(09)V99 COMP_3.
				25210B             15 :RET:_UB92_835_BL      PIC S9(09)V99 COMP_3.
				QC0819             15 :RET:_UB92_835_PD_TO_EMP PIC S9(09)V99 COMP_3.
				QC0819             15 :RET:_UB92_835_PNLTY_AMT PIC S9(09)V99 COMP_3.
				QC0819             15 :RET:_UB92_835_SANC_AMT  PIC S9(09)V99 COMP_3.
				QC0819             15 :RET:_UB92_835_CAP_AMT   PIC S9(09)V99 COMP_3.
				25210C
				111553             15 :RET:_ORIG_HDR_PROC_TYPE PIC X(02).
				111553             15 :RET:_ORIG_HDR_LINE_CORR_ID PIC S9(03) COMP_3.
				111553             15 :RET:_UB92_RVNU_CD       PIC S9(05)    COMP_3.
				111553             15 :RET:_UB92_CHRG_AMT      PIC S9(07)V99 COMP_3.
				111553             15 :RET:_UB92_ALLW_AMT      PIC S9(09)V99 COMP_3.
				61812A             15 :RET:_UB92_835_BL_AMT    PIC S9(09)V99 COMP_3.
				61812B             15 :RET:_UB92_835_CO_OTHER  PIC S9(09)V99 COMP_3.
				61812B             15 :RET:_UB92_BLNCE_REV_LVL PIC X(01).
				A61812             15 :RET:_UB92_835_BAL_NYSPD PIC S9(09)V99 COMP_3.
					 */
	private BigDecimal UB92_835_ADJD_PAID_AMT = BigDecimal.ZERO;
	private String UB92_835_ADJD_REV_CD ; 
	private String UB92_835_ADJD_PROC_CD ; 
	private String UB92_835_ADJD_PROC_MOD_1_CD ; 
	private String UB92_835_ADJD_PROC_MOD_2_CD ; 
	private String UB92_835_ADJD_PROC_MOD_3_CD ; 
	private String UB92_835_ADJD_PROC_MOD_4_CD ; 
	private int ORIG_HDR_SEQ_NBR; 
	private BigDecimal UB92_835_CHRG= BigDecimal.ZERO;
	private BigDecimal UB92_835_COINS= BigDecimal.ZERO;
	private BigDecimal UB92_835_DED= BigDecimal.ZERO;
	private BigDecimal UB92_835_COPAY= BigDecimal.ZERO;
	private BigDecimal UB92_835_PW= BigDecimal.ZERO;
	private BigDecimal UB92_835_NC= BigDecimal.ZERO;
	private BigDecimal UB92_835_BL= BigDecimal.ZERO;
	private BigDecimal UB92_835_PD_TO_EMP= BigDecimal.ZERO;
	private BigDecimal UB92_835_PNLTY_AMT= BigDecimal.ZERO;
	private BigDecimal UB92_835_SANC_AMT= BigDecimal.ZERO;
	private BigDecimal UB92_835_CAP_AMT= BigDecimal.ZERO;
	private int ORIG_HDR_LINE_CORR_ID;
	private String UB92_RVNU_CD;
	private BigDecimal UB92_CHRG_AMT= BigDecimal.ZERO;
	private BigDecimal UB92_ALLW_AMT= BigDecimal.ZERO;
	private BigDecimal UB92_835_BL_AMT= BigDecimal.ZERO;
	private BigDecimal UB92_835_CO_OTHER= BigDecimal.ZERO;
	private BigDecimal UB92_835_BAL_NYSPD= BigDecimal.ZERO;
	private String ORIG_HDR_PROC_TYPE;
	private String UB92_BLNCE_REV_LVL;
	
	public BigDecimal getUB92_835_ADJD_PAID_AMT() {
		return UB92_835_ADJD_PAID_AMT;
	}
	public void setUB92_835_ADJD_PAID_AMT(BigDecimal uB92_835_ADJD_PAID_AMT) {
		UB92_835_ADJD_PAID_AMT = uB92_835_ADJD_PAID_AMT;
	}
	public String getUB92_835_ADJD_REV_CD() {
		return UB92_835_ADJD_REV_CD;
	}
	public void setUB92_835_ADJD_REV_CD(String uB92_835_ADJD_REV_CD) {
		UB92_835_ADJD_REV_CD = uB92_835_ADJD_REV_CD;
	}
	public String getUB92_835_ADJD_PROC_CD() {
		return UB92_835_ADJD_PROC_CD;
	}
	public void setUB92_835_ADJD_PROC_CD(String uB92_835_ADJD_PROC_CD) {
		UB92_835_ADJD_PROC_CD = uB92_835_ADJD_PROC_CD;
	}
	public String getUB92_835_ADJD_PROC_MOD_1_CD() {
		return UB92_835_ADJD_PROC_MOD_1_CD;
	}
	public void setUB92_835_ADJD_PROC_MOD_1_CD(String uB92_835_ADJD_PROC_MOD_1_CD) {
		UB92_835_ADJD_PROC_MOD_1_CD = uB92_835_ADJD_PROC_MOD_1_CD;
	}
	public String getUB92_835_ADJD_PROC_MOD_2_CD() {
		return UB92_835_ADJD_PROC_MOD_2_CD;
	}
	public void setUB92_835_ADJD_PROC_MOD_2_CD(String uB92_835_ADJD_PROC_MOD_2_CD) {
		UB92_835_ADJD_PROC_MOD_2_CD = uB92_835_ADJD_PROC_MOD_2_CD;
	}
	public String getUB92_835_ADJD_PROC_MOD_3_CD() {
		return UB92_835_ADJD_PROC_MOD_3_CD;
	}
	public void setUB92_835_ADJD_PROC_MOD_3_CD(String uB92_835_ADJD_PROC_MOD_3_CD) {
		UB92_835_ADJD_PROC_MOD_3_CD = uB92_835_ADJD_PROC_MOD_3_CD;
	}
	public String getUB92_835_ADJD_PROC_MOD_4_CD() {
		return UB92_835_ADJD_PROC_MOD_4_CD;
	}
	public void setUB92_835_ADJD_PROC_MOD_4_CD(String uB92_835_ADJD_PROC_MOD_4_CD) {
		UB92_835_ADJD_PROC_MOD_4_CD = uB92_835_ADJD_PROC_MOD_4_CD;
	}
	public int getORIG_HDR_SEQ_NBR() {
		return ORIG_HDR_SEQ_NBR;
	}
	public void setORIG_HDR_SEQ_NBR(int oRIG_HDR_SEQ_NBR) {
		ORIG_HDR_SEQ_NBR = oRIG_HDR_SEQ_NBR;
	}
	public BigDecimal getUB92_835_CHRG() {
		return UB92_835_CHRG;
	}
	public void setUB92_835_CHRG(BigDecimal uB92_835_CHRG) {
		UB92_835_CHRG = uB92_835_CHRG;
	}
	public BigDecimal getUB92_835_COINS() {
		return UB92_835_COINS;
	}
	public void setUB92_835_COINS(BigDecimal uB92_835_COINS) {
		UB92_835_COINS = uB92_835_COINS;
	}
	public BigDecimal getUB92_835_DED() {
		return UB92_835_DED;
	}
	public void setUB92_835_DED(BigDecimal uB92_835_DED) {
		UB92_835_DED = uB92_835_DED;
	}
	public BigDecimal getUB92_835_COPAY() {
		return UB92_835_COPAY;
	}
	public void setUB92_835_COPAY(BigDecimal uB92_835_COPAY) {
		UB92_835_COPAY = uB92_835_COPAY;
	}
	public BigDecimal getUB92_835_PW() {
		return UB92_835_PW;
	}
	public void setUB92_835_PW(BigDecimal uB92_835_PW) {
		UB92_835_PW = uB92_835_PW;
	}
	public BigDecimal getUB92_835_NC() {
		return UB92_835_NC;
	}
	public void setUB92_835_NC(BigDecimal uB92_835_NC) {
		UB92_835_NC = uB92_835_NC;
	}
	public BigDecimal getUB92_835_BL() {
		return UB92_835_BL;
	}
	public void setUB92_835_BL(BigDecimal uB92_835_BL) {
		UB92_835_BL = uB92_835_BL;
	}
	public BigDecimal getUB92_835_PD_TO_EMP() {
		return UB92_835_PD_TO_EMP;
	}
	public void setUB92_835_PD_TO_EMP(BigDecimal uB92_835_PD_TO_EMP) {
		UB92_835_PD_TO_EMP = uB92_835_PD_TO_EMP;
	}
	public BigDecimal getUB92_835_PNLTY_AMT() {
		return UB92_835_PNLTY_AMT;
	}
	public void setUB92_835_PNLTY_AMT(BigDecimal uB92_835_PNLTY_AMT) {
		UB92_835_PNLTY_AMT = uB92_835_PNLTY_AMT;
	}
	public BigDecimal getUB92_835_SANC_AMT() {
		return UB92_835_SANC_AMT;
	}
	public void setUB92_835_SANC_AMT(BigDecimal uB92_835_SANC_AMT) {
		UB92_835_SANC_AMT = uB92_835_SANC_AMT;
	}
	public BigDecimal getUB92_835_CAP_AMT() {
		return UB92_835_CAP_AMT;
	}
	public void setUB92_835_CAP_AMT(BigDecimal uB92_835_CAP_AMT) {
		UB92_835_CAP_AMT = uB92_835_CAP_AMT;
	}
	public int getORIG_HDR_LINE_CORR_ID() {
		return ORIG_HDR_LINE_CORR_ID;
	}
	public void setORIG_HDR_LINE_CORR_ID(int oRIG_HDR_LINE_CORR_ID) {
		ORIG_HDR_LINE_CORR_ID = oRIG_HDR_LINE_CORR_ID;
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
	public BigDecimal getUB92_ALLW_AMT() {
		return UB92_ALLW_AMT;
	}
	public void setUB92_ALLW_AMT(BigDecimal uB92_ALLW_AMT) {
		UB92_ALLW_AMT = uB92_ALLW_AMT;
	}
	public BigDecimal getUB92_835_BL_AMT() {
		return UB92_835_BL_AMT;
	}
	public void setUB92_835_BL_AMT(BigDecimal uB92_835_BL_AMT) {
		UB92_835_BL_AMT = uB92_835_BL_AMT;
	}
	public BigDecimal getUB92_835_CO_OTHER() {
		return UB92_835_CO_OTHER;
	}
	public void setUB92_835_CO_OTHER(BigDecimal uB92_835_CO_OTHER) {
		UB92_835_CO_OTHER = uB92_835_CO_OTHER;
	}
	public BigDecimal getUB92_835_BAL_NYSPD() {
		return UB92_835_BAL_NYSPD;
	}
	public void setUB92_835_BAL_NYSPD(BigDecimal uB92_835_BAL_NYSPD) {
		UB92_835_BAL_NYSPD = uB92_835_BAL_NYSPD;
	}
	public String getORIG_HDR_PROC_TYPE() {
		return ORIG_HDR_PROC_TYPE;
	}
	public void setORIG_HDR_PROC_TYPE(String oRIG_HDR_PROC_TYPE) {
		ORIG_HDR_PROC_TYPE = oRIG_HDR_PROC_TYPE;
	}
	public String getUB92_BLNCE_REV_LVL() {
		return UB92_BLNCE_REV_LVL;
	}
	public void setUB92_BLNCE_REV_LVL(String uB92_BLNCE_REV_LVL) {
		UB92_BLNCE_REV_LVL = uB92_BLNCE_REV_LVL;
	}
	@Override
	public String toString() {
		return "Ub92_835AdjdSvc [UB92_835_ADJD_PAID_AMT=" + UB92_835_ADJD_PAID_AMT + ", \n UB92_835_ADJD_REV_CD="
				+ UB92_835_ADJD_REV_CD + ", \n UB92_835_ADJD_PROC_CD=" + UB92_835_ADJD_PROC_CD
				+ ", \n UB92_835_ADJD_PROC_MOD_1_CD=" + UB92_835_ADJD_PROC_MOD_1_CD + ", \n UB92_835_ADJD_PROC_MOD_2_CD="
				+ UB92_835_ADJD_PROC_MOD_2_CD + ", \n UB92_835_ADJD_PROC_MOD_3_CD=" + UB92_835_ADJD_PROC_MOD_3_CD
				+ ", \n UB92_835_ADJD_PROC_MOD_4_CD=" + UB92_835_ADJD_PROC_MOD_4_CD + ", \n ORIG_HDR_SEQ_NBR="
				+ ORIG_HDR_SEQ_NBR + ", \n UB92_835_CHRG=" + UB92_835_CHRG + ", \n UB92_835_COINS=" + UB92_835_COINS
				+ ", \n UB92_835_DED=" + UB92_835_DED + ", \n UB92_835_COPAY=" + UB92_835_COPAY + ", \n UB92_835_PW="
				+ UB92_835_PW + ", \n UB92_835_NC=" + UB92_835_NC + ", \n UB92_835_BL=" + UB92_835_BL
				+ ", \n UB92_835_PD_TO_EMP=" + UB92_835_PD_TO_EMP + ", \n UB92_835_PNLTY_AMT=" + UB92_835_PNLTY_AMT
				+ ", \n UB92_835_SANC_AMT=" + UB92_835_SANC_AMT + ", \n UB92_835_CAP_AMT=" + UB92_835_CAP_AMT
				+ ", \n ORIG_HDR_LINE_CORR_ID=" + ORIG_HDR_LINE_CORR_ID + ", \n UB92_RVNU_CD=" + UB92_RVNU_CD
				+ ", \n UB92_CHRG_AMT=" + UB92_CHRG_AMT + ", \n UB92_ALLW_AMT=" + UB92_ALLW_AMT + ", \n UB92_835_BL_AMT="
				+ UB92_835_BL_AMT + ", \n UB92_835_CO_OTHER=" + UB92_835_CO_OTHER + ", \n UB92_835_BAL_NYSPD="
				+ UB92_835_BAL_NYSPD + ", \n ORIG_HDR_PROC_TYPE=" + ORIG_HDR_PROC_TYPE + ", \n UB92_BLNCE_REV_LVL="
				+ UB92_BLNCE_REV_LVL + "]";
	}
	
	
	
	
	
}
