package dev.jimmycedeno.springai.controller.multiLLM;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiLLMConfiguration {
  @Bean
  ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
    return ChatClient.create(openAiChatModel);
  }

  @Bean
  ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
    return ChatClient.create(ollamaChatModel);
  }
}
