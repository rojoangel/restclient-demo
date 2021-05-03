package com.orelly.restclientdemo.services;

import com.orelly.restclientdemo.entities.Site;
import com.orelly.restclientdemo.json.GeoCodeResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeoCoderService {
  private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";
  private final WebClient client;

  @Autowired
  public GeoCoderService(WebClient.Builder builder) {
    this.client = builder
        .baseUrl("https://maps.googleapis.com")
        .build();
  }

  public Site getLatLng(String... address) {
    String encoded = Stream.of(address)
        .map(component -> URLEncoder.encode(component, StandardCharsets.UTF_8))
        .collect(Collectors.joining(","));

    GeoCodeResponse response = client
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/maps/api/geocode/json")
            .queryParam("address", encoded)
            .queryParam("key", KEY)
            .build())
        .retrieve()
        .bodyToMono(GeoCodeResponse.class)
        .block(Duration.ofSeconds(2));

    return new Site(
        response.getFormattedAddress(),
        response.getLocation().getLat(),
        response.getLocation().getLng()
    );
  }
}
