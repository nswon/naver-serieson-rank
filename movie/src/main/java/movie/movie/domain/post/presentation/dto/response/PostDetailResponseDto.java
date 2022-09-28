package movie.movie.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import movie.movie.domain.post.domain.Post;

@Getter
public class PostDetailResponseDto {

    private String imgPath;
    private String title;
    private double average;
    private String movieDate;
    private String content;
    private int view;

    @Builder
    public PostDetailResponseDto(Post post) {
        this.imgPath = post.getImgPath();
        this.title = post.getTitle();
        this.average = post.getAverage();
        this.movieDate = post.getMovieDate();
        this.content = post.getContent();
        this.view = post.getView();
    }
}
