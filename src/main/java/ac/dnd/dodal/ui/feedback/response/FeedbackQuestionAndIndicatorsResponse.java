package ac.dnd.dodal.ui.feedback.response;

import java.util.List;

public record FeedbackQuestionAndIndicatorsResponse(
    List<FeedbackQuestionAndIndicatorElement> elements
) {
}
