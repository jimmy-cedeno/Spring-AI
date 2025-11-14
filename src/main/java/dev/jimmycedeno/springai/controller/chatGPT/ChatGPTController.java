package dev.jimmycedeno.springai.controller.chatGPT;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class ChatGPTController {

  private static final Logger log = LoggerFactory.getLogger(ChatGPTController.class);
  private final ChatClient chatClient;

  @GetMapping("chat")
  String home() {
    return "index";
  }

  @HxRequest
  @PostMapping("/api/chat")
  HtmxResponse generate(@RequestParam String message, Model model) {
    log.info("User message: {}", message);
    String response = chatClient
        .prompt()

        .user(message)
        .call()
        .content();

    model.addAttribute("response", response);
    model.addAttribute("message", message);
    return HtmxResponse.builder()
        .view("response :: responseFragment")
        .view("recent-message-list :: messageFragment")
        .build();
  }
}
