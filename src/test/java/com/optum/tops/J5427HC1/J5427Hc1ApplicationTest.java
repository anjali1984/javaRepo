package com.optum.tops.J5427HC1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.optum.tops.J5427HC1.JP835RED.DAO.Iprocessing7700;
import com.optum.tops.J5427HC1.concurrency.ICallableClaimTask;
import com.optum.tops.J5427HC1.dao.D54DAO.Imp.FetchCoblnAmtsDao;
import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.ICOBLN2121Service;
import com.optum.tops.J5427HC1.services.ICOBLN2131Service;
import com.optum.tops.J5427HC1.services.ICheckCOBClaim;
import com.optum.tops.J5427HC1.services.IInstlReduction2140Service;
import com.optum.tops.J5427HC1.services.ILoadCobLnLineAmtsService2150;
import com.optum.tops.J5427HC1.services.ILoadSumForReductService2170;
import com.optum.tops.J5427HC1.services.IOpsHcfaService;
import com.optum.tops.J5427HC1.services.IProfReduction2160Service;
import com.optum.tops.J5427HC1.services.IRequestProcessor;
import com.optum.tops.J5427HC1.services.Imp.COBONL2200Service;
import com.optum.tops.J5427HC1.services.Imp.RequestProcessor;
import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.JP54RedReturn;

import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = J5427Hc1Application.class)
@TestPropertySource({"classpath:queries.properties","classpath:application.properties","classpath:log4j.properties"})

@ComponentScan(basePackages =
{
		"com.optum.tops.J5427HC1.services.*",
		"com.optum.tops.J5427HC1.dao.D54DAO.CheckCOBDao",
		"com.optum.tops.J5427HC1.models",
        "com.optum.tops.JP835RED.models"})
public class J5427Hc1ApplicationTest {

	@Autowired
	ICheckCOBClaim checkCOBClaim;
	@Autowired
	IOpsHcfaService opshcfacheck;
	@Autowired 
	ICOBLN2121Service cobln2121;	
	@Autowired 
	ICOBLN2131Service cobln2131;
	@Autowired 
	IProfReduction2160Service profRed2160;
	@Autowired 
	Iprocessing7700 process7700;
	@Autowired
	ILoadSumForReductService2170 loadSumForReductService2170;
	IRequestProcessor requestProcessor;
	ICallableClaimTask callableClaimTask;
	Hc1Request hc1Req;
	HC1Response hc1Resp;
	JP54RedRequest requestToRed;
	JP54RedReturn respFromRed;
	@Autowired
	ReqClaimEntryVO claimReqVo;
	@Autowired
	ReqClaimEntry claimReq;
	@Autowired
	V5427HC1 claimResp;
	@Autowired
	IInstlReduction2140Service Inst2140;	
	@Autowired
	ILoadCobLnLineAmtsService2150 Inst2150;
	@Autowired
	ILoadSumForReductService2170 Inst2170;

	@Test
	public void contextLoads() {
	}


	@Test
	public void BigDecimalComputationTest()
	{
		IRequestProcessor reqPrcr=new RequestProcessor();
		V5427HC1 currentClaim =new V5427HC1();
		currentClaim.setHC1_COB_INST_OR_PROF("I");
		currentClaim.setHC1_COB_COB_835_PROC_IND("M");
		currentClaim.setHC1_COB_NBR_LINES(1);
		currentClaim.setHC1_COB_835_COB_PRIM_IMPAC(BigDecimal.ZERO);
		System.out.println("BigDecimalComputationTest::"+"ten "+currentClaim.getHC1_COB_835_COB_PRIM_IMPAC());

		BigDecimal compute=currentClaim.getHC1_COB_835_COB_PRIM_IMPAC().add(BigDecimal.TEN).subtract(BigDecimal.TEN);
		System.out.println("BigDecimalComputationTest::"+"compute "+compute);


	}

	@Test
	public void COBClaimTest()

