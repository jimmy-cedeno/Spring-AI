package dev.jimmycedeno.springai.controller.chatAndVisionCompabilities;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatModel {
  private final ChatClient chatClient;

  @GetMapping("/dad-jokes")
  public String jokes(@RequestParam(value = "topic", defaultValue = "Dogs") String topic) {
    return chatClient
        .prompt()
        .user(u -> u.text("Dime un chiste de {topic}").param("topic", topic))
        .call().content();
  }

}
