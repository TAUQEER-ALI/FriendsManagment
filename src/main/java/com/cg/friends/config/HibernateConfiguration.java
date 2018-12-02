package com.cg.friends.config;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tauqeer Ali Chaudhari
 *
1:34:55 AM
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		System.out.println("HibernateConfiguration.sessionFactory() @@@@==>>>");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		props.put(DRIVER, environment.getProperty("mysql.driver"));
		props.put(URL, environment.getProperty("mysql.url"));
		props.put(USER, environment.getProperty("mysql.user"));
		props.put(PASS, environment.getProperty("mysql.password"));
		props.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
		props.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, 
				environment.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.cg.friends.model");
		return factoryBean;
	}


	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}
	
}