package com.optum.tops.J5427HC1.dao.D54DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.optum.tops.J5427HC1.models.ClaimIndicatorValues;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.V5427HC1;

@Repository
@PropertySource("queries.properties")
public class CheckCOBDao {

	@Autowired
	private DataSource ds; 

	@Value("${hc1.cobClaimQuery}")
	private String cobClaimQuery; 

	public V5427HC1 am_i_COB_claim(ReqClaimEntry claim) {
		System.out.println("IN CHECKCOBDao ");
		/*query.setLength(0); // To ensure its cleared of previous query
		query.append("SELECT BKE2.ICN_SUFX_CD ");
		query.append(" ,BKE2.ICN_SUFX_VERS_NBR ");
		query.append(" ,BKE2.NEW_COB_LOGC_CD ");
		query.append(" ,BKE2.NEW_835_COB_PROC_IND ");
		query.append(" ,BKE2.FACL_OR_PROF_CD ");
		query.append(" ,BKE2.ALLW_AMT_DTRM_CD ");
		query.append(" ,BKE2.DIAG_B_NBR ");
		query.append(" ,BKE2.SUFX_TOT_CHRG_AMT ");
		query.append(" ,BKE2.EMC_IND "); //Used in conjunction with "BKE2.NEW_COB_LOGC_CD" to determine if this claim qualifies for Penny Process 
		query.append("FROM T5410DTA.ADJD_CLMSF_BLK_E2 BKE2 ");
		query.append(" ");
		query.append("INNER JOIN T5410DTA.ADJD_CLMSF_LN LNE ");
		query.append(" ON LNE.INVN_CTL_NBR = BKE2.INVN_CTL_NBR ");
		query.append(" AND LNE.ICN_SUFX_CD = BKE2.ICN_SUFX_CD ");
		query.append(" AND LNE.PROC_DT = BKE2.PROC_DT ");
		query.append(" AND LNE.PROC_TM = BKE2.PROC_TM ");
		query.append(" AND LNE.ICN_SUFX_VERS_NBR = BKE2.ICN_SUFX_VERS_NBR ");
		query.append("WHERE BKE2.INVN_CTL_NBR = ? ");
		query.append(" AND BKE2.PROC_DT = ? ");
		query.append(" AND BKE2.PROC_TM = ? ");
		query.append(" AND BKE2.ICN_SUFX_VERS_NBR = ");
		query.append(" ( SELECT MAX( BKE2A.ICN_SUFX_VERS_NBR) ");
		query.append(" FROM T5410DTA.ADJD_CLMSF_BLK_E2 BKE2A ");
		query.append(" WHERE BKE2A.INVN_CTL_NBR = BKE2.INVN_CTL_NBR ");
		query.append(" AND BKE2A.ICN_SUFX_CD = BKE2.ICN_SUFX_CD ");
		query.append(" AND BKE2A.PROC_DT = BKE2.PROC_DT ");
		query.append(" AND BKE2A.PROC_TM = BKE2.PROC_TM ");
		query.append(" AND BKE2A.ICN_SUFX_VERS_NBR = BKE2.ICN_SUFX_VERS_NBR");
		query.append(" ) ");
		query.append(" AND LNE.DFT_NBR = ? ");
		query.append("FETCH FIRST 1 ROWS ONLY ");
		query.append("WITH UR");*/

		Connection con = null;
		PreparedStatement ps = null;
		V5427HC1 individual_claim_response = new V5427HC1() ;
		ClaimIndicatorValues indicator_object = individual_claim_response.getMy_indicator() ; 

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(cobClaimQuery.toString());
			ps.setString(1, claim.getHc1_REQ_CLM_INVN_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, claim.getHc1_REQ_CLM_PROC_DT());
			ps.setString(3, claim.getHc1_REQ_CLM_PROC_TM());
			ps.setString(4, claim.getHc1_REQ_CLM_DRFT_NBR());
			ResultSet rs = ps.executeQuery();


			//rs.next();//Query returns only 1 row, so to get to the 1st row 
			if(!rs.next()){
				System.out.println("No data returned by query in CheckCOBDao");
			}else{
				// Set the indicator values in the indicator_object of V5427HC1
				// (Claim object)
				indicator_object.setDBKE2_ICN_SUFX_CD(rs.getString("ICN_SUFX_CD"));
				indicator_object.setDBKE2_ICN_SUFX_VERS_NBR(rs.getString("ICN_SUFX_VERS_NBR"));
				indicator_object.setDBKE2_COB_LOGC_CD(rs.getString("NEW_COB_LOGC_CD"));
				indicator_object.setDBKE2_835_COB_PROC_IND(rs.getString("NEW_835_COB_PROC_IND"));
				indicator_object.setDBKE2_SUFX_TOT_CHRG_AMT(rs.getBigDecimal("SUFX_TOT_CHRG_AMT"));
			}

			if (indicator_object.getDBKE2_COB_LOGC_CD().trim().equals("Y")
					&& (indicator_object.getDBKE2_835_COB_PROC_IND().trim().equals("Y")
							|| indicator_object.getDBKE2_835_COB_PROC_IND().trim().equals("M"))) {

				individual_claim_response.setHC1_COB_COB_CLAIM_INDICATOR("Y"); 
				individual_claim_response.setHC1_COB_COB_CALC_IND(rs.getString("NEW_COB_LOGC_CD"));
				individual_claim_response.setHC1_COB_COB_835_PROC_IND(rs.getString("NEW_835_COB_PROC_IND"));
				individual_claim_response.setHC1_COB_INST_OR_PROF(rs.getString("FACL_OR_PROF_CD"));
				individual_claim_response.setHC1_COB_ALLOW_AMT_IND(rs.getString("ALLW_AMT_DTRM_CD"));

				if(rs.getString("DIAG_B_NBR").trim().contains("S") || rs.getString("DIAG_B_NBR").trim().contains("P")){
					indicator_object.setNYSTATE_COB_CLAIM("Y");
					if(rs.getString("DIAG_B_NBR").trim().contains("S"))
						indicator_object.setNYSTATE_COB_CLAIM_PAIDTO("S");
					else if(rs.getString("DIAG_B_NBR").trim().contains("P"))
						indicator_object.setNYSTATE_COB_CLAIM_PAIDTO("P");
					else
						System.out.println("Invalid Value in CheckCOBDao for NYSTATE");
				}

				//Determine if claim qualifies for Penny Process, To be used later in 2000-Processing 
				if(rs.getString("NEW_COB_LOGC_CD").trim().equalsIgnoreCase("Y") && rs.getString("EMC_IND").trim().equalsIgnoreCase("Y")){
					indicator_object.setPENNY_PROC_INDICATOR("Y");
				}else{
					indicator_object.setPENNY_PROC_INDICATOR("N");
				}

			}//Not a COB Claim  
			else {
				individual_claim_response.setHC1_COB_COB_CLAIM_INDICATOR("N");
				individual_claim_response.setHC1_COB_NBR_LINES(0);
				individual_claim_response.setHC1_COB_COB_CALC_IND(rs.getString("NEW_COB_LOGC_CD"));
				individual_claim_response.setHC1_COB_COB_835_PROC_IND(rs.getString("NEW_835_COB_PROC_IND"));
				individual_claim_response.setHC1_COB_INST_OR_PROF(rs.getString("FACL_OR_PROF_CD"));
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
		return individual_claim_response;
	}
}
