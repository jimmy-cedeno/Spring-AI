package dev.jimmycedeno.springai.controller.structuredOutputs;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamsController {
  private final ChatClient chatClient;

  @GetMapping("/teams")
  public List<NbaTeam> teams() {
    return chatClient.prompt()
        .user("Puedes decirme el nombre de todos los equipos de la NBA")
        .call()
        .entity(new ParameterizedTypeReference<List<NbaTeam>>() {});
  }
}
