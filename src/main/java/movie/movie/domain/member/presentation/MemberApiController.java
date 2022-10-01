package movie.movie.domain.member.presentation;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.member.presentation.dto.request.MemberJoinRequestDto;
import movie.movie.domain.member.presentation.dto.request.MemberLoginRequestDto;
import movie.movie.domain.member.presentation.dto.response.TokenResponseDto;
import movie.movie.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public void join(@RequestBody MemberJoinRequestDto requestDto) {
        memberService.join(requestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody MemberLoginRequestDto requestDto) {
        return memberService.login(requestDto);
    }
}
