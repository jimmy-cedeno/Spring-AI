package dev.jimmycedeno.springai.controller.basicChat;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SimplePromptsController {
  private final ChatClient chatClient;

  @GetMapping("")
  String simple() {
    return chatClient.prompt(new Prompt.Builder().content("dime un chiste").build()).call().content();
  }
}
