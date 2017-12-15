package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;

//Class representing the  WS_LINE_HOLD_TBL in D5427HC1
//EAch line can have either a WS_LN_NY_DED_MM_AMT field filled 
// Or WS_LN_MM_DED_AMT field filled. 
//An array/list of size 7 of these fields should be in the IndicatorObject of the Claim 
public class LineHold {
	
	private BigDecimal LN_NY_DED_MM_AMT = new BigDecimal(0); 
	private BigDecimal LN_MM_DED_AMT = new BigDecimal(0);
	
	public BigDecimal getLN_NY_DED_MM_AMT() {
		return LN_NY_DED_MM_AMT;
	}
	public void setLN_NY_DED_MM_AMT(BigDecimal lN_NY_DED_MM_AMT) {
		LN_NY_DED_MM_AMT = lN_NY_DED_MM_AMT;
	}
	public BigDecimal getLN_MM_DED_AMT() {
		return LN_MM_DED_AMT;
	}
	public void setLN_MM_DED_AMT(BigDecimal lN_MM_DED_AMT) {
		LN_MM_DED_AMT = lN_MM_DED_AMT;
	} 
}
