package movie.movie.domain.commentLike.domain;

import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean existsByPostAndComment(Post post, Comment comment);
    void deleteByPostAndComment(Post post, Comment comment);
}
