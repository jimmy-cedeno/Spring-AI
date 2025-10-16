package dev.jimmycedeno.joke.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/youtube")
public class YoutubeController {

  private final OpenAiChatModel chatModel;
  @Value("classpath:prompts/youtube.st")
  private Resource youTubeResource;

  @GetMapping("/popular")
  String findPopularYoutubersByGenre(@RequestParam(value = "genre", defaultValue = "tech") String genre) {

    PromptTemplate promptTemplate = new PromptTemplate(youTubeResource);
    Prompt prompt = promptTemplate.create(Map.of("genre", genre));

    return chatModel.call(prompt).getResult().getOutput().getText();
  }
}
