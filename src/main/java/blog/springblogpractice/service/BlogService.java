package blog.springblogpractice.service;

import blog.springblogpractice.domain.Article;
import blog.springblogpractice.dto.AddArticleRequest;
import blog.springblogpractice.repository.BlogRepository;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest addArticleRequest) {
        return blogRepository.save(addArticleRequest.toEntity());
    }
}
