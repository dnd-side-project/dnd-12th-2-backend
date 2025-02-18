package ac.dnd.dodal.application.feedback.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.domain.feedback.enums.FeedbackType;
import ac.dnd.dodal.domain.feedback.model.FeedbackQuestion;
import ac.dnd.dodal.domain.feedback.model.DefaultFeedbackIndicator;
import ac.dnd.dodal.application.feedback.usecase.GetFeedbackQuestionAndIndicatorUseCase;
import ac.dnd.dodal.ui.feedback.response.FeedbackQuestionAndIndicatorsResponse;
import ac.dnd.dodal.ui.feedback.response.FeedbackQuestionAndIndicatorElement;

@Service
@RequiredArgsConstructor
public class FeedbackFacadeService implements GetFeedbackQuestionAndIndicatorUseCase {

    private final FeedbackQuestionService feedbackQuestionService;
    private final DefaultFeedbackIndicatorService defaultFeedbackIndicatorService;

    // TODO: userID와 함께 기타 지표까지 조회하는 로직 추가
    @Override
    public FeedbackQuestionAndIndicatorsResponse 
            getFeedbackQuestionAndIndicators(FeedbackType type) {
        List<FeedbackQuestion> questions = feedbackQuestionService.findAllByType(type);
        List<FeedbackQuestionAndIndicatorElement> elements = new ArrayList<>();

        for (FeedbackQuestion question : questions) {
            List<DefaultFeedbackIndicator> indicators = defaultFeedbackIndicatorService
                .findAllByQuestionAndType(question.getQuestion(), type);
            elements.add(FeedbackQuestionAndIndicatorElement.of(question, indicators));
        }

        return new FeedbackQuestionAndIndicatorsResponse(elements);
    }
}
