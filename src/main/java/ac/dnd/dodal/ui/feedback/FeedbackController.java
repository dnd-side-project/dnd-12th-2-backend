package ac.dnd.dodal.ui.feedback;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.domain.feedback.enums.FeedbackType;
import ac.dnd.dodal.application.feedback.usecase.GetFeedbackQuestionAndIndicatorUseCase;
import ac.dnd.dodal.ui.feedback.response.FeedbackQuestionAndIndicatorsResponse;

@RestController
@RequestMapping("api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final GetFeedbackQuestionAndIndicatorUseCase getFeedbackQuestionAndIndicatorUseCase;

    @GetMapping
    public ApiResponse<FeedbackQuestionAndIndicatorsResponse> getFeedbackQuestionAndIndicator(
            @RequestParam FeedbackType type) {

        return ApiResponse.success
            (getFeedbackQuestionAndIndicatorUseCase.getFeedbackQuestionAndIndicators(type));
    }
}
