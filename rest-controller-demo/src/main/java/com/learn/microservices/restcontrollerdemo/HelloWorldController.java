package com.learn.microservices.restcontrollerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String hello() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-internationalized")
	public String helloInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
