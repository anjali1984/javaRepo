package com.optum.tops.mse.rest2.j5427ct2.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.optum.tops.mse.rest2.j5427ct2.copybook.Vycpidtl;
import com.optum.tops.mse.rest2.j5427ct2.dao.IDao;
import com.optum.tops.mse.rest2.j5427ct2.utilities.DSM_RETURN_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Configuration
@PropertySource(value={"classpath:ct2-queries.properties"})
@Repository
@Qualifier("daoImpTest")
public class DaoImp implements IDao {
//EnvironmentAware

	private PreparedStatement ps;
	private final String FETCH = "FETCH";
	private final String CLOSE = "CLOSE";
	private final String pcpCopayAmount = "PCP_COPAY_AMT";
	private final String spcCopayAmount = "SPCL_COPAY_AMT";
	public static Connection conn;
	public String defaultlogic = "N";
	public boolean callNext=false;
	String queryBLvl1;
	String queryBLvl2;
	String queryBLvl3;
	String queryBLvl4;
	@Value("${select.copay.amnt.clvl1}")
	String queryCLvl1;
	String queryCLvl2;
	String queryCLvl3;
	String queryCLvl4;

	@Autowired
	private Environment env;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*public DaoImp()
	{
		return;
	}
	
	@Autowired
	public DaoImp(Environment env,  JdbcTemplate jdbcTemplate) {
		
		this.env=env;
		this .jdbcTemplate=jdbcTemplate;

      //  LOGGER.info(env.getProperty("test"));

        System.out.println("Created DAOImp");

    }*/
	
