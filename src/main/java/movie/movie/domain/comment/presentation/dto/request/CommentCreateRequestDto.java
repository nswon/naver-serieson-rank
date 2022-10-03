package movie.movie.domain.comment.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;
import movie.movie.domain.comment.domain.Comment;

@Getter
public class CommentCreateRequestDto {

    private String content;

    @Builder
    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
