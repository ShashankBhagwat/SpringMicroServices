package com.learn.microservices.restcontrollerdemo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learn.microservices.restcontrollerdemo.Repository.UserTableRepo;
import com.learn.microservices.restcontrollerdemo.entity.UserTable;
import com.learn.microservices.restcontrollerdemo.exception.UserNotFoundException;

@RestController
public class MainController {

	@Autowired
	UserTableRepo repo;

	@GetMapping(path = "/users")
	public List<UserTable> findAllUsers() {
		return repo.findAllUsers();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<UserTable> findOne(@PathVariable long id) {
		UserTable user = repo.findUser(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);

		EntityModel<UserTable> resource = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable long id) {
		UserTable user = repo.deleteUser(id);

		if (user == null)
			throw new UserNotFoundException("id -" + id);
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody UserTable user) {
		// http://localhost:8080/users
		// {
		// "name": "SFDGDG"
		// }
		repo.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
