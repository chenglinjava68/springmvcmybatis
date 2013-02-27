package com.test.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行时间Filter
 * @author ehoo
 *
 */
public class ProcessTimeFilter implements Filter {
	
	final Logger log = LoggerFactory.getLogger(ProcessTimeFilter.class);
	
	public static final String START_TIME = "_start_time";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		long time = System.currentTimeMillis();
		chain.doFilter(request, resp);
		time = System.currentTimeMillis() - time;
		log.info("process in {} ms: {}", time, request.getRequestURI());
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}