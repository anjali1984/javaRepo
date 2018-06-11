package com.optum.tops.J5427HC1.models;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class V5427HC1String {

	private String HC1_COB_INVENTORY_CONTROL_DT="";
	private String HC1_COB_COB_CLAIM_INDICATOR="N";    //Cob claim indicator
	private String HC1_COB_NEW_COB_CALC_IND="";
	private String HC1_COB_NEW_COB_835_PROC_IND="";
	private String HC1_COB_INST_OR_PROF="";        
	private String error="";   
	private String HC1_COB_ALLOW_AMT_IND="";      
	private String HC1_COB_835_RPT_ALLOW_AMT="0.00";   
	private String HC1_COB_OI_PAID_AMT="0.00";         
	private String HC1_COB_MEDC_PAID_AMT="0.00";      
	private String HC1_COB_PRV_WRT_OFF="0.00";         

	private String HC1_COB_835_COB_PRIM_IMPAC ="0.00";
	private String HC1_COB_835_PAT_RESP_OVERC="0.00";
	private String HC1_COB_835_PAT_RESP_DEDUC="0.00";
	private String HC1_COB_835_PAT_RESP_COPAY="0.00";
	private String HC1_COB_835_PAT_RESP_COINS="0.00";
	private String HC1_COB_835_PAT_RESP_NTCOV="0.00";
	private String HC1_COB_835_PAT_RESP_MCEST="0.00";
	private String HC1_COB_835_PAT_RESP_TOTAL="0.00";
	private String HC1_COB_835_PRV_NC_AMT="0.00";    
	private String HC1_COB_835_DENY_NC_AMT="0.00";
	private List<HC1_COB_LINE_ENTRY> HC1_COB_LINE_DATA_AREA = new ArrayList<HC1_COB_LINE_ENTRY>(); //Various Line level data for this Claim to be returned  
	private List<String> HC1_COB_835_OOB_ERROR = new ArrayList<String>();	

	private int HC1_COB_NBR_LINES;


	public String getHC1_COB_INVENTORY_CONTROL_DT() {
		return HC1_COB_INVENTORY_CONTROL_DT;
	}

	public void setHC1_COB_INVENTORY_CONTROL_DT(String hC1_COB_INVENTORY_CONTROL_DT) {
		HC1_COB_INVENTORY_CONTROL_DT = hC1_COB_INVENTORY_CONTROL_DT;
	}

	public String getHC1_COB_COB_CLAIM_INDICATOR() {
		return HC1_COB_COB_CLAIM_INDICATOR;
	}

	public void setHC1_COB_COB_CLAIM_INDICATOR(String hC1_COB_COB_CLAIM_INDICATOR) {
		HC1_COB_COB_CLAIM_INDICATOR = hC1_COB_COB_CLAIM_INDICATOR;
	}

	public String getHC1_COB_NEW_COB_CALC_IND() {
		return HC1_COB_NEW_COB_CALC_IND;
	}

	public void setHC1_COB_NEW_COB_CALC_IND(String hC1_COB_NEW_COB_CALC_IND) {
		HC1_COB_NEW_COB_CALC_IND = hC1_COB_NEW_COB_CALC_IND;
	}

	public String getHC1_COB_NEW_COB_835_PROC_IND() {
		return HC1_COB_NEW_COB_835_PROC_IND;
	}

	public void setHC1_COB_NEW_COB_835_PROC_IND(String hC1_COB_NEW_COB_835_PROC_IND) {
		HC1_COB_NEW_COB_835_PROC_IND = hC1_COB_NEW_COB_835_PROC_IND;
	}

	public String getHC1_COB_INST_OR_PROF() {
		return HC1_COB_INST_OR_PROF;
	}

	public void setHC1_COB_INST_OR_PROF(String hC1_COB_INST_OR_PROF) {
		HC1_COB_INST_OR_PROF = hC1_COB_INST_OR_PROF;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getHC1_COB_ALLOW_AMT_IND() {
		return HC1_COB_ALLOW_AMT_IND;
	}

	public void setHC1_COB_ALLOW_AMT_IND(String hC1_COB_ALLOW_AMT_IND) {
		HC1_COB_ALLOW_AMT_IND = hC1_COB_ALLOW_AMT_IND;
	}

	public String getHC1_COB_835_RPT_ALLOW_AMT() {
		return HC1_COB_835_RPT_ALLOW_AMT;
	}

	public void setHC1_COB_835_RPT_ALLOW_AMT(String hC1_COB_835_RPT_ALLOW_AMT) {
		HC1_COB_835_RPT_ALLOW_AMT = hC1_COB_835_RPT_ALLOW_AMT;
	}

	public String getHC1_COB_OI_PAID_AMT() {
		return HC1_COB_OI_PAID_AMT;
	}

	public void setHC1_COB_OI_PAID_AMT(String hC1_COB_OI_PAID_AMT) {
		HC1_COB_OI_PAID_AMT = hC1_COB_OI_PAID_AMT;
	}

	public String getHC1_COB_MEDC_PAID_AMT() {
		return HC1_COB_MEDC_PAID_AMT;
	}

	public void setHC1_COB_MEDC_PAID_AMT(String hC1_COB_MEDC_PAID_AMT) {
		HC1_COB_MEDC_PAID_AMT = hC1_COB_MEDC_PAID_AMT;
	}

	public String getHC1_COB_PRV_WRT_OFF() {
		return HC1_COB_PRV_WRT_OFF;
	}

	public void setHC1_COB_PRV_WRT_OFF(String hC1_COB_PRV_WRT_OFF) {
		HC1_COB_PRV_WRT_OFF = hC1_COB_PRV_WRT_OFF;
	}

	public String getHC1_COB_835_COB_PRIM_IMPAC() {
		return HC1_COB_835_COB_PRIM_IMPAC;
	}

	public void setHC1_COB_835_COB_PRIM_IMPAC(String hC1_COB_835_COB_PRIM_IMPAC) {
		HC1_COB_835_COB_PRIM_IMPAC = hC1_COB_835_COB_PRIM_IMPAC;
	}

	public String getHC1_COB_835_PAT_RESP_OVERC() {
		return HC1_COB_835_PAT_RESP_OVERC;
	}

	public void setHC1_COB_835_PAT_RESP_OVERC(String hC1_COB_835_PAT_RESP_OVERC) {
		HC1_COB_835_PAT_RESP_OVERC = hC1_COB_835_PAT_RESP_OVERC;
	}

	public String getHC1_COB_835_PAT_RESP_DEDUC() {
		return HC1_COB_835_PAT_RESP_DEDUC;
	}

	public void setHC1_COB_835_PAT_RESP_DEDUC(String hC1_COB_835_PAT_RESP_DEDUC) {
		HC1_COB_835_PAT_RESP_DEDUC = hC1_COB_835_PAT_RESP_DEDUC;
	}

	public String getHC1_COB_835_PAT_RESP_COPAY() {
		return HC1_COB_835_PAT_RESP_COPAY;
	}

	public void setHC1_COB_835_PAT_RESP_COPAY(String hC1_COB_835_PAT_RESP_COPAY) {
		HC1_COB_835_PAT_RESP_COPAY = hC1_COB_835_PAT_RESP_COPAY;
	}

	public String getHC1_COB_835_PAT_RESP_COINS() {
		return HC1_COB_835_PAT_RESP_COINS;
	}

	public void setHC1_COB_835_PAT_RESP_COINS(String hC1_COB_835_PAT_RESP_COINS) {
		HC1_COB_835_PAT_RESP_COINS = hC1_COB_835_PAT_RESP_COINS;
	}

	public String getHC1_COB_835_PAT_RESP_NTCOV() {
		return HC1_COB_835_PAT_RESP_NTCOV;
	}

	public void setHC1_COB_835_PAT_RESP_NTCOV(String hC1_COB_835_PAT_RESP_NTCOV) {
		HC1_COB_835_PAT_RESP_NTCOV = hC1_COB_835_PAT_RESP_NTCOV;
	}

	public String getHC1_COB_835_PAT_RESP_MCEST() {
		return HC1_COB_835_PAT_RESP_MCEST;
	}

	public void setHC1_COB_835_PAT_RESP_MCEST(String hC1_COB_835_PAT_RESP_MCEST) {
		HC1_COB_835_PAT_RESP_MCEST = hC1_COB_835_PAT_RESP_MCEST;
	}

	public String getHC1_COB_835_PAT_RESP_TOTAL() {
		return HC1_COB_835_PAT_RESP_TOTAL;
	}

	public void setHC1_COB_835_PAT_RESP_TOTAL(String hC1_COB_835_PAT_RESP_TOTAL) {
		HC1_COB_835_PAT_RESP_TOTAL = hC1_COB_835_PAT_RESP_TOTAL;
	}

	public String getHC1_COB_835_PRV_NC_AMT() {
		return HC1_COB_835_PRV_NC_AMT;
	}

	public void setHC1_COB_835_PRV_NC_AMT(String hC1_COB_835_PRV_NC_AMT) {
		HC1_COB_835_PRV_NC_AMT = hC1_COB_835_PRV_NC_AMT;
	}

	public String getHC1_COB_835_DENY_NC_AMT() {
		return HC1_COB_835_DENY_NC_AMT;
	}

	public void setHC1_COB_835_DENY_NC_AMT(String hC1_COB_835_DENY_NC_AMT) {
		HC1_COB_835_DENY_NC_AMT = hC1_COB_835_DENY_NC_AMT;
	}


	public List<HC1_COB_LINE_ENTRY> getHC1_COB_LINE_DATA_AREA() {
		return HC1_COB_LINE_DATA_AREA;
	}

	public void setHC1_COB_LINE_DATA_AREA(List<HC1_COB_LINE_ENTRY> hC1_COB_LINE_DATA_AREA) {
		HC1_COB_LINE_DATA_AREA = hC1_COB_LINE_DATA_AREA;
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



}
