package movie.movie.domain.comment.presentation.dto.response;

import lombok.Getter;
import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.member.domain.Member;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String nickname;
    private final String content;
    private final LocalDateTime createdDate;
    private final int likeCount;
//    private final int unLikeCount;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.nickname = comment.getMember().getNickname();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
//        this.unLikeCount = comment.getMember().getCommentUnLikes().size();
    }
}
