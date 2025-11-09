package dev.jimmycedeno.springai.controller.chatAndVisionCompabilities;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.content.Media;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageModel {
  private final ChatClient chatClient;

  @GetMapping("/image-describe")
  public String describeImage() {
    var image = new ClassPathResource("/images/image.jpg");
    return chatClient
        .prompt()
        .user(u -> u.text("Dime que ves en la siguiente imagen").media(new Media(MimeTypeUtils.IMAGE_JPEG, image)))
        .call()
        .content();
  }

  @GetMapping("/code-describe")
  public String code() {
    var image = new ClassPathResource("/images/code.png");
    return chatClient
        .prompt()
        .user(u -> u.text("La siguiente imagen es un screenshot de algo de codigo. Dame tu mejor descripcion de lo que hace el codigo.").media(new Media(MimeTypeUtils.IMAGE_PNG, image)))
        .call()
        .content();
  }

  @GetMapping("/image-to-code")
  public String imageToCode() {
    var image = new ClassPathResource("/images/code.png");
    return chatClient
        .prompt()
        .user(u -> u.text("La siguiente imagen es un screenshot de algo de codigo. Transcribe de la imagen a codigo de java.").media(new Media(MimeTypeUtils.IMAGE_PNG, image)))
        .call()
        .content();
  }
}
