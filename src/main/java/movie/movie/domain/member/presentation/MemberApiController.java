package movie.movie.domain.member.presentation;

import lombok.RequiredArgsConstructor;
import movie.movie.domain.member.presentation.dto.request.MemberJoinRequestDto;
import movie.movie.domain.member.presentation.dto.request.MemberLoginRequestDto;
import movie.movie.domain.member.presentation.dto.response.MemberResponseDto;
import movie.movie.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
    public String login(@RequestBody MemberLoginRequestDto requestDto, HttpServletResponse res) {
        MemberResponseDto response = memberService.login(requestDto);
        Cookie cookie = new Cookie("ACCESS_TOKEN", response.getAccessToken());
        cookie.setHttpOnly(false);
        cookie.setPath("/");
//        cookie.setDomain("localhost");
        res.addCookie(cookie);
        return response.getNickname();
    }
}
