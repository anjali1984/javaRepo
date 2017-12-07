package com.optum.tops.J5427HC1.dao.D54DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

@Repository
public class CheckOPS_HCFADAO {

	@Autowired
	private DataSource ds;

	static StringBuffer query = new StringBuffer();

	public V5427HC1 am_i_OPS_HCFA(ReqClaimEntry incoming_claim, V5427HC1 claim) {
		query.setLength(0); // To ensure its cleared of previous query
		query.append("SELECT ");
		query.append("* ");
		query.append("FROM ADJD_CLMSF_LN LNE ");
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
			ps.setString(1, incoming_claim.getHC1_REQ_CLM_INVN_CTL_NBR());
			ps.setString(2, claim.getMy_indicator().getDBKE2_ICN_SUFX_CD());
			ps.setString(3, incoming_claim.getHC1_REQ_CLM_PROC_DT());
			ps.setString(4, incoming_claim.getHC1_REQ_CLM_PROC_TM());
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
}
