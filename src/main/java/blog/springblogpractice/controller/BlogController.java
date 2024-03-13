package blog.springblogpractice.controller;

import blog.springblogpractice.domain.Article;
import blog.springblogpractice.dto.AddArticleRequest;
import blog.springblogpractice.dto.ArticleResponse;
import blog.springblogpractice.service.BlogService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/api/articles")
    @ResponseBody
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest addArticleRequest) {
        Article savedArticle = blogService.save(addArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    @ResponseBody
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll().stream()
                .map(ArticleResponse::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(articles);
    }

    @GetMapping("/api/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> findById(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(article);
    }

    @DeleteMapping("/api/articles/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
