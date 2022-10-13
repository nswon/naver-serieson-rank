package movie.movie.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.commentLike.domain.CommentLike;
import movie.movie.domain.commentUnLike.domain.CommentUnLike;
import movie.movie.domain.post.domain.Post;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<CommentUnLike> commentUnLikes = new ArrayList<>();

    @Builder
    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void encodedPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void addUserAuthority() {
        this.role = Role.ROLE_USER;
    }

    public void addCommentLike(CommentLike commentLike) {
        commentLikes.add(commentLike);
    }

    public void addCommentUnLike(CommentUnLike commentUnLike) {
        commentUnLikes.add(commentUnLike);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
