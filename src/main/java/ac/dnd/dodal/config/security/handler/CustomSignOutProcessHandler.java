package ac.dnd.dodal.config.security.handler;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.config.security.info.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomSignOutProcessHandler implements LogoutHandler {

    private final UserRepository userRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Long userId =  (Long) authentication.getPrincipal();
        processSignOut(userId);
    }

    protected void processSignOut(Long userId) {
        userRepository.updateRefreshToken(userId, null); // RefreshToken 삭제
        log.info("User {} sign out", userId);
    }
}
