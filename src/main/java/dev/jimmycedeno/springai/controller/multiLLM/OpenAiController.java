package dev.jimmycedeno.springai.controller.multiLLM;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {
  private final ChatClient chatClient;

  public OpenAiController(@Qualifier("openAiChatClient") ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @GetMapping("/openAI")
  String openAi() {
    return chatClient.prompt()
        .user("Cuentame un dato interesante sobre OpenAi")
        .call().content();
  }
}
