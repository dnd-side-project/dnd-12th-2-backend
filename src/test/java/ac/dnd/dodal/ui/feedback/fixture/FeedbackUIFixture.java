package ac.dnd.dodal.ui.feedback.fixture;

import ac.dnd.dodal.domain.feedback.enums.FeedbackIndicator;
import ac.dnd.dodal.domain.feedback.enums.FeedbackQuestion;
import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;

public class FeedbackUIFixture {

    private static final String question = FeedbackQuestion.SUCCESS_PLAN_QUESTION.getQuestion();
    private static final String indicator = FeedbackIndicator.REORGANIZE_PRIORITIES.getIndicator();

    public static CreateFeedbackRequest createFeedbackRequest() {
        return new CreateFeedbackRequest(question, indicator);
    }
}
