package com.ebanks.springapp.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * The Class MetricFilter.
 */
public class MetricFilter implements Filter {

    @Autowired
    private MetricService metricService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws java.io.IOException, ServletException {
        chain.doFilter(request, response);
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String path = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

        int status = ((HttpServletResponse) response).getStatus();
        metricService.increaseActuatorCount(status);
        metricService.increaseCount(path, status);
    }

	/*
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}