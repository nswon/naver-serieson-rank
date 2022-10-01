package movie.movie.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import movie.movie.domain.comment.presentation.dto.response.CommentResponseDto;
import movie.movie.domain.post.domain.Post;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDetailResponseDto {

    private final String imgPath;
    private final String title;
    private final double average;
    private final String movieDate;
    private final String content;
    private final int view;
    private final List<CommentResponseDto> comments;

    @Builder
    public PostDetailResponseDto(Post post) {
        this.imgPath = post.getImgPath();
        this.title = post.getTitle();
        this.average = post.getAverage();
        this.movieDate = post.getMovieDate();
        this.content = post.getContent();
        this.view = post.getView();
        this.comments = post.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
