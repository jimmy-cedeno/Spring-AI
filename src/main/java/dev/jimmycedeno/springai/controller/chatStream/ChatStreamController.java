package dev.jimmycedeno.springai.controller.chatStream;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

//@CrossOrigin
@RestController
@AllArgsConstructor
public class ChatStreamController {
  private final ChatClient chatClient;

  @PostMapping("/streamController/chat")
  String chat(@RequestParam String message) {
    return chatClient.prompt()
        .user(message)
        .call()
        .content();
  }

  @GetMapping("/streamController/streamResponse")
  Flux<String> chatWithStream(@RequestParam String message) {
    return chatClient.prompt()
        .user(message)
        .stream().content();
  }
}
