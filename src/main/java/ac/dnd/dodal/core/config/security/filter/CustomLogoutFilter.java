package ac.dnd.dodal.core.config.security.filter;

import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.config.security.enums.ESecurityCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomLogoutFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 로그아웃 요청이 들어왔는 지 확인
        if ("/api/auth/sign-out".equals(httpRequest.getRequestURI()) && "POST".equalsIgnoreCase(
                httpRequest.getMethod())) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 인증된 사용자가 아니라면 401 응답을 반환
            if (authentication == null || !authentication.isAuthenticated()) {
                request.setAttribute("exception", ESecurityCode.INVALID_AUTHENTICATION);
            }
        }
        filterChain.doFilter(request, response);
    }
}