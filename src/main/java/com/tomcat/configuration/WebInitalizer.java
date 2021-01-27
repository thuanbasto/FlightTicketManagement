package com.tomcat.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitalizer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringConfig.class);
		ctx.setServletContext(servletContext);
		
		DispatcherServlet dispatcher = new DispatcherServlet(ctx);
		// catch 404 HTTP Code in Exception Controller
		dispatcher.setThrowExceptionIfNoHandlerFound(true);
		
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcher);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
//		// support UTF-8 for Client and Server
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true);
//		servletContext.addFilter("encodingFilter", characterEncodingFilter)
//		.addMappingForServletNames(null, false, "/*");
	}
	
}
