package dev.jimmycedeno.springai.controller.basicChat;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class StreamController {
  private final ChatClient chatClient;

  @GetMapping("/stream")
  Flux<String> stream() {
    return chatClient
        .prompt()
        .user("Voy a viajar a Kansas City la proxima semana, cuales son los 10 mejores lugares de BBQ que puedo visitar.")
        .stream().content();
  }
}
