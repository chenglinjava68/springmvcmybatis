package com.test.util.logback;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.util.WebUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public abstract class LogbackWebConfigurer {

	public static final String CONFIG_LOCATION_PARAM = "logbackConfigLocation";

	public static void initLogging(ServletContext servletContext) {
		String location = servletContext
				.getInitParameter(CONFIG_LOCATION_PARAM);
		if (location != null) {
			try {
				if (!ResourceUtils.isUrl(location)) {
					location = SystemPropertyUtils
							.resolvePlaceholders(location);
					location = WebUtils.getRealPath(servletContext, location);
					servletContext.log("Initializing logback from [" + location
							+ "]");
					LoggerContext lc = (LoggerContext) LoggerFactory
							.getILoggerFactory();
					try {
						JoranConfigurator configurator = new JoranConfigurator();
						configurator.setContext(lc);
						lc.reset();
						configurator.doConfigure(location);
					} catch (JoranException e) {
						e.printStackTrace();
					}
					StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
				}
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException(
						"Invalid 'logbackConfigLocation' parameter: "
								+ e.getMessage());
			}
		}
	}
}
