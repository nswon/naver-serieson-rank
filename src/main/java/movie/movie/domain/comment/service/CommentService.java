package movie.movie.domain.comment.service;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.comment.domain.CommentRepository;
import movie.movie.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import movie.movie.domain.post.domain.Post;
import movie.movie.domain.post.domain.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createComment(Long postId, CommentCreateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("영화가 존재하지 않습니다."));

        Comment comment = requestDto.toEntity();
        comment.confirmPost(post);

//        commentRepository.saveComment(comment.getCreatedDate(), comment.getNickname(), comment.getContent(), comment.getPost().getId());
        commentRepository.save(comment);
    }
}
