package com.optum.tops.JP835RED.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

public class JP54RedReturn {

	//Corresponds to :RET:-835-CLM-RED-TBL in the return copybook from RED, Populated by 7701 Section.
	private List<Ret835ClmRed> ret835ClmRedTbl = new ArrayList<Ret835ClmRed>(10); 

	////Corresponds to :RET:-835-CLM-RARC-TBL, Populated by 7702 Section.
	private List<Ret835ClmRarc> ret835ClmRarcTbl = new ArrayList<Ret835ClmRarc>(3); 

	// Corresponds to :RET:-835-CLM-ERR-TBL, Populated by 7703 Section of Red. 
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

	// Corresponds to :RET:-835-LINE-LVL, Populated by 7705 Section of Red. 
	private Ret835LineLvl[] ret835LineLvl = new Ret835LineLvl[7];

	//Corresponds to :RET:-835-REDUCT-AREA, populated by 7704, 
	private List<Ret835Reduct> ret835ReductArea = new ArrayList<Ret835Reduct>(150); 
	private List<Ret835PrcLvl> ret835PrcLvl=new ArrayList<Ret835PrcLvl>(60);
	private List<Ub92_835AdjdSvc> retUB92_835_AdjdSvcInfo = new ArrayList<Ub92_835AdjdSvc>(60); // Corresponds to :RET:-UB92-835-ADJD-SVC-INFO structure


	//getters and setters
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

	public Ret835LineLvl[] getRet835LineLvl() {
		return ret835LineLvl;
	}


	public void setRet835LineLvl(Ret835LineLvl[] ret835LineLvl) {
		this.ret835LineLvl = ret835LineLvl;
	}
	public List<Ret835Reduct> getRet835ReductArea() {
		return ret835ReductArea;
	}


	public void setRet835ReductArea(List<Ret835Reduct> ret835ReductArea) {
		this.ret835ReductArea = ret835ReductArea;
	}


	public List<Ub92_835AdjdSvc> getRetUB92_835_AdjdSvcInfo() {
		return retUB92_835_AdjdSvcInfo;
	}


	public void setRetUB92_835_AdjdSvcInfo(List<Ub92_835AdjdSvc> retUB92_835_AdjdSvcInfo) {
		this.retUB92_835_AdjdSvcInfo = retUB92_835_AdjdSvcInfo;
	}



	public List<Ret835PrcLvl> getRet835PrcLvl() {
		return ret835PrcLvl;
	}


	public void setRet835PrcLvl(List<Ret835PrcLvl> ret835PrcLvl) {
		this.ret835PrcLvl = ret835PrcLvl;
	}


}