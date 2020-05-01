package com.learn.microservices.restcontrollerdemo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.microservices.restcontrollerdemo.entity.UserTable;

@Repository
public interface UserJpaRepo extends JpaRepository<UserTable, Long> {
}
