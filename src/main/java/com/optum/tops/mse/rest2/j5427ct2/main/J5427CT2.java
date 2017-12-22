package com.optum.tops.mse.rest2.j5427ct2.main;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cics.server.CicsException;
import com.optum.tops.mse.rest2.j5427ct2.copybook.Vycpidtl;
import com.optum.tops.mse.rest2.j5427ct2.dao.IDao;
import com.optum.tops.mse.rest2.j5427ct2.dao.imp.DaoImp;
import com.optum.tops.mse.rest2.j5427ct2.dao.imp.Db2Connect;

@CrossOrigin()
@RestController
public class J5427CT2 {


	private static SimpleDateFormat sdfStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
	String copybook;

	@Autowired
	DaoImp dao;


	@RequestMapping(value="/welcome/{request}",method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> welcome(@PathVariable String request) throws CicsException{

		String  copybook =request;

		/*
		 * sample request string and the URL,all white spaces replaced with & in the request string
		 */
		/*http://localhost:8080/Rest2J5427CT2/welcome/BBCTI0030000000442017-04-062017-04-0697000&IBOF0&999999999990054&&&TNYY000100{000150{&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&";
		String  copybook = "BBCTI0030000000442017-04-062017-04-0697000 IBOF0 999999999990054   TNYY000100{000150{	                                                                                                                                                                                                    ";
		 */
		J5427CT2 jc2=new J5427CT2();

		final String returnData  = jc2.startMain(copybook,dao);
		ResponseEntity<String> resp = new ResponseEntity<String>(returnData, HttpStatus.OK);
		return resp;

	}

	//@RequestMapping(value="/welcome",method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE })
	//public ResponseEntity<String> startMain(String args,DaoImp dao) throws CicsException
	public String startMain(String copybook,DaoImp dao) throws CicsException
	{
		long startTime = System.currentTimeMillis();


		//copybook = "BBCTI0030000000442017-04-062017-04-0697000 IBOF0 999999999990054   TNYY000100{000150{	                                                                                                                                                                                                    ";
		//System.out.println("request data " + copybook);

		/**
		 * anjali:needs review
		 */
		Vycpidtl sp1 = new Vycpidtl();
		sp1.VycpidtlReq(copybook);
		//Vycpidtl sp1 = new Vycpidtl(copybook);

		sp1.setRETURN_MATCH_FOUND_FLAG("N");

		/**
		 * setting funccd for testing only
		 */
		sp1.setREQUEST_FUNCTION_CODE("02");
		/**
		 *  Perform query based on function cd value 
		 */
		switch (sp1.getREQUEST_FUNCTION_CODE()) 
		{

		case "01":
			// if the function code is 1
			//			System.out.println("This is the function code number 1 ");

			dao.benLvl1(sp1);
			//			System.out.println("This is the flag after the First pass through " + sp1.getRETURN_MATCH_FOUND_FLAG());
			if (sp1.getREQUEST_NTWK_IND1().equals("T") && sp1.getREQUEST_NTWK_IND2().equals("T")) {
				if (!sp1.getRETURN_MATCH_FOUND_FLAG().equals("Y")) {
					sp1.setRETURN_ZEROISE_T1_AMT_FLAG("Y");
					sp1.setREQUEST_NTWK_IND1("I");
					sp1.setREQUEST_NTWK_IND2("B");
					dao.benLvl1(sp1);
					//					System.out.println("This is the flag after the Second pass through " + sp1.getRETURN_MATCH_FOUND_FLAG());
					if (!sp1.getRETURN_MATCH_FOUND_FLAG().equals("Y")) {
						sp1.setREQUEST_NTWK_IND1("T");
						sp1.setREQUEST_NTWK_IND2("T");
					}
				} else {
					sp1.setRETURN_ZEROISE_T1_AMT_FLAG("N");
				}
			}
			if (!sp1.getRETURN_MATCH_FOUND_FLAG().equals("Y") && !sp1.getREQUEST_PROC_RANGE_FROM().equals("99999")) {
				sp1.setREQUEST_PROC_RANGE_FROM("99999");
				sp1.setREQUEST_PROC_RANGE_TO("99999");
				sp1.setREQUEST_PROC_TYPE_CD("9");
				dao.defaultlogic.equals("N");
				dao.benLvl1(sp1);
				//				System.out.println("This is the flag after the Third Pass through " + sp1.getRETURN_MATCH_FOUND_FLAG());
			}
			break;
		case "02":
			dao.causLvl1(sp1);
			//System.out.println("This is the flag after the First pass through " + sp1.getRETURN_MATCH_FOUND_FLAG());
			if (sp1.getREQUEST_NTWK_IND1().equals("T") && sp1.getREQUEST_NTWK_IND2().equals("T")) {
				if (!sp1.getRETURN_MATCH_FOUND_FLAG().equals("Y")) {
					sp1.setRETURN_ZEROISE_T1_AMT_FLAG("Y");
					sp1.setREQUEST_NTWK_IND1("I");
					sp1.setREQUEST_NTWK_IND2("B");
					dao.causLvl1(sp1);
					//System.out.println("This is the flag after the Second pass through " + sp1.getRETURN_MATCH_FOUND_FLAG());
					if (!sp1.getRETURN_MATCH_FOUND_FLAG().equals("Y")) {
						sp1.setREQUEST_NTWK_IND1("T");
						sp1.setREQUEST_NTWK_IND2("T");
					}
				} else {
					sp1.setRETURN_ZEROISE_T1_AMT_FLAG("N");
				}
			}
			if (!sp1.getRETURN_MATCH_FOUND_FLAG().equals("Y") && !"99999".equals(sp1.getREQUEST_PROC_RANGE_FROM())) {
				sp1.setREQUEST_PROC_RANGE_FROM("99999");
				sp1.setREQUEST_PROC_RANGE_TO("99999");
				sp1.setREQUEST_PROC_TYPE_CD("9");
				dao.defaultlogic.equals("N");
				dao.causLvl1(sp1);
				//System.out.println("This is the flag after the Third Pass through " + sp1.getRETURN_MATCH_FOUND_FLAG());
			}

			break;

		}



		final String returnData  = sp1.returnVycpidtl();
		System.out.println("returnData "+returnData);
		System.out.println("Completed in " + (System.currentTimeMillis() - startTime) + " ms");
		/*final String returnData  = "test";
		 * ResponseEntity<String> resp = new ResponseEntity<String>(returnData, HttpStatus.OK);
		return resp;*/
		return returnData;
	} 



	private Vycpidtl mapRequestArea(String copybook2) {
		/**
		 * anjali:needs review
		 */
		Vycpidtl sp1 = new Vycpidtl();
		sp1.VycpidtlReq(copybook2);
		//Vycpidtl sp1 = new Vycpidtl(copybook);

		return sp1;
	}

}
