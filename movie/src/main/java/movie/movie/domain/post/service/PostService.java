package movie.movie.domain.post.service;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.post.domain.PostRepository;
import movie.movie.domain.post.presentation.dto.response.PostAllResponseDto;
import movie.movie.domain.post.presentation.dto.response.PostDetailResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public List<PostAllResponseDto> findPostByView() {
        return postRepository.getPostByView().stream()
                .map(PostAllResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostAllResponseDto> findPostByDate() {
        return postRepository.getPostByDate().stream()
                .map(PostAllResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostDetailResponseDto findPostById(Long id) {
        return postRepository.findById(id)
                .map(PostDetailResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("영화가 존재하지 않습니다."));
    }
}
