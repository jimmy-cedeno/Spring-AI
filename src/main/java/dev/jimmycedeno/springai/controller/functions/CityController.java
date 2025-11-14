package dev.jimmycedeno.springai.controller.functions;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController {
  private final ChatClient chatClient;
  private final WeatherService weatherService;
  private final WeatherConfigProperties weatherConfigProperties;

  @GetMapping("/cities")
  public String cities(@RequestParam("message") String message) {

    return chatClient
        .prompt()
        .system("Eres un asistente util que responde preguntas sobre ciudades de todo el mundo, si te preguntan el clima de una ciudad limitate a responder su temperatura actual.")
        .user(message)
        .tools(weatherService)
        .call().content();
  }
}
