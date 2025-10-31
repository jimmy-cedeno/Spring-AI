package dev.jimmycedeno.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DadJokeController {

  private final ChatModel chatModel;

  @GetMapping("dad-joke")
  String jokes() {
    var system = new SystemMessage("Tu principal funcion es decir chistes de padres, si alguien te solicita un chiste de otro tipo, solo diles que no lo sabes.");
    var user = new UserMessage("Dime un chiste de padres.");
    Prompt prompt = new Prompt(List.of(system, user));
    return chatModel.call(prompt).getResult().getOutput().getText();
  }
}
