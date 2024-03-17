package blog.springblogpractice.controller;

import blog.springblogpractice.domain.Article;
import blog.springblogpractice.service.BlogService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JsonParseTestController {
    private final BlogService blogService;

    public JsonParseTestController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/api/test")
    public List<Article> test() {
        return blogService.saveBulkArticles();
    }
}
