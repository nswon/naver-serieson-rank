package movie.movie.domain.member.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;
import movie.movie.domain.member.domain.Member;

@Getter
public class MemberJoinRequestDto {

    private String email;
    private String nickname;
    private String password;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
