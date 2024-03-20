package blog.springblogpractice.controller;

import blog.springblogpractice.domain.Article;
import blog.springblogpractice.domain.Comment;
import blog.springblogpractice.dto.AddArticleRequest;
import blog.springblogpractice.dto.AddCommentRequest;
import blog.springblogpractice.dto.ArticleResponse;
import blog.springblogpractice.dto.ArticleViewResponse;
import blog.springblogpractice.dto.CommentResponse;
import blog.springblogpractice.dto.UpdateArticleRequest;
import blog.springblogpractice.service.BlogService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        Article savedArticle = blogService.saveArticle(addArticleRequest);
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
    public ResponseEntity<Article> findArticleById(@PathVariable long id) {
        Article article = blogService.findArticleById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(article);
    }

    @DeleteMapping("/api/articles/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/api/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest updateArticleRequest) {
        Article article = blogService.updateById(id, updateArticleRequest);
        return ResponseEntity.ok().body(article);
    }

    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findArticleById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    ///////////////// 위 Article 아래 Comment ///////////////////////
    @PostMapping("/api/comments/{articleId}")
    @ResponseBody
    public ResponseEntity<CommentResponse> addComment(@RequestBody AddCommentRequest addCommentRequest,
                                                      @PathVariable Long articleId) {
        Article article = blogService.findArticleById(articleId);
        Comment savedComment = blogService.saveComment(addCommentRequest, article);
        article.addComment(savedComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment.toResponse());
    }

    @GetMapping("/api/comments/{articleId}/{commentId}")
    @ResponseBody
    public ResponseEntity<Comment> findComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        Article article = blogService.findArticleById(articleId);
        Comment comment = article.getComments().get(Math.toIntExact(commentId));

        return ResponseEntity.status(HttpStatus.FOUND).body(comment);
    }
}
