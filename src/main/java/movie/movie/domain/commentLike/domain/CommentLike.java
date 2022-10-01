package movie.movie.domain.commentLike.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie.movie.domain.comment.domain.Comment;
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

    @Builder
    public CommentLike(Post post, Comment comment) {
        this.post = post;
        this.comment = comment;
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addCommentLike(this);
    }

    public void confirmComment(Comment comment) {
        this.comment = comment;
        comment.addCommentLike(this);
    }
}
