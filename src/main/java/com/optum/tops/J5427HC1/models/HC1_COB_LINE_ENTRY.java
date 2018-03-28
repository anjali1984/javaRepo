package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;

public class HC1_COB_LINE_ENTRY {
	
	private String HC1_COB_LN_SRV_ID;             
	private String HC1_COB_LN_ALLOW_AMT_IND;      
	private BigDecimal HC1_COB_LN_835_RPT_ALLOW_AMT=BigDecimal.ZERO;  
	private BigDecimal HC1_COB_LN_EOB_OI_PAID_AMT=BigDecimal.ZERO;    
	private BigDecimal HC1_COB_LN_EOB_MEDC_PAID_AMT=BigDecimal.ZERO;  
	private BigDecimal HC1_COB_LN_COB_PRV_WRT_OFF=BigDecimal.ZERO;    
	private BigDecimal HC1_COB_LN_835_COB_PRIM_IMPAC=BigDecimal.ZERO; 
	private BigDecimal HC1_COB_LN_835_PAT_RESP_OVERC=BigDecimal.ZERO; 
	private BigDecimal HC1_COB_LN_835_PAT_RESP_DEDUC=BigDecimal.ZERO; 
	private BigDecimal HC1_COB_LN_835_PAT_RESP_COPAY=BigDecimal.ZERO; 
	private BigDecimal HC1_COB_LN_835_PAT_RESP_COINS=BigDecimal.ZERO; 
	private BigDecimal HC1_COB_LN_835_PAT_RESP_NTCOV=BigDecimal.ZERO; 
	private BigDecimal HC1_COB_LN_835_MEDC_EST_AMT=BigDecimal.ZERO;   
	private BigDecimal HC1_COB_LN_835_PAT_RESP_TOTAL=BigDecimal.ZERO; 
	private BigDecimal HC1_COB_LN_835_PRV_NC_AMT=BigDecimal.ZERO;     
	private BigDecimal HC1_COB_LN_835_DENY_NC_AMT=BigDecimal.ZERO;    
	
	private int HC1_COB_LINE_LN_ID;       
	private String HC1_COB_LINE_SRVC_CD;     
	private String HC1_COB_LINE_PMT_SVC_CD;  
	private String HC1_COB_LINE_AUTH_PROC_CD;
	private String HC1_COB_LINE_FST_DT;      
	private String HC1_COB_LINE_LST_SRVC_DT; 
	private BigDecimal HC1_COB_LINE_CHRG_AMT=BigDecimal.ZERO;
	
