package com.test.util.logback;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LogbackConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LogbackWebConfigurer.initLogging(event.getServletContext());
	}

}