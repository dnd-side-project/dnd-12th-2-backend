package ac.dnd.dodal.ui.feedback.fixture;

import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;

public class FeedbackUIFixture {

    public static CreateFeedbackRequest createFeedbackRequest() {
        return new CreateFeedbackRequest("question", "indicator");
    }
}
