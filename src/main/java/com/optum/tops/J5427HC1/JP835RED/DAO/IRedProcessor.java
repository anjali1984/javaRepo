package com.optum.tops.J5427HC1.JP835RED.DAO;

import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.JP54RedReturn;

public interface IRedProcessor {

	JP54RedReturn InstClaim2100(JP54RedRequest request_to_red, String logId);

	JP54RedReturn ProClaim2200(JP54RedRequest request_to_red, String logId);

}