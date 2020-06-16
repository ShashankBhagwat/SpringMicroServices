package com.learn.microservices.restcontrollerdemo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private long id;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private UserTable userTable;

	public Post() {
	}

	public Post(String description, UserTable userTable) {
		super();
		this.description = description;
		this.userTable = userTable;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserTable getUserTable() {
		return userTable;
	}

	public void setUserTable(UserTable userTable) {
		this.userTable = userTable;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}
