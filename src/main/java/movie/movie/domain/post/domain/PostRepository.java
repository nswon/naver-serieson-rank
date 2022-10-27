package movie.movie.domain.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from post p order by p.view", nativeQuery = true)
    List<Post> getPostByView();

    @Query(value = "select * from post p order by p.movie_date", nativeQuery = true)
    List<Post> getPostByDate();

    @Query(value = "select * from post p where p.id = :id", nativeQuery = true)
    Optional<Post> getPostById(@Param("id") Long id);
}
