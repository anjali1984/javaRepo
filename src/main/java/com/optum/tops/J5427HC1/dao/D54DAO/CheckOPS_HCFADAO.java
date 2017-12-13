package com.optum.tops.J5427HC1.dao.D54DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.optum.tops.J5427HC1.models.ADJD_CLMSF_ORIGHDR_LINE;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

@Repository
public class CheckOPS_HCFADAO {

	@Autowired
	private DataSource ds;

	static StringBuffer query = new StringBuffer();

	public V5427HC1 am_i_OPS_HCFA(ReqClaimEntry incoming_claim, V5427HC1 claim) {
		System.out.println("In CheckOPS_HCFADAO ");
		query.setLength(0); // To ensure its cleared of previous query
		query.append("SELECT ");
		query.append("* ");
		query.append("FROM D5406TOP.ADJD_CLMSF_LN LNE ");
		query.append("WHERE LNE.INVN_CTL_NBR = ? ");
		query.append("AND LNE.ICN_SUFX_CD = ? ");
		query.append("AND LNE.PROC_DT = ? ");
		query.append("AND LNE.PROC_TM = ? ");
		query.append("AND LNE.ICN_SUFX_VERS_NBR = ? ");
		query.append("AND LNE.PMT_SVC_CD  = 'OPS' ");
		query.append("AND LNE.ORIG_SRVC_PLC_CD = 'AS' ");

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(query.toString());
			ps.setString(1, incoming_claim.getHc1_REQ_CLM_INVN_CTL_NBR());
			ps.setString(2, claim.getMy_indicator().getDBKE2_ICN_SUFX_CD());
			ps.setString(3, incoming_claim.getHc1_REQ_CLM_PROC_DT());
			ps.setString(4, incoming_claim.getHc1_REQ_CLM_PROC_TM());
			ps.setString(5, claim.getMy_indicator().getDBKE2_ICN_SUFX_VERS_NBR());

			ResultSet rs = ps.executeQuery();

			// this returns false if the cursor is not before the first record
			// or if there are no rows in the ResultSet.
			if (!rs.isBeforeFirst()) {
				System.out.println("No data returned by CheckOPS_HCFA DAO");
				claim.getMy_indicator().setOPS_HCFA_INDICATOR("N");
			}else{
				//If the query returned at least 1 record mark it as a OS_HCFA Claim 
				claim.getMy_indicator().setOPS_HCFA_INDICATOR("Y");
				
			}

		} catch (SQLException e) {
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

		return claim;
	}
	
	//Gets the rows from ADJD_CLMSF_ORIGHDR, puts at max 150 of them into the IndicatorObject of the claim to be returned 
	//Corresponds to 2002-GET-ORIGHDR-DETAILS-SECT SECTION. 
	public V5427HC1 get_CSR_ORIGHDR(ReqClaimEntry incoming_claim, V5427HC1 claim) {
		query.setLength(0);
		query.append("SELECT   HDR.RVNU_CD ");
		query.append(", HDR.PROC_CD ");
		query.append(", HDR.CHRG_AMT ");
		query.append(", HDR.LN_CORR_ID ");
		query.append(", HDR.LN_NBR ");
		query.append(", HDR.ORIG_PL_OF_SRVC_CD ");
		query.append(", HDR.UB92_RVNU_CD ");
		query.append(", HDR.UB92_CHRG_AMT ");
		query.append(", HDR.UB92_NOT_COV_AMT ");
		query.append(", HDR.UB92_PROC_CD ");
		query.append(", HDR.HCFA_SRVC_PLC_CD");
		query.append(", HDR.UB92_OVR_CD ");
		query.append(", HDR.UB92_RMK_CD ");
		query.append(", HDR.ORIG_UB92_RMRK_CD ");
		query.append("FROM  D5406TOP.ADJD_CLMSF_ORIGHDR   	HDR");
		query.append("WHERE  HDR.INVN_CTL_NBR     	= ? ");
		query.append("AND  HDR.ICN_SUFX_CD        = ? ");
		query.append("AND  HDR.PROC_DT            = ? ");
		query.append("AND  HDR.PROC_TM            = ? ");
		query.append("AND  HDR.ICN_SUFX_VERS_NBR  = ? ");
		query.append("ORDER BY  HDR.LN_CORR_ID ASC "); 
		query.append("FOR FETCH ONLY ") ; 
		query.append(" WITH UR "); 
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(query.toString());
			ps.setString(1, incoming_claim.getHc1_REQ_CLM_INVN_CTL_NBR());
			ps.setString(2, claim.getMy_indicator().getDBKE2_ICN_SUFX_CD());
			ps.setString(3, incoming_claim.getHc1_REQ_CLM_PROC_DT());
			ps.setString(4, incoming_claim.getHc1_REQ_CLM_PROC_TM());
			ps.setString(5, claim.getMy_indicator().getDBKE2_ICN_SUFX_VERS_NBR()); 
			
			ResultSet rs = ps.executeQuery(); 
			int i = 0 ; 
			ADJD_CLMSF_ORIGHDR_LINE hdr_line ; 
			List<ADJD_CLMSF_ORIGHDR_LINE> Hdr_data = claim.getMy_indicator().getHC1_ADJD_CLMSF_ORIGHDR_DATAAREA() ; 
			
			while(rs.next() && i < 150 ){
				hdr_line = new ADJD_CLMSF_ORIGHDR_LINE() ;  
				hdr_line.setRVNU_CD(rs.getString("HDR.RVNU_CD"));
				hdr_line.setPROC_CD(rs.getString("HDR.PROC_CD"));
				hdr_line.setCHRG_AMT(rs.getBigDecimal("HDR.CHRG_AMT")); 
				hdr_line.setLN_CORR_ID(rs.getInt("HDR.LN_CORR_ID"));
				hdr_line.setLN_NBR(rs.getInt("HDR.LN_NBR")); 
				hdr_line.setORIG_PL_OF_SRVC_CD(rs.getString("HDR.ORIG_PL_OF_SRVC_CD")); 
				hdr_line.setUB92_RVNU_CD(rs.getString("HDR.UB92_RVNU_CD")); 
				hdr_line.setUB92_CHRG_AMT(rs.getBigDecimal("HDR.UB92_CHRG_AMT")); 
				hdr_line.setUB92_NOT_COV_AMT(rs.getBigDecimal("HDR.UB92_NOT_COV_AMT")); 
				hdr_line.setUB92_PROC_CD(rs.getString("HDR.UB92_PROC_CD")); 
				hdr_line.setHCFA_SRVC_PLC_CD(rs.getString("HDR.HCFA_SRVC_PLC_CD"));
				hdr_line.setUB92_OVR_CD(rs.getString("HDR.UB92_OVR_CD"));
				hdr_line.setUB92_RMK_CD(rs.getString("HDR.UB92_RMK_CD"));
				hdr_line.setORIG_UB92_RMRK_CD(rs.getString("HDR.ORIG_UB92_RMRK_CD"));
				Hdr_data.add(hdr_line); 
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
		return claim ; 
	}
}
