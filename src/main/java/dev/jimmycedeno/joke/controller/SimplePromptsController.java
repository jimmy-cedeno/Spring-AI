package dev.jimmycedeno.joke.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SimplePromptsController {
  private final OpenAiChatModel chatModel;

  @GetMapping("")
  String simple() {
    return chatModel.call(new Prompt.Builder().content("dime un chiste").build()).getResult().getOutput().getText();
  }
}
