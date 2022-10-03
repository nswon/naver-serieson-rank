package movie.movie.global;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test(HttpServletResponse response) {
        Cookie cookie = new Cookie("please", "please");
        response.addCookie(cookie);
        return "확인좀";
    }
}
