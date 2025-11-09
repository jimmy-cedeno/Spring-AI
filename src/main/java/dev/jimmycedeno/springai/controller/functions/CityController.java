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
  private final WeatherConfigProperties weatherConfigProperties;

  @GetMapping("/cities")
  public String cities(@RequestParam("message") String message) {
//    SystemMessage systemMessage = new SystemMessage("Eres un asistente util que responde preguntas sobre ciudades de todo el mundo");
//    UserMessage userMessage = new UserMessage(message);

    return chatClient
        .prompt()
        .system("Eres un asistente util que responde preguntas sobre ciudades de todo el mundo")
        .user(message)
        .tools(new WeatherService(weatherConfigProperties))
        .call().content();
  }
}
