package blog.springblogpractice.dto;

import blog.springblogpractice.domain.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleResponse {
    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        title = article.getTitle();
        content = article.getContent();
    }
}