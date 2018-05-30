package com.optum.tops.J5427HC1.dao.D54DAO.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.optum.tops.J5427HC1.dao.D54DAO.ICheckCOBDao;
import com.optum.tops.J5427HC1.models.ClaimIndicatorValues;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

@Repository
@PropertySource("classpath:queries.properties")
public class CheckCOBDao implements ICheckCOBDao {

	@Autowired
	private DataSource ds; 

	@Value("${hc1.cobClaimQuery}")
	private String cobClaimQuery; 
	Logger logger=Logger.getLogger("genLogger");

	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.dao.D54DAO.ICheckCOBDao#am_i_COB_claim(com.optum.tops.J5427HC1.models.ReqClaimEntryVO)
	 */
	@Override
	public V5427HC1 amICobClaim(ReqClaimEntryVO individual_claim2) {



		String location="J5427HC1.dao.D54DAO.CheckCOBDao.am_i_COB_claim(ReqClaimEntryVO)";
		Connection con = null;
		PreparedStatement ps = null;
		V5427HC1 individual_claim_response = new V5427HC1() ;
		ClaimIndicatorValues indicator_object = individual_claim_response.getMy_indicator() ; 

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(cobClaimQuery.toString());
			ps.setString(1, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_INVN_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_DT());
			ps.setString(3, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_TM());
			ps.setString(4, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_DRFT_NBR());

			ResultSet rs = ps.executeQuery();


			//rs.next();//Query returns only 1 row, so to get to the 1st row 
			if(!rs.next()){
				individual_claim_response.setError("No CobLines Returned");
				logger.info(location.concat(" Empty Resultset").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));
				//System.out.println("No data returned by query in CheckCOBDao");
			}else{
				// Set the indicator values in the indicator_object of V5427HC1
				// (Claim object)
				indicator_object.setDBKE2_ICN_SUFX_CD(rs.getString("ICN_SUFX_CD"));
				indicator_object.setDBKE2_ICN_SUFX_VERS_NBR(rs.getString("ICN_SUFX_VERS_NBR"));
				indicator_object.setDBKE2_COB_LOGC_CD(rs.getString("NEW_COB_LOGC_CD"));
				indicator_object.setDBKE2_835_COB_PROC_IND(rs.getString("NEW_835_COB_PROC_IND"));
				indicator_object.setDBKE2_SUFX_TOT_CHRG_AMT(rs.getBigDecimal("SUFX_TOT_CHRG_AMT"));
				System.out.println("SUFX_TOT_CHRG_AMT "+indicator_object.getDBKE2_SUFX_TOT_CHRG_AMT());


				//COB CLAIM setters.
				if (indicator_object.getDBKE2_COB_LOGC_CD().trim().equals("Y")
						&& (indicator_object.getDBKE2_835_COB_PROC_IND().trim().equals("Y")
								|| indicator_object.getDBKE2_835_COB_PROC_IND().trim().equals("M"))) {

					individual_claim_response.setHC1_COB_COB_CLAIM_INDICATOR("Y"); 
					individual_claim_response.setHC1_COB_NEW_COB_CALC_IND(rs.getString("NEW_COB_LOGC_CD"));
					individual_claim_response.setHC1_COB_NEW_COB_835_PROC_IND(rs.getString("NEW_835_COB_PROC_IND"));
					individual_claim_response.setHC1_COB_INST_OR_PROF(rs.getString("FACL_OR_PROF_CD"));
					individual_claim_response.setHC1_COB_ALLOW_AMT_IND(rs.getString("ALLW_AMT_DTRM_CD"));

					if(rs.getString("DIAG_B_NBR").trim().contains("S") || rs.getString("DIAG_B_NBR").trim().contains("P")){
						indicator_object.setNYSTATE_COB_CLAIM("Y");
						if(rs.getString("DIAG_B_NBR").trim().contains("S"))
							indicator_object.setNYSTATE_COB_CLAIM_PAIDTO("S");
						else if(rs.getString("DIAG_B_NBR").trim().contains("P"))
							indicator_object.setNYSTATE_COB_CLAIM_PAIDTO("P");
						else
							logger.info(location.concat(" Invalid Value in CheckCOBDao for NYSTATE").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));
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
					individual_claim_response.setHC1_COB_NEW_COB_CALC_IND(rs.getString("NEW_COB_LOGC_CD"));
					individual_claim_response.setHC1_COB_NEW_COB_835_PROC_IND(rs.getString("NEW_835_COB_PROC_IND"));
					individual_claim_response.setHC1_COB_INST_OR_PROF(rs.getString("FACL_OR_PROF_CD"));
				}
			}
		} catch (SQLException e) {
			//logger.info(location.concat("No Rows returned").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"),e);
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
