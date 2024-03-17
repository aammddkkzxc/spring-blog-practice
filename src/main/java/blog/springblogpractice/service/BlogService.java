package blog.springblogpractice.service;

import blog.springblogpractice.domain.Article;
import blog.springblogpractice.dto.AddArticleRequest;
import blog.springblogpractice.dto.UpdateArticleRequest;
import blog.springblogpractice.external.ExampleAPIClient;
import blog.springblogpractice.repository.ArticleRepository;
import blog.springblogpractice.repository.CommentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ExampleAPIClient apiClient;

    public BlogService(ArticleRepository articleRepository, CommentRepository commentRepository, ExampleAPIClient apiClient) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.apiClient = apiClient;
    }

    public Article save(AddArticleRequest addArticleRequest) {
        return articleRepository.save(addArticleRequest.toEntity());
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public Article updateById(Long id, UpdateArticleRequest updateArticleRequest) {
        Article article = findArticleById(id);

        article.update(updateArticleRequest.getTitle(), updateArticleRequest.getContent());

        return article;
    }

    public List<Article> saveBulkArticles() {
        List<Article> articles = parsedArticles();
        return articleRepository.saveAll(articles);
    }

    private List<Article> parsedArticles() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        String responseJson = apiClient.fetchDataFromApi(url);

        ObjectMapper objectMapper = new ObjectMapper();
        List<LinkedHashMap<String, String>> jsonMapList = new ArrayList<>();

        try {
            jsonMapList = objectMapper.readValue(responseJson, List.class);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json", e.getMessage());
        }

        return jsonMapList.stream()
                .map(hashMap -> new Article(hashMap.get("title"), hashMap.get("body")))
                .toList();
    }
}
