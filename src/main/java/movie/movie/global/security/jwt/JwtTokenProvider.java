package movie.movie.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private final long ACCESS_TOKEN_VALID_TIME = 30 * 24 * 60 * 60 * 1000L;
    private static final String HEADER_ACCESS_TOKEN = "ACCESS_TOKEN";

    public String createAccessToken(String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            for(Cookie c : cookies) {
                if(c.getName().equals(HEADER_ACCESS_TOKEN)) {
                    return c.getValue();
                }
            }
        }
        else {
            throw new IllegalArgumentException("쿠키가 존재하지 않습니다.");
        }
        return null;
    }

    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
