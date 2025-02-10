package ac.dnd.dodal.config.security.filter;

import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.config.security.enums.SecurityExceptionCode;
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
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 로그아웃 요청이 들어왔는 지 확인
        if ("/api/auth/sign-out".equals(httpRequest.getRequestURI()) && "POST".equalsIgnoreCase(
                httpRequest.getMethod())) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 인증된 사용자가 아니라면 401 응답을 반환
            if (authentication == null || !authentication.isAuthenticated()) {
                httpResponse.getWriter().write(JSONValue.toJSONString(ApiResponse.failure(SecurityExceptionCode.FAILURE_LOGOUT)));
                return; // 필터 체인을 끊고 응답을 반환
            }
        }
        filterChain.doFilter(request, response);
    }
}
