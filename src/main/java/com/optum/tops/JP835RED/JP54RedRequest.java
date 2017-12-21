package com.optum.tops.JP835RED;

/*
 *               15 :REQ:-835-INVN-CTL-NBR     PIC X(10).
                 15 :REQ:-835-ICN-SUFX-CD      PIC X(02).
                 15 :REQ:-835-PROC-DT          PIC X(10).
                 15 :REQ:-835-PROC-TM          PIC X(08).
 */

public class JP54RedRequest {

	private int RED_INV_CTL_NBR ; 
	
	private int RED_ICN_SUFX_CD ; 

	private int RED_PROC_DT ; 

	private int RED_PROC_TM ;

	public int getRED_INV_CTL_NBR() {
		return RED_INV_CTL_NBR;
	}

	public void setRED_INV_CTL_NBR(int rED_INV_CTL_NBR) {
		RED_INV_CTL_NBR = rED_INV_CTL_NBR;
	}

	public int getRED_ICN_SUFX_CD() {
		return RED_ICN_SUFX_CD;
	}

	public void setRED_ICN_SUFX_CD(int rED_ICN_SUFX_CD) {
		RED_ICN_SUFX_CD = rED_ICN_SUFX_CD;
	}

	public int getRED_PROC_DT() {
		return RED_PROC_DT;
	}

	public void setRED_PROC_DT(int rED_PROC_DT) {
		RED_PROC_DT = rED_PROC_DT;
	}

	public int getRED_PROC_TM() {
		return RED_PROC_TM;
	}

	public void setRED_PROC_TM(int rED_PROC_TM) {
		RED_PROC_TM = rED_PROC_TM;
	} 



	
}
