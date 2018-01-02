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
	
	
}
