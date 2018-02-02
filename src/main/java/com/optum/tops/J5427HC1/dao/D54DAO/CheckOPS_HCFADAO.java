package com.optum.tops.J5427HC1.dao.D54DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.optum.tops.J5427HC1.models.ADJD_CLMSF_ORIGHDR_LINE;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

@Repository
@PropertySource("queries.properties")
public class CheckOPS_HCFADAO {

	@Autowired
	private DataSource ds;
	
	@Value("${hc1.amI_OPS_HCFA_Query}")
	private String amI_OPS_HCFA_Query;
	
	@Value("${hc1.get_CSR_ORIGHDR_Query}")
	private String get_CSR_ORIGHDR_Query;
	
	static StringBuilder query = new StringBuilder();
	

	public V5427HC1 am_i_OPS_HCFA(ReqClaimEntry incoming_claim, V5427HC1 claim) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(amI_OPS_HCFA_Query.toString());
			ps.setString(1, incoming_claim.getHc1_REQ_CLM_INVN_CTL_NBR());
			ps.setString(2, claim.getMy_indicator().getDBKE2_ICN_SUFX_CD());
			ps.setString(3, incoming_claim.getHc1_REQ_CLM_PROC_DT());
			ps.setString(4, incoming_claim.getHc1_REQ_CLM_PROC_TM());
			ps.setString(5, claim.getMy_indicator().getDBKE2_ICN_SUFX_VERS_NBR());

			ResultSet rs = ps.executeQuery();

			// this returns false if the cursor is not before the first record
			// or if there are no rows in the ResultSet.
			if(!rs.next()){
				//System.out.println("No data returned by query in CheckOPS_HCFADAO setOPS_HCFA_INDICATOR to \"N\" ");
				claim.getMy_indicator().setOPS_HCFA_INDICATOR("N");
			}
			else{
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
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(get_CSR_ORIGHDR_Query.toString());
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
				hdr_line.setRVNU_CD(rs.getString("RVNU_CD"));
				hdr_line.setPROC_CD(rs.getString("PROC_CD"));
				hdr_line.setCHRG_AMT(rs.getBigDecimal("CHRG_AMT")); 
				hdr_line.setLN_CORR_ID(rs.getInt("LN_CORR_ID"));
				hdr_line.setLN_NBR(rs.getInt("LN_NBR")); 
				hdr_line.setORIG_PL_OF_SRVC_CD(rs.getString("ORIG_PL_OF_SRVC_CD")); 
				hdr_line.setUB92_RVNU_CD(rs.getString("UB92_RVNU_CD")); 
				hdr_line.setUB92_CHRG_AMT(rs.getBigDecimal("UB92_CHRG_AMT")); 
				hdr_line.setUB92_NOT_COV_AMT(rs.getBigDecimal("UB92_NOT_COV_AMT")); 
				hdr_line.setUB92_PROC_CD(rs.getString("UB92_PROC_CD")); 
				hdr_line.setHCFA_SRVC_PLC_CD(rs.getString("HCFA_SRVC_PLC_CD"));
				hdr_line.setUB92_OVR_CD(rs.getString("UB92_OVR_CD"));
				hdr_line.setUB92_RMK_CD(rs.getString("UB92_RMK_CD"));
				hdr_line.setORIG_UB92_RMRK_CD(rs.getString("ORIG_UB92_RMRK_CD"));
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
