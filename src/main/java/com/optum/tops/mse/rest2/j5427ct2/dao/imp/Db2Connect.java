package com.optum.tops.mse.rest2.j5427ct2.dao.imp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
//@ComponentScan(basePackages = "com.optum.tops")
@PropertySource(value = { "classpath:ct2-application.properties" ,})
public class Db2Connect {

	@Autowired
	private Environment env;
	@Bean
	public DataSource dataSource() {
		/*
		 * for jndi db2connect
		 */
		/*final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        System.out.println("test datasource "+env.getRequiredProperty("jndiName"));
        DataSource dataSource = dsLookup.getDataSource(env.getRequiredProperty("jndiName"));
        return dataSource;*/
	
		/*
		 * for jdbc db2connect
		 */
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}

}



