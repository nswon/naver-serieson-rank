package movie.movie.domain.commentUnLike.domain;

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
public class CommentUnLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private long count;

    @Builder
    public CommentUnLike(Post post, Member member, Comment comment) {
        this.post = post;
        this.member = member;
        this.comment = comment;
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addCommentUnLike(this);
    }

    public void confirmMember(Member member) {
        this.member = member;
        member.addCommentUnLike(this);
    }

    public void confirmComment(Comment comment) {
        this.comment = comment;
        comment.addCommentUnLike(this);
    }

    public void addCount() {
        this.count++;
    }
}
