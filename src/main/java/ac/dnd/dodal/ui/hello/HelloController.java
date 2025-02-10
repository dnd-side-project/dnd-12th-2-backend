package ac.dnd.dodal.ui.hello;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @GetMapping
    public ApiResponse<Object> hello() {
        return ApiResponse.success("Hello!");
    }

    @GetMapping("/test")
    public ApiResponse<Object> test(@UserId Long userId) {
        return ApiResponse.success(userId);
    }
}
