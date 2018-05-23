package com.optum.tops.J5427HC1.exception;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse {
	String errMsg;
	
	String errorCd;
	
	String Advice;
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public String getErrorCd() {
		return errorCd;
	}

	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}

	public String getAdvice() {
		return Advice;
	}

	public void setAdvice(String advice) {
		Advice = advice;
	}

	
}
