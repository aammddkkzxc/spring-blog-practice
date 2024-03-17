package blog.springblogpractice.dto;

import blog.springblogpractice.domain.Article;
import blog.springblogpractice.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddCommentRequest {
    private String body;

    public Comment toEntity(Article article) {
        return Comment.builder().article(article).body(body).build();
    }
}
