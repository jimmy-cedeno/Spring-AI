package dev.jimmycedeno.springai.controller.basicChat;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ChatController {

  private final ChatClient chatClient;

  @GetMapping("/joke")
  String generate(@RequestParam(value = "message", defaultValue = "Dime un chiste") String message) {
    return chatClient.prompt().user(message).call().content();
  }
}
