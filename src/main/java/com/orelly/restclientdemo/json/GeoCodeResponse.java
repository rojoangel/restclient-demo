package com.orelly.restclientdemo.json;

import java.util.List;

public class GeoCodeResponse {
  private String status;
  private List<GeoCodeResult> results;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<GeoCodeResult> getResults() {
    return results;
  }

  public void setResults(List<GeoCodeResult> results) {
    this.results = results;
  }

  public Location getLocation() {
    return this.results.get(0).getGeometry().getLocation();
  }

  public String getFormattedAddress() {
    return this.results.get(0).getFormattedAddress();
  }
}
