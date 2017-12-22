package com.optum.tops.mse.rest2.j5427ct2.copybook;

import java.sql.SQLException;
import java.util.Arrays;

import com.optum.ndb.utilities.zOSUtilities;
import com.optum.tops.mse.rest2.j5427ct2.cobol.utils.CobolConversion;


public class Vycpidtl {

	//request return area
	//request area
	private  String REQUEST_CALLING_PROGRAM;
	private  String REQUEST_COPAY_ID;
	private  String REQUEST_EFF_DATE;
	private  String REQUEST_CAN_DATE;
	private  String REQUEST_TOPS_SERV_CD;
	private  String REQUEST_NTWK_IND1;
	private  String REQUEST_NTWK_IND2;
	private  String REQUEST_PLC_OF_SRVC;
	private  String REQUEST_CAUSE_CD;
	private  String REQUEST_BEN_LVL_CD;
	private  String REQUEST_PROC_TYPE_CD;
	private  String REQUEST_PROC_RANGE_FROM;
	private  String REQUEST_PROC_RANGE_TO;
	private  String REQUEST_REQ_AGE;
	private  String REQUEST_PROV_ORG_TYPE_CD;
	private  String REQUEST_SRVC_BEN_SET_CD_T1;
	private  String REQUEST_SRVC_BEN_SET_CD_INN;

	//return area
	private String RETURN_MATCH_FOUND_FLAG;


	private String RETURN_ZEROISE_T1_AMT_FLAG;
	private String RETURN_PCP_COPAY_AMT;
	private String RETURN_SPCL_COPAY_AMT;

	//changes for program
	private String REQUEST_FUNCTION_CODE;
	private String DSM_RETURN_CODE;
	private String DSM_ERR_TBL_ACTION;
	private String DSM_ERR_TBL_NAME;
	private String DSM_ERR_SECTN_NAME;
	private String DSM_SQLCODE;
	private String DSM_SQL_ERROR_MESSAGE;

	// for testing purposes only - change to actual test values
	//	public Vycpidtl(){
	//		this.REQUEST_CALLING_PROGRAM = "TESTTEST";
	//		this.REQUEST_COPAY_ID = 123456789;
	//		this.REQUEST_EFF_DATE = "2017-01-01";
	//		this.REQUEST_CAN_DATE = "9999-12-31";
	//		this.REQUEST_TOPS_SERV_CD = "1234567";
	//		this.REQUEST_NTWK_IND1 = "1";
	//		this.REQUEST_NTWK_IND2 = "2";
	//		this.REQUEST_PLC_OF_SRVC = "HO";
	//		this.REQUEST_CAUSE_CD = "C";
	//		this.REQUEST_BEN_LVL_CD = "T";
	//		this.REQUEST_PROC_TYPE_CD = "1";
	//		this.REQUEST_PROC_RANGE_FROM = "12345";
	//		this.REQUEST_PROC_RANGE_TO = "12345";
	//		this.REQUEST_REQ_AGE = 0023;
	//		this.REQUEST_PROV_ORG_TYPE_CD = "UHC";
	//		this.REQUEST_SRVC_BEN_SET_CD_T1 = "T";
	//		this.REQUEST_SRVC_BEN_SET_CD_INN = "O";
	//		this.REQUEST_FUNCTION_CODE = 1;
	//		return;
	//	}




