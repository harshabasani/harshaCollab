package com.niit.collaboration.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class[] getRootConfigClasses()
	{
		System.out.println("*********getRootConfigClasses called in AppInitializer********");
		return new Class[] {AppConfig.class};
     }
	
	@Override
	protected Class[] getServletConfigClasses()
	{
		System.out.println("*********getServletConfigClasses called in AppInitializer********");
		return null;
	}
	
	@Override
	protected String[] getServletMappings()
	{
		System.out.println("*********getServletMapping called in AppInitializer********");
		return new String[] {"/"};
	}
	
}
