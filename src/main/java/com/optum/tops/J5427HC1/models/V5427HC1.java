package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//Individual Claim to be appended to the response 
public class V5427HC1 {

	private String HC1_COB_INVENTORY_CONTROL_DT;
	private ClaimIndicatorValues my_indicator ; 
	private String HC1_COB_COB_CLAIM_INDICATOR;    //Cob claim indicator
	private String HC1_COB_COB_CALC_IND; 
	private String HC1_COB_COB_835_PROC_IND;
	private String HC1_COB_INST_OR_PROF;        
	       
	private String HC1_COB_ALLOW_AMT_IND;       
	private BigDecimal HC1_COB_835_RPT_ALLOW_AMT;   
	private BigDecimal HC1_COB_OI_PAID_AMT;         
	private BigDecimal HC1_COB_MEDC_PAID_AMT;      
	private BigDecimal HC1_COB_PRV_WRT_OFF;         
	
	private BigDecimal HC1_COB_835_COB_PRIM_IMPAC;
	private BigDecimal HC1_COB_835_PAT_RESP_OVERC;
	private BigDecimal HC1_COB_835_PAT_RESP_DEDUC;
	private BigDecimal HC1_COB_835_PAT_RESP_COPAY;
	private BigDecimal HC1_COB_835_PAT_RESP_COINS;
	private BigDecimal HC1_COB_835_PAT_RESP_NTCOV;
	private BigDecimal HC1_COB_835_PAT_RESP_MCEST;
	private BigDecimal HC1_COB_835_PAT_RESP_TOTAL;
	private BigDecimal HC1_COB_835_PRV_NC_AMT;    
	private BigDecimal HC1_COB_835_DENY_NC_AMT;
	
	private List<HC1_COB_LINE_ENTRY> HC1_COB_LINE_DATA_AREA = new ArrayList<HC1_COB_LINE_ENTRY>(); //Various Lines for this Claim 
	private List<String> HC1_COB_835_OOB_ERROR = new ArrayList<String>();	
	
	private int HC1_COB_NBR_LINES;
	
	

	public ClaimIndicatorValues getMy_indicator() {
		return my_indicator;
	}

	public void setMy_indicator(ClaimIndicatorValues my_indicator) {
		this.my_indicator = my_indicator;
	}

	public String getHC1_COB_INVENTORY_CONTROL_DT() {
		return HC1_COB_INVENTORY_CONTROL_DT;
	}

	public void setHC1_COB_INVENTORY_CONTROL_DT(String hC1_COB_INVENTORY_CONTROL_DT) {
		HC1_COB_INVENTORY_CONTROL_DT = hC1_COB_INVENTORY_CONTROL_DT;
	}

	public String getHC1_COB_COB_CALC_INDICATOR() {
		return HC1_COB_COB_CLAIM_INDICATOR;
	}

	public void setHC1_COB_COB_CALC_IND(String hC1_COB_COB_CLAIM_IND) {
		HC1_COB_COB_CLAIM_INDICATOR = hC1_COB_COB_CLAIM_IND;
	}

	public String getHC1_COB_COB_835_PROC_IND() {
		return HC1_COB_COB_835_PROC_IND;
	}

	public void setHC1_COB_COB_835_PROC_IND(String hC1_COB_COB_835_PROC_IND) {
		HC1_COB_COB_835_PROC_IND = hC1_COB_COB_835_PROC_IND;
	}

	public String getHC1_COB_INST_OR_PROF() {
		return HC1_COB_INST_OR_PROF;
	}

	public void setHC1_COB_INST_OR_PROF(String hC1_COB_INST_OR_PROF) {
		HC1_COB_INST_OR_PROF = hC1_COB_INST_OR_PROF;
	}

	public String getHC1_COB_ALLOW_AMT_IND() {
		return HC1_COB_ALLOW_AMT_IND;
	}

	public void setHC1_COB_ALLOW_AMT_IND(String hC1_COB_ALLOW_AMT_IND) {
		HC1_COB_ALLOW_AMT_IND = hC1_COB_ALLOW_AMT_IND;
	}

	public BigDecimal getHC1_COB_835_RPT_ALLOW_AMT() {
		return HC1_COB_835_RPT_ALLOW_AMT;
	}

	public void setHC1_COB_835_RPT_ALLOW_AMT(BigDecimal hC1_COB_835_RPT_ALLOW_AMT) {
		HC1_COB_835_RPT_ALLOW_AMT = hC1_COB_835_RPT_ALLOW_AMT;
	}

	public BigDecimal getHC1_COB_OI_PAID_AMT() {
		return HC1_COB_OI_PAID_AMT;
	}

	public void setHC1_COB_OI_PAID_AMT(BigDecimal hC1_COB_OI_PAID_AMT) {
		HC1_COB_OI_PAID_AMT = hC1_COB_OI_PAID_AMT;
	}

	public BigDecimal getHC1_COB_MEDC_PAID_AMT() {
		return HC1_COB_MEDC_PAID_AMT;
	}

	public void setHC1_COB_MEDC_PAID_AMT(BigDecimal hC1_COB_MEDC_PAID_AMT) {
		HC1_COB_MEDC_PAID_AMT = hC1_COB_MEDC_PAID_AMT;
	}

	public BigDecimal getHC1_COB_PRV_WRT_OFF() {
		return HC1_COB_PRV_WRT_OFF;
	}

	public void setHC1_COB_PRV_WRT_OFF(BigDecimal hC1_COB_PRV_WRT_OFF) {
		HC1_COB_PRV_WRT_OFF = hC1_COB_PRV_WRT_OFF;
	}

