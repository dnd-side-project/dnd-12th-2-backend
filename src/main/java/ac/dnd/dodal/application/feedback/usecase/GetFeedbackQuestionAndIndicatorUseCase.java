package ac.dnd.dodal.application.feedback.usecase;

import ac.dnd.dodal.domain.feedback.enums.FeedbackType;
import ac.dnd.dodal.ui.feedback.response.FeedbackQuestionAndIndicatorsResponse;

public interface GetFeedbackQuestionAndIndicatorUseCase {
    
    FeedbackQuestionAndIndicatorsResponse getFeedbackQuestionAndIndicators
        (FeedbackType type);
}
