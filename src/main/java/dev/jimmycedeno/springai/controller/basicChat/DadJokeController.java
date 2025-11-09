package dev.jimmycedeno.springai.controller.basicChat;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DadJokeController {

  private final ChatClient chatClient;

  @GetMapping("dad-joke")
  String jokes() {
    return chatClient.prompt()
        .system("Tu principal funcion es decir chistes de padres, si alguien te solicita un chiste de otro tipo, solo diles que no lo sabes.")
        .user("Dime un chiste malcriado sobre hijos.")
        .call().content();
  }
}
