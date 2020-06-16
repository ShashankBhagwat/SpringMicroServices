package com.learningproject.microservices.currencyexchangeservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningproject.microservices.currencyexchangeservice.entity.ExchangeValue;

public interface ExchangeValueRepo extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from, String to);
}
