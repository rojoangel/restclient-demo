package com.orelly.restclientdemo.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.orelly.restclientdemo.entities.Site;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeoCoderServiceTest {

  @Autowired
  GeoCoderService service;

  @Test
  void getLatLngWithoutStreet() {
    Site site = service.getLatLng("Boston", "MA");
    assertAll(
        () -> assertEquals(42.36, site.getLatitude(), 0.01),
        () -> assertEquals(-71.06, site.getLongitude(), 0.01)
    );
  }

  @Test
  void getLatLngWithStreet() {
    Site site = service.getLatLng("1600 Ampitheatre Parkway",
        "Mountain View", "CA");
    assertAll(
        () -> assertEquals(37.43, site.getLatitude(), 0.01),
        () -> assertEquals(-122.08, site.getLongitude(), 0.01)
    );
  }
}