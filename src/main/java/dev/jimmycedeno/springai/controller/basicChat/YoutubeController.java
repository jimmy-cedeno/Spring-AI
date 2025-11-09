package dev.jimmycedeno.springai.controller.basicChat;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/youtube")
public class YoutubeController {

  private final ChatClient chatClient;
  @Value("classpath:prompts/youtube.st")
  private Resource youTubeResource;

  @GetMapping("/popular")
  String findPopularYoutubersByGenre(@RequestParam(value = "genre", defaultValue = "tech") String genre) {
    return chatClient
        .prompt()
        .user(u -> u.text(youTubeResource).param("genre", genre))
        .call().content();
  }
}
