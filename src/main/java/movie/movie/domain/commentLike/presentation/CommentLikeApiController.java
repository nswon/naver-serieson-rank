package movie.movie.domain.commentLike.presentation;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.commentLike.service.CommentLikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class CommentLikeApiController {

    private final CommentLikeService commentLikeService;

    @PutMapping("/{postId}/{commentId}/like")
    public void like(@PathVariable("postId") Long postId,
                     @PathVariable("commentId") Long commentId) {
        commentLikeService.like(postId, commentId);
    }
}
