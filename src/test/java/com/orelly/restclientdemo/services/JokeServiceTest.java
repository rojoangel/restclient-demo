package com.orelly.restclientdemo.services;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JokeServiceTest {

  private final Logger logger = LoggerFactory.getLogger(JokeService.class);

  @Autowired
  private JokeService service;

  @Test
  void getJoke() {
    String joke = service.getJoke("Craig", "Walls");
    logger.info(() -> joke);
    assertTrue(joke.contains("Craig") &&
        joke.contains("Walls"));
  }
}