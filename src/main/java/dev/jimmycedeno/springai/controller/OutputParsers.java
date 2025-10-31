package dev.jimmycedeno.springai.controller;

import dev.jimmycedeno.springai.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
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
  private final ChatModel chatModel;

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
        {format}
        """;

    var outputParser = new ListOutputConverter(new DefaultConversionService());
    PromptTemplate promptTemplate = new PromptTemplate(message);
    Prompt prompt = promptTemplate.create(Map.of("artist", artist, "format", outputParser.getFormat()));
    ChatResponse response = chatModel.call(prompt);
    return outputParser.convert(response.getResult().getOutput().getText());
  }

  @GetMapping("/book/author/{author}")
  Map<String, Object> getAuthorSocialLinks(@PathVariable String author) {
    var propmptMessage = """
        Proporcióname una lista de links del author {author}.\
        Incluye el nombre del autor como key y sus enlaces a redes sociales como object.\
        {format}
        """;

    var outputParser = new MapOutputConverter();
    String format = outputParser.getFormat();
    PromptTemplate promptTemplate = new PromptTemplate(propmptMessage);
    Prompt prompt = promptTemplate.create(Map.of("author", author, "format", format));
    ChatResponse response = chatModel.call(prompt);
    return outputParser.convert(response.getResult().getOutput().getText());
  }

  @GetMapping("/by-author")
  Author getBookByAuthor(@RequestParam(value = "author", defaultValue = "Ken Kousen") String author){
    var promptMessage = """
        Genera una lista de libros escritos por el autor {author}. \
        Si no está seguro de que un libro pertenece a este autor \
        por favor no lo incluya. \
        {format} \
        """;
    var outputParser = new BeanOutputConverter<>(Author.class);
    String format = outputParser.getFormat();
    PromptTemplate promptTemplate = new PromptTemplate(promptMessage);
    Prompt prompt = promptTemplate.create(Map.of("author", author, "format", format));
    ChatResponse response = chatModel.call(prompt);
    return outputParser.convert(response.getResult().getOutput().getText());
  }
}
