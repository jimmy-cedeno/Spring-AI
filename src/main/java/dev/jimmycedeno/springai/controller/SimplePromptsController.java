package dev.jimmycedeno.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SimplePromptsController {
  private final ChatModel chatModel;

  @GetMapping("")
  String simple() {
    return chatModel.call(new Prompt.Builder().content("dime un chiste").build()).getResult().getOutput().getText();
  }
}
