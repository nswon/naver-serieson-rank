package movie.movie.domain.post.presentation.dto.response;

import lombok.Getter;
import movie.movie.domain.post.domain.Post;

@Getter
public class PostAllResponseDto {

    private final Long id;
    private final String imgPath;
    private final String title;
    private final double average;
    private final String movieDate;

    public PostAllResponseDto(Post post) {
        this.id = post.getId();
        this.imgPath = post.getImgPath();
        this.title = post.getTitle();
        this.average = post.getAverage();
        this.movieDate = post.getMovieDate();
    }
}
