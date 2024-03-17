package blog.springblogpractice;

import blog.springblogpractice.external.ExampleAPIClient;
import blog.springblogpractice.repository.ArticleRepository;
import blog.springblogpractice.repository.CommentRepository;
import blog.springblogpractice.service.BlogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public Config(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @Bean
    public BlogService blogService() {
        return new BlogService(articleRepository, commentRepository, exampleAPIClient());
    }

    @Bean
    public ExampleAPIClient exampleAPIClient() {
        return new ExampleAPIClient();
    }
}
