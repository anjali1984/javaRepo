package com.optum.tops.J5427HC1.JP835RED;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.optum.tops.JP835RED.models.JP54RedRequest;
import com.optum.tops.JP835RED.models.Ret835ClmErr;
import com.optum.tops.JP835RED.models.Ret835ClmRarc;
import com.optum.tops.JP835RED.models.Ret835ClmRed;
import com.optum.tops.JP835RED.models.Ret835LineLvl;
import com.optum.tops.JP835RED.models.Ret835PrcLvl;
import com.optum.tops.JP835RED.models.Ret835Reduct;
import com.optum.tops.JP835RED.models.Ub92_835AdjdSvc;

@Repository
@PropertySource("queries.properties")
public class processing7700 {

	@Autowired
	private DataSource ds;
	
	static StringBuffer query = new StringBuffer();
	
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

	//Common method used by all other methods.
	public int maxSufxVersNbr(JP54RedRequest req){
		query.setLength(0);
		query.append("SELECT MAX(ICN_SUFX_VERS_NBR) "); 
		query.append("FROM T5410DTA.ADJD_CLMSF_FACL_PD_RDUC C ");
		query.append("WHERE C.INVN_CTL_NBR  = ? ");
		query.append("AND C.ICN_SUFX_CD   =  ? ");
		query.append("AND C.PROC_DT       =  ? ");
		query.append("AND C.PROC_TM       =  ? "); 

		Connection con = null ; 
		PreparedStatement ps = null;
		int result = -1 ; //Default so if this query doesnt return a valid answer
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(query.toString());
			ps = con.prepareStatement(query.toString());
			ps.setString(1, req.getRED_INV_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ResultSet rs = ps.executeQuery();	
			result=rs.getInt(1);

		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result; 
	}

	public List<Ret835ClmRed> do7701(JP54RedRequest req){
		/*
		 * read data from ADJD_CLMSF_RARC_CD  table and move them to CB variables
		 */
		query.setLength(0);
		query.append("SELECT   PD_AMT_RDUC_CATGY_ID ");
		query.append(",FACL_PD_AMT_RDUC_AMT ");
		query.append(",PD_AMT_RDUC_GRP_CD ");
		query.append(",PD_AMT_RDUC_CARC_CD ");
		query.append(",PD_AMT_RDUC_RARC_CD ");
		query.append("FROM T5410DTA.ADJD_CLMSF_FACL_PD_RDUC ");
		query.append("WHERE  INVN_CTL_NBR        = ? ");
		query.append("AND ICN_SUFX_CD           =  ? ");
		query.append("AND PROC_DT               =  ? ");
		query.append("AND PROC_TM               =  ? ");
		query.append("AND ICN_SUFX_VERS_NBR     = ");
		query.append(maxSufxVersNbr(req) + " "); 

		Connection con = null ; 
		PreparedStatement ps = null;
		List<Ret835ClmRed> Clm835RedTbl = new ArrayList<Ret835ClmRed>(); 

		try{
			con = ds.getConnection();
			ps = con.prepareStatement(query.toString());
			ps.setString(1, req.getRED_INV_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, req.getRED_ICN_SUFX_CD());
			ps.setString(3, req.getRED_PROC_DT());
			ps.setString(4, req.getRED_PROC_TM());
			ResultSet rs = ps.executeQuery();	

			int runner= 0 ; 
			while(runner < 10 && rs.next() ){
				Ret835ClmRed obj = new Ret835ClmRed(); 
				obj.setCLM_RD_CATGY_ID(rs.getString("PD_AMT_RDUC_CATGY_ID"));
				obj.setCLM_RD_PD_AMT(rs.getBigDecimal("FACL_PD_AMT_RDUC_AMT"));
				obj.setCLM_RD_GRP_CD(rs.getString("PD_AMT_RDUC_GRP_CD"));
				obj.setCLM_RD_CARC_CD(rs.getString("PD_AMT_RDUC_CARC_CD"));
				obj.setCLM_RD_RARC_CD(rs.getString("PD_AMT_RDUC_RARC_CD"));
				Clm835RedTbl.add(obj);
				runner++; 
			}

		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Clm835RedTbl;


	}
	public List<Ret835ClmRarc> do7702(JP54RedRequest req){
		/*
		 * read data from adjd_clmsf_facl_pd_rduc table and move them to CB variables
		 */
		/*query.setLength(0);
		query.append("SELECT   CLM_RARC_CD ");
		query.append(",CLM_RMRK_CD ");
		query.append("FROM T5410DTA.ADJD_CLMSF_RARC_CD ");
		query.append("WHERE  INVN_CTL_NBR       =  ? ");
		query.append("AND ICN_SUFX_CD           = ? ");
		query.append("AND PROC_DT               = ? ");
		query.append("AND PROC_TM               = ? ");
		query.append("AND ICN_SUFX_VERS_NBR     = ");
		query.append(maxSufxVersNbr(req) + " "); */

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
			while( runner < 3 && rs.next()){
				Ret835ClmRarc obj = new Ret835ClmRarc(); 
				obj.setCLM_RC_RARC_CD(rs.getString("CLM_RARC_CD"));
				obj.setCLM_RC_RMRK_CD(rs.getString("CLM_RMRK_CD"));
				ClmRarcTbl.add(obj);
				runner++ ; 
			}

		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ClmRarcTbl; 
	}
	public List<Ret835ClmErr> do7703(JP54RedRequest req){
		/*
		 * read data from ADJD_CLMSF_RARC_CD  table and move them to CB variables
		 */
		/*query.setLength(0);
		query.append("SELECT   CLM_ERR_CD ");
		query.append(",CLM_LN_ERR_TYP_CD ");
		query.append("FROM T5410DTA.ADJD_CLMSF_ERR_CD ");
		query.append("WHERE  INVN_CTL_NBR       = ? ");
		query.append("AND ICN_SUFX_CD           = ? ");
		query.append(" AND PROC_DT              = ? ");
		query.append("AND PROC_TM               = ? ");
		query.append("AND ICN_SUFX_VERS_NBR     = "); 
		query.append(maxSufxVersNbr(req) + " ");
		*/

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
			while(counter < 3 && rs.next()){
				Ret835ClmErr temp = new Ret835ClmErr();
				temp.setCLM_ERR_CD(rs.getString("CLM_ERR_CD"));
				temp.setCLM_ERR_TYP_CD(rs.getString("CLM_LN_ERR_TYP_CD"));
				ClmErrTbl.add(temp);
				counter++; 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ClmErrTbl;

	}
	
	/**
	 * read data from ADJD_CLMSFLN_PD_AMT_RDUC  table and moved them to Ret835Reduct object 
	 * @param req
	 * @return
	 */
	public List<Ret835Reduct> do7704(JP54RedRequest req){

		/*query.setLength(0);
		query.append("SELECT   PD_AMT_RDUC_CATGY_ID ");
		query.append(",PD_AMT_RDUC_AMT ");
		query.append(",PD_AMT_RDUC_GRP_CD ");
		query.append(",PD_AMT_RDUC_CARC_CD ");
		query.append(",PD_AMT_RDUC_RARC_CD ");
		query.append(",LN_ID ");
		query.append(",RVNU_CD ");
		query.append(",PROC_CD ");
		query.append(",PROC_TYP_CD ");
		query.append(",ORIG_HDR_SEQ_NBR ");
		query.append(" FROM ADJD_CLMSFLN_PD_AMT_RDUC ");
		query.append("WHERE  INVN_CTL_NBR          = ? ");
		query.append("AND ICN_SUFX_CD           = ? ");
		query.append("AND PROC_DT               = ? ");
		query.append("AND PROC_TM               = ? ");
		query.append("AND ICN_SUFX_VERS_NBR     = ");
		query.append("(SELECT MAX(ICN_SUFX_VERS_NBR) ");
		query.append("FROM ADJD_CLMSFLN_PD_AMT_RDUC C ");
		query.append("WHERE C.INVN_CTL_NBR  = ? ");
		query.append("AND C.ICN_SUFX_CD   = ? ");
		query.append("AND C.PROC_DT       = ? ");
		query.append("AND C.PROC_TM       = ?) ");
		*/
		
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
			while(counter<150 && rs.next())
			{
				Ret835Reduct temp=new Ret835Reduct();
				temp.setRET_835_RD_SVC_ID(rs.getInt("LN_ID"));
				temp.setRET_835_RD_CATGY_ID(rs.getInt("PD_AMT_RDUC_CATGY_ID"));
				temp.setRET_835_RD_PD_AMT(rs.getBigDecimal("PD_AMT_RDUC_AMT"));

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
			}

			return ClmRed;

		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		/*SELECT   PD_AMT_RDUC_CATGY_ID 
		,PD_AMT_RDUC_AMT 
		,PD_AMT_RDUC_GRP_CD 
		,PD_AMT_RDUC_CARC_CD 
		,PD_AMT_RDUC_RARC_CD 
		,LN_ID 
		,RVNU_CD 
		,PROC_CD 
		,PROC_TYP_CD 
		,ORIG_HDR_SEQ_NBR 
		 FROM ADJD_CLMSFLN_PD_AMT_RDUC 
		WHERE  INVN_CTL_NBR          = ? 
				AND ICN_SUFX_CD           = ? 
				AND PROC_DT               = ? 
				AND PROC_TM               = ? 
				AND ICN_SUFX_VERS_NBR     = 
				(SELECT MAX(ICN_SUFX_VERS_NBR) 
				FROM ADJD_CLMSFLN_PD_AMT_RDUC C 
				WHERE C.INVN_CTL_NBR  = ? 
				AND C.ICN_SUFX_CD   = ? 
				AND C.PROC_DT       = ? 
				AND C.PROC_TM       = ?) */
		return ClmRed;

	}
	
	/**
	 * read data from ADJD_CLMSFLN_RARC_CD  table and move them to CB variables
	 * @param req
	 * @return
	 */
	public Ret835LineLvl[] do7705(JP54RedRequest req){

		Connection con = null ; 
		PreparedStatement ps = null;
/*		List<Ret835LineLvl> ret835LineLvlList = new ArrayList<Ret835LineLvl>();
*/			
		Ret835LineLvl[] ret835LineLvl=new Ret835LineLvl[7];

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

			/*if (rs.isBeforeFirst()) {
				rs.next();
				ws_ln=rs.getBigDecimal("LN_ID").intValue();
				System.out.println("ws_ln "+ws_ln);
				ws_sub=1;
			}
*/
			while (rs.next())//makes the first row as current ,then second the current and so on 
			{
				/*anjali:needs review*/
				if(rs.isFirst())
				{
					ws_ln=rs.getBigDecimal("LN_ID").intValue()-1;
					System.out.println("ws_ln "+ws_ln);
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
				System.out.println("ws_ln "+ws_ln);

				ret835LineLvl[ws_ln].setRET_835_20LN_SVC_ID(rs.getBigDecimal("LN_ID"));
				//what if therse no element in tbl for ws_ln index
				ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_RARC_CD(rs.getString("CLM_LN_RARC_CD"));
				ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_RMRK_CD(rs.getString("CLM_LN_RMRK_CD"));
				ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_REV_CD(rs.getBigDecimal("RVNU_CD"));
				ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_PROC_CD(rs.getString("PROC_CD"));
				ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_PROC_TYP_CD(rs.getString("PROC_TYP_CD"));
				ret835LineLvl[ws_ln].getRet835LnRarcTbl()[ws_sub].setRET_835_LN_REV_ID(rs.getBigDecimal("ORIG_HDR_SEQ_NBR"));
				
			}
			return ret835LineLvl;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret835LineLvl;

	}
	/**
	 * read data from ADJD_CLMSFLN_ERR_CD table and move them to CB variables
	 * @param JP54RedRequest req
	 * @return
	 */
	public List<Ret835PrcLvl> do7706(JP54RedRequest req){
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
			while (cntr<=60 && rs.next())
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
			}
			return ret835PrcLvlList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ret835PrcLvlList;
	}
	public List<Ub92_835AdjdSvc> do7708(JP54RedRequest req){
		/*
		 * EXTRACT 835 PRORATED DATA AND POPULATE THE INTO RETURN AREA  
		 */
		/*query.setLength(0);
		query.append("SELECT   RPT_835_PD_AMT ");
		query.append(",RPT_835_RVNU_CD ");
		query.append(",RPT_835_PROC_CD ");
		query.append(",RPT_835_PROC_MOD_1_CD ");
		query.append(",RPT_835_PROC_MOD_2_CD ");
		query.append(",RPT_835_PROC_MOD_3_CD ");
		query.append(",RPT_835_PROC_MOD_4_CD ");
		query.append(",LN_NBR ");
		query.append(",PROC_TYP_CD ");
		query.append(",LN_CORR_ID ");
		query.append(",UB92_RVNU_CD ");
		query.append(",UB92_CHRG_AMT ");
		query.append(",UB92_ALLW_AMT ");
		query.append("FROM T5410DTA.ADJD_CLMSF_ORIGHDR ");
		query.append("WHERE INVN_CTL_NBR       = ? ");
		query.append("AND ICN_SUFX_CD          = ? ");
		query.append("AND PROC_DT              = ? ");
		query.append("AND PROC_TM              = ? ");
		query.append("AND ICN_SUFX_VERS_NBR    = ");
		query.append(maxSufxVersNbr(req) + " "); 
		*/
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
			while(counter < 60 && rs.next()){
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
			}

			//PROCESS THE RESULTS 
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return retUB92_835_AdjdSvcInfo;
	}
}
