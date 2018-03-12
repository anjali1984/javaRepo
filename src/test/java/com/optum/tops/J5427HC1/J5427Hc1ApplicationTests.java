package com.optum.tops.J5427HC1;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.optum.tops.J5427HC1.dao.D54DAO.FetchCoblnAmtsDao;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.COBLN2121Service;
import com.optum.tops.J5427HC1.services.COBLN2131Service;
import com.optum.tops.J5427HC1.services.COBONL2200Service;
import com.optum.tops.J5427HC1.services.CheckCOBClaim;
import com.optum.tops.J5427HC1.services.InstlReduction2140Service;
import com.optum.tops.J5427HC1.services.LoadCobLnLineAmtsService2150;
import com.optum.tops.J5427HC1.services.LoadSumForReductService2170;
import com.optum.tops.J5427HC1.services.OpsHcfaService;
import com.optum.tops.J5427HC1.services.RequestProcessor;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = J5427Hc1Application.class)
@TestPropertySource({"classpath:queries.properties","classpath:application.properties","classpath:log4j.properties"})

@ComponentScan(basePackages =
{
		"com.optum.tops.J5427HC1.services.CheckCOBClaim",
"com.optum.tops.J5427HC1.dao.D54DAO.CheckCOBDao" })
public class J5427Hc1ApplicationTests {

	@Autowired
	CheckCOBClaim checkCOBClaim;
	@Autowired
	OpsHcfaService opshcfacheck;
	@Autowired 
	COBLN2121Service cobln2121;	
	@Autowired 
	COBLN2131Service cobln2131;
	@Autowired
	ReqClaimEntryVO claimReqVo;
	@Autowired
	ReqClaimEntry claimReq;
	@Autowired
	V5427HC1 claimResp;
	@Autowired
	InstlReduction2140Service Inst2140;	
	@Autowired
	LoadCobLnLineAmtsService2150 Inst2150;
	@Autowired
	LoadSumForReductService2170 Inst2170;

	@Test
	public void contextLoads() {
	}


	@Test
	public void BigDecimalComputationTest()
	{
		RequestProcessor reqPrcr=new RequestProcessor();
		V5427HC1 currentClaim =new V5427HC1();
		currentClaim.setHC1_COB_INST_OR_PROF("I");
		currentClaim.setHC1_COB_COB_835_PROC_IND("M");
		currentClaim.setHC1_COB_NBR_LINES(1);
		currentClaim.setHC1_COB_835_COB_PRIM_IMPAC(BigDecimal.ZERO);
		System.out.println("ten "+currentClaim.getHC1_COB_835_COB_PRIM_IMPAC());

		BigDecimal compute=currentClaim.getHC1_COB_835_COB_PRIM_IMPAC().add(BigDecimal.TEN).subtract(BigDecimal.TEN);
		System.out.println("compute "+compute);


	}

	@Test
	public void COBClaimTest()

	{
		try {
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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
			System.out.println("COB_CLAIM_INDICATOR "+claimResp.getHC1_COB_COB_CLAIM_INDICATOR());
			System.out.println("DBKE2_ICN_SUFX_CD "+claimResp.getMy_indicator().getDBKE2_ICN_SUFX_CD());
			System.out.println("DBKE2_ICN_SUFX_VERS_NBR "+claimResp.getMy_indicator().getDBKE2_ICN_SUFX_VERS_NBR());


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void opshcClaimTest()

	{
		try {
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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
			System.out.println("OPS_HCFA_INDICATOR "+claimResp.getMy_indicator().getOPS_HCFA_INDICATOR());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getCsrOrigHdrDataTest()

	{
		try {
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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

			claimResp=opshcfacheck.get_CSR_ORIGHDR_DATA(claimReqVo, claimResp);
			System.out.println("HC1_ADJD_CLMSF_ORIGHDR_DATAAREA "+claimResp.getMy_indicator().getHC1_ADJD_CLMSF_ORIGHDR_DATAAREA().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getResultsCoblnLineFldsTest()
	{
		try{
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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
			System.out.println("WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// penny process needs to be  tested
	/*
	 * need different claim to test OimcCobAmtsDao.getData
	 */
	@Test
	public void do2131LogicTest()
	{
		try{
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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
			System.out.println("WS_LINE_REDUCTION_TABLE "+claimResp.getMy_indicator().getWS_LINE_REDUCTION_TABLE().toString());
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
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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
			claimReq.setReqPolNbr("742846");
			claimReq.setReqEEId("S994061000");
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
