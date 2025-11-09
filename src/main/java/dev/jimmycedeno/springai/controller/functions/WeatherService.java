package dev.jimmycedeno.springai.controller.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {
  private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
  private final WeatherConfigProperties weatherProps;
  private RestClient restClient;

  public WeatherService(WeatherConfigProperties weatherProps) {
    this.weatherProps = weatherProps;
    restClient = RestClient.create(weatherProps.apiUrl());
  }



  @Tool(description = "Obtener el clima actual para la ciudad dada")
  public Response apply(Request request) {
    log.info("Weather Request: {}", request);
    Response response = restClient.get()
        .uri("/current.json?key={key}&q={q}", weatherProps.apiKey(), request.city)
        .retrieve()
        .body(Response.class);
    log.info("Weather Response: {}", response);
    return response;
  }

  public record Request(String city) {
  }

  public record Response(Location location, Current current) {
  }

  public record Location(String name, String region, String country, double lat, double lon) {
  }

  public record Current(String temp_f, Condition condition, String wind_mph, String humidity) {
  }

  public record Condition(String text) {
  }


}