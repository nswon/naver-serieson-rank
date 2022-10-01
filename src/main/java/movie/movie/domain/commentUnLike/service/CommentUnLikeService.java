package movie.movie.domain.commentUnLike.service;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.comment.domain.Comment;
import movie.movie.domain.comment.domain.CommentRepository;
import movie.movie.domain.commentLike.domain.CommentLikeRepository;
import movie.movie.domain.commentUnLike.domain.CommentUnLike;
import movie.movie.domain.commentUnLike.domain.CommentUnLikeRepository;
import movie.movie.domain.member.domain.Member;
import movie.movie.domain.member.domain.MemberRepository;
import movie.movie.domain.post.domain.Post;
import movie.movie.domain.post.domain.PostRepository;
import movie.movie.global.security.jwt.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentUnLikeService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final CommentUnLikeRepository commentUnLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    public void unLike(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("영화가 존재하지 않습니다."));

        Member member = memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("로그인 후 이용해주세요."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if(!commentLikeRepository.existsByPostAndCommentAndMember(post, comment, member)) {
            throw new IllegalArgumentException("이미 공감한 글입니다.");
        }

        if(!commentUnLikeRepository.existsByPostAndCommentAndMember(post, comment, member)) {
            commentUnLikeRepository.deleteByPostAndCommentAndMember(post, comment, member);
        }

        CommentUnLike commentUnLike = CommentUnLike.builder()
                .post(post)
                .comment(comment)
                .member(member)
                .build();

        commentUnLike.confirmPost(post);
        commentUnLike.confirmComment(comment);
        commentUnLike.confirmMember(member);

        commentUnLikeRepository.save(commentUnLike);
    }
}
