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
//    private final int likeCount;
//    private final int unLikeCount;

    /*
    게시글에 내가 작성한 댓글의 좋아요 수
    select * from (select * from like) A inner join (select * from comment where writer=) B on A.comment_id = B.id
     */

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.nickname = comment.getMember().getNickname();
        this.content = comment.getContent();
//        this.unLikeCount = comment.getMember().getCommentUnLikes().size();
    }
}
