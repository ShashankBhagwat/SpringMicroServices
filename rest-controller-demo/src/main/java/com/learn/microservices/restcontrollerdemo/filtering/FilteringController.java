package com.learn.microservices.restcontrollerdemo.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retriveBean() {
		return new SomeBean("v1", "v2", "v3", "v4");
	}

	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue dynamicFiltering() {
		SomeBean someBean = new SomeBean("v1", "v2", "v3", "v4");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("string", "string4", "string3",
				"string2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;

	}
}

@JsonIgnoreProperties(value = { "string2" })
@JsonFilter("SomeBeanFilter")
class SomeBean {

	private String string;
	private String string2;

	@JsonIgnore
	private String string3;

	private String string4;

	public SomeBean(String string, String string2, String string3, String string4) {
		this.string = string;
		this.string2 = string2;
		this.string3 = string3;
		this.string4 = string4;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getString2() {
		return string2;
	}

	public void setString2(String string2) {
		this.string2 = string2;
	}

	public String getString3() {
		return string3;
	}

	public void setString3(String string3) {
		this.string3 = string3;
	}

	public String getString4() {
		return string4;
	}

	public void setString4(String string4) {
		this.string4 = string4;
	}

}
