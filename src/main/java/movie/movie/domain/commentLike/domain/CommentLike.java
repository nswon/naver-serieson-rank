package movie.movie.domain.commentLike.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.member.domain.Member;
import movie.movie.domain.post.domain.Post;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public CommentLike(Post post, Comment comment, Member member) {
        this.post = post;
        this.comment = comment;
        this.member = member;
    }

    public void confirmComment(Comment comment) {
        this.comment = comment;
        comment.addCommentLike(this);
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addCommentLike(this);
    }

    public void confirmMember(Member member) {
        this.member = member;
        member.addCommentLike(this);
    }


}
