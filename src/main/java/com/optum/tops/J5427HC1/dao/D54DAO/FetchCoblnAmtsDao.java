package com.optum.tops.J5427HC1.dao.D54DAO;

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

import com.optum.tops.J5427HC1.models.COBLN_LINE_FLDS;
import com.optum.tops.J5427HC1.models.ReqClaimEntry;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;

@Repository
@PropertySource("queries.properties")
public class FetchCoblnAmtsDao {

	@Autowired
	private DataSource ds;
	
	@Value("${hc1.getCoblnFlds_Query}")
	private String getCoblnFlds_Query;
	
	private  Logger logger=Logger.getLogger("genLogger");

	public List<COBLN_LINE_FLDS> getCoblnFlds(ReqClaimEntryVO individual_claim2, String suffix_cd){
	
		
		String location="J5427HC1.dao.D54DAO.FetchCoblnAmtsDao.getCoblnFlds(ReqClaimEntryVO, String)";
		Connection con = null;
		PreparedStatement ps = null;
		List<COBLN_LINE_FLDS> query_results = new ArrayList<COBLN_LINE_FLDS>(); 
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(getCoblnFlds_Query.toString());
			ps.setString(1, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_INVN_CTL_NBR()); // Sets the icn variable in the query
			ps.setString(2, suffix_cd);
			ps.setString(3, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_DRFT_NBR());
			ps.setString(4, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_TM());
			ps.setString(5, individual_claim2.getReqClaimEntry().getHc1_REQ_CLM_PROC_DT());
		
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset;No Cob lines returned").concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

			}else{

				do{
			
				COBLN_LINE_FLDS returned_record = new COBLN_LINE_FLDS(); 
				returned_record.setLN_ID(rs.getInt("LN_ID"));
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
				
			}while(rs.next());
			}
		}catch (SQLException e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"),e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"),e);
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.error(location.concat("  LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"),e);
				e.printStackTrace();
			}
		}
		logger.info(location.concat("No of Cob lines returned from Resultset").concat("[").concat(Integer.toString(query_results.size())).concat("]")
				.concat(" LOGID:").concat("[").concat(individual_claim2.getLogId()).concat("]"));

		return query_results;
		
	}
}
