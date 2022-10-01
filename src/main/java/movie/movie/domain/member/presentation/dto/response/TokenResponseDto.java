package movie.movie.domain.member.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
public class TokenResponseDto {

    private Cookie cookie;

    @Builder
    public TokenResponseDto(Cookie cookie) {
        this.cookie = cookie;
    }
}
