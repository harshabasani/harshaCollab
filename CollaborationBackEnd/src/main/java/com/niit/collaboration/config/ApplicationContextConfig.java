package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.model.UserDetails;

import antlr.debug.Event;

import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.dao.UserDetailsDAOImpl;
import com.niit.collaboration.model.Job;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig
{
	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
		System.out.println("*************getDataSource called in ApplicationContextConfig***************");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("HR1");
		dataSource.setPassword("harsha11");
		return dataSource;
	}
	
	private Properties getHibernateProperties()
	{
		System.out.println("*************getHibernateProperties called in ApplicationContextConfig***************");
		Properties properties = new Properties();
		properties.put("hibernate.show_SQL","true");
		properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		//properties.put("hibernate.hbm2ddl.auto","update");
		return properties;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		System.out.println("*************getSessionFactory called in ApplicationContextConfig***************");
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(UserDetails.class);
		sessionBuilder.addAnnotatedClasses(Job.class);
		sessionBuilder.addAnnotatedClasses(Event.class);
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("*************getTransactionManager called in ApplicationContextConfig***************");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		
		return transactionManager;
	}
	
	@Autowired
	@Bean(name="userDetailsDAO")
	public UserDetailsDAO getUserDetailsDAO(SessionFactory sessionFactory)
	{
		System.out.println("************getUserDetailsDAO bean called in ApplicationContextConfig***************");
		return new UserDetailsDAOImpl(sessionFactory);
	}

	
}
