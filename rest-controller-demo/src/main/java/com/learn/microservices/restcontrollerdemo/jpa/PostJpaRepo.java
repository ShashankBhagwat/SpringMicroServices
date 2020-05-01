package com.learn.microservices.restcontrollerdemo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.microservices.restcontrollerdemo.entity.Post;

@Repository
public interface PostJpaRepo extends JpaRepository<Post, Long> {
}
