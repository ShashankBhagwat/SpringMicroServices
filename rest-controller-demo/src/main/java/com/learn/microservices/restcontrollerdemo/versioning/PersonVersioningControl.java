package com.learn.microservices.restcontrollerdemo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningControl {

	@GetMapping("v1/person")
	public PersonV1 personv1() {
		// http://localhost:8080/v1/person
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("v2/person")
	public PersonV2 personv2() {
		// http://localhost:8080/v2/person
		return new PersonV2(new Name("Lisa", "Chastian"));
	}

	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 personParam1() {
		// http://localhost:8080/person/param?version=1
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 personParam2() {
		// http://localhost:8080/person/param?version=2
		return new PersonV2(new Name("Lisa", "Chastian"));
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headersParam1() {
		// http://localhost:8080/person/header
		// X-API-VERSION=1
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headersParam2() {
		// http://localhost:8080/person/header
		// X-API-VERSION=2
		return new PersonV2(new Name("Lisa", "Chastian"));
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 produces1() {
		// http://localhost:8080/person/produces
		// Accept = application/vnd.company.app-v1+json
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 produces2() {
		// http://localhost:8080/person/produces
		// Accept = application/vnd.company.app-v2+json
		return new PersonV2(new Name("Lisa", "Chastian"));
	}
}
