package com.optum.tops.J5427HC1.dao.D54DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.optum.tops.J5427HC1.models.COBLN_2131;

@Repository
public class OIMC_CoblnAmtsDao {
	@Autowired
	private DataSource ds;
	
	static StringBuffer query = new StringBuffer();
	
	public List<COBLN_2131> getData(String ICN, String Icn_Sufx_Cd){
		List<COBLN_2131> return_data = new ArrayList<COBLN_2131>() ;
		query.setLength(0); // To ensure its cleared of previous query
		query.append("SELECT SRVC.INVN_CTL_NBR "); 
		query.append(",SRVC.ICN_SUFX_CD ");
		query.append(",SRVC.ORIG_LN_CORR_ID ");
		query.append(",(CASE WHEN (SRVC.MEDCR_PD_AMT = -1 ) ");
		query.append("THEN 0 ELSE SRVC.MEDCR_PD_AMT   END )  ");
		query.append(",(CASE WHEN (SRVC.OI_PD_LN_AMT = -1 ) ");
		query.append("THEN 0 ELSE SRVC.OI_PD_LN_AMT  END ) ");
		query.append("FROM  COB_SRVC_CALC_DATA SRVC ");
		query.append("WHERE SRVC.INVN_CTL_NBR  =  ? ");
		query.append("AND SRVC.ICN_SUFX_CD   = ? ");
		query.append("AND SRVC.LST_UPDT_DTTM = ");
		query.append("(SELECT MAX(SRVC2.LST_UPDT_DTTM) ");
		query.append("FROM  COB_SRVC_CALC_DATA    SRVC2  ");
		query.append("WHERE SRVC2.INVN_CTL_NBR    = SRVC.INVN_CTL_NBR ");
		query.append("AND SRVC2.ICN_SUFX_CD     = SRVC.ICN_SUFX_CD ");
		query.append("AND SRVC2.ORIG_LN_CORR_ID = SRVC.ORIG_LN_CORR_ID) ");
		query.append("FOR FETCH ONLY ");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(query.toString());
			ps.setString(1, ICN);
			ps.setString(2, Icn_Sufx_Cd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				COBLN_2131 record = new COBLN_2131(); 
				record.setInvn_Ctl_Nbr(rs.getString("SRVC.INVN_CTL_NB"));
				record.setIcn_Sufx_Cd(rs.getString("SRVC.ICN_SUFX_CD"));
				record.setOrig_Ln_Corr_Id(rs.getInt("SRVC.ORIG_LN_CORR_I"));
				record.setMedcr_Pd_Amt(rs.getBigDecimal("SRVC.MEDCR_PD_AMT"));
				record.setOi_Pd_Ln_Amt(rs.getBigDecimal("SRVC.OI_PD_LN_AMT"));
				return_data.add(record);
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
		
		
		return return_data; 
	}
}
