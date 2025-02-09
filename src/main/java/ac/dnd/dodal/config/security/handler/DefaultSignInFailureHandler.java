package ac.dnd.dodal.config.security.handler;

import ac.dnd.dodal.config.security.AbstractFailureResponse;
import ac.dnd.dodal.config.security.enums.ESecurityCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultSignInFailureHandler extends AbstractFailureResponse implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        setFailureResponse(response, ESecurityCode.FAILURE_LOGIN);
    }
}
