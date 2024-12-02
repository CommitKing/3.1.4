package springApp.SpringSecApp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/login";
        Set<String> grantedAuthorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        if (grantedAuthorities.contains("ROLE_ADMIN")) {
            redirectUrl = "/admin";
        } else if (grantedAuthorities.contains("ROLE_USER")) {
            redirectUrl = "/user";
        }
        /*for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if ("ROLE_USER".equals(role)) {
                redirectUrl = "/user";
                break;
            } else if ("ROLE_ADMIN".equals(role)) {
                redirectUrl = "/admin";
                break;
            }
        }*/
        response.sendRedirect(redirectUrl);
    }
}
