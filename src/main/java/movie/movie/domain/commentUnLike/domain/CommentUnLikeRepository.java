package movie.movie.domain.commentUnLike.domain;

import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.member.domain.Member;
import movie.movie.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentUnLikeRepository extends JpaRepository<CommentUnLike, Long> {

    boolean existsByPostAndCommentAndMember(Post post, Comment comment, Member member);
    void deleteByPostAndCommentAndMember(Post post, Comment comment, Member member);

}
