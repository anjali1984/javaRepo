package com.optum.tops.J5427HC1.JP835RED;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.Ret835ClmErr;
import com.optum.tops.JP835RED.models.Ret835ClmRarc;
import com.optum.tops.JP835RED.models.Ret835ClmRed;
import com.optum.tops.JP835RED.models.Ret835LineLvl;
import com.optum.tops.JP835RED.models.Ret835LnRarcTbl;
import com.optum.tops.JP835RED.models.Ret835PrcLvl;
import com.optum.tops.JP835RED.models.Ret835Reduct;
import com.optum.tops.JP835RED.models.Ub92_835AdjdSvc;

@Repository
@PropertySource("classpath:queries.properties")
public class processing7700 {

	@Autowired
	private DataSource ds;

	@Value("${hc1.7701.query}")
	private String hc17701Query;

	@Value("${hc1.7702.query}")
	private String hc17702Query;

	@Value("${hc1.7703.query}")
	private String hc17703Query;

	@Value("${hc1.7704.query}")
	private String hc17704Query;

	@Value("${hc1.7705.query}")
	private String hc17705Query;

	@Value("${hc1.7706.query}")
	private String hc17706Query;

	@Value("${hc1.7708.query}")
	private String hc17708Query;
	private  Logger logger=Logger.getLogger("genLogger");

