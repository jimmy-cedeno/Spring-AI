package dev.jimmycedeno.joke;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@AllArgsConstructor
public class ChatController {

  private final OpenAiChatModel chatModel;

  public ChatController(OpenAiChatModel chatModel) {
    this.chatModel = chatModel;
  }

  @GetMapping("/joke")
  String generate(@RequestParam(value = "message", defaultValue = "Dime un chiste") String message) {
    return chatModel.call(message);
  }
}
