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

import com.optum.tops.J5427HC1.models.COBLN_2131;

@Repository
@PropertySource("queries.properties")
public class OIMC_CoblnAmtsDao {
	@Autowired
	private DataSource ds;

	@Value("${hc1.OIMC_CoblnAmtsDao_Query}")
	private String OIMC_CoblnAmtsDao_Query;
	private  Logger logger=Logger.getLogger("genLogger");

	public List<COBLN_2131> getData(String ICN, String Icn_Sufx_Cd, String logId){
		List<COBLN_2131> return_data = new ArrayList<COBLN_2131>() ;
		

		String location="J5427HC1.dao.D54DAO.OIMC_CoblnAmtsDao.getData(String, String)";
			Connection con = null;
		PreparedStatement ps = null;

		try{
			con = ds.getConnection();
			ps = con.prepareStatement(OIMC_CoblnAmtsDao_Query);
			ps.setString(1, ICN);
			ps.setString(2, Icn_Sufx_Cd);

		
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				logger.info(location.concat(" Empty Resultset for OIMC_CoblnAmtsDao_Query").concat(" LOGID:").concat("[").concat(logId).concat("]"));

			}else{

				do{

					COBLN_2131 record = new COBLN_2131(); 
					record.setInvn_Ctl_Nbr(rs.getString("INVN_CTL_NBR"));
					record.setIcn_Sufx_Cd(rs.getString("ICN_SUFX_CD"));
					record.setOrig_Ln_Corr_Id(rs.getInt("ORIG_LN_CORR_ID"));
					if(rs.getBigDecimal("MEDCR_PD_AMT").compareTo(new BigDecimal(-1)) == 0) // if it is -1 then set the amount to 0 
						record.setMedcr_Pd_Amt(new BigDecimal(0));
					else{
						record.setMedcr_Pd_Amt(rs.getBigDecimal("MEDCR_PD_AMT"));
					}
					if(rs.getBigDecimal("OI_PD_LN_AMT").compareTo(new BigDecimal(-1)) == 0) // if it is -1 then set the amount to 0 
						record.setOi_Pd_Ln_Amt(new BigDecimal(0));
					else{
						record.setOi_Pd_Ln_Amt(rs.getBigDecimal("OI_PD_LN_AMT"));
					}

					return_data.add(record);
				}while(rs.next());
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


		return return_data; 
	}
}
