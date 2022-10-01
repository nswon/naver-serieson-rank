package movie.movie.domain.comment.presentation.dto.response;

import lombok.Getter;
import movie.movie.domain.comment.domain.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final String nickname;
    private final String content;
    private final LocalDateTime createdDate;

    public CommentResponseDto(Comment comment) {
        this.nickname = comment.getNickname();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
    }
}
