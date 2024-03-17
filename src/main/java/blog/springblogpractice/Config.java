package blog.springblogpractice;

import blog.springblogpractice.external.ExampleAPIClient;
import blog.springblogpractice.repository.ArticleRepository;
import blog.springblogpractice.service.BlogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final ArticleRepository articleRepository;

    public Config(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Bean
    public BlogService blogService() {
        return new BlogService(articleRepository, exampleAPIClient());
    }

    @Bean
    public ExampleAPIClient exampleAPIClient() {
        return new ExampleAPIClient();
    }
}
