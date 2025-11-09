package dev.jimmycedeno.springai.controller.formatoutput;

import dev.jimmycedeno.springai.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OutputParsers {
  private final ChatClient chatClient;

  //  @GetMapping("/songs")
//  String getSongsByArtist(@RequestParam(value = "artist", defaultValue = "Taylor Swift") String artist) {
//    var message = """
//        Por favor dame una lista del top 10 de canciones para el artista {artist}. Si no lo sabes, solo di "No lo sé".
//        """;
//    PromptTemplate promptTemplate = new PromptTemplate(message);
//    Prompt prompt = promptTemplate.create(Map.of("artist", artist));
//    ChatResponse response = chatModel.call(prompt);
//    return response.getResult().getOutput().getText();
//  }
  @GetMapping("/defaultConversion")
  List<String> getSongsByArtist(@RequestParam(value = "artist", defaultValue = "Taylor Swift") String artist) {
    var message = """
        Por favor dame una lista del top 10 de canciones para el artista {artist}. Si no lo sabes, solo di "No lo sé".
        """;

    var outputParser = new ListOutputConverter(new DefaultConversionService());
    return chatClient
        .prompt()
        .user(u -> u.text(message).params(Map.of("artist", artist)))
        .call()
        .entity(new ParameterizedTypeReference<>() {
        });
  }

  @GetMapping("/book/author/{author}")
  Map<String, Object> getAuthorSocialLinks(@PathVariable String author) {
    var propmptMessage = """
        Proporcióname una lista de links del author {author}.\
        Incluye el nombre del autor como key y sus enlaces a redes sociales como object.\
        """;

    return chatClient
        .prompt()
        .user(u -> u.text(propmptMessage).param("author", author))
        .call()
        .entity(new ParameterizedTypeReference<>() {
        });
  }

  @GetMapping("/by-author")
  Author getBookByAuthor(@RequestParam(value = "author", defaultValue = "Ken Kousen") String author){
    var promptMessage = """
        Genera una lista de libros escritos por el autor {author}. \
        Si no está seguro de que un libro pertenece a este autor \
        por favor no lo incluya. \
        """;
    return chatClient
        .prompt()
        .user(u -> u.text(promptMessage).param("author", author))
        .call()
        .entity(Author.class);
  }
}