	public List<Ret835ClmRed> do7701(JP54RedRequest req, String logId){
		/*
		 * read data from ADJD_CLMSF_RARC_CD  table and move them to CB variables
		 */


		String location="J5427HC1.JP835RED.processing7700.do7701(JP54RedRequest, String)";

		Connection con = null ; 
		PreparedStatement ps = null;
		List<Ret835ClmRed> Clm835RedTbl = new ArrayList<Ret835ClmRed>(); 

		try{
			con = ds.getConnection();
			ps = con.prepareStatement(hc17701Query.toString());
			ps.setString(1, req.getRED_INV_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ps.setString(5, req.getRED_INV_CTL_NBR());
			ps.setString(6, req.getRED_ICN_SUFX_CD());
			System.out.println("sufx "+req.getRED_ICN_SUFX_CD());
			ps.setString(7, req.getRED_PROC_DT());
			ps.setString(8, req.getRED_PROC_TM());
			ResultSet rs = ps.executeQuery();	

			int runner= 0 ; 
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for Ret835ClmRed Table").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{
				do{

					Ret835ClmRed obj = new Ret835ClmRed(); 
					obj.setCLM_RD_CATGY_ID(rs.getString("PD_AMT_RDUC_CATGY_ID"));
					obj.setCLM_RD_PD_AMT(rs.getBigDecimal("FACL_PD_AMT_RDUC_AMT"));
					System.out.println("obj.getCLM_RD_PD_AMT "+obj.getCLM_RD_PD_AMT());
					obj.setCLM_RD_GRP_CD(rs.getString("PD_AMT_RDUC_GRP_CD"));
					obj.setCLM_RD_CARC_CD(rs.getString("PD_AMT_RDUC_CARC_CD"));
					obj.setCLM_RD_RARC_CD(rs.getString("PD_AMT_RDUC_RARC_CD"));
					Clm835RedTbl.add(obj);
					runner++; 
				}while(runner < 10 && rs.next() );
			}

		}catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
				e.printStackTrace();
			}
		}
		return Clm835RedTbl;


	}
	public List<Ret835ClmRarc> do7702(JP54RedRequest req, String logId){
		/*
		 * read data from adjd_clmsf_facl_pd_rduc table and move them to CB variables
		 */
		String location="J5427HC1.JP835RED.processing7700.do7702(JP54RedRequest, String)";

		Connection con = null ; 
		PreparedStatement ps = null;
		List<Ret835ClmRarc> ClmRarcTbl = new ArrayList<Ret835ClmRarc>();

		try{
			con = ds.getConnection();
			ps = con.prepareStatement(hc17702Query.toString());
			ps.setString(1, req.getRED_INV_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ps.setString(5,req.getRED_INV_CTL_NBR());
			ps.setString(6,req.getRED_ICN_SUFX_CD());
			ps.setString(7, req.getRED_PROC_DT());
			ps.setString(8, req.getRED_PROC_TM());
			ResultSet rs = ps.executeQuery();
			int runner = 0 ; 
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for Ret835ClmRarc Table").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{
				do{
					Ret835ClmRarc obj = new Ret835ClmRarc(); 
					obj.setCLM_RC_RARC_CD(rs.getString("CLM_RARC_CD"));
					obj.setCLM_RC_RMRK_CD(rs.getString("CLM_RMRK_CD"));
					ClmRarcTbl.add(obj);
					runner++ ; 
				}while( runner < 3 && rs.next());
			}

		}catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
				e.printStackTrace();
			}
		}

		return ClmRarcTbl; 
	}
	public List<Ret835ClmErr> do7703(JP54RedRequest req, String logId){
		/*
		 * read data from ADJD_CLMSF_RARC_CD  table and move them to CB variables
		 */

		String location="J5427HC1.JP835RED.processing7700.do7703(JP54RedRequest, String)";

		Connection con = null ; 
		PreparedStatement ps = null;
		List<Ret835ClmErr> ClmErrTbl = new ArrayList<Ret835ClmErr>();

		try{
			con = ds.getConnection();
			ps = con.prepareStatement(hc17703Query.toString());
			ps.setString(1, req.getRED_INV_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ps.setString(5,req.getRED_INV_CTL_NBR());
			ps.setString(6,req.getRED_ICN_SUFX_CD());
			ps.setString(7, req.getRED_PROC_DT());
			ps.setString(8, req.getRED_PROC_TM());
			ResultSet rs = ps.executeQuery();
			int counter = 0 ; 
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for Ret835ClmErr Table").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{
				do{
					Ret835ClmErr temp = new Ret835ClmErr();
					temp.setCLM_ERR_CD(rs.getString("CLM_ERR_CD"));
					temp.setCLM_ERR_TYP_CD(rs.getString("CLM_LN_ERR_TYP_CD"));
					ClmErrTbl.add(temp);
					counter++; 
				}while(counter < 3 && rs.next());

			}

		}catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
				e.printStackTrace();
			}
		}

		return ClmErrTbl;

	}

	/**
	 * read data from ADJD_CLMSFLN_PD_AMT_RDUC  table and moved them to Ret835Reduct object 
	 * @param req
	 * @param logId 
	 * @return
	 */
	public List<Ret835Reduct> do7704(JP54RedRequest req, String logId){

		String location="J5427HC1.JP835RED.processing7700.do7704(JP54RedRequest, String)";

		Connection con = null ; 
		PreparedStatement ps = null;
		List <Ret835Reduct> ClmRed=new ArrayList<Ret835Reduct>();

		try{
			con = ds.getConnection();
			ps = con.prepareStatement(hc17704Query.toString());
			ps.setString(1,req.getRED_INV_CTL_NBR());
			ps.setString(2,req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ps.setString(5,req.getRED_INV_CTL_NBR());
			ps.setString(6,req.getRED_ICN_SUFX_CD());
			ps.setString(7, req.getRED_PROC_DT());
			ps.setString(8, req.getRED_PROC_TM());

			ResultSet rs = ps.executeQuery();

			int counter=0;
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for Ret835Reduct Table").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{
				do
				{

					Ret835Reduct temp=new Ret835Reduct();
					temp.setRET_835_RD_SVC_ID(rs.getInt("LN_ID"));
					temp.setRET_835_RD_CATGY_ID(rs.getInt("PD_AMT_RDUC_CATGY_ID"));
					temp.setRET_835_RD_PD_AMT(rs.getBigDecimal("PD_AMT_RDUC_AMT"));
					System.out.println("temp.setRET_835_RD_PD_AMT "+temp.getRET_835_RD_PD_AMT());
					if(req.getRED_835_TRAN_TYPE().equals("87"))
					{
						temp.setRET_835_RD_PD_AMT(temp.getRET_835_RD_PD_AMT().multiply((BigDecimal.ONE).negate()));
					}
					temp.setRET_835_RD_GRP_ID(rs.getString("PD_AMT_RDUC_GRP_CD"));
					temp.setRET_835_RD_CARC_CD(rs.getString("PD_AMT_RDUC_CARC_CD"));
					temp.setRET_835_RD_RARC_CD(rs.getString("PD_AMT_RDUC_RARC_CD"));
					temp.setRET_835_RD_REV_CD(rs.getInt("RVNU_CD"));
					temp.setRET_835_RD_PROC_CD(rs.getString("PROC_CD"));
					temp.setRET_835_RD_PROC_TYP_CD(rs.getString("PROC_TYP_CD"));
					temp.setRET_835_RD_REV_ID(rs.getInt("ORIG_HDR_SEQ_NBR"));
					ClmRed.add(temp);
				}while(counter<150 && rs.next());
			}

			return ClmRed;

		}catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
				e.printStackTrace();
			}
		}

		return ClmRed;

	}

	/**
	 * read data from ADJD_CLMSFLN_RARC_CD  table and move them to CB variables
	 * @param req
	 * @param logId 
	 * @return
	 */
	public Ret835LineLvl[] do7705(JP54RedRequest req, String logId){
		String location="J5427HC1.JP835RED.processing7700.do7705(JP54RedRequest, String)";

		Connection con = null ; 
		PreparedStatement ps = null;
		/*		List<Ret835LineLvl> ret835LineLvlList = new ArrayList<Ret835LineLvl>();
		 */			
		Ret835LineLvl[] ret835LineLvl=new Ret835LineLvl[7];
		/**
		 * initialize array ret835LineLvl
		 */
		for(int i=0;i<7;i++)
		{
			ret835LineLvl[i]=new Ret835LineLvl();
			for (int j=0;j<3;j++)
			{
				ret835LineLvl[i].getRet835LnRarcTbl()[j]=new Ret835LnRarcTbl();	
			}
		}

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(hc17705Query);

			ps.setString(1, req.getRED_INV_CTL_NBR());
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ps.setString(5, req.getRED_INV_CTL_NBR());
			ps.setString(6, req.getRED_ICN_SUFX_CD());
			ps.setString(7, req.getRED_PROC_DT());
			ps.setString(8,req.getRED_PROC_TM());

			ResultSet  rs =ps.executeQuery();
			int ws_ln=0;
			int ws_sub=0;

			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for Ret835LineLvl Table").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{
				do//makes the first row as current ,then second the current and so on 
				{
					if(rs.isFirst())
					{
						ws_ln=rs.getBigDecimal("LN_ID").intValue()-1;
						ws_sub=1;
					}else
					{
						if(rs.getBigDecimal("LN_ID").intValue()==ws_ln)
						{
							ws_sub++;
						}
						else
						{
							ws_sub=0;
							ret835LineLvl[ws_ln].setRET_835_20LN_SVC_ID(rs.getBigDecimal("LN_ID"));

						}

					}
					ws_ln=rs.getBigDecimal("LN_ID").intValue();

					ret835LineLvl[ws_ln].setRET_835_20LN_SVC_ID(rs.getBigDecimal("LN_ID"));
					ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_RARC_CD(rs.getString("CLM_LN_RARC_CD"));
					ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_RMRK_CD(rs.getString("CLM_LN_RMRK_CD"));
					ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_REV_CD(rs.getBigDecimal("RVNU_CD"));
					ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_PROC_CD(rs.getString("PROC_CD"));
					ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_PROC_TYP_CD(rs.getString("PROC_TYP_CD"));
					ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_REV_ID(rs.getBigDecimal("ORIG_HDR_SEQ_NBR"));
				}while (rs.next());
			}
			return ret835LineLvl;
		} catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
				e.printStackTrace();
			}
		}
		return ret835LineLvl;

	}
	/**
	 * read data from ADJD_CLMSFLN_ERR_CD table and move them to CB variables
	 * @param logId 
	 * @param JP54RedRequest req
	 * @return
	 */
	public List<Ret835PrcLvl> do7706(JP54RedRequest req, String logId){
		String location="J5427HC1.JP835RED.processing7700.do7706(JP54RedRequest, String)";

		Connection con=null;
		PreparedStatement ps=null;
		List<Ret835PrcLvl> ret835PrcLvlList=new ArrayList<Ret835PrcLvl>();

		try {
			con=ds.getConnection();
			ps=con.prepareStatement(hc17706Query);
			ps.setString(1, req.getRED_INV_CTL_NBR());
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ps.setString(5, req.getRED_INV_CTL_NBR());
			ps.setString(6, req.getRED_ICN_SUFX_CD());
			ps.setString(7, req.getRED_PROC_DT());
			ps.setString(8, req.getRED_PROC_TM());

			ResultSet rs=ps.executeQuery();
			int cntr=1;
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for Ret835PrcLvl Table").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{
				do
				{
					Ret835PrcLvl ret835PrcLvl=new Ret835PrcLvl();
					ret835PrcLvl.setRET_835_ERR_CD(rs.getString("CLM_LN_ERR_CD"));
					ret835PrcLvl.setRET_835_ERR_PROC_TYP_CD(rs.getString("CLM_LN_ERR_TYP_CD"));
					ret835PrcLvl.setRET_835_ERR_SVC_ID(rs.getBigDecimal("LN_ID"));
					ret835PrcLvl.setRET_835_ERR_REV_CD(rs.getBigDecimal("RVNU_CD"));	
					ret835PrcLvl.setRET_835_ERR_PROC_CD(rs.getString("PROC_CD"));
					ret835PrcLvl.setRET_835_ERR_PROC_TYP_CD(rs.getString("PROC_TYP_CD"));
					ret835PrcLvl.setRET_835_ERR_REV_ID(rs.getBigDecimal("ORIG_HDR_SEQ_NBR"));
					ret835PrcLvlList.add(ret835PrcLvl);
					cntr++;
				}while (cntr<=60 && rs.next());

			}
			return ret835PrcLvlList;

		} catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
				e.printStackTrace();
			}
		}

		return ret835PrcLvlList;
	}
	public List<Ub92_835AdjdSvc> do7708(JP54RedRequest req, String logId){
		/*
		 * EXTRACT 835 PRORATED DATA AND POPULATE THE INTO RETURN AREA  
		 */

		String location="J5427HC1.JP835RED.processing7700.do7708(JP54RedRequest, String)";

		Connection con = null ; 
		PreparedStatement ps = null;
		List<Ub92_835AdjdSvc> retUB92_835_AdjdSvcInfo = new ArrayList<Ub92_835AdjdSvc>();

		try{
			con = ds.getConnection();
			ps = con.prepareStatement(hc17708Query.toString());
			ps.setString(1, req.getRED_INV_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ps.setString(5,req.getRED_INV_CTL_NBR());
			ps.setString(6,req.getRED_ICN_SUFX_CD());
			ps.setString(7, req.getRED_PROC_DT());
			ps.setString(8, req.getRED_PROC_TM());
			ResultSet rs = ps.executeQuery(); 
			int counter = 0 ; 
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for Ub92_835AdjdSvc Table").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{
				do{
					Ub92_835AdjdSvc temp = new Ub92_835AdjdSvc(); 
					temp.setUB92_835_ADJD_PAID_AMT(rs.getBigDecimal("RPT_835_PD_AMT"));
					temp.setUB92_835_ADJD_REV_CD(rs.getString("RPT_835_RVNU_CD"));
					temp.setUB92_835_ADJD_PROC_CD(rs.getString("RPT_835_PROC_CD"));
					temp.setUB92_835_ADJD_PROC_MOD_1_CD(rs.getString("RPT_835_PROC_MOD_1_CD"));
					temp.setUB92_835_ADJD_PROC_MOD_2_CD(rs.getString("RPT_835_PROC_MOD_2_CD"));
					temp.setUB92_835_ADJD_PROC_MOD_3_CD(rs.getString("RPT_835_PROC_MOD_3_CD"));
					temp.setUB92_835_ADJD_PROC_MOD_4_CD(rs.getString("RPT_835_PROC_MOD_4_CD"));
					temp.setORIG_HDR_SEQ_NBR(rs.getInt("LN_NBR"));
					temp.setORIG_HDR_PROC_TYPE(rs.getString("PROC_TYP_CD"));
					temp.setORIG_HDR_LINE_CORR_ID(rs.getInt("LN_CORR_ID"));
					temp.setUB92_RVNU_CD(rs.getString("UB92_RVNU_CD"));
					temp.setUB92_CHRG_AMT(rs.getBigDecimal("UB92_CHRG_AMT"));
					temp.setUB92_ALLW_AMT(rs.getBigDecimal("UB92_ALLW_AMT"));

					retUB92_835_AdjdSvcInfo.add(temp);

					counter++;
				}			while(counter < 60 && rs.next());
			}


			//PROCESS THE RESULTS 
		}catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(logId).concat("]"),e);
				e.printStackTrace();
			}
		}

		return retUB92_835_AdjdSvcInfo;
	}
}
