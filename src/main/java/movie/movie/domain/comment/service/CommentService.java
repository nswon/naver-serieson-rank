package movie.movie.domain.comment.service;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.comment.domain.CommentRepository;
import movie.movie.domain.comment.presentation.dto.request.CommentCreateRequestDto;
import movie.movie.domain.member.domain.Member;
import movie.movie.domain.member.domain.MemberRepository;
import movie.movie.domain.post.domain.Post;
import movie.movie.domain.post.domain.PostRepository;
import movie.movie.global.security.jwt.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean createComment(Long postId, CommentCreateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("영화가 존재하지 않습니다."));

        Member member = memberRepository.getMemberByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("로그인 후 이용해주세요."));

        Comment comment = requestDto.toEntity();
        comment.confirmPost(post);
        comment.confirmMember(member);

        commentRepository.saveComment(comment.getContent(), comment.getMember().getId(), comment.getPost().getId());
        return true;
    }
}
