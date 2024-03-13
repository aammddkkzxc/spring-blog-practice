package blog.springblogpractice.service;

import blog.springblogpractice.domain.Article;
import blog.springblogpractice.dto.AddArticleRequest;
import blog.springblogpractice.repository.BlogRepository;
import java.util.List;
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

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}
