package ac.dnd.dodal.ui.feedback.response;

import java.util.List;
import java.util.stream.Collectors;

import ac.dnd.dodal.domain.feedback.model.FeedbackQuestion;
import ac.dnd.dodal.domain.feedback.model.DefaultFeedbackIndicator;

public record FeedbackQuestionAndIndicatorElement(
    String question,
    String description,
    int order,
    List<String> indicators
) {
    
    public static FeedbackQuestionAndIndicatorElement of(
        FeedbackQuestion question,
        List<DefaultFeedbackIndicator> indicators
    ) {
        return new FeedbackQuestionAndIndicatorElement(
            question.getQuestion(),
            question.getDescription(),
            question.getOrderNumber(),
            indicators.stream()
                .map(DefaultFeedbackIndicator::getIndicator)
                .collect(Collectors.toList())
        );
    }
}
