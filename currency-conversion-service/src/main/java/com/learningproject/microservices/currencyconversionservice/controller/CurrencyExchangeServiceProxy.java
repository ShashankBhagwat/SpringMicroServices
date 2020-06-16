package com.learningproject.microservices.currencyconversionservice.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//When using ribbon, we will provide the url in application.properties like below
//currency-exchange-service.ribbon.listOfServers=http://localhost:8001,http://localhost:8000
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")

//To talk to currency-exchange-service directly
//@FeignClient(name = "currency-exchange-service")

//To talk to currency-exchange-service thru Zuul
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")

//http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/10000
//Hit this link multiple times and it will be shared between 8000 and 8001 ports of currency-exchange-service
public interface CurrencyExchangeServiceProxy {

	// Talk directly to currency-exchange
	// @GetMapping("/currency-exchange/from/{from}/to/{to}")
	// http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/10000

	// Thru Zuul
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	// http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10000
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);

	// ****Request ->
	// /currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10000
	// Request uri ->
	// {}org.springframework.cloud.netflix.zuul.filters.pre.Servlet30RequestWrapper@2db60192
	// ****Request->/currency-exchange-service/currency-exchange/from/USD/to/INR
	// Request uri->
	// {}org.springframework.cloud.netflix.zuul.filters.pre.Servlet30RequestWrapper
	// @24f 3f 959
}
