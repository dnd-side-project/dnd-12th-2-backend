package ac.dnd.dodal.ui.user_guide;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.application.user_guide.usecase.GetUserGuideUseCase;
import ac.dnd.dodal.ui.user_guide.response.UserGuideResponse;

@RestController
@RequestMapping("/api/user-guides")
@RequiredArgsConstructor
public class UserGuideController {

    private final GetUserGuideUseCase getUserGuideUseCase;

    @GetMapping
    public ApiResponse<UserGuideResponse> getUserGuide(
        @UserId Long userId,
        @RequestParam String type) {
        GuideType guideType = GuideType.of(type);

        return ApiResponse.success(getUserGuideUseCase.getUserGuide(userId, guideType));
    }
}
