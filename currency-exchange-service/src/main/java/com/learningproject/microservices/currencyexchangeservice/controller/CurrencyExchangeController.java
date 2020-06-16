package com.learningproject.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learningproject.microservices.currencyexchangeservice.Repository.ExchangeValueRepo;
import com.learningproject.microservices.currencyexchangeservice.entity.ExchangeValue;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepo repository;

	// http://localhost:8000/currency-exchange/from/USD/to/INR
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		logger.info("{}", exchangeValue);

		return exchangeValue;
	}
}
