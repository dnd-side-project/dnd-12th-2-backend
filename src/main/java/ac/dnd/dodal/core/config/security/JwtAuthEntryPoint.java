package ac.dnd.dodal.core.config.security;

import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.config.security.enums.ESecurityCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.getWriter().write(JSONValue.toJSONString(ApiResponse.failure(ESecurityCode.ACCESS_DENIED_ERROR)));
    }
}
