package com.niit.collaboration.testcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.collaboration.dao.UserDetailsDAO;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDetailsTestCase {
	
	AnnotationConfigApplicationContext context;
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@Before
	public void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();
		
		userDetailsDAO = (UserDetailsDAO) context.getBean("userDetailsDAO");
	}
	
	@Test
	public void listAllUsersTestCase()
	{
		assertEquals("list users",3,userDetailsDAO.list().size());
	}

}
