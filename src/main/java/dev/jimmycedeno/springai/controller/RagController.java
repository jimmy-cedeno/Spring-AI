package dev.jimmycedeno.springai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RagController {
  private final ChatModel chatModel;
  private final VectorStore vectorStore;

  @Value("classpath:/prompts/rag.st")
  private Resource ragPromptTemplate;

  @GetMapping("/rag")
  public String rag(@RequestParam(value = "message", defaultValue = "Que es Java") String message) {
    List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.builder().query(message).topK(2).build());
    List<String> contentList = similarDocuments.stream().map(Document::getText).toList();

    PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
    Map<String, Object> promptParams = new HashMap<>();
    promptParams.put("input", message);
    promptParams.put("documents", String.join("\n", contentList));
    Prompt prompt = promptTemplate.create(promptParams);

    return chatModel.call(prompt).getResult().getOutput().getText();

  }

}
