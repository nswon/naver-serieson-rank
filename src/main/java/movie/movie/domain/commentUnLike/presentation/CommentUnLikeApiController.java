package movie.movie.domain.commentUnLike.presentation;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.commentUnLike.service.CommentUnLikeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unLike")
public class CommentUnLikeApiController {

    private final CommentUnLikeService commentUnLikeService;

    @PutMapping("/{postId}/{commentId}/unlike")
    public void unlike(@PathVariable("postId") Long postId,
                       @PathVariable("commentId") Long commentId) {
        commentUnLikeService.unLike(postId, commentId);
    }
}
