package movie.movie.domain.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie.movie.domain.commentLike.domain.CommentLike;
import movie.movie.domain.commentUnLike.domain.CommentUnLike;
import movie.movie.domain.member.domain.Member;
import movie.movie.domain.post.domain.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private final List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    private final List<CommentUnLike> commentUnLikes = new ArrayList<>();

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addComment(this);
    }

    public void confirmMember(Member member) {
        this.member = member;
        member.addComment(this);
    }

    public void addCommentLike(CommentLike commentLike) {
        commentLikes.add(commentLike);
    }

    public void addCommentUnLike(CommentUnLike commentUnLike) {
        commentUnLikes.add(commentUnLike);
    }
}
