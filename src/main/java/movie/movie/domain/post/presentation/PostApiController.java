package movie.movie.domain.post.presentation;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.post.presentation.dto.response.PostAllResponseDto;
import movie.movie.domain.post.presentation.dto.response.PostDetailResponseDto;
import movie.movie.domain.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostApiController {

    private final PostService postService;

    @GetMapping("/view")
    public List<PostAllResponseDto> findPostByView() {
        return postService.findPostByView();
    }

    @GetMapping("/date")
    public List<PostAllResponseDto> findPostByDate() {
        return postService.findPostByDate();
    }

    @GetMapping("/{postId}")
    public PostDetailResponseDto findPostById(@PathVariable("postId") Long postId) {
        return postService.findPostById(postId);
    }
}
