package dev.yudin.YourPersonalFinancesBot.config;//package com.yourcodereview.java01.yourpersonalfinancesbot.config;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.io.IOException;
//import java.util.Objects;
//import java.util.Properties;
//import javax.sql.DataSource;
//
//@Configuration
//@ComponentScan(basePackages = "com.yourcodereview.java01.yourpersonalfinancesbot")
//@PropertySource("classpath:applicationTest.properties")
//public class AppConfigTest {
//
//	private static final Logger log = LogManager.getLogger();
//
//	@Autowired
//	private Environment propertyDataHolder;
//
////	@Bean
////	public DataSource dataSource() {
////		try {
////			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
////			return dbBuilder.setType(EmbeddedDatabaseType.H2)
////					.addScripts("classpath:sql/schema.sql",
////							"classpath:sql/test-data.sql")
////					.build();
////		} catch (Exception e) {
////			log.error("EmЬedded DataSource bean cannot Ье created!", e);
////			return null;
////		}
////	}
//
//	@Bean
//	public DataSource dataSource() {
//
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		String jdbcDriver = propertyDataHolder.getProperty("h2.driver.testDB");
//		String jdbcUrl = propertyDataHolder.getProperty("h2.url.testDB");
//		String jdbcUser = propertyDataHolder.getProperty("h2.user.testDB");
//		String jdbcPassword = propertyDataHolder.getProperty("h2.password.testDB");
//
//		dataSource.setDriverClassName(Objects.requireNonNull(jdbcDriver));
//		dataSource.setUrl(jdbcUrl);
//		dataSource.setUsername(jdbcUser);
//		dataSource.setPassword(jdbcPassword);
//
//		return dataSource;
//	}
//
//	private Properties hibernateProperties() {
//		Properties hibernateProp = new Properties();
//		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		hibernateProp.put("hibernate.format_sql", true);
//		hibernateProp.put("hibernate.use_sql_comments", true);
//		hibernateProp.put("hibernate.show_sql", true);
//		hibernateProp.put("hibernate.max_fetch_depth", 3);
//		hibernateProp.put("hibernate.jdbc.batch_size", 10);
//		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//		return hibernateProp;
//	}
//
//	@Bean
//	public SessionFactory sessionFactory() throws IOException {
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource());
//		sessionFactoryBean.setPackagesToScan("com.yourcodereview.java01.yourpersonalfinancesbot.entities");
//		sessionFactoryBean.setHibernateProperties(hibernateProperties());
//		sessionFactoryBean.afterPropertiesSet();
//		return sessionFactoryBean.getObject();
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() throws IOException {
//		return new HibernateTransactionManager(sessionFactory());
//	}
//
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource());
//	}
//
////	@Bean
////	public DataSourceTransactionManager transactionManager() {
////
////		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
////		transactionManager.setDataSource(dataSource());
////
////		return transactionManager;
////	}
//}
