package blog.springblogpractice.dto;

import blog.springblogpractice.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddArticleRequest {
    private final String title;
    private final String content;

    public Article toEntity() {
        return Article.builder().title(title).content(content).build();
    }
}
