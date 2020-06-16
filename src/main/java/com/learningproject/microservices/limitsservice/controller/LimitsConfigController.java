package com.learningproject.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningproject.microservices.limitsservice.Configuration;
import com.learningproject.microservices.limitsservice.model.LimitConfig;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigController {

	@Autowired
	private Configuration config;

	@GetMapping(path = "/limits")
	public LimitConfig retrieveLimitsFromConfig() {
		return new LimitConfig(config.getMinimum(), config.getMaximum());
	}

	@GetMapping(path = "/fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallBackRetrieveConfiguration")
	public LimitConfig retrieveConfig() {
		throw new RuntimeException("Not available");
	}

	public LimitConfig fallBackRetrieveConfiguration() {
		return new LimitConfig(999, 99999);
	}
}
