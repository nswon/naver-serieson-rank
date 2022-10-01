package movie.movie.domain.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query(value = "insert into comment(nickname, content) values (:created_date, :nickname, :name :post_id);", nativeQuery = true)
    void saveComment(@Param("created_date") LocalDateTime created_date,
                     @Param("nickname") String nickname,
                     @Param("name") String name,
                     @Param("post_id") Long post_id);
}
