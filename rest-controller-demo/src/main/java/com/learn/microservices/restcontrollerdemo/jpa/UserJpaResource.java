package com.learn.microservices.restcontrollerdemo.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.learn.microservices.restcontrollerdemo.entity.Post;
import com.learn.microservices.restcontrollerdemo.entity.UserTable;
import com.learn.microservices.restcontrollerdemo.exception.UserNotFoundException;

@RestController
public class UserJpaResource {

	@Autowired
	UserJpaRepo userRepo;

	@Autowired
	PostJpaRepo postRepo;

	@GetMapping(path = "/jpa/users")
	public List<UserTable> findAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<UserTable> findOne(@PathVariable long id) {
		Optional<UserTable> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		EntityModel<UserTable> resource = new EntityModel<UserTable>(user.get());
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable long id) {
		Optional<UserTable> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		userRepo.deleteById(id);
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> createUser(@RequestBody UserTable user) {
		// http://localhost:8080/users
		// Content-Type = application/json
		// JSON..Not text
		// {
		// "name": "SFDGDG"
		// }
		userRepo.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrieveposts(@PathVariable long id) {
		Optional<UserTable> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		return user.get().getPosts();
	}

	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> postPost(@PathVariable long id, @RequestBody Post post) {
		Optional<UserTable> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		post.setUserTable(user.get());
		postRepo.save(post);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@DeleteMapping(path = "/jpa/users/{id}/posts/{post_id}")
	public void deletepost(@PathVariable long id, @PathVariable long post_id) {
		Optional<UserTable> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		Optional<Post> post = postRepo.findById(post_id);
		if (!post.isPresent())
			throw new UserNotFoundException("id-" + id);

		postRepo.deleteById(post_id);
		// user.get().getPosts().remove(post);

	}
}