	public void VycpidtlReq (String copybook2){
		//Feed the byte array 

		setREQUEST_CALLING_PROGRAM(copybook2.substring(0, 8));
		setREQUEST_COPAY_ID(copybook2.substring(8, 17));
		setREQUEST_EFF_DATE(copybook2.substring(17, 27));
		setREQUEST_CAN_DATE(copybook2.substring(27, 37));
		setREQUEST_TOPS_SERV_CD(copybook2.substring(37, 43));
		setREQUEST_NTWK_IND1(CobolConversion.spaceConversion(copybook2.substring(43, 44)));
		setREQUEST_NTWK_IND2(copybook2.substring(44, 45));
		setREQUEST_PLC_OF_SRVC(copybook2.substring(45, 47));
		setREQUEST_CAUSE_CD(copybook2.substring(47, 48));
		setREQUEST_BEN_LVL_CD(copybook2.substring(48, 49));
		setREQUEST_PROC_TYPE_CD(CobolConversion.spaceConversion(copybook2.substring(49, 50)));
		setREQUEST_PROC_RANGE_FROM(copybook2.substring(50, 55));
		setREQUEST_PROC_RANGE_TO(copybook2.substring(55, 60));
		setREQUEST_REQ_AGE(copybook2.substring(60, 64));
		setREQUEST_PROV_ORG_TYPE_CD(CobolConversion.spaceConversion(copybook2.substring(64, 67)));
		setREQUEST_SRVC_BEN_SET_CD_T1(copybook2.substring(67, 68));
		setREQUEST_SRVC_BEN_SET_CD_INN(copybook2.substring(68, 69));
		//Return area initialization
		setRETURN_MATCH_FOUND_FLAG(copybook2.substring(69,70));
		setRETURN_ZEROISE_T1_AMT_FLAG(copybook2.substring(70,71));	
		setRETURN_PCP_COPAY_AMT(CobolConversion.javaConversion(copybook2.substring(71, 78)));
		setRETURN_SPCL_COPAY_AMT(CobolConversion.javaConversion(copybook2.substring(78,85)));
		//
		setREQUEST_FUNCTION_CODE(CobolConversion.spaceConversion(copybook2.substring(85,87)));
		setDSM_RETURN_CODE(CobolConversion.spaceConversion(copybook2.substring(87, 90)));
		setDSM_ERR_TBL_ACTION(CobolConversion.spaceConversion(copybook2.substring(90,100)));
		setDSM_ERR_TBL_NAME(CobolConversion.spaceConversion(copybook2.substring(100,125)));
		setDSM_ERR_SECTN_NAME(CobolConversion.spaceConversion(copybook2.substring(125,159)));
		setDSM_SQLCODE(CobolConversion.spaceConversion(copybook2.substring(159, 162)));
		setDSM_SQL_ERROR_MESSAGE(CobolConversion.spaceConversion(copybook2.substring(162, 282)));
		System.out.println("162 to 282 "+CobolConversion.spaceConversion(copybook2.substring(162, 282)));


		//		this.REQUEST_CALLING_PROGRAM = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 0, 8));
		//		this.REQUEST_COPAY_ID = zOSUtilities.byteArrayToInt(Arrays.copyOfRange(copybook2, 8, 17));
		//		this.REQUEST_EFF_DATE = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 17, 27));
		//		this.REQUEST_CAN_DATE = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 27, 37));
		//		this.REQUEST_TOPS_SERV_CD = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 37, 43));
		//		this.REQUEST_NTWK_IND1 = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 43, 44));
		//		this.REQUEST_NTWK_IND2 = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 44, 45));
		//		this.REQUEST_PLC_OF_SRVC = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 45, 47));
		//		this.REQUEST_CAUSE_CD = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 47, 48));
		//		this.REQUEST_BEN_LVL_CD = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 48, 49));
		//		this.REQUEST_PROC_TYPE_CD = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 49, 50));
		//		this.REQUEST_PROC_RANGE_FROM = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 50, 55));
		//		this.REQUEST_PROC_RANGE_TO = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 55, 60));
		//		this.REQUEST_REQ_AGE = zOSUtilities.byteArrayToInt(Arrays.copyOfRange(copybook2, 60, 64));
		//		this.REQUEST_PROV_ORG_TYPE_CD = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 64, 67));
		//		this.REQUEST_SRVC_BEN_SET_CD_T1 = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 67, 68));
		//		this.REQUEST_SRVC_BEN_SET_CD_INN = zOSUtilities.byteArrayToString(Arrays.copyOfRange(copybook2, 68, 69));
		//		this.REQUEST_FUNCTION_CODE = zOSUtilities.byteArrayToInt(Arrays.copyOfRange(copybook2, 69, 70));
	}

