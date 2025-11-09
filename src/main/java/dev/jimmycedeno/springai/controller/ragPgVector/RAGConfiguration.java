package dev.jimmycedeno.springai.controller.ragPgVector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class RAGConfiguration {
  private static final Logger log = LoggerFactory.getLogger(RAGConfiguration.class);

  @Value("classpath:/docs/java.txt")
  private Resource resource;

  @Value("vectorstore.json")
  private String vectorStoreName;

  @Bean
  VectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
    SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
    File file = getVectorStoreFile();
    if (file.exists()) {
      log.info("Vector store file exists");
      simpleVectorStore.load(file);
    } else {
      log.info("Vector store file does not exist, loading documents");
      final var textReader = new TextReader(resource);
      textReader.getCustomMetadata().put("filename", "java.txt");
      List<Document> documents = textReader.get();
      final var textSplitter = new TokenTextSplitter();
      final var splitDocuments = textSplitter.apply(documents);

      simpleVectorStore.add(splitDocuments);
      simpleVectorStore.save(file);
    }
    return simpleVectorStore;
  }

  private File getVectorStoreFile() {
    var path = Paths.get("src", "main", "resources", "data");
    String absolutePath = path.toFile().getAbsolutePath() + "/" + vectorStoreName;
    return new File(absolutePath);

  }
}
