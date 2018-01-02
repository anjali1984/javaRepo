package com.optum.tops.JP835RED.models;

import java.util.ArrayList;
import java.util.List;

public class JP54RedReturn {
	private List<Ret835ClmRed> ret835ClmRedTbl = new ArrayList<Ret835ClmRed>(10);
	private List<Ret835ClmRarc> ret835ClmRarcTbl = new ArrayList<Ret835ClmRarc>(3); 
	private List<Ret835ClmErr> ret835ClmErrTbl = new ArrayList<Ret835ClmErr>(3); 
	

		// 10 :RET:-835-REDUCT-AREA OCCURS 150 TIMES.
		// 15 :RET:-835-RD-SVC-ID PIC S9(03) COMP-3.
		// 15 :RET:-835-RD-REV-ID PIC S9(03) COMP-3.
		// 15 :RET:-835-RD-REV-CD PIC S9(05) COMP-3.
		// 15 :RET:-835-RD-PROC-CD PIC X(05).
		// 15 :RET:-835-RD-PROC-TYP-CD PIC X(01).
		// 15 :RET:-835-RD-CATGY-ID PIC S9(4) COMP.
		// 15 :RET:-835-RD-GRP-CD PIC X(02).
		// 15 :RET:-835-RD-CARC-CD PIC X(05).
		// 15 :RET:-835-RD-RARC-CD PIC X(06).
		// 15 :RET:-835-RD-PD-AMT PIC S9(09)V99 COMP-3.
	private List<Ret835ReductArea> ret835ReductArea = new ArrayList<Ret835ReductArea>(150);

	private List<Ub92_835AdjdSvc> retUB92_835_AdjdSvcInfo = new ArrayList<Ub92_835AdjdSvc>(60); 
	
	public List<Ret835ClmRed> getRet835ClmRedTbl() {
		return ret835ClmRedTbl;
	}


	public void setRet835ClmRedTbl(List<Ret835ClmRed> ret835ClmRedTbl) {
		this.ret835ClmRedTbl = ret835ClmRedTbl;
	}


	public List<Ret835ClmRarc> getRet835ClmRarcTbl() {
		return ret835ClmRarcTbl;
	}


	public void setRet835ClmRarcTbl(List<Ret835ClmRarc> ret835ClmRarcTbl) {
		this.ret835ClmRarcTbl = ret835ClmRarcTbl;
	}


	public List<Ret835ClmErr> getRet835ClmErrTbl() {
		return ret835ClmErrTbl;
	}


	public void setRet835ClmErrTbl(List<Ret835ClmErr> ret835ClmErrTbl) {
		this.ret835ClmErrTbl = ret835ClmErrTbl;
	}


	public List<Ret835ReductArea> getRet835ReductArea() {
		return ret835ReductArea;
	}


	public void setRet835ReductArea(List<Ret835ReductArea> ret835ReductArea) {
		this.ret835ReductArea = ret835ReductArea;
	}


	public List<Ub92_835AdjdSvc> getRetUB92_835_AdjdSvcInfo() {
		return retUB92_835_AdjdSvcInfo;
	}


	public void setRetUB92_835_AdjdSvcInfo(List<Ub92_835AdjdSvc> retUB92_835_AdjdSvcInfo) {
		this.retUB92_835_AdjdSvcInfo = retUB92_835_AdjdSvcInfo;
	}
	
	
}