	public String returnVycpidtl(){
		//make sure the return fields are correctly spaced.

		setRETURN_PCP_COPAY_AMT(CobolConversion.cobolConversion(getRETURN_PCP_COPAY_AMT(), 5,2));
		setRETURN_SPCL_COPAY_AMT(CobolConversion.cobolConversion(getRETURN_SPCL_COPAY_AMT(),5,2));

		StringBuffer sb = new StringBuffer();

		sb.append(getREQUEST_CALLING_PROGRAM());
		sb.append(getREQUEST_COPAY_ID());
		sb.append(getREQUEST_EFF_DATE());
		sb.append(getREQUEST_CAN_DATE());
		sb.append(getREQUEST_TOPS_SERV_CD());
		sb.append(getREQUEST_NTWK_IND1());
		sb.append(getREQUEST_NTWK_IND2());
		sb.append(getREQUEST_PLC_OF_SRVC());
		sb.append(getREQUEST_CAUSE_CD());
		sb.append(getREQUEST_BEN_LVL_CD());
		sb.append(getREQUEST_PROC_TYPE_CD());
		sb.append(getREQUEST_PROC_RANGE_FROM());
		sb.append(getREQUEST_PROC_RANGE_TO());
		sb.append(getREQUEST_REQ_AGE());
		sb.append(getREQUEST_PROV_ORG_TYPE_CD());
		sb.append(getREQUEST_SRVC_BEN_SET_CD_T1());
		sb.append(getREQUEST_SRVC_BEN_SET_CD_INN());
		sb.append(getRETURN_MATCH_FOUND_FLAG()); 
		sb.append(getRETURN_ZEROISE_T1_AMT_FLAG());
		sb.append(getRETURN_PCP_COPAY_AMT());
		sb.append(getRETURN_SPCL_COPAY_AMT());
		sb.append(getREQUEST_FUNCTION_CODE());
		sb.append(getDSM_RETURN_CODE());
		sb.append(getDSM_ERR_TBL_ACTION());
		sb.append(getDSM_ERR_TBL_NAME());
		sb.append(getDSM_ERR_SECTN_NAME());
		sb.append(getDSM_SQLCODE());
		sb.append(getDSM_SQL_ERROR_MESSAGE());
		
		System.out.println("getDSM_SQL_ERROR_MESSAGE() "+getDSM_SQL_ERROR_MESSAGE());

		return sb.toString();

	}

