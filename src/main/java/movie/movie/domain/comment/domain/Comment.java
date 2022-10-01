package movie.movie.domain.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie.movie.domain.commentLike.domain.CommentLike;
import movie.movie.domain.post.domain.Post;
import movie.movie.global.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "post")
    private final List<CommentLike> commentLikes = new ArrayList<>();

    @Builder
    public Comment(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addComment(this);
    }

    public void addCommentLike(CommentLike commentLike) {
        commentLikes.add(commentLike);
    }
}
