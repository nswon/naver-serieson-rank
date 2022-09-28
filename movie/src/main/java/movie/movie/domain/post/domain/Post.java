package movie.movie.domain.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private String movieDate;

    @Column(nullable = false)
    private double average;

    @Column(nullable = false)
    private int view;

    @Builder
    public Post(String imgPath, String title, String content, int view, double average, String date) {
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
        this.view = view;
        this.average = average;
        this.movieDate = date;
    }
}
