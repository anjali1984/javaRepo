package com.optum.tops.JP835RED.models;

/*
 *               15 :REQ:-835-INVN-CTL-NBR     PIC X(10).
                 15 :REQ:-835-ICN-SUFX-CD      PIC X(02).
                 15 :REQ:-835-PROC-DT          PIC X(10).
                 15 :REQ:-835-PROC-TM          PIC X(08).
 */

public class JP54RedRequest {

	private String RED_INV_CTL_NBR ; 
	
	private String RED_ICN_SUFX_CD ; 

	private String RED_PROC_DT ; 

	private String RED_PROC_TM ;

	public String getRED_INV_CTL_NBR() {
		return RED_INV_CTL_NBR;
	}

	public void setRED_INV_CTL_NBR(String rED_INV_CTL_NBR) {
		RED_INV_CTL_NBR = rED_INV_CTL_NBR;
	}

	public String getRED_ICN_SUFX_CD() {
		return RED_ICN_SUFX_CD;
	}

	public void setRED_ICN_SUFX_CD(String rED_ICN_SUFX_CD) {
		RED_ICN_SUFX_CD = rED_ICN_SUFX_CD;
	}

	public String getRED_PROC_DT() {
		return RED_PROC_DT;
	}

	public void setRED_PROC_DT(String rED_PROC_DT) {
		RED_PROC_DT = rED_PROC_DT;
	}

	public String getRED_PROC_TM() {
		return RED_PROC_TM;
	}

	public void setRED_PROC_TM(String rED_PROC_TM) {
		RED_PROC_TM = rED_PROC_TM;
	} 



	
}
