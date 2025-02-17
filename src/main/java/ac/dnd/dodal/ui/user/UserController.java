package ac.dnd.dodal.ui.user;

import ac.dnd.dodal.application.user.usecase.CreateUserAnswerUseCase;
import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.user.request.CreateUserAnswerRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final CreateUserAnswerUseCase createUserAnswerUseCase;

    @PostMapping("/onboarding")
    public ApiResponse<?> createUserAnswer(
            @UserId Long userId,
            @RequestBody CreateUserAnswerRequestDto createUserAnswerRequestDto
    ) {
        createUserAnswerUseCase.createUserAnswer(userId, createUserAnswerRequestDto);
        return ApiResponse.success();
    }
}
