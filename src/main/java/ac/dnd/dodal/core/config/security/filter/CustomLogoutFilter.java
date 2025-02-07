package ac.dnd.dodal.core.config.security.filter;

import ac.dnd.dodal.core.config.security.enums.E_security_code;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
            System.out.println("🔴 인증 실패: SecurityContext에 인증 정보 없음");

            // 인증된 사용자가 아니라면 401 응답을 반환
            if (authentication == null || !authentication.isAuthenticated()) {
                System.out.println("🔴 인증 실패: SecurityContext에 인증 정보 없음");

                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json;charset=UTF-8");

                Map<String, Object> responseData = new HashMap<>();
                responseData.put("success", false);
                responseData.put("data", null);
                responseData.put("error", E_security_code.FAILURE_LOGOUT.getMessage());

                httpResponse.getWriter().write(JSONValue.toJSONString(responseData));

                httpResponse.flushBuffer();
                return; // 필터 체인을 끊고 응답을 반환
            }
        }
        filterChain.doFilter(request, response);
    }
}
