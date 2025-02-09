package ac.dnd.dodal.core.config.security.handler;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.domain.user.enums.EUserCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomSignOutResultHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        setSuccessResponse(response);
    }

    private void setSuccessResponse(HttpServletResponse response) throws IOException {
        response.getWriter().write(JSONValue.toJSONString(ApiResponse.success()));
    }
}
