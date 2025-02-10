package ac.dnd.dodal.core.config.security.handler;

import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.config.security.enums.SecurityExceptionCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        setFailureResponse(response, SecurityExceptionCode.ACCESS_DENIED_ERROR);
    }

    protected void setFailureResponse(HttpServletResponse response, SecurityExceptionCode securityCode) throws IOException {
        response.getWriter().write(JSONValue.toJSONString(ApiResponse.failure(securityCode)));
    }
}