package ac.dnd.dodal.ui.onboarding;

import ac.dnd.dodal.application.onboarding.usecase.GetQuestionsAndAnswersUseCase;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.onboarding.response.GetOnBoardingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onboarding")
public class OnBoardingController {

    private final GetQuestionsAndAnswersUseCase getQuestionsAndAnswersUseCase;

    @GetMapping("")
    public ApiResponse<List<GetOnBoardingResponseDto>> getOnboardingQuestionAndAnswer(){
        return ApiResponse.success(getQuestionsAndAnswersUseCase.getQuestionsAndAnswers());
    }
}