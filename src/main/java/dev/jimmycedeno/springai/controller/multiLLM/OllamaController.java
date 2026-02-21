package dev.jimmycedeno.springai.controller.multiLLM;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {
  private final ChatClient chatClient;

  public OllamaController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @GetMapping("/ollama")
  String ollama() {
    return chatClient.prompt()
        .user("Cuentame un dato interesante sobre Meta")
        .call().content();
  }
}
