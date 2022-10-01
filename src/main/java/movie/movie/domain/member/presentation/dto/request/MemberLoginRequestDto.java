package movie.movie.domain.member.presentation.dto.request;

import lombok.Getter;

@Getter
public class MemberLoginRequestDto {

    private String email;
    private String password;
}
