package movie.movie.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.movie.domain.member.domain.Member;
import movie.movie.domain.member.domain.MemberRepository;
import movie.movie.domain.member.presentation.dto.request.MemberJoinRequestDto;
import movie.movie.domain.member.presentation.dto.request.MemberLoginRequestDto;
import movie.movie.global.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void join(MemberJoinRequestDto requestDto) {
        log.info(">>>>>>>>>>회원가입 됨");
        if(memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member member = memberRepository.save(requestDto.toEntity());
        member.encodedPassword(passwordEncoder);
        member.addUserAuthority();
    }

    @Transactional
    public String login(MemberLoginRequestDto requestDto) {
        log.info(">>>>>>>>>>>>>>>로그인 시작");
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if(!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
    }
}
