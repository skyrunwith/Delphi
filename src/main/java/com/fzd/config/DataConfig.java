package com.fzd.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.JTASessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.fzd.model", "com.fzd.dao"})
@PropertySource("classpath:app.properties")
public class DataConfig {

	@Autowired
	Environment env;

	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.className"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10);
		return dataSource;
	}

	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.fzd.model");
		builder.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		builder.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		builder.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		builder.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		return builder.buildSessionFactory();
	}

	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(){
		HibernateTransactionManager tx = new HibernateTransactionManager(sessionFactory());
		return tx;
	}
}