	{
		try {

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");
			claimResp=checkCOBClaim.COB_claim_check(claimReqVo);
			System.out.println("COBClaimTest::"+"COB_CLAIM_INDICATOR "+claimResp.getHC1_COB_COB_CLAIM_INDICATOR());
			System.out.println("COBClaimTest::"+"DBKE2_ICN_SUFX_CD "+claimResp.getMy_indicator().getDBKE2_ICN_SUFX_CD());
			System.out.println("COBClaimTest::"+"DBKE2_ICN_SUFX_VERS_NBR "+claimResp.getMy_indicator().getDBKE2_ICN_SUFX_VERS_NBR());


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void opshcClaimTest()

	{
		try {

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(new BigDecimal(0));
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("P");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");

			claimResp=opshcfacheck.Ops_Hcfa_claim_check(claimReqVo,claimResp);
			System.out.println("opshcClaimTest::"+"OPS_HCFA_INDICATOR "+claimResp.getMy_indicator().getOPS_HCFA_INDICATOR());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getCsrOrigHdrDataTest()

	{
		try {

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			//claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("0000000000");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");

			claimResp=opshcfacheck.get_CSR_ORIGHDR_DATA(claimReqVo, claimResp);
			System.out.println("getCsrOrigHdrDataTest::"+"HC1_ADJD_CLMSF_ORIGHDR_DATAAREA "+claimResp.getMy_indicator().getHC1_ADJD_CLMSF_ORIGHDR_DATAAREA().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getResultsCoblnLineFldsTest()
	{
		try{

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");

			claimResp=cobln2121.getResultsCobln_Line_Flds(claimReqVo, claimResp);
			System.out.println("getResultsCoblnLineFldsTest::"+"WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// penny process needs to be  tested when Ret835PrcLv returns something
	@Test
	public void do2003PennySectionTest()
	{
		try{

			requestToRed=new JP54RedRequest();
			respFromRed=new JP54RedReturn();

			requestToRed.setRED_INV_CTL_NBR("1047907467");
			requestToRed.setRED_ICN_SUFX_CD("01");
			requestToRed.setRED_PROC_DT("2017-10-26");
			requestToRed.setRED_PROC_TM("14.03.23");
			respFromRed.setRet835PrcLvl(process7700.do7706(requestToRed, "1000199084"));
			claimResp=profRed2160.do2003PennySection(claimResp, respFromRed, "1000199084");
			for( int i=0;i<claimResp.getMy_indicator().getSVC_LINE_PENNY_IND_ENTRY().length;i++)
				System.out.println("do2003PennySectionTest::"+"SVC_LINE_PENNY_IND_ENTRY "+claimResp.getMy_indicator().getSVC_LINE_PENNY_IND_ENTRY()[i]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/*
	 * need different claim to test OimcCobAmtsDao.getData
	 */
	@Test
	public void do2131LogicTest()
	{
		try{

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");

			claimResp=cobln2131.do2131Logic(claimReqVo, claimResp);
			System.out.println("do2131LogicTest::"+"WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test
	public void do7704Test()
	{
		try{
			requestToRed=new JP54RedRequest();
			respFromRed=new JP54RedReturn();
			requestToRed.setRED_INV_CTL_NBR("1047907467");
			requestToRed.setRED_ICN_SUFX_CD("01");
			requestToRed.setRED_PROC_DT("2017-10-26");
			requestToRed.setRED_PROC_TM("14.03.23");
			respFromRed.setRet835ReductArea(process7700.do7704(requestToRed, "10479074670000007234"));
			System.out.println("do7704Test:: Ret835ReductArea "+respFromRed.getRet835ReductArea());

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void do7705Test()
	{
		try{
			requestToRed=new JP54RedRequest();
			respFromRed=new JP54RedReturn();
			requestToRed.setRED_INV_CTL_NBR("1047907467");
			requestToRed.setRED_ICN_SUFX_CD("01");
			requestToRed.setRED_PROC_DT("2017-10-26");
			requestToRed.setRED_PROC_TM("14.03.23");
			respFromRed.setRet835LineLvl(process7700.do7705(requestToRed, "10479074670000007234"));
			System.out.println("do7705Test:: Ret835LineLvl "+respFromRed.getRet835LineLvl());


		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void do7706Test()
	{
		try{
			requestToRed=new JP54RedRequest();
			respFromRed=new JP54RedReturn();
			requestToRed.setRED_INV_CTL_NBR("1047907467");
			requestToRed.setRED_ICN_SUFX_CD("01");
			requestToRed.setRED_PROC_DT("2017-10-26");
			requestToRed.setRED_PROC_TM("14.03.23");
			respFromRed.setRet835PrcLvl(process7700.do7706(requestToRed, "1000199084"));
			System.out.println("do7706Test:: Ret835PrcLvl "+respFromRed.getRet835PrcLvl());


		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void do7708Test()
	{
		try{
			requestToRed=new JP54RedRequest();
			respFromRed=new JP54RedReturn();
			requestToRed.setRED_INV_CTL_NBR("1047907467");
			requestToRed.setRED_ICN_SUFX_CD("01");
			requestToRed.setRED_PROC_DT("2017-10-26");
			requestToRed.setRED_PROC_TM("14.03.23");
			respFromRed.setRetUB92_835_AdjdSvcInfo(process7700.do7708(requestToRed, "10479074670000007234"));
			System.out.println("do7708Test:: RetUB92_835_AdjdSvcInfo "+respFromRed.getRetUB92_835_AdjdSvcInfo());


		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void do2160SectionTest()
	{
		try{



			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);

			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");
			claimResp.setHC1_COB_COB_835_PROC_IND("M");

			claimResp=cobln2121.getResultsCobln_Line_Flds(claimReqVo, claimResp);

			claimResp=profRed2160.do2160Section(claimReqVo, claimResp);
			System.out.println("do2160LogicTest::"+"WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void do2170SectionTest()
	{
		try{

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("5949529216");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0133928370");
			claimReq.setHc1_REQ_CLM_PROC_DT("2016-04-29");
			claimReq.setHc1_REQ_CLM_PROC_TM("21.09.31");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("M");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");
			claimResp.setHC1_COB_COB_835_PROC_IND("M");

			claimResp=cobln2121.getResultsCobln_Line_Flds(claimReqVo, claimResp);

			claimResp=profRed2160.do2160Section(claimReqVo, claimResp);
			claimResp=loadSumForReductService2170.do2170(claimResp,claimReqVo);

			System.out.println("do2160LogicTest::"+"WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/**
	 * Institutional test cases can be added here
	 */
	@Test
	public void do2140LogicTest()
	{
		try{

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");

			claimResp=Inst2140.do2140Section(claimReqVo, claimResp);
			System.out.println("WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void do2150LogicTest()
	{
		try{

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");

			claimResp=Inst2150.do2150Section(claimResp, claimReqVo);
			System.out.println("WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void do2170LogicTest()
	{
		try{

			claimReq.setHc1_REQ_CLM_INVN_CTL_NBR("1047907467");
			claimReq.setHc1_REQ_CLM_DRFT_NBR("0000007234");
			claimReq.setHc1_REQ_CLM_PROC_DT("2017-10-26");
			claimReq.setHc1_REQ_CLM_PROC_TM("14.03.23");
			claimReq.setHc1_REQ_CLM_TRANS_CD("00");
			claimReq.setHc1_REQ_CLM_COB_IND("");
			claimReq.setHc1_REQ_RESPONSE_CODE(BigDecimal.ZERO);
			claimReqVo.setReqClaimEntry(claimReq);
			claimReqVo.setLogId("10479074670000007234");

			claimResp.getMy_indicator().setPENNY_PROC_INDICATOR("Y");
			claimResp.setHC1_COB_INST_OR_PROF("I");
			claimResp.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_CD("01");
			claimResp.getMy_indicator().setDBKE2_ICN_SUFX_VERS_NBR("1");

			claimResp=Inst2170.do2170(claimResp, claimReqVo);
			System.out.println("WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}