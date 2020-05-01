package com.learn.microservices.restcontrollerdemo.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learn.microservices.restcontrollerdemo.entity.UserTable;

@Repository
@Transactional
public class UserTableRepo {

	@Autowired
	EntityManager manager;

	public UserTable findUser(long id) {
		return manager.find(UserTable.class, id);
	}

	public List<UserTable> findAllUsers() {
		Query q = manager.createQuery("select u from UserTable u", UserTable.class);
		List resultList = q.getResultList();
		return resultList;
	}

	public UserTable deleteUser(long id) {
		UserTable user = findUser(id);
		if (user != null)
			manager.remove(user);

		return user;
	}

	public void save(UserTable user) {
		manager.merge(user);
		manager.flush();
	}
}
