package dev.jimmycedeno.springai.controller.basicChat;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StuffingPrompt {
  private final ChatClient chatClient;
  @Value("classpath:/prompts/olympic-sports.st")
  private Resource olympicSportsResource;
  @Value("classpath:/docs/olympic-sports.txt")
  private Resource olympicSportsDocs;


  @GetMapping("/2024")
  String get2024OlympicSports(
      @RequestParam(value = "message", defaultValue = "Cuales juegos fueron incluidos en los juegos olimpicos del 2024?") String message,
      @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit
  ) throws IOException {
    PromptTemplate promptTemplate = new PromptTemplate(olympicSportsResource);
    String context;
    if (stuffit) {
      context = Files.readString(olympicSportsDocs.getFile().toPath());
    } else {
      context = "";
    }

    return chatClient
        .prompt()
        .user(u -> u.text(olympicSportsResource).params(Map.of("question", message, "context", context)))
        .call()
        .content();
  }
}
