package springApp.SpringSecApp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Stream;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .flatMap(auth -> auth.equals("ROLE_USER") ? Stream.of("/user") :
                        auth.equals("ROLE_ADMIN") ? Stream.of("/admin/edit-panel") :
                                Stream.empty()).findFirst().orElse("/login");
        response.sendRedirect(redirectUrl);
    }
}
