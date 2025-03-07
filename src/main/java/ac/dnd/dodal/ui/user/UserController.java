package ac.dnd.dodal.ui.user;

import ac.dnd.dodal.application.user.usecase.CheckIsDoneUserAnswerUseCase;
import ac.dnd.dodal.application.user.usecase.CreateUserAnswerUseCase;
import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.user.request.CreateUserAnswerRequestDto;
import ac.dnd.dodal.ui.user.response.GetUserAnswerResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final CreateUserAnswerUseCase createUserAnswerUseCase;
    private final CheckIsDoneUserAnswerUseCase checkIsDoneUserAnswerUseCase;

    // 사용자가 온보딩을 완료했는 지에 대한 여부와 완료했다면 사용자의 답변을 반환
    @GetMapping("/onboarding")
    public ApiResponse<GetUserAnswerResponseDto> getOnboardingQuestions(@UserId Long userId) {
        return ApiResponse.success(checkIsDoneUserAnswerUseCase.checkIsDoneUserAnswer(userId));
    }

    @PostMapping("/onboarding")
    public ApiResponse<?> createUserAnswer(
            @UserId Long userId,
            @RequestBody CreateUserAnswerRequestDto createUserAnswerRequestDto
    ) {
        createUserAnswerUseCase.createUserAnswer(userId, createUserAnswerRequestDto);
        return ApiResponse.success();
    }
}
