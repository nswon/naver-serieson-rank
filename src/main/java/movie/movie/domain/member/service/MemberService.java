package movie.movie.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.movie.domain.member.domain.Member;
import movie.movie.domain.member.domain.MemberRepository;
import movie.movie.domain.member.presentation.dto.request.MemberJoinRequestDto;
import movie.movie.domain.member.presentation.dto.request.MemberLoginRequestDto;
import movie.movie.domain.member.presentation.dto.response.MemberResponseDto;
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
        if(memberRepository.getMemberByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member member = memberRepository.save(requestDto.toEntity());
//        memberRepository.saveMember(requestDto.getEmail(), requestDto.getNickname(), requestDto.getPassword(), "ROLE_USER");
        member.addUserAuthority();
        member.encodedPassword(passwordEncoder);
    }

    @Transactional
    public MemberResponseDto login(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.getMemberByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if(!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
        return MemberResponseDto.builder()
                .nickname(member.getNickname())
                .accessToken(accessToken)
                .build();
    }
}
