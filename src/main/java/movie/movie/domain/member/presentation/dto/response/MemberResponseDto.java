package movie.movie.domain.member.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
public class MemberResponseDto {

    private String nickname;
    private String accessToken;

    @Builder
    public MemberResponseDto(String nickname, String accessToken) {
        this.nickname = nickname;
        this.accessToken = accessToken;
    }
}
