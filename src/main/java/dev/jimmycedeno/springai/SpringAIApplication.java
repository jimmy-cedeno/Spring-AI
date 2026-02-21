package dev.jimmycedeno.springai;

import dev.jimmycedeno.springai.controller.functions.WeatherConfigProperties;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(WeatherConfigProperties.class)
public class SpringAIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAIApplication.class, args);
	}

//  @Bean
//  public ChatClient chatClient(ChatClient.Builder builder){
//    return builder
////        .defaultSystem()
//        .defaultAdvisors(MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build())
//        .build();
//  }

  @Bean
  public ChatClient chatClient(@Qualifier("openAiChatClient") ChatClient chatClient) {
    return chatClient;
  }
}
