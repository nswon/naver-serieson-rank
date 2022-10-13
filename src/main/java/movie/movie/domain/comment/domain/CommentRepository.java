package movie.movie.domain.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query(value = "insert into comment(content, member_id, post_id) values (:content, :member_id, :post_id);", nativeQuery = true)
    void saveComment(@Param("content") String content,
                     @Param("member_id") Long member_id,
                     @Param("post_id") Long post_id);
}
