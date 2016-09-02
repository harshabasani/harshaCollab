package com.niit.collaboration.testcase;



import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.model.UserDetails;

public class UserDetailsTest {
	
public static void main(String[] args) {
		
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		System.out.println("*****************am before refresh in UserDetailsTest***************");
		context.refresh();
		System.out.println("*****************am after refresh in UserDetailsTest****************");
	UserDetails u =(UserDetails)	  context.getBean("userDetails");
	
	UserDetailsDAO userDetailsDAO = (UserDetailsDAO)  context.getBean("userDetailsDAO");
	

    u.setId("4");
	u.setName("mahesh");
	u.setPassword("mahesh");
	u.setMobile("9123654781");
	u.setEmail("mahesh@gmail.com");
	u.setAddress("Hyd");
	u.setGender("male");
	u.setRole("role_user");
	u.setStatus('N');
	
	userDetailsDAO.saveOrUpdate(u);

	}

}
