package com.learningproject.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	// http://localhost:8765/{application-name}/{uri}
	// http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
	// http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10000
	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("****Request -> {} Request uri -> {}" + request, request.getRequestURI());
		return null;
		// ****Request -> /currency-exchange-service/currency-exchange/from/USD/to/INR
		// Request uri ->
		// {}org.springframework.cloud.netflix.zuul.filters.pre.Servlet30RequestWrapper@1300bda0
	}

	@Override
	public String filterType() {
		// pre,post,error
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
