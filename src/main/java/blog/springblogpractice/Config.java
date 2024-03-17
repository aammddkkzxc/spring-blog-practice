package blog.springblogpractice;

import blog.springblogpractice.external.ExampleAPIClient;
import blog.springblogpractice.repository.BlogRepository;
import blog.springblogpractice.service.BlogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final BlogRepository blogRepository;

    public Config(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Bean
    public BlogService blogService() {
        return new BlogService(blogRepository, exampleAPIClient());
    }

    @Bean
    public ExampleAPIClient exampleAPIClient() {
        return new ExampleAPIClient();
    }
}