	//	public byte[] returnResults(byte[] copybookByteArray){
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getRETURN_MATCH_FOUND_FLAG(), 1) , 0, copybookByteArray, 70, 1);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getRETURN_ZEROISE_T1_AMT_FLAG(), 1) , 0, copybookByteArray, 71, 1);
	//		System.arraycopy (zOSUtilities.stringToBigInt(this.getRETURN_PCP_COPAY_AMT(), 7) , 0, copybookByteArray, 72, 7);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getRETURN_SPCL_COPAY_AMT(), 7) , 0, copybookByteArray, 79, 7);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getDSM_RETURN_CODE(), 7) , 0, copybookByteArray, 86, 3);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getDSM_ERR_TBL_ACTION(), 7) , 0, copybookByteArray, 89, 10);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getDSM_ERR_TBL_NAME(), 7) , 0, copybookByteArray, 99, 25);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getDSM_ERR_SECTN_NAME(), 7) , 0, copybookByteArray, 124, 34);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getDSM_SQLCODE(), 7) , 0, copybookByteArray, 158, 3);
	//		System.arraycopy (zOSUtilities.stringToByteArray(this.getDSM_SQL_ERROR_MESSAGE(), 7) , 0, copybookByteArray, 161, 120);
	//		return copybookByteArray;
	//		// Need to send back errors here as well
	//	}



	public String getDSM_RETURN_CODE() {
		return DSM_RETURN_CODE;
	}


	public String getDSM_SQLCODE() {
		return DSM_SQLCODE;
	}

	public void setDSM_SQLCODE(String dSM_SQLCODE) {
		DSM_SQLCODE = dSM_SQLCODE;
	}

	public String getDSM_SQL_ERROR_MESSAGE() {
		return DSM_SQL_ERROR_MESSAGE;
	}

	public void setDSM_SQL_ERROR_MESSAGE(SQLException e) {
		DSM_SQL_ERROR_MESSAGE = e.toString();
	}

	public void setREQUEST_CALLING_PROGRAM(String rEQUEST_CALLING_PROGRAM) {
		REQUEST_CALLING_PROGRAM = rEQUEST_CALLING_PROGRAM;
	}

	public void setREQUEST_COPAY_ID(String string) {
		REQUEST_COPAY_ID = string;
	}

	public void setREQUEST_EFF_DATE(String rEQUEST_EFF_DATE) {
		REQUEST_EFF_DATE = rEQUEST_EFF_DATE;
	}

	public void setREQUEST_CAN_DATE(String rEQUEST_CAN_DATE) {
		REQUEST_CAN_DATE = rEQUEST_CAN_DATE;
	}

	public void setREQUEST_TOPS_SERV_CD(String rEQUEST_TOPS_SERV_CD) {
		REQUEST_TOPS_SERV_CD = rEQUEST_TOPS_SERV_CD;
	}

	public void setREQUEST_PLC_OF_SRVC(String rEQUEST_PLC_OF_SRVC) {
		REQUEST_PLC_OF_SRVC = rEQUEST_PLC_OF_SRVC;
	}

	public void setREQUEST_CAUSE_CD(String rEQUEST_CAUSE_CD) {
		REQUEST_CAUSE_CD = rEQUEST_CAUSE_CD;
	}

	public void setREQUEST_BEN_LVL_CD(String rEQUEST_BEN_LVL_CD) {
		REQUEST_BEN_LVL_CD = rEQUEST_BEN_LVL_CD;
	}

	public void setREQUEST_REQ_AGE(String string) {
		REQUEST_REQ_AGE = string;
	}

	public void setREQUEST_PROV_ORG_TYPE_CD(String rEQUEST_PROV_ORG_TYPE_CD) {
		REQUEST_PROV_ORG_TYPE_CD = rEQUEST_PROV_ORG_TYPE_CD;
	}

	public void setREQUEST_SRVC_BEN_SET_CD_T1(String rEQUEST_SRVC_BEN_SET_CD_T1) {
		REQUEST_SRVC_BEN_SET_CD_T1 = rEQUEST_SRVC_BEN_SET_CD_T1;
	}

	public void setREQUEST_SRVC_BEN_SET_CD_INN(String rEQUEST_SRVC_BEN_SET_CD_INN) {
		REQUEST_SRVC_BEN_SET_CD_INN = rEQUEST_SRVC_BEN_SET_CD_INN;
	}

	public void setREQUEST_FUNCTION_CODE(String rEQUEST_FUNCTION_CODE) {
		REQUEST_FUNCTION_CODE = rEQUEST_FUNCTION_CODE;
	}

	public void setDSM_SQL_ERROR_MESSAGE(String dSM_SQL_ERROR_MESSAGE) {
		DSM_SQL_ERROR_MESSAGE = dSM_SQL_ERROR_MESSAGE;
	}

	public String getRETURN_SPCL_COPAY_AMT() {
		return RETURN_SPCL_COPAY_AMT;
	}

	public void setRETURN_SPCL_COPAY_AMT(String string) {
		RETURN_SPCL_COPAY_AMT = string;
	}

	public String getREQUEST_CALLING_PROGRAM() {
		return REQUEST_CALLING_PROGRAM;
	}

	public String getREQUEST_COPAY_ID() {
		return REQUEST_COPAY_ID;
	}

	public String getREQUEST_EFF_DATE() {
		return REQUEST_EFF_DATE;
	}

	public String getREQUEST_CAN_DATE() {
		return REQUEST_CAN_DATE;
	}

	public String getREQUEST_TOPS_SERV_CD() {
		return REQUEST_TOPS_SERV_CD;
	}

	public String getREQUEST_NTWK_IND1() {
		return REQUEST_NTWK_IND1;
	}

	public String getREQUEST_NTWK_IND2() {
		return REQUEST_NTWK_IND2;
	}

	public String getREQUEST_PLC_OF_SRVC() {
		return REQUEST_PLC_OF_SRVC;
	}

	public String getREQUEST_CAUSE_CD() {
		return REQUEST_CAUSE_CD;
	}

	public String getREQUEST_BEN_LVL_CD() {
		return REQUEST_BEN_LVL_CD;
	}

	public String getREQUEST_PROC_TYPE_CD() {
		return REQUEST_PROC_TYPE_CD;
	}

	public String getREQUEST_PROC_RANGE_FROM() {
		return REQUEST_PROC_RANGE_FROM;
	}

	public String getREQUEST_PROC_RANGE_TO() {
		return REQUEST_PROC_RANGE_TO;
	}
	public void setREQUEST_PROC_TYPE_CD(String rEQUEST_PROC_TYPE_CD) {
		REQUEST_PROC_TYPE_CD = rEQUEST_PROC_TYPE_CD;
	}

	public void setREQUEST_PROC_RANGE_FROM(String rEQUEST_PROC_RANGE_FROM) {
		REQUEST_PROC_RANGE_FROM = rEQUEST_PROC_RANGE_FROM;
	}

	public void setREQUEST_PROC_RANGE_TO(String rEQUEST_PROC_RANGE_TO) {
		REQUEST_PROC_RANGE_TO = rEQUEST_PROC_RANGE_TO;
	}

	public String getREQUEST_REQ_AGE() {
		return REQUEST_REQ_AGE;
	}

	public String getREQUEST_PROV_ORG_TYPE_CD() {
		return REQUEST_PROV_ORG_TYPE_CD;
	}

	public String getREQUEST_SRVC_BEN_SET_CD_T1() {
		return REQUEST_SRVC_BEN_SET_CD_T1;
	}

	public String getREQUEST_SRVC_BEN_SET_CD_INN() {
		return REQUEST_SRVC_BEN_SET_CD_INN;
	}
	public String getREQUEST_FUNCTION_CODE() {
		return REQUEST_FUNCTION_CODE;
	}
	public String getRETURN_PCP_COPAY_AMT() {
		return RETURN_PCP_COPAY_AMT;
	}

	public void setRETURN_PCP_COPAY_AMT(String pCP_COPAY_AMT) {
		RETURN_PCP_COPAY_AMT = pCP_COPAY_AMT;
	}

	public String getDSM_ERR_TBL_ACTION() {
		return DSM_ERR_TBL_ACTION;
	}

	public void setDSM_ERR_TBL_ACTION(String dSM_ERR_TBL_ACTION) {
		DSM_ERR_TBL_ACTION = dSM_ERR_TBL_ACTION;
	}

	public String getDSM_ERR_TBL_NAME() {
		return DSM_ERR_TBL_NAME;
	}

	public void setDSM_ERR_TBL_NAME(String dSM_ERR_TBL_NAME) {
		DSM_ERR_TBL_NAME = dSM_ERR_TBL_NAME;
	}

	public String getDSM_ERR_SECTN_NAME() {
		return DSM_ERR_SECTN_NAME;
	}

	public void setDSM_ERR_SECTN_NAME(String dSM_ERR_SECTN_NAME) {
		DSM_ERR_SECTN_NAME = dSM_ERR_SECTN_NAME;
	}

	public String getRETURN_MATCH_FOUND_FLAG() {
		return RETURN_MATCH_FOUND_FLAG;
	}

	public void setRETURN_MATCH_FOUND_FLAG(String rETURN_MATCH_FOUND_FLAG) {
		RETURN_MATCH_FOUND_FLAG = rETURN_MATCH_FOUND_FLAG;
	}

	public String getRETURN_ZEROISE_T1_AMT_FLAG() {
		return RETURN_ZEROISE_T1_AMT_FLAG;
	}

	public void setRETURN_ZEROISE_T1_AMT_FLAG(String rETURN_ZEROISE_T1_AMT_FLAG) {
		RETURN_ZEROISE_T1_AMT_FLAG = rETURN_ZEROISE_T1_AMT_FLAG;
	}

	public void setDSM_RETURN_CODE(String dSM_RETURN_CODE) {
		DSM_RETURN_CODE = dSM_RETURN_CODE;
	}

	public void setREQUEST_NTWK_IND1(String rEQUEST_NTWK_IND1) {
		REQUEST_NTWK_IND1 = rEQUEST_NTWK_IND1;
	}

	public void setREQUEST_NTWK_IND2(String rEQUEST_NTWK_IND2) {
		REQUEST_NTWK_IND2 = rEQUEST_NTWK_IND2;
	}

}
