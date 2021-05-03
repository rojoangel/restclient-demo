package com.orelly.restclientdemo.services;

import com.orelly.restclientdemo.json.JokeResponse;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JokeService {

  private final WebClient client;

  @Autowired
  public JokeService(WebClient.Builder builder) {
    client = builder.baseUrl("http://api.icndb.com").build();
  }

  public String getJoke(String first, String last) {
    return client.get()
        .uri(uriBuilder -> uriBuilder.path("/jokes/random")
            .queryParam("limitTo", "[nerdy]")
            .queryParam("firstName", first)
            .queryParam("lastName", last)
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(JokeResponse.class)
        .map(jokeResponse -> jokeResponse.getValue().getJoke())
        .block(Duration.ofSeconds(2));
  }
}
