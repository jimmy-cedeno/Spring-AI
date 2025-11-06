package dev.jimmycedeno.springai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StuffingPrompt {
  private final ChatModel chatModel;
  @Value("classpath:/prompts/olympic-sports.st")
  private Resource olympicSportsResource;
  @Value("classpath:/docs/olympic-sports.txt")
  private Resource olympicSportsDocs;


  @GetMapping("/2024")
  String get2024OlympicSports(
      @RequestParam(value = "message", defaultValue = "Cuales juegos fueron incluidos en los juegos olimpicos del 2024?") String message,
      @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit
  ) {
    PromptTemplate promptTemplate = new PromptTemplate(olympicSportsResource);
    Map<String, Object> map = new HashMap<>();
    map.put("question", message);
    if (stuffit) {
      map.put("context", olympicSportsDocs);
    } else {
      map.put("context", "");
    }

    Prompt prompt = promptTemplate.create(map);
    ChatResponse response = chatModel.call(prompt);
    return response.getResult().getOutput().getText();
  }
}
