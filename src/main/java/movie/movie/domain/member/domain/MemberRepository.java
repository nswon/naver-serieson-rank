package movie.movie.domain.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Modifying
    @Query(value = "insert into member(email, nickname, password, role) values (:email, :nickname, :password, :role);", nativeQuery = true)
    void saveMember(@Param("email") String email,
                        @Param("nickname") String nickname,
                        @Param("password") String password,
                        @Param("role") String role);

//    Optional<Member> findByEmail(String email);

    @Query(value = "select * from member m where m.email = :email", nativeQuery = true)
    Optional<Member> getMemberByEmail(@Param("email") String email);
}
