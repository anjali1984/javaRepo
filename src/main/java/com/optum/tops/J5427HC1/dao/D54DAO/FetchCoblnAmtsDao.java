package com.optum.tops.J5427HC1.dao.D54DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.optum.tops.J5427HC1.models.COBLN_LINE_FLDS;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;

@Repository
public class FetchCoblnAmtsDao {

	@Autowired
	private DataSource ds; 

	static StringBuffer query = new StringBuffer();
	
	public List<COBLN_LINE_FLDS> getCoblnFlds(ReqClaimEntry incoming_Claim, String suffix_cd){
		query.setLength(0); // To ensure its cleared of previous query
		query.append("SELECT LN.LN_ID ");
		query.append(" ,CB.RPTG_LN_ALLW_AMT ");
		//query.append(" ,IFNULL(CB.RPTG_LN_ALLW_AMT,0) ");
		query.append(" ,LN.SRVC_CD ");
		query.append(" ,LN.PMT_SVC_CD ");
		query.append(" ,LN.RMRK_CD ");
		query.append(" ,LN.OVR_CD ");
		query.append(" ,LN.AUTH_PROC_CD ");
		query.append(" ,LN.CHRG_AMT  "); 
		query.append(" ,LN.NC_AMT ");
		query.append(" ,LN.FST_DT ");
		query.append(" ,LN.LST_SRVC_DT ");
		query.append(" ,LN.OI_PD_LN_AMT ");
		query.append(" ,LN.MEDC_L04_AMT ");
		query.append(" ,LN.ALLW_AMT_DTRM_CD  ");
		query.append(" ,LN.LN_PROV_WRITE_OFF ");
		query.append(" ,LN.MM_DED_AMT ");
		query.append(" ,LN.NYSCHG_DED_MM_AMT");
		query.append(" ,LN.ORIG_LN_CORR_ID  ");
		query.append(" ,LN.ORIG_LN_CHRG_AMT  ");
//		query.append(" FROM  T5410DBA.ADJD_CLMSFLN_COB   	CB ");
//		query.append(" ,ADJD_CLMSF_LN    		  	LN ");
		query.append(" FROM  T5410DBA.ADJD_CLMSF_LN          LN ") ;
		query.append(" LEFT OUTER JOIN T5410DBA.ADJD_CLMSFLN_COB   CB ") ;
		query.append("ON  CB.INVN_CTL_NBR      = LN.INVN_CTL_NBR ") ;
		query.append("AND  CB.ICN_SUFX_CD       = LN.ICN_SUFX_CD  ") ;
		query.append("AND  CB.PROC_DT           = LN.PROC_DT ") ;
		query.append("AND  CB.PROC_TM           = LN.PROC_TM  ") ;
		query.append("AND  CB.ICN_SUFX_VERS_NBR = LN.ICN_SUFX_VERS_NBR ") ;
		query.append("AND  CB.LN_ID             = LN.LN_ID ") ;
		query.append("WHERE  LN.INVN_CTL_NBR      = ? "); 
		query.append("AND  LN.ICN_SUFX_CD       = ? ");
		query.append("AND  LN.DFT_NBR           = ? ");
		query.append("AND  LN.PROC_TM           = ? ");
		query.append("AND  LN.PROC_DT           = ? ");
		query.append(" ORDER BY LN.LN_ID ASC "); // ADDED so that the index out of bounds is solved for arraylist
		query.append("FOR FETCH ONLY ");
		
		Connection con = null;
		PreparedStatement ps = null;
		List<COBLN_LINE_FLDS> query_results = new ArrayList<COBLN_LINE_FLDS>(); 
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(query.toString());
			ps.setString(1, incoming_Claim.getHc1_REQ_CLM_INVN_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, suffix_cd);
			ps.setString(3, incoming_Claim.getHc1_REQ_CLM_DRFT_NBR());
			ps.setString(4, incoming_Claim.getHc1_REQ_CLM_PROC_TM());
			ps.setString(5, incoming_Claim.getHc1_REQ_CLM_PROC_DT());
			ResultSet rs = ps.executeQuery();
			
			System.out.println("RESULT SET LINE IDS ORDER");
			while(rs.next()){
				COBLN_LINE_FLDS returned_record = new COBLN_LINE_FLDS(); 
				returned_record.setLN_ID(rs.getInt("LN_ID"));
				System.out.println(rs.getInt("LN_ID"));
				returned_record.setRPTG_LN_ALLW_AMT(rs.getBigDecimal("RPTG_LN_ALLW_AMT"));
				returned_record.setLN_SRVC_CD(rs.getString("SRVC_CD"));
				returned_record.setLN_PMT_SVC_CD(rs.getString("PMT_SVC_CD"));
				returned_record.setLN_RMRK_CD(rs.getString("RMRK_CD"));
				returned_record.setLN_OVR_CD(rs.getString("OVR_CD"));
				returned_record.setLN_AUTH_PROC_CD(rs.getString("AUTH_PROC_CD"));
				returned_record.setLN_CHRG_AMT(rs.getBigDecimal("CHRG_AMT"));
				returned_record.setLN_NC_AMT(rs.getBigDecimal("NC_AMT"));
				returned_record.setLN_FST_DT(rs.getString("FST_DT"));
				returned_record.setLN_LST_SRVC_DT(rs.getString("LST_SRVC_DT"));
				returned_record.setLN_OI_PD_LN_AMT(rs.getBigDecimal("OI_PD_LN_AMT"));
				
				if(rs.getBigDecimal("MEDC_L04_AMT").compareTo(BigDecimal.ZERO) < 0){
					returned_record.setLN_MEDC_L04_AMT(BigDecimal.ZERO);
				}else{
					returned_record.setLN_MEDC_L04_AMT(rs.getBigDecimal("MEDC_L04_AMT"));
				}
				
				returned_record.setLN_ALLW_AMT_DTRM_CD(rs.getString("ALLW_AMT_DTRM_CD"));
				returned_record.setLN_LN_PROV_WRITE_OFF(rs.getBigDecimal("LN_PROV_WRITE_OFF"));
				returned_record.setLN_MM_DED_AMT(rs.getBigDecimal("MM_DED_AMT"));
				returned_record.setLN_NYSCHG_DED_MM_AMT(rs.getBigDecimal("NYSCHG_DED_MM_AMT"));
				returned_record.setLN_ORIG_LN_CORR_ID(rs.getInt("ORIG_LN_CORR_ID"));
				returned_record.setLN_ORIG_LN_CHRG_AMT(rs.getBigDecimal("ORIG_LN_CHRG_AMT"));
				
				query_results.add(returned_record); 
				
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
		return query_results;
		
	}
}
