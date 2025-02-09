package ac.dnd.dodal.config.security;

import ac.dnd.dodal.common.response.ExceptionDto;
import ac.dnd.dodal.config.security.enums.ESecurityCode;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFailureResponse {
    protected void setFailureResponse(HttpServletResponse response, ESecurityCode securityCode) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", null);
        result.put("error", new ExceptionDto(securityCode.getCode(), securityCode.getMessage()));

        response.getWriter().write(JSONValue.toJSONString(result));
    }
}
