package movie.movie.domain.commentLike.service;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.comment.domain.CommentRepository;
import movie.movie.domain.commentLike.domain.CommentLike;
import movie.movie.domain.commentLike.domain.CommentLikeRepository;
import movie.movie.domain.post.domain.Post;
import movie.movie.domain.post.domain.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void like(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("영화가 존재하지 않습니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재히지 않습니다."));

        if(commentLikeRepository.existsByPostAndComment(post, comment)) {
            commentLikeRepository.deleteByPostAndComment(post, comment);
        }
        CommentLike commentLike = CommentLike.builder()
                .post(post)
                .comment(comment)
                .build();

        commentLike.confirmPost(post);
        commentLike.confirmComment(comment);
        commentLikeRepository.save(commentLike);
    }
}
