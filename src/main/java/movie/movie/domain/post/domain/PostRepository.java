package movie.movie.domain.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from post p order by p.view desc limit 0, 5", nativeQuery = true)
    List<Post> getPostByView();

    @Query(value = "select * from post p order by p.movie_date desc limit 0, 5", nativeQuery = true)
    List<Post> getPostByDate();
}