	/*@Override
	public void setEnvironment(Environment env) {
		// TODO Auto-generated method stub
		this.env=env;
		
	}*/
	//To resolve ${} in @Value
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
			return new PropertySourcesPlaceholderConfigurer();
		}

	public void benLvl1(Vycpidtl requestArea) {

		if (requestArea.getREQUEST_PROC_RANGE_FROM().equals("99999")) {
			defaultlogic = "Y";
		}
		System.out.println("test requestArea.getREQUEST_COPAY_ID()1"+requestArea.getREQUEST_COPAY_ID());

		queryBLvl1=env.getRequiredProperty("select.copay.amnt.blvl1");

		try {

			/**using Spring jdbctemplate
			 * 
			 */
			jdbcTemplate.query(queryBLvl1, new Object[] {
					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_BEN_LVL_CD(),
					requestArea.getREQUEST_PROC_TYPE_CD(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					Integer.parseInt(requestArea.getREQUEST_REQ_AGE()),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN()
			}, 
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();
					if (rs.next()) {
						//						System.out.println("2400-FETCH-BEN-LVL-CSR1: Y");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						System.out.println("test in dao "+rs.getString(spcCopayAmount));
						//						System.out.println("PCP_COPAY_AMT" + requestArea.getRETURN_PCP_COPAY_AMT());
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");

						//						closeConnection(requestArea);
					} else {
						//System.out.println("2400-FETCH-BEN-LVL-CSR1: N");
						callNext=true;
						//benLvl2(requestArea);
						System.out.println("test in dao else ");
					}
					return requestArea;
				}
			});
			if (callNext)
			{
				benLvl2(requestArea);
				callNext=false;
			}
		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("2400-FETCH-BEN-LVL-CSR1: " + e.toString());
			//anjali:to be reviewed later..SqlException replaced with DataAccess
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
		}


	}


	public void benLvl2(Vycpidtl requestArea) {

		queryBLvl2=env.getRequiredProperty("select.copay.amnt.blvl2");

		try{
			System.out.println("test requestArea.getREQUEST_COPAY_ID()2"+requestArea.getREQUEST_COPAY_ID());
			jdbcTemplate.query(queryBLvl2, new Object[] {
					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_BEN_LVL_CD(),
					requestArea.getREQUEST_PROC_TYPE_CD(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN()
			}, 
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();
					if (rs.next()) {
						//						System.out.println("2400-FETCH-BEN-LVL-CSR1: Y");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						System.out.println("test in dao "+rs.getString(spcCopayAmount));
						//						System.out.println("PCP_COPAY_AMT" + requestArea.getRETURN_PCP_COPAY_AMT());
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");


					} else if (defaultlogic != "N") {
						//				System.out.println("3200-FETCH-BEN-LVL-CSR2: N");
						callNext=true;
					} 
					return requestArea;
				}
			});
			if (callNext)
			{
				benLvl3(requestArea);
				callNext=false;
			}
		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("3200-FETCH-BEN-LVL-CSR2" + e.toString());
			//anjali:to be reviewed later..SqlException replaced with DataAccess,also fetchsize as 1
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
		}
	}

	public void benLvl3(Vycpidtl requestArea) {



		queryBLvl3=env.getRequiredProperty("select.copay.amnt.blvl3");

		try{

			jdbcTemplate.query(queryBLvl3, new Object[] {

					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_BEN_LVL_CD(),			
					Integer.parseInt(requestArea.getREQUEST_REQ_AGE()),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN()
			}, 
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();

					if (rs.next()) {
						//				System.out.println("4200-FETCH-BEN-LVL-CSR3: Y");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");
						//				closeConnection(requestArea);
					} else {
						//				System.out.println("4200-FETCH-BEN-LVL-CSR3: N");
						callNext=true;
					}
					return requestArea;
				}
			});
			if (callNext)
			{
				benLvl4(requestArea);
				callNext=false;
			}
		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("4200-FETCH-BEN-LVL-CSR3" + e.toString());
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
		}

	}

	public void benLvl4(Vycpidtl requestArea) {

		System.out.println("test requestArea.getREQUEST_COPAY_ID()4"+requestArea.getREQUEST_COPAY_ID());

		queryBLvl4=env.getRequiredProperty("select.copay.amnt.blvl4");

		try{

			jdbcTemplate.query(queryBLvl4, new Object[] {
					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_BEN_LVL_CD(),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
			},
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();

					if (rs.next()) {
						//				System.out.println("5200-FETCH-BEN-LVL-CSR4: Y");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");
						//				closeConnection(requestArea);
					} 
					return requestArea;
				}
			});

		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("5200-FETCH-BEN-LVL-CSR4" + e.toString());
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
			///Parameter instance     is invalid for the requested conversion. ERRORCODE=-4461, SQLSTATE=42815
		}
	}

	public void causLvl1(Vycpidtl requestArea) {
		

		//queryCLvl1=env.getRequiredProperty("select.copay.amnt.clvl1");

		try{

			jdbcTemplate.query(queryCLvl1, new Object[] {

					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_PROC_TYPE_CD(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					Integer.parseInt(requestArea.getREQUEST_REQ_AGE()),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN()
			},
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();

					if (rs.next()) {
						//				System.out.println("6300-FETCH-POS-CAUS-CSR1: Yes");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						//				System.out.println("PCP_COPAY_AMT" + requestArea.getRETURN_PCP_COPAY_AMT());
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");
						//				closeConnection(requestArea);
					} else {
						//				System.out.println("6300-FETCH-POS-CAUS-CSR1: No");
						callNext=true;
					}
					return requestArea;
				}
			});
			if(callNext)
			{
				causLvl2(requestArea);
				callNext=false;
			}
		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("6300-FETCH-POS-CAUS-CSR1" + e.toString());
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
		}
	}

	public void causLvl2(Vycpidtl requestArea) {

		queryCLvl2=env.getRequiredProperty("select.copay.amnt.clvl2");

		try{

			jdbcTemplate.query(queryCLvl2, new Object[] {
					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_PROC_TYPE_CD(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					requestArea.getREQUEST_PROC_RANGE_FROM(),
					requestArea.getREQUEST_PROC_RANGE_TO(),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN()
			},
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();
					if (rs.next()) {
						//				System.out.println("7200-FETCH-POS-CAUS-CSR2: Yes");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						//				System.out.println("PCP_COPAY_AMT" + requestArea.getRETURN_PCP_COPAY_AMT());
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");
						//				closeConnection(requestArea);
					} else {
						//if (defaultlogic != "N") {
					
						//System.out.println("7200-FETCH-POS-CAUS-CSR2: No");
						callNext=true;
						//causLvl3(requestArea);
					} 
					return requestArea;
				}
			});
			if(callNext)
			{
				causLvl3(requestArea);
				callNext=false;
			}

		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("7200-FETCH-POS-CAUS-CSR2" + e.toString());
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
		}
	}

	public void causLvl3(Vycpidtl requestArea) {
		queryCLvl3=env.getRequiredProperty("select.copay.amnt.clvl3");

		try{

			jdbcTemplate.query(queryCLvl3, new Object[] {
					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					Integer.parseInt(requestArea.getREQUEST_REQ_AGE()),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN()
			},
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();
					if (rs.next()) {
						//				System.out.println("8200-FETCH-POS-CAUS-CSR3: Yes");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						//				System.out.println("PCP_COPAY_AMT" + requestArea.getRETURN_PCP_COPAY_AMT());
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");
						//				closeConnection(requestArea);
					} else {
						//				System.out.println("8200-FETCH-POS-CAUS-CSR3: No");
						callNext=true;
						//causLvl4(requestArea);
					}
					return requestArea;
				}
			});
			if(callNext)
			{
				causLvl4(requestArea);
				callNext=false;
			}

		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("8200-FETCH-POS-CAUS-CSR3" + e.toString());
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
		}
	}

	public void causLvl4(Vycpidtl requestArea) {
		queryCLvl4=env.getRequiredProperty("select.copay.amnt.clvl4");

		try{

			jdbcTemplate.query(queryCLvl4, new Object[] {
					Integer.parseInt(requestArea.getREQUEST_COPAY_ID()),
					requestArea.getREQUEST_EFF_DATE(),
					requestArea.getREQUEST_CAN_DATE(),
					requestArea.getREQUEST_TOPS_SERV_CD(),
					requestArea.getREQUEST_NTWK_IND1(),
					requestArea.getREQUEST_NTWK_IND2(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_PLC_OF_SRVC(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_CAUSE_CD(),
					requestArea.getREQUEST_PROV_ORG_TYPE_CD(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_T1(),
					requestArea.getREQUEST_SRVC_BEN_SET_CD_INN()
			},
					new ResultSetExtractor<Vycpidtl>(){
				public Vycpidtl extractData(ResultSet rs) throws SQLException,DataAccessException
				{
					Vycpidtl requestArea=new Vycpidtl();


					if (rs.next()) {
						//				System.out.println("9200-FETCH-POS-CAUS-CSR4: Yes");
						requestArea.setRETURN_PCP_COPAY_AMT(rs.getString(pcpCopayAmount));
						requestArea.setRETURN_SPCL_COPAY_AMT(rs.getString(spcCopayAmount));
						//				System.out.println("PCP_COPAY_AMT" + requestArea.getRETURN_PCP_COPAY_AMT());
						requestArea.setRETURN_MATCH_FOUND_FLAG("Y");
						//				closeConnection(requestArea);
					} else {
						//				System.out.println("9200-FETCH-POS-CAUS-CSR4: No");
						//				closeConnection(requestArea);
					}
					return requestArea;
				}
			});

		} catch (DataAccessException e) {
			requestArea.setDSM_RETURN_CODE(DSM_RETURN_CODE.DSM_DB2_ERROR.toString());
			requestArea.setDSM_ERR_TBL_ACTION(FETCH);
			requestArea.setDSM_ERR_TBL_NAME("COPAY_RULE_VAR/DTL");
			requestArea.setDSM_ERR_SECTN_NAME("9200-FETCH-POS-CAUS-CSR4" + e.toString());
			requestArea.setDSM_SQL_ERROR_MESSAGE(e.getMessage());
			e.printStackTrace();
		}
	}
}