	public String getHC1_COB_LN_SRV_ID() {
		return HC1_COB_LN_SRV_ID;
	}
	public void setHC1_COB_LN_SRV_ID(String hC1_COB_LN_SRV_ID) {
		HC1_COB_LN_SRV_ID = hC1_COB_LN_SRV_ID;
	}
	public String getHC1_COB_LN_ALLOW_AMT_IND() {
		return HC1_COB_LN_ALLOW_AMT_IND;
	}
	public void setHC1_COB_LN_ALLOW_AMT_IND(String hC1_COB_LN_ALLOW_AMT_IND) {
		HC1_COB_LN_ALLOW_AMT_IND = hC1_COB_LN_ALLOW_AMT_IND;
	}
	public BigDecimal getHC1_COB_LN_835_RPT_ALLOW_AMT() {
		return HC1_COB_LN_835_RPT_ALLOW_AMT;
	}
	public void setHC1_COB_LN_835_RPT_ALLOW_AMT(BigDecimal hC1_COB_LN_835_RPT_ALLOW_AMT) {
		HC1_COB_LN_835_RPT_ALLOW_AMT = hC1_COB_LN_835_RPT_ALLOW_AMT;
	}
	public BigDecimal getHC1_COB_LN_EOB_OI_PAID_AMT() {
		return HC1_COB_LN_EOB_OI_PAID_AMT;
	}
	public void setHC1_COB_LN_EOB_OI_PAID_AMT(BigDecimal hC1_COB_LN_EOB_OI_PAID_AMT) {
		HC1_COB_LN_EOB_OI_PAID_AMT = hC1_COB_LN_EOB_OI_PAID_AMT;
	}
	public BigDecimal getHC1_COB_LN_EOB_MEDC_PAID_AMT() {
		return HC1_COB_LN_EOB_MEDC_PAID_AMT;
	}
	public void setHC1_COB_LN_EOB_MEDC_PAID_AMT(BigDecimal hC1_COB_LN_EOB_MEDC_PAID_AMT) {
		HC1_COB_LN_EOB_MEDC_PAID_AMT = hC1_COB_LN_EOB_MEDC_PAID_AMT;
	}
	public BigDecimal getHC1_COB_LN_COB_PRV_WRT_OFF() {
		return HC1_COB_LN_COB_PRV_WRT_OFF;
	}
	public void setHC1_COB_LN_COB_PRV_WRT_OFF(BigDecimal hC1_COB_LN_COB_PRV_WRT_OFF) {
		HC1_COB_LN_COB_PRV_WRT_OFF = hC1_COB_LN_COB_PRV_WRT_OFF;
	}
	public BigDecimal getHC1_COB_LN_835_COB_PRIM_IMPAC() {
		return HC1_COB_LN_835_COB_PRIM_IMPAC;
	}
	public void setHC1_COB_LN_835_COB_PRIM_IMPAC(BigDecimal hC1_COB_LN_835_COB_PRIM_IMPAC) {
		HC1_COB_LN_835_COB_PRIM_IMPAC = hC1_COB_LN_835_COB_PRIM_IMPAC;
	}
	public BigDecimal getHC1_COB_LN_835_PAT_RESP_OVERC() {
		return HC1_COB_LN_835_PAT_RESP_OVERC;
	}
	public void setHC1_COB_LN_835_PAT_RESP_OVERC(BigDecimal hC1_COB_LN_835_PAT_RESP_OVERC) {
		HC1_COB_LN_835_PAT_RESP_OVERC = hC1_COB_LN_835_PAT_RESP_OVERC;
	}
	public BigDecimal getHC1_COB_LN_835_PAT_RESP_DEDUC() {
		return HC1_COB_LN_835_PAT_RESP_DEDUC;
	}
	public void setHC1_COB_LN_835_PAT_RESP_DEDUC(BigDecimal hC1_COB_LN_835_PAT_RESP_DEDUC) {
		HC1_COB_LN_835_PAT_RESP_DEDUC = hC1_COB_LN_835_PAT_RESP_DEDUC;
	}
	public BigDecimal getHC1_COB_LN_835_PAT_RESP_COPAY() {
		return HC1_COB_LN_835_PAT_RESP_COPAY;
	}
	public void setHC1_COB_LN_835_PAT_RESP_COPAY(BigDecimal hC1_COB_LN_835_PAT_RESP_COPAY) {
		HC1_COB_LN_835_PAT_RESP_COPAY = hC1_COB_LN_835_PAT_RESP_COPAY;
	}
	public BigDecimal getHC1_COB_LN_835_PAT_RESP_COINS() {
		return HC1_COB_LN_835_PAT_RESP_COINS;
	}
	public void setHC1_COB_LN_835_PAT_RESP_COINS(BigDecimal hC1_COB_LN_835_PAT_RESP_COINS) {
		HC1_COB_LN_835_PAT_RESP_COINS = hC1_COB_LN_835_PAT_RESP_COINS;
	}
	public BigDecimal getHC1_COB_LN_835_PAT_RESP_NTCOV() {
		return HC1_COB_LN_835_PAT_RESP_NTCOV;
	}
	public void setHC1_COB_LN_835_PAT_RESP_NTCOV(BigDecimal hC1_COB_LN_835_PAT_RESP_NTCOV) {
		HC1_COB_LN_835_PAT_RESP_NTCOV = hC1_COB_LN_835_PAT_RESP_NTCOV;
	}
	public BigDecimal getHC1_COB_LN_835_MEDC_EST_AMT() {
		return HC1_COB_LN_835_MEDC_EST_AMT;
	}
	public void setHC1_COB_LN_835_MEDC_EST_AMT(BigDecimal hC1_COB_LN_835_MEDC_EST_AMT) {
		HC1_COB_LN_835_MEDC_EST_AMT = hC1_COB_LN_835_MEDC_EST_AMT;
	}
	public BigDecimal getHC1_COB_LN_835_PAT_RESP_TOTAL() {
		return HC1_COB_LN_835_PAT_RESP_TOTAL;
	}
	public void setHC1_COB_LN_835_PAT_RESP_TOTAL(BigDecimal hC1_COB_LN_835_PAT_RESP_TOTAL) {
		HC1_COB_LN_835_PAT_RESP_TOTAL = hC1_COB_LN_835_PAT_RESP_TOTAL;
	}
	public BigDecimal getHC1_COB_LN_835_PRV_NC_AMT() {
		return HC1_COB_LN_835_PRV_NC_AMT;
	}
	public void setHC1_COB_LN_835_PRV_NC_AMT(BigDecimal hC1_COB_LN_835_PRV_NC_AMT) {
		HC1_COB_LN_835_PRV_NC_AMT = hC1_COB_LN_835_PRV_NC_AMT;
	}
	public BigDecimal getHC1_COB_LN_835_DENY_NC_AMT() {
		return HC1_COB_LN_835_DENY_NC_AMT;
	}
	public void setHC1_COB_LN_835_DENY_NC_AMT(BigDecimal hC1_COB_LN_835_DENY_NC_AMT) {
		HC1_COB_LN_835_DENY_NC_AMT = hC1_COB_LN_835_DENY_NC_AMT;
	}
	public int getHC1_COB_LINE_LN_ID() {
		return HC1_COB_LINE_LN_ID;
	}
	public void setHC1_COB_LINE_LN_ID(int hC1_COB_LINE_LN_ID) {
		HC1_COB_LINE_LN_ID = hC1_COB_LINE_LN_ID;
	}
	public String getHC1_COB_LINE_SRVC_CD() {
		return HC1_COB_LINE_SRVC_CD;
	}
	public void setHC1_COB_LINE_SRVC_CD(String hC1_COB_LINE_SRVC_CD) {
		HC1_COB_LINE_SRVC_CD = hC1_COB_LINE_SRVC_CD;
	}
	public String getHC1_COB_LINE_PMT_SVC_CD() {
		return HC1_COB_LINE_PMT_SVC_CD;
	}
	public void setHC1_COB_LINE_PMT_SVC_CD(String hC1_COB_LINE_PMT_SVC_CD) {
		HC1_COB_LINE_PMT_SVC_CD = hC1_COB_LINE_PMT_SVC_CD;
	}
	public String getHC1_COB_LINE_AUTH_PROC_CD() {
		return HC1_COB_LINE_AUTH_PROC_CD;
	}
	public void setHC1_COB_LINE_AUTH_PROC_CD(String hC1_COB_LINE_AUTH_PROC_CD) {
		HC1_COB_LINE_AUTH_PROC_CD = hC1_COB_LINE_AUTH_PROC_CD;
	}
	public String getHC1_COB_LINE_FST_DT() {
		return HC1_COB_LINE_FST_DT;
	}
	public void setHC1_COB_LINE_FST_DT(String hC1_COB_LINE_FST_DT) {
		HC1_COB_LINE_FST_DT = hC1_COB_LINE_FST_DT;
	}
	public String getHC1_COB_LINE_LST_SRVC_DT() {
		return HC1_COB_LINE_LST_SRVC_DT;
	}
	public void setHC1_COB_LINE_LST_SRVC_DT(String hC1_COB_LINE_LST_SRVC_DT) {
		HC1_COB_LINE_LST_SRVC_DT = hC1_COB_LINE_LST_SRVC_DT;
	}
	public BigDecimal getHC1_COB_LINE_CHRG_AMT() {
		return HC1_COB_LINE_CHRG_AMT;
	}
	public void setHC1_COB_LINE_CHRG_AMT(BigDecimal hC1_COB_LINE_CHRG_AMT) {
		HC1_COB_LINE_CHRG_AMT = hC1_COB_LINE_CHRG_AMT;
	}
	@Override
	public String toString() {
		return "HC1_COB_LINE_ENTRY [HC1_COB_LN_SRV_ID=" + HC1_COB_LN_SRV_ID + ", HC1_COB_LN_ALLOW_AMT_IND="
				+ HC1_COB_LN_ALLOW_AMT_IND + ", HC1_COB_LN_835_RPT_ALLOW_AMT=" + HC1_COB_LN_835_RPT_ALLOW_AMT
				+ ", HC1_COB_LN_EOB_OI_PAID_AMT=" + HC1_COB_LN_EOB_OI_PAID_AMT + ", HC1_COB_LN_EOB_MEDC_PAID_AMT="
				+ HC1_COB_LN_EOB_MEDC_PAID_AMT + ", HC1_COB_LN_COB_PRV_WRT_OFF=" + HC1_COB_LN_COB_PRV_WRT_OFF
				+ ", HC1_COB_LN_835_COB_PRIM_IMPAC=" + HC1_COB_LN_835_COB_PRIM_IMPAC
				+ ", HC1_COB_LN_835_PAT_RESP_OVERC=" + HC1_COB_LN_835_PAT_RESP_OVERC
				+ ", HC1_COB_LN_835_PAT_RESP_DEDUC=" + HC1_COB_LN_835_PAT_RESP_DEDUC
				+ ", HC1_COB_LN_835_PAT_RESP_COPAY=" + HC1_COB_LN_835_PAT_RESP_COPAY
				+ ", HC1_COB_LN_835_PAT_RESP_COINS=" + HC1_COB_LN_835_PAT_RESP_COINS
				+ ", HC1_COB_LN_835_PAT_RESP_NTCOV=" + HC1_COB_LN_835_PAT_RESP_NTCOV + ", HC1_COB_LN_835_MEDC_EST_AMT="
				+ HC1_COB_LN_835_MEDC_EST_AMT + ", HC1_COB_LN_835_PAT_RESP_TOTAL=" + HC1_COB_LN_835_PAT_RESP_TOTAL
				+ ", HC1_COB_LN_835_PRV_NC_AMT=" + HC1_COB_LN_835_PRV_NC_AMT + ", HC1_COB_LN_835_DENY_NC_AMT="
				+ HC1_COB_LN_835_DENY_NC_AMT + ", HC1_COB_LINE_LN_ID=" + HC1_COB_LINE_LN_ID + ", HC1_COB_LINE_SRVC_CD="
				+ HC1_COB_LINE_SRVC_CD + ", HC1_COB_LINE_PMT_SVC_CD=" + HC1_COB_LINE_PMT_SVC_CD
				+ ", HC1_COB_LINE_AUTH_PROC_CD=" + HC1_COB_LINE_AUTH_PROC_CD + ", HC1_COB_LINE_FST_DT="
				+ HC1_COB_LINE_FST_DT + ", HC1_COB_LINE_LST_SRVC_DT=" + HC1_COB_LINE_LST_SRVC_DT
				+ ", HC1_COB_LINE_CHRG_AMT=" + HC1_COB_LINE_CHRG_AMT + "]";
	}
	
	

}
