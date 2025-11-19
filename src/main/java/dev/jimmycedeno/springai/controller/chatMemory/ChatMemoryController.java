package dev.jimmycedeno.springai.controller.chatMemory;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMemoryController {
  private final ChatClient chatClient;

  @GetMapping("/chat-memory")
  String chat(@RequestParam String message) {
    return chatClient.prompt()
        .user(message)
        .call().content();
  }
}
