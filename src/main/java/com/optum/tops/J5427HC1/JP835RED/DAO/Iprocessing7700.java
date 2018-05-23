package com.optum.tops.J5427HC1.JP835RED.DAO;

import java.util.List;

import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.Ret835ClmErr;
import com.optum.tops.JP835RED.models.Ret835ClmRarc;
import com.optum.tops.JP835RED.models.Ret835ClmRed;
import com.optum.tops.JP835RED.models.Ret835LineLvl;
import com.optum.tops.JP835RED.models.Ret835PrcLvl;
import com.optum.tops.JP835RED.models.Ret835Reduct;
import com.optum.tops.JP835RED.models.Ub92_835AdjdSvc;

public interface Iprocessing7700 {

	List<Ret835ClmRed> do7701(JP54RedRequest req, String logId);

	List<Ret835ClmRarc> do7702(JP54RedRequest req, String logId);

	List<Ret835ClmErr> do7703(JP54RedRequest req, String logId);

	/**
	 * read data from ADJD_CLMSFLN_PD_AMT_RDUC  table and moved them to Ret835Reduct object 
	 * @param req
	 * @param logId 
	 * @return
	 */
	List<Ret835Reduct> do7704(JP54RedRequest req, String logId);

	/**
	 * read data from ADJD_CLMSFLN_RARC_CD  table and move them to CB variables
	 * @param req
	 * @param logId 
	 * @return
	 */
	Ret835LineLvl[] do7705(JP54RedRequest req, String logId);

	/**
	 * read data from ADJD_CLMSFLN_ERR_CD table and move them to CB variables
	 * @param logId 
	 * @param JP54RedRequest req
	 * @return
	 */
	List<Ret835PrcLvl> do7706(JP54RedRequest req, String logId);

	List<Ub92_835AdjdSvc> do7708(JP54RedRequest req, String logId);

}