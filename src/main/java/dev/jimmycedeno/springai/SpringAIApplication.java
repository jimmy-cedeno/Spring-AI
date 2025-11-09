package dev.jimmycedeno.springai;

import dev.jimmycedeno.springai.controller.functions.WeatherConfigProperties;
import org.springframework.ai.chat.client.ChatClient;
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

  @Bean
  public ChatClient chatClient(ChatClient.Builder builder){
    return builder
//        .defaultSystem()
        .build();
  }
}
