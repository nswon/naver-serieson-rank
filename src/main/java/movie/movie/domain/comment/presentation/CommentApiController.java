package movie.movie.domain.comment.presentation;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import movie.movie.domain.comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/{postId}/new")
    public void createComment(@PathVariable("postId") Long postId,
                              @RequestBody CommentCreateRequestDto requestDto) {
        commentService.createComment(postId, requestDto);
    }

}