	public BigDecimal getHC1_COB_835_COB_PRIM_IMPAC() {
		return HC1_COB_835_COB_PRIM_IMPAC;
	}

	public void setHC1_COB_835_COB_PRIM_IMPAC(BigDecimal hC1_COB_835_COB_PRIM_IMPAC) {
		HC1_COB_835_COB_PRIM_IMPAC = hC1_COB_835_COB_PRIM_IMPAC;
	}

	public BigDecimal getHC1_COB_835_PAT_RESP_OVERC() {
		return HC1_COB_835_PAT_RESP_OVERC;
	}

	public void setHC1_COB_835_PAT_RESP_OVERC(BigDecimal hC1_COB_835_PAT_RESP_OVERC) {
		HC1_COB_835_PAT_RESP_OVERC = hC1_COB_835_PAT_RESP_OVERC;
	}

	public BigDecimal getHC1_COB_835_PAT_RESP_DEDUC() {
		return HC1_COB_835_PAT_RESP_DEDUC;
	}

	public void setHC1_COB_835_PAT_RESP_DEDUC(BigDecimal hC1_COB_835_PAT_RESP_DEDUC) {
		HC1_COB_835_PAT_RESP_DEDUC = hC1_COB_835_PAT_RESP_DEDUC;
	}

	public BigDecimal getHC1_COB_835_PAT_RESP_COPAY() {
		return HC1_COB_835_PAT_RESP_COPAY;
	}

	public void setHC1_COB_835_PAT_RESP_COPAY(BigDecimal hC1_COB_835_PAT_RESP_COPAY) {
		HC1_COB_835_PAT_RESP_COPAY = hC1_COB_835_PAT_RESP_COPAY;
	}

	public BigDecimal getHC1_COB_835_PAT_RESP_COINS() {
		return HC1_COB_835_PAT_RESP_COINS;
	}

	public void setHC1_COB_835_PAT_RESP_COINS(BigDecimal hC1_COB_835_PAT_RESP_COINS) {
		HC1_COB_835_PAT_RESP_COINS = hC1_COB_835_PAT_RESP_COINS;
	}

	public BigDecimal getHC1_COB_835_PAT_RESP_NTCOV() {
		return HC1_COB_835_PAT_RESP_NTCOV;
	}

	public void setHC1_COB_835_PAT_RESP_NTCOV(BigDecimal hC1_COB_835_PAT_RESP_NTCOV) {
		HC1_COB_835_PAT_RESP_NTCOV = hC1_COB_835_PAT_RESP_NTCOV;
	}

	public BigDecimal getHC1_COB_835_PAT_RESP_MCEST() {
		return HC1_COB_835_PAT_RESP_MCEST;
	}

	public void setHC1_COB_835_PAT_RESP_MCEST(BigDecimal hC1_COB_835_PAT_RESP_MCEST) {
		HC1_COB_835_PAT_RESP_MCEST = hC1_COB_835_PAT_RESP_MCEST;
	}

	public BigDecimal getHC1_COB_835_PAT_RESP_TOTAL() {
		return HC1_COB_835_PAT_RESP_TOTAL;
	}

	public void setHC1_COB_835_PAT_RESP_TOTAL(BigDecimal hC1_COB_835_PAT_RESP_TOTAL) {
		HC1_COB_835_PAT_RESP_TOTAL = hC1_COB_835_PAT_RESP_TOTAL;
	}

	public BigDecimal getHC1_COB_835_PRV_NC_AMT() {
		return HC1_COB_835_PRV_NC_AMT;
	}

	public void setHC1_COB_835_PRV_NC_AMT(BigDecimal hC1_COB_835_PRV_NC_AMT) {
		HC1_COB_835_PRV_NC_AMT = hC1_COB_835_PRV_NC_AMT;
	}

	public BigDecimal getHC1_COB_835_DENY_NC_AMT() {
		return HC1_COB_835_DENY_NC_AMT;
	}

	public void setHC1_COB_835_DENY_NC_AMT(BigDecimal hC1_COB_835_DENY_NC_AMT) {
		HC1_COB_835_DENY_NC_AMT = hC1_COB_835_DENY_NC_AMT;
	}

	public List<String> getHC1_COB_835_OOB_ERROR() {
		return HC1_COB_835_OOB_ERROR;
	}

	public void setHC1_COB_835_OOB_ERROR(List<String> hC1_COB_835_OOB_ERROR) {
		HC1_COB_835_OOB_ERROR = hC1_COB_835_OOB_ERROR;
	}

	public int getHC1_COB_NBR_LINES() {
		return HC1_COB_NBR_LINES;
	}

	public void setHC1_COB_NBR_LINES(int hC1_COB_NBR_LINES) {
		HC1_COB_NBR_LINES = hC1_COB_NBR_LINES;
	}

	public List<HC1_COB_LINE_ENTRY> getHC1_COB_LNE_DATA_AREA() {
		return HC1_COB_LINE_DATA_AREA;
	}

	public void setHC1_COB_LNE_DATA_AREA(List<HC1_COB_LINE_ENTRY> hC1_COB_LNE_DATA_AREA) {
		HC1_COB_LINE_DATA_AREA = hC1_COB_LNE_DATA_AREA;
	}

	public String getHC1_COB_COB_CALC_IND() {
		return HC1_COB_COB_CALC_IND;
	}

	public String getHC1_COB_COB_CLAIM_INDICATOR() {
		return HC1_COB_COB_CLAIM_INDICATOR;
	}

	public void setHC1_COB_COB_CLAIM_INDICATOR(String hC1_COB_COB_CLAIM_INDICATOR) {
		HC1_COB_COB_CLAIM_INDICATOR = hC1_COB_COB_CLAIM_INDICATOR;
	}

}
