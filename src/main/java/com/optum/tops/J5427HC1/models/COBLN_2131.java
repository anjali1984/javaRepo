package com.optum.tops.J5427HC1.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
//Model Class representing each record that's returned by the COB-SERV-CALC cursor
public class COBLN_2131 {
	
	private String Invn_Ctl_Nbr; 
	private String Icn_Sufx_Cd; 
	private int Orig_Ln_Corr_Id; 
	private BigDecimal Medcr_Pd_Amt=BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY); 
	private BigDecimal Oi_Pd_Ln_Amt=BigDecimal.valueOf(0.00).setScale(2, RoundingMode.UNNECESSARY);
	public String getInvn_Ctl_Nbr() {
		return Invn_Ctl_Nbr;
	}
	public void setInvn_Ctl_Nbr(String invn_Ctl_Nbr) {
		Invn_Ctl_Nbr = invn_Ctl_Nbr;
	}
	public String getIcn_Sufx_Cd() {
		return Icn_Sufx_Cd;
	}
	public void setIcn_Sufx_Cd(String icn_Sufx_Cd) {
		Icn_Sufx_Cd = icn_Sufx_Cd;
	}
	public int getOrig_Ln_Corr_Id() {
		return Orig_Ln_Corr_Id;
	}
	public void setOrig_Ln_Corr_Id(int orig_Ln_Corr_Id) {
		Orig_Ln_Corr_Id = orig_Ln_Corr_Id;
	}
	public BigDecimal getMedcr_Pd_Amt() {
		return Medcr_Pd_Amt;
	}
	public void setMedcr_Pd_Amt(BigDecimal medcr_Pd_Amt) {
		Medcr_Pd_Amt = medcr_Pd_Amt;
	}
	public BigDecimal getOi_Pd_Ln_Amt() {
		return Oi_Pd_Ln_Amt;
	}
	public void setOi_Pd_Ln_Amt(BigDecimal oi_Pd_Ln_Amt) {
		Oi_Pd_Ln_Amt = oi_Pd_Ln_Amt;
	} 
	
	

}
