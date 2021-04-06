package SpringDB.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class RestAuthSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long validityTime;
    private final String secret;

    public RestAuthSuccesHandler(@Value("${jwt.validityTime}") long validityTime,@Value("${jwt.validityTime}") String secret) {
        this.validityTime = validityTime;
        this.secret = secret;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetails main = (UserDetails) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(main.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + validityTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization", "Bearer " + token);
    }
}
